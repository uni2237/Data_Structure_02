package app;

import fileHandling.FileObject;
import fileHandling.IOType;
import dataGeneration.RandomNumberGenerator;

// MVC설계 중 C Controller에 해당하며 실질적인 app을 컨트롤 하는 역할을 한다.
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
				AppView.DebugPrint("경로를 사용할 수 있습니다.: " + filePath);
				break; // return filePath;
			}
			
			AppView.outputLine("[오류] 입력한 파일 경로가 존재하지 않습니다: " + filePath);
		}
		
		this.setFilePath(filePath);
	}
	
	private void inputStringOfFileName(){
		String fileName = null;
		while(true){
			fileName = AppView.inputFileName();
			if (FileObject.fileDoesExist(this.filePath(), fileName)){
				AppView.outputErrorLine(" 해당 파일이 이미 존재합니다: " + fileName);
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
				AppView.outputErrorLine("입력 값이 0보다 작습니다: " + maxCount);
			}
			else{
				break;
			}
		}
		
		this.setMaxCount(maxCount);
	}
	
	public void inputAndMakeFile(){
		AppView.outputLine("> 무작위 파일 생성을 위해 정보를 입력해야합니다:");
		this.inputStringOfFilePath();
		this.inputStringOfFileName();
		this.inputNumberOfGenerationCount();
		AppView.outputLine("");
		
		AppView.outputLine("> 무작위 파일을 생성합니다:");
		// 파일 객체 생성 그리고 오픈
		// 주어진 파일 이름으로 파일 객체를 생성한다
		this.setFileObject(new FileObject(this.filePath(), this.filename(), IOType.Output));
		this.setRandomGen(new RandomNumberGenerator());
		this.randomGen().prepare(this.maxCount());
		
		AppView.outputLine("> 파일을 오픈합니다:");
		if(this.fileObject().openFile()){
			//무작위 수를 파일의 원소 개수만큼을 반복하여 얻어서 파일에 출력한다
			for( int i = 0; i < this._maxCount; i++){
				int randomValue = this.randomGen().randomNumber();
				//AppView.DebugPrint(String.format("  파일에 숫자를 기록합니다: %d", randomValue));
				this.fileObject().writerInteger(randomValue);
			}
		}
		else{
			String filePath;
			do {
				if(this.fileObject().filePathDoesExist(this.filePath())) {
					AppView.DebugPrint("경로를 사용할 수 있습니다 : " + this.filePath());
					break;
				}
				AppView.outputErrorLine("[오류] 입력한 파일 경로가 없습니다: " + this.filePath());
				filePath = AppView.inputFilePath();
				this.setFilePath(filePath);
			} while(true);
			
			// 오류가 있으면 적절한 메세지를 내보내고 파일 경로의 이름을 다시 입력받는다.
			// 파일 경로가 존재하지 않는 경우/
		}
		AppView.outputLine("> 파일을 생성하였습니다:");
		//파일을 닫는다
		this.fileObject().closeFile();
	}
	
	

	
	public void run() {
		AppView.outputLine("<<< 외부정렬 프로그램을 시작합니다 >>>");
		AppView.outputLine("");
		
		this.inputAndMakeFile();
		
		AppView.outputLine("<<< 외부정렬 프로그램을 종료합니다 >>>");
	}

}
