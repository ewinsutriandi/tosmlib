package com.matematikadetik.tosm.model;


import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by ewin.sutriandi on 11/9/2017.
 */

public class Question implements Serializable, Comparable<Question>, Comparator<Question>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int firstNum;
    private int secondNum;
    private int operation;
    public ToSMLevel level;

    public Question(int firstNum,int secondNum, int operation, ToSMLevel level){
        this.firstNum = firstNum;
        this.secondNum = secondNum;
        this.operation = operation;
        this.level = level;
    }

    public int getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public int getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(int secondNum) {
        this.secondNum = secondNum;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public boolean isFirstNumLarger(){
        return firstNum > secondNum;
    }
    public boolean isFirstNumLesser(){
        return firstNum < secondNum;
    }

    public boolean isDifficult(){
        boolean difficult = false;
        switch (level){
            case A1:
                switch (operation){
                    case ToSMUtil.OPERATION_ADD:
                        if ((firstNum + secondNum)>=10) difficult = true;
                        break;
                    case ToSMUtil.OPERATION_SUB:
                        if ((firstNum )>=10) difficult = true;
                        break;
                    case ToSMUtil.OPERATION_MULT:
                        if ((firstNum * secondNum)>=10) difficult = true;
                        break;
                    case ToSMUtil.OPERATION_DIV:
                        if ((firstNum )>=10) difficult = true;
                        break;
                }
                break;
            case A2:
                String answer="";
                switch (operation){
                    case ToSMUtil.OPERATION_ADD:
                        answer = String.valueOf(firstNum+secondNum);
                        break;
                    case ToSMUtil.OPERATION_SUB:
                        answer = String.valueOf(firstNum-secondNum);
                        break;
                    case ToSMUtil.OPERATION_MULT:
                        answer = String.valueOf(firstNum*secondNum);
                        break;
                    case ToSMUtil.OPERATION_DIV:
                        answer = String.valueOf(firstNum/secondNum);
                        break;
                }
                if (answer.length()>=3) difficult = true;
                break;
        }
        return difficult;
    }

    public String toString(){
        String opSymbol;
        switch (operation){
            case ToSMUtil.OPERATION_ADD:
                opSymbol = "+";
                break;
            case ToSMUtil.OPERATION_SUB:
                opSymbol = "-";
                break;
            case ToSMUtil.OPERATION_MULT:
                opSymbol = "X";
                break;
            case ToSMUtil.OPERATION_DIV:
                opSymbol = ":";
                break;
            default:
                opSymbol="";
                break;
        }
        return firstNum+" "+opSymbol+" "+secondNum;
    }
    public String toToSMString(){
        return firstNum+"\n"+secondNum;
    }

    @Override
    public int compareTo(Question o) {
        if (firstNum != o.firstNum){
            return firstNum - o.firstNum;
        } else return secondNum - o.secondNum;
    }

    @Override
    public int compare(Question question, Question question2) {
        return question.compareTo(question2);
    }
}
