package dictionary;

public class Item {
	private int _count;
	
	public Item(){
		this.setCount(0);
	}
	
	public Item(int givenCount){
		this.setCount(givenCount);
	}
	
	public int count() {
		return _count;
	}

	public void setCount(int aCount) {
		this._count = aCount;
	}
	
	public void incrementCount(){
		this.setCount(this.count() + 1);
	}

}
