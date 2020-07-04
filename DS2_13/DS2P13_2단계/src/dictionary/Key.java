package dictionary;

public class Key {
	
	private String _value;
	
	//Constructor
	public Key(){
		this.setValue(null);
	}
	public Key(String givenKey){
		this.setValue(givenKey);
	}
	
	// Getters & Setters
	public String value() {
		return _value;
	}
	public void setValue(String aValue) {
		this._value = aValue;
	}
	
	public int compareTo (Object arg0){
		Key aKey = (Key)arg0;
		int index = 0;
		
		while(this.value().charAt(index) == aKey.value().charAt(index)){
			if (this.value().charAt(index) == '\0') return 0;
			index++;
		}
		
		return (this.value().charAt(index) - aKey.value().charAt(index));
	}
	

}
