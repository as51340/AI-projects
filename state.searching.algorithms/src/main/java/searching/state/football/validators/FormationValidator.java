package searching.state.football.validators;

import java.util.List;

import searching.state.football.constants.Constants;
import searching.state.football.models.Formation;
import searching.state.football.models.Team;

/**
 * Class for validating team's formation
 * @author Andi Škrgat
 *
 */
public class FormationValidator<T> implements IValidator<T>{
	

	/**
	 * Method for validating team's formation
	 * @return
	 */
	@Override
	public boolean validate(T state) {
		Team team = (Team) state;
		if(team.getPositions().size() != Constants.stateSize) return false;
		Formation formation = team.getFormation();
		if(formation == Formation.FOUR_FOUR_TWO) {
			return checkFourFourTwoFormation(state);
		} else if(formation == Formation.FOUR_THREE_THREE) {
			return checkFourThreeThreeFormation(state);
		} else if(formation == Formation.FOUR_TWO_FOUR) {
			return checkFourTwoFourFormation(state);
		}
		throw new UnsupportedOperationException("Team's current formation is not yet implemented!");
	}
	
	
	/**
	 * Checks if team has valid 4-2-4 formation
	 * @return
	 */
	public boolean checkFourTwoFourFormation(T state) {
		if(!checkFourAtTheBackline(state)) return false;
		if(!checkTwoMidfielders(state)) return false;
		if(!checkFourAttackers(state)) return false;
		return true;
	}
	
	/**
	 * Checks if team has valid 4-3-3 formation
	 * @return
	 */
	public boolean checkFourThreeThreeFormation(T state) {
		if(!checkFourAtTheBackline(state)) return false;
		if(!checkThreeInTheMiddle(state)) return false;
		if(!checkThreeAttackers(state)) return false;
		return true;
	}
	
	
	
	/**
	 * Checks if team has valid 4-4-2 formation
	 * @return true or false
	 */
	public boolean checkFourFourTwoFormation(T state) {
		if(!checkFourAtTheBackline(state)) return false;
		if(!checkFourInTheMiddle(state)) return false;
		if(!checkTwoStrikers(state)) return false;
		return true;
	}
	
	/**
	 * Checks if some team has valid four positions at the back.
	 * @return true or false
	 */
	public boolean checkFourAtTheBackline(T state) {
		Team team = (Team) state;
		if(!team.getPositions().get(0).getName().equals("RB")) return false;
		if(!team.getPositions().get(1).getName().equals("CB")) return false;
		if(!team.getPositions().get(2).getName().equals("CB")) return false;
		if(!team.getPositions().get(3).getName().equals("LB")) return false;
		return true;
	}
	

	/**
	 * Checks correct positions of two midfielders at the pitch
	 * @return
	 */
	private boolean checkTwoMidfielders(T state) {
		Team team = (Team) state;
		if(!(team.getPositions().get(4).getName().equals("*"))) return false;
		if(!team.getPositions().get(5).getName().equals("CDM")) return false;
		if(!team.getPositions().get(6).getName().equals("CDM")) return false;
		if(!(team.getPositions().get(7).getName().equals("*"))) return false;
		return true;
	}
	
	/**
	 * Checks if three midfielders are at the correct positions on the pitch
	 * @return
	 */
	public boolean checkThreeInTheMiddle(T state) {
		Team team = (Team) state;
		if((!team.getPositions().get(4).getName().equals("CM") || !team.getPositions().get(5).getName().equals("CDM") || !team.getPositions().get(6).getName().equals("CM") || !(team.getPositions().get(7).getName().equals("*"))) && 
				(!(team.getPositions().get(4).getName().equals("*")) || !team.getPositions().get(5).getName().equals("CM") || !team.getPositions().get(6).getName().equals("CDM") || !team.getPositions().get(7).getName().equals("CM"))) return false;
		return true;
	}
	
	/**
	 * Checks if some team has valid four positions in the middle
	 * @return
	 */
	public boolean checkFourInTheMiddle(T state) {
		Team team = (Team) state;
		if(!team.getPositions().get(4).getName().equals("RM")) return false;
		if(!team.getPositions().get(5).getName().equals("CM")) return false;
		if(!team.getPositions().get(6).getName().equals("CM")) return false;
		if(!team.getPositions().get(7).getName().equals("LM")) return false;
		return true;
	}
	

	/**
	 * Checks correct positions of four attackers at the pitch
	 * @return
	 */
	public boolean checkFourAttackers(T state) {
		Team team = (Team) state;
		if(!team.getPositions().get(8).getName().equals("RW")) return false;
		if(!team.getPositions().get(9).getName().equals("ST")) return false;
		if(!team.getPositions().get(10).getName().equals("ST")) return false;
		if(!team.getPositions().get(11).getName().equals("LW")) return false;
		return true;
	}
	
	/**
	 * Checks if three attackers have correct positions in the team
	 * @return
	 */
	public boolean checkThreeAttackers(T state) {
		Team team = (Team) state;
		if((!team.getPositions().get(8).getName().equals("RW") || !team.getPositions().get(9).getName().equals("ST") || !team.getPositions().get(10).getName().equals("LW") || !(team.getPositions().get(11).getName().equals("*"))) && 
				(!(team.getPositions().get(8).getName().equals("*")) || !team.getPositions().get(9).getName().equals("RW") || !team.getPositions().get(10).getName().equals("ST") || !team.getPositions().get(11).getName().equals("LW"))) return false;
		return true;
	}
	
	/**
	 * Checks positions of strikers in the team
	 * @return
	 */
	public boolean checkTwoStrikers(T state) {
		Team team = (Team) state;
		if(!(team.getPositions().get(8).getName().equals("*"))) return false;
		if(!team.getPositions().get(9).getName().equals("ST")) return false;
		if(!team.getPositions().get(10).getName().equals("ST")) return false;
		if(!(team.getPositions().get(11).getName().equals("*"))) return false;
		return true;
	}
}
