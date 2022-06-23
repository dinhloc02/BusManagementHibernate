package entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedList;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDetail implements Comparable<AssignmentDetail>, Serializable {
    private Driver driver;
   private LinkedList<DetailRoute> detailRoutes;


    public int compareTo(AssignmentDetail assignmentDetail) {
        return this.getDriver().getFullName().compareTo(assignmentDetail.getDriver().getFullName());
    }

    public int getDistance() {
        if (isEmptyOrNull(detailRoutes.toArray())) {
            return 0;
        }
        int sum = 0;
        for (DetailRoute detailRoute : detailRoutes) {
            sum += detailRoute.getQuantity() * detailRoute.getRoute().getDistance();
        }
        return sum;
    }

    public static boolean isEmptyOrNull(Object[] obj) {
        return obj.length == 0 && obj == null;
    }
}