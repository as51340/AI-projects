package searching.state.football.validators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import searching.state.football.models.Formation;
import searching.state.football.models.Team;
import searching.state.football.positions.Position;
import searching.state.football.util.FootballFactory;
import searching.state.football.validators.FormationValidator;

class ValidatorTest {
	
	private FootballFactory ff = new FootballFactory();

	@Test
	void validateFourFourTwoFormation() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		positions.addAll(ff.createFourMiddleline());
		positions.addAll(ff.createOffensiveLine());
		Team team = new Team("Milan", Formation.FOUR_FOUR_TWO, positions);
		FormationValidator<Team> validator = new FormationValidator<>();
		assertEquals(true, validator.validate(team));
	}
	
	@Test
	void invalidateFourFourTwoFormationStrikerWrong() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		positions.addAll(ff.createFourMiddleline());
		Position st1 = new Position("ST");
		positions.add(st1);
		Position empty1 = new Position("");
		positions.add(empty1);
		Position st2 = new Position("ST");
		positions.add(st2);
		Position empty2 = new Position("");
		positions.add(empty2);
		Team team = new Team("Milan", Formation.FOUR_FOUR_TWO, positions);
		FormationValidator<Team> validator = new FormationValidator<>();
		assertEquals(false, validator.validate(team));
	}
	
	@Test
	void invalidateFourFourTwoFormationStrikerWrong2() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		positions.addAll(ff.createFourMiddleline());
		Position empty1 = new Position("");
		positions.add(empty1);
		Position st1 = new Position("ST");
		positions.add(st1);
		Position empty2 = new Position("");
		positions.add(empty2);
		Position st2 = new Position("ST");
		positions.add(st2);
		Team team = new Team("Milan", Formation.FOUR_FOUR_TWO, positions);
		FormationValidator<Team> validator = new FormationValidator<>();
		assertEquals(false, validator.validate(team));
	}
	
	@Test
	void invalidateDefensiveLine1() {
		List<Position> positions = new ArrayList<>();
		Position rb = new Position("RB");
		positions.add(rb);
		Position cb1 = new Position("CB");
		positions.add(cb1);
		Position cb2 = new Position("LB");
		positions.add(cb2);
		Position lb = new Position("CB");
		positions.add(lb);
		positions.addAll(ff.createFourMiddleline());
		positions.addAll(ff.createOffensiveLine());
		Team team = new Team("Milan", Formation.FOUR_FOUR_TWO, positions);
		FormationValidator<Team> validator = new FormationValidator<>();
		assertEquals(false, validator.validate(team));
	}
	
	@Test
	void invalidateDefensiveLine2() {
		List<Position> positions = new ArrayList<>();
		Position rb = new Position("LB");
		positions.add(rb);
		Position cb1 = new Position("CB");
		positions.add(cb1);
		Position cb2 = new Position("CB");
		positions.add(cb2);
		Position lb = new Position("RB");
		positions.add(lb);
		positions.addAll(ff.createFourMiddleline());
		positions.addAll(ff.createOffensiveLine());
		Team team = new Team("Milan", Formation.FOUR_FOUR_TWO, positions);
		FormationValidator<Team> validator = new FormationValidator<>();
		assertEquals(false, validator.validate(team));
	}
	
	@Test
	void invalidateCentralLine() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		Position rm = new Position("LM");
		positions.add(rm);
		Position cm1 = new Position("CM");
		positions.add(cm1);
		Position cm2 = new Position("CM");
		positions.add(cm2);
		Position lm = new Position("RM");
		positions.add(lm);
		positions.addAll(ff.createOffensiveLine());
		Team team = new Team("Milan", Formation.FOUR_FOUR_TWO, positions);
		FormationValidator<Team> validator = new FormationValidator<>();
		assertEquals(false, validator.validate(team));
	}
	
	@Test
	void validateFourTwoFourFormation() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		positions.addAll(ff.createTwoMidfielderes());
		positions.addAll(ff.createFourAttackers());
		Team team = new Team("Milan", Formation.FOUR_TWO_FOUR, positions);
		FormationValidator<Team> validator = new FormationValidator<>();
		assertEquals(true, validator.validate(team));
	}
	
	@Test
	void invalidateFourTwoFourFormationCentral() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		Position rm = new Position("CDM");
		positions.add(rm);
		Position cm1 = new Position("CDM");
		positions.add(cm1);
		Position cm2 = new Position("CDM");
		positions.add(cm2);
		Position lm = new Position("LM");
		positions.add(lm);
		positions.addAll(ff.createFourAttackers());
		assertThrows(IllegalStateException.class, () -> {
			Team team = new Team("Milan", Formation.FOUR_TWO_FOUR, positions);
			FormationValidator<Team> validator = new FormationValidator<>();
			validator.validate(team);
		});
	}
	
	@Test
	void validateFourThreeThreeFormation1() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		positions.addAll(ff.createThreeMiddlelineV1());
		positions.addAll(ff.createThreeAttacklineV1());
		Team team = new Team("Milan", Formation.FOUR_THREE_THREE, positions);
		FormationValidator<Team> validator = new FormationValidator<>();
		assertEquals(true, validator.validate(team));
	}
	
	@Test
	void validateFourThreeThreeFormation2() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		positions.addAll(ff.createThreeMiddlelineV2());
		positions.addAll(ff.createThreeAttacklineV2());
		Team team = new Team("Milan", Formation.FOUR_THREE_THREE, positions);
		FormationValidator<Team> validator = new FormationValidator<>();
		assertEquals(true, validator.validate(team));
	}
	
	@Test
	void validateFourThreeThreeFormation3() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		positions.addAll(ff.createThreeMiddlelineV1());
		positions.addAll(ff.createThreeAttacklineV2());
		Team team = new Team("Milan", Formation.FOUR_THREE_THREE, positions);
		FormationValidator<Team> validator = new FormationValidator<>();
		assertEquals(true, validator.validate(team));
	}
	
	@Test
	void validateFourThreeThreeFormation4() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		positions.addAll(ff.createThreeMiddlelineV2());
		positions.addAll(ff.createThreeAttacklineV1());
		Team team = new Team("Milan", Formation.FOUR_THREE_THREE, positions);
		FormationValidator<Team> validator = new FormationValidator<>();
		assertEquals(true, validator.validate(team));
	}
	
	
	@Test
	void invalidateFourThreeThreeFormation1() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		positions.addAll(ff.createThreeMiddlelineV1());
		positions.addAll(ff.createTwoMidfielderes());
		Team team = new Team("Milan", Formation.FOUR_THREE_THREE, positions);
		FormationValidator<Team> validator = new FormationValidator<>();
		assertEquals(false, validator.validate(team));
	}
	
	@Test
	void invalidateFourThreeThreeFormation2() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		positions.addAll(ff.createThreeMiddlelineV1());
		positions.addAll(ff.createFourAttackers());
		assertThrows(IllegalStateException.class, () -> {
			Team team = new Team("Milan", Formation.FOUR_THREE_THREE, positions);
			FormationValidator<Team> validator = new FormationValidator<>();
			validator.validate(team);
		});
	}
	
	@Test
	void invalidateFourThreeThreeFormation3() {
		List<Position> positions = new ArrayList<>();
		positions.addAll(ff.createFourBackline());
		positions.addAll(ff.createThreeMiddlelineV1());
		positions.addAll(ff.createOffensiveLine());
		Team team = new Team("Milan", Formation.FOUR_THREE_THREE, positions);
		FormationValidator<Team> validator = new FormationValidator<>();
		assertEquals(false, validator.validate(team));
	}
	

}
