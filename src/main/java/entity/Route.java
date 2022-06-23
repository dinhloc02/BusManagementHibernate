package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import repositories.RouteDAO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Entity

@Table(name = "route")
@Data
@NoArgsConstructor
public class Route implements Serializable {
    @Id
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE1")
//    @SequenceGenerator(name="SEQUENCE1", sequenceName="SEQUENCE1", allocationSize=1)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    int id;
    @Column(name = "distance", nullable = false)
    int distance;
    @Column(name = "numberstop", nullable = false)
    int numberStop;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id == route.id && distance == route.distance && numberStop == route.numberStop;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, distance, numberStop);
    }


    public static int id() {
        List<Route> routes = RouteDAO.getAll();
        if (routes.isEmpty()) {
            return 99;
        }
        int max = routes.get(0).getId();
            for (int i = 1; i < routes.size(); ++i) {
                if (max < routes.get(i).getId()) {
                    max = routes.get(i).getId();
                }
            }
        return max;
    }

    public void inputInfo() {
        this.id = id()+1;
        System.out.println("Nhập khoảng cách:");
        do {
            try {
                this.setDistance(new Scanner(System.in).nextInt());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Khoảng cách không được là kí tự nhập lại:");
            }
        } while (true);
        System.out.println("Số điểm dừng của tuyến:");
        do {
            try {
                this.setNumberStop(new Scanner(System.in).nextInt());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Khoảng cách không được là kí tự nhập lại:");
            }
        } while (true);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", distance=" + distance +
                ", numberStop=" + numberStop +
                '}';
    }
}
