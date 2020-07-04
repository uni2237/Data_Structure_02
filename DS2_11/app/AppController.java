package app;

import experiment.ExperimentManagerForThreeSorts;
import experiment.ExperimentManager;
import experiment.ExperimentManagerForQuickSorts;
import experiment.ExperimentManagerForQuickSortWithInsertionSort;
import experiment.ListOrder;

//MVC설계 중 C Controller에 해당하며 실질적인 app을 컨트롤 하는 역할을 한다.
public class AppController {

	// Private variable
	private ExperimentManagerForThreeSorts _managerForThreeSorts;
	private ExperimentManagerForQuickSorts _managerForQuickSorts;
	private ExperimentManagerForQuickSortWithInsertionSort _managerForQuickSortWithInsertionSort;

	// Getters & Setters
	private ExperimentManagerForThreeSorts managerForThreeSorts() {
		return this._managerForThreeSorts;
	}

	private void setManagerForThreeSorts(ExperimentManagerForThreeSorts newExperimentManager) {
		this._managerForThreeSorts = newExperimentManager;
	}

	private ExperimentManagerForQuickSorts managerForQuickSorts() {
		return this._managerForQuickSorts;
	}

	private void setManagerForQuickSorts(ExperimentManagerForQuickSorts newExperimentManager) {
		this._managerForQuickSorts = newExperimentManager;
	}

	private ExperimentManagerForQuickSortWithInsertionSort managerForQuickSortWithInsertionSort() {
		return this._managerForQuickSortWithInsertionSort;
	}

	private void setManagerForQuickSortWithInsertionSort(
			ExperimentManagerForQuickSortWithInsertionSort newExperimentManager) {
		this._managerForQuickSortWithInsertionSort = newExperimentManager;
	}

	// private methods
	private void showTableTitle(ListOrder anOrder) {
		AppView.outputLine("> " + anOrder.orderName() + " 데이터에 대한 측정: ");
	}

	private void showTableHeadForThreeSorts() {
		AppView.outputLine(String.format("%8s", " ") + String.format("%26s", "<Insertion Sort>")
				+ String.format("%26s", "  <Quick Sort>  ") + String.format("%26s", "  <Heap Sort>"));
		AppView.outputLine(String.format("%7s", "데이터 크기") + String.format("%26s", "Measure (Estimate)")
				+ String.format("%26s", "Measure (Estimate)") + String.format("%26s", "Measure (Estimate)"));
	}

	private void showTableHeadForQuickSorts() {
		AppView.outputLine(String.format("%7s ", "  데이터 크기     ") + String.format("%17s", "         <Pivot Left>")
				+ String.format("%17s", "    <Pivot Mid>") + String.format("%17s", "    <Pivot Median>")
				+ String.format("%17s", "    <Pivot Random>") + String.format("%17s", "    <Insertion Sort>"));
	}

	private void showTableContentForThreeSorts() {
		for (int iteration = 0; iteration < this.managerForThreeSorts().parameterSetForMeasurement()
				.numberOfIteration(); iteration++) {
			int startingSize = this.managerForThreeSorts().parameterSetForMeasurement().startingSize();
			int incrementSize = this.managerForThreeSorts().parameterSetForMeasurement().incrementSize();
			int sortingSize = startingSize + (incrementSize * iteration);
			AppView.outputLine("[" + String.format("%7d", sortingSize) + "]"
					+ String.format("%15d", this.managerForThreeSorts().measurementForInsertionSortAt(iteration)) + " ("
					+ String.format("%8d", this.managerForThreeSorts().estimationForInsertionSortAt(iteration)) + ")"
					+ String.format("%15d", this.managerForThreeSorts().measurementForQuickSortAt(iteration)) + " ("
					+ String.format("%8d", this.managerForThreeSorts().estimationForQuickSortAt(iteration)) + ")"
					+ String.format("%15d", this.managerForThreeSorts().measurementForHeapSortAt(iteration)) + " ("
					+ String.format("%8d", this.managerForThreeSorts().estimationForHeapSortAt(iteration)) + ")");
		}
	}

	private void showTableHeadForQuickSortWithInsertionSort() {
		AppView.output(String.format("%7s  ", "데이터 크기") + String.format("%12s", "<Pivot Random>"));
		int numberOfIteration = this.managerForQuickSortWithInsertionSort().parameterSetForMaxSizeOfInsertionSort()
				.numberOfIteration();
		for (int iterationOfMaxSize = 0; iterationOfMaxSize < numberOfIteration; iterationOfMaxSize++) {
			int startingMaxSize = this.managerForQuickSortWithInsertionSort().parameterSetForMaxSizeOfInsertionSort()
					.startingSize();
			int IncrementSizeOfMaxSize = this.managerForQuickSortWithInsertionSort()
					.parameterSetForMaxSizeOfInsertionSort().incrementSize();

			int maxSortingSize = startingMaxSize + IncrementSizeOfMaxSize * iterationOfMaxSize;
			AppView.output(String.format("  <Size%3d>", maxSortingSize));
		}
		AppView.outputLine("");
	}

