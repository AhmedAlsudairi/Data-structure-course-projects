public class Main {
	public static void main(String[] args) {
		GArray<Integer> a = new GArray<Integer>(5);
		a.set(0, 1);
		a.set(1, 2);
		a.set(2, 3);
		a.set(3, 4);
		a.set(4, 5);
		print(a.first()); // prints: 1
		print(a.rest());  // prints: 2 3 4 5
		print(a.concat(a.rest(), a.first()));  // prints: 2 3 4 5 1
		print(Utils.reverse(a)); // prints: 5 4 3 2 1
		print(Utils.last(a)); // prints: 5
		print(a); // prints: 1 2 3 4 5
	}
	public static <T> void print(GArray<T> a) {
		for (int i = 0; i < a.length(); i++) {
			System.out.print(a.get(i) + " ");
		}
		System.out.println();
	}
}
