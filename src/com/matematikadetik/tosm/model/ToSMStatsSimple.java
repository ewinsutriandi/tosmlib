package com.matematikadetik.tosm.model;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ewin.sutriandi on 11/9/2017.
 */

public class ToSMStatsSimple implements Serializable{
    private String uid;
    private String level;
    private double opm,opmeasy,opmdifficult;
    private double time;
    private int qnum;
    private int qcorrect;
    public String operation;
    private String timetaken;
    public static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    public ToSMStatsSimple(){

    }
    public ToSMStatsSimple(ToSMStats s){
        this.level = s.getLevel().name();
        this.opm = s.getOperationPerMinute().getOpm();
        this.opmeasy = s.getOperationPerMinute().getOpmEasy();
        this.opmdifficult = s.getOperationPerMinute().getOpmDifficult();
        this.time = s.getTimeElapsedInSeconds();
        this.qcorrect = s.getCorrectAnswerCount();
        this.qnum = s.getCorrectAnswerCount()+s.getIncorrectAnswerCount();
        switch (s.operation){
            case ToSMUtil.OPERATION_ADD:
                operation = "TAMBAH";
                break;
            case ToSMUtil.OPERATION_SUB:
                operation = "KURANG";
                break;
            case ToSMUtil.OPERATION_MULT:
                operation = "KALI";
                break;
            case ToSMUtil.OPERATION_DIV:
                operation = "BAGI";
                break;
        }
        this.timetaken = df.format(new Date());
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getOpm() {
        return opm;
    }

    public void setOpm(double opm) {
        this.opm = opm;
    }

    public double getOpmeasy() {
        return opmeasy;
    }

    public void setOpmeasy(double opmeasy) {
        this.opmeasy = opmeasy;
    }

    public double getOpmdifficult() {
        return opmdifficult;
    }

    public void setOpmdifficult(double opmdifficult) {
        this.opmdifficult = opmdifficult;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getQnum() {
        return qnum;
    }

    public void setQnum(int qnum) {
        this.qnum = qnum;
    }

    public int getQcorrect() {
        return qcorrect;
    }

    public void setQcorrect(int qcorrect) {
        this.qcorrect = qcorrect;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUid() {
        return uid;
    }

    public String getTimetaken() {
        return timetaken;
    }

    public void setTimetaken(String timetaken) {
        this.timetaken = timetaken;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
