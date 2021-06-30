package searching.state.football.validators;

import searching.state.football.positions.Position;

/**
 * Validates position name, otherwise we could get in trouble when implementing AI algorithms.
 * @author Andi Škrgat
 *
 */
public class PositionValidator<T> implements IValidator<T>{
	
	@Override
	public boolean validate(T state) {
		Position position = (Position) state;
		if(!position.getName().equals("RB") && !position.getName().equals("CB") && !position.getName().equals("LB") && !position.getName().equals("RM")
				&& !position.getName().equals("LM") && !position.getName().equals("CM") && !position.getName().equals("RW") && !position.getName().equals("ST")
				&& !position.getName().equals("LW") && !position.getName().equals("CDM")) return false;
		return true;
	}
	
	/**
	 * @param position
	 * @return true if position is valid position in 442 formation
	 */
	public boolean isValidFourFourTwoPosition(String position) {
		if(!position.equals("RB") && !position.equals("CB") && !position.equals("LB") && !position.equals("RM") && !position.equals("CM") && 
				!position.equals("LM") && !position.equals("ST")) return false;
		return true;
	}
	
	/**
	 * 
	 * @param position
	 * @return true if position is valid position in 424 formation
	 */
	public boolean isValidFourTwoFourPosition(String position) {
		if(!position.equals("RB") && !position.equals("CB") && !position.equals("LB") && !position.equals("CDM") && !position.equals("RW") && 
				!position.equals("LW") && !position.equals("ST")) return false;
		return true;
	}
	
	/**
	 * @param position
	 * @return if position is valid position in 433 formation
	 */
	public boolean isValidFourThreeThreeFormation(String position) {
		if(!position.equals("RB") && !position.equals("CB") && !position.equals("LB") && !position.equals("CDM") && !position.equals("CM") && 
				!position.equals("LW") && !position.equals("ST") && !position.equals("RW")) return false;
		return true;
	}

}
