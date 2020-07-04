package app;

import graph.AdjacencyGraph;
import graph.WeightedDirectedAdjacencyListGraph;
import graph.WeightedEdge;
import list.Iterator;
import list.LinkedStack;
import shortestPaths.ShortestPaths;

// MVC설계 중 C Controller에 해당하며 실질적인 app을 컨트롤 하는 역할을 한다.
public class AppController {

	// Private variable
	private AdjacencyGraph<WeightedEdge> _graph;
	private ShortestPaths<WeightedEdge> _shortestPaths;

	// Getters & Setters
	private AdjacencyGraph<WeightedEdge> graph() {
		return this._graph;
	}

	private void setGraph(AdjacencyGraph<WeightedEdge> newGraph) {
		this._graph = newGraph;
	}
	
	public ShortestPaths<WeightedEdge> shortestPaths(){
		return this._shortestPaths;
	}
	
	public void setShortestPaths(ShortestPaths<WeightedEdge> newShortestPaths){
		this._shortestPaths = newShortestPaths;
	}

	// Constructor
	public AppController() {
		this.setGraph(null);
		this.setShortestPaths(new ShortestPaths<WeightedEdge>());
	}

	private void inputAndMakeGraph() {
		AppView.outputLine("> 입력할 그래프의 vertex 수와 edge 수를 먼저 입력해야 합니다.");
		int numberOfVertices = this.inputNumberOfVertices();
		
		//vertex가 1개인 경우 예외처리
//		while(numberOfVertices == 1){
//			AppView.outputLine("> vertex 개수가 1일 경우 edge 수가  0개 이외에 불가능하여 최단경로 검사가 불가능합니다.");
//			numberOfVertices = this.inputNumberOfVertices();
//		}
		this.setGraph(new WeightedDirectedAdjacencyListGraph<WeightedEdge>(numberOfVertices));

		int numberOfEdges = this.inputNumberOfEdges();
		
		//예외처리 edge의 개수가 최대 edge수보다 많은 경우
		while(numberOfEdges>(numberOfVertices*(numberOfVertices-1))){
			AppView.outputLine("[오류] edge 수가 vertex개수에 따른 최대 edge 수를 벗어났습니다.");
			numberOfEdges = this.inputNumberOfEdges();

		}
		AppView.outputLine("");
		AppView.outputLine("> 이제부터 edge 를 주어진 수 만큼 입력합니다.");

		// for (int edgeCount = 0; edgeCount < numberOfEdges; edgeCount++){
		int edgeCount = 0;
		while (edgeCount < numberOfEdges) {
			WeightedEdge edge = this.inputEdge();
			if (this.graph().edgeDoesExist(edge)) {
				AppView.outputLine("[오류] 입력된 edge (" + edge.tailVertex() + "," + edge.headVertex() + ", ("
						+ edge.weight() + ")) 는 그래프에 이미 존재합니다.");
			} else {
				edgeCount++;
				this.graph().addEdge(edge);
				AppView.outputLine("!새로운 edge (" + edge.tailVertex() + "," + edge.headVertex() + ", (" + edge.weight()
						+ ")) 가 그래프에 삽입되었습니다.");
			}
		}

	}
	
	private int inputSourceVertex(){
		int sourceVertex = AppView.inputSourceVertex();
		while(! this.graph().vertexDoesExist(sourceVertex)){
			AppView.outputLine("[오류] 입력된 출발 vertex는 존재하지 않습니다: " + sourceVertex);
			sourceVertex = AppView.inputSourceVertex();
		}
		return sourceVertex;
		}

	
	
