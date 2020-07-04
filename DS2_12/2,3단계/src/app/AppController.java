package app;

import fileHandling.FileObject;
import fileHandling.IOType;
import dataGeneration.RandomNumberGenerator;
import externalSort.FileSort;

public class AppController {

	private static boolean DEBUG = false;

	private String _filePath;
	private String _inputFileName;
	private String _outputFileName;
	private FileSort _fileSort;

	private FileSort fileSort() {
		return this._fileSort;
	}

	private void setFileSort(FileSort aFileSort) {
		this._fileSort = aFileSort;
	}

	private String inputFileName() {
		return this._inputFileName;
	}

	private void setInputFileName(String aInputFileName) {
		this._inputFileName = aInputFileName;
	}

	private String outputFileName() {
		return this._outputFileName;
	}

	private void setOutputFileName(String aOutputFileName) {
		this._outputFileName = aOutputFileName;
	}

	private String filePath() {
		return this._filePath;
	}

	private void setFilePath(String aFilePath) {
		this._filePath = aFilePath;
	}

	private void inputStringOfFilePath() {
		String filePath;
		while (true) {
			filePath = AppView.inputFilePath();
			if (filePath == null) {
				filePath = ".";
			}

			if (FileObject.filePathDoesExist(filePath)) {
				AppView.DebugPrint("경로를 사용할 수 있습니다: ");
				break;
			}

			AppView.outputLine("[오류] 입력한 파일 경로가 존재하지 않습니다: ");
		}

		this.setFilePath(filePath);
	}

	private void inputStringOfInputFileName() {
		String fileName = null;
		while (true) {
			AppView.outputLine("- 입력 파일의 이름을 입력 합니다.");
			if (DEBUG) {
				fileName = "testset sorted.txt";
				AppView.DebugPrint("? 파일이름을 임의로 설정합니다: " + fileName);
				break;
			}
			fileName = AppView.inputFileName();
			if (!(FileObject.fileDoesExist(this.filePath(), fileName))) {
				AppView.outputLine(" 해당 파일이 존재하지않습니다: " + fileName);
				break;
			} else {
				break;
			}
		}
		this.setInputFileName(fileName);
	}

	private void inputStringOfOutputFileName() {

		String fileName;

		while (true) {
			AppView.outputLine("- 출력 파일의 이름을 입력 합니다.");
			if (DEBUG) {
				fileName = "testset sorted.txt";
				AppView.DebugPrint("? 파일이름을 임의로 설정합니다: " + fileName);
				break;
			}
			fileName = AppView.inputFileName();
			if (FileObject.fileDoesExist(this.filePath(), fileName)) {
				AppView.outputLine(" 해당 파일이 존재합니다: " + fileName);
				break;
			} else {
				break;
			}
		}

		this.setOutputFileName(fileName);
	}

	public void inputAndMakeFile() {
		AppView.outputLine(">  외부 정렬을 수행하기 위해 정보를 입력해야합니다:");
		this.inputStringOfFilePath();
		this.inputStringOfInputFileName();
		this.inputStringOfOutputFileName();
		AppView.outputLine("");

	}

	private void devideAndSort() {

		this.setFileSort(new FileSort(200, this.filePath()));

		AppView.outputLine("> 2-way 파일 정렬을 시작합니다.");
		this.fileSort().sortFile(this.inputFileName(), this.outputFileName());
	}

	private void showSortedFile() {

		AppView.outputLine("> 정렬된 파일의 이름은 다음과 같습니다:");
		AppView.outputLine("- FileName : " + this.outputFileName());
		AppView.outputLine("");
		AppView.outputLine("> 파일을 검증합니다.");
		AppView.outputLine("");

		this.fileSort().validation();
	}

	public void run() {

		AppView.outputLine("<<< 외부정렬 프로그램을 시작합니다 >>>");
		AppView.outputLine("");

		this.inputAndMakeFile();
		this.devideAndSort();
		this.showSortedFile();

		AppView.outputLine("<<< 외부정렬 프로그램을 종료합니다 >>>");
	}

}
