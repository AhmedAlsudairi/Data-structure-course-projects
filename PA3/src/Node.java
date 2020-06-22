public class Node<T> {
	public T data;
	public Queue<Node<T>> childs;
	public Node () {
		data = null;
		childs=new LinkedQueue<Node<T>>();
	}

	public Node (T val) {
		data = val;
		childs=new LinkedQueue<Node<T>>();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	

}