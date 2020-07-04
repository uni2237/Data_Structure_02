package app;

import experiment.DataGenerator;
import sort.QuickSortByPivotLeft;
import sort.QuickSortByPivotMedian;
import sort.QuickSortByPivotMid;
import sort.QuickSortByPivotRandom;
import sort.QuickSortWithInsertionSort;
import sort.Sort;

// MVC���� �� C Controller�� �ش��ϸ� �������� app�� ��Ʈ�� �ϴ� ������ �Ѵ�.
public class AppController {

	// Constants
	private static final int TEST_SIZE = 10000;
	private static final QuickSortByPivotLeft<Integer> QuickSortByPivotLeft = new QuickSortByPivotLeft<Integer>(true);
	private static final QuickSortByPivotMid<Integer> QuickSortByPivotMid = new QuickSortByPivotMid<Integer>(true);
	private static final QuickSortByPivotMedian<Integer> QuickSortByPivotMedian = new QuickSortByPivotMedian<Integer>(
			true);
	private static final QuickSortByPivotRandom<Integer> QuickSortByPivotRandom = new QuickSortByPivotRandom<Integer>(
			true);
	private static final QuickSortWithInsertionSort<Integer> QuickSortWithInsertionSort = new QuickSortWithInsertionSort<Integer>(
			true);

	// Private variable
	private Integer[] _list;
	private String _listTypeName;
	private boolean _nonDecreasing;

	// Getters & Setters
	private Integer[] list() {
		return this._list;
	}

	private void setList(Integer[] newList) {
		this._list = newList;
	}

	private String listTypeName() {
		return this._listTypeName;
	}

	private void setListTypeName(String newListTypeName) {
		this._listTypeName = newListTypeName;
	}

	private boolean nonDecreasingSort() {
		return this._nonDecreasing;
	}

	private void setNonDecreasingSort(boolean newNonDecreasing) {
		this._nonDecreasing = newNonDecreasing;
	}

	// Private methods
	private String sortingOrderName(Sort<Integer> aSort) {
		if (aSort.nonDecreasingOrder()) {
			return "��������";
		} else {
			return "��������";
		}
	}

	private void outputValidationMessage(Sort<Integer> aSort, Integer[] aList) {
		AppView.output("- [" + this.listTypeName() + "]�� ���� [" + this.sortingOrderName(aSort) + "]�� ["
				+ aSort.getClass().getSimpleName() + "] ����� ");
		if (this.sortedListIsValid(aList, aSort.nonDecreasingOrder())) {
			AppView.outputLine("�ùٸ��ϴ�.");
		} else {
			AppView.outputLine("�߸��Ǿ����ϴ�.");
		}
	}

	private Integer[] copyList(Integer[] aList) {
		Integer[] copiedList = new Integer[aList.length];
		for (int i = 0; i < aList.length; i++) {
			copiedList[i] = aList[i];
		}
		return copiedList;
	}

	private boolean sortedListIsValid(Integer[] aList, boolean nonDecreasing) {
		if (nonDecreasing) {
			for (int i = 0; i < aList.length - 1; i++) {
				if (aList[i].compareTo(aList[i + 1]) > 0) {
					return false;
				}
			}
			return true;
		} else {
			for (int i = 0; i < aList.length - 1; i++) {
				if (aList[i].compareTo(aList[i + 1]) < 0) {
					return false;
				}
			}
			return true;
		}
	}

	private void validateSort(Sort<Integer> aSort) {
		Integer[] list = copyList(this.list());
		aSort.sort(list);
		this.outputValidationMessage(aSort, list);
	}

	private void validateSorts() {
		this.validateSort(AppController.QuickSortByPivotLeft);
		this.validateSort(AppController.QuickSortByPivotMid);
		this.validateSort(AppController.QuickSortByPivotMedian);
		this.validateSort(AppController.QuickSortByPivotRandom);
		this.validateSort(AppController.QuickSortWithInsertionSort);
		AppView.outputLine("");
	}

	private void validateWithRandomList() {
		this.setListTypeName("������ ����Ʈ");
		this.setList(DataGenerator.randomList(AppController.TEST_SIZE));
		this.validateSorts();
	}

	private void validateWithAscendingList() {
		this.setListTypeName("�������� ����Ʈ");
		this.setList(DataGenerator.ascendingList(AppController.TEST_SIZE));
		this.validateSorts();
	}

	private void validateWithDescendingList() {
		this.setListTypeName("�������� ����Ʈ");
		this.setList(DataGenerator.descendingList(AppController.TEST_SIZE));
		this.validateSorts();
	}

	private void setSortingOrder(boolean aNonDecreasing) {
		AppController.QuickSortByPivotLeft.setNonDecreasingOrder(aNonDecreasing);
		AppController.QuickSortByPivotMid.setNonDecreasingOrder(aNonDecreasing);
		AppController.QuickSortByPivotMedian.setNonDecreasingOrder(aNonDecreasing);
		AppController.QuickSortByPivotRandom.setNonDecreasingOrder(aNonDecreasing);
		AppController.QuickSortWithInsertionSort.setNonDecreasingOrder(aNonDecreasing);

	}

	// Constructor
	public AppController() {
	}

	// private methods

	public void run() {
		AppView.outputLine("<<< ���� �˰������ �����ϴ� ���α׷��� �����մϴ� >>>");
		AppView.outputLine("");

		AppView.outputLine("> �������� ���� ���α׷��� ����:");
		this.setSortingOrder(true);
		this.validateWithRandomList();
		this.validateWithAscendingList();
		this.validateWithDescendingList();

		AppView.outputLine("> �������� ���� ���α׷��� ����:");
		this.setSortingOrder(false);
		this.validateWithRandomList();
		this.validateWithAscendingList();
		this.validateWithDescendingList();

		AppView.outputLine("<<< ���� �˰������ �����ϴ� ���α׷��� �����մϴ� >>>");
	}

}
