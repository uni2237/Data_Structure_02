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
		AppView.outputLine("> ���������� ����� ������ �����ϴ�:");
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
			AppView.outputLine("- �Է��� edge�� �� vertex�� ���ʷ� �Է��ؾ� �մϴ�.");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();

			if (this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)) {
				if (tailVertex == headVertex) {
					AppView.outputLine("[����] �� vertex ��ȣ�� �����մϴ�.");
				} else {
					return (new Edge(tailVertex, headVertex));
				}
			} else {
				if (!this.graph().vertexDoesExist(tailVertex)) {
					AppView.outputLine("[����] �������� �ʴ� tail vertex �Դϴ� : " + tailVertex);
				}
				if (!this.graph().vertexDoesExist(headVertex)) {
					AppView.outputLine("[����] �������� �ʴ� head vertex �Դϴ� : " + headVertex);
				}

			}
		} while (true);
	}

	private void inputAndMakeGraph() {
		AppView.outputLine("> �Է��� �׷����� vertex ���� edge ���� ���� �Է��ؾ� �մϴ�.");
		int numberOfVertices = this.inputNumberOfVertices();

		this.setGraph(new DirectedAdjacencyListGraph<Edge>(numberOfVertices));

		int numberOfEdges = this.inputNumberOfEdges();

		AppView.outputLine("");
		AppView.outputLine("> �������� edge �� �־��� �� ��ŭ �Է��մϴ�:");

		// for (int edgeCount = 0; edgeCount < numberOfEdges; edgeCount++){
		int edgeCount = 0;
		while (edgeCount < numberOfEdges) {
			Edge edge = this.inputEdge();
			if (this.graph().edgeDoesExist(edge)) {
				AppView.outputLine(
						"[����] �Էµ� edge (" + edge.tailVertex() + "," + edge.headVertex() + ") �� �׷����� �̹� �����մϴ�.");
			} else {
				edgeCount++;
				this.graph().addEdge(edge);
				AppView.outputLine("!���ο� edge (" + edge.tailVertex() + "," + edge.headVertex() + ") �� �׷����� ���ԵǾ����ϴ�.");
			}
		}

	}

	private void showGraph() {
		AppView.outputLine("");
		AppView.outputLine("> �Էµ� �׷����� ������ �����ϴ�:");
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
			AppView.outputLine("[����] vertex ���� 0 ���� Ŀ�� �մϴ�: " + numberOfVertices);
			numberOfVertices = AppView.inputNumberOfVertices();
		}
		return numberOfVertices;
	}

	private int inputNumberOfEdges() {
		int numberOfEdges = AppView.inputNumberOfEdges();
		while (numberOfEdges < 0) {
			AppView.outputLine("[����] vertex ���� 0���� ũ�ų� ���ƾ� �մϴ�: " + numberOfEdges);
			numberOfEdges = AppView.inputNumberOfEdges();
		}
		return numberOfEdges;
	}

	public void run() {
		AppView.outputLine("<<< �������� ���α׷��� �����մϴ� >>>");
		this.inputAndMakeGraph();
		this.showGraph();
		if (this.topologicalSort().solve(this.graph())) {
			this.showSortedResult();
		} else {
			AppView.outputLine("");
			AppView.outputLine(" [����] ���������� ���������� ��ġ�� ���߽��ϴ�. �׷����� ����Ŭ�� �����մϴ�. ");
		}

		AppView.outputLine("");
		// ���̿� �� ���� �� ����� ��
		AppView.outputLine("<<< �������� ���α׷��� �����մϴ�  >>>");
	}
}
