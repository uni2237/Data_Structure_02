
public class AppController {
	// Private instance variables
	private AdjacencyMatrixGraph _graph;

	// Getter & Setter
	private AdjacencyMatrixGraph graph() {
		return this._graph;
	}

	private void setGraph(AdjacencyMatrixGraph newGraph) {
		this._graph = newGraph;
	}

	public AppController() {
		this.setGraph(null); // AppController 객체 생성 시점에, 그래프 객체는 아직 존재하지 않음
	}
	

	private void inputAndMakeGraph() {
		AppView.outputLine(">입력할 그래프의 vertex수와 edge 수를 먼저 입력해야 합니다:");
		int numberOfVertices = this.inputNumberOfVertices();
		this.setGraph(new AdjacencyMatrixGraph(numberOfVertices));

		int numberOfEdges = this.inputNumberOfEdges();
		AppView.outputLine("");
		AppView.outputLine(">이제부터 edge를 주어진 수 만큼 입력합니다.");

		int edgeCount = 0;
		while (edgeCount < numberOfEdges) {
			Edge edge = this.inputEdge();
			if (this.graph().edgeDoesExist(edge)) {
				AppView.outputLine(
						"(오류) 입력된 edge (" + edge.tailVertex() + "," + edge.headVertex() + ")는 그래프에 이미 존재합니다.");
			} else {
				edgeCount++;
				this.graph().addEdge(edge);
				AppView.outputLine("!새로운 edge (" + edge.tailVertex() + "," + edge.headVertex() + ")가 그래프에 삽입되었습니다.");
			}
		}
	}

	private int inputNumberOfVertices() {
		int numberOfVertices = AppView.inputNumberOfVertices();
		while (numberOfVertices <= 0) {
			AppView.outputLine("[오류] vertex 수는 0 보다 커야 합니다; " + numberOfVertices);
		}
		return numberOfVertices;
	}

	private int inputNumberOfEdges() {
		int numberOfEdges = AppView.inputNumberOfEdges();
		while (numberOfEdges < 0) {
			AppView.outputLine("[오류] vertex 수는 0보다 크거나 같아야 합니다: " + numberOfEdges);
		}
		return numberOfEdges;
	}

	private Edge inputEdge() {
		do {
			AppView.outputLine("- 입력할 edge의 두 vertex를 차례로 입력해야 합니다:");
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
					AppView.outputLine("[오류] 존재하지 않는 tail vertex 입니다: " + tailVertex);
				}
				if (!this.graph().vertexDoesExist(headVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 head vertex 입니다: " + headVertex);
				}
			}
		} while (true);

	}

	private void showGraph() {
		AppView.outputLine("");
		AppView.outputLine(">입력된 그래프는 다음과 같습니다:");
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
		AppView.outputLine("<<< 입력되는 그래프의 사이클 검사를 시작합니다 >>>");
		this.inputAndMakeGraph();
		this.showGraph();
		AppView.outputLine("");
		// 사이에 한 줄을 더 띄우기로 함
		AppView.outputLine("<<< 그래프의 입력과 사이클 검사를 종료합니다 >>>");
	}
}
