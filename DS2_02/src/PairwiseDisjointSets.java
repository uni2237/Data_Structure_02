
public class PairwiseDisjointSets {
	// 그래프의 현재 상태의 component들을 관리함

	// Constants
	private static final int INITIAL_SINGLETON_SET_SIZE = 1;
	// Private instance variables
	private int _numberOfElements;
	private int[] _parentTree;

	// Constructor
	public PairwiseDisjointSets(int givenNumberOfElements) {
		this.setNumberOfElements(givenNumberOfElements);
		this.setParentTree(new int[this.numberOfElements()]);
		for (int rootOfSingletonSet = 0; rootOfSingletonSet < this.numberOfElements(); rootOfSingletonSet++) {
			this.setSizeOfSetFor(rootOfSingletonSet, INITIAL_SINGLETON_SET_SIZE);
		}

	}

	// 주어진 원소 aMember가 속해있는 집합을 얻음, 집합은 정수로 표현
	// Find 를 할 때에는, collapsing rule 을 적용한다. 
	// find를 할 때, root를 찾아 parent를 따라 올라가면서 만나게 되는 모든 원소는,
	// 그 parent를 root로 만든다.

	public int find(int aMember) {

		int rootCandidate = aMember;
		while (this.parentDoesExist(rootCandidate)) {
			rootCandidate = this.parentOf(rootCandidate);
			// 계속 부모를 타고 올라가서 루트를 찾음

		}
		int root = rootCandidate;
		// Apply Collapsing Rule
		int child = aMember;
		int parent = this.parentOf(child);
		if (parent >= 0) {
			while (parent != root) {
				this.setParentOf(child, root);
				child = parent;
				parent = this.parentOf(child);
			}
		}
		return root;
	}

	// 주어진 원소 aMemberA가 속한 집합과 주어진 원소 aMemberB가 속한 집합을 합하여 하나의 집합으로 만든다.
	// 두 원소가 원래 같은 집합에 속해 있으면 집합의 변화는 없다
	// Union 을 할 때에는, weighting rule 을 적용한다. 
	// 크기가 큰 집합의 루트를, 작은 집합의 루트의 parent 가 되게 한다.
	public void union(int aMemberA, int aMemberB) {
		int rootOfSetA = find(aMemberA);
		int rootOfSetB = find(aMemberB);

		// 루트들은 트리의 사이를 음수로 가지고 있음
		int sizeOfSetA = this.sizeOfSetFor(rootOfSetA);
		int sizeOfSetB = this.sizeOfSetFor(rootOfSetB);

		// Applying Weighting Rule
		if (sizeOfSetA < sizeOfSetB) {
			// 집합 A의 루트를 집합 B의 자식으로 함
			this.setParentOf(rootOfSetA, rootOfSetB);
			this.setSizeOfSetFor(rootOfSetB, sizeOfSetA + sizeOfSetB);
			
		}
		else {
			//집합 B의 루트를 집합 A의 자식으로 함
			this.setParentOf(rootOfSetB, rootOfSetA);
			this.setSizeOfSetFor(rootOfSetA, sizeOfSetA + sizeOfSetB);
		}

	}

	// Private getter/setter
	private int numberOfElements() {
		return this._numberOfElements;
	}

	private void setNumberOfElements(int newNumberOfElements) {
		this._numberOfElements = newNumberOfElements;

	}

	private int[] parentTree() {
		return this._parentTree;
	}

	private void setParentTree(int[] newParentTree) {
		this._parentTree = newParentTree;
	}

	// parent 얻기
	private int parentOf(int aMember) {
		return this.parentTree()[aMember];
	}

	// parent 설정
	private void setParentOf(int aChildMember, int newParentMember) {
		this.parentTree()[aChildMember] = newParentMember;
	}

	// parent가 존재하는지 여부확인
	private boolean parentDoesExist(int aMember) {
		return (this.parentOf(aMember) >= 0);
	}

	// 주어진 root가 속한 집합의 크기 얻기
	private int sizeOfSetFor(int aRoot) {
		return (-this.parentOf(aRoot)); // return (-this._parentTree[aRoot]);
	}

	// 주어진 root가 속한 집합의 크기 설정하기

	private void setSizeOfSetFor(int aRoot, int newSize) {
		this.setParentOf(aRoot, -newSize);

		// this._parentTree[aRoot] = -newSize;
	}
}
