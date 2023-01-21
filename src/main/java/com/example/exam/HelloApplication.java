package com.example.exam;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        TextField city = new TextField();
        city.setLayoutX(10);
        city.setLayoutY(10);

        city.setPromptText("city");


        TextField date = new TextField();
        date.setLayoutX(10);
        date.setLayoutY(40);

        date.setPromptText("date");


        TextField seats = new TextField();
        seats.setLayoutX(10);
        seats.setLayoutY(70);

        seats.setPromptText("seats");

        TextField price = new TextField();
        price.setLayoutX(10);
        price.setLayoutY(100);

        price.setPromptText("price");

        Text text = new Text();
        text.setLayoutX(70);
        text.setLayoutY(150);

        Button button = new Button("insert");
        button.setLayoutX(10);
        button.setLayoutY(130);



        Group root = new Group();
        root.getChildren().add(city);
        root.getChildren().add(date);
        root.getChildren().add(seats);
        root.getChildren().add(price);
        root.getChildren().add(button);
        root.getChildren().add(text);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FlightUtil.createTable();
                String name = city.getText();
                String name1 = date.getText();
                int name2 = Integer.parseInt(seats.getText());
                int name3 = Integer.parseInt(price.getText());

                String result = FlightUtil.insert(new Flight(name, name1, name2, name3));
                text.setText(result);

                PieChart pieChart = new PieChart();
                pieChart.setLayoutX(200);
                pieChart.setLayoutY(20);

                pieChart.setData(FlightUtil.chart());
                root.getChildren().add(pieChart);
            }
        });



        Scene scene = new Scene(root, 880, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}