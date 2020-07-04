package dictionary;

import java.lang.reflect.Array;
import list.List;
import list.Iterator;
import list.LinkedList;

public class HashTable implements Dictionary {

	private static final int HASH_TABLE_SIZE = 193;

	private List<Node>[] _bucket;
	private int _hashTableSize;
	private int _numOfItems;
	private int _numOfNonEmptyBuckets;
	private DictionaryKeyIterator _dictionaryKeyIterator;

	public DictionaryKeyIterator dictionaryKeyIterator() {
		return this._dictionaryKeyIterator;
	}

	public void setDictionaryKeyIterator(DictionaryKeyIterator aDictionaryKeyIterator) {
		this._dictionaryKeyIterator = aDictionaryKeyIterator;
	}

	private List<Node>[] bucket() {
		return this._bucket;
	}

	private void setBucket(List<Node>[] aNode) {
		this._bucket = aNode;
	}

	private int hashTableSize() {
		return this._hashTableSize;
	}

	private void setHashTableSize(int aHashTableSize) {
		this._hashTableSize = aHashTableSize;
	}

	private int numOfItems() {
		return this._numOfItems;
	}

	private void setNumOfItems(int aNumOfItems) {
		this._numOfItems = aNumOfItems;
	}

	private int numOfNonEmptyBuckets() {
		return this._numOfNonEmptyBuckets;
	}

	private void setNumOfNunEmptyBuckets(int aNumOfNonEmptyBuckets) {
		this._numOfNonEmptyBuckets = aNumOfNonEmptyBuckets;
	}

	@SuppressWarnings("unchecked")
	public HashTable() {
		this.setHashTableSize(HASH_TABLE_SIZE);
		this.setNumOfItems(0);
		this.setNumOfNunEmptyBuckets(0);

		this.setBucket((LinkedList<Node>[]) Array.newInstance(LinkedList.class, this.hashTableSize()));

		for (int i = 0; i < this.hashTableSize(); i++) {
			this._bucket[i] = new LinkedList<Node>();
		}
	}

	@Override
	public boolean keyDoesExist(Key aKey) {
		// TODO Auto-generated method stub
		int hashIndex = hash(aKey.value());
		List<Node> sysnonymList = this.bucket()[hashIndex];
		if (this.bucket()[hashIndex].isEmpty()) {
			return false;
		} else {
			Iterator<Node> iterator = sysnonymList.iterator();
			while (iterator.hasNext()) {
				Node node = iterator.next();
				if (node.key().value().equals(aKey.value())) {

					return true;
				}
			}
			return false;
		}
	}

	@Override
	public Object objectForKey(Key aKey) {
		int hashIndex = hash(aKey.value());
		List<Node> aList = this.bucket()[hashIndex];

		if (!aList.isEmpty()) {
			Iterator<Node> iterator = aList.iterator();

			while (iterator.hasNext()) {
				Node aNode = iterator.next();
				if (aNode.key().value().equals(aKey.value())) {
					Item tmp = new Item(aNode.item().count());
					return tmp;
				}
			}
		}
		return null;
	}

	@Override
	public boolean addObjectForKey(Object anObject, Key aKey) {
		if (!this.keyDoesExist(aKey)) {
			int hashIndex = hash(aKey.value());
			Node aNode = new Node(aKey, (Item) anObject);

			this.bucket()[hashIndex].add(aNode);
			return true;
		}
		return false;
	}

	public float loadingFactor() {
		return ((float) this.numOfNonEmptyBuckets() / (float) this.hashTableSize());
	}

	public float averageSynonymListLength() {
		Iterator<Node> iterator = null;
		for (int i = 0; i < HASH_TABLE_SIZE; i++) {
			if (this.bucket()[i] != null) {
				iterator = this.bucket()[i].iterator();
				while (iterator.hasNext()) {
					Node aNode = iterator.next();
					Item anItem = aNode.item();
					this.setNumOfItems(this.numOfItems() + anItem.count());
				}
			}
		}
		return ((float) this.numOfItems() / (float) this.numOfNonEmptyBuckets());
	}

	private int hash(String idString) {
		int sum = 0;
		int index = 0;
		char[] id = idString.toCharArray();
		while (index < id.length) {
			sum = sum + (int) id[index];
			index++;
		}
		return (sum % _hashTableSize);
	}

	@Override
	public boolean replaceObjectForKey(Object anObject, Key aKey) {
		if (this.keyDoesExist(aKey)) {
			int hashIndex = hash(aKey.value());
			Node aNode = null;
			Iterator<Node> iterator = this.bucket()[hashIndex].iterator();
			while (iterator.hasNext()) {
				aNode = iterator.next();
				if (aNode.key().value().equals(aKey.value())) { // 같은키를 갖는 아이템을
																// 찾음
					aNode.item().incrementCount();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean removeObjectForKey(Object anObject, Key aKey) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeAllObjects() {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<Object> iterator() {
		// TODO Auto-generated method stub
		return new DictionaryKeyIterator();
	}

	public class DictionaryKeyIterator implements Iterator<Object> {
		private int _currentBucketIndex;
		private Object _availableNode;
		@SuppressWarnings("rawtypes")
		private Iterator _linkedListIterator;

		private int currentBucketIndex() {
			return this._currentBucketIndex;
		}

		private void setCurrentBucketIndex(int aCurrentBucketIndex) {
			this._currentBucketIndex = aCurrentBucketIndex;
		}

		private Object availableNode() {
			return this._availableNode;
		}

		private void setAvailableNode(Object aNode) {
			this._availableNode = aNode;
		}

		private DictionaryKeyIterator() {
			this.begin();
		}

		public void begin() {
			if (_bucket == null)
				return;
			this.setCurrentBucketIndex(0);
			nextNonEmptyBucket();
		}

		public boolean nextNonEmptyBucket() {
			for (int i = this.currentBucketIndex(); i < _hashTableSize; i++) {
				if (!_bucket[i].isEmpty()) {
					this.setCurrentBucketIndex(++i);
					_numOfNonEmptyBuckets++;
					_linkedListIterator = bucket()[currentBucketIndex() - 1].iterator();
					setAvailableNode(_linkedListIterator.next());
					return true;
				}
			}
			this.setCurrentBucketIndex(_hashTableSize);
			this.setAvailableNode(null);
			return false;
		}

		@Override
		public boolean hasNext() {
			return this.availableNode() != null;
		}

		@Override
		public Object next() {
			if (this.currentBucketIndex() == 0 || this.availableNode() == null) {
				if (!nextNonEmptyBucket()) {
					return null;
				}
			}
			Object aNode = this.availableNode();
			if (_linkedListIterator.hasNext())
				setAvailableNode(_linkedListIterator.next());
			else
				nextNonEmptyBucket();

			return aNode;
		}
	}

}