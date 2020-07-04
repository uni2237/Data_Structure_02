package app;
import java.util.Scanner;

//MVC �� �� V View�� �ش��Ѵ�. app�� ������� ����ϸ�
//AppController �͸� ���踦 �ΰ�, model�� �ش��ϴ� class�ʹ� �������̾�� �Ѵ�.

//instance�� �ʿ�� ���� �ʴ� class�̹Ƿ� static class�� ������
//java������ static class�� �����ϴ� ����� �������� �ʴ´�. �׷��Ƿ�  static class�� ȿ���� ���� ���Ͽ�
//class�� final�� �����Ͽ� ����� ���� �����ڴ� private�� �����Ͽ�, instance ��ü ������ ���´�.
//�� class�� ������ ��� �Լ��� ������ static���� �����Ѵ�.
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
			AppView.output("? ��� vertex�� �Է��Ͻÿ�: ");
			scannedToken = AppView.scanner.next();
			try{
				sourceVertex = Integer.parseInt(scannedToken);
				return sourceVertex;
			}
			catch(NumberFormatException e){
				AppView.outputLine("[����] ��� vertex �Է¿� ������ �ֽ��ϴ�: " + scannedToken);
				sourceVertex = AppView.inputSourceVertex();
			}
			return sourceVertex;
		}
	}

	public static int inputNumberOfVertices(){
		int numberOfVertices;
		String scannedToken;
		while(true){
			AppView.output("? vertex ���� �Է��Ͻÿ� : ");
			scannedToken = AppView.scanner.next();
			try{
				numberOfVertices = Integer.parseInt(scannedToken);
				return numberOfVertices;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(����) Vertex �� �Է¿� ������ �ֽ��ϴ�. : " + scannedToken);
			}
			
		}
	}
	
	public static int inputNumberOfEdges(){
		int numberOfEdges;
		String scannedToken;
		while(true){
			AppView.output("? edge ���� �Է��Ͻÿ� : ");
			scannedToken = AppView.scanner.next();
			try{
				numberOfEdges = Integer.parseInt(scannedToken);
				return numberOfEdges;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(����) edge �� �Է¿� ������ �ֽ��ϴ�. : " + scannedToken);
			}
			
		}
	}
	
	public static int inputTailVertex(){
		int tailVertex;
		String scannedToken;
		while(true){
			AppView.output("? tail vertex �� �Է��Ͻÿ� : ");
			scannedToken = AppView.scanner.next();
			try{
				tailVertex = Integer.parseInt(scannedToken);
				return tailVertex;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(����) tail vertex �Է¿� ������ �ֽ��ϴ�. : " + scannedToken);
			}
			
		}
	}
	
	public static int inputHeadVertex(){
		int headVertex;
		String scannedToken;
		while(true){
			AppView.output("? head vertex �� �Է��Ͻÿ� : ");
			scannedToken = AppView.scanner.next();
			try{
				headVertex = Integer.parseInt(scannedToken);
				return headVertex;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(����) head vertex �Է¿� ������ �ֽ��ϴ�. : " + scannedToken);
			}
			
		}
	}
	
	//edge�� cost�� �پ� �ִ� weighted graph�� �ٷ�� ���� �Լ�
	public static int inputCost(){
		int cost;
		String scannedToken;
		while(true){
			AppView.output("? cost �� �Է��Ͻÿ� : ");
			scannedToken = AppView.scanner.next();
			try{
				cost = Integer.parseInt(scannedToken);
				return cost;
			}
			catch(NumberFormatException e){
				AppView.outputLine("[����] cost �Է¿� ������ �ֽ��ϴ�: " + scannedToken);
			}
		}
	}
}
