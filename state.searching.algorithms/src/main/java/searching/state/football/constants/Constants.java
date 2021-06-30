package searching.state.football.constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import searching.state.football.ai.data.Pair;

/**
 * Class for saving different constants
 * @author askrgat
 *
 */
public class Constants {
	
	/**
	 * Number of possible states
	 */
	public static final short stateSize = 12; //GK is not included 
	
	/**
	 * Number of possible positions per line 
	 */
	public static final short rowSize = 4;
	
	/**
	 * Number of lines
	 */
	public static final short colSize = 3;
	
	/**
	 * All possible positions
	 */
	public static final String[] allPositions = {"RB, CB, CB, LB, RM, CDM, CM, LM, RW, ST, LW"}; 
	
	/**
	 * 442 positions
	 */
	public static final String[] positions_442 = {"RB", "CB", "CB", "LB", "RM", "CM", "CM", "LM", "ST", "ST"};
	
	/**
	 * 424 positions
	 */
	public static final String[] positions_424 = {"RB", "CB", "CB", "LB", "CDM", "CDM", "RW", "LW", "ST", "ST"};
	
	/**
	 * 433 positions
	 */
	public static final String[] positions_433 = {"RB", "CB", "CB", "LB", "CDM", "CM", "CM", "RW", "LW", "ST"};
	
	
	/**
	 * 442 positions
	 */
	public static final String[] real_positions_442 = {"RB", "CB", "CB", "LB", "RM", "CM", "CM", "LM", "*", "ST", "ST", "*"};
	
	/**
	 * 424 positions
	 */
	public static final String[] real_positions_424 = {"RB", "CB", "CB", "LB", "*", "CDM", "CDM", "*", "RW", "LW", "ST", "ST"};
	
	/**
	 * 433 positions 
	 */
	public static final String[] real_positions_433_v1 = {"RB", "CB", "CB", "LB", "*", "CDM", "CM", "CM", "*", "RW", "LW", "ST"};
	
	/**
	 * 433 positions 
	 */
	public static final String[] real_positions_433_v2 = {"RB", "CB", "CB", "LB", "*", "CDM", "CM", "CM", "RW", "LW", "ST", "*"};
	
	/**
	 * 433 positions 
	 */
	public static final String[] real_positions_433_v3 = {"RB", "CB", "CB", "LB", "CDM", "CM", "CM", "*", "RW", "LW", "ST", "*"};
	
	/**
	 * 433 positions 
	 */
	public static final String[] real_positions_433_v4 = {"RB", "CB", "CB", "LB", "CDM", "CM", "CM", "*", "*", "RW", "LW", "ST"};

	
}
