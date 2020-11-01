package edu.umb.cs681.hw04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PKL {

	private String team;
	private int matchesPlayed;
	private int won;
	private int lost;
	private int points;
	

	public PKL(String team, int matchesPlayed, int won, int lost, int points) {
		this.team = team;
		this.matchesPlayed = matchesPlayed;
		this.won = won;
		this.lost = lost;
		this.points = points;
		
	}

	public String getTeam() {
		return team;
	}

	public int getMatchesPlayed() {
		return matchesPlayed;
	}

	public int getWon() {
		return won;
	}

	public int getLost() {
		return lost;
	}

	public int getPoints() {
		return points;
	}

	
	public static void main(String args[]) {
		ArrayList<PKL> pklTable = new ArrayList<>();

		pklTable.add(new PKL("U Mumba", 12, 4, 8, 8));
		pklTable.add(new PKL("Telugu Titans",12, 5, 7, 10));
		pklTable.add(new PKL("Dabang Delhi KC", 12,3,9, 6));

		long numberOfTeams = pklTable.stream().count();
		System.out.println("Number of teams in PKL: " + numberOfTeams);

		PKL leastPointsTeam = pklTable.stream().min(Comparator.comparing(PKL::getPoints)).get();
		System.out.println("Team that has least points: " + leastPointsTeam.getTeam());

		List<PKL> sortedBypoints = pklTable.stream().sorted(Comparator.comparing(PKL::getPoints, Comparator.reverseOrder()))
				.collect(Collectors.toList());
		System.out.println("Teams sorted by Points: ");
		sortedBypoints.forEach((PKL pkl) -> System.out.println(pkl.getTeam() + ": " + pkl.getPoints()));

	}
}