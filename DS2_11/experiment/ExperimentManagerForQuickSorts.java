package experiment;

import app.AppView;
import sort.QuickSortByPivotLeft;
import sort.QuickSortByPivotMedian;
import sort.QuickSortByPivotMid;
import sort.QuickSortByPivotRandom;
import sort.QuickSortWithInsertionSort;

public class ExperimentManagerForQuickSorts extends ExperimentManager {

	private static final boolean DEBUG_MODE = false;

	private static void showDebugMessage(String aMessage) {
		if (ExperimentManagerForQuickSorts.DEBUG_MODE) {
			AppView.outputDebugMessage(aMessage);
		}
	}

	// Constants
	private static final QuickSortByPivotLeft<Integer> QuickSortByPivotLeft = new QuickSortByPivotLeft<Integer>(true);
	private static final QuickSortByPivotMid<Integer> QuickSortByPivotMid = new QuickSortByPivotMid<Integer>(true);
	private static final QuickSortByPivotMedian<Integer> QuickSortByPivotMedian = new QuickSortByPivotMedian<Integer>(
			true);
	private static final QuickSortByPivotRandom<Integer> QuickSortByPivotRandom = new QuickSortByPivotRandom<Integer>(
			true);
	private static final QuickSortWithInsertionSort<Integer> QuickSortWithInsertionSort = new QuickSortWithInsertionSort<Integer>(
			true);

	// Private instance variables
	private long[] _measurementForQuickSortByPivotLeft;
	private long[] _measurementForQuickSortByPivotMid;
	private long[] _measurementForQuickSortByPivotMedian;
	private long[] _measurementForQuickSortByPivotRandom;
	private long[] _measurementForQuickSortWithInsertionSort;

	// Getters & setters
	private long[] measurementForQuickSortByPivotLeft() {
		return this._measurementForQuickSortByPivotLeft;
	}

	private void setMeasurementForQuickSortByPivotLeft(long[] newMeasurement) {
		this._measurementForQuickSortByPivotLeft = newMeasurement;
	}

	private long[] measurementForQuickSortByPivotMid() {
		return this._measurementForQuickSortByPivotMid;
	}

	private void setMeasurementForQuickSortByPivotMid(long[] newMeasurement) {
		this._measurementForQuickSortByPivotMid = newMeasurement;
	}

	private long[] measurementForQuickSortByPivotMedian() {
		return this._measurementForQuickSortByPivotMedian;
	}

	private void setMeasurementForQuickSortByPivotMedian(long[] newMeasurement) {
		this._measurementForQuickSortByPivotMedian = newMeasurement;
	}

	private long[] measurementForQuickSortByPivotRandom() {
		return this._measurementForQuickSortByPivotRandom;
	}

	private void setMeasurementForQuickSortByPivotRandom(long[] newMeasurement) {
		this._measurementForQuickSortByPivotRandom = newMeasurement;
	}

	private long[] measurementForQuickSortWithInsertionSort() {
		return this._measurementForQuickSortWithInsertionSort;
	}

	private void setMeasurementForQuickSortWithInsertionSort(long[] newMeasurement) {
		this._measurementForQuickSortWithInsertionSort = newMeasurement;
	}

	// protected method
	@Override
	protected void performMeasuring(ListOrder anOrder) {
		Integer[] list = this.dataSet().listWithOrder(anOrder);

		this.setMeasurementForQuickSortByPivotLeft(
				this.experiment().durationsOfSort(ExperimentManagerForQuickSorts.QuickSortByPivotLeft, list));
		ExperimentManagerForQuickSorts.showDebugMessage("[Debug] end of QuickSortByPivotLeft\n");

		this.setMeasurementForQuickSortByPivotMid(
				this.experiment().durationsOfSort(ExperimentManagerForQuickSorts.QuickSortByPivotMid, list));
		ExperimentManagerForQuickSorts.showDebugMessage("[Debug] end of QuickSortByPivotMid\n");

		this.setMeasurementForQuickSortByPivotMedian(
				this.experiment().durationsOfSort(ExperimentManagerForQuickSorts.QuickSortByPivotMedian, list));
		ExperimentManagerForQuickSorts.showDebugMessage("[Debug] end of QuickSortByPivotMedian\n");

		this.setMeasurementForQuickSortByPivotRandom(
				this.experiment().durationsOfSort(ExperimentManagerForQuickSorts.QuickSortByPivotRandom, list));
		ExperimentManagerForQuickSorts.showDebugMessage("[Debug] end of QuickSortByPivotRandom\n");

		this.setMeasurementForQuickSortWithInsertionSort(
				this.experiment().durationsOfSort(ExperimentManagerForQuickSorts.QuickSortWithInsertionSort, list));
		ExperimentManagerForQuickSorts.showDebugMessage("[Debug] end of QuickSortWithInsertionSort\n");

	}

	// public method
	public long measurementForQuickSortByPivotLeftAt(int anIndex) {
		return this.measurementForQuickSortByPivotLeft()[anIndex];
	}

	public long measurementForQuickSortByPivotMidAt(int anIndex) {
		return this.measurementForQuickSortByPivotMid()[anIndex];
	}

	public long measurementForQuickSortByPivotMedianAt(int anIndex) {
		return this.measurementForQuickSortByPivotMedian()[anIndex];
	}

	public long measurementForQuickSortByPivotRandomAt(int anIndex) {
		return this.measurementForQuickSortByPivotRandom()[anIndex];
	}

	public long measurementForQuickSortWithInsertionSortAt(int anIndex) {
		return this.measurementForQuickSortWithInsertionSort()[anIndex];
	}

	@Override
	public void performExperiment(ListOrder anOrder) {
		this.performMeasuring(anOrder);
		// TODO Auto-generated method stub
		
	}

}
