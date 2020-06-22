public class MovieUtils {
	// Return the movie with the given id if found, null otherwise.
	public static Movie findMovieById(List<Movie> movies, int id) {
		if(movies.empty()) {
			return null;
		}
		
		movies.findFirst();
		
		while(!movies.last()) {
			if(movies.retrieve().id==id)
				return movies.retrieve();
			
			movies.findNext();
		}
		if(movies.retrieve().id==id)
			return movies.retrieve();
		
		return null;
		
	}
	// Return the list of movies having a given title. If none is found, empty list is returned.
	public static List<Movie> findMovieByTitle(List<Movie> movies, String title) {
		List<Movie> moviesTitle=new LinkedList<Movie>();
		if(!movies.empty()) {
		movies.findFirst();
		
		while(!movies.last()) {
			if(movies.retrieve().title.equalsIgnoreCase(title)) {
				moviesTitle.insert(movies.retrieve());
			}
			movies.findNext();
		}
		if(movies.retrieve().title.equalsIgnoreCase(title)) {
			moviesTitle.insert(movies.retrieve());
		}
		}
		return moviesTitle;
		
	}
	// Return the list of movies of a given genre. If none is found, empty list is returned.
	public static List<Movie> findMoviesByGenre(List<Movie> movies, String genre) {
		List<Movie> moviesGenre=new LinkedList<Movie>();
		if(!movies.empty()) {
		Cond<String> gen=new StringEqualCond(genre);
		movies.findFirst();
		
		while(!movies.last()) {
			if(movies.retrieve().genres.findFirstEle(gen)) {
				moviesGenre.insert(movies.retrieve());
			}
			movies.findNext();
		}
		if(movies.retrieve().genres.findFirstEle(gen)) {
			moviesGenre.insert(movies.retrieve());
		}
		}
		return moviesGenre;
	}
	// Return the list of movies of given genres (a movie must have all genres to be in the list). If none is found, empty list is returned. Assume genres is not empty.
	public static List<Movie> findMoviesByGenres(List<Movie> movies, List<String> genres) {
		List<Movie> moviesGenres=new LinkedList<Movie>();
		if(!movies.empty()) {
		movies.findFirst();
		
		boolean genOnot;
		
		while(!movies.last()) {
			genOnot=true;
			genres.findFirst();
			while(!genres.last() && genOnot) {
				movies.retrieve().genres.findFirst();
			if(movies.retrieve().genres.findFirstEle(new StringEqualCond(genres.retrieve()))) {
				genres.findNext();
				
			}else
				genOnot=false;
			}
			if(genOnot) {
				movies.retrieve().genres.findFirst();
				if(movies.retrieve().genres.findFirstEle(new StringEqualCond(genres.retrieve()))) {
					genres.findNext();
					
				}else
					genOnot=false;
			}
			if(genOnot) {
				moviesGenres.insert(movies.retrieve());
			}
			movies.findNext();
		}//last
		genOnot=true;
		genres.findFirst();
		while(!genres.last() && genOnot) {
			movies.retrieve().genres.findFirst();
			if(movies.retrieve().genres.findFirstEle(new StringEqualCond(genres.retrieve()))) {
				genres.findNext();
				
			}else
				genOnot=false;
			}
			if(genOnot) {
				movies.retrieve().genres.findFirst();
				if(movies.retrieve().genres.findFirstEle(new StringEqualCond(genres.retrieve()))) {
					genres.findNext();
					
				}else
					genOnot=false;
			}
			if(genOnot) {
				moviesGenres.insert(movies.retrieve());
			}
			
		}
		
		return moviesGenres;
	}
}
