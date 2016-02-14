package pl.spring.demo.player;

import java.util.Map;

import pl.spring.demo.manager.StockManager;
import pl.spring.demo.ownedstock.OwnedStock;
import pl.spring.demo.wallet.Cash;
import pl.spring.demo.wallet.SmartWallet;

public interface Player {

	Map<String, Object> initBuy(String date);

	OwnedStock initSell(String date);

	void finishSell(Cash returnedCash);

	void finishBuy(OwnedStock returnedStock);

	void printWallet();

	SmartWallet getSmartWallet();

	StockManager getStockmanager();

	void printStocks();

	void ballanceCashInWallet();


}