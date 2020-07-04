package dictionary;

public class Node {
	
	private Key _key;
	private Item _item;
	
	//Getters & Setters
	public Key key() {
		return _key;
	}
	public void setKey(Key aKey) {
		this._key = aKey;
	}
	public Item item() {
		return _item;
	}
	public void setItem(Item aItem) {
		this._item = aItem;
	}
	
	public Node(){
		this.setKey(null);
		this.setItem(null);
	}
	
	public Node(Key givenKey, Item givenObject){
		this.setKey(givenKey);
		this.setItem(givenObject);
	}
	
	
	


}
