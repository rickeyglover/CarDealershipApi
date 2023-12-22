package com.pluralsight.dealership;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DataManager {
    public static Scanner scanner = new Scanner(System.in);

    static DataSource dataSource;

    public DataManager(DataSource dataSource) {
        DataManager.dataSource = dataSource;
    }

    public static void getVehicle(ResultSet resultSet) throws SQLException {
        String vin = resultSet.getString("VIN");
        double price = resultSet.getDouble("Price");
        String make = resultSet.getString("Make");
        String model = resultSet.getString("Model");
        int year = resultSet.getInt("Year");
        String color = resultSet.getString("Color");
        int odometer = resultSet.getInt("Mileage");
        String type = resultSet.getString("Type");

        System.out.printf("""
                    
                    VIN: %s
                    Price: %.2f
                    Make: %s
                    Model: %s
                    Year: %d
                    Color: %s
                    Odometer: %d
                    Type: %s
                    -------------------------
                    """,
                vin, price, make, model, year, color, odometer, type);
    }
    public static void priceRange() {
        System.out.println("Enter lowest and highest price");

        System.out.println("Lowest price: ");
        String lowest = scanner.nextLine().trim();

        System.out.println("\nHighest price: ");
        String highest = scanner.nextLine().trim();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM vehicles WHERE Price BETWEEN ? AND ?")
        ) {
            preparedStatement.setString(1, lowest);
            preparedStatement.setString(2, highest);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    getVehicle(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Cars retrieved: %d\n");
    }

    public static void makeModel() {
        System.out.println("Enter Make and Model");

        System.out.println("Make: ");
        String make = scanner.nextLine().trim();

        System.out.println("\nModel: ");
        String model = scanner.nextLine().trim();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM vehicles WHERE Make = ? AND Model = ?")
        ) {
            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    getVehicle(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Cars retrieved: %d\n");
    }

    public static void yearRange() {
        System.out.println("Enter lowest and highest year");

        System.out.println("Lowest year: ");
        int lowest = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("\nHighest year: ");
        int highest = Integer.parseInt(scanner.nextLine().trim());

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM vehicles WHERE Year BETWEEN ? AND ?")
        ) {
            preparedStatement.setInt(1, lowest);
            preparedStatement.setInt(2, highest);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    getVehicle(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Cars retrieved: %d\n");
    }

    public static void carColor() {
        System.out.println("Enter a car color");

        System.out.println("Car Color: ");
        String carColor = scanner.nextLine().trim();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM vehicles WHERE Color = ?")
        ) {
            preparedStatement.setString(1, carColor);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    getVehicle(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Cars retrieved: %d\n");
    }

    public static void mileageRange() {
        System.out.println("Enter lowest and highest mileage");

        System.out.println("Lowest mileage: ");
        String lowest = scanner.nextLine().trim();

        System.out.println("\nHighest mileage: ");
        String highest = scanner.nextLine().trim();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM vehicles WHERE Mileage BETWEEN ? AND ?")
        ) {
            preparedStatement.setString(1, lowest);
            preparedStatement.setString(2, highest);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    getVehicle(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Cars retrieved: %d\n");
    }

    public static void carType() {
        System.out.println("Enter a car type");

        System.out.println("Car type: ");
        String carType = scanner.nextLine().trim();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM vehicles WHERE Type = ?")
        ) {
            preparedStatement.setString(1, carType);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    getVehicle(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Cars retrieved: %d\n");
    }
}