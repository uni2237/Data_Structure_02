package app;

import topologicalSort.TopologicalSort;
import graph.AdjacencyGraph;
import graph.DirectedAdjacencyListGraph;
import list.Iterator;
import list.List;
import graph.Edge;

public class AppController {
	// Private instance variables
	private AdjacencyGraph<Edge> _graph;
	private TopologicalSort<Edge> _topologicalSort;

	// Getter & Setter
	private AdjacencyGraph<Edge> graph() {
		return this._graph;
	}

	private void setGraph(AdjacencyGraph<Edge> newGraph) {
		this._graph = newGraph;
	}

	private TopologicalSort<Edge> topologicalSort() {
		return this._topologicalSort;
	}

	private void setTopologicalSort(TopologicalSort<Edge> newTopologicalSort) {
		this._topologicalSort = newTopologicalSort;
	}

	public AppController() {
		this.setGraph(null);
		this.setTopologicalSort(new TopologicalSort<Edge>());

	}

	private void showSortedResult() {
		AppView.outputLine("");
		AppView.outputLine("> 위상정렬의 결과는 다음과 같습니다:");
		List<Integer> topologicallySortedList = this.topologicalSort().topologicallySortedList();
		Iterator<Integer> iterator = topologicallySortedList.listIterator();
		while (iterator.hasNext()) {
			int vertex = iterator.next();
			AppView.output("->" + vertex + " ");
		}
		AppView.outputLine("");
	}

	private Edge inputEdge() {
		do {
			AppView.outputLine("- 입력할 edge의 두 vertex를 차례로 입력해야 합니다.");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();

			if (this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)) {
				if (tailVertex == headVertex) {
					AppView.outputLine("[오류] 두 vertex 번호가 동일합니다.");
				} else {
					return (new Edge(tailVertex, headVertex));
				}
			} else {
				if (!this.graph().vertexDoesExist(tailVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 tail vertex 입니다 : " + tailVertex);
				}
				if (!this.graph().vertexDoesExist(headVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 head vertex 입니다 : " + headVertex);
				}

			}
		} while (true);
	}

	private void inputAndMakeGraph() {
		AppView.outputLine("> 입력할 그래프의 vertex 수와 edge 수를 먼저 입력해야 합니다.");
		int numberOfVertices = this.inputNumberOfVertices();

		this.setGraph(new DirectedAdjacencyListGraph<Edge>(numberOfVertices));

		int numberOfEdges = this.inputNumberOfEdges();

		AppView.outputLine("");
		AppView.outputLine("> 이제부터 edge 를 주어진 수 만큼 입력합니다:");

		// for (int edgeCount = 0; edgeCount < numberOfEdges; edgeCount++){
		int edgeCount = 0;
		while (edgeCount < numberOfEdges) {
			Edge edge = this.inputEdge();
			if (this.graph().edgeDoesExist(edge)) {
				AppView.outputLine(
						"[오류] 입력된 edge (" + edge.tailVertex() + "," + edge.headVertex() + ") 는 그래프에 이미 존재합니다.");
			} else {
				edgeCount++;
				this.graph().addEdge(edge);
				AppView.outputLine("!새로운 edge (" + edge.tailVertex() + "," + edge.headVertex() + ") 가 그래프에 삽입되었습니다.");
			}
		}

	}

	private void showGraph() {
		AppView.outputLine("");
		AppView.outputLine("> 입력된 그래프는 다음과 같습니다:");
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
			AppView.outputLine("[오류] vertex 수는 0 보다 커야 합니다: " + numberOfVertices);
			numberOfVertices = AppView.inputNumberOfVertices();
		}
		return numberOfVertices;
	}

	private int inputNumberOfEdges() {
		int numberOfEdges = AppView.inputNumberOfEdges();
		while (numberOfEdges < 0) {
			AppView.outputLine("[오류] vertex 수는 0보다 크거나 같아야 합니다: " + numberOfEdges);
			numberOfEdges = AppView.inputNumberOfEdges();
		}
		return numberOfEdges;
	}

	public void run() {
		AppView.outputLine("<<< 위상정렬 프로그램을 시작합니다 >>>");
		this.inputAndMakeGraph();
		this.showGraph();
		if (this.topologicalSort().solve(this.graph())) {
			this.showSortedResult();
		} else {
			AppView.outputLine("");
			AppView.outputLine(" [오류] 위상정렬을 성공적으로 마치지 못했습니다. 그래프에 사이클이 존재합니다. ");
		}

		AppView.outputLine("");
		// 사이에 한 줄을 더 띄우기로 함
		AppView.outputLine("<<< 위상정렬 프로그램을 종료합니다  >>>");
	}
}
