package searching.state.football.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import searching.state.football.models.Formation;
import searching.state.football.models.Team;
import searching.state.football.positions.Position;
import searching.state.football.util.FootballFactory;
import searching.state.football.validators.FormationValidator;

class TeamTest {

	private FootballFactory ff = new FootballFactory();

	@Test
	void emptyPositionsCreation442() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		positions.addAll(ff.createFourMiddleline());
		positions.addAll(ff.createOffensiveLine());
		Team team = new Team("Milan", Formation.FOUR_FOUR_TWO, positions);
		assertEquals(8, team.getEmptyPositions()[0]);
		assertEquals(11, team.getEmptyPositions()[1]);
	}
	
	@Test
	void emptyPositionsCreation424() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		positions.addAll(ff.createTwoMidfielderes());
		positions.addAll(ff.createFourAttackers());
		Team team = new Team("Milan", Formation.FOUR_TWO_FOUR, positions);
		assertEquals(4, team.getEmptyPositions()[0]);
		assertEquals(7, team.getEmptyPositions()[1]);
	}
	
	@Test
	void emptyPositionsCreation433v1() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		positions.addAll(ff.createThreeMiddlelineV1());
		positions.addAll(ff.createThreeAttacklineV1());
		Team team = new Team("Milan", Formation.FOUR_THREE_THREE, positions);
		assertEquals(7, team.getEmptyPositions()[0]);
		assertEquals(11, team.getEmptyPositions()[1]);
	}
	
	@Test
	void emptyPositionsCreation433v2() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		positions.addAll(ff.createThreeMiddlelineV2());
		positions.addAll(ff.createThreeAttacklineV2());
		Team team = new Team("Milan", Formation.FOUR_THREE_THREE, positions);
		assertEquals(4, team.getEmptyPositions()[0]);
		assertEquals(8, team.getEmptyPositions()[1]);
	}
	
	@Test
	void emptyPositionsCreation433v3() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		positions.addAll(ff.createThreeMiddlelineV1());
		positions.addAll(ff.createThreeAttacklineV2());
		Team team = new Team("Milan", Formation.FOUR_THREE_THREE, positions);
		assertEquals(7, team.getEmptyPositions()[0]);
		assertEquals(8, team.getEmptyPositions()[1]);
	}
	
	@Test
	void emptyPositionsCreation433v4() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		positions.addAll(ff.createThreeMiddlelineV2());
		positions.addAll(ff.createThreeAttacklineV1());
		Team team = new Team("Milan", Formation.FOUR_THREE_THREE, positions);
		assertEquals(4, team.getEmptyPositions()[0]);
		assertEquals(11, team.getEmptyPositions()[1]);
	}

}
