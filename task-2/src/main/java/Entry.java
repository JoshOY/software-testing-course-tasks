package main.java;

import main.java.feeCounter.FeeCounter;
import main.java.feeCounter.exceptions.CrossYearDebtInvalidException;
import main.java.feeCounter.exceptions.MinuteCountInvalidException;
import main.java.feeCounter.exceptions.PrematureTimeInvalidException;

/**
 * Created by joshoy on 16/4/28.
 */
public class Entry {

    public static void main(String[] args) {

        // args[0] 通话时间
        // args[1] 不按时缴费次数
        // args[2] 跨年度未交费用

        if (args.length != 3) {
            System.out.println("参数不足.");
            return;
        }

        double minuteCount = Double.parseDouble( args[0] );
        int prematureTime = Integer.parseInt( args[1] );
        double crossYearDebt = Double.parseDouble( args[2] );

        try {
            FeeCounter feeCounter = new FeeCounter(minuteCount, prematureTime, crossYearDebt);
            System.out.print(feeCounter.calculateFee());
        } catch (MinuteCountInvalidException e) {
            // e.printStackTrace();
            System.out.print("invalid input");
        } catch (PrematureTimeInvalidException e) {
            // e.printStackTrace();
            System.out.print("invalid input");
        } catch (CrossYearDebtInvalidException e) {
            // e.printStackTrace();
            System.out.print("invalid input");
        }
    }

}
