package sort;

public class QuickSortWithInsertionSort<E extends Comparable<E>> extends QuickSortByPivotRandom<E> {

	private static final int DEFAULT_MAX_SIZE_FOR_INSERTION_SORT = 20;

	private int _maxSizeForInsertionSort;

	// getter & setter
	public int maxSizeForInsertionSort() {
		return this._maxSizeForInsertionSort;
	}

	public void setMaxSizeForInsertionSort(int newMaxSizeForInsertionSort) {
		this._maxSizeForInsertionSort = newMaxSizeForInsertionSort;
	}

	private boolean insertionSort(E[] aList, int left, int right) {
		for (int i = (right - 1); i >= left; i--) {
			E insertedElement = aList[i];
			int j = i + 1;
			while (this.compare(aList[j], insertedElement) < 0) {
				aList[j - 1] = aList[j];
				j++;
			}
			aList[j - 1] = insertedElement;
		}
		return true;
	}

	@Override
	protected void quickSortRecursively(E[] aList, int left, int right) {
		int currentSize = right - left;
		if (currentSize > 0) {
			if (currentSize <= this.maxSizeForInsertionSort()) {
				this.insertionSort(aList, left, right);
			} else {
				int mid = partition(aList, left, right); // DEVIDE
				quickSortRecursively(aList, left, mid - 1); // CONQUER
				quickSortRecursively(aList, mid + 1, right); // CONQUER
			}
		}
	}

	// Constructor
	public QuickSortWithInsertionSort(boolean givenSortingOrder) {
		super(givenSortingOrder);
	}

}
