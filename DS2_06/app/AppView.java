package app;
import java.util.Scanner;

//MVC 모델 중 V View에 해당한다. app의 입출력을 담당하며
//AppController 와만 관계를 맺고, model에 해당하는 class와는 독립적이어야 한다.

//instance를 필요로 하지 않는 class이므로 static class로 하지만
//java에서는 static class를 선언하는 방법을 제공하지 않는다. 그러므로  static class의 효과를 내기 위하여
//class를 final로 선언하여 상속을 막고 생성자는 private로 선언하여, instance 객체 생성을 막는다.
//또 class의 나머지 모든 함수와 변수는 static으로 선언한다.
public final class AppView {
	
	private static Scanner scanner = new Scanner(System.in);
	
	//Only for controlling debugging message
	private static final boolean DEBUG_MODE = true;
	public static void outputDebugMessage (String aString){
		if(DEBUG_MODE){
			System.out.print(aString);
		}
	}
	
	public static void outputLine(String aString) {
		System.out.println(aString);
	}
	
	public static void output(String aString){
		System.out.print(aString);
	}

	
	private AppView(){
		
	}
	
	public static int inputSourceVertex(){
		int sourceVertex;
		String scannedToken;
		while(true){
			AppView.output("? 출발 vertex를 입력하시오: ");
			scannedToken = AppView.scanner.next();
			try{
				sourceVertex = Integer.parseInt(scannedToken);
				return sourceVertex;
			}
			catch(NumberFormatException e){
				AppView.outputLine("[오류] 출발 vertex 입력에 오류가 있습니다: " + scannedToken);
				sourceVertex = AppView.inputSourceVertex();
			}
			return sourceVertex;
		}
	}

	public static int inputNumberOfVertices(){
		int numberOfVertices;
		String scannedToken;
		while(true){
			AppView.output("? vertex 수를 입력하시오 : ");
			scannedToken = AppView.scanner.next();
			try{
				numberOfVertices = Integer.parseInt(scannedToken);
				return numberOfVertices;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(오류) Vertex 수 입력에 오류가 있습니다. : " + scannedToken);
			}
			
		}
	}
	
	public static int inputNumberOfEdges(){
		int numberOfEdges;
		String scannedToken;
		while(true){
			AppView.output("? edge 수를 입력하시오 : ");
			scannedToken = AppView.scanner.next();
			try{
				numberOfEdges = Integer.parseInt(scannedToken);
				return numberOfEdges;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(오류) edge 수 입력에 오류가 있습니다. : " + scannedToken);
			}
			
		}
	}
	
	public static int inputTailVertex(){
		int tailVertex;
		String scannedToken;
		while(true){
			AppView.output("? tail vertex 를 입력하시오 : ");
			scannedToken = AppView.scanner.next();
			try{
				tailVertex = Integer.parseInt(scannedToken);
				return tailVertex;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(오류) tail vertex 입력에 오류가 있습니다. : " + scannedToken);
			}
			
		}
	}
	
	public static int inputHeadVertex(){
		int headVertex;
		String scannedToken;
		while(true){
			AppView.output("? head vertex 를 입력하시오 : ");
			scannedToken = AppView.scanner.next();
			try{
				headVertex = Integer.parseInt(scannedToken);
				return headVertex;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(오류) head vertex 입력에 오류가 있습니다. : " + scannedToken);
			}
			
		}
	}
	
	//edge에 cost가 붙어 있는 weighted graph를 다루기 위한 함수
	public static int inputCost(){
		int cost;
		String scannedToken;
		while(true){
			AppView.output("? cost 를 입력하시오 : ");
			scannedToken = AppView.scanner.next();
			try{
				cost = Integer.parseInt(scannedToken);
				return cost;
			}
			catch(NumberFormatException e){
				AppView.outputLine("[오류] cost 입력에 오류가 있습니다: " + scannedToken);
			}
		}
	}
}
