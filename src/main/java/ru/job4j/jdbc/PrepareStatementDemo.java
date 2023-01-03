package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrepareStatementDemo {

    private Connection connection;

    public PrepareStatementDemo() throws Exception {
        initConnection();
    }

    public void initConnection() throws Exception {
        Config conf = new Config("app.proerties");
        conf.load();
        Class.forName(conf.value("driver_class"));
        connection = DriverManager.getConnection(conf.value("url"), conf.value("username"), conf.value("password"));
    }

    public void insert(City city) {
        try (PreparedStatement statement = connection.prepareStatement("insert into cities(name, population) values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1,city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1   ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement("update cities set name = ?, population = ? where id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement("delete from cities where id = ?")) {
            statement.setInt(1, id);
            result  = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<City> findAll () {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select * from cities")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(resultSet.getInt("id"), resultSet.getString("name1"), resultSet.getInt("population")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

}
