package dictionary;

import list.Iterator;

public interface Dictionary {

	public boolean keyDoesExist(Key aKey);

	public Object objectForKey(Key aKey);

	public boolean addObjectForKey(Object anObject, Key aKey);

	public boolean replaceObjectForKey(Object anObject, Key aKey);

	public boolean removeObjectForKey(Object anObject, Key aKey);

	public void removeAllObjects();

	public Iterator<Object> iterator();

}
