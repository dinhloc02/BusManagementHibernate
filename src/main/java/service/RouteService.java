package service;

import main.Main;
import repositories.RouteDAO;
import entity.Route;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RouteService {
    public void addInfo() {
        System.out.println("Nhập số lượng tuyến cần thêm:");
        int quantity = 0;
        do {
            try {
                quantity = new Scanner(System.in).nextInt();
                if (quantity > 0) {
                    break;
                }
                System.out.println("Số người tuyến phải lớn hơn 0 nhập lại:");
            } catch (InputMismatchException e) {
                System.out.println("Số người tuyến phải là số nguyên nhập lại:");
            }
        } while (true);
        for (int i = 0; i < quantity; ++i) {
            Route route = new Route();
            route.inputInfo();
            Main.routes.add(route);
            boolean check = RouteDAO.add(route);
            if (check) {
                System.out.println("Thêm mới thành công");
            } else {
                System.out.println("Thêm mới thất bại");
            }
        }
    }

    public void showAllRoute() {
        List<Route> routes = RouteDAO.getAll();
        Main.routes.add((Route) routes);
        if (routes.isEmpty()) {
            System.out.println("Không có tuyến nào trong csdl!");
        } else {
            routes.forEach(System.out::println);
        }
    }
    public static boolean isempty() {
        for (Route Route : Main.routes) {
            if (Route != null) {
                return false;
            }
        }
        return true;
    }
}
