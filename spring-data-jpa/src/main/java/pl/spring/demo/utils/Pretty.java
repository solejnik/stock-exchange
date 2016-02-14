package pl.spring.demo.utils;

public class Pretty {
	public static Double pretty(Double number) {
		return (double) ((double) Math.round(number * 100) / 100);
	}
}
