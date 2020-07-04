package list;

public class LinkedNode<T> {

	// private instance variable
	private T _element;
	private LinkedNode<T> _next;

	// Getters & Setters
	public T element() {
		return this._element;
	}

	public void setElement(T newElement) {
		this._element = newElement;
	}

	public LinkedNode<T> next() {
		return this._next;
	}

	public void setNext(LinkedNode<T> nexNext) {
		this._next = nexNext;
	}

	// Constructors
	public LinkedNode() {
		this.setElement(null);
		this.setNext(null);
	}

	public LinkedNode(T givenElement, LinkedNode<T> givenNext) {
		this.setElement(givenElement);
		this.setNext(givenNext);
	}

}
