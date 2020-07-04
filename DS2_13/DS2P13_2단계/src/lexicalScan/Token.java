package lexicalScan;

import dictionary.Key;

public class Token {
	
	public enum TokenType {TOKEN_NULL, TOKEN_ID, TOKEN_int, TOKEN_Float, TOKEN_String};
	private static final int MAX_TOKEN_VALU_LENGTH = 255;
	
	private TokenType _tokenType;
	private String		_tokenValue;
	
	
	public TokenType tokenType() {
		return _tokenType;
	}
	public void setTokenType(TokenType aTokenType) {
		this._tokenType = aTokenType;
	}
	public String tokenValue() {
		return _tokenValue;
	}
	private void setTokenValue(String aTokenValue) {
		this._tokenValue = aTokenValue;
	}
	
	// Constructor
	public Token(){
		this.setTokenType(TokenType.TOKEN_NULL);
		this.setTokenValue(null);
	}
	
	public Token(TokenType givenTokenType, String givenTokenValue){
		this.setTokenType(givenTokenType);
		this.setTokenValue(givenTokenValue);
	}
	
	public void clearValue(){
		this.setTokenValue("");
	}
	
	public void addChar(String aChar){
		if(this.tokenValue().length() < MAX_TOKEN_VALU_LENGTH -1){
			this.setTokenValue(this.tokenValue() + aChar);
		}
	}
	
	public Token copy(){
		return new Token(this.tokenType(), this.tokenValue());
	}
	
	// static methods
	public static Key KeyFromToken(Token token){
		String tokenValue = token.tokenValue();
		Key newkey = new Key();
		
		newkey.setValue(tokenValue);
		return newkey;
	}
	
	

}
