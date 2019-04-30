package com.matematikadetik.tosm.model;



import java.io.Serializable;

/**
 * Created by ewin.sutriandi on 11/9/2017.
 */

public class Answer implements Serializable, Comparable<Answer>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Question question;
    private int answer;
    private boolean correct;
    private long time;//in ms

    public Answer(Question question, int answer, boolean correct,long time) {
        this.question = question;
        this.answer = answer;
        this.correct = correct;
        this.time = time;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    public long getTime(){
        return time;
    }

    @Override
    public int compareTo(Answer answer) {
        return question.compareTo(answer.getQuestion());
    }
}
