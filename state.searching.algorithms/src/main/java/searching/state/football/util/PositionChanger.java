package searching.state.football.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import searching.state.football.constants.Constants;
import searching.state.football.models.Team;
import searching.state.football.positions.Position;

/**
 * Class that provides functionality for obtaining all new states from current
 * state
 * 
 * @author askrgat
 *
 */
public class PositionChanger {

	/**
	 * Index2 is always index of empty position
	 * 
	 * @param index1
	 * @param index2
	 */
	private static Team swapPositions(int index1, int index2, Team team) {
		Team newTeam = new Team(team);
		newTeam.setPosition(index2, new Position(team.getPositions().get(index1)));
		newTeam.setPosition(index1, new Position("*"));
		newTeam.calculateEmptyPositions();
		return newTeam;
	}

	/**
	 * Successor function, for one state calculates all new possible
	 * 
	 * @return
	 */
	public static Collection<Team> getNextStates(Team team) {
		Collection<Team> teams = new ArrayList<Team>();
		short emptyPositions[] = team.getEmptyPositions();
		int length = emptyPositions.length;
		for (int i = 0; i < length; i++) {
			int index1, index2 = emptyPositions[i];
			if (emptyPositions[i] >= Constants.rowSize && emptyPositions[i] < Constants.stateSize) { // if we can move // up
				index1 = emptyPositions[i] - Constants.rowSize;
				if(!team.getPositions().get(index1).equals(team.getPositions().get(index2))) {
					teams.add(swapPositions(index1,index2, team));
				}
			}
			if (emptyPositions[i] >= 0 && emptyPositions[i] < 2 * Constants.rowSize) { // we can move it down
				index1 = emptyPositions[i] + Constants.rowSize;
				if(!team.getPositions().get(index1).equals(team.getPositions().get(index2))) {
					teams.add(swapPositions(index1,index2, team));
				}
			}
			if (emptyPositions[i] % Constants.rowSize != 0) { // we can move it left
				index1 = emptyPositions[i] - 1;
				if(!team.getPositions().get(index1).equals(team.getPositions().get(index2))) {
					teams.add(swapPositions(index1,index2, team));
				}
			}
			if ((emptyPositions[i] + 1) % Constants.rowSize != 0) { // we can move it right
				index1 = emptyPositions[i] + 1;
				if(!team.getPositions().get(index1).equals(team.getPositions().get(index2))) {
					teams.add(swapPositions(index1,index2, team));
				}
			}
		}
		return teams;
	}
}
