package app;
import java.util.Scanner;

//MVC �� �� V View�� �ش��Ѵ�. app�� ������� ����ϸ�
//AppController �͸� ���踦 �ΰ�, model�� �ش��ϴ� class�ʹ� �������̾�� �Ѵ�.

public final class AppView {
	
	private static Scanner scanner = new Scanner(System.in);
	
	private AppView(){
		
	}	

	public static void outputLine(String aString) {
		System.out.println(aString);
	}
	
	public static void output(String aString){
		System.out.print(aString);
	}

	public static void outputlnDebugMessage(String aMessage) {
		// TODO Auto-generated method stub
		System.out.println(aMessage);
		
	}
	
	public static void outputDebugMessage(String aMessage) {
		// TODO Auto-generated method stub
		System.out.print(aMessage);
		
	}
	
	
	public static String inputFileName(){
		String fileName;
		String scannedToken;
		while(true){
			AppView.output("? ����� ���� �̸��� �Է��Ͻÿ�: ");
			scannedToken = AppView.scanner.next();
			fileName = scannedToken;
			return fileName;
		}
	}
	
	public static String inputFilePath(){
		String filePath;
		String scannedToken;
		while(true){
			AppView.output("? ������ ������ ��θ� �Է��Ͻÿ�( . �Է½� ������Ʈ ��� ��� ): ");
			scannedToken = AppView.scanner.next();
			filePath = scannedToken;
			return filePath;
		}
	}
	
	public static int inputMaxSize(){
		int maxSize;
		String scannedToken;
		while(true){
			AppView.output("? ������ ���� �ִ� ���� �Է��ϼ���: ");
			scannedToken = AppView.scanner.next();
			try{
				maxSize = Integer.parseInt(scannedToken);
				return maxSize;
			}
			catch(NumberFormatException e){
				AppView.outputLine("[����] �ִ� �� �Է¿� ������ �ֽ��ϴ�: " + scannedToken);
			}
		}
	}

}
