package searching.state.football.ai.algorithms.informative;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
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
 * Advanced version of AStarSearch algorithms which compares nodes by total
 * estimates distance regarding goal state. It is complete and optimal under
 * assumption of having consistent heuristics
 * 
 * @author askrgat
 *
 * @param <T>
 */
public class AStarSearchAdvanced<T> implements ISearchAlgorithm<T> {

	private Heuristics<T> heuristics;

	public AStarSearchAdvanced(Heuristics<T> heuristics) {
		this.heuristics = heuristics;
	}

	@Override
	public Optional<Node<T>> search(T startState, Function<T, Collection<T>> succFunction, Predicate<T> goal,
			FileWriter fw) throws IOException {

		Queue<HeuristicNode<T>> open = new PriorityQueue<>(Comparators.COMPARE_BY_TOTAL);
		Map<T, HeuristicNode<T>> openMap = new HashMap<>();
		Map<T, HeuristicNode<T>> closedMap = new HashMap<>();

		HeuristicNode<T> startNode = new HeuristicNode<>(startState, null, 0.0,
				this.heuristics.getEstimatedCost(startState));
		open.add(startNode);
		openMap.put(startState, startNode);

		while (!open.isEmpty()) {

			HeuristicNode<T> node = open.remove();
			openMap.remove(node.getState());

			fw.write(node.getState() + "\n");

			if (goal.test(node.getState()))
				return Optional.of(node);
			closedMap.put(node.getState(), node);

			Collection<T> newStates = succFunction.apply(node.getState());

			Iterable<StateCostPair<T>> realNewStates = StateCostPair.unitCostIterable(newStates);

			for (StateCostPair<T> next : realNewStates) {
				
				HeuristicNode<T> nextNode = new HeuristicNode<>(
						next.getState(), 
						node,
						node.getCost() + next.getCost(), 
						node.getCost() + next.getCost() + heuristics.getEstimatedCost(next.getState()));
				
				// Ako u listi otvorenih imamo cvor za isto stanje ali jeftiniji, vozi dalje...
				HeuristicNode<T> nodeInOpen = openMap.get(nextNode.getState());
				if (nodeInOpen != null && nodeInOpen.getCost() < nextNode.getCost()) {
					continue;
				}

				// Ako u listi zatvorenih imamo cvor za isto stanje ali jeftiniji, vozi dalje...
				HeuristicNode<T> nodeInClosed = closedMap.get(nextNode.getState());
				if (nodeInClosed != null && nodeInClosed.getCost() < nextNode.getCost()) {
					continue;
				}

				if (nodeInOpen != null) {
					openMap.remove(nodeInOpen.getState());
					open.remove(nodeInOpen);
				}
				if (nodeInClosed != null) {
					closedMap.remove(nodeInClosed.getState());
				}

				open.add(nextNode);
				openMap.put(nextNode.getState(), nextNode);
			}
		}

		return Optional.empty();

	}

}
