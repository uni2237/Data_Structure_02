package app;

import dictionary.Dictionary;
import dictionary.HashTable;
import dictionary.Item;
import dictionary.Key;
import dictionary.Node;
import lexicalScan.LexicalScanner;
import lexicalScan.Token;
import lexicalScan.Token.TokenType;
import list.Iterator;

import java.io.IOException;

import app.AppView;

// MVC설계 중 C Controller에 해당하며 실질적인 app을 컨트롤 하는 역할을 한다.
public class AppController {

	private static boolean DEBUG = false;

	private String _fileName;
	private LexicalScanner _lexScanner;
	private Dictionary _dictionary = new HashTable();

	// Getters & Setters
	private String fileName() {
		return _fileName;
	}

	private void setFileName(String aFileName) {
		this._fileName = aFileName;
	}

	private LexicalScanner lexScanner() {
		return _lexScanner;
	}

	private void setLexScanner(LexicalScanner aLexScanner) {
		this._lexScanner = aLexScanner;
	}

	private Dictionary dictionary() {
		return _dictionary;
	}

	private void setDictionary(Dictionary aDictionary) {
		this._dictionary = aDictionary;
	}

	// Constructor
	public AppController() {

	}

	private void inputAndMakeDictionary() {
		// 스캔 할 파일을 입력받는다.

		setFileName(AppView.inputFileName());
		AppView.outputLine("> 파일을 스캔하여 사전을 구성합니다:");

		this.setLexScanner(new LexicalScanner(this.fileName()));

		Token token = null;
		Key aKey = null;
		Item anItem = null;
		try {
			while ((token = this.lexScanner().nextToken()) != null) {
				if (token.tokenType() == TokenType.TOKEN_ID) {
					aKey = Token.KeyFromToken(token);

					if (this.dictionary().keyDoesExist(aKey)) {
						anItem = (Item) this.dictionary().objectForKey(aKey);
						this.dictionary().replaceObjectForKey(anItem, aKey);
					} else {
						anItem = new Item(1);
						this.dictionary().addObjectForKey(anItem, aKey);
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		AppView.outputLine("");

	}

	private void showAllKeysAndItems() {
		Item item = null;
		String keyValue = "";

		Iterator<Object> dicKeyIterator = this.dictionary().iterator();
		int itemCount = 0;

		while (dicKeyIterator.hasNext()) {
			Node aNode = (Node) dicKeyIterator.next();
			Key key = aNode.key();
			keyValue = key.value();
			item = (Item) this.dictionary().objectForKey(key);
			AppView.outputLine(" * count=" + itemCount + ", Key \"" + keyValue + "\", frequency =" + item.count());
			itemCount++;
		}
	}

	public void showHashResult() {
		AppView.outputLine("> 사전 정보를 출력합니다 ");
		this.showAllKeysAndItems();
		float loadingFactor = ((HashTable) this.dictionary()).loadingFactor();
		AppView.outputLine("");

		AppView.outputLine(" - Loading Factor: " + loadingFactor);
		AppView.outputLine("");

		float averageSynonymListLength = ((HashTable) this.dictionary()).averageSynonymListLength();
		AppView.outputLine(" - Average Synonym List Length: " + averageSynonymListLength);
		AppView.outputLine("");
		AppView.outputLine("");

	}

	public void run() {
		AppView.outputLine("<<< 해쉬를 이용한 사전 프로그램을 시작합니다 >>>");
		AppView.outputLine("");
		AppView.outputLine("> 스캔할 파일의 이름을 입력해 주세요.");
		this.inputAndMakeDictionary();
		AppView.outputLine("파일을 스캔하여 사전을 구성합니다:");
		this.showHashResult();

		AppView.outputLine("<<< 해쉬를 이용한 사전 프로그램을 종료합니다 >>>");
	}
}
