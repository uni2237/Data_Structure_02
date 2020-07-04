
public class AppController {
	// Private instance variables
	private AdjacencyMatrixGraph _graph;
	private PairwiseDisjointSets _pairwiseDisjointSets;

	// Getter & Setter
	private AdjacencyMatrixGraph graph() {
		return this._graph;
	}

	private void setGraph(AdjacencyMatrixGraph newGraph) {
		this._graph = newGraph;
	}

	private PairwiseDisjointSets pairwiseDisjointSets() {
		return this._pairwiseDisjointSets;
	}

	private void setPairwiseDisjointSets(PairwiseDisjointSets newPairwiseDisjointSets) {
		this._pairwiseDisjointSets = newPairwiseDisjointSets;
	}

	public AppController() {
		this.setGraph(null); // AppController ��ü ���� ������, �׷��� ��ü�� ���� �������� ����
		this.setPairwiseDisjointSets(null);
	}

	// vertex ���� �����Ͽ� PairwiseDisjointSets ��ü �����ϴ� �ϸ� ��
	private void initCycleDetection() {
		this.setPairwiseDisjointSets(new PairwiseDisjointSets(this.graph().numberOfVertices()));
	}

	// Edge�� ���ԵǾ�����, cycle�� ��������� ���� �˻��ϱ����� ������ �Լ� ���
	private boolean addedEdgeDoesMakeCycle(Edge anAddedEdge) {
		int tailVertex = anAddedEdge.tailVertex();
		int headVertex = anAddedEdge.headVertex();
		int setForTailVertex = this.pairwiseDisjointSets().find(tailVertex);
		int setForHeadVertex = this.pairwiseDisjointSets().find(headVertex);
		if (setForTailVertex == setForHeadVertex) {
			return true;

		} else {
			this.pairwiseDisjointSets().union(setForTailVertex, setForHeadVertex);
			return false;
		}

	}

	private void inputAndMakeGraph() {
		AppView.outputLine(">�Է��� �׷����� vertex���� edge ���� ���� �Է��ؾ� �մϴ�:");
		int numberOfVertices = this.inputNumberOfVertices();
		this.setGraph(new AdjacencyMatrixGraph(numberOfVertices));

		int numberOfEdges = this.inputNumberOfEdges();
		AppView.outputLine("");
		AppView.outputLine(">�������� edge�� �־��� �� ��ŭ �Է��մϴ�.");
		this.initCycleDetection();

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
				if (this.addedEdgeDoesMakeCycle(edge)) {
					AppView.outputLine("![Cycle] ���Ե� edge (" + edge.tailVertex() + ", " + edge.headVertex()
							+ ") �� �׷����� ����Ŭ�� ��������ϴ�.");
				}
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

	public void run() {
		AppView.outputLine("<<< �ԷµǴ� �׷����� ����Ŭ �˻縦 �����մϴ� >>>");
		this.inputAndMakeGraph();
		this.showGraph();
		AppView.outputLine("");
		// ���̿� �� ���� �� ����� ��
		AppView.outputLine("<<< �׷����� �Է°� ����Ŭ �˻縦 �����մϴ� >>>");
	}
}
