package service;

import entity.*;
import entity.Driver;
import main.Main;
import repositories.AssignmentDAO;
import repositories.RouteDAO;

import java.util.*;

public class AssignmentService {


    public static Driver inputDriver() {
        System.out.println("Nhập mã người lái xe cần phân công:");
        int idDriver = 0;
        Driver driver = null;
        do {
            do {
                try {
                    idDriver = new Scanner(System.in).nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Mã người lái xe phải là số nguyên nhập lại:");
                }
            } while (true);
            for (Driver driver1 : Main.drivers) {
                if (driver1.getId() == idDriver) {
                    driver = driver1;
                    break;
                }
            }
            if (driver != null) {
                break;
            }
            System.out.println("không có mã người lái xe nhập lại");
        } while (true);
        return driver;
    }

    public static List<DetailRoute> inputDetailRoute() {
        System.out.println("Nhập số lượng tuyến cần phân công");
        int quantity = 0;
        do {
            try {
                quantity = new Scanner(System.in).nextInt();
                if (quantity > 0 && quantity < 16) {
                    break;
                }
                System.out.println("Số tuyến phân công không thể nhỏ hơn 1 và lớn hơn 15 nhập lại:");
            } catch (InputMismatchException e) {
                System.out.println("Số tuyến cần phân công  phải là số nguyên nhập lại:");
            }
        } while (true);
        Route route1 = null;
        List<DetailRoute> detailRoutes = new LinkedList<>();
        for (int i = 0; i < quantity; ++i) {
            System.out.println("Nhập mã số tuyến cần phân công: ");
            int idRoutes = 0;
            do {
                do {
                    try {
                        idRoutes = new Scanner(System.in).nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Mã tuyến phải là số nguyên nhập lại:");
                    }
                } while (true);
                boolean check = true;
                for (Route Route : Main.routes) {
                    for (DetailRoute detailRoute : detailRoutes) {
                        if (detailRoute != null && detailRoute.getRoute().getId() == idRoutes) {
                            check = false;
                            break;
                        }
                    }
                    if (!check) {
                        break;
                    }
                    if (Route.getId() == idRoutes) {
                        route1 = Route;
                        break;
                    }
                }
                if (route1 != null) {
                    break;
                }
                System.out.println("Không có mã tuyến hoặc đã có mã tuyến nhập lại:");
            } while (true);
            System.out.println("Nhập số lượt cần phân công:");
            int number = 0;
            do {
                try {
                    number = new Scanner(System.in).nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Số lượt phải là số nguyên nhập lại:");
                }
            } while (true);

            DetailRoute detailRoute = new DetailRoute(route1, number);

            detailRoutes.add(detailRoute);

        }
        return detailRoutes;
    }

    public static void inputAssignmentDetail() {
        if (RouteService.isempty() || DriverSevice.isempty()) {
            System.out.println("Nhập người lái xe và tuyến trước");
            return;
        }
        System.out.println("Nhập số lượng lái xe cần phân công:");
        int quantity = 0;
        do {
            try {
                quantity = new Scanner(System.in).nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Số lượt phải là số nguyên nhập lại:");
            }
        } while (true);
        boolean check = false;
        for (int i = 0; i < quantity; ++i) {
            Driver driver = inputDriver();
            for (AssignmentDetail assignmentDetail : Main.assignmentDetails) {
                if (assignmentDetail != null && driver.getId() == assignmentDetail.getDriver().getId()) {
                    check = true;
                    break;
                }
            }
            if (check) {
                System.out.println("lái xe đã có trong danh sách .");
                continue;
            }
            List<DetailRoute> detailRoutes = inputDetailRoute();
            if (numberTurn((LinkedList<DetailRoute>) detailRoutes) > 15) {
                System.out.println("Số lượt lái xe không được vượt quá 15.");
                continue;
            }
            AssignmentDetail assignmentTable = new AssignmentDetail(driver, (LinkedList<DetailRoute>) detailRoutes);
            for (int j = 0; j < detailRoutes.size(); ++j) {
                Assignment assignment = new Assignment(driver.getId(),detailRoutes.get(i).getRoute().getId(), detailRoutes.get(i).getQuantity());
                AssignmentDAO.add(assignment);
            }
        }
    }

    public static int numberTurn(LinkedList<DetailRoute> detailRoutes) {
        int sum = 0;
        for (DetailRoute detailRoute : detailRoutes) {
            sum += detailRoute.getQuantity();
        }
        return sum;
    }
    public static void showAllAssignment() {
        List<Assignment> assignments = AssignmentDAO.getAll();
        if (assignments.isEmpty()) {
            System.out.println("Không có tuyến nào trong csdl!");
        } else {
            assignments.forEach(System.out::println);
        }
    }
    public static void showAllAssignmentSort() {
        List<Assignment> assignments = AssignmentDAO.getAllSort();
        if (assignments.isEmpty()) {
            System.out.println("Không có tuyến nào trong csdl!");
        } else {
            assignments.forEach(System.out::println);
        }
    }
}


