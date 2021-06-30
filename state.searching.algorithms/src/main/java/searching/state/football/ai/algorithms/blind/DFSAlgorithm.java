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
 * This is the implementation of DFS algorithm. It is not optimal and complete is only in situations when we have acyclic states graph. 
 * We can observe space complexity only when algorithm is complete(read above, neccessary condition is taht we have acyclic states graph) and then it 
 * amount O(b*m) where b is branching factor and m is number of nodes in sequence. Time complexity is always same and it amounts O(b^m)
 * @author askrgat. Preuzeto od @marcupic.
 *
 * @param <T>
 */
public class DFSAlgorithm<T> implements ISearchAlgorithm<T>{

	@Override
	public Optional<Node<T>> search(T startState, Function<T, Collection<T>> succFunction, Predicate<T> goal, FileWriter fw) throws IOException {
		
		Deque<Node<T>> open = new LinkedList<Node<T>>(); 
		open.add(new Node<>(startState, null)); // first node doesn't have parent
		Set<T> visited = new HashSet<>();
		
		while(!open.isEmpty()) {
			Node<T> checkNode = open.removeFirst();
			visited.add(checkNode.getState());
			fw.write(checkNode.getState().toString() + "\n");
			if(goal.test(checkNode.getState())) return Optional.of(checkNode);
			Collection<T> newStates = succFunction.apply(checkNode.getState());
			for(T newState: newStates) {
				if(visited.contains(newState)) continue;
				open.addFirst(new Node<>(newState, checkNode));
			}
		}
		return Optional.empty(); //solution doesn't exist
	}

}
