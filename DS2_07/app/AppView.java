package app;

import java.util.Scanner;

public final class AppView {

	public static Scanner scanner = new Scanner(System.in);

	private static final boolean DEBUG_MODE = true;

	public static void outputDebugMessage(String aString) {
		if (DEBUG_MODE) {
			System.out.println(aString);
		}
	}

	public static int inputSourceVertex() {
		int sourceVertex;
		String scannedToken;
		while (true) {
			AppView.output("? 출발 vertex를 입력하시오: ");
			scannedToken = AppView.scanner.next();
			try {
				sourceVertex = Integer.parseInt(scannedToken);
				return sourceVertex;
			} catch (NumberFormatException e) {
				AppView.outputLine("[오류] 출발 vertex 입력에 오류가 있습니다: " + scannedToken);
			}
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

	public static int inputNumberOfVertices() {
		/*
		 * 적절한 prompt 메시지를 내보내면서, vertex 수를 입력 받는다.  정수가 아니면 오류 처리하고, 다시 입력 받는다. 
		 * try-catch 구문을 이용한다.
		 */

		int numberOfVertices;
		String scannedToken;
		while (true) {
			AppView.output("? vertex 수를 입력하시오: ");
			scannedToken = AppView.scanner.next();
			try {
				numberOfVertices = Integer.parseInt(scannedToken);
				return numberOfVertices;

			} catch (NumberFormatException e) {
				AppView.outputLine("(오류) Vertex 수 입력에 오륙가 있습니다: " + scannedToken);

			}
		}
	}

	public static int inputNumberOfEdges() {
		// inputNumberOfVertices() 와 동일한 방법으로 edge 수를 입력 받는다
		int numberOfEdges;
		String scannedToken;
		while (true) {
			AppView.output("? edge 수를 입력하시오: ");
			scannedToken = AppView.scanner.next();
			try {
				numberOfEdges = Integer.parseInt(scannedToken);
				return numberOfEdges;
			} catch (NumberFormatException e) {
				AppView.outputLine("(오류) edge 수 입력에 오류가 있습니다: " + scannedToken);
			}
		}
	}

	public static int inputTailVertex() {
		// Tail vertex 로, 숫자 하나를 입력 받는다.
		int tailVertex;
		String scannedToken;
		while (true) {
			AppView.output("? tail vertex 를 입력하시오. ");
			scannedToken = AppView.scanner.next();
			try {
				tailVertex = Integer.parseInt(scannedToken);
				return tailVertex;
			} catch (NumberFormatException e) {
				AppView.outputLine("(오류)head vertex 입력에 오류가 있습니다: " + scannedToken);

			}
		}

	}

	public static int inputHeadVertex() {
		// Head vertex 로, 숫자 하나를 입력 받는다.
		int headVertex;
		String scannedToken;
		while (true) {
			AppView.output("? head vertex 를 입력하시오:");
			scannedToken = AppView.scanner.next();
			try {
				headVertex = Integer.parseInt(scannedToken);
				return headVertex;
			} catch (NumberFormatException e) {
				AppView.outputLine("(오류)head vertex 입력에 오류가 있습니다: " + scannedToken);

			}
		}

	}

	public static int inputCost() {
		int cost;
		String scannedToken;
		while (true) {
			AppView.output("? cost 를 입력하시오: ");
			scannedToken = AppView.scanner.next();
			try {
				cost = Integer.parseInt(scannedToken);
				return cost;

			} catch (NumberFormatException e) {
				AppView.outputLine("[오류] cost 입력에 오류가 있습니다: " + scannedToken);

			}
		}
	}
}
