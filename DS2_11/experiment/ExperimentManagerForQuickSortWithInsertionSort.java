package experiment;

import app.AppView;
import sort.QuickSortByPivotRandom;
import sort.QuickSortWithInsertionSort;

public class ExperimentManagerForQuickSortWithInsertionSort extends ExperimentManager {

	private static final boolean DUBUG_MODE = false;

	private static void showDebugMessage(String aMessage) {
		if (ExperimentManagerForQuickSortWithInsertionSort.DUBUG_MODE) {
			AppView.outputDebugMessage(aMessage);
		}
	}

	// Constants for the experiment parameters
	protected static final int DEFAULT_NUMBER_OF_ITERATOR = 10;
	protected static final int DEFAULT_INCREMENT_SIZE = 10000;
	protected static final int DEFAULT_STARTING_SIZE = DEFAULT_INCREMENT_SIZE;

	protected static final int DEFAULT_NUMBER_OF_REPETITION_OF_SINGLE_SORT = 10;

	private static final QuickSortByPivotRandom<Integer> QuickSortByPivotRandom = new QuickSortByPivotRandom<Integer>(
			true);

	private static final QuickSortWithInsertionSort<Integer> QuickSortWithInsertionSort = new QuickSortWithInsertionSort<Integer>(
			true);

	private static final int DEFAULT_INSERTION_SORT_STARTING_SIZE = 10;
	private static final int DEFAULT_INSERTION_SORT_INCREMENT_SIZE = 10;
	private static final int DEFAULT_INSERTION_SORT_NUMBER_OF_ITERATION = 9;

	private ParameterSetForIteration _parameterSetForMaxSizeOfInsertionSort;

	private long[] _measurementForQuickSortByPivotRandom;
	private long[][] _measurementForQuickSortWithInsertionSort;

	public ParameterSetForIteration parameterSetForMaxSizeOfInsertionSort() {
		return this._parameterSetForMaxSizeOfInsertionSort;
	}

	public void setParameterSetForMaxSizeOfInsertionSort(ParameterSetForIteration newParameterSet) {
		this._parameterSetForMaxSizeOfInsertionSort = newParameterSet;
	}

	private long[] measurementForQuickSortByPivotRandom() {
		return this._measurementForQuickSortByPivotRandom;
	}

	private void setMeasurementForQuickSortByPivotRandom(long[] newMesurement) {
		this._measurementForQuickSortByPivotRandom = newMesurement;
	}

	private long[][] measurementForQuickSortWithInsertionSort() {
		return this._measurementForQuickSortWithInsertionSort;
	}

	private void setMeasurementForQuickSortWithInsertionSort(long[][] newMesurement) {
		this._measurementForQuickSortWithInsertionSort = newMesurement;
	}

	private void setMeasurementForQuickSortWithInsertionSort(long[] newMeasurement, int measurementIndex) {
		this.measurementForQuickSortWithInsertionSort()[measurementIndex] = newMeasurement;
	}

	@Override
	protected void performMeasuring(ListOrder anOrder) {
		Integer[] list = this.dataSet().listWithOrder(anOrder);

		this.setMeasurementForQuickSortWithInsertionSort(
				new long[this.parameterSetForMaxSizeOfInsertionSort().numberOfIteration()][]);

		this.setMeasurementForQuickSortByPivotRandom(this.experiment()
				.durationsOfSort(ExperimentManagerForQuickSortWithInsertionSort.QuickSortByPivotRandom, list));

		ExperimentManagerForQuickSortWithInsertionSort.showDebugMessage("[Debug] end of QuickSortByPivotRandom\n");

		for (int iteration = 0; iteration < this.parameterSetForMaxSizeOfInsertionSort()
				.numberOfIteration(); iteration++) {
			int size = this.parameterSetForMaxSizeOfInsertionSort().startingSize()
					+ this.parameterSetForMaxSizeOfInsertionSort().incrementSize() * iteration;
			QuickSortWithInsertionSort.setMaxSizeForInsertionSort(size);
			this.setMeasurementForQuickSortWithInsertionSort(
					this.experiment().durationsOfSort(
							ExperimentManagerForQuickSortWithInsertionSort.QuickSortWithInsertionSort, list),
					iteration);

			ExperimentManagerForQuickSortWithInsertionSort
					.showDebugMessage("[Debug] end of QuickSortWithInsertionSort" + size + "\n");
		}
	}

	public ExperimentManagerForQuickSortWithInsertionSort() {
		this.setParameterSetForMeasurement(new ParameterSetForMeasurement(DEFAULT_STARTING_SIZE,
				DEFAULT_NUMBER_OF_ITERATION, DEFAULT_INCREMENT_SIZE, DEFAULT_NUMBER_OF_REPETITION_OF_SINGLE_SORT));
		this.setParameterSetForMaxSizeOfInsertionSort(new ParameterSetForIteration(DEFAULT_INSERTION_SORT_STARTING_SIZE,
				DEFAULT_INSERTION_SORT_NUMBER_OF_ITERATION, DEFAULT_INSERTION_SORT_INCREMENT_SIZE));
	}

	public long measurementForQuickSortByPivotRandomAt(int anIndex) {
		return this.measurementForQuickSortByPivotRandom()[anIndex];
	}

	public long measurementForQuickSortWithInsertionSortAt(int aMeasurementIndex, int anIndex) {
		return this.measurementForQuickSortWithInsertionSort()[aMeasurementIndex][anIndex];
	}

	@Override
	public void performExperiment(ListOrder anOrder) {
		// TODO Auto-generated method stub
		this.performMeasuring(anOrder);

	}

}