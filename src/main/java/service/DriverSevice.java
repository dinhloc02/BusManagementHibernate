package service;

import entity.Driver;
import main.Main;
import repositories.DriverDAO;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DriverSevice {


    public void addInfo() {
        System.out.println("Nhập số lượng lái xe cần thêm:");
        int quantity = 0;
        do {
            try {
                quantity = new Scanner(System.in).nextInt();
                if (quantity > 0) {
                    break;
                }
                System.out.println("Số lái xe phải lớn hơn 0 nhập lại:");
            } catch (InputMismatchException e) {
                System.out.println("Số lái xe phải là số nguyên nhập lại:");
            }
        } while (true);
        for (int i = 0; i < quantity; ++i) {
            Driver driver = new Driver();
            driver.inputInfo();
            Main.drivers.add(driver);
            boolean check = DriverDAO.add(driver);
            if (check) {
                System.out.println("Thêm mới thành công");
            } else {
                System.out.println("Thêm mới thất bại");
            }
        }
    }

    public void showAllDriver() {
        List<Driver> drivers = DriverDAO.getAll();
        Main.drivers.add((Driver) drivers);
        if (drivers.isEmpty()) {
            System.out.println("Không có lái xe nào trong cơ sở dữ liệu");
        } else {
            drivers.forEach(System.out::println);
        }
    }
    public static boolean isempty() {
        for (Driver driver : Main.drivers) {
            if (driver != null) {
                return false;
            }
        }
        return true;
    }
}
