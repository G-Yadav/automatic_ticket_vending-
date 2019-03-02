package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.event.MouseEvent;
import java.time.LocalDate;

public class MainController {

    public static boolean status = false;
    public static String name = "Gaurav";
    public static String from = null;
    public static String to = null;
    public static LocalDate date = null;
    public static String typeClass = null;
    public static String train = null;
    public static Double amount = null;
    public static String day = null;
    public static ObservableList<String> list = FXCollections.observableArrayList(
            "All Class",
            "AC 3 Tier",
            "AC 2 Tier",
            "AC 1 Tier",
            "Sleeper"
    );

    public static void logoutClicked(MouseEvent event) {

    }
}
