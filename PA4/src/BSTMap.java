public class BSTMap<K extends Comparable<K>, T> implements Map<K, T> {
	public BSTNode<K, T> root; // Do not change this
	private int numOfNodes;
	public BSTMap() {
		root =null;
		numOfNodes=0;
	}

	@Override
	public int size() {
		
	

		return numOfNodes;
	}

	@Override
	public boolean full() {
		

		return false;
	}

	@Override
	public void clear() {
		

		root=null;
		numOfNodes=0;
		
	}

	@Override
	public boolean update(K k, T e) {
		BSTNode<K, T> tmp=root;
		
	

		
		return helperUpdate(k, e, tmp);
	}
	
	private boolean helperUpdate(K k, T e,BSTNode<K, T> tmp) {
		
		if(tmp==null) {
			return false;
		}
		
		
			if(k.compareTo(tmp.key)==0) {
				tmp.key=k;
				tmp.data=e;
				return true;
			}
			else if(k.compareTo(tmp.key)>0) {
				return helperUpdate(k, e, tmp.right);
			}else if(k.compareTo(tmp.key)<0){
				return helperUpdate(k, e, tmp.left);
			}
		
		
		return false;
	}

	@Override
	public Pair<Boolean, T> retrieve(K k) {
		Pair<Boolean, T> res=new Pair<Boolean, T>(false,null);
		
		BSTNode<K, T> tmp=root;
		
		

		return helperRetrieve(k, tmp, res);
	}
	
	private Pair<Boolean, T> helperRetrieve(K k,BSTNode<K, T> tmp,Pair<Boolean, T> res) {
		
		if(tmp==null) {
			return res;
		}
		
		
		if(k.compareTo(tmp.key)==0) {
			res=new Pair<Boolean, T>(true, tmp.data);
			return res;
		}
		else if(k.compareTo(tmp.key)>0) {
			return helperRetrieve(k,tmp.right,res);
		}else if(k.compareTo(tmp.key)<0){
			return helperRetrieve(k,tmp.left,res);
		}
		
		
		return res;
	}

	@Override
	public boolean insert(K k, T e) {
		try {
		BSTNode<K, T> newElement=new BSTNode<K, T>(k,e);
		
	

		if(root==null) {
			root=newElement;
			numOfNodes++;
			return true;
		}
		
		BSTNode<K, T> tmp=root;
		BSTNode<K, T> tmpBack=null;
		
		while(tmp!=null) {
			if(k.compareTo(tmp.key)==0) {
				return false;
			}
			else if(k.compareTo(tmp.key)>0) {
				tmpBack=tmp;
				tmp=tmp.right;
			}else if(k.compareTo(tmp.key)<0){
				tmpBack=tmp;
				tmp=tmp.left;
			}
		}
		
		if(k.compareTo(tmpBack.key)>0) {
			tmpBack.right=newElement;
			numOfNodes++;
			return true;
		}else if(k.compareTo(tmpBack.key)<0) {
			tmpBack.left=newElement;
			numOfNodes++;
			return true;
		}
		
		
		return false;
		}catch (Exception z) {
			return false;
		}
	}

	@Override
	public boolean remove(K k) {
		try {
		if(root==null) {
			
			return false;
		}
	

		BSTNode<K, T> tmp=root;
		BSTNode<K, T> tmpBack=null;
		
		while(tmp!=null) {
			if(k.compareTo(tmp.key)==0) {
				//we find it
				
				//case 1
				if(tmp.left==null&&tmp.right==null) {
					if(tmp.key.compareTo(tmpBack.key)>0) {
						tmpBack.right=null;
					}
					if(tmp.key.compareTo(tmpBack.key)<0) {
						tmpBack.left=null;
					}
					
				}else  //case 2 part 1
					if(tmp.left==null&&tmp.right!=null) {
						BSTNode<K, T> RightChild=tmp.right;
						
						if(tmpBack.left.equals(tmp)) {
							tmpBack.left=RightChild;
						}
						if(tmpBack.right.equals(tmp)) {
							tmpBack.right=RightChild;
							}
						
				}else  //case 2 part 2
					if(tmp.left!=null&&tmp.right==null) {
						BSTNode<K, T> LeftChild=tmp.left;
						
						
						if(tmpBack.left.equals(tmp)) {
							tmpBack.left=LeftChild;
						}
						if(tmpBack.right.equals(tmp)) {
							tmpBack.right=LeftChild;
							}
				}else
				//case 3 
					{
					tmpBack=tmp;
					BSTNode<K, T> minimum = tmp.right;
					
					while(minimum.left!=null) {
						tmpBack=minimum;
						minimum=minimum.left;
					}
					this.update(tmp.key, minimum.data);
					tmp.key=minimum.key;
					tmp=minimum;
					K newKey=minimum.key;
					
					if(tmp.left!=null) {
						tmp=tmp.left;
					}else {
						tmp=tmp.right;
					}
					
					if(tmpBack==null) {
						root=tmp;
					}else {
						if(newKey.compareTo(tmpBack.key)<0) {
							tmpBack.left=tmp;
						}else {
							tmpBack.right=tmp;
						}
					}
					
					
					

					
					
				}
				
				numOfNodes--;
				return true;
			}
			else if(k.compareTo(tmp.key)>0) {
				tmpBack=tmp;
				tmp=tmp.right;
			}else if(k.compareTo(tmp.key)<0){
				tmpBack=tmp;
				tmp=tmp.left;
			}
		}
		return false;
		}catch (Exception e) {
			return false;
		}
	}


	@Override
	public List<K> getKeys() {
		List<K> resKeys = new LinkedList<>();
		
		

		return helperGetKeys(root,resKeys);
	}
	
	private List<K> helperGetKeys(BSTNode<K, T> tmp,List<K> l) {
		
		
		
		if(tmp==null) {
			return l;
		}
		
		helperGetKeys(tmp.left,l);
		
		l.insert(tmp.key);
		
		helperGetKeys(tmp.right, l);
		
		return l;
	}
	
}
