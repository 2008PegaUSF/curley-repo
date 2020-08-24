package app.week1core;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import questionset.*;
import operations.Question;

public class App {
	//some helper members for the solutions
	public static Scanner con = new Scanner(System.in);
	public static Random rando = new Random();
	public static void main(String[] args) {
		//storing our questions in an arraylist...
		ArrayList<Question> questionList = new ArrayList<Question>();
		questionList.add(new Q1Solution());
		questionList.add(new Q2Solution());
		questionList.add(new Q3Solution());
		questionList.add(new Q4Solution());
		questionList.add(new Q5Solution());
		questionList.add(new Q6Solution());
		questionList.add(new Q7Solution());
		questionList.add(new Q8Solution());
		questionList.add(new Q9Solution());
		questionList.add(new Q10Solution());
		questionList.add(new Q11Solution());
		questionList.add(new Q12Solution());
		questionList.add(new Q13Solution());
		questionList.add(new Q14Solution());
		questionList.add(new Q15Solution());
		questionList.add(new Q16Solution(args));
		questionList.add(new Q17Solution());
		questionList.add(new Q18Solution());
		questionList.add(new Q19Solution());
		questionList.add(new Q20Solution());
		
		//watch it fly!
		for(Question q : questionList) {
			q.performTask();
		}

	}
}
