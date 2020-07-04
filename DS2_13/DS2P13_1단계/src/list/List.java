package list;

public interface List<T> {
	
	public int size();
	
	public void reset();
	
	public boolean isEmpty();
	public boolean isFull();
	
	public T firstElement();
	public T lastElement();
	public T elementAtIndex(int anIndex);
	
	public boolean add(T anElement);
	public boolean addToFirst(T anElement);
	public boolean addToLast(T anElement);
	public boolean addAtIndex(T anElement, int anIndex);
	
	public T removeAny();
	public T removeFirst();
	public T removeLast();
	public T removeAtIndex(int anIndex);

	public Iterator<T> iterator();


}
