package com.matematikadetik.tosm.example;

import com.matematikadetik.tosm.model.SessionManager;
import com.matematikadetik.tosm.model.ToSMUtil;
import com.matematikadetik.tosm.model.ToSMLevel;

public class InvokingTestSession {
	public static void main(String[] args) {
		 SessionManager sess = new SessionManager(ToSMUtil.OPERATION_ADD,ToSMLevel.A2,false);
		 System.out.println(sess.getCurrentQuestionIdx());
		 System.out.println(sess.getCurrentQuestion());
		 System.out.println(sess.getQuestionRemaining());
	}

}
