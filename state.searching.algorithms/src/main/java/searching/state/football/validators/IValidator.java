package searching.state.football.validators;

/**
 * Specifies validate method
 * @author Andi Škrgat
 *
 */
public interface IValidator<T> {
	
	boolean validate(T state);

}
