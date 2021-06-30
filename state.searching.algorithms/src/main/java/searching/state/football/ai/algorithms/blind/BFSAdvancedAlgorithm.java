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
 * Here is implementation of advanced BFS algorithm: It is also complete and optimal but we managed to decrease time and space complexity and they are 
 * now O(b^d) both. B is branching factor and d is depth of tree where we out goal state is situated. 
 * @author askrgat. Preuzeto od @marcupic.
 *
 * @param <T>
 */
public class BFSAdvancedAlgorithm<T> implements ISearchAlgorithm<T> {

	@Override
	public Optional<Node<T>> search(T startState, Function<T, Collection<T>> succFunction, Predicate<T> goal, FileWriter fw) throws IOException {
		
		Node<T> startNode = new Node<>(startState, null);
		Deque<Node<T>> open = new LinkedList<Node<T>>();
		
		if(goal.test(startState)) return Optional.of(startNode);
		open.addLast(startNode);
		Set<T> visited = new HashSet<>();
		
		
		while(!open.isEmpty()) {
			Node<T> checkNode = open.removeFirst();
			fw.write(checkNode.getState().toString() + "\n");
			visited.add(checkNode.getState());
			Collection<T> newStates = succFunction.apply(checkNode.getState());
			for(T newState: newStates) {
				if(visited.contains(newState)) continue;
				Node<T> newNode = new Node<>(newState, checkNode);
				if(goal.test(newState)) return Optional.of(newNode);
				open.addLast(newNode);
			}
		}
		return Optional.empty();
	}
	

}
