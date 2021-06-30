package searching.state.football.ai.heuristics;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import searching.state.football.constants.Constants;
import searching.state.football.models.Formation;
import searching.state.football.models.Team;
import searching.state.football.positions.Position;

/**
 * 
 * @author askrgat
 *
 * @param <T>
 */
public class HeuristicsNumWrong implements Heuristics<Team>{

	@Override
	public double getEstimatedCost(Team state) {
		if(state.getFormation() == Formation.FOUR_FOUR_TWO) {
			return numberOfWrongPositions(state, Constants.real_positions_442);
		} else if(state.getFormation() == Formation.FOUR_TWO_FOUR) {
			return numberOfWrongPositions(state, Constants.real_positions_424);
		} else if(state.getFormation() == Formation.FOUR_THREE_THREE) {
			List<Double> nums = new ArrayList<Double>();
			nums.add(numberOfWrongPositions(state, Constants.real_positions_433_v1));
			nums.add(numberOfWrongPositions(state, Constants.real_positions_433_v2));
			nums.add(numberOfWrongPositions(state, Constants.real_positions_433_v3));
			nums.add(numberOfWrongPositions(state, Constants.real_positions_433_v4));
			double min = Double.MAX_VALUE;
			for(int i = 0; i < 4; i++) { //number of possible versions of 433 formations
				if(nums.get(i) < min) {
					min = nums.get(i);
				}
			}
			return min;
		}
		throw new IllegalStateException("Unimplemented formation for this type of heuristics!");
	}
	
	private double numberOfWrongPositions(Team state, String[] positions) {
		List<Position> team_positions = state.getPositions();
		double sol = 0.0;
		for(int i = 0; i < Constants.stateSize; i++) {
			if(!team_positions.get(i).getName().equals(positions[i])) sol+=1.0;
		}
		return sol;
	}

}
