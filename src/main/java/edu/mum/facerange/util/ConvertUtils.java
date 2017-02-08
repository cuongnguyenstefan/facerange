package edu.mum.facerange.util;

public class ConvertUtils {
	public static int parseInt(Object value, int defaultValue) {
		try {
			if (value == null) return defaultValue;
			
			return Integer.parseInt(value.toString());
		} catch (NumberFormatException nfe) {
			// Log exception.
			return defaultValue;
		}
	}
}
