package app;


import graph.WeightedUndirectedAdjacencyMatrixGraph;
import graph.WeightedEdge;

public class AppController {
	// Private instance variables
	private WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> _graph;

	// Getter & Setter
	private WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> graph() {
		return this._graph;
	}

	private void setGraph(WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> newGraph) {
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

	}

	private WeightedEdge inputEdge() {
		do {
			AppView.outputLine("- �Է��� edge�� �� vertex�� cost�� ���ʷ� �Է��ؾ� �մϴ�:");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();
			int cost = AppView.inputCost();
			if (this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)) {
				if (tailVertex == headVertex) {
					AppView.outputLine("[����] �� vertex ��ȣ�� �����մϴ�.");
				} else {
					return (new WeightedEdge(tailVertex, headVertex, cost));
				}
			} else {
				if (!this.graph().vertexDoesExist(tailVertex)) {
					AppView.outputLine("[����] �������� �ʴ� vertex �Դϴ�: " + tailVertex);
				}
				if (!this.graph().vertexDoesExist(headVertex)) {
					AppView.outputLine("[����] �������� �ʴ� vertex �Դϴ�: " + headVertex);
				}
				if (cost < 0) {
					AppView.outputLine("[����] edge�� ������ ����̾�� �մϴ�: " + cost);
				}
			}
		} while (true);

	}

	private void inputAndMakeGraph() {
		AppView.outputLine("> �Է��� �׷����� vertex ���� edge ���� ���� �Է��ؾ� �մϴ�:");
		int numberOfVertices = this.inputNumberOfVertices();
		this.setGraph(new WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge>(numberOfVertices));

		int numberOfEdges = this.inputNumberOfEdges();
		AppView.outputLine("");
		AppView.outputLine("> �������� edge�� �־��� �� ��ŭ �Է��մϴ�:");

		int edgeCount = 0;
		while (edgeCount < numberOfEdges) {
			WeightedEdge edge = this.inputEdge();
			if (this.graph().edgeDoesExist(edge)) {
				AppView.outputLine(
						"(����) �Էµ� edge (" + edge.tailVertex() + "," + edge.headVertex() + ")�� �׷����� �̹� �����մϴ�.");
			} else {
				edgeCount++;
				this.graph().addEdge(edge);
				AppView.outputLine("!���ο� edge (" + edge.tailVertex() + "," + edge.headVertex() + ", (" + edge.weight()
						+ ")) �� �׷����� ���ԵǾ����ϴ�.");

			}
		}
	}

	private void showGraph() {
		AppView.outputLine("");
		AppView.outputLine("> �Էµ� �׷����� ������ �����ϴ�:");
		for (int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++) {
			AppView.output("[" + tailVertex + "] ->");
			for (int headVertex = 0; headVertex < this.graph().numberOfVertices(); headVertex++) {
				if (this.graph().edgeDoesExist(tailVertex, headVertex)) {
					AppView.output(" " + headVertex);
					AppView.output("(" + this.graph().weightOfEdge(tailVertex, headVertex) + ")");

				}
			}

			AppView.outputLine("");

		}
		
		AppView.outputLine("");
		AppView.outputLine("> �Էµ� �׷����� Adjacency Matrix�� ������ �����ϴ�:");
		AppView.output("      ");
		for (int headVertex = 0; headVertex < this.graph().numberOfVertices(); headVertex++) {
			AppView.output(String.format(" [%s]", headVertex));

		}
		AppView.outputLine("");
		for (int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++) {
			AppView.output("[" + tailVertex + "] ->");
			for (int headVertex = 0; headVertex < this.graph().numberOfVertices(); headVertex++) {
				int weight = this.graph().weightOfEdge(tailVertex, headVertex);
				AppView.output(String.format("%4d", weight));
			}
			AppView.outputLine("");
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

	public void run() {
		AppView.outputLine("<<< �ּҺ�� Ȯ�� Ʈ�� ã�� ���α׷��� �����մϴ� >>>");
		this.inputAndMakeGraph();
		this.showGraph();

		AppView.outputLine("");
		// ���̿� �� ���� �� ����� ��
		AppView.outputLine("<<< �ּҺ�� Ȯ�� Ʈ�� ã�� ���α׷��� �����մϴ�  >>>");
	}
}