
public class LinkedList<T> implements List<T> {

	private Node<T> head;
	private Node<T> current;

	@Override
	public boolean empty() {
		return head == null;

	}

	@Override
	public boolean full() {
		
		return false;
	}

	@Override
	public void findFirst() {
		current = head;
		
	}

	@Override
	public void findNext() {
		try {
		current = current.next;
		}catch (Exception e) {
			return;
		}
	}

	@Override
	public boolean last() {
		try {
		return current.next == null;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public T retrieve() {
		try {
		return current.data;
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public void update(T e) {
		try {
		current.data = e;
		}catch (Exception t) {
		return ;
	}
	}

	@Override
	public void insert(T e) {
		try {
		Node<T> tmp;
		if (empty()) {
			current = head = new Node<T> (e);
		}
		else {
			tmp = current.next;
			current.next = new Node<T> (e);
			current = current.next;
			current.next = tmp;
		}

		}catch (Exception t) {
			return ;
		}
	}

	@Override
	public void remove() {
		try {
		if (current == head) {
			head = head.next;
		}
		else {
			Node<T> tmp = head;

			while (tmp.next != current)
				tmp = tmp.next;

			tmp.next = current.next;
		}

		if (current.next == null)
			current = head;
		else
			current = current.next;

		}catch (Exception e) {
			return ;
		}
	}

	@Override
	public int size() {
	

		return helperSize(head);
	}
	private int helperSize(Node<T> tmp) {
		if(tmp==null) {
			return 0;
		}
		return  1+helperSize(tmp.next);	
	}
	
	@Override
	public boolean exists(T e) {
	

		return helperExists(e, head);
	}
	private boolean helperExists(T e,Node<T> tmp) {
		if(tmp==null) {
			return false;
		}
		
		if(tmp.data.equals(e)) {
			return true; 
		}
		
		return  helperExists(e, tmp.next);
		
	}
}
