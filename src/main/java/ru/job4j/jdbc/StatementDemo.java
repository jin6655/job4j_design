package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;
import java.util.StringJoiner;

public class StatementDemo {

    private static Connection getConnection() throws Exception {
        Config conf = new Config("app.properties");
        conf.load();
        Class.forName(conf.value("driver_class"));
        return DriverManager.getConnection(conf.value("url"), conf.value("username"), conf.value("password"));
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

    public static void main(String[] args) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format("create table if not exists demo_table(%s, %s);",
                        "id serial primary key",
                        "name text"
                );
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));
            }
        }
    }

}
