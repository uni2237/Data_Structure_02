package experiment;

import app.AppView;

public abstract class ExperimentManager {

	private static final boolean DEBUG_MODE = false;

	private static void showDebugMessage(String aMessage) {
		if (ExperimentManager.DEBUG_MODE) {
			AppView.outputDebugMessage(aMessage);
		}
	}

	// Constants for the experiment parameters
	protected static final int DEFAULT_NUMBER_OF_ITERATION = 10;
	protected static final int DEFAULT_INCREMENT_SIZE = 1000;
	protected static final int DEFAULT_STARTING_SIZE = DEFAULT_INCREMENT_SIZE;

	protected static final int DEFAULT_NUMBER_OF_REPETITION_OF_SINGLE_SORT = 1;

	// private instance variables
	private ExperimentDataSet _dataSet;
	private Experiment _experiment;
	private ParameterSetForMeasurement _parameterSetForMeasurement;

	// Getters & Setters
	public ExperimentDataSet dataSet() {
		return this._dataSet;
	}

	protected void setDataSet(ExperimentDataSet newDataSet) {
		this._dataSet = newDataSet;
	}

	protected Experiment experiment() {
		return this._experiment;
	}

	protected void setExperiment(Experiment newExperiment) {
		this._experiment = newExperiment;
	}

	public ParameterSetForMeasurement parameterSetForMeasurement() {
		return this._parameterSetForMeasurement;
	}

	protected void setParameterSetForMeasurement(ParameterSetForMeasurement newParameterSet) {
		this._parameterSetForMeasurement = newParameterSet;
	}

	// Private method
	protected void prepareDataSet() {
		this.setDataSet(new ExperimentDataSet(this.parameterSetForMeasurement().maxDataSize()));
	}

	// Protected methods
	protected void setParameterSetWithDefaults() {
		this.setParameterSetForMeasurement(new ParameterSetForMeasurement(DEFAULT_STARTING_SIZE,
				DEFAULT_NUMBER_OF_ITERATION, DEFAULT_INCREMENT_SIZE, DEFAULT_NUMBER_OF_REPETITION_OF_SINGLE_SORT));
	}

	// protected method
	protected abstract void performMeasuring(ListOrder anOrder);

	// Constructor
	public ExperimentManager() {
		this.setParameterSetWithDefaults();
		showDebugMessage("super - ExperimentManager");
	}

	// public methods
	public void prepareExperiment(ParameterSetForMeasurement aParameterSet) {
		if (aParameterSet != null) {
			this.setParameterSetForMeasurement(aParameterSet);
		}
		this.setExperiment(new Experiment(this.parameterSetForMeasurement()));

		this.prepareDataSet();

		this.performMeasuring(ListOrder.Random);
	}

	public abstract void performExperiment(ListOrder anOrder);

}
