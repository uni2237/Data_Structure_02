package app;

import fileHandling.FileObject;
import fileHandling.IOType;
import dataGeneration.RandomNumberGenerator;

// MVC���� �� C Controller�� �ش��ϸ� �������� app�� ��Ʈ�� �ϴ� ������ �Ѵ�.
public class AppController {

	// Private variable
	private FileObject _fileObject;
	private String _filePath;
	private String _fileName;
	private int _maxCount;
	private RandomNumberGenerator _randomGen;
	
	
	// Getters & Setters
	
	private RandomNumberGenerator randomGen(){
		return this._randomGen;
	}
	private void setRandomGen( RandomNumberGenerator aRandomGen){
		this._randomGen = aRandomGen;
	}
	
	private FileObject fileObject(){
		return this._fileObject;
	}
	private void setFileObject(FileObject aFileObject){
		this._fileObject = aFileObject;
	}
	
	private String filePath(){
		return this._filePath;
	}
	private void setFilePath(String aFilePath){
		this._filePath = aFilePath;
	}
	
	private String filename(){
		return this._fileName;
	}
	private void setFileName(String aFileName){
		this._fileName = aFileName;
	}
	
	private int maxCount(){
		return this._maxCount;
	}
	private void setMaxCount(int aMaxCount){
		this._maxCount = aMaxCount;
	}

	
	
	//private methods
	private void inputStringOfFilePath(){
		String filePath;
		while(true){
			filePath = AppView.inputFilePath();
			if(filePath == null){
				filePath = ".";
			}
			
			if(FileObject.filePathDoesExist(filePath)){
				AppView.DebugPrint("��θ� ����� �� �ֽ��ϴ�.: " + filePath);
				break; // return filePath;
			}
			
			AppView.outputLine("[����] �Է��� ���� ��ΰ� �������� �ʽ��ϴ�: " + filePath);
		}
		
		this.setFilePath(filePath);
	}
	
	private void inputStringOfFileName(){
		String fileName = null;
		while(true){
			fileName = AppView.inputFileName();
			if (FileObject.fileDoesExist(this.filePath(), fileName)){
				AppView.outputErrorLine(" �ش� ������ �̹� �����մϴ�: " + fileName);
			}
			else{
				break;
			}
		}
		this.setFileName(fileName);
	}
	
	public void inputNumberOfGenerationCount(){
		int maxCount = 0;
		while(true){
			maxCount = AppView.inputMaxSize();
			if (maxCount < 0){
				AppView.outputErrorLine("�Է� ���� 0���� �۽��ϴ�: " + maxCount);
			}
			else{
				break;
			}
		}
		
		this.setMaxCount(maxCount);
	}
	
	public void inputAndMakeFile(){
		AppView.outputLine("> ������ ���� ������ ���� ������ �Է��ؾ��մϴ�:");
		this.inputStringOfFilePath();
		this.inputStringOfFileName();
		this.inputNumberOfGenerationCount();
		AppView.outputLine("");
		
		AppView.outputLine("> ������ ������ �����մϴ�:");
		// ���� ��ü ���� �׸��� ����
		// �־��� ���� �̸����� ���� ��ü�� �����Ѵ�
		this.setFileObject(new FileObject(this.filePath(), this.filename(), IOType.Output));
		this.setRandomGen(new RandomNumberGenerator());
		this.randomGen().prepare(this.maxCount());
		
		AppView.outputLine("> ������ �����մϴ�:");
		if(this.fileObject().openFile()){
			//������ ���� ������ ���� ������ŭ�� �ݺ��Ͽ� �� ���Ͽ� ����Ѵ�
			for( int i = 0; i < this._maxCount; i++){
				int randomValue = this.randomGen().randomNumber();
				//AppView.DebugPrint(String.format("  ���Ͽ� ���ڸ� ����մϴ�: %d", randomValue));
				this.fileObject().writerInteger(randomValue);
			}
		}
		else{
			String filePath;
			do {
				if(this.fileObject().filePathDoesExist(this.filePath())) {
					AppView.DebugPrint("��θ� ����� �� �ֽ��ϴ� : " + this.filePath());
					break;
				}
				AppView.outputErrorLine("[����] �Է��� ���� ��ΰ� �����ϴ�: " + this.filePath());
				filePath = AppView.inputFilePath();
				this.setFilePath(filePath);
			} while(true);
			
			// ������ ������ ������ �޼����� �������� ���� ����� �̸��� �ٽ� �Է¹޴´�.
			// ���� ��ΰ� �������� �ʴ� ���/
		}
		AppView.outputLine("> ������ �����Ͽ����ϴ�:");
		//������ �ݴ´�
		this.fileObject().closeFile();
	}
	
	

	
	public void run() {
		AppView.outputLine("<<< �ܺ����� ���α׷��� �����մϴ� >>>");
		AppView.outputLine("");
		
		this.inputAndMakeFile();
		
		AppView.outputLine("<<< �ܺ����� ���α׷��� �����մϴ� >>>");
	}

}
