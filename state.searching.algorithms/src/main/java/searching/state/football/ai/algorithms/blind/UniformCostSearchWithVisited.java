package searching.state.football.ai.algorithms.blind;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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
 * Advanced version of UniformCostSearchAlgorithm. Time and space complexity are some not so pretty numbers so we'll leave it behind. Algorithm is 
 * complete and optimal.
 * @author askrgat
 *
 * @param <T>
 */
public class UniformCostSearchWithVisited<T> implements ISearchAlgorithm<T> {

	@SuppressWarnings("unchecked")
	@Override
	public Optional<Node<T>> search(T startState, Function<T, Collection<T>> succFunction, Predicate<T> goal, FileWriter fw) throws IOException {

		Queue<CostNode<T>> open = new PriorityQueue<CostNode<T>>();
		open.add(new CostNode<>(startState, null, 0.0));
		Set<T> visited = new HashSet<>();

		while (!open.isEmpty()) {
			CostNode<T> checkNode = open.remove();
			if (goal.test(checkNode.getState()))
				return Optional.of(checkNode);
			visited.add(checkNode.getState());
			fw.write(checkNode.getState().toString() + "\n");
			Collection<T> newStates = succFunction.apply(checkNode.getState());

			Collection<StateCostPair<T>> realNewStates = (Collection<StateCostPair<T>>) newStates;

			for (StateCostPair<T> newState : realNewStates) {
				if (visited.contains(newState.getState()))
					continue;

				double newStateCost = checkNode.getCost() + newState.getCost();

				Iterator<CostNode<T>> itr = open.iterator();

				boolean openHasCheaper = false;
				while (itr.hasNext()) {
					CostNode<T> newNode = itr.next();

					if (!newNode.getState().equals(newState.getState()))
						continue;

					if (newStateCost < newNode.getCost()) {
						itr.remove();
					} else {
						openHasCheaper = true;
					}
					break;
				}

				if (!openHasCheaper) {
					CostNode<T> newCostNode = new CostNode<T>(newState.getState(), checkNode, newStateCost);
					open.add(newCostNode);
				}

			}
		}
		return Optional.empty();
	}

}
