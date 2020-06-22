public class qNode<T> {
	public T data;
	public qNode<T> next;

	public qNode () {
		data = null;
		next = null;
	}

	public qNode (T val) {
		data = val;
		next = null;
	}

	// Setters/Getters...
}