	private void solveAndShowShortestPaths(){
	
		AppView.outputLine("");
		AppView.outputLine("> 주어진 그래프에서 최단 경로를 찾습니다:");
		if(this.graph().numberOfVertices() <= 1){
			AppView.outputLine("[오류] vertex 수 (" + this.graph().numberOfVertices()+
					") 가 너무 적어서, 최단경로 찾기를 하지 않습니다. 2 개 이상이어야 합니다.");
		}
		else{
			AppView.outputLine("> 출발점을 입력해야 합니다:");
			int sourceVertex = this.inputSourceVertex();
			if(this.shortestPaths().solve(this.graph(), sourceVertex)){
				AppView.outputLine("");
				AppView.outputLine("> 최단 경로별 배용과 경로는 다음과 같습니다:");
				AppView.outputLine("출발점=" + sourceVertex + ":");
				for(int destination = 0; destination < this.graph().numberOfVertices(); destination++){
					if(destination != sourceVertex){
						AppView.output(" [목적점=" + destination + "] ");
						//component 관련 예외처리
						if(this.shortestPaths().minCostOfPathToDestination(destination) >= Integer.MAX_VALUE/2){
							AppView.outputLine("연결된 경로가 없습니다.");
						}
						else{
						AppView.output("최소비용=" + this.shortestPaths().minCostOfPathToDestination(destination) + ", ");
						AppView.output("경로:");
						LinkedStack<Integer> pathToDestination = this.shortestPaths().pathToDestination(destination);
						LinkedStack<Integer>.IteratorForLinkedStack iterator = pathToDestination.iterator();
						while(iterator.hasNext()){
							AppView.output(" -> " + iterator.next());
						}
						AppView.outputLine("");
					}
					}
				}
				
			}
			else {
				AppView.outputLine("[오류] 최단경로 찾기를 실패하였습니다.");
			}
		}
	}

	private void showGraph() {
		AppView.outputLine("");
		AppView.outputLine("> 입력된 그래프는 다음과 같습니다:");
		for (int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++) {
			AppView.output("[" + tailVertex + "] ->");
			Iterator<WeightedEdge> neighborIterator =
					this.graph().neighborIteratorOf(tailVertex);
			while(neighborIterator.hasNext()){
				WeightedEdge nextEdge = neighborIterator.next();
				AppView.output(" " + nextEdge.headVertex());
				AppView.output("(" + nextEdge.weight() + ")");
			}
			AppView.outputLine("");
		}
		
		
	}

	private int inputNumberOfVertices() {
		int numberOfVertices = AppView.inputNumberOfVertices();
		while (numberOfVertices <= 0) {
			AppView.outputLine("[오류] vertex 수는 0보다 커야 합니다: " + numberOfVertices);
			numberOfVertices = AppView.inputNumberOfVertices();
		}
		return numberOfVertices;
	}

	private int inputNumberOfEdges() {
		int numberOfEdges = AppView.inputNumberOfEdges();
		while (numberOfEdges < 0) {
			AppView.outputLine("[오류] edge 수는 0보다 크거나 같아야 합니다: " + numberOfEdges);
			numberOfEdges = AppView.inputNumberOfVertices();
		}
		return numberOfEdges;
	}
	
	// Cost의 input을 양수로 하기위한 method
	private int inputCost() {
		int inputcost = AppView.inputCost();
		while (inputcost < 0) {
			AppView.outputLine("[오류] cost 값은 0보다 커야 합니다: " + inputcost);
			inputcost = AppView.inputCost();
		}
		return inputcost;
	}

	private WeightedEdge inputEdge() {
		do {
			AppView.outputLine("- 입력할 edge의 두 vertex와 cost를 차례로 입력해야 합니다.");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();
			int cost = this.inputCost(); //cost 예외처리

			if (this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)) {
				if (tailVertex == headVertex) {
					AppView.outputLine("[오류] 두 vertex 번호가 동일합니다.");
				} else {
					return (new WeightedEdge(tailVertex, headVertex, cost));
				}
			} else {
				if (!this.graph().vertexDoesExist(tailVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 tail vertex 입니다 : " + tailVertex);
				}
				if (!this.graph().vertexDoesExist(headVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 head vertex 입니다 : " + headVertex);
				}
				if (cost < 0) {
					AppView.outputLine("[오류] edge의 비용은 양수이어야 합니다 : " + headVertex);
				}
			}
		} while (true);
	}

	public void run() {
		// TODO Auto-generated method stub
		AppView.outputLine("<<< 최단경로 찾기 프로그램을 시작합니다 >>>");
		this.inputAndMakeGraph();
		this.showGraph();


		this.solveAndShowShortestPaths();
		AppView.outputLine("");
		AppView.outputLine("<<< 최단경로 찾기 프로그램을 종료합니다 >>>");

	}

}
