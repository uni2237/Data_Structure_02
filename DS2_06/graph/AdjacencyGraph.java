package graph;


public abstract class AdjacencyGraph<E extends Edge> implements Graph<E> {

	//Constants
	protected static final int EDGE_EXIST = 1;
	protected static final int EDGE_NONE = 0;

	// Prviate instance variables
	private int _numberOfVertices;
	private int _numberOfEdges;
	


	//public getters & setters
	public int numberOfVertices() {
		return this._numberOfVertices;
	}
	
	public int numberOfEdges() {
		return this._numberOfEdges;
	}
	
	// protected getters & setters
	protected void setNumberOfEdges(int newNumberOfEdges) {
		this._numberOfEdges = newNumberOfEdges;
	}
	
	protected void setNumberOfVertices(int newNumberOfVertices) {
		this._numberOfVertices = newNumberOfVertices;
	}

	// �Է¹��� vertex�� ��ȿ�� ������ ������ üũ�ϴ� �Լ�
	public boolean vertexDoesExist(int aVertex) {
		return ((aVertex >= 0) && (aVertex < this.numberOfVertices()) );
	}
	
	public boolean edgeIsValid(int aTailVertex, int aHeadVertex){
		return (this.vertexDoesExist(aTailVertex) &&
				this.vertexDoesExist(aHeadVertex) );
	}
	
	public boolean edgeIsValid(E anEdge){
		if(anEdge != null){
		return (this.vertexDoesExist(anEdge.tailVertex()) &&
				this.vertexDoesExist(anEdge.headVertex()) );
	}
	return false;
	}


}
