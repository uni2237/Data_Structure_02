package list;


public class LinkedList<T> {
	
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
	
	public int size(){
		return this._size;
	}
	
	private void setSize(int newSize){
		this._size = newSize;
	}
	
	// Constructor
	public LinkedList(){
		this.setSize(0);
		this.setHead(null);
	}
	
	//public methods
	public boolean isEmpty(){
		return (this.size() == 0);
	}
	
	public boolean isFull(){
		return false;
	}
	
	public boolean add (T anElement){
		LinkedNode<T> newHeadNode = new LinkedNode<T>(anElement,this.head());
		this.setHead(newHeadNode);
		this.setSize(this.size() + 1);
		return true;
	}
	
	public T removeAny(){
		if(this.isEmpty()){
			return null;
		}
		else{
			T removedElement = this.head().element();
			this.setHead(this.head().next());
			this.setSize(this.size() - 1);
			return removedElement;
		}
	}
	
	
	//�ݺ��ڸ� ���� ��ü�� ����ڰ� ���� ��ü�� �������� �ʰ�
	//���Ḯ��Ʈ ��ü���� �ٷ� �� iterator()�� ���ؼ� ��û�Ͽ� ��´�.
	public IteratorForLinkedList iterator(){
		return new IteratorForLinkedList();
	}
	
	//inner class
	//���Ḯ��Ʈ�� �ݺ��� ��ü�� ���� class
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
		
		//�����Լ� ����
		public boolean hasNext(){
			return (this.nextNode() != null);
		}
		
		public T next(){
			T nextElement = this.nextNode().element();
			this.setNextNode(this.nextNode().next());
			return nextElement;
		}
	}
	

}
