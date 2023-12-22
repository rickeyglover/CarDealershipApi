package com.pluralsight.dealership;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.pluralsight.dealership.DataManager.dataSource;

public class Add_Remove {
    public static Scanner scanner = new Scanner(System.in);

    public static void addRemoveScreen() {
        String input;
        while (true) {
            System.out.println("""
                    \nLog Screen
                     1) Add Car
                     2) Remove Car
                     0) Exit
                    Select an option:""");

            input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    addCar(dataSource);
                    break;
                case "2":
                    removeCar(dataSource);
                    break;
                case "0":
                    System.out.println("Exiting. Thank you for using D & B Used Cars.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
                    break;
            }
            System.out.println("Do you want to perform another add or removal? (Y/N)");
            String continueOption = scanner.nextLine().trim();
            if (!continueOption.equalsIgnoreCase("Y")) {
                System.out.println("Exiting. Thank you for using D & B Used Cars.");
                System.exit(0);
            }
        }
    }

    public static void addCar(DataSource dataSource) {
        System.out.println("Enter a car");

        System.out.println("Vin: ");
        String vin = scanner.nextLine().trim();

        System.out.println("Price: ");
        String price = scanner.nextLine().trim();

        System.out.println("Make: ");
        String make = scanner.nextLine().trim();

        System.out.println("Model: ");
        String model = scanner.nextLine().trim();

        System.out.println("Year: ");
        String year = scanner.nextLine().trim();

        System.out.println("Color: ");
        String color = scanner.nextLine().trim();

        System.out.println("Mileage: ");
        String mileage = scanner.nextLine().trim();

        System.out.println("\nType: ");
        String type = scanner.nextLine().trim();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "INSERT INTO vehicles (VIN, Price, Make, Model, Year, Color, Mileage, Type) " +
                                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                             Statement.RETURN_GENERATED_KEYS);
        ) {

            preparedStatement.setString(1, vin);
            preparedStatement.setString(2, price);
            preparedStatement.setString(3, make);
            preparedStatement.setString(4, model);
            preparedStatement.setString(5, year);
            preparedStatement.setString(6, color);
            preparedStatement.setString(7, mileage);
            preparedStatement.setString(8, type);

            int rows = preparedStatement.executeUpdate();

            System.out.printf("Car added %d\n", rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeCar(DataSource dataSource) {
        System.out.println("Enter VIN of the car to remove:");
        String vin = scanner.nextLine().trim();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM vehicles WHERE VIN = ?")) {

            preparedStatement.setString(1, vin);

            int rows = preparedStatement.executeUpdate();

            System.out.printf("Car row deleted: %d\n", rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}