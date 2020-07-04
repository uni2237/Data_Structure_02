package list;


public class CircularQueue<T> implements Queue<T>{
	
	private final static int DEFAULT_CIRCULAR_QUEUE_CAPACITY = 1000;

	private int _size;
	private T[] _elements;
	private int _capacity; 
	private int _front;
	private int _rear;
	

	private int capacity(){
		return this._capacity;
	}
	private void setCapacity(int newCapacity){
		this._capacity = newCapacity;
	}
	
	private T[] elements() {
		return this._elements;
	}	
	private void setElements(T[] newElements){
		this._elements = newElements;
	}
	
	private int rear(){
		return this._rear;
	}
	private void setRear(int newRear){
		this._rear = newRear;
	}
	
	private int front(){
		return this._front;
	}
	private void setFront(int newFront){
		this._front = newFront;
	}
	

	public CircularQueue(){
		this(DEFAULT_CIRCULAR_QUEUE_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public CircularQueue(int givenCapacity) {
		this.setCapacity(givenCapacity + 1);
		this.setElements((T[]) new Object[this.capacity()]);
		
		this.setFront(0);
		this.setRear(-1);
	}
	@Override
	public int size(){
		return this._size;
	}
	
	private void setSize(int aSize){
		this._size = aSize;
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (this.size() == 0);
	}
	
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return (this.size() == this.capacity());
	}
	
	
	@Override
	public boolean add(T element) {
		// TODO Auto-generated method stub
		if(this.isFull()){
			throw new ArrayIndexOutOfBoundsException();
		}
		
		if(this.rear() == (this.capacity() - 1)){
			this.setRear(0);
		}
		else {
			this.setRear(this.rear() + 1);
		}
		this.elements()[this.rear()] = element;
		this.setSize(this.size() + 1);
		
		return true;
	}
	
	@Override
	public T remove() {
		// TODO Auto-generated method stub
		if(this.isEmpty()){
			return null;			
		}
		
		T item = this.peek();
		this.setFront(this.front() + 1);
		if(this.front() == this.capacity()){
			this.setFront(0);
		}
		
		this.setSize(this.size() - 1);
		
		return item;
	}
	
	@Override
	public T peek() {
		// TODO Auto-generated method stub
		if(this.isEmpty()){
			throw new ArithmeticException();
		}
		
		return this.elements()[this.front()];
	}

	private int nextRear(){
		return (this.rear() + 1) % this.capacity();
	}
	
	private int nextFront(){
		return (this.front() + 1) % this.capacity();
	}
	


	@Override
	public void reset() {
		this.setFront(0);
		this.setRear(0);
	}





	@Override
	public boolean offer(T element) {
		return false;
	}

	@Override
	public T poll() {
		return null;
	}

	@Override
	public T element() {
		return null;
	}


}
