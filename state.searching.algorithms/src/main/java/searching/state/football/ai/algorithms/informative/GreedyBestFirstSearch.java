package searching.state.football.ai.algorithms.informative;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

import searching.state.football.ai.algorithms.ISearchAlgorithm;
import searching.state.football.ai.data.Comparators;
import searching.state.football.ai.data.HeuristicNode;
import searching.state.football.ai.data.Node;
import searching.state.football.ai.data.StateCostPair;
import searching.state.football.ai.heuristics.Heuristics;

/**
 * This implementation doesn't take into account price up to some node. In other words it only looks forward. This algorithm is not complete neither optimal but we can construct it to be complete
 * by using list of visited states. Time and space complexity O(b^d)
 * @author askrgat
 *
 * @param <T>
 */
public class GreedyBestFirstSearch<T> implements ISearchAlgorithm<T> {

	/*
	 * Heuristics used in greedy search
	 */
	private Heuristics<T> heuristics;
	
	/**
	 * Simple constructor
	 * @param heuristics
	 */
	public GreedyBestFirstSearch(Heuristics<T> heuristics) {
		this.heuristics = heuristics;
	}
	
	
	@Override
	public Optional<Node<T>> search(T startState, Function<T, Collection<T>> succFunction, Predicate<T> goal, FileWriter fw) throws IOException {
		
		Queue<HeuristicNode<T>> open = new PriorityQueue<>(Comparators.COMPARE_BY_HEURISTICS);
		HeuristicNode<T> startNode = new HeuristicNode<>(startState, null, 0.0, this.heuristics.getEstimatedCost(startState));
		open.add(startNode);
		
		while(!open.isEmpty()) {
			HeuristicNode<T> checkNode = open.remove();
			
			fw.write(checkNode.getState() + "\n");

			
			if(goal.test(checkNode.getState())) return Optional.of(checkNode);
			
			Collection<T> newStates = succFunction.apply(checkNode.getState());
			
			Iterable<StateCostPair<T>> realNewStates = StateCostPair.unitCostIterable(newStates);
			
			for(StateCostPair<T> newState: realNewStates) {
				double cost = checkNode.getCost() + newState.getCost();
				double total = cost + heuristics.getEstimatedCost(newState.getState());
				open.add(new HeuristicNode<T>(newState.getState(), checkNode, cost, total));
			}
			
			
		}
		return Optional.empty();
	}

}