	private void showTableContentForQuickSortWithInsertionSort() {
		int numberOfIteration = this.managerForQuickSortWithInsertionSort().parameterSetForMeasurement()
				.numberOfIteration();
		for (int iteration = 0; iteration < numberOfIteration; iteration++) {
			int startingSize = this.managerForQuickSortWithInsertionSort().parameterSetForMeasurement().startingSize();
			int incrementSize = this.managerForQuickSortWithInsertionSort().parameterSetForMeasurement()
					.incrementSize();
			int sortingSize = startingSize + (incrementSize * iteration);
			AppView.output("[" + String.format("%7d", sortingSize) + "]");
			AppView.output(String.format("%14d",
					this.managerForQuickSortWithInsertionSort().measurementForQuickSortByPivotRandomAt(iteration)));
			int numberOfIterationOfMaxSize = this.managerForQuickSortWithInsertionSort()
					.parameterSetForMaxSizeOfInsertionSort().numberOfIteration();
			for (int iterationOfMaxSize = 0; iterationOfMaxSize < numberOfIterationOfMaxSize; iterationOfMaxSize++) {
				AppView.output(String.format("%11d", this.managerForQuickSortWithInsertionSort()
						.measurementForQuickSortWithInsertionSortAt(iterationOfMaxSize, iteration)));
			}
			AppView.outputLine("");
		}
	}

	private void showTableContentForQuickSorts() {
		for (int iteration = 0; iteration < this.managerForQuickSorts().parameterSetForMeasurement()
				.numberOfIteration(); iteration++) {
			int startingSize = this.managerForQuickSorts().parameterSetForMeasurement().startingSize();
			int incrementSize = this.managerForQuickSorts().parameterSetForMeasurement().incrementSize();
			int sortingSize = startingSize + (incrementSize * iteration);
			AppView.outputLine("[" + String.format("%7d", sortingSize) + "]"
					+ String.format("%17d", this.managerForQuickSorts().measurementForQuickSortByPivotLeftAt(iteration))
					+ String.format("%17d", this.managerForQuickSorts().measurementForQuickSortByPivotMidAt(iteration))
					+ String.format("%17d",
							this.managerForQuickSorts().measurementForQuickSortByPivotMedianAt(iteration))
					+ String.format("%17d",
							this.managerForQuickSorts().measurementForQuickSortByPivotRandomAt(iteration))
					+ String.format("%17d",
							this.managerForQuickSorts().measurementForQuickSortWithInsertionSortAt(iteration)));
		}
	}

	private void showResultTableThreeSorts(ListOrder anOrder) {
		this.showTableTitle(anOrder);
		this.showTableHeadForThreeSorts();
		this.showTableContentForThreeSorts();
		AppView.outputLine("");
	}

	private void showResultTableQuickSorts(ListOrder anOrder) {
		this.showTableTitle(anOrder);
		this.showTableHeadForQuickSorts();
		this.showTableContentForQuickSorts();
		AppView.outputLine("");
	}

	private void showResultTableQuickSortWithInsertionSort(ListOrder anOrder) {
		this.showTableTitle(anOrder);
		this.showTableHeadForQuickSortWithInsertionSort();
		this.showTableContentForQuickSortWithInsertionSort();
		AppView.outputLine("");
	}

	private void measureAndShow(ExperimentManager anExperimentMager, ListOrder anOrder) {
		anExperimentMager.performExperiment(anOrder);
		if (anExperimentMager.getClass().equals(ExperimentManagerForThreeSorts.class)) {
			this.showResultTableThreeSorts(anOrder);
		} else if (anExperimentMager.getClass().equals(ExperimentManagerForQuickSorts.class)) {
			this.showResultTableQuickSorts(anOrder);
		}
		// 추가 구현
		else {
			this.showResultTableQuickSortWithInsertionSort(anOrder);
		}
	}

	public void run() {
		AppView.outputLine("<<< 정렬 성능 비교 프로그램을 시작합니다 >>>");
		AppView.outputLine("");
		{
			AppView.outputLine(">> 3 가지 정렬의 성능 비교: 삽입, 퀵, 힢 <<");
			this.setManagerForThreeSorts(new ExperimentManagerForThreeSorts());
			this.managerForThreeSorts().prepareExperiment(null);

			this.measureAndShow(this.managerForThreeSorts(), ListOrder.Random);
			this.measureAndShow(this.managerForThreeSorts(), ListOrder.Ascending);
			this.measureAndShow(this.managerForThreeSorts(), ListOrder.Descending);
		}
		{
			AppView.outputLine(">> 5 가지 퀵 정렬 버전의 성능 비교 <<");
			this.setManagerForQuickSorts(new ExperimentManagerForQuickSorts());
			this.managerForQuickSorts().prepareExperiment(null);

			this.measureAndShow(this.managerForQuickSorts(), ListOrder.Random);
			this.measureAndShow(this.managerForQuickSorts(), ListOrder.Ascending);
			this.measureAndShow(this.managerForQuickSorts(), ListOrder.Descending);
		}
		{
			AppView.outputLine(">> 삽입 정렬을 사용하는 퀵 정렬의 성능: 삽입 정렬을 실행하는 크기별 성능을 비교 <<");
			this.setManagerForQuickSortWithInsertionSort(new ExperimentManagerForQuickSortWithInsertionSort());
			this.managerForQuickSortWithInsertionSort().prepareExperiment(null);

			this.measureAndShow(this.managerForQuickSortWithInsertionSort(), ListOrder.Random);

			this.managerForQuickSortWithInsertionSort().parameterSetForMaxSizeOfInsertionSort().setStartingSize(15);
			this.managerForQuickSortWithInsertionSort().parameterSetForMaxSizeOfInsertionSort().setIncrementSize(1);
			this.managerForQuickSortWithInsertionSort().parameterSetForMaxSizeOfInsertionSort()
					.setNumberOfIteration(11);
			this.measureAndShow(this.managerForQuickSortWithInsertionSort(), ListOrder.Random);

		}
		AppView.outputLine("<<< 정렬 성능 비교 프로그램을 종료합니다 >>>");
	}
}