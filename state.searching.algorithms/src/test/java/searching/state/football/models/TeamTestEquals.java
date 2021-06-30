package searching.state.football.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import searching.state.football.positions.Position;
import searching.state.football.util.FootballFactory;
import searching.state.football.util.PositionChanger;

/**
 * Tests belowe are for team equals testing
 * 
 * @author askrgat
 *
 */
class TeamTestEquals {

	private FootballFactory ff = new FootballFactory();

	@Test
	void copyConstructorTest() {
		List<Position> positions = ff.createFourBackline();
		positions.addAll(ff.createFourMiddleline());
		positions.addAll(ff.createOffensiveLine());
		Team team1 = new Team("Barcelona", Formation.FOUR_FOUR_TWO, positions);
		Team team2 = new Team(team1);
		assertEquals(team1, team2);
	}

	@Test
	void differentTeamsSamePositions() {
		List<Position> positions = ff.createFourBackline();
		positions.addAll(ff.createFourMiddleline());
		positions.addAll(ff.createOffensiveLine());
		Team team1 = new Team("Barcelona", Formation.FOUR_FOUR_TWO, positions);
		Team team2 = new Team("Barcelona", Formation.FOUR_FOUR_TWO, positions);
		assertEquals(team1, team2);
	}

	@Test
	void differentTeamNames() {
		List<Position> positions = ff.createFourBackline();
		positions.addAll(ff.createFourMiddleline());
		positions.addAll(ff.createOffensiveLine());
		Team team1 = new Team("Barcelona", Formation.FOUR_FOUR_TWO, positions);
		Team team2 = new Team("Real Madrid", Formation.FOUR_FOUR_TWO, positions);
		assertNotEquals(team1, team2);
	}

	@Test
	void differentFormations() {
		List<Position> positions = ff.createFourBackline();
		positions.addAll(ff.createFourMiddleline());
		positions.addAll(ff.createOffensiveLine());
		Team team1 = new Team("Barcelona", Formation.FOUR_FOUR_TWO, positions);
		Team team2 = new Team("Barcelona", Formation.FOUR_TWO_FOUR, positions);
		assertNotEquals(team1, team2);
	}

	@Test
	void wrongPositionsRegardingFormations() {
		List<Position> positions1 = ff.createFourBackline();
		positions1.addAll(ff.createFourMiddleline());
		positions1.addAll(ff.createOffensiveLine());
		List<Position> positions2 = ff.createFourBackline();
		positions2.addAll(ff.createFourAttackers());
		positions2.addAll(ff.createTwoMidfielderes());
		Team team1 = new Team("Barcelona", Formation.FOUR_FOUR_TWO, positions1);
		Team team2 = new Team("Barcelona", Formation.FOUR_FOUR_TWO, positions2);
		assertNotEquals(team1, team2);
	}

	@Test
	void wrongPositionsRegardingFormations1() {
		List<Position> positions1 = ff.createFourBackline();
		positions1.addAll(ff.createFourMiddleline());
		positions1.addAll(ff.createOffensiveLine());
		List<Position> positions2 = ff.createFourBackline();
		positions2.addAll(ff.createTwoMidfielderes());
		positions2.addAll(ff.createFourAttackers());
		Team team1 = new Team("Barcelona", Formation.FOUR_FOUR_TWO, positions1);
		Team team2 = new Team("Barcelona", Formation.FOUR_FOUR_TWO, positions2);
		assertNotEquals(team1, team2);
	}

	@Test
	void same433FormationV1() {
		List<Position> positions = ff.createFourBackline();
		positions.addAll(ff.createThreeMiddlelineV1());
		positions.addAll(ff.createThreeAttacklineV1());
		Team team1 = new Team("Barcelona", Formation.FOUR_THREE_THREE, positions);
		Team team2 = new Team("Barcelona", Formation.FOUR_THREE_THREE, positions);
		assertEquals(team1, team2);
	}

	@Test
	void different433FormationV2() {
		List<Position> positions1 = ff.createFourBackline();
		positions1.addAll(ff.createThreeMiddlelineV1());
		positions1.addAll(ff.createThreeAttacklineV1());
		List<Position> positions2 = ff.createFourBackline();
		positions2.addAll(ff.createThreeMiddlelineV2());
		positions2.addAll(ff.createThreeAttacklineV2());
		Team team1 = new Team("Barcelona", Formation.FOUR_THREE_THREE, positions1);
		Team team2 = new Team("Barcelona", Formation.FOUR_THREE_THREE, positions2);
		assertNotEquals(team1, team2);
	}

