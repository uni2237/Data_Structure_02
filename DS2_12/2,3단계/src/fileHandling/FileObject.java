package fileHandling;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import app.AppView;

import java.io.FileReader;
import java.io.BufferedReader;

public class FileObject {
	
	private String _filePath;
	private String _fileName;
	private IOType _fileIOType;
	
	private FileReader _fileReader;
	private BufferedReader _reader;
	private FileWriter _fileWriter;
	private File _file;
	
	private BufferedReader reader(){
		return this._reader;
	}
	private void setReader(BufferedReader aReader){
		this._reader = aReader;
	}
	
	private FileWriter fileWriter(){
		return this._fileWriter;
	}
	private void setFileWriter(FileWriter aFileWriter){
		this._fileWriter = aFileWriter;
	}
	
	private FileReader fileReader(){
		return this._fileReader;
	}
	private void setFileReader(FileReader aFileReader){
		this._fileReader = aFileReader;
	}
	
	private File file(){
		return this._file;
	}
	private void setFile(File aFile){
		this._file = aFile;
	}
	
	private String filePath(){
		return this._filePath;
	}
	public void setFilePath(String newFilePath){
		this._filePath = newFilePath;
	}
	
	protected String fileName(){
		return this._fileName;
	}
	public void setFileName(String newFileName){
		this._fileName = newFileName;
	}
	
	private IOType fileIOType(){
		return this._fileIOType;
	}
	public void setIOType(IOType newIOType){
		this._fileIOType = newIOType;
	}
	
	public FileObject(String givenFilePath, String givenFileName, IOType givenIOType){
		this.setFilePath(givenFilePath);
		this.setFileName(givenFileName);
		this.setIOType(givenIOType);
	}
	
	public FileObject(){
		
	}
	
	public static boolean filePathDoesExist(String aFilePath){
		if(aFilePath == null)
			return false;
		
		File file = new File(aFilePath);
		AppView.DebugPrint("[DEBUG] 입력한 파일 경로: " + file.getAbsolutePath() + "\n");
		
		return file.exists();
	}
	
	public static boolean fileDoesExist(String aFilePath, String aFileName){
		if(aFilePath == null || aFileName == null)
			return false;
		
		File file = new File(aFilePath + "/" + aFileName);
		AppView.DebugPrint("[DEBUG] 입력한 파일 경로 및 이름: " + file.getAbsolutePath() + "\n");
		
		return file.exists();
	}
	
	public boolean closeFile(){
		if(this.fileIOType().equals(IOType.Input)){
			try{
				this.reader().close();
				this.fileReader().close();
			}
			catch(IOException e){
				e.printStackTrace();
				return false;
			}
		}
		else if(this.fileIOType().equals(IOType.Output)){
			try{
				this.fileWriter().close();
			}
			catch(IOException e){
				e.printStackTrace();
				return false;
			}
		}
		else{
			return false;
		}
		
		return true;
	}
	
	public boolean openFile(){
		if(this.filePath() == null || this.fileName() == null){
			AppView.outputErrorLine("File Object에 파일 이름이나 파일 경로가 설정되어 있지 않습니다.");
			return false;
		}
		File afile = null;
		if(this.fileIOType().equals(IOType.Input)){
			afile = new File(this.filePath() + "/" + this.fileName());
			try{
				this.setFileReader(new FileReader(afile));
				this.setReader(new BufferedReader(this.fileReader()));
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
				return false;
			}
		}
		else if(this.fileIOType().equals(IOType.Output)){
			afile = new File(this.filePath() + "/" + this.fileName());
			
			try{
				if(afile.createNewFile() == false){
					return false;
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
			
			try{
				this.setFileWriter(new FileWriter(afile));
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		else{
			AppView.outputErrorLine("File IOType에 파일 이름이나 파일 경로가 설정되어 있지 않습니다.");
			return false;
		}
		
		this.setFile(afile);
		return true;
	}
	
	public boolean writeInteger(Integer value){
		if(this.file().canWrite() == false){
			return false;
		}
		if(value == null){
			return false;
		}
		
		try{
			this.fileWriter().write(String.format("%d\n", value.intValue()));
		}
		catch(IOException e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean removeFile(){
		boolean state = false;
		try{
			state = this.file().delete();
		}
		catch(SecurityException e){
			e.printStackTrace();
		}
		
		return state;
	}
	
	public boolean renameFile(String aNewFileName){
		File newFile = new File(aNewFileName);
		boolean result = this.file().renameTo(newFile);
		
		if(result == false){
			return false;
		}
		
		this.setFile(newFile);
		return true;
	}
	
	public Integer readInteger(){
		if(this.file().canRead() == false){
			return null;
		}
		
		Integer value = null;
		try{
			String readedLine = this.reader().readLine();
			if(readedLine == null)
				return null;
			value = Integer.parseInt(readedLine);
		}
		catch(IOException e){
			e.printStackTrace();
			return null;
		}
		
		return value;
	}

}
