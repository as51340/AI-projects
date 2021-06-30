package searching.state.football.ai.heuristics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import searching.state.football.ai.data.Pair;
import searching.state.football.constants.Constants;
import searching.state.football.constants.MappedConstants;
import searching.state.football.models.Formation;
import searching.state.football.models.Team;

public class HeuristicsEuclidean implements Heuristics<Team> {

	private MappedConstants mappedConstants;

	public HeuristicsEuclidean() {
		mappedConstants = new MappedConstants();
	}

	@Override
	public double getEstimatedCost(Team state) {
		double sol = 0.0;
		int i, j, i1, j1;
		Map<String, List<Pair<Integer>>> map = null;
		if(state.getFormation() == Formation.FOUR_FOUR_TWO) map = mappedConstants.getMapped_positions_442();
		else throw new IllegalStateException("Not yet implemented");
		for (int k = 0; k < Constants.stateSize; k++) {
			i = k / Constants.rowSize;
			j = k % Constants.rowSize;
			String pos = state.getPositions().get(k).getName();
			if(pos.equals("*")) continue;
			List<Pair<Integer>> position_list = map.get(pos);
			int size = position_list.size();
			double min = Double.MAX_VALUE;
			double rez = 0.0;
			for (int z = 0; z < size; z++) {
				i1 = position_list.get(z).getT1();
				j1 = position_list.get(z).getT1();
				rez = Math.sqrt((i - i1) * (i - i1) + (j - j1) * (j - j1));
				if (rez < min)
					min = rez;
			}
			sol += min;
		}
		return sol;
	}

}
