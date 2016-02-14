package pl.spring.demo.wallet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.enums.Currency;
import pl.spring.demo.service.ExchangeService;
import pl.spring.demo.utils.Pretty;

@Component
public class Ballancer {
	@Autowired
	private ExchangeService exchangeService;

	public Ballancer() {
	}

	public void ballanceWallet(List<Cash> cash) {
		Double pln = 0.0;
		Double euro = 0.0;
		Cash plnCash = null, euroCash = null;
		for (Cash casH : cash) {
			if (casH.getCurrency().equals(Currency.PLN)) {
				pln = casH.getAmount();
				plnCash = casH;
			} else if (casH.getCurrency().equals(Currency.EUR)) {
				euro = casH.getAmount();
				euroCash = casH;
			}
		}
		Double ratioPLNtoEUR = pln / (exchangeService.changeEurToPln(euro));
		if (ratioPLNtoEUR < 0.81 || ratioPLNtoEUR > 1.22) {
			Double halfCashInPLN = (pln + (exchangeService.changeEurToPln(euro))) / 2;
			if (halfCashInPLN > pln) {
				euro -= exchangeService.changePlnToEur(halfCashInPLN - pln);
				plnCash.setAmount(Pretty.pretty(halfCashInPLN));
				euroCash.setAmount(Pretty.pretty(euro));
			} else if (halfCashInPLN < pln) {
				euro += exchangeService.changePlnToEur(pln - halfCashInPLN);
				plnCash.setAmount(Pretty.pretty(halfCashInPLN));
				euroCash.setAmount(Pretty.pretty(euro));
			}
		}
	}

	public double balanceToPLN(List<Cash> cash) {
		Double pln = 0.0;
		Double euro = 0.0;
		Cash plnCash = null, euroCash = null;
		for (Cash casH : cash) {
			if (casH.getCurrency().equals(Currency.PLN)) {
				pln = casH.getAmount();
				plnCash = casH;
			} else if (casH.getCurrency().equals(Currency.EUR)) {
				euro = casH.getAmount();
				euroCash = casH;
			}
		}
		Double all = pln + (exchangeService.changeEurToPln(euro));
		all = Pretty.pretty(all);
		plnCash.setAmount(Pretty.pretty(all));
		euroCash.setAmount(0.0);
		return Pretty.pretty(all);
	}
}
