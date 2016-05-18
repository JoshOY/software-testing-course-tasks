package main.java.feeCounter;

import main.java.feeCounter.exceptions.CrossYearDebtInvalidException;
import main.java.feeCounter.exceptions.MinuteCountInvalidException;
import main.java.feeCounter.exceptions.PrematureTimeInvalidException;

/**
 * Created by joshoy on 16/4/28.
 */
public class FeeCounter {

    private final double FEE_PER_MINUTE = 0.15;
    private final double BASIC_FEE = 25.0;
    private final double CROSS_YEAR_DEBT_RATE = 0.05;

    protected double minuteCount;
    protected double crossYearDebt;
    protected int prematureTime;


    public FeeCounter(double minuteCount, int prematureTime, int currentMonth, double crossYearDebt)
            throws MinuteCountInvalidException, PrematureTimeInvalidException {

        this.minuteCount = minuteCount;
        this.prematureTime = prematureTime;
        this.crossYearDebt = crossYearDebt;

        // 通话时间小于0
        if (this.minuteCount < 0) {
            throw new MinuteCountInvalidException();
        }
        // 通话时间段的最大容许不按时交费次数小于0
        if (this.prematureTime < 0) {
            throw new PrematureTimeInvalidException();
        }

    }

    public FeeCounter(double minuteCount, int prematureTime, double crossYearDebt)
            throws MinuteCountInvalidException, PrematureTimeInvalidException, CrossYearDebtInvalidException {

        this.minuteCount = minuteCount;
        this.prematureTime = prematureTime;
        this.crossYearDebt = crossYearDebt;

        // 通话时间小于0
        if (this.minuteCount < 0) {
            throw new MinuteCountInvalidException();
        }
        // 本年不按时交费次数小于0
        if (this.prematureTime < 0) {
            throw new PrematureTimeInvalidException();
        }
        if (this.crossYearDebt < 0) {
            throw new CrossYearDebtInvalidException();
        }
        // 不按时交费次数超出上限
        if (this.countMinuteLargerThanUpperLimit() == true) {
            throw new PrematureTimeInvalidException();
        }
    }

    /**
     * 计算费用
     * @return
     */
    public double calculateFee() {

        // 计算与折扣无关部分: 基本租借费 + 跨年欠费 * 0.05
        double ret = this.BASIC_FEE + this.crossYearDebt * this.CROSS_YEAR_DEBT_RATE;

        // 如果已超过允许欠费上限
        if ( false == this.notExceedPrematureTime() ) {
            ret += this.FEE_PER_MINUTE * this.minuteCount;
            return ret;
        }

        // 如果未超过, 开始按折扣计算
        /* 0分钟 */
        if (this.minuteCount == 0) {
            return ret;
        }

        double discountRatio = 0;
        /* (0, 60] */
        discountRatio = 0.010;
        if (this.minuteCount <= 60) {
            ret += this.FEE_PER_MINUTE * this.minuteCount * (1.0 - discountRatio);
        }
        else if (this.minuteCount > 60) {
            ret += this.FEE_PER_MINUTE * 60 * (1.0 - discountRatio) ;
        }

        /* (60, 120] */
        discountRatio = 0.015;
        if (this.minuteCount > 60 && this.minuteCount <= 120) {
            ret += this.FEE_PER_MINUTE * (this.minuteCount - 60) * (1.0 - discountRatio);
        }
        else if (this.minuteCount > 120) {
            ret += this.FEE_PER_MINUTE * (120 - 60) * (1.0 - discountRatio);
        }

        /* (120, 180] */
        discountRatio = 0.020;
        if (this.minuteCount > 120 && this.minuteCount <= 180) {
            ret += this.FEE_PER_MINUTE * (this.minuteCount - 120) * (1.0 - discountRatio);
        }
        else if (this.minuteCount > 180) {
            ret += this.FEE_PER_MINUTE * (180 - 120) * (1.0 - discountRatio);
        }

        /* (180, 300] */
        discountRatio = 0.025;
        if (this.minuteCount > 180 && this.minuteCount <= 300) {
            ret += this.FEE_PER_MINUTE * (this.minuteCount - 180) * (1.0 - discountRatio);
        }
        else if (this.minuteCount > 300) {
            ret += this.FEE_PER_MINUTE * (300 - 180) * (1.0 - discountRatio);
        }

        /* (300, +inf) */
        discountRatio = 0.030;
        if (this.minuteCount > 300) {
            ret += this.FEE_PER_MINUTE * (this.minuteCount - 300) * (1.0 - discountRatio);
        }

        return ret;
    }

    /**
     * 检查该月通话时间是否超过上限
     * @return
     */
    private boolean countMinuteLargerThanUpperLimit() {
        if (this.prematureTime > 11) {
            return true;
        }
        return false;
    }

    /**
     * 检查是否已超过通话时间段的最大容许不按时交费次数
     * @return true: 未超过; false: 已超过
     */
    private boolean notExceedPrematureTime() {
        if (this.minuteCount <= 60) {
            return this.prematureTime <= 1;
        }
        else if (this.minuteCount <= 120) {
            return this.prematureTime <= 2;
        }
        else if (this.minuteCount <= 180) {
            return this.prematureTime <= 3;
        }
        else if (this.minuteCount <= 300) {
            return this.prematureTime <= 3;
        }
        else {
            return this.prematureTime <= 6;
        }
    }

}
