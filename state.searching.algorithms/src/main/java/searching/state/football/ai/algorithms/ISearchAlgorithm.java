package searching.state.football.ai.algorithms;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import searching.state.football.ai.data.Node;

/**
 * Specifies search function for different implementations of searching state algorithms
 * @author askrgat
 *
 * @param <T>
 */
public interface ISearchAlgorithm<T> {

	
	/**
	 * Search function
	 * @param startState
	 * @param succFunction
	 * @param goal
	 * @return
	 */
	Optional<Node<T>> search(T startState, Function<T, Collection<T>> succFunction, Predicate<T> goal, FileWriter fw) throws IOException;	
	
}
