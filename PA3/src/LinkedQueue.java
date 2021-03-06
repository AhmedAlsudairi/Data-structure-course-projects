
public class LinkedQueue<T> implements Queue<T>{
	private qNode<T> head, tail;
	private int size;
	
	
	public LinkedQueue() {
		head = tail = null;
		size = 0;
	}
	@Override
	public int length() {
		return size;

	}

	@Override
	public boolean full() {
		
		return false;
	}

	@Override
	public void enqueue(T e) {
		if(tail == null){
			head = tail = new qNode<T>(e);
		}
		else {
			tail.next = new qNode<T>(e);
			tail = tail.next;
		}
		size++;

		
	}

	@Override
	public T serve() {
		T x = head.data;
		head = head.next;
		size--;
		if(size == 0)
			tail = null;
		return x;

	}

}
