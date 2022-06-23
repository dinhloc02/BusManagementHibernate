package main;

import service.AssignmentService;
import service.DriverSevice;
import service.RouteService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainService {
   static RouteService routeService = new RouteService();
   static DriverSevice driverSevice = new DriverSevice();

    public static void menu() {
        System.out.println("--------------QUẢN LÝ ĐIỂM SINH VIÊN--------------");
        System.out.println("1. Nhập danh sách người lái xe");
        System.out.println("2. In danh sách người lái xe");
        System.out.println("3. Nhập danh sách tuyến ");
        System.out.println("4. In ra danh sách tuyến");
        System.out.println("5.Quản lý phân công lái xe");
        System.out.println("6. In ra danh sách phân công lái xe ");
        System.out.println("7. Sắp xếp danh sách giảng dạy theo tên sinh viên");
        System.out.println("8. Tính điểm tổng kết cho mỗi sinh viên");
        System.out.println("9. Thoát");
        System.out.println("----------------------------------------------");

        boolean check = true;
        int choice = 0;
        do {
            System.out.println("Nhập chức năng:");
            do {
                try {
                    choice = new Scanner(System.in).nextInt();
                    if (choice >= 0 && choice <= 9) {
                        break;
                    }
                    System.out.println("Chức năng phải là từ 1 đến 9 nhập lại:");
                } catch (InputMismatchException e) {
                    System.out.println("Mã môn học không được là kí tự nhập lại:");
                }
            } while (true);
            switch (choice) {
                case 1:
                    driverSevice.addInfo();
                    break;
                case 2:
                    driverSevice.showAllDriver();
                    break;
                case 3:
                    routeService.addInfo();
                    break;
                case 4:routeService.showAllRoute();
                    break;
                case 5:
                    AssignmentService.inputAssignmentDetail();
                    break;
                case 6:
                    AssignmentService.showAllAssignment();
                    break;
                case 7:AssignmentService.showAllAssignmentSort();
                    break;
                case 8:
                    break;
                case 9:
                    check = false;
            }
        } while (check);
    }
}
