package pl.spring.demo.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.spring.demo.ownedstock.OwnedStock;

@Component
@Scope("prototype")
public class StockManager {

	private List<OwnedStock> ownedStocks= new ArrayList<OwnedStock>();

	public StockManager() {
	}

	public void addStocks(OwnedStock ownedStock) {
		if(ownedStock!=null){
			OwnedStock stock = getOwnedStock(ownedStock.getName());
			if (ownedStock != null && stock != null && stock.getAmount() > 0 && ownedStock.getAmount() > 0
					&& ownedStock.getSigned()) {
				stock.setAmount(stock.getAmount() + ownedStock.getAmount());
			} else if (stock == null && ownedStock.getAmount() > 0 && ownedStock.getSigned()) {
				ownedStocks.add(ownedStock);
			}
		}
	}

	public OwnedStock removeStocks(String stockName, Integer stockAmount) {
		OwnedStock ownedStock = getOwnedStock(stockName);
		if (ownedStock != null && ownedStock.getAmount() >= stockAmount) {
			ownedStock.setAmount(ownedStock.getAmount() - stockAmount);
			return new OwnedStock(ownedStock.getName(),stockAmount,true);
		}
		return null;
	}

	public OwnedStock getOwnedStock(String name) {
		for (OwnedStock ownedStock : ownedStocks) {
			if (ownedStock.getName().equals(name)) {
				return ownedStock;
			}
		}
		return null;
	}

	public List<OwnedStock> getOwnedStocks() {
		return new ArrayList<OwnedStock>(ownedStocks);
	}

	public void printStocks() {
		for (OwnedStock stock : ownedStocks) {
			System.out.println(stock);
		}
	}
	
	public void clearStocks(){
		ownedStocks.clear();
	}

}
