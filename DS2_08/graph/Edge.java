package graph;

public class Edge {
	// ����� �ν��Ͻ� ����
	private int _tailVertex;
	private int _headVertex;

	public Edge(int givenTailVertex, int givenHeadVertex) {
		// ������
		// �־��� tail vertex�� head vertex�� ���� edge ��ü ����
		/*
		 * this._tailVertex=givenTailVertex; this._headVertex=givenHeadVertex;
		 */

		this.setTailvertex(givenTailVertex);
		this.setHeadVertex(givenHeadVertex);
	}

	public Edge reversed() {
		return new Edge(this.headVertex(), this.tailVertex());
	}

	public void setTailvertex(int newTailVertex) {
		// edge�� tail vertex�� newTailVertex�� ����
		this._tailVertex = newTailVertex;

	}

	public int tailVertex() {
		// edge�� tail vertex�� ����
		return this._tailVertex;

	}

	public void setHeadVertex(int newHeadVertex) {
		// edge�� head Vertex�� newHeadVertex�� ����
		this._headVertex = newHeadVertex;
	}

	public int headVertex() {
		// edge�� head vertex�� ����
		return this._headVertex;
	}

}
