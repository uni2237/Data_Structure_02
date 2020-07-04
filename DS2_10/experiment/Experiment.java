package experiment;

import app.AppView;
import sort.Sort;

public class Experiment {

	private static final boolean DEBUG_MODE = false;

	private static void showDebugMessage(String aMessage) {
		if (Experiment.DEBUG_MODE) {
			AppView.outputDebugMessage(aMessage);
		}
	}

	// Private static methods
	private static Integer[] copyListOfGivenSize(Integer[] aList, int givenSize) {
		if (givenSize <= aList.length) {
			Integer[] copiedList = new Integer[givenSize];
			for (int i = 0; i < givenSize; i++) {
				copiedList[i] = aList[i];
			}
			return copiedList;
		}
		return null;
	}

	// Public static methods
	public static long durationOfSinglSort(Sort<Integer> aSort, Integer[] aList) {
		Timer.start();
		{
			aSort.sort(aList);
		}
		Timer.stop();
		return Timer.duration();
	}

	// Private instance
	private ParameterSetForMeasurement _parameterSetForMeasurement;

	// Getters & Setters
	private ParameterSetForMeasurement parameterSetForMeasurement() {
		return this._parameterSetForMeasurement;
	}

	private void setParameterSetForMeasurement(ParameterSetForMeasurement newParameterSet) {
		this._parameterSetForMeasurement = newParameterSet;
	}

	// Constructor
	public Experiment(ParameterSetForMeasurement givenParameterSet) {
		this.setParameterSetForMeasurement(givenParameterSet);
	}

	// public method
	public long[] durationsOfSort(Sort<Integer> sort, Integer[] list) {
		long[] durations = new long[this.parameterSetForMeasurement().numberOfIteration()];
		for (int i = 0, size = this.parameterSetForMeasurement().startingSize(); i < this.parameterSetForMeasurement()
				.numberOfIteration(); i++, size += this.parameterSetForMeasurement().incrementSize()) {
			long sumOfDurations = 0;
			for (int repeated = 0; repeated < this.parameterSetForMeasurement().numberOfRepetitionOfSingleSort();
					repeated++) 
			{
				Integer[] currentList = Experiment.copyListOfGivenSize(list, size);
				sumOfDurations += Experiment.durationOfSinglSort(sort, currentList);
			}
			durations[i] = sumOfDurations / this.parameterSetForMeasurement().numberOfRepetitionOfSingleSort();
			Experiment.showDebugMessage("[Debug.Experiment] " + sort.toString() + " (" + i + ")\n");
		}
		return durations;
	}

}
