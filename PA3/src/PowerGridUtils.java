public class PowerGridUtils {

	// Return the IDs of all elements in the power grid pg in pre-order.
	public static Queue<Integer> collectPreorder(GT<PGElem> pg){
		
		pg.findRoot();
		Queue<Integer> qIDs=new LinkedQueue<Integer>();
		
		if(pg.empty()) {
			return qIDs;
		}
		
		
		return helperCollectPreorder(pg, pg.retrieve(), qIDs);
		
	}
	
	private static Queue<Integer> helperCollectPreorder(GT<PGElem> pg,PGElem temp,Queue<Integer> qIDs){
		
		
		if(temp==null) {
			return qIDs;
		}
		
		if(pg.empty()) {
			return qIDs;
		}
		
		
		qIDs.enqueue(temp.getId());
		
		if(pg.nbChildren()==0) {
			return qIDs;
		}
		
		for(int i=0;i<pg.nbChildren();i++) {
			pg.findChild(i);
			helperCollectPreorder( pg, pg.retrieve(),qIDs);
			pg.findParent();
		}
		
		return qIDs;
	}
	// Searches the power grid pg for the element with ID id. If found, it is made current and true is returned, otherwise false is returned.
	public static boolean find(GT<PGElem> pg, int id) {
		
		if(pg==null) {
			return false;
		}
		
		if(pg.empty()) {
			return false;
		}
		
		int curID=pg.retrieve().getId();
		
		Queue<Integer> IDs=collectPreorder(pg);
		
		pg.findRoot();
		int size=IDs.length();
		
		for(int i=0;i<size;i++) {
			
			int temp=IDs.serve();
			
			if(temp==id) {
				helperFind(pg, temp,pg.retrieve());
				return true;
			}
		}
		
		helperFind(pg, curID,pg.retrieve());
		
		return false;
		
	}

	private static boolean helperFind(GT<PGElem> pg, int id,PGElem temp) {
	if(temp.getId()==id) {
		return true;
	}
	for(int i=0;i<pg.nbChildren();i++) {
		pg.findChild(i);
		if(helperFind(pg, id, pg.retrieve())) {
			//return=true
			return helperFind(pg, id, pg.retrieve());
		}
		pg.findParent();
	}
		return false;
	}
	// Add the generator element gen to the power grid pg. This can only be done if the grid is empty. If successful, the method returns true. If there is already a generator, or gen is not of type Generator, false is returned.
	public static boolean addGenerator(GT<PGElem> pg, PGElem gen) {
		if(pg.empty()&&gen.getType()==ElemType.Generator) {
			pg.insert(gen);
			return true;
		}else return false;
		
		
	}

	// Attaches pgn to the element id and returns true if successful. Note that a consumer can only be attached to a transmitter, and no element can be be attached to it. The tree must not contain more than one generator located at the root. If id does not exist, or there is already aelement with the same ID as pgn, pgn is not attached, and the method retrurns false.
	public static boolean attach(GT<PGElem> pg, PGElem pgn, int id) {
		if(pgn.getType()==ElemType.Generator) {
			if(pg.empty()) {
				pg.insert(pgn);
				return true;
				
			}
			else{
				return false;
			}
				
		}
		
		if(!find(pg, id)) {
			return find(pg, id);
		}
		
		if(find(pg, pgn.getId())) {
			return !find(pg, pgn.getId());
		}
		if(pgn.getType()==ElemType.Transmitter) {
			if(!(pg.retrieve().getType()==ElemType.Consumer)) {
			pg.insert(pgn);
			return true;
			}else {
				return false; 
			}
		}
		
		if(pgn.getType()==ElemType.Consumer) {
			if(pg.retrieve().getType()==ElemType.Transmitter) {
				pg.insert(pgn);
				return true;
			}else {
				return false;
			}
		}
		
		return false;
	}

	// Removes element by ID, all corresponding subtree is removed. If removed, true is returned, false is returned otherwise.
	public static boolean remove(GT<PGElem> pg, int id) {
		if(pg.empty()) {
			return false;
		}
		if(pg==null) {
			return false;
		}
		pg.findRoot();
		return helperRemove(pg, id, pg.retrieve());
	}
	
	private static boolean helperRemove(GT<PGElem> pg, int id,PGElem temp) {
		if(pg.empty()) {
			return false;
		}
		if(temp==null) {
			return false;
		}
		
		if(temp.getId()==id) {
			pg.remove();
			return true;
		}
		
		for(int i=0;i<pg.nbChildren();i++) {
			pg.findChild(i);
			//if...break
			boolean flagTree=helperRemove( pg, id,pg.retrieve());
			if(flagTree) {
				//return=true
				return flagTree;
			}
			pg.findParent();
		}
		
		return false;
	}
	// Computes total power that consumed by a element (and all its subtree). If id is incorrect, -1 is returned.
	public static double totalPower(GT<PGElem> pg, int id) {
		
		if(pg.empty()) {
			//check
			return 0;
		}
		if(pg==null) {
			
			return 0;
		}
		
		pg.findRoot();
		
		if(find(pg, id)) {
		return helperTotalPower(pg,id,pg.retrieve());
		}
		else 
			return -1;
	}
	private static double helperTotalPower(GT<PGElem> pg, int id,PGElem temp) {
		double x=0;
		if(temp==null) {
			return x;
		}
		
		if(pg.nbChildren()==0&&pg.retrieve().getType()==ElemType.Consumer) {
			x+= pg.retrieve().getPower();
		}
		
		for(int i=0;i<pg.nbChildren();i++) {
			pg.findChild(i);
			x+=helperTotalPower( pg, id,pg.retrieve());
			pg.findParent();
		}
		
		return x;
	}
	// Checks if the power grid contains an overload. The method returns the ID of the first element preorder that has an overload and -1 if there is no overload.
	public static int findOverload(GT<PGElem> pg) {
		if(pg.empty()) {
			//check
			return 0;
		}
		if(pg==null) {
			
			return 0;
		}
		
		pg.findRoot();
		
		return helperFindOverload(pg, pg.retrieve());
		
	}
	
	private static int helperFindOverload(GT<PGElem> pg,PGElem temp) {
		if(temp==null) {
			return -1;
		}
		
		if(pg.nbChildren()==0&&pg.retrieve().getType()==ElemType.Consumer) {
			return -1;
		}
		int curID=pg.retrieve().getId();
		if(temp.getPower()<totalPower(pg,temp.getId() )) {
			return temp.getId();
		}
		
		find(pg, curID);
		
		for(int i=0;i<pg.nbChildren();i++) {
			pg.findChild(i);
			int x= helperFindOverload( pg,pg.retrieve());
			
			if(x!=-1) {
				return x;
			}
			pg.findParent();
		}
		
		return -1;
	}
}
