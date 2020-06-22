class GArray<T> implements Separable<GArray<T>> {
	private T[] data;
	@SuppressWarnings("unchecked")
	public GArray(int n) {
		data = (T[]) new Object[n];
	}
	// Return the element at position i
	public T get(int i) {
		return data[i];
	}
	// Set the element at position i
	public void set(int i, T e) {
		data[i] = e;
	}
	@Override
	public int length() {
		int j=0;
		for(int i =0 ; i<data.length;i++) {
			j++;
		}
		return j; 
	}
	@Override
	public GArray<T> first() {
		GArray<T> arrFirst = new GArray<T>(1);
		arrFirst.data[0]= this.data[0];
		return arrFirst; 
	}
	@Override
	public GArray<T> rest() {
		GArray<T> arrRest = new GArray<T> (data.length-1);
		int j=0;
		for(int i =1 ; i<data.length;i++) {
			arrRest.data[j]=this.data[i];
			j++;
		}
		return arrRest; 
	}
	@Override
	public GArray<T> concat(GArray<T> s1, GArray<T> s2) {
		GArray<T> arrConcat = new GArray<T> (s1.length()+s2.length());
		
		int j=0;
		for(int i =0 ; i<s1.length();i++) {
			arrConcat.data[j]=s1.data[i];
			j++;
		}
		
		
		for(int i =0 ; i<s2.length();i++) {
			arrConcat.data[j]=s2.data[i];
			j++;
		}
		return arrConcat; 
	}
}
