package com.matematikadetik.tosm.model;

/**
 * Created by ewin.sutriandi on 11/15/2017.
 */

public class ToSMStatsByNumber {
    private int number;
    private int correctCumulative;
    private long timeCumulative; //in ms
    private int wrongCumulative;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCorrectCumulative() {
        return correctCumulative;
    }

    public void setCorrectCumulative(int correctCumulative) {
        this.correctCumulative = correctCumulative;
    }

    public long getTimeCumulative() {
        return timeCumulative;
    }

    public void setTimeCumulative(long timeCumulative) {
        this.timeCumulative = timeCumulative;
    }

    public int getWrongCumulative() {
        return wrongCumulative;
    }

    public void setWrongCumulative(int wrongCumulative) {
        this.wrongCumulative = wrongCumulative;
    }
    public void addAnswer(Answer ans){
        if (ans.isCorrect()) {
            correctCumulative++;
        } else {
            wrongCumulative++;
        }
        timeCumulative = timeCumulative + ans.getTime();
    }

    public double getOperationPerMinute(){
        double opm = (int) ((correctCumulative * 60000)/ timeCumulative);
        return opm;
    }


}
