package com.matematikadetik.tosm.model;


import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ewin.sutriandi on 11/9/2017.
 */

public class ToSMStats implements Serializable{
    private ToSMLevel level;
    private List<Answer> answerList;
    public int operation;
    public static final String TOSM_STAT_KEY = "STAT_KEY";
    private int[] datum = {2000,3000,4000,5000,6000};
    public static final double THRESHOLD_CORR = 50;

    public ToSMStats(List<Answer> answerList, int operation, ToSMLevel level) {
        this.answerList = answerList;
        this.operation = operation;
        this.level = level;
    }

    public ToSMLevel getLevel() {
        return level;
    }

    public int getOperation() {
        return operation;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public int getCorrectAnswerCount(){
        int i = 0;
        for (Answer ans: answerList ) {
            if (ans.isCorrect()) {
                i++;
            }
        }
        return i;
    }
    public int getIncorrectAnswerCount(){
        int i = 0;
        for (Answer ans: answerList ) {
            if (!ans.isCorrect()) {
                i++;
            }
        }
        return i;
    }
    public List<Answer> getIncorrectAnswer(){
        List<Answer> wrongAnswers = new ArrayList<>();
        for (Answer ans: answerList ) {
            if (!ans.isCorrect()) {
                wrongAnswers.add(ans);
            }
        }
        Collections.sort(wrongAnswers);
        return wrongAnswers;
    }

    public List<Answer>[] getCorrectButSlowAnswer(){
        List<Answer>[] ll = new List[datum.length];
        for (int i=0;i<datum.length;i++){
            ll[i] = new ArrayList<Answer>();
        }
        for (Answer ans: answerList ) {
            if (ans.isCorrect()) {
                for (int i=datum.length-1;i>=0;i--){
                    int dt = datum[i];
                    if (ans.getTime()>dt) {
                        ll[i].add(ans);
                        break;
                    }
                }
            }
        }
        for (List<Answer> l:ll) {
            Collections.sort(l);
        }
        return ll;
    }

    public String getIncorrectAnswerInString(){
        String s ="-";
        List<Answer> wrongAnswers = getIncorrectAnswer();
        if (wrongAnswers.size()>0){
            s = wrongAnswers.get(0).getQuestion().toString();
            for(int i=1;i<wrongAnswers.size();i++){
                s = s + ", "+wrongAnswers.get(i).getQuestion().toString();
            }
        }
        return s;
    }

    public String getCorrectButSlowAnswerInString(){
        String sg="";
        List<Answer>[] ll = getCorrectButSlowAnswer();
        for (int j=0;j<ll.length;j++){
            List<Answer> l = ll[j];
            int threshold = datum[j]/1000;
            String s ="> "+threshold+" detik: ";
            if (l.size()>0){
                s += l.get(0).getQuestion().toString();
                for(int i=1;i<l.size();i++){
                    s = s + ", "+l.get(i).getQuestion().toString();
                }
            }
            if (j>0){
                sg += '\n'+s;
            } else {
                sg = s;
            }
        }
        return sg;
    }

    public double getCorrectPercentage(){
        double correct = getCorrectAnswerCount();
        double size = answerList.size();
        double pct = correct * 100 / size;
        return pct;
    }

    public String getCorrectPercentageString(){
        double pct = getCorrectPercentage();
        DecimalFormat df = new DecimalFormat("#,##0.00'%'");
        return df.format(pct);
    }

    public double getTimeElapsedInSeconds(){
        long time=0;
        for(Answer ans: answerList){
            time = time + ans.getTime();
        }
        return time/1000;
    }


    public OperationPerMinute getOperationPerMinute(){
        double opm=0,opmLarger=0,opmLesser=0,opmEqual=0,opmEasy=0,opmDifficult=0;
        long time=0, timeLarger=0, timeLesser=0, timeEqual=0, timeEasy=0,timeDifficult=0;
        int cor = 0, corLarger = 0,corLesser = 0, corEq = 0, corEasy = 0, corDifficult = 0;
        for (Answer ans : answerList) {
            time+=ans.getTime();
            if (ans.isCorrect()) {
                cor++;
            }
            if (ans.getQuestion().isFirstNumLarger()){
                timeLarger += ans.getTime();
                if (ans.isCorrect()) {
                    corLarger++;
                 }
            }
            else if (ans.getQuestion().isFirstNumLesser()){
                timeLesser += ans.getTime();
                if (ans.isCorrect()) {
                    corLesser++;
                }
            } else {
                timeEqual += ans.getTime();
                if (ans.isCorrect()) {
                    corEq++;
                }
            }

            if (ans.getQuestion().isDifficult()){
                timeDifficult += ans.getTime();
                if (ans.isCorrect()){
                    corDifficult++;
                }
            } else {
                timeEasy += ans.getTime();
                if (ans.isCorrect()){
                    corEasy++;
                }
            }
        }
        opm =  ((double)(cor * 60000))/ ((double) time);
        if (timeLarger > 0) opmLarger = ((double)(corLarger * 60000)) /((double)timeLarger);
        if (timeLesser > 0) opmLesser = ((double) (corLesser * 60000)) /((double)timeLesser);
        if (timeEqual > 0) opmEqual = ((double) (corEq * 60000)) /((double)timeEqual);
        if (timeDifficult > 0) opmDifficult = ((double)(corDifficult * 60000)) / ((double)timeDifficult);
        if (timeEasy > 0) opmEasy = ((double)(corEasy * 60000)) / ((double)timeEasy);
        return new OperationPerMinute(opm,opmLarger,opmLesser,opmEqual,opmEasy,opmDifficult);
    }

    public Map<Integer,ToSMStatsByNumber> getTOSMStatByNumber(){
        Map<Integer,ToSMStatsByNumber> stat = new HashMap<>();
        for (Answer ans:answerList){
            Question q = ans.getQuestion();
            ToSMStatsByNumber se1 = stat.get(q.getFirstNum());
            ToSMStatsByNumber se2 = stat.get(q.getSecondNum());
            if (se1 == null) {
                se1 = new ToSMStatsByNumber();
                se1.setNumber(q.getFirstNum());
                stat.put(se1.getNumber(),se1);
            }
            se1.addAnswer(ans);

            if (se2 == null) {
                se2 = new ToSMStatsByNumber();
                se2.setNumber(q.getSecondNum());
                stat.put(se2.getNumber(),se2);
            }
            se2.addAnswer(ans);
        }
        return stat;
    }


    public boolean isValidResult(){
        double pct = getCorrectPercentage();
        return (pct>=THRESHOLD_CORR);
    }
}
