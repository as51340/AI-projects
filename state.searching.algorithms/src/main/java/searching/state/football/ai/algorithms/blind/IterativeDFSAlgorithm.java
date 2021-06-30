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
 * Iterative DFS algorithm allows us to have algorithms that is complete and optimal with satisfactory space complexity of O(b*d) and time complexity 
 * amounts O(b^d)
 * @author askrgat. Preuzeto od @marcupic.
 * @param <T>
 */
public class IterativeDFSAlgorithm<T> implements ISearchAlgorithm<T>{

	@Override
	public Optional<Node<T>> search(T startState, Function<T, Collection<T>> succFunction, Predicate<T> goal, FileWriter fw) throws IOException {
		
		for(int i = 0; ; i++) {
			Optional<Node<T>> result = DLSAlgorithm(startState, succFunction, goal, i, fw);
			if(result != null) return result; //because if we return empty we know we couldn't find anything but if it is null then maybe there is state
			//with larger depth
		}
	}
	
	/**
	 * Checks if algorithm can generate new population for state T so it stays belowe limit i.
	 * @param startState
	 * @param succFunction
	 * @param goal
	 * @param i
	 * @return
	 * @throws IOException 
	 */
	private Optional<Node<T>> DLSAlgorithm(T startState, Function<T, Collection<T>> succFunction, Predicate<T> goal, int i, FileWriter fw) throws IOException {
		Deque<Node<T>> open = new LinkedList<Node<T>>();
		Node<T> startNode = new Node<>(startState, null);
		open.add(startNode);
		boolean cutoffOccured = false;
		Set<T> visited = new HashSet<>();
		
		
		while(!open.isEmpty()) {
			Node<T> checkNode = open.removeFirst();
			fw.write(checkNode.getState().toString() + "\n");
			visited.add(checkNode.getState());
			if(goal.test(checkNode.getState())) return Optional.of(checkNode);
			if(checkNode.getDepth() < i) {
				Collection<T> newStates = succFunction.apply(checkNode.getState());
				for(T newState: newStates) {
					if(visited.contains(newState)) continue;
					open.addFirst(new Node<>(newState, checkNode));
				}
			} else {
				cutoffOccured = true;
			}
		}
		return cutoffOccured ? null : Optional.empty();
	}
	

}
