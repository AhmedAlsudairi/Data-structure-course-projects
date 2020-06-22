// Separable structure
public interface Separable<U> {
	// Return the length.
	int length();
	// Return the first element.
	U first();
	// Return all elements except the the first one.
	U rest();
	// Concatenate the two parameters. The current object is not changed.
	U concat(U s1, U s2);
}
