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
 * If heuristics is optimistic and we have acyclic states graph then algorithm
 * is complete and optimal. If not we can construct advanced version of this
 * algorithm which uses list of visited states and with the assumption of having
 * consistent heuristic we will have again complete and optimal algorithm. Time and space complexity O(b*m)
 * 
 * @author askrgat
 *
 * @param <T>
 */
public class AStarSearchBasic<T> implements ISearchAlgorithm<T> {

	private Heuristics<T> heuristics;

	public AStarSearchBasic(Heuristics<T> heuristics) {
		this.heuristics = heuristics;
	}

	
	@Override
	public Optional<Node<T>> search(T startState, Function<T, Collection<T>> succFunction, Predicate<T> goal, FileWriter fw) throws IOException {

		Queue<HeuristicNode<T>> open = new PriorityQueue<HeuristicNode<T>>(Comparators.COMPARE_BY_TOTAL);
		HeuristicNode<T> startNode = new HeuristicNode<>(startState, null, 0.0,
				this.heuristics.getEstimatedCost(startState));
		open.add(startNode);

		while (!open.isEmpty()) {

			HeuristicNode<T> checkNode = open.remove();
			fw.write(checkNode.getState() + "\n");

			if (goal.test(checkNode.getState()))
				return Optional.of(checkNode);

			Collection<T> newStates = succFunction.apply(startState);

			Iterable<StateCostPair<T>> realNewStates = StateCostPair.unitCostIterable(newStates);

			for (StateCostPair<T> newState : realNewStates) {
				double cost = checkNode.getCost() + newState.getCost();
				double total = cost + heuristics.getEstimatedCost(newState.getState());

				open.add(new HeuristicNode<>(newState.getState(), checkNode, cost, total));

			}

		}
		
		return Optional.empty();

	}

}
