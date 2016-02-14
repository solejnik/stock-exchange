package pl.spring.demo.enums;

public enum Currency {
	PLN(), EUR();


	public static Currency getCurrencyByString(String currency) {
		switch (currency) {
		case "PLN":
			return PLN;
		case "EUR":
			return EUR;
		default:
			return null;
		}
	}
}
