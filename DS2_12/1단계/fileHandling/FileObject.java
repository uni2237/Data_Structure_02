package fileHandling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import app.AppView;

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
	private void setReader (BufferedReader aReader){
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
	private void setFilePath(String newFilewPath){
		this._filePath = newFilewPath;
	}
	
	protected String fileName(){
		return this._fileName;
	}
	public void setFileName(String newFileName){
		this._fileName = newFileName;
	}
	
	private IOType fileIOtype(){
		return this._fileIOType;
	}
	public void setIOType(IOType newIOType){
		this._fileIOType = newIOType;
	}
	
	// Constructor
	public FileObject(String givenFilePath, String givenFileName, IOType givenIOType){
		// givenFilename�� ������ givenIOType���� ����ϴ� ���� ��ü�� �����Ѵ�
		// ���� ������ open ������ �ʾҴ�.
		this.setFilePath(givenFilePath);
		this.setFileName(givenFileName);
		this.setIOType(givenIOType);
	}
	
	public FileObject(){
		//�⺻ ������
	}
	
	// Public Methods
	public static boolean filePathDoesExist(String aFilePath){
		if(aFilePath == null) return false;
		
		File file = new File(aFilePath);
		AppView.DebugPrint("[DEBUG] �Է��� ���� ��� : " + file.getAbsolutePath() + "\n");
		
		return file.exists();
	}
	
	public static boolean fileDoesExist(String aFilePath, String aFileName){
		if (aFilePath == null || aFileName == null) return false;
		
		File file = new File(aFilePath + "/" + aFileName);
		AppView.DebugPrint("[DEBUG] �Է��� ���� ��� �� �̸�: " + file.getAbsolutePath() + "\n");
		
		return file.exists();
	}
	
	public boolean closeFile(){
		if(this.fileIOtype().equals(IOType.Input)){
			
		}
		else if (this._fileIOType.equals(IOType.Output)){
			try{
				this.fileWriter().close();
			} catch (IOException e){
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
		if(this.filePath() == null
					|| this.fileName() == null){
			AppView.outputErrorLine("File Object�� ���� �̸��̳� ��ΰ� �����Ǿ� ���� �ʽ��ϴ�.");
			return false;
		}
		File afile = null;
		if (this.fileIOtype().equals(IOType.Input)){
			
		}
		else if (this.fileIOtype().equals(IOType.Output)){
			afile = new File(this.filePath() + "/" + this.fileName());
			
			// Create File
			try{
				if (afile.createNewFile() == false){
					return false;
				}
			} catch (IOException e){
				e.printStackTrace();
			}
			
			// Create File Writer Stream
			try{
				this.setFileWriter( new FileWriter(afile));
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		else{
			AppView.outputErrorLine("File IOType");
			return false;
		}
		
		this.setFile(afile);
		return true;
	}
	
	public boolean writerInteger(Integer value){
		if(this.file().canWrite() == false){
			return false;
		}
		if (value == null){
			return false;
		}
		
		try{
			this.fileWriter().write(String.format("%d\n", value.intValue()));
		} catch (IOException e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
