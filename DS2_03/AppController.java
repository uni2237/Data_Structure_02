
public class AppController {
	// Private instance variables
	private AdjacencyMatrixGraph _graph;
	private Coloring _coloring;

	private Coloring coloring() {
		return this._coloring;
	}

	private void setColoring(Coloring aColoring) {
		this._coloring = aColoring;
	}

	// Getter & Setter
	private AdjacencyMatrixGraph graph() {
		return this._graph;
	}

	private void setGraph(AdjacencyMatrixGraph newGraph) {
		this._graph = newGraph;
	}

	/*
	 * private PairwiseDisjointSets pairwiseDisjointSets() { return
	 * this._pairwiseDisjointSets; }
	 * 
	 * private void setPairwiseDisjointSets(PairwiseDisjointSets
	 * newPairwiseDisjointSets) { this._pairwiseDisjointSets =
	 * newPairwiseDisjointSets; }
	 */
	public AppController() {
		this.setGraph(null); // AppController ��ü ���� ������, �׷��� ��ü�� ���� �������� ����
		this.setColoring(null);
	}

	private void inputAndMakeGraph() {
		AppView.outputLine(">�Է��� �׷����� vertex���� edge ���� ���� �Է��ؾ� �մϴ�:");
		int numberOfVertices = this.inputNumberOfVertices();
		this.setGraph(new AdjacencyMatrixGraph(numberOfVertices));

		int numberOfEdges = this.inputNumberOfEdges();
		AppView.outputLine("");
		AppView.outputLine(">�������� edge�� �־��� �� ��ŭ �Է��մϴ�.");

		int edgeCount = 0;
		while (edgeCount < numberOfEdges) {
			Edge edge = this.inputEdge();
			if (this.graph().edgeDoesExist(edge)) {
				AppView.outputLine(
						"(����) �Էµ� edge (" + edge.tailVertex() + "," + edge.headVertex() + ")�� �׷����� �̹� �����մϴ�.");
			} else {
				edgeCount++;
				this.graph().addEdge(edge);
				AppView.outputLine("!���ο� edge (" + edge.tailVertex() + "," + edge.headVertex() + ")�� �׷����� ���ԵǾ����ϴ�.");

			}
		}
	}

	private int inputNumberOfVertices() {
		int numberOfVertices = AppView.inputNumberOfVertices();
		while (numberOfVertices <= 0) {
			AppView.outputLine("[����] vertex ���� 0 ���� Ŀ�� �մϴ�; " + numberOfVertices);
		}
		return numberOfVertices;
	}

	private int inputNumberOfEdges() {
		int numberOfEdges = AppView.inputNumberOfEdges();
		while (numberOfEdges < 0) {
			AppView.outputLine("[����] vertex ���� 0���� ũ�ų� ���ƾ� �մϴ�: " + numberOfEdges);
		}
		return numberOfEdges;
	}

	private Edge inputEdge() {
		do {
			AppView.outputLine("- �Է��� edge�� �� vertex�� ���ʷ� �Է��ؾ� �մϴ�:");
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
					AppView.outputLine("[����] �������� �ʴ� tail vertex �Դϴ�: " + tailVertex);
				}
				if (!this.graph().vertexDoesExist(headVertex)) {
					AppView.outputLine("[����] �������� �ʴ� head vertex �Դϴ�: " + headVertex);
				}
			}
		} while (true);

	}

	private void showGraph() {
		AppView.outputLine("");
		AppView.outputLine(">�Էµ� �׷����� ������ �����ϴ�:");
		for (int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++) {
			AppView.output("[" + tailVertex + "] ->");
			for (int headVertex = 0; headVertex < this.graph().numberOfVertices(); headVertex++) {
				if (this.graph().edgeDoesExist(new Edge(tailVertex, headVertex))) {
					AppView.output(" " + headVertex);
				}
			}

			AppView.outputLine("");

		}

	}

	private void showColoring() {
		AppView.outputLine("");
		AppView.outputLine("> �� vertex�� ĥ���� ������ ������ �����ϴ�:");
		for (int vertex = 0; vertex < this.graph().numberOfVertices(); vertex++) {
			AppView.outputLine("[" + vertex + "} " + this.coloring().vertexColor(vertex).name());

		}
		AppView.outputLine("");
		AppView.outputLine("> �� �� vertex�� ������ ���� edge���� ������ �����ϴ�:");
		if (this.coloring().sameColorEdges().size() == 0) {
			AppView.outputLine("!! ��� edge�� �� �� vertex�� ������ �ٸ��ϴ�.");
		} else {
			LinkedList<Edge>.IteratorForLinkedList iterator = this.coloring().sameColorEdges().iterator();
			while (iterator.hasNext()) {
				Edge currentEdge = iterator.next();
				AppView.output("(" + currentEdge.tailVertex() + "," + currentEdge.headVertex() + "):");
				AppView.outputLine(" " + this.coloring().vertexColor(currentEdge.tailVertex()).name());
			}
		}
	}

	public void run() {
		AppView.outputLine("<<< �׷��� ��ĥ�ϱ⸦ �����մϴ� >>>");
		this.inputAndMakeGraph();
		this.showGraph();
		this.setColoring(new Coloring(this.graph()));
		this.coloring().runColoring();
		this.showColoring();

		AppView.outputLine("");
		// ���̿� �� ���� �� ����� ��
		AppView.outputLine("<<< ��ĥ�ϱ⸦ �����մϴ� >>>");
	}
}
