package searching.state.football.ai.data;

import java.util.Iterator;

/**
 * Razred modelira ure�eni par (stanje, cijena).
 * 
 * @author marcupic
 *
 * @param <S> Parametar koji predstavlja stanje
 */
public class StateCostPair<S> {

	/**
	 * Stanje.
	 */
	private S state;
	
	/**
	 * Cijena prijelaza.
	 */
	private double cost;
	
	/**
	 * Konstruktor.
	 * @param state stanje
	 * @param cost cijena
	 */
	public StateCostPair(S state, double cost) {
		super();
		this.state = state;
		this.cost = cost;
	}
	
	/**
	 * Dohvat stanja.
	 * @return stanje
	 */
	public S getState() {
		return state;
	}
	
	/**
	 * Dohvat cijene.
	 * @return cijena
	 */
	public double getCost() {
		return cost;
	}
	
	@Override
	public String toString() {
		return String.format("(%s,%f)", state, cost);
	}
	
	/**
	 * Pomo�na metoda koja za predano stanje stvara ure�eni
	 * par (stanje, 1).
	 * @param state stanje
	 * @return ure�eni par s jedini�nom cijenom
	 */
	public static <S> StateCostPair<S> unitCost(S state) {
		return new StateCostPair<>(state, 1.0);
	}
	
	/**
	 * Pomo�na metoda koja iterabilni objekt stanja zamata u iterabilni objekt
	 * parova s jedini�nom cijenom prijelaza.
	 * 
	 * @param states iterabilni objekt stanja
	 * @return iterabilni objekt ure�enih parova s jedini�nom cijenom
	 */
	public static <S> Iterable<StateCostPair<S>> unitCostIterable(final Iterable<S> states) {
		return new Iterable<StateCostPair<S>>() {
			@Override
			public Iterator<StateCostPair<S>> iterator() {
				return new Iterator<StateCostPair<S>>() {
					Iterator<S> iter = states.iterator();
					@Override
					public boolean hasNext() {
						return iter.hasNext();
					}
					@Override
					public StateCostPair<S> next() {
						return new StateCostPair<>(iter.next(), 1.0);
					}
				};
			}
		};
	}
}
