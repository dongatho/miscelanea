package com.wocn.random;

import java.util.HashMap;
import java.util.Map;

public class RandomNumber {

	public static void main(String[] arg) {
		int max, min, attempt, maxAttempts, neededNumbers;

		Map<Integer, String> map = new HashMap<Integer, String>();

		/* ------------------------------------------------- */
		System.out.println("\nRange too short for needed ramdom numbers:");
		neededNumbers = 10;
		min = 1;
		max = 5;
		attempt = 0;
		maxAttempts = 15;
		for (int i = 0; i < neededNumbers; i++) {

			int randomNum = getRandomNumber(min, max, map, attempt, maxAttempts);
			System.out.println("randomNum = " + randomNum);
			map.put(randomNum, i + "");
		}

		/* ------------------------------------------------- */
		map.clear();
		System.out
				.println("\nRange and needed ramdom numbers are equals. Increase maxAttempts for make sure.:");
		neededNumbers = 5;
		min = 1;
		max = 5;
		attempt = 0;
		maxAttempts = 20;
		for (int i = 0; i < neededNumbers; i++) {

			int randomNum = getRandomNumber(min, max, map, attempt, maxAttempts);
			System.out.println("randomNum = " + randomNum);
			map.put(randomNum, i + "");
		}

		/* ------------------------------------------------- */
		map.clear();
		System.out
				.println("\nRange bigger than ramdom numbers are equals. Increase maxAttempts for make sure.:");
		neededNumbers = 5;
		min = 1;
		max = 60;
		attempt = 0;
		maxAttempts = 20;
		for (int i = 0; i < neededNumbers; i++) {

			int randomNum = getRandomNumber(min, max, map, attempt, maxAttempts);
			System.out.println("randomNum = " + randomNum);
			map.put(randomNum, i + "");
		}

	}

	/**
	 * Genera un número aleatorio dentro de un rango(incluye los límites) y un
	 * número máximo de intentos. En caso de no encontrar (o no haber) un número
	 * aleatorio dentro del rango y en el máximo número de intentos retorna un
	 * -1. Lo mejor es tener que el número de intentos sea bastante mayor que
	 * los posibles aleatorios (diferentes) que puedan crearse.
	 * 
	 * @param minimum
	 * @param maximum
	 * @param map
	 * @param attempt
	 * @param attemptLimit
	 * @return
	 */
	public static int getRandomNumber(int minimum, int maximum,
			Map<Integer, ?> map, int attempt, int attemptLimit) {

		if (minimum > maximum) {
			throw new IllegalArgumentException("Rango mal definido.");
		}

		int randomInt = -1;

		if (attempt <= attemptLimit) {
			randomInt = (int) (Math.random() * (maximum - minimum + 1)) + 1;

			if (map.containsKey(randomInt)) {
				randomInt = getRandomNumber(minimum, maximum, map, attempt + 1,
						attemptLimit);
			}
		}

		return randomInt;

	}

}
