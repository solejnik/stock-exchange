package pl.spring.demo.brockeroffice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.ownedstock.OwnedStock;
import pl.spring.demo.player.Player;
import pl.spring.demo.stockexchange.StockExchange;
import pl.spring.demo.to.StockTo;
import pl.spring.demo.utils.Pretty;
import pl.spring.demo.wallet.Cash;

@Component
public class BrockerOffice {
	@Autowired
	private StockExchange stockExchange;
	@Autowired
	private List<Player> players;
	private List<StockTo> dailyStocksTable;
	private List<Date> dates = new ArrayList<Date>();
	private Player player;

	public BrockerOffice() {
	}

	public void play(int buyStrategyNumber, int sellStrategyNumber, boolean showdailyStocks) {
		player = choosePlayer(buyStrategyNumber, sellStrategyNumber);
		for (Date date : dates) {
			updateStocks();
			Map<String, Object> initBuy = player.initBuy(date.toString());
			OwnedStock boughtStock = sellStockToPlayer(initBuy);
			player.finishBuy(boughtStock);
			OwnedStock initSell = player.initSell(date.toString());
			Cash CashFromStock = buyStockFromPlayer(initSell);
			player.finishSell(CashFromStock);
			System.out.println("Dzien " + date);
			if (showdailyStocks) {
				printDailyStocks();
			}
			System.out.println("Stan portfela");
			player.ballanceCashInWallet();
			player.printWallet();
			System.out.println("Stan akcji");
			player.printStocks();
			System.out.println("------------------------");
		}
		System.out.println(countStocksValue(player.getStockmanager().getOwnedStocks())
				+ player.getSmartWallet().getAvailableCashInPLN());
	}

	public void initBrockerOffice() {
		dates = stockExchange.getDates();
	}

	public Double countStocksValue(List<OwnedStock> ownedStocks) {
		Double value = 0.0;
		for (OwnedStock stock : ownedStocks) {
			value += getStockPrice(stock.getName()) * stock.getAmount();
		}
		return value;
	}

	public OwnedStock sellStockToPlayer(Map<String, Object> data) {
		Cash cash = (Cash) data.get("cash");
		Integer stockAmount = (Integer) data.get("amount");
		StockTo candidateStock = getStockByName((String) data.get("name"));
		if (candidateStock != null) {
			Double candidateStockPrice = candidateStock.getPrice();
			double d1 = Pretty.pretty(candidateStockPrice.doubleValue() * stockAmount);
			double d2 = cash.getAmount().doubleValue();
			if (cash.getSigned() && d1 == d2) {
				return new OwnedStock(candidateStock.getName(), stockAmount, true);
			}
		}
		return null;
	}

	public Cash buyStockFromPlayer(OwnedStock stockToSell) {
		if (stockToSell == null) {
			return new Cash("PLN", 0.0, true);
		} else if (stockToSell.getSigned() && stockToSell != null) {
			return new Cash("PLN", getStockPrice(stockToSell.getName()) * stockToSell.getAmount(), true);
		}
		return null;
	}

	private Player choosePlayer(int buyStrategyNumber, int sellStrategyNumber) {
		if (buyStrategyNumber == 1 && sellStrategyNumber == 1) {
			return getPlayerFromList("PlayerImpl1");
		} else if (buyStrategyNumber == 1 && sellStrategyNumber == 1) {
			return getPlayerFromList("PlayerImpl2");
		} else if (buyStrategyNumber == 1 && sellStrategyNumber == 2) {
			return getPlayerFromList("PlayerImpl3");
		} else if (buyStrategyNumber == 1 && sellStrategyNumber == 3) {
			return getPlayerFromList("PlayerImpl4");
		} else if (buyStrategyNumber == 1 && sellStrategyNumber == 4) {
			return getPlayerFromList("PlayerImpl5");
		} else if (buyStrategyNumber == 2 && sellStrategyNumber == 1) {
			return getPlayerFromList("PlayerImpl6");
		} else if (buyStrategyNumber == 2 && sellStrategyNumber == 2) {
			return getPlayerFromList("PlayerImpl7");
		} else if (buyStrategyNumber == 2 && sellStrategyNumber == 3) {
			return getPlayerFromList("PlayerImpl8");
		} else if (buyStrategyNumber == 2 && sellStrategyNumber == 4) {
			return getPlayerFromList("PlayerImpl9");
		} else if (buyStrategyNumber == 3 && sellStrategyNumber == 1) {
			return getPlayerFromList("PlayerImpl10");
		} else if (buyStrategyNumber == 3 && sellStrategyNumber == 2) {
			return getPlayerFromList("PlayerImpl11");
		} else if (buyStrategyNumber == 3 && sellStrategyNumber == 3) {
			return getPlayerFromList("PlayerImpl12");
		} else if (buyStrategyNumber == 3 && sellStrategyNumber == 4) {
			return getPlayerFromList("PlayerImpl13");
		} else if (buyStrategyNumber == 4 && sellStrategyNumber == 1) {
			return getPlayerFromList("PlayerImpl14");
		} else if (buyStrategyNumber == 4 && sellStrategyNumber == 2) {
			return getPlayerFromList("PlayerImpl15");
		} else if (buyStrategyNumber == 4 && sellStrategyNumber == 3) {
			return getPlayerFromList("PlayerImpl16");
		}
		return null;
	}

	private Player getPlayerFromList(String playerClassName) {
		for (Player player : players) {
			if (player.getClass().toString().contains(playerClassName)) {
				return player;
			}
		}
		return null;
	}

	private void updateStocks() {
		dailyStocksTable = stockExchange.getStocks();
	}

	private Double getStockPrice(String stockName) {
		for (StockTo stock : dailyStocksTable) {
			if (stock.getName().equals(stockName)) {
				return stock.getPrice();
			}
		}
		return null;
	}

	private StockTo getStockByName(String stockName) {
		for (StockTo stock : dailyStocksTable) {
			if (stock.getName().equals(stockName)) {
				return stock;
			}
		}
		return null;
	}

	private void printDailyStocks() {
		for (StockTo stock : dailyStocksTable) {
			System.out.println("DailyStock[" + stock.getName() + ", price" + stock.getPrice() + "]");
		}
	}
}
