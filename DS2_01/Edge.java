
public class Edge {
	//비공개 인스턴스 변수
	private int _tailVertex;
	private int _headVertex;

	public Edge(int givenTailVertex,int givenHeadVertex) {
		//생성자
		//주어진 tail vertex와 head vertex를 갖는 edge 객체 얻음
		/*this._tailVertex=givenTailVertex;
		this._headVertex=givenHeadVertex;
		*/
		
		this.setTailvertex(givenTailVertex);
		this.setHeadVertex(givenHeadVertex);
	}
	
	public void setTailvertex(int newTailVertex) {
		//edge의 tail vertex를 newTailVertex로 설정
		this._tailVertex=newTailVertex;
	
	}
	public int tailVertex()
	{
		//edge의 tail vertex를 얻음
		return this._tailVertex;
		
	}
	public void setHeadVertex(int newHeadVertex) {
		//edge의 head Vertex를 newHeadVertex로 설정
		this._headVertex=newHeadVertex;
	}
	
	public int headVertex() {
		//edge의 head vertex를 얻음
		return this._headVertex;
	}
	
	
}
