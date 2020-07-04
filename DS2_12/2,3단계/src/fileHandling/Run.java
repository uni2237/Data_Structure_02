package fileHandling;

import app.AppView;

public class Run extends FileObject {

	public static void setDirectoryPath(String aDirectoryPath){
		_DIRECTORY_PATH = aDirectoryPath;
	}
	private static String _DIRECTORY_PATH = null;
	
	public static void setFilePrefix(String aFilePrefix){
		_FILE_PREFIX = aFilePrefix;
	}
	private static String _FILE_PREFIX = null;
	
	public static void setRunId(int newRunId){
		RUN_ID = newRunId;
	}
	private static int RUN_ID  = 0;
	
	private int _runID;
	
	private int runID(){
		return this._runID;
	}
	
	private void setRunID(int aRunID){
		this._runID = aRunID;
	}
	
	public Run(){	
		this.setRunID(Run.RUN_ID);
		Run.setRunId(this.runID() + 1);
		String aFileName = String.format("%s_%d temp", Run._FILE_PREFIX, this.runID());
		this.setFilePath(Run._DIRECTORY_PATH + "/tmp/");
		this.setFileName(aFileName);
		this.setIOType(IOType.Output);
		
	}
	
	public void makeRun(Integer[] elementList){
		if (elementList.length == 0){
			AppView.outputErrorLine("Make Run이 실패하였습니다: 리스트 없음");
			return;
		}
		
		if(this.openFile() == false){
			AppView.outputErrorLine("파일을 여는 것이 실패하였습니다:" + this.fileName());
			return;
		}
		
		for(int i = 0; i < elementList.length; i++){
			if (this.writeInteger(elementList[i]) == false){
				AppView.outputErrorLine("더 이상 쓸 수 없습니다:");
				break;
			}
		}
		
		if ( this.closeFile() == false){
			AppView.outputErrorLine("파일을 닫는 것에 실패하였습니다:" + this.fileName());
			return;
		}
	}
	
	
}
