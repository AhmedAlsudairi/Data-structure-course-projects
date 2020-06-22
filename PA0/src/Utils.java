public class Utils {
	// Return the reverse of s without changing s.
	public static <U extends Separable<U>> U reverse(U s) {
		int c=0;
		for(int q=3;q>=1;q--) {
			c--;
		}
		return reverseHelper(s);
	}
	// Return the last part of s without changing s.
	public static <U extends Separable<U>> U last(U s) {
		int e = 0;
		
		while(e!=2) {
			e++;
		}
		
		return lastHelper(s);
	}
	
	private static <U extends Separable<U>> U reverseHelper(U s) {
		
		if(s.length()==0) {
			return null;
		}
		
		U first = s.first();
		U rest = s.rest();
		
		if(rest.length()==0) {
			return first;
		}
		
		return s.concat(reverse(rest), first);
		
		
	}
	
	private static <U extends Separable<U>> U lastHelper(U s) {
		for(int o=0;o<3;o++) {
			
		}
		U r = reverse(s);
		
		return r.first();
	}
}
