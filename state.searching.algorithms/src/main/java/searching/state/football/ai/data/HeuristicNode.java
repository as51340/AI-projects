package searching.state.football.ai.data;

/**
 * Heuristic node
 * 
 * @author marcupic
 */
public class HeuristicNode<T> extends CostNode<T> {

	private double totalEstimatedCost;

	public HeuristicNode(T state, CostNode<T> parent, double cost, double totalEstimatedCost) {
		super(state, parent, cost);
		this.totalEstimatedCost = totalEstimatedCost;
	}

	public double getTotalEstimatedCost() {
		return totalEstimatedCost;
	}

	@Override
	public HeuristicNode<T> getParent() {
		return (HeuristicNode<T>) super.getParent();
	}

	@Override
	public String toString() {
		return String.format("(%s,%.1f,%.1f)", state, cost, totalEstimatedCost);
	}
	
}
