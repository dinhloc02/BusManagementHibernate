package main;

import entity.*;
import entity.Assignment;

import java.util.LinkedList;

public class Main {
    public static LinkedList<Driver> drivers = new LinkedList<>();
    public static LinkedList<Route> routes = new LinkedList<>();
    public static LinkedList<AssignmentDetail> assignmentDetails= new LinkedList<>();

    public static void main(String[] args) {
        MainService.menu();
    }
}
