package pl.spring.demo.player.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import pl.spring.demo.manager.StockManager;
import pl.spring.demo.ownedstock.OwnedStock;
import pl.spring.demo.player.Player;
import pl.spring.demo.strategy.BuyStockStrategy;
import pl.spring.demo.strategy.SellStockStrategy;
import pl.spring.demo.wallet.Cash;
import pl.spring.demo.wallet.SmartWallet;

@Component("player5")
public class PlayerImpl5 implements Player  {
	@Autowired
	private SmartWallet smartWallet;
	@Autowired
	private StockManager stockManager;
	@Autowired
	@Qualifier(value = "buyStrategy2")
	private BuyStockStrategy buyStrategy;
	@Autowired
	@Qualifier(value = "sellStrategy1")
	private SellStockStrategy sellStrategy;

	public PlayerImpl5() {

	}

	@Override
	public Map<String, Object> initBuy(String date) {
		Map<String, Object> buyStrategyDecisions = buyStrategy.getBuyStrategyDecisions(date,
				smartWallet.getAvailableCashInPLN());
		Map<String, Object> dataToBuy = new HashMap<String, Object>();
		dataToBuy.put("name", buyStrategyDecisions.get("name"));
		dataToBuy.put("amount", buyStrategyDecisions.get("amount"));
		Cash cashToBrockerOffice = smartWallet.takeCashFromWallet(
				(Double) buyStrategyDecisions.get("price") * (Integer) buyStrategyDecisions.get("amount"));
		dataToBuy.put("cash", cashToBrockerOffice);
		return dataToBuy;
	}

	@Override
	public OwnedStock initSell(String date) {
		Map<String, Object> buyStrategyDecisions = sellStrategy.getSellStrategyDecisions(date,
				stockManager.getOwnedStocks());
		Map<String, Object> dataToSell = new HashMap<String, Object>();
		OwnedStock stockToSell = stockManager.removeStocks((String) buyStrategyDecisions.get("name"),
				(Integer) buyStrategyDecisions.get("amount"));
		dataToSell.put("stock", stockToSell);
		return stockToSell;
	}

	@Override
	public void finishSell(Cash returnedCash) {
		smartWallet.pushCashToWallet(returnedCash);
	}

	@Override
	public void finishBuy(OwnedStock returnedStock) {
		stockManager.addStocks(returnedStock);
	}

	@Override
	public void printWallet() {
		smartWallet.printWallet();
	}

	@Override
	public SmartWallet getSmartWallet() {
		return smartWallet;
	}

	@Override
	public StockManager getStockmanager() {
		return stockManager;
	}

	@Override
	public void printStocks() {
		 stockManager.printStocks();
	}

	@Override
	public void ballanceCashInWallet() {
		smartWallet.ballance();
	}
}
