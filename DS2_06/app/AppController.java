package app;

import graph.AdjacencyGraph;
import graph.WeightedDirectedAdjacencyListGraph;
import graph.WeightedEdge;
import list.Iterator;
import list.LinkedStack;
import shortestPaths.ShortestPaths;

// MVC���� �� C Controller�� �ش��ϸ� �������� app�� ��Ʈ�� �ϴ� ������ �Ѵ�.
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
		AppView.outputLine("> �Է��� �׷����� vertex ���� edge ���� ���� �Է��ؾ� �մϴ�.");
		int numberOfVertices = this.inputNumberOfVertices();
		
		//vertex�� 1���� ��� ����ó��
//		while(numberOfVertices == 1){
//			AppView.outputLine("> vertex ������ 1�� ��� edge ����  0�� �̿ܿ� �Ұ����Ͽ� �ִܰ�� �˻簡 �Ұ����մϴ�.");
//			numberOfVertices = this.inputNumberOfVertices();
//		}
		this.setGraph(new WeightedDirectedAdjacencyListGraph<WeightedEdge>(numberOfVertices));

		int numberOfEdges = this.inputNumberOfEdges();
		
		//����ó�� edge�� ������ �ִ� edge������ ���� ���
		while(numberOfEdges>(numberOfVertices*(numberOfVertices-1))){
			AppView.outputLine("[����] edge ���� vertex������ ���� �ִ� edge ���� ������ϴ�.");
			numberOfEdges = this.inputNumberOfEdges();

		}
		AppView.outputLine("");
		AppView.outputLine("> �������� edge �� �־��� �� ��ŭ �Է��մϴ�.");

		// for (int edgeCount = 0; edgeCount < numberOfEdges; edgeCount++){
		int edgeCount = 0;
		while (edgeCount < numberOfEdges) {
			WeightedEdge edge = this.inputEdge();
			if (this.graph().edgeDoesExist(edge)) {
				AppView.outputLine("[����] �Էµ� edge (" + edge.tailVertex() + "," + edge.headVertex() + ", ("
						+ edge.weight() + ")) �� �׷����� �̹� �����մϴ�.");
			} else {
				edgeCount++;
				this.graph().addEdge(edge);
				AppView.outputLine("!���ο� edge (" + edge.tailVertex() + "," + edge.headVertex() + ", (" + edge.weight()
						+ ")) �� �׷����� ���ԵǾ����ϴ�.");
			}
		}

	}
	
	private int inputSourceVertex(){
		int sourceVertex = AppView.inputSourceVertex();
		while(! this.graph().vertexDoesExist(sourceVertex)){
			AppView.outputLine("[����] �Էµ� ��� vertex�� �������� �ʽ��ϴ�: " + sourceVertex);
			sourceVertex = AppView.inputSourceVertex();
		}
		return sourceVertex;
		}

	
	
	private void solveAndShowShortestPaths(){
	
		AppView.outputLine("");
		AppView.outputLine("> �־��� �׷������� �ִ� ��θ� ã���ϴ�:");
		if(this.graph().numberOfVertices() <= 1){
			AppView.outputLine("[����] vertex �� (" + this.graph().numberOfVertices()+
					") �� �ʹ� ���, �ִܰ�� ã�⸦ ���� �ʽ��ϴ�. 2 �� �̻��̾�� �մϴ�.");
		}
		else{
			AppView.outputLine("> ������� �Է��ؾ� �մϴ�:");
			int sourceVertex = this.inputSourceVertex();
			if(this.shortestPaths().solve(this.graph(), sourceVertex)){
				AppView.outputLine("");
				AppView.outputLine("> �ִ� ��κ� ���� ��δ� ������ �����ϴ�:");
				AppView.outputLine("�����=" + sourceVertex + ":");
				for(int destination = 0; destination < this.graph().numberOfVertices(); destination++){
					if(destination != sourceVertex){
						AppView.output(" [������=" + destination + "] ");
						//component ���� ����ó��
						if(this.shortestPaths().minCostOfPathToDestination(destination) >= Integer.MAX_VALUE/2){
							AppView.outputLine("����� ��ΰ� �����ϴ�.");
						}
						else{
						AppView.output("�ּҺ��=" + this.shortestPaths().minCostOfPathToDestination(destination) + ", ");
						AppView.output("���:");
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
				AppView.outputLine("[����] �ִܰ�� ã�⸦ �����Ͽ����ϴ�.");
			}
		}
	}

	private void showGraph() {
		AppView.outputLine("");
		AppView.outputLine("> �Էµ� �׷����� ������ �����ϴ�:");
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
			AppView.outputLine("[����] vertex ���� 0���� Ŀ�� �մϴ�: " + numberOfVertices);
			numberOfVertices = AppView.inputNumberOfVertices();
		}
		return numberOfVertices;
	}

	private int inputNumberOfEdges() {
		int numberOfEdges = AppView.inputNumberOfEdges();
		while (numberOfEdges < 0) {
			AppView.outputLine("[����] edge ���� 0���� ũ�ų� ���ƾ� �մϴ�: " + numberOfEdges);
			numberOfEdges = AppView.inputNumberOfVertices();
		}
		return numberOfEdges;
	}
	
	// Cost�� input�� ����� �ϱ����� method
	private int inputCost() {
		int inputcost = AppView.inputCost();
		while (inputcost < 0) {
			AppView.outputLine("[����] cost ���� 0���� Ŀ�� �մϴ�: " + inputcost);
			inputcost = AppView.inputCost();
		}
		return inputcost;
	}

	private WeightedEdge inputEdge() {
		do {
			AppView.outputLine("- �Է��� edge�� �� vertex�� cost�� ���ʷ� �Է��ؾ� �մϴ�.");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();
			int cost = this.inputCost(); //cost ����ó��

			if (this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)) {
				if (tailVertex == headVertex) {
					AppView.outputLine("[����] �� vertex ��ȣ�� �����մϴ�.");
				} else {
					return (new WeightedEdge(tailVertex, headVertex, cost));
				}
			} else {
				if (!this.graph().vertexDoesExist(tailVertex)) {
					AppView.outputLine("[����] �������� �ʴ� tail vertex �Դϴ� : " + tailVertex);
				}
				if (!this.graph().vertexDoesExist(headVertex)) {
					AppView.outputLine("[����] �������� �ʴ� head vertex �Դϴ� : " + headVertex);
				}
				if (cost < 0) {
					AppView.outputLine("[����] edge�� ����� ����̾�� �մϴ� : " + headVertex);
				}
			}
		} while (true);
	}

	public void run() {
		// TODO Auto-generated method stub
		AppView.outputLine("<<< �ִܰ�� ã�� ���α׷��� �����մϴ� >>>");
		this.inputAndMakeGraph();
		this.showGraph();


		this.solveAndShowShortestPaths();
		AppView.outputLine("");
		AppView.outputLine("<<< �ִܰ�� ã�� ���α׷��� �����մϴ� >>>");

	}

}
