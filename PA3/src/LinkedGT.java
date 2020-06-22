
public class LinkedGT<T> implements GT<T> {

	Node<T> Generator;
	Node<T> current;
	
	public LinkedGT() {
		 Generator=current=null;
	}
	
	@Override
	public boolean empty() {
		
		return Generator==null;
	}

	@Override
	public boolean full() {
		
		return false;
	}

	@Override
	public T retrieve() {
		
		return current.data;
	}

	@Override
	public void update(T e) {
		current.data=e;
		
	}

	@Override
	public boolean insert(T e) {
		Node<T> NewElement=	new Node<T>(e);

		if(this.empty()) {
			current=Generator=NewElement;
			return true;
		}
			current.childs.enqueue(NewElement);
			current=NewElement;
			return true;
			
		
	}

	@Override
	public int nbChildren() {
		if(current==null) {  
			//alter
			return -1;
			//
			}else {
				
		int k = current.childs.length();
		
		return k;
		}
	}

	@Override
	public boolean findChild(int i) {
		if(current==null) {
			return false;
		}else {
			boolean isThereChild=false;
			
			Queue<Node<T>> qChilds=current.childs;
			int k=current.childs.length();
			//alter
			if(i>=k) {
				return false;
			}
			//
			for(int j=0;j<k;j++) {
				Node<T> findChild=qChilds.serve();
				qChilds.enqueue(findChild);
				if(j==i) {
					current=findChild;
					isThereChild=true;
				}
				
			}
			return isThereChild;
		}
		
		
	}

	
	@Override
	public boolean findParent() {
		if(this.empty()) {
			return false;
		}else if(current==Generator) {
			return false;
		}
		
		
		return findParent(Generator);
		
	}
	
	private boolean findParent(Node<T> q) {
		if(q==null) {
			return false;
		}
		
		Queue<Node<T>> qChilds=q.childs;
		int k= qChilds.length();
		boolean isThere=false;
		for(int i=0;i<k;i++) {
			Node<T> chlildOfQ=qChilds.serve();
			qChilds.enqueue(chlildOfQ);
			if(chlildOfQ==current) {
				current=q;
				isThere=true;
			}
		}
			if(isThere==true) {
				return true;
			}
			
			for(int j=0;j<k;j++) {
				Node<T> chlildOfQ=qChilds.serve();
				qChilds.enqueue(chlildOfQ);
				findParent(chlildOfQ);
			}
			//alter
			return isThere;
			//
		}
		
	@Override
	public void findRoot() {
		current=Generator;
		
	}

	@Override
	public void remove() {
		if(current==Generator) {
			current=Generator=null;
			return;
		}
		
		Node<T> temp=current;
		findParent();
		Queue<Node<T>> qChilds=current.childs;
		int k=qChilds.length();
		for(int i=0;i<k;i++) {
			Node<T> x=qChilds.serve();
			
			if(!x.equals(temp)) {
				qChilds.enqueue(x);
			}
		}
		
	}
	

}
