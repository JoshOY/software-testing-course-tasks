package courselab.datenext;

/**
 * Created by joshoy on 16/3/31.
 */
public class Date {
    private int year;
    private int month;
    private int day;
    private boolean errFlag;

    public Date(int year, int month, int day) {
        this.errFlag = false;
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
    }

    public Date(int year, int month, int day, boolean errFlag) {
        this.errFlag = errFlag;
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
    }

    public Date(Date d) {
        errFlag = false;
        this.setYear(d.getYear());
        this.setMonth(d.getMonth());
        this.setDay(d.getDay());
    }

    @Override
    public String toString() {
        if (errFlag) {
            return "Invalid Date";
        }

        String ret = "" + this.year + "-";
        if (this.month < 10) {
            ret = ret + "0" + this.month;
        }
        else {
            ret = ret + this.month;
        }
        ret = ret + "-";
        if (this.day < 10) {
            ret = ret + "0" + this.day;
        }
        else {
            ret = ret + this.day;
        }
        return ret;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        try {
            if (year < 0) {
                throw new InvalidDateException();
            }
            this.year = year;
        }
        catch (InvalidDateException err) {
            // err.printStackTrace();
            this.errFlag = true;
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        try {
            if (month < 0 || month > 12) {
                throw new InvalidDateException();
            }
            this.month = month;
        }
        catch (InvalidDateException err) {
            this.errFlag = true;
            // err.printStackTrace();
        }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        try {
            this.day = day;
            if ((this.month == 1) || (this.month == 3) || (this.month == 5) || (this.month == 7)
                    || (this.month == 8) || (this.month == 10) || (this.month == 12)) {
                if (this.day > 31) {
                    errFlag = true;
                    throw new InvalidDateException();
                }
            }
            /* Day number can't larger than 30 */
            if ((this.month == 4) || (this.month == 6) || (this.month == 9) || (this.month == 11)) {
                if (this.day > 30) {
                    errFlag = true;
                    throw new InvalidDateException();
                }
            }
            /* Check February */
            if (this.month == 2) {
                /* Case leap year */
                if (DateUtil.isLeapYear(this.year)) {
                    if (this.day > 29) {
                        errFlag = true;
                    }
                }
                /* Normal years */
                else {
                    if (this.day > 28) {
                        errFlag = true;
                    }
                }
            }

        }
        catch (InvalidDateException err) {
            this.errFlag = true;
            // err.printStackTrace();
        }
    }

    public Date nextDay() {
        if ((this.getMonth() == 12) && (this.getDay() == 31)){
            return new Date(this.getYear() + 1, 1, 1);
        }
        else if (DateUtil.isLastDayOfMonth(this)) {
            return new Date(this.getYear(), this.getMonth() + 1, 1, this.errFlag);
        }
        else {
            return new Date(this.getYear(), this.getMonth(), this.getDay() + 1, this.errFlag);
        }
    }
}
