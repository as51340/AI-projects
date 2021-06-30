package searching.state.football.ai.data;

import java.util.Comparator;

/**
 * Provides different comparators
 * @author askrgat
 *
 */
public class Comparators {

	/**
	 * Comparator for using uniform cost search algorithm
	 */
	public static final Comparator<HeuristicNode<?>> COMPARE_BY_COST = (n1, n2) -> Double.compare(n1.getCost(), n2.getCost());
	
	/**
	 * Compares two heuristic node using only information about estimation of distance between node and final node. This comparator is used in greedy
	 * algorithm
	 */
	public static final Comparator<HeuristicNode<?>> COMPARE_BY_HEURISTICS = (n1, n2) -> Double.compare(n1.getTotalEstimatedCost() - n1.getCost(), 
			n2.getTotalEstimatedCost() - n2.getCost());
	
	/**
	 * Comparator used in A search algorithm
	 */
	public static final Comparator<HeuristicNode<?>> COMPARE_BY_TOTAL = (n1, n2) -> Double.compare(n1.getTotalEstimatedCost(), n2.getTotalEstimatedCost());
	
	
}
