package app;
import java.util.Scanner;

//MVC 모델 중 V View에 해당한다. app의 입출력을 담당하며
//AppController 와만 관계를 맺고, model에 해당하는 class와는 독립적이어야 한다.

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
			AppView.output("? 사용할 파일 이름을 입력하시오: ");
			scannedToken = AppView.scanner.next();
			fileName = scannedToken;
			return fileName;
		}
	}
	
	public static String inputFilePath(){
		String filePath;
		String scannedToken;
		while(true){
			AppView.output("? 파일을 저장할 경로를 입력하시오( . 입력시 프로젝트 경로 사용 ): ");
			scannedToken = AppView.scanner.next();
			filePath = scannedToken;
			return filePath;
		}
	}
	
	public static int inputMaxSize(){
		int maxSize;
		String scannedToken;
		while(true){
			AppView.output("? 생성할 수의 최대 값을 입력하세요: ");
			scannedToken = AppView.scanner.next();
			try{
				maxSize = Integer.parseInt(scannedToken);
				return maxSize;
			}
			catch(NumberFormatException e){
				AppView.outputLine("[오류] 최대 값 입력에 오류가 있습니다: " + scannedToken);
			}
		}
	}

}
