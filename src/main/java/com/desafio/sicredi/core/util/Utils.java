package com.desafio.sicredi.core.util;

import java.util.regex.Pattern;

public class Utils {
	private Utils() {}

	public static boolean contemApenasDigito(String texto) {
		return Pattern.matches("\\d*", texto);
	}

}
