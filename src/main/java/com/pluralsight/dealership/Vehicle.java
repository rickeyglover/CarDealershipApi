package com.pluralsight.dealership;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private String vin;
    private final int year;
    private final String make;
    private final String model;
    private final String type;
    private final String color;
    private int odometer;
    private final double price;

    public Vehicle(String vin, double price, String make, String model, int year, String color, int odometer, String type) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.type = type;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }


    public double getPrice() {
        return this.price;
    }

    public String getVin() {
        return this.vin;
    }

    public int getYear() {
        return this.year;
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public String getType() {
        return this.type;
    }

    public String getColor() {
        return this.color;
    }

    public int getOdometer() {
        return this.odometer;
    }

    //method
    public static Vehicle getVehicle(ResultSet resultSet) throws SQLException {
        String vin = resultSet.getString("VIN");
        double price = resultSet.getDouble("Price");
        String make = resultSet.getString("Make");
        String model = resultSet.getString("Model");
        int year = resultSet.getInt("Year");
        String color = resultSet.getString("Color");
        int odometer = resultSet.getInt("Mileage");
        String type = resultSet.getString("Type");

        Vehicle vehicle = new Vehicle(vin, price, make, model, year, color, odometer, type);
        System.out.printf("\nVIN: %s" +
                        "\nPrice: %.2f" +
                        "\nMake: %s" +
                        "\nModel: %s" +
                        "\nYear: %d" +
                        "\nColor: %s" +
                        "\nOdometer: %d" +
                        "\nType: %s\n-------------------------\n",
                vin, price, make, model, year, color, odometer, type);
        return vehicle;
    }
}