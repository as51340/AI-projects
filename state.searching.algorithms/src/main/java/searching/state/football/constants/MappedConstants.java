package searching.state.football.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import searching.state.football.ai.data.Pair;

public class MappedConstants {

	private Map<String, List<Pair<Integer>>> mapped_positions_442 = new HashMap<>();
	
	public MappedConstants() {
		construct_442_map();
	}
	
	private void construct_442_map() {
		List<Pair<Integer>> list_rb = new ArrayList<Pair<Integer>>();
		list_rb.add(new Pair<Integer>(0, 0));
		mapped_positions_442.put("RB", list_rb);
		List<Pair<Integer>> list_cb = new ArrayList<Pair<Integer>>();
		list_cb.add(new Pair<Integer>(0, 1));
		list_cb.add(new Pair<Integer>(0, 2));
		mapped_positions_442.put("CB", list_cb);
		List<Pair<Integer>> list_lb = new ArrayList<Pair<Integer>>();
		list_lb.add(new Pair<Integer>(0, 3));
		mapped_positions_442.put("LB", list_lb);
		List<Pair<Integer>> list_rm = new ArrayList<Pair<Integer>>();
		list_rm.add(new Pair<Integer>(1, 0));
		mapped_positions_442.put("RM", list_rm);
		List<Pair<Integer>> list_cm = new ArrayList<Pair<Integer>>();
		list_cm.add(new Pair<Integer>(1, 1));
		list_cm.add(new Pair<Integer>(1, 2));
		mapped_positions_442.put("CM", list_cm);
		List<Pair<Integer>> list_lm = new ArrayList<Pair<Integer>>();
		list_lm.add(new Pair<Integer>(1, 3));
		mapped_positions_442.put("LM", list_lm);
		List<Pair<Integer>> list_st = new ArrayList<Pair<Integer>>();
		list_st.add(new Pair<Integer>(2, 1));
		list_st.add(new Pair<Integer>(2, 2));
		mapped_positions_442.put("ST", list_st);
	}

	
	public Map<String, List<Pair<Integer>>> getMapped_positions_442() {
		return mapped_positions_442;
	}
	
	

	public void setMapped_positions_442(Map<String, List<Pair<Integer>>> mapped_positions_442) {
		this.mapped_positions_442 = mapped_positions_442;
	}

	
}
