package searching.state.football.ai.algorithms.blind;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import searching.state.football.ai.algorithms.ISearchAlgorithm;
import searching.state.football.ai.data.CostNode;
import searching.state.football.ai.data.Node;
import searching.state.football.ai.data.StateCostPair;

/**
 * Searching path with minimal price. Dijkstra's algorithm. We are implying here that {@linkplain Collection} is structured from elements 
 * {@linkplain StateCostPair}. This algorithm is complete, meaning it will always find solution if exists but not only that, it will always find 
 * path with minimal price on it.
 * @author askrgat. Preuzeto od @marcupic.
 *
 * @param <T>
 */
public class UniformCostSearchAlgorithm<T> implements ISearchAlgorithm<T>{

	@SuppressWarnings("unchecked")
	@Override
	public Optional<Node<T>> search(T startState, Function<T, Collection<T>> succFunction, Predicate<T> goal, FileWriter fw) throws IOException {
		
		Queue<CostNode<T>> open = new PriorityQueue<CostNode<T>>(); //sorts ascending prices not descending
		open.add(new CostNode<>(startState, null, 0.0));
		Set<T> visited = new HashSet<>();
		
		
		while(!open.isEmpty()) {
			CostNode<T> checkNode = open.remove();
			visited.add(checkNode.getState());
			fw.write(checkNode.getState().toString() + "\n");
			if(goal.test(checkNode.getState())) return Optional.of(checkNode);
			Collection<T> newStates = succFunction.apply(checkNode.getState());
			for(T newState: newStates) {
				if(visited.contains(newState)) continue;
				open.add(new CostNode<>(newState, checkNode, checkNode.getCost() + ((StateCostPair<T>) newState).getCost()));
			}
		}
		
		return Optional.empty();
	}

}
