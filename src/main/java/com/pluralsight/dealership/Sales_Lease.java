package com.pluralsight.dealership;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static com.pluralsight.dealership.DataManager.dataSource;

public class Sales_Lease {
    public static Scanner scanner = new Scanner(System.in);

    public static void salesLeaseScreen() {
        String input;
        while (true) {
            System.out.println("""
                    \nLog Screen
                     1) Car Sale
                     2) Car Lease
                     0) Exit
                    Select an option:""");

            input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    carSale(dataSource);
                    break;
                case "2":
                    carLease(dataSource);
                    break;
                case "0":
                    System.out.println("Exiting. Thank you for using D & B Used Cars.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
                    break;
            }
            System.out.println("Do you want to perform another Sale or Lease? (Y/N)");
            String continueOption = scanner.nextLine().trim();
            if (!continueOption.equalsIgnoreCase("Y")) {
                System.out.println("Exiting. Thank you for using D & B Used Cars.");
                System.exit(0);
            }
        }
    }

    public static void carSale(DataSource dataSource) {
        System.out.println("Enter details for the sale");

        System.out.println("VIN: ");
        String vin = scanner.nextLine().trim();

        System.out.println("Contract Date (YYYY-MM-DD): ");
        String contractDate = scanner.nextLine().trim();

        System.out.println("Buyer Name: ");
        String buyerName = scanner.nextLine().trim();

        System.out.println("Sale Amount: ");
        double saleAmount = scanner.nextDouble();
        scanner.nextLine();  // Consume the remaining newline character

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO sales_contracts (VIN, contract_date, buyer_name, sale_amount) VALUES (?, ?, ?, ?)"
             )) {
            preparedStatement.setString(1, vin);
            preparedStatement.setString(2, contractDate);
            preparedStatement.setString(3, buyerName);
            preparedStatement.setDouble(4, saleAmount);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Sale contract created successfully.");
            } else {
                System.out.println("Failed to create sale contract.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void carLease(DataSource dataSource) {
        System.out.println("Enter details for the lease");

        System.out.println("VIN: ");
        String vin = scanner.nextLine().trim();

        System.out.println("Contract Date (YYYY-MM-DD): ");
        String contractDate = scanner.nextLine().trim();

        System.out.println("Lessee Name: ");
        String lesseeName = scanner.nextLine().trim();

        System.out.println("Lease Amount: ");
        double leaseAmount = scanner.nextDouble();
        scanner.nextLine();  // Consume the remaining newline character

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO lease_contracts (VIN, contract_date, lessee_name, lease_amount) VALUES (?, ?, ?, ?)"
             )) {
            preparedStatement.setString(1, vin);
            preparedStatement.setString(2, contractDate);
            preparedStatement.setString(3, lesseeName);
            preparedStatement.setDouble(4, leaseAmount);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Lease contract created successfully.");
            } else {
                System.out.println("Failed to create lease contract.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}