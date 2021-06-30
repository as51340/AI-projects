package searching.state.football.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import searching.state.football.constants.Constants;
import searching.state.football.positions.Position;

/**
 * Factory for creating different football lines
 * 
 * @author Andi Škrgat
 *
 */
public class FootballFactory {

	/**
	 * Creates initial random formation
	 * 
	 * @param textPositions
	 * @return
	 */
	public List<Position> createRandomFormation(String[] textPositions) {
		Random random = new Random(new Date().getTime());
		int[] emptyPositions = random.ints(Constants.stateSize - 10, 0, Constants.stateSize).distinct().toArray();
		if (emptyPositions.length != 2 || (emptyPositions[0] == emptyPositions[1])) {
			throw new IllegalStateException("Error in createRandomFormation, empty positions: " + emptyPositions.length);
		}
		Set<Integer> usedPositions = new HashSet<Integer>();
		usedPositions.add(emptyPositions[0]);
		usedPositions.add(emptyPositions[1]);
		Position positions[] = new Position[Constants.stateSize];
		positions[emptyPositions[0]] = new Position("*");
		positions[emptyPositions[1]] = new Position("*");
		int j = 0;
		while (usedPositions.size() != Constants.stateSize) {
			int index = random.nextInt(Constants.stateSize);
			if (usedPositions.contains(index))
				continue;
			positions[index] = new Position(textPositions[j++]);
			usedPositions.add(index);
		}
		return Arrays.asList(positions);
	}

	/**
	 * @return 424 correct formation
	 */
	public List<Position> createRealFourTwoFour() {
		List<Position> positions = createFourBackline();
		positions.addAll(createTwoMidfielderes());
		positions.addAll(createFourAttackers());
		return positions;
	}

	/**
	 * Creates good formatted 442 formation
	 * 
	 * @return
	 */
	public List<Position> createRealFourFourTwo() {
		List<Position> positions = createFourBackline();
		positions.addAll(createFourMiddleline());
		positions.addAll(createOffensiveLine());
		return positions;
	}

	/**
	 * Selects uniformly one of the possible four 433 formations
	 * 
	 * @return
	 */
	public List<Position> createRealFourThreeThree() {
		Random random = new Random();
		random.setSeed(new Date().getTime());
		int ran = random.nextInt(4);
		List<Position> v1 = createFourBackline();
		if (ran == 0) {
			v1.addAll(createThreeMiddlelineV1());
			v1.addAll(createThreeAttacklineV1());
		} else if (ran == 1) {
			v1.addAll(createThreeMiddlelineV1());
			v1.addAll(createThreeAttacklineV2());
		} else if (ran == 2) {
			v1.addAll(createThreeMiddlelineV2());
			v1.addAll(createThreeAttacklineV1());
		} else {
			v1.addAll(createThreeMiddlelineV2());
			v1.addAll(createThreeAttacklineV2());
		}
		return v1;
	}

	/**
	 * Create four attackers
	 * 
	 * @return
	 */
	public List<Position> createFourAttackers() {
		List<Position> positions = new ArrayList<>();
		Position rw = new Position("RW");
		positions.add(rw);
		Position st1 = new Position("ST");
		positions.add(st1);
		Position st2 = new Position("ST");
		positions.add(st2);
		Position lw = new Position("LW");
		positions.add(lw);
		return positions;
	}

	/**
	 * Creates two midfielders
	 * 
	 * @return
	 */
	public List<Position> createTwoMidfielderes() {
		List<Position> positions = new ArrayList<>();
		Position empty1 = new Position("*");
		positions.add(empty1);
		Position cdm1 = new Position("CDM");
		positions.add(cdm1);
		Position cdm2 = new Position("CDM");
		positions.add(cdm2);
		Position empty2 = new Position("*");
		positions.add(empty2);
		return positions;
	}

	/**
	 * Creates defensive line
	 * 
	 * @return
	 */
	public List<Position> createFourBackline() {
		List<Position> positions = new ArrayList<>();
		Position rb = new Position("RB");
		positions.add(rb);
		Position cb1 = new Position("CB");
		positions.add(cb1);
		Position cb2 = new Position("CB");
		positions.add(cb2);
		Position lb = new Position("LB");
		positions.add(lb);
		return positions;
	}

	/**
	 * Creates central line
	 * 
	 * @return
	 */
	public List<Position> createFourMiddleline() {
		List<Position> positions = new ArrayList<>();
		Position rm = new Position("RM");
		positions.add(rm);
		Position cm1 = new Position("CM");
		positions.add(cm1);
		Position cm2 = new Position("CM");
		positions.add(cm2);
		Position lm = new Position("LM");
		positions.add(lm);
		return positions;
	}

	/**
	 * Creates central line
	 * 
	 * @return
	 */
	public List<Position> createThreeMiddlelineV1() {
		List<Position> positions = new ArrayList<>();
		Position rm = new Position("CM");
		positions.add(rm);
		Position cm1 = new Position("CDM");
		positions.add(cm1);
		Position cm2 = new Position("CM");
		positions.add(cm2);
		Position lm = new Position("*");
		positions.add(lm);
		return positions;
	}

	/**
	 * Creates central line
	 * 
	 * @return
	 */
	public List<Position> createThreeMiddlelineV2() {
		List<Position> positions = new ArrayList<>();
		Position lm = new Position("*");
		positions.add(lm);
		Position rm = new Position("CM");
		positions.add(rm);
		Position cm1 = new Position("CDM");
		positions.add(cm1);
		Position cm2 = new Position("CM");
		positions.add(cm2);
		return positions;
	}

	/**
	 * Creates attacking line
	 * 
	 * @return
	 */
	public List<Position> createThreeAttacklineV1() {
		List<Position> positions = new ArrayList<>();
		Position rm = new Position("RW");
		positions.add(rm);
		Position cm1 = new Position("ST");
		positions.add(cm1);
		Position cm2 = new Position("LW");
		positions.add(cm2);
		Position lm = new Position("*");
		positions.add(lm);
		return positions;
	}

	/**
	 * Creates central line
	 * 
	 * @return
	 */
	public List<Position> createThreeAttacklineV2() {
		List<Position> positions = new ArrayList<>();
		Position lm = new Position("*");
		positions.add(lm);
		Position rm = new Position("RW");
		positions.add(rm);
		Position cm1 = new Position("ST");
		positions.add(cm1);
		Position cm2 = new Position("LW");
		positions.add(cm2);
		return positions;
	}

	/**
	 * Creates offensive line
	 * 
	 * @return
	 */
	public List<Position> createOffensiveLine() {
		List<Position> positions = new ArrayList<>();
		Position empty1 = new Position("*");
		positions.add(empty1);
		Position st1 = new Position("ST");
		positions.add(st1);
		Position st2 = new Position("ST");
		positions.add(st2);
		Position empty2 = new Position("*");
		positions.add(empty2);
		return positions;
	}
}
