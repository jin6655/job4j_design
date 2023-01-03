package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password"));
    }

    private void out(String sql, String text, String tableName) throws SQLException {
        connection.createStatement().execute(sql);
        System.out.println(text + " " + tableName);
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format("create table if not exists %s()", tableName);
        out(sql, "Создана таблица", tableName);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format("drop table %s", tableName);
        out(sql, "Удалена таблица", tableName);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format("alter table %s add %s %s", tableName, columnName, type);
        out(sql, "Добавлен столбец", tableName);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format("alter table %s drop column %s", tableName, columnName);
        out(sql, "Удалён столбец", tableName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format("alter table %s rename column %s to %s", tableName, columnName, newColumnName);
        out(sql, "Переименован столбец", tableName);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (BufferedReader read = new BufferedReader(new FileReader("settingSql.properties"))) {
            properties.load(read);
        }
        TableEditor tableEditor = new TableEditor(properties);
        try (Connection connect = tableEditor.connection) {
            try (Statement statement = connect.createStatement()) {
                tableEditor.connection = connect;
                tableEditor.createTable("red_table");
                System.out.println(getTableScheme(tableEditor.connection, "red_table"));
                tableEditor.addColumn("red_table", "name", "text");
                System.out.println(getTableScheme(tableEditor.connection, "red_table"));
                tableEditor.addColumn("red_table", "age", "int");
                System.out.println(getTableScheme(tableEditor.connection, "red_table"));
                tableEditor.dropColumn("red_table", "age");
                System.out.println(getTableScheme(tableEditor.connection, "red_table"));
                tableEditor.addColumn("red_table", "age", "int");
                System.out.println(getTableScheme(tableEditor.connection, "red_table"));
                tableEditor.renameColumn("red_table", "age", "new_age");
                System.out.println(getTableScheme(tableEditor.connection, "red_table"));
                tableEditor.dropTable("red_table");
            }
        }
    }

}
