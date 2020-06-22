public class MGraph<K extends Comparable<K>> implements Graph<K> {
	public Map<K, List<K>> adj; // Do not change this
	public MGraph() {
		adj = new BSTMap<K,List<K>>();
	}
	@Override
	public boolean addNode(K i) {
		try {
			
		

		List<K> listOfKeys=new LinkedList<K>();
		//BSTNode<K,List<K>> newNode = new BSTNode<K,List<K>>(i,listOfKeys);
		
		
		return adj.insert(i, listOfKeys);
		}catch (Exception e) {
			return false;
		}
	}
	@Override
	public boolean isNode(K i) {
		try {
			


		List<K> listOfKeys=adj.getKeys();
		
		
		return listOfKeys.exists(i);
		}catch (Exception e) {
			return false;
		}
	}
	@Override
	public boolean addEdge(K i, K j) {
		try {
			

		List<K> listOfKeys=adj.getKeys();
		
		if(!listOfKeys.exists(i)||!listOfKeys.exists(j)) {
			return false;
		}
		
		Pair<Boolean, List<K>> listI=adj.retrieve(i);
		Pair<Boolean, List<K>> listJ=adj.retrieve(j);
		
		if(listI.second.exists(j)||listJ.second.exists(i)) {
			return false;
		}
		
		listI.second.insert(j);
		listJ.second.insert(i);
		
		
		return true;
		}catch (Exception e) {
			return false;
		}
	}
	@Override
	public boolean isEdge(K i, K j) {
		try {
			

		List<K> listOfKeys=adj.getKeys();
		
		if(!listOfKeys.exists(i)||!listOfKeys.exists(j)) {
			return false;
		}
		
		Pair<Boolean, List<K>> listI=adj.retrieve(i);
		Pair<Boolean, List<K>> listJ=adj.retrieve(j);
		
		if(listI.second.exists(j)&&listJ.second.exists(i)) {
			return true;
		}
		
		return false;
		}catch (Exception e) {
			return false;
		}
	}
	@Override
	public List<K> neighb(K i) {
		try {
			


		List<K> listOfKeys=adj.getKeys();
		
		if(!listOfKeys.exists(i)) {
			return null;
		}
		
		Pair<Boolean, List<K>> listI=adj.retrieve(i);
		
		return listI.second;
		}catch (Exception e) {
			return null;
		}
	}
	@Override
	public int deg(K i) {
		try {
			

		List<K> listOfKeys=adj.getKeys();
		
		if(!listOfKeys.exists(i)) {
			return -1;
		}
		
		Pair<Boolean, List<K>> listI=adj.retrieve(i);
		
		return listI.second.size();
		}catch (Exception e) {
			return -1;
		}
	}
	@Override
	public List<K> getNodes() {
		try {
			

		List<K> listOfKeys=adj.getKeys();
		return listOfKeys;
		}catch (Exception e) {
			return null;
		}
	}
	
}
