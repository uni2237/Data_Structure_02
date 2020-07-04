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
				AppView.DebugPrint("��θ� ����� �� �ֽ��ϴ�: ");
				break;
			}

			AppView.outputLine("[����] �Է��� ���� ��ΰ� �������� �ʽ��ϴ�: ");
		}

		this.setFilePath(filePath);
	}

	private void inputStringOfInputFileName() {
		String fileName = null;
		while (true) {
			AppView.outputLine("- �Է� ������ �̸��� �Է� �մϴ�.");
			if (DEBUG) {
				fileName = "testset sorted.txt";
				AppView.DebugPrint("? �����̸��� ���Ƿ� �����մϴ�: " + fileName);
				break;
			}
			fileName = AppView.inputFileName();
			if (!(FileObject.fileDoesExist(this.filePath(), fileName))) {
				AppView.outputLine(" �ش� ������ ���������ʽ��ϴ�: " + fileName);
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
			AppView.outputLine("- ��� ������ �̸��� �Է� �մϴ�.");
			if (DEBUG) {
				fileName = "testset sorted.txt";
				AppView.DebugPrint("? �����̸��� ���Ƿ� �����մϴ�: " + fileName);
				break;
			}
			fileName = AppView.inputFileName();
			if (FileObject.fileDoesExist(this.filePath(), fileName)) {
				AppView.outputLine(" �ش� ������ �����մϴ�: " + fileName);
				break;
			} else {
				break;
			}
		}

		this.setOutputFileName(fileName);
	}

	public void inputAndMakeFile() {
		AppView.outputLine(">  �ܺ� ������ �����ϱ� ���� ������ �Է��ؾ��մϴ�:");
		this.inputStringOfFilePath();
		this.inputStringOfInputFileName();
		this.inputStringOfOutputFileName();
		AppView.outputLine("");

	}

	private void devideAndSort() {

		this.setFileSort(new FileSort(200, this.filePath()));

		AppView.outputLine("> 2-way ���� ������ �����մϴ�.");
		this.fileSort().sortFile(this.inputFileName(), this.outputFileName());
	}

	private void showSortedFile() {

		AppView.outputLine("> ���ĵ� ������ �̸��� ������ �����ϴ�:");
		AppView.outputLine("- FileName : " + this.outputFileName());
		AppView.outputLine("");
		AppView.outputLine("> ������ �����մϴ�.");
		AppView.outputLine("");

		this.fileSort().validation();
	}

	public void run() {

		AppView.outputLine("<<< �ܺ����� ���α׷��� �����մϴ� >>>");
		AppView.outputLine("");

		this.inputAndMakeFile();
		this.devideAndSort();
		this.showSortedFile();

		AppView.outputLine("<<< �ܺ����� ���α׷��� �����մϴ� >>>");
	}

}
