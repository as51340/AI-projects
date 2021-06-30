package searching.state.football.ai.algorithms.blind;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import searching.state.football.ai.algorithms.ISearchAlgorithm;
import searching.state.football.ai.data.Node;


/**
 * This is the simplest implementation of BFS algorithm. Both time and space complecity are O(b^(d+1)) where b is branching factor and d is depth of 
 * our goal state. This algorithms is complete and optimal because it always finds solutions and such a solution is always minimal -> We can find our goal
 * state in minimal number of steps from starting state.
 * @author askrgat. Preuzeto od @marcupic.
 *
 * @param <T>
 */
public class BFSimpleAlgorithm<T> implements ISearchAlgorithm<T>{

	@Override
	public Optional<Node<T>> search(T startState, Function<T, Collection<T>> succFunction, Predicate<T> goal, FileWriter fw) throws IOException {
		
		Deque<Node<T>> open = new LinkedList<Node<T>>(); 
		open.add(new Node<>(startState, null)); // first node doesn't have parent
		Set<T> visited = new HashSet<T>();
		
		while(!open.isEmpty()) {
			Node<T> checkNode = open.removeFirst();
			visited.add(checkNode.getState());
			fw.write(checkNode.getState().toString() + "\n");
			if(goal.test(checkNode.getState())) return Optional.of(checkNode);
			Collection<T> newStates = succFunction.apply(checkNode.getState());
			for(T newState: newStates) {
				if(visited.contains(newState)) continue;
				open.addLast(new Node<>(newState, checkNode));
			}
		}
		return Optional.empty(); //solution doesn't exist
	}
	

}
