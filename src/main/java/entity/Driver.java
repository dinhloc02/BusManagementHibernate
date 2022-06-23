package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import repositories.DriverDAO;
import repositories.RouteDAO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Entity
@Table(name = "driver")
@Data
@NoArgsConstructor
public class Driver implements Serializable {
    public static final String LA = " Loại A";
    public static final String LB = " Loại B";
    public static final String LC = " Loại C";
    public static final String LD = " Loại D";
    public static final String LE = " Loại E";
    public static final String LF = " Loại F";
    @Id
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE1")
//    @SequenceGenerator(name="SEQUENCE1", sequenceName="SEQUENCE1", allocationSize=1)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    int id;
    @Column(name = "full_name", nullable = false)
    String fullName;
    @Column(nullable = false)
    String adress;
    @Column(name = "phone_number", nullable = false)
    int phoneNumber;
    @Column(name = "levell", nullable = false)
    String level;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id == driver.id && phoneNumber == driver.phoneNumber && Objects.equals(fullName, driver.fullName) && Objects.equals(adress, driver.adress) && Objects.equals(level, driver.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, adress, phoneNumber, level);
    }

    public static int id() {
        List<Driver> drivers = DriverDAO.getAll();
        if (drivers.isEmpty()) {
            return 9999;
        }
        int max = drivers.get(0).getId();
        for (int i = 1; i < drivers.size(); ++i) {
            if (max < drivers.get(i).getId()) {
                max = drivers.get(i).getId();
            }
        }
        return max;
    }

    public void inputInfo() {
        this.id = id() + 1;
        System.out.println("Nhập họ tên người lái xe:");
        this.setFullName(new Scanner(System.in).nextLine());
        System.out.println("Nhập địa chỉ người lái xe:");
        this.setAdress(new Scanner(System.in).nextLine());
        System.out.println("Nhập số điện thoại người lái xe:");
        do {
            try {
                this.setPhoneNumber(new Scanner(System.in).nextInt());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Số điện thoại không được là kí tự nhập lại:");
            }
        } while (true);
        System.out.println("Trình độ người lái xe là :(1.Loại A , 2.Loại B , 3.Loại C ,4.Loại D , 5.Loại E , 6.Loại F)");
        int types = 0;
        do {
            try {
                types = new Scanner(System.in).nextInt();
                if (types > 0 && types < 6) {
                    break;
                }
                System.out.println("Trình độ phải chọn từ 1 đến 6 nhập lại:");
            } catch (InputMismatchException e) {
                System.out.println("Trình độ phải chọn từ 1 đến 6 nhập lại:");
            }
        } while (true);
        switch (types) {
            case 1:
                this.setLevel(Driver.LA);
                break;
            case 2:
                this.setLevel(Driver.LB);
                break;
            case 3:
                this.setLevel(Driver.LC);
                break;
            case 4:
                this.setLevel(Driver.LD);
                break;
            case 5:
                this.setLevel(Driver.LE);
                break;
            case 6:
                this.setLevel(Driver.LF);
                break;
        }
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", adress='" + adress + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", level='" + level + '\'' +
                '}';
    }
}
