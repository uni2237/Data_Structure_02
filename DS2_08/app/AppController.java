package app;

import equivalenceClasses.EquivalenceClasses;
import graph.AdjacencyGraph;
import graph.Edge;
import graph.UndirectedAdjacencyListGraph;
import list.Iterator;
import list.List;

public class AppController {
	// Private instance variables
	private AdjacencyGraph<Edge> _graph;
	private EquivalenceClasses<Edge> _equivalenceClasses;

	private AdjacencyGraph<Edge> graph() {
		return this._graph;

	}

	private void setGraph(AdjacencyGraph<Edge> newGraph) {
		this._graph = newGraph;
	}

	private EquivalenceClasses<Edge> equivalenceClasses() {
		return this._equivalenceClasses;
	}

	private void setEquivalenceClasses(EquivalenceClasses<Edge> newEquivalenceClasses) {
		this._equivalenceClasses = newEquivalenceClasses;
	}

	public AppController() {
		this.setGraph(null);
		this.setEquivalenceClasses(new EquivalenceClasses<Edge>());

	}

	/*
	 * private void showSortedResult() { AppView.outputLine("");
	 * AppView.outputLine("> 위상정렬의 결과는 다음과 같습니다:"); List<Integer>
	 * topologicallySortedList = this.topologicalSort().topologicallySortedList();
	 * Iterator<Integer> iterator = topologicallySortedList.listIterator(); while
	 * (iterator.hasNext()) { int vertex = iterator.next(); AppView.output("->" +
	 * vertex + " "); } AppView.outputLine(""); }
	 */
	private Edge inputEdge() {
		do {
			AppView.outputLine("- 입력할 관계쌍의 두 원소를 차례로 입력해야 합니다.");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();

			if (this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)) {
				if (tailVertex == headVertex) {
					AppView.outputLine("[오류] 두 원소가 동일합니다.");
				} else {
					return (new Edge(tailVertex, headVertex));
				}
			} else {
				if (!this.graph().vertexDoesExist(tailVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 첫 번쨰 원소 입니다 : " + tailVertex);
				}
				if (!this.graph().vertexDoesExist(headVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 두 번째 원소 입니다 : " + headVertex);
				}

			}
		} while (true);
	}

	private void inputAndMakeGraph() {
		AppView.outputLine("> 입력할 동등 관계의 원소의 수와 관계 쌍의 수를 먼저 입력해야 합니다.");
		int numberOfVertices = this.inputNumberOfVertices();

		this.setGraph(new UndirectedAdjacencyListGraph<Edge>(numberOfVertices));

		int numberOfEdges = this.inputNumberOfEdges();
		// 예외처리
		while (numberOfEdges > (numberOfVertices * (numberOfVertices - 1))) {
			AppView.outputLine("[오류] 관계 쌍의 수가 원소의 개수에 따른 최대 관계 쌍의 수를 벗어났습니다.");
			numberOfEdges = this.inputNumberOfEdges();

		}
		AppView.outputLine("");
		AppView.outputLine("> 이제부터 관계 쌍을 주어진 수 만큼 입력합니다:");

		// for (int edgeCount = 0; edgeCount < numberOfEdges; edgeCount++){
		int edgeCount = 0;
		while (edgeCount < numberOfEdges) {
			Edge edge = this.inputEdge();
			if (this.graph().edgeDoesExist(edge)) {
				AppView.outputLine(
						"[오류] 입력된 관계 쌍 (" + edge.tailVertex() + "," + edge.headVertex() + ") 는 그래프에 이미 존재합니다.");
			} else {
				edgeCount++;
				this.graph().addEdge(edge);
				AppView.outputLine("!새로운 관계 쌍 (" + edge.tailVertex() + "," + edge.headVertex() + ") 가 그래프에 삽입되었습니다.");
			}
		}

	}

	private void showGraph() {
		AppView.outputLine("");
		AppView.outputLine("> 입력된 관계 쌍의 그래프는 다음과 같습니다:");
		for (int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++) {
			AppView.output("[" + tailVertex + "] ->");
			Iterator<Edge> neighborIterator = this.graph().neighborIteratorOf(tailVertex);
			while (neighborIterator.hasNext()) {
				Edge nextEdge = neighborIterator.next();
				AppView.output(" " + nextEdge.headVertex());

			}
			AppView.outputLine("");
		}

	}

	private int inputNumberOfVertices() {
		int numberOfVertices = AppView.inputNumberOfVertices();
		while (numberOfVertices <= 0) {
			AppView.outputLine("[오류] 원소의 개수는 0 보다 커야 합니다: " + numberOfVertices);
			numberOfVertices = AppView.inputNumberOfVertices();
		}
		return numberOfVertices;
	}

	private int inputNumberOfEdges() {
		int numberOfEdges = AppView.inputNumberOfEdges();
		while (numberOfEdges < 0) {
			AppView.outputLine("[오류] 관계쌍의 개수는 0보다 크거나 같아야 합니다: " + numberOfEdges);
			numberOfEdges = AppView.inputNumberOfEdges();
		}
		return numberOfEdges;
	}

	public void run() {
		AppView.outputLine("<<< 동등 클래스 찾기 프로그램을 시작합니다 >>>");
		this.inputAndMakeGraph();
		this.showGraph();
		if (this.equivalenceClasses().solve(this.graph())) {
			this.showEquivalenceClasses();

		} else {
			AppView.outputLine("");
			AppView.outputLine("[오류] 동등 클래스를 성공적으로 마치지 못했습니다.");

		}

		AppView.outputLine("");
		// 사이에 한 줄을 더 띄우기로 함
		AppView.outputLine("<<< 동등 클래스 찾기 프로그램을 종료합니다  >>>");
	}

	private void showEquivalenceClasses() {
		AppView.outputLine("");
		AppView.outputLine("> 찾아진 동등 클래스는 다음과 같습니다:"); // The next expression is independent of // the implementation of
														// the list for the "Equivalence Classes".
		Iterator<List<Integer>> equivalenceClassListIterator = this.equivalenceClasses().equivalenceClassList()
				.listIterator();
		for (int classOrder = 0; equivalenceClassListIterator.hasNext(); classOrder++) {
			AppView.output("[동등 클래스 " + String.format("%2d", classOrder) + "]");
			// The next expression is independent of // the implementation of the list for
			// the "Equivalence Class Elements".
			List<Integer> equivalenceClass = (List<Integer>) equivalenceClassListIterator.next();
			Iterator<Integer> equivalenceClassIterator = equivalenceClass.listIterator();
			AppView.output(String.format(" = {%2d", equivalenceClassIterator.next()));
			while (equivalenceClassIterator.hasNext()) {
				AppView.output(String.format(",%2d", equivalenceClassIterator.next()));
			}
			AppView.outputLine(" }");
		}
	}

}
