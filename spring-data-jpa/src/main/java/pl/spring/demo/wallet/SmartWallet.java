package pl.spring.demo.wallet;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.spring.demo.enums.Currency;
@Component
@Scope("prototype")
public class SmartWallet {


	@Autowired
	private Ballancer ballancer;
	@Resource(name="initCash")
	private List<Cash> cash = new ArrayList<Cash>();
	

	public SmartWallet() {
	}

	public void ballance() {
		ballancer.ballanceWallet(cash);
	}

	public void printWallet() {
		for (Cash casH:cash) {
			System.out.println(casH);
		}
	}
	
	public Double getAvailableCashInPLN(){
		return ballancer.balanceToPLN(cash);
	}
	
	public Cash takeCashFromWallet(Double cashInPLN){
		Cash currentCash = getCash("PLN");
		currentCash.setAmount(currentCash.getAmount()-cashInPLN);
		Cash cashToReturn = new Cash("PLN",cashInPLN);
		cashToReturn.signCash();
		return cashToReturn;
	}
	
	public void pushCashToWallet(Cash cashInPln){
		if(cashInPln.getSigned()){
			Cash currentCash = getCash("PLN");
			if(currentCash!=null){
				currentCash.setAmount(currentCash.getAmount()+cashInPln.getAmount());
			}else if(currentCash==null){
				cash.add(cashInPln);
			}
		}
	
	}
	
	public Cash getCash(String currency){
		Cash wantedCash = null;
		for(Cash casH:cash){
			if(casH.getCurrency().equals(Currency.getCurrencyByString(currency))){
				wantedCash = casH;
				break;
			}
		}
		return wantedCash;
	}

	public void setCash(List<Cash> cash) {
		this.cash = cash;
	}
	
	public void clearWallet(){
		cash.clear();
	}
	
	
}
