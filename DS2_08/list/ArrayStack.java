package list;

public class ArrayStack<E> implements Stack<E> {

	private static final int DEFAULT_CAPACITY = 100;
	private int _capacity;
	private int _top;
	private E[] _elements;

	private int capacity() {
		return this._capacity;
	}

	private void setCapacity(int newCapacity) {
		this._capacity = newCapacity;
	}

	private int top() {
		return this._top;
	}

	private void setTop(int newTop) {
		this._top = newTop;
	}

	protected E[] elements() {
		return this._elements;
	}

	private void setElements(E[] newElements) {
		this._elements = newElements;
	}

	public ArrayStack() {
		this(ArrayStack.DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayStack(int giveCapacity) {
		this.setCapacity(giveCapacity);
		this.setElements((E[]) new Object[this.capacity()]);
		this.setTop(-1);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.setTop(-1);

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return (this.top() + 1);

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.top() == -1;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return this.size() == ArrayStack.DEFAULT_CAPACITY;

	}

	@Override
	public boolean push(E anElement) {
		// TODO Auto-generated method stub
		if (!isFull()) {
			this.setTop(this.size());
			this.elements()[this.top()] = anElement;
			return true;
		}
		return false;

	}

	@Override
	public E pop() {
		if (!this.isEmpty()) {
			E tmp = this.elements()[this.top()];
			this.elements()[this.top()] = null;
			this.setTop(this.top() - 1);
			return tmp;
		}
		return null;
	}

	@Override
	public E peek() {
		if (!this.isEmpty()) {
			E peekElement = this.elements()[this.top()];
			return peekElement;
		}
		return null;
	}
}
