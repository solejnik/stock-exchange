package pl.spring.demo.strategy;

import java.util.List;
import java.util.Map;

import pl.spring.demo.ownedstock.OwnedStock;
import pl.spring.demo.to.StockTo;

public interface SellStockStrategy {
	public Map<String,Object> getSellStrategyDecisions(String date,List<OwnedStock> availableStocks);
}
