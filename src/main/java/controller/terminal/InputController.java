package controller.terminal;

import model.ScannerSingleton;

import java.util.Scanner;

public class InputController {
	private Scanner scanner;
	private static InputController instance = null;

	private InputController() {
		scanner = ScannerSingleton.getInstance().getScanner();
	}

	public static synchronized InputController getInstance() {
		if (instance == null) {
			instance = new InputController();
		}
		return instance;
	}

	public int[] handleInput(String input) {
		if (input == null || input.isEmpty()) {
			System.out.println("Ungueltige Eingabe (Eingabe ist leer)");
			return null;
		} else {
			int[] idAndAmount = seperateString(input);
			if (idAndAmount.length != 2) {
				System.out.println("Ungueltige Eingabe (Falsche Menge an Integer)");
				return null;
			}
			return idAndAmount;
		}
	}

	public int[] seperateString(String string) {
		if (string == null || string.isEmpty()) {
			System.out.println("Ungueltige Eingabe (Eingabe ist leer)");
			return new int[]{0, 0};
		}
		String[] tokens = string.split(" ");
		int[] idAndAmount = new int[tokens.length];
		for (int i = 0; i < tokens.length; i++) {
			try {
				idAndAmount[i] = Integer.parseInt(tokens[i]);
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return idAndAmount;
	}

	public char stringToChar() {
		String charInput = scanner.next();
		charInput = charInput.toLowerCase();
		return charInput.charAt(0);
	}

	public void swallowLeftOverNewline() {
		//if (scanner.hasNextLine()) {
			scanner.nextLine();

		//}
	}
}
