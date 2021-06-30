package searching.state.football.ai.data;

/**
 * Structure CostNode for implementing uniformCostSearchAlgoritm
 * @author askrgat. Preuzeto od @marcupic.
 *
 * @param <T>
 */
public class CostNode<T> extends Node<T> implements Comparable<CostNode<T>> {
	
	protected double cost;

	public CostNode(T state, CostNode<T> parent, double cost) {
		super(state, parent);
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}

	@Override
	public CostNode<T> getParent() {
		return (CostNode<T>) super.getParent();
	}

	@Override
	public int compareTo(CostNode<T> other) {
		return Double.compare(this.cost, other.cost);
	}

	@Override
	public String toString() {
		return String.format("(%s,%.1f)", state, cost);
	}
}

