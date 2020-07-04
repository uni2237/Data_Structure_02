package app;

import java.util.Scanner;

public final class AppView {
	private static Scanner scanner = new Scanner(System.in);

	private static final boolean DEBUG_MODE = true;

	public static void outputDebugMessage(String aString) {
		if (DEBUG_MODE) {
			System.out.print(aString);
		}
	}

	public static void DebugPrint(String aString) {
		if (DEBUG_MODE) {
			System.out.println(aString);
		}
	}

	private AppView() {

	}

	public static void outputLine(String aString) {
		System.out.println(aString);
	}

	public static void output(String aString) {
		System.out.print(aString);
	}

	public static void outputErrorLine(String aString) {
		System.out.println("[����] " + aString);
	}

	public static String inputFileName() {
		String fileName;
		String scannedToken;
		while (true) {
			AppView.output("? ����� ���� �̸��� �Է��Ͻÿ�: ");
			scannedToken = AppView.scanner.next();
			fileName = scannedToken;
			return fileName;
		}
	}

	public static String inputFilePath() {
		String filePath;
		String scannedToken;
		while (true) {
			AppView.output("? ������ ������ ��θ� �Է��Ͻÿ�( . �Է½� ������Ʈ ��� ��� ): ");
			scannedToken = AppView.scanner.next();
			filePath = scannedToken;
			return filePath;
		}
	}

	public static int inputMaxSize() {
		int maxSize;
		String scannedToken;
		while (true) {
			AppView.output("? ������ ���� �ִ� ���� �Է��ϼ���: ");
			scannedToken = AppView.scanner.next();
			try {
				maxSize = Integer.parseInt(scannedToken);
				return maxSize;
			} catch (NumberFormatException e) {
				AppView.outputLine("[����] �ִ� �� �Է¿� ������ �ֽ��ϴ�: " + scannedToken);
			}
		}
	}
}
