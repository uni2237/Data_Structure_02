package list;

public class LinkedStack<E> implements Stack<E> {
	
	// Private instance variables
	private int _size;
	private LinkedNode<E> _top;
	
	//Getters & Setters
	private LinkedNode<E> top(){
		return this._top;
	}
	private void setTop(LinkedNode<E> newTop){
		this._top = newTop;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this._size;
	}
	
	private void setSize(int newSize){
		this._size = newSize;
	}
	
	// Constructor
	public LinkedStack() {
		// TODO Auto-generated constructor stub
		this.setSize(0);
		this.setTop(null);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (this.size() == 0);
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean push(E anElement) {
		// TODO Auto-generated method stub
		LinkedNode<E> newTop = new LinkedNode<E>(anElement, this.top());
		this.setTop(newTop);
		this.setSize(this.size()+1);
		return true;
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		if(this.isEmpty()){
			return null;
		}
		else{
			E removedElement = this.top().element();
			this.setTop(this.top().next());
			this.setSize(this.size()-1);
			return removedElement;
		}
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
	public IteratorForLinkedStack iterator(){
		return new IteratorForLinkedStack();
	}
	
	public class IteratorForLinkedStack implements Iterator<E>{
		LinkedNode<E> _nextNode;
		
		private LinkedNode<E> nextNode(){
			return this._nextNode;
		}
		private void setNextNode(LinkedNode<E> aLinkedNode){
			this._nextNode = aLinkedNode;
		}
		
		//Constructor
		
		private IteratorForLinkedStack(){
			this.setNextNode(LinkedStack.this.top());
		}
		
		//hasNext() & next()
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (this.nextNode() != null);
		}
		@Override
		public E next() {
			// TODO Auto-generated method stub
			E nextElement = this.nextNode().element();
			this.setNextNode(this.nextNode().next());
			return nextElement;
		}
		
		
	}

}
