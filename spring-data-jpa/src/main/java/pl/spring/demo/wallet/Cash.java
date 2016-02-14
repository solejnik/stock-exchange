package pl.spring.demo.wallet;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.spring.demo.enums.Currency;
import pl.spring.demo.utils.Pretty;

@Component
@Scope("prototype")
public class Cash {
	private Currency currency;
	private Double amount;
	private Boolean signed;

	public Cash() {
	}

	public Cash(String currency, Double amount, Boolean signed) {
		this.currency = Currency.getCurrencyByString(currency);
		this.amount = Pretty.pretty(amount);
		this.signed = signed;
	}

	public Cash(String currency, Double amount) {
		this.currency = Currency.getCurrencyByString(currency);
		this.amount = Pretty.pretty(amount);
		this.signed = false;
	}

	public Cash(Cash oldCash) {
		this.currency = oldCash.getCurrency();
		this.amount = Pretty.pretty(oldCash.getAmount());
		this.signed = oldCash.getSigned();
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = Pretty.pretty(amount);
	}

	public Boolean getSigned() {
		return signed;
	}

	public void setSigned(Boolean signed) {
		this.signed = signed;
	}

	public void signCash() {
		this.signed = true;
	}

	@Override
	public String toString() {
		return "Cash [currency=" + currency + ", amount=" + amount + ", signed=" + signed + "]";
	}

}
