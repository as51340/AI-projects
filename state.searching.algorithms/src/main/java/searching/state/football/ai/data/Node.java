package searching.state.football.ai.data;

/**
 * Most basic implementation of node in search state algorithms. This kind of implementation allows us to use class Node in many situations considering
 * fact we parametrized Node.
 * @author askrgat
 *
 */
public class Node<T> {
	
	protected T state;
	
	protected Node<T> parent;
	
	/**
	 * Simple constructor
	 * @param state
	 * @param parent
	 */
	public Node(T state, Node<T> parent) {
		this.state = state;
		this.parent = parent;
	}

	/**
	 * @return the state
	 */
	public T getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(T state) {
		this.state = state;
	}

	/**
	 * @return the parent
	 */
	public Node<T> getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Node<T> parent) {
		this.parent = parent;
	}
	
	/**
	 * Complexity is O(n) because every time we are going from this node to the first possible node from which we came here.
	 * This node is always on depth 0. We should try solve with with DP to have O(1) time complexity for retrieving depth. 
	 * @return
	 */
	public int getDepth() {
		int depth = 0; 
		Node<T> current = this.getParent();
		while(current != null) {
			depth++;
			current = current.getParent();
		}
		return depth;
	}
	
	

}
