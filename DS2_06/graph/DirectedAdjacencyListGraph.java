package graph;


import list.Iterator;
import list.LinkedList;

public class DirectedAdjacencyListGraph<E extends Edge> extends AdjacencyGraph<E> {
	
	private LinkedList<E>[] _adjacency;
	
	// Getters & Setters
	protected LinkedList<E>[] adjacency(){
		return this._adjacency;
	}
	
	protected void setAdjacency(LinkedList<E>[] newAdjacency) {
		this._adjacency = newAdjacency;
	}
	
	// Protected method
	protected LinkedList<E> neighborListOf(int aTailVertex){
		return this.adjacency()[aTailVertex];
	}
	
	protected int adjacencyOfEdge(int aTailVertex, int aHeadVertex){
		if(this.vertexDoesExist(aTailVertex) && this.vertexDoesExist(aHeadVertex)){
			Iterator<E> iterator = this.neighborIteratorOf(aTailVertex);
			while(iterator.hasNext()){
				E neighborEdge = iterator.next();
				if(aHeadVertex == neighborEdge.headVertex()){
					return AdjacencyGraph.EDGE_EXIST;
				}
			}
		}
		return AdjacencyGraph.EDGE_NONE;
	}
	
	//Constructor
	@SuppressWarnings("unchecked")
	public DirectedAdjacencyListGraph(int givenNumberOfVertices){
		this.setNumberOfVertices(givenNumberOfVertices);
		this.setAdjacency(new LinkedList[givenNumberOfVertices]);
		for(int tailVertex = 0; tailVertex < this.numberOfVertices(); tailVertex++){
			this.adjacency()[tailVertex] = new LinkedList<E>();
		}
		this.setNumberOfEdges(0);
		
	}
	//Public Methods
	@Override
	public boolean edgeDoesExist(int aTailVertex, int aHeadVertex) {
		// TODO Auto-generated method stub
		return (this.adjacencyOfEdge(aTailVertex, aHeadVertex) != AdjacencyGraph.EDGE_NONE);
	}

	@Override
	public boolean edgeDoesExist(E anEdge) {
		// TODO Auto-generated method stub
		if(anEdge != null){
			return this.edgeDoesExist(anEdge.tailVertex(), anEdge.headVertex());
		}
		return false;
	}
	
	public E edge(int aTailVertex, int aHeadVertex){
		if(this.vertexDoesExist(aTailVertex)){
			Iterator<E> iterator = this.neighborIteratorOf(aTailVertex);
			while(iterator.hasNext()){
				E neighborEdge = iterator.next();
				if(aHeadVertex == neighborEdge.headVertex()){
					return neighborEdge;
				}
			}
		}
		return null;
	}


	@Override
	public boolean addEdge(E anEdge) {
		// TODO Auto-generated method stub
		if( this.edgeIsValid(anEdge) && (! this.edgeDoesExist(anEdge))){
			this.neighborListOf(anEdge.tailVertex()).add(anEdge);
			this.setNumberOfEdges(this.numberOfEdges() + 1);
			return true;
		}
		return false;
	}



	@Override
	public Iterator<E> neighborIteratorOf(int aTailVertex) {
		// TODO Auto-generated method stub
		if(this.vertexDoesExist(aTailVertex)){
			return (Iterator<E>) this.neighborListOf(aTailVertex).iterator();
		}
		return null;
	}
}