	@Test
	void different433FormationV3() {
		List<Position> positions1 = ff.createFourBackline();
		positions1.addAll(ff.createThreeMiddlelineV1());
		positions1.addAll(ff.createThreeAttacklineV2());
		List<Position> positions2 = ff.createFourBackline();
		positions2.addAll(ff.createThreeMiddlelineV1());
		positions2.addAll(ff.createThreeAttacklineV2());
		Team team1 = new Team("Barcelona", Formation.FOUR_THREE_THREE, positions1);
		Team team2 = new Team("Barcelona", Formation.FOUR_THREE_THREE, positions2);
		assertEquals(team1, team2);
	}

	@Test
	void different433FormationV4() {
		List<Position> positions1 = ff.createFourBackline();
		positions1.addAll(ff.createThreeMiddlelineV2());
		positions1.addAll(ff.createThreeAttacklineV1());
		List<Position> positions2 = ff.createFourBackline();
		positions2.addAll(ff.createThreeMiddlelineV2());
		positions2.addAll(ff.createThreeAttacklineV1());
		Team team1 = new Team("Barcelona", Formation.FOUR_FOUR_TWO, positions1);
		Team team2 = new Team("Barcelona", Formation.FOUR_FOUR_TWO, positions2);
		assertEquals(team1, team2);
	}

	@Test
	void equalsAndHashCodeNotWorking() {
		List<Position> positions1 = ff.createRealFourFourTwo();
		Team team1 = new Team("Barcelona", Formation.FOUR_FOUR_TWO, positions1);
		team1.calculateEmptyPositions();
		Set<Team> teams = new HashSet<Team>();
		teams.add(team1);
		List<Team> newStates = (List<Team>) PositionChanger.getNextStates(team1);
		List<Team> newNewStates = (List<Team>) PositionChanger.getNextStates(newStates.get(0));
		Team team2 = newNewStates.get(1);
		System.out.println(team2);
//		System.out.println(team2.hashCode());
//		System.out.println(teams.contains(team2));
		System.out.println("All");
		System.out.println(team1.hashCode());
		System.out.println(team2.hashCode());
		System.out.println("Names");
		System.out.println(team1.getName().hashCode());
		System.out.println(team2.getName().hashCode());
		System.out.println("Formation");
		System.out.println(team1.getFormation().hashCode());
		System.out.println(team2.getFormation().hashCode());
		System.out.println("Positions");
		System.out.println(team1.getPositions().hashCode());
		System.out.println(team2.getPositions().hashCode());
		System.out.println("Empty Positions");
		System.out.println(Arrays.hashCode(team1.getEmptyPositions()));
		System.out.println(Arrays.hashCode(team2.getEmptyPositions()));
		System.out.println("Team1 empty");
		System.out.println(team1.getEmptyPositions()[0]);
		System.out.println(team1.getEmptyPositions()[1]);
		System.out.println("Team2 empty");
		System.out.println(team2.getEmptyPositions()[0]);
		System.out.println(team2.getEmptyPositions()[1]);
		System.out.println("Team2 parent");
		System.out.println(newStates.get(0).getEmptyPositions()[0]);
		System.out.println(newStates.get(0).getEmptyPositions()[1]);
	}

	@Test
	void hashCode1() {
		FootballFactory ff = new FootballFactory();
		Team team1 = new Team("Barcelona", Formation.FOUR_FOUR_TWO, ff.createRealFourFourTwo());
		Set<Team> teams = new HashSet<Team>();
		teams.add(team1);
		Team team2 = new Team("Barcelona", Formation.FOUR_FOUR_TWO, ff.createRealFourFourTwo());
		assertEquals(true, teams.contains(team2));
	}

	@Test
	void hashCode2() {
		FootballFactory ff = new FootballFactory();
		Team team1 = new Team("Barcelona", Formation.FOUR_FOUR_TWO, ff.createRealFourFourTwo());
		Set<Team> teams = new HashSet<Team>();
		teams.add(team1);
		Team team2 = new Team(team1);
		assertEquals(true, teams.contains(team2));
	}

	@Test
	void emptyPositions() {
		FootballFactory ff = new FootballFactory();
		Team team1 = new Team("Barcelona", Formation.FOUR_FOUR_TWO, ff.createRealFourFourTwo());
		Team team2 = new Team(team1);
		assertEquals(team1.getEmptyPositions()[0], team2.getEmptyPositions()[0]);
		assertEquals(team1.getEmptyPositions()[1], team2.getEmptyPositions()[1]);
	}

}
