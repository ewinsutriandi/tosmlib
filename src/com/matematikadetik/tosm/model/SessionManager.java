package com.matematikadetik.tosm.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ewin on 20/12/17.
 */

public class SessionManager {
    public static final String SIMULATION_KEY="simul_key";
    public static final String HALFPENUH_KEY="halfpenuh_key";
    List<Question> questionList = new ArrayList<>();
    List<Answer> answerList = new ArrayList<>();
    int operationType;
    ToSMLevel level;
    private int currentQuestionIdx = 0;
    public boolean isSimulation;
    public boolean isSetengahPenuh;
    public boolean isQuizEnd = false;


    public SessionManager(int operationType, ToSMLevel level, boolean simulation){
        this.operationType = operationType;
        this.isSimulation = simulation;
        this.level = level;
        generateQuestion();
    }

    public SessionManager(int operationType, ToSMLevel level, boolean simulation, boolean isSetengahPenuh){
        this.operationType = operationType;
        this.isSimulation = simulation;
        this.isSetengahPenuh = isSetengahPenuh;
        this.level = level;
        generateQuestion();
    }

    private void generateQuestion(){
        switch (level){
            case A1:
                generateA1Questions();
                break;
            case A2:
                generateA2Questions();
                break;
        }

    }
    private void generateA1Questions(){
        int cnt = 182;
        int minNum, maxNum, multiplier;
        if (isSimulation){
            minNum = 4;
            maxNum = 6;
            multiplier = 1;
        } else {
            minNum = 1;
            maxNum = 9;
            multiplier = 2;
            if (isSetengahPenuh) {
                multiplier = 1;
                cnt = 91;
            }
        }
        //loop for multiplier of question size
        int firstNum,secondNum;
        for(int y = 0; y< multiplier; y++){
            //loop for first number
            for(int i=minNum;i<=maxNum;i++){
                //loop for second number
                for(int j=minNum;j<=maxNum;j++){
                    Question q;
                    if(operationType==ToSMUtil.OPERATION_DIV){
                        firstNum = i * j;
                        secondNum = j;
                    } else if(operationType==ToSMUtil.OPERATION_SUB){
                        firstNum = i + j;
                        secondNum = j;
                    }
                    else {
                        firstNum = i;
                        secondNum = j;
                    }
                    q = new Question(firstNum,secondNum,operationType,ToSMLevel.A1);
                    questionList.add(q);
                }
            }
        }
        Collections.shuffle(questionList);
        int selisih = cnt - questionList.size();
        for (int i=0;i<selisih;i++){
            Question q = questionList.get(i);
            questionList.add(q);
        }
    }

    private void generateA2Questions(){
        int minNum, maxNum, multiplier;
        multiplier = 1;
        if (isSimulation){
            minNum = -2;
            maxNum = 2;
        } else {
            minNum = -9;
            maxNum = 9;
        }
        //loop for multiplier of question size
        int firstNum,secondNum,secondMinNum;
        secondMinNum = minNum;
        if (isSetengahPenuh) secondMinNum = 1;
        for(int y = 0; y< multiplier; y++){
            //loop for first number
            for(int i=minNum;i<=-1;i++){
                if (i==0) continue;
                //loop for second number
                for(int j=secondMinNum;j<=maxNum;j++){
                    if (j==0) continue; // no zero value
                    if (j>0 && i >0) continue; // no two positives
                    Question q;
                    if(operationType==ToSMUtil.OPERATION_DIV){
                        firstNum = i * j;
                        secondNum = j;
                    } else if(operationType==ToSMUtil.OPERATION_SUB){
                        if (i==j) continue; //f(x1,x2)!=0
                        firstNum = i + j;
                        secondNum = j;
                    } else if(operationType==ToSMUtil.OPERATION_ADD){
                        if (i==-j) continue; //f(x1,x2)!=0
                        firstNum = i;
                        secondNum = j;
                    }
                    else {
                        firstNum = i;
                        secondNum = j;
                    }
                    q = new Question(firstNum,secondNum,operationType,ToSMLevel.A2);
                    questionList.add(q);
                }
            }
        }
        Collections.shuffle(questionList);

    }

    public Question getCurrentQuestion(){
        return questionList.get(currentQuestionIdx);
    }
    public int getCurrentQuestionIdx(){
        return currentQuestionIdx;
    }
    public int getQuestionRemaining() {
        return questionList.size() - getCurrentQuestionIdx();
    }

    public void answer(String answer,long time){
        boolean correct=false;
        int ans = 0;
        try{
            ans = Integer.valueOf(answer);
            correct = ToSMUtil.evaluate(getCurrentQuestion(),ans);
        } catch (Exception ex){
        }
        Answer a = new Answer(getCurrentQuestion(),ans,correct,time);
        answerList.add(a);
        if(currentQuestionIdx==questionList.size()-1){
            isQuizEnd = true;
        } else currentQuestionIdx++;
    }
}
