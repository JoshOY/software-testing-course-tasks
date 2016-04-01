package courselab.datenext;

/**
 * Created by joshoy on 16/3/31.
 */
public abstract class DateUtil {

    public static boolean isLeapYear(int year) {
        if ( (year % 400 == 0) || ((year % 100 != 0) && (year % 4 == 0)) ) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isLastDayOfMonth(Date d) {
        if ( (d.getMonth() == 1) || (d.getMonth() == 3) || (d.getMonth() == 5) || (d.getMonth() == 7)
                || (d.getMonth() == 8) || (d.getMonth() == 10) || (d.getMonth() == 12)) {
            if (d.getDay() == 31) {
                return true;
            }
            else {
                return false;
            }
        }
        else if ((d.getMonth() == 4) || (d.getMonth() == 6) || (d.getMonth() == 9) || (d.getMonth() == 11)) {
            if (d.getDay() == 30) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (d.getMonth() == 2) {
            if ( isLeapYear(d.getYear()) && (d.getDay() == 29) ) {
                return true;
            }
            if ( (!isLeapYear(d.getYear())) && (d.getDay() == 28) ) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
}
