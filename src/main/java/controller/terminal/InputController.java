package controller.terminal;

public class InputController {
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
}
