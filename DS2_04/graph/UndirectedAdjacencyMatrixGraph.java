package graph;

public class UndirectedAdjacencyMatrixGraph<E extends Edge> implements Graph<E> {
	private static final int EDGE_EXIST = 1;
	private static final int EDGE_NONE = 0;

	// Private instance variables
	private int _numberOfVertices;
	private int _numberOfEdges;
	private int[][] _adgacency;

	@Override
	public int numberOfVertices() {
		// TODO Auto-generated method stub
		return this._numberOfVertices;
	}

	protected void setNumberOfVertices(int newNumberOfVertices) {
		this._numberOfVertices = newNumberOfVertices;
	}

	@Override
	public int numberOfEdges() {
		// TODO Auto-generated method stub
		return this._numberOfEdges;
	}

	protected void setNumberOfEdges(int newNumberOfEdges) {
		this._numberOfEdges = newNumberOfEdges;
	}

	protected int[][] adjacency() {
		return this._adgacency;
	}

	protected void setAdjacency(int[][] newAdjacency) {
		this._adgacency = newAdjacency;
	}

	@Override
	public boolean vertexDoesExist(int aVertex) {
		// TODO Auto-generated method stub
		return (aVertex >= 0 && aVertex < this.numberOfVertices());
	}

	@Override
	public boolean edgeDoesExist(int aTailVertex, int aHeadVertex) {
		// TODO Auto-generated method stub
		if (this.edgeIsValid(aTailVertex, aHeadVertex)) {
			return (this.adjacencyOfEdgeDoesExist(aTailVertex, aHeadVertex));
		}
		return false;
	}

	@Override
	public boolean edgeDoesExist(E anEdge) {
		// TODO Auto-generated method stub
		if (anEdge != null) {

			return this.edgeDoesExist(anEdge.tailVertex(), anEdge.headVertex());
		}

		return false;
	}

	@Override
	public boolean edgeIsValid(int aTailVertex, int aHeadVertex) {
		// TODO Auto-generated method stub
		return (this.vertexDoesExist(aTailVertex) && this.vertexDoesExist(aHeadVertex));
	}

	@Override
	public boolean edgeIsValid(E anEdge) {
		// TODO Auto-generated method stub
		if (anEdge != null) {
			return (this.edgeIsValid(anEdge.tailVertex(), anEdge.headVertex()));

		}
		return false;
	}

	@Override
	public E edge(int aTailVertex, int aHeadVertex) {
		// TODO Auto-generated method stub
		if (this.edgeDoesExist(aTailVertex, aHeadVertex)) {
			return (E) new Edge(aTailVertex, aHeadVertex);
		}

		return null;
	}

	@Override
	public boolean addEdge(E anEdge) {
		// TODO Auto-generated method stub
		if (anEdge != null) {
			if (this.edgeIsValid(anEdge) && !this.edgeDoesExist(anEdge)) {
				int tailVertex = anEdge.tailVertex();
				int headVertex = anEdge.headVertex();
				this.setAdjacencyOfEdgeAsExist(tailVertex, headVertex);
				this.setAdjacencyOfEdgeAsExist(headVertex, tailVertex);
				this.setNumberOfEdges(this.numberOfEdges() + 1);
				return true;

			}
		}
		return false;

	}

	protected int adjacencyOfEdge(int tailVertex, int headVertex) {
		return this.adjacency()[tailVertex][headVertex];
	}

	protected void setAdjacencyOfEdgeAs(int tailVertex, int headVertex, int anAdjacencyOfEdge) {
		this.adjacency()[tailVertex][headVertex] = anAdjacencyOfEdge;
	}

	private void setAdjacencyOfEdgeAsExist(int tailVertex, int headVertex) {
		this.setAdjacencyOfEdgeAs(tailVertex, headVertex, UndirectedAdjacencyMatrixGraph.EDGE_EXIST);

	}

	protected void setAdjacencyOfEdgeAsNone(int tailVertex, int headVertex) {
		this.setAdjacencyOfEdgeAs(tailVertex, headVertex, UndirectedAdjacencyMatrixGraph.EDGE_NONE);
	}

	protected UndirectedAdjacencyMatrixGraph() {

	}

	public UndirectedAdjacencyMatrixGraph(int givenNumberOfVertices) {
		this.setNumberOfVertices(givenNumberOfVertices);
		this.setNumberOfEdges(0);
		this.setAdjacency(new int[givenNumberOfVertices][givenNumberOfVertices]);
		for (int tailVertex = 0; tailVertex < this.numberOfVertices(); tailVertex++) {
			for (int headVertex = 0; headVertex < this.numberOfVertices(); headVertex++) {
				this.setAdjacencyOfEdgeAsNone(tailVertex, headVertex);

			}
		}
	}

	private boolean adjacencyOfEdgeDoesExist(int tailVertex, int headVertex) {
		return (this.adjacencyOfEdge(tailVertex, headVertex) != UndirectedAdjacencyMatrixGraph.EDGE_NONE);

	}
}