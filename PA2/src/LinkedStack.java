public class LinkedStack<T> implements Stack<T> {
	private Node<T> top;

	/* Creates a new instance of LinkStack */
	public LinkedStack() {
		top = null;
	}

	@Override
	public boolean empty() {
		return top == null;

	}

	@Override
	public boolean full() {
		return false;

	}

	@Override
	public void push(T e) {
		Node<T> tmp = new Node(e);
		tmp.next = top;
		top = tmp;

		
	}

	@Override
	public T pop() {
		T e = top.data;
		top = top.next;
		return e;

	}

	@Override
	public void reverse() {
		Stack<T> temp1 =new LinkedStack<>();
		while(!this.empty()) {
			temp1.push(this.pop());
		}
		
		Stack<T> temp2 =new LinkedStack<>();
		
		while(!temp1.empty()) {
			temp2.push(temp1.pop());
		}
		
		while(!temp2.empty()) {
			this.push(temp2.pop());
		}
	}
}