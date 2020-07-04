package list;

public interface Queue<T> {

	public int size();
	
	public void reset();
	
	public boolean isEmpty();
	public boolean isFull();
	
	public boolean add(T element);
	public boolean offer(T element);

	public T remove();
	public T poll();
	
	public T element();
	public T peek();
	
}
