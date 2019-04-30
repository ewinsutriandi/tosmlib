package com.matematikadetik.tosm.model;

import java.text.DecimalFormat;

/**
 * Created by ewin.sutriandi@gmail.com on 11/15/2017.
 */

public class OperationPerMinute {
    double opm;
    double opmLargerFirstNum;
    double opmLesserFirstNum;
    double opmEasy;
    double opmDifficult;


    double opmEqualNum;

    public OperationPerMinute(double opm,
                              double opmLargerFirstNum, double opmLesserFirstNum, double opmEqualNum,
                              double opmEasy, double opmDifficult) {
        this.opm = opm;
        this.opmLargerFirstNum = opmLargerFirstNum;
        this.opmLesserFirstNum = opmLesserFirstNum;
        this.opmEqualNum = opmEqualNum;
        this.opmEasy = opmEasy;
        this.opmDifficult = opmDifficult;
    }

    public double getOpm() {
        return opm;
    }

    public void setOpm(double opm) {
        this.opm = opm;
    }

    public double getOpmLargerFirstNum() {
        return opmLargerFirstNum;
    }

    public void setOpmLargerFirstNum(double opmLargerFirstNum) {
        this.opmLargerFirstNum = opmLargerFirstNum;
    }

    public double getOpmLesserFirstNum() {
        return opmLesserFirstNum;
    }

    public void setOpmLesserFirstNum(double opmLesserFirstNum) {
        this.opmLesserFirstNum = opmLesserFirstNum;
    }
    public double getOpmEqualNum() {
        return opmEqualNum;
    }

    public void setOpmEqualNum(double opmEqualNum) {
        this.opmEqualNum = opmEqualNum;
    }

    public double getOpmEasy() {
        return opmEasy;
    }

    public double getOpmDifficult() {
        return opmDifficult;
    }

    public String toString(){
        return "opm:"+opm
                +"\n, opm larger fnum:"+opmLargerFirstNum
                +"\n, opm lesser fnum:"+opmLesserFirstNum
                +"\n, opm equal fnum:"+opmEqualNum;
    }

    public double getTimePerOperation(){
        return 60/getOpm();
    }
    public String getTimePerOperationTwoDigitDecimal(){
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(getTimePerOperation());
    }
}
