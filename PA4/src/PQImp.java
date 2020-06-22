public class PQImp<P extends Comparable<P>, T> implements PQK<P, T> {
	private int size;
	private int MaxSize;
	private PQNode<P,T> head,tail;

	public PQImp(int k) {
		head = null;
		size = 0;
		tail=null;
		MaxSize=k;
	}

	@Override
	public int length() {
		
		

		return size;
	}

	@Override
	public void enqueue(P pr, T e) {
		try {
			
			

		PQNode<P,T> tmp = new PQNode<P,T>(e, pr);
		//case 1 
		if(size<MaxSize) {
		if((size == 0) || ((pr.compareTo(head.priority)>0))) {
			tmp.next = head;
			head = tmp;
			if(size==0) {
				tail=head;
			}
		}else if(pr.compareTo(tail.priority)<=0) {
			tail.next=tmp;
			tail=tail.next;
		}
		else {
			PQNode<P,T> p = head;
			PQNode<P,T> q = null;
			while((p != null) && ((pr.compareTo(p.priority)<0||(pr.compareTo(p.priority)==0)))) {
				q = p;
				p = p.next;
			}
			tmp.next = p;
			q.next = tmp;
			
		}
		size++;
		}
		//case 2 (check the remove of the last element)
		if(size==MaxSize&&MaxSize!=0) {
			if(pr.compareTo(tail.priority)>0) {
				if((size == 0) || ((pr.compareTo(head.priority)>0))) {
					tmp.next = head;
					head = tmp;
					if(size==0) {
						tail=head;
					}
				}else if(pr.compareTo(tail.priority)<=0) {
					tail.next=tmp;
					tail=tail.next;
				}
				else {
					PQNode<P,T> p = head;
					PQNode<P,T> q = null;
					while((p != null) && ((pr.compareTo(p.priority)<0||(pr.compareTo(p.priority)==0)))) {
						q = p;
						p = p.next;
					}
					tmp.next = p;
					q.next = tmp;
					
				}
			}else {
				return;
			}
		}
		}catch (Exception x) {
			return;
		}
	}

	@Override
	public Pair<P, T> serve() {
		try {
		
			

		if(head==null) {
			return null;
		}
		
		PQNode<P,T> node = head;
		Pair<P, T> pqe=new Pair<P, T>(node.priority,node.data);
		node=node.next;
		head = head.next;
		size--;
		
		if(head==null) {
			tail=null;
		}
		return pqe;
		}catch (Exception e) {
			return null;
		}
	}
	
}
