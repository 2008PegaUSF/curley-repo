package fightclub;

import java.util.List;

import fightclub.beans.FighterBean;
import fightclubdaoimpl.FighterDAOImpl;
import fightclubdaoimpl.MatchDAOImpl;

public class FightClubMain {

	public static void main(String[] args) {
		MatchDAOImpl matches = new MatchDAOImpl();
		FighterDAOImpl fighters = new FighterDAOImpl();

		List<FighterBean> fighterList;

		fighterList = fighters.getAllFighers();

		java.util.Random rand = new java.util.Random();

		java.util.Scanner console = new java.util.Scanner(System.in);
		
		int fighter1;
		int fighter2;

		int victory;

		System.out.println("WELCOME TO THE EXTREEEEEEEEEEME FIGHT CLUB");

		System.out.println("HERE IS YOUR LIST OF FIGHTERS, CHOOSE WISELY!");
		printFighterList(fighterList);
		boolean done = false;
		while (!done) {
			System.out
					.println("PLAYER ONE! CHOOSE YOUR FIGHTER !!!!1@ (in a range of 1 to " + fighterList.size() + ").");
			System.out.println();
			fighter1 = console.nextInt();
			try {
				if (fighter1 > 0 && fighter1 < fighterList.size()+1) {
					System.out.println("CHOICE ACCEPTED!");
				} else {

					throw new NumberFormatException();
				}
				/*
				 * switch (fighterList.size()) { case 1: { System.out.println } }
				 */
			} catch (NumberFormatException e) {
				System.out.println("PLAYER ONE! YOU SUCK AT ENTERING NUMBERS! PLAYER 2 WIIIIIIINNNNNNSSSSSSS");

			}
			System.out
					.println("PLAYER TWO! CHOOSE YOUR FIGHTER !!!!1@ (in a range of 1 to " + fighterList.size() + ").");
			System.out.println();
			fighter2 = console.nextInt();
			try {
				if (fighter1 > 0 && fighter1 < fighterList.size()+1) {
					System.out.println("CHOICE ACCEPTED!");
				} else {

					throw new NumberFormatException();
				}
				/*
				 * switch (fighterList.size()) { case 1: { System.out.println } }
				 */
			} catch (NumberFormatException e) {
				System.out.println("PLAYER TWO! YOU SUCK AT ENTERING NUMBERS! PLAYER 1 WIIIIIIINNNNNNSSSSSSS");

			}
			
			System.out.print(fighterList.get(fighter1-1).getFighterName() + " and " + fighterList.get(fighter2-1).getFighterName() +
								" get in a fight, ");
			
			victory = rand.nextInt(3)+1;
			switch(victory) {
			case 1:
				System.out.print(fighterList.get(fighter1-1).getFighterName() + " wins.\n");
				break;
			case 2:
				System.out.print(fighterList.get(fighter2-1).getFighterName() + " wins.\n");
				break;
			case 3:
				System.out.print("ARE BOTH LOSERS! NO DRAWS IN EXTREEEEEEEEME FIGHT CLUB!\n");
				break;
			}
			matches.createNewMatch(victory);
		}
	}

	private static void printFighterList(List<FighterBean> list) {
		for (FighterBean fighter : list) {
			System.out.println(fighter.toString());
		}

	}

}
