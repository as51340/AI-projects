package searching.state.football.ai.heuristics;

/**
 * Su�elje predstavlja model proizvoljne
 * heuristike.
 * 
 * @param <S> Tip stanja
 * 
 * @author marcupic
 */
public interface Heuristics<S> {
	/**
	 * Metoda vra�a procjenu tro�ka prelaska
	 * iz zadanog stanja do ciljnog stanja. Kako
	 * se do�lo do trenutnog stanja nije bitno
	 * i nema nikakve uloge u vra�enoj vrijednosti.
	 * 
	 * @param state stanje iz kojeg se kre�e
	 * @return procjena tro�ka
	 */
	double getEstimatedCost(S state);
}
