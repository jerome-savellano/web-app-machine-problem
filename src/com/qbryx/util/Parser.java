package com.qbryx.util;

public class Parser {
	
	public static <T> T parse(String s, Class<T> clazz) throws Exception {
		return clazz.getConstructor(new Class[] {String.class}).newInstance(s);
	}
}
