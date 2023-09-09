package model.objects;

import java.util.Scanner;

public class ScannerSingleton {
	private static ScannerSingleton instance = null;
	private Scanner scanner;

	private ScannerSingleton() {
		scanner = new Scanner(System.in);
	}

	public static synchronized ScannerSingleton getInstance() {
		if (instance == null) {
			instance = new ScannerSingleton();
		}
		return instance;
	}

	public Scanner getScanner() {
		return scanner;
	}
}
