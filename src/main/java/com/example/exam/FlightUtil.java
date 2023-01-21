package com.example.exam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightUtil {
    FlightUtil(){

    }

    private static final String CREATE = "CREATE TABLE IF NOT EXISTS FLIGHTS(\" +\n" +
            "            \"CITY VARCHAR(30),\" +\n" +
            "            \"DATE VARCHAR(30),\" +\n" +
            "            \"SEAT INTEGER,\" +\n" +
            "            \"PRICE INTEGER)";

    public static void createTable() {
        try {
            JDBCconf.getStatement().executeUpdate(CREATE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String insert(Flight flight) {

        String INSERT_TABLE = "INSERT INTO FLIGHTS(CITY, DATE, SEAT, PRICE) VALUES(" +
                "'" + flight.getCity() +
                " ', '" + flight.getDate() +
                "'," + flight.getSeat() +
                "," + flight.getPrice() + ")";

        try {
            JDBCconf.getStatement().executeUpdate(INSERT_TABLE);
            return "ინფორმაცია წარმატებით დაემატა";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<PieChart.Data> chart(){

        String SELECT = "SELECT CITY, COUNT(*) AS COUNT FROM FLIGHTS GROUP BY CITY";

        ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList();

        try{
            ResultSet result = JDBCconf.getStatement().executeQuery(SELECT);

            while (result.next()){
                observableList.add(new PieChart.Data(result.getString("CITY"), result.getInt("COUNT")));
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return observableList;

    }
}
