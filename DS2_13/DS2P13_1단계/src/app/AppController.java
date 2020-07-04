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

// MVC���� �� C Controller�� �ش��ϸ� �������� app�� ��Ʈ�� �ϴ� ������ �Ѵ�.
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
		// ��ĵ �� ������ �Է¹޴´�.

		setFileName(AppView.inputFileName());
		AppView.outputLine("> ������ ��ĵ�Ͽ� ������ �����մϴ�:");

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
		AppView.outputLine("> ���� ������ ����մϴ� ");
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
		AppView.outputLine("<<< �ؽ��� �̿��� ���� ���α׷��� �����մϴ� >>>");
		AppView.outputLine("");
		AppView.outputLine("> ��ĵ�� ������ �̸��� �Է��� �ּ���.");
		this.inputAndMakeDictionary();
		AppView.outputLine("������ ��ĵ�Ͽ� ������ �����մϴ�:");
		this.showHashResult();

		AppView.outputLine("<<< �ؽ��� �̿��� ���� ���α׷��� �����մϴ� >>>");
	}
}
