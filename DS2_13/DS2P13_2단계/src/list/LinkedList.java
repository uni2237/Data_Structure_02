package list;

public class LinkedList<T> implements List<T> {
	
	//Private instance variables
	private LinkedNode<T> _head;
	private int _size;
	
	//Getters & Setters
	private LinkedNode<T> head(){
		return this._head;
	}
	
	private void setHead(LinkedNode<T> newHead){
		this._head = newHead;
	}
	
	@Override
	public int size(){
		return this._size;
	}
	
	private void setSize(int newSize){
		this._size = newSize;
	}
	
	// Constructor
	public LinkedList(){
		this.reset();
	}
	
	
	//public methods
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.setSize(0);
		this.setHead(null);
	}

	@Override
	public boolean isEmpty(){
		return (this.size() == 0);
	}
	
	@Override
	public boolean isFull(){
		return false;
	}
	

	@Override
	public T firstElement() {
		// TODO Auto-generated method stub
		if(this.isEmpty()){
			return null;
		}
		else{
			return this.head().element();
		}
	}

	@Override
	public T lastElement() {
		// TODO Auto-generated method stub
		LinkedNode<T> current = this.head();
		if(current == null){
		return null;
		}
		else{
			while(current.next() != null){
				current = current.next();
			}
			return current.element();
		}
	}

	@Override
	public T elementAtIndex(int anIndex) {
		// TODO Auto-generated method stub
		if (anIndex < 0 || anIndex >= this.size()){
		return null;
		}
		else{
			LinkedNode<T> current = this.head();
			for(int i = 0; i<anIndex; i++){
				current = current.next();
			}
			return current.element();
		}
	}
	
	@Override
	public boolean add (T anElement){
		return this.addToFirst(anElement);
	}

	@Override
	public boolean addToFirst(T anElement) {
		// TODO Auto-generated method stub
		LinkedNode<T> newHeadNode = new LinkedNode<T>(anElement,this.head());
		this.setHead(newHeadNode);
		this.setSize(this.size() + 1);
		return true;
	}

	@Override
	public boolean addToLast(T anElement) {
		// TODO Auto-generated method stub
		if(this.isEmpty()){
			return this.addToFirst(anElement);
		}
		else{
			LinkedNode<T> current = this.head();
			while(current.next() != null){
				current = current.next();
			}
			current.setNext(new LinkedNode<T>(anElement, null));
			this.setSize(this.size()+1);
			
			return true;
		}
	}

	@Override
	public boolean addAtIndex(T anElement, int anIndex) {
		// TODO Auto-generated method stub
		if(anIndex < 0 || anIndex > this.size()){
			return false;
		}
		else if(anIndex == 0){
			return this.addToFirst(anElement);
		}
		else{
			LinkedNode<T> previous = this.head();
			LinkedNode<T> current = previous.next();
			for(int i = 1; i< anIndex; i++){
				previous = current;
				current = current.next();
			}
			
			LinkedNode<T> newNode = new LinkedNode<T>(anElement, current);
			previous.setNext(newNode);
			this.setSize(this.size()+1);
			return true;
		}
	}
	
	@Override
	public T removeAny(){
		return this.removeFirst();
	}
	

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub
		if(! this.isEmpty()){
			T firstElement = this.head().element();
			this.setHead(this.head().next());
			this.setSize(this.size() - 1);
			return firstElement;
		}
		return null;
	}

	@Override
	public T removeLast() {
		// TODO Auto-generated method stub
		if(this.isEmpty()){
			return null;
		}
		else if(this.head().next() == null){
		T lastElement = this.head().element();
		this.setHead(null);
		this.setSize(0);
		return lastElement;
		}
		else{
			LinkedNode<T> previous = this.head();
			LinkedNode<T> current = previous.next();
			while (current.next() != null){
				previous = current;
				current = current.next();
			}
			T lastElement = current.element();
			previous.setNext(current.next());
			this.setSize(this.size() - 1);
			return lastElement;
		}
	}

	@Override
	public T removeAtIndex(int anIndex) {
		// TODO Auto-generated method stub
		if(anIndex < 0 || anIndex >= this.size()){
			return null;
		}
		else if(anIndex == 0){
			return this.removeFirst();
		}
		else{
			LinkedNode<T> previous = this.head();
			LinkedNode<T> current = previous.next();
			for(int i = 1; i< anIndex; i++){
				previous = current;
				current = current.next();
			}
			previous.setNext(current.next());
			this.setSize(this.size() -1);
			return current.element();
		}
	}
	
	//반복자를 위한 객체는 사용자가 직접 객체를 생성하지 않고
	//연결리스트 객체에게 바로 이 iterator()를 통해서 요청하여 얻는다.
	@Override
	public Iterator<T> iterator(){
		return new IteratorForLinkedList();
	}

	
	//inner class
	//연결리스트의 반복자 객체를 위한 class
	public class IteratorForLinkedList implements Iterator<T> {
		//private instance variables
		LinkedNode<T> _nextNode;
		
		//private getter & Setter
		private LinkedNode<T> nextNode(){
			return this._nextNode;
		}
		
		private void setNextNode(LinkedNode<T> newLinkedNode){
			this._nextNode = newLinkedNode;
		}
		
		//Constructor
		private IteratorForLinkedList(){
			this.setNextNode(LinkedList.this.head());
		}
		
		//공개함수 구현
		@Override
		public boolean hasNext(){
			return (this.nextNode() != null);
		}
		
		@Override
		public T next(){
			T nextElement = this.nextNode().element();
			this.setNextNode(this.nextNode().next());
			return nextElement;
		}
	}


	

}
