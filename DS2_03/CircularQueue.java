
public class CircularQueue<T> implements Queue<T> {
	// interface Queue<T> ����

	// Private Variables
	private T[] _elements;
	private int _capacity;
	// ���Ҹ� �����ϴ� �迭 _elements[]�� ũ��, ���� ���� ������ ������ ������ (_capacity-1)
	private int _front;
	private int _rear;

	// Getters&Setters
	private T[] elements() {
		return this._elements;

	}

	private void setElements(T[] newElements) {
		this._elements = newElements;
	}

	private int capacity() {
		return this._capacity;
	}

	private void setCapacity(int newCapacity) {
		this._capacity = newCapacity;
	}

	private int front() {
		return this._front;
	}

	private void setFront(int newFront) {
		this._front = newFront;

	}

	private int rear() {
		return this._rear;
	}

	private void setRear(int newRear) {
		this._rear = newRear;
	}

	// Private methods
	private int nextRear() {
		return (this.rear() + 1) % this.capacity();
	}

	private int nextFront() {
		return (this.front() + 1) % this.capacity();
	}

	// Constructor
	public CircularQueue(int maxNumberOfElements) {
		this.setCapacity(maxNumberOfElements + 1);
		this.setElements((T[]) new Object[this.capacity()]);
		this.reset();
	}

	// Public Methods
	public void reset() {
		this.setFront(0);
		this.setRear(0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		if (this.front() <= this.rear()) {
			return (this.rear() - this.front());
		} else {
			return (this.capacity() + this.rear() - this.front());
		}

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (this.front() == this.rear());
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return (this.front() == this.nextRear());
	}

	@Override
	public boolean add(T anElement) {
		// TODO Auto-generated method stub
		if (this.isFull()) {
			return false;

		} else {
			this.setRear(this.nextRear());
			this.elements()[this.rear()] = anElement;
			return true;
		}
	}

	@Override
	public T remove() {
		// TODO Auto-generated method stub
		if (this.isEmpty()) {
			return null;
		} else {
			this.setFront(this.nextFront());
			return this.elements()[this.front()];
		}
	}
}
