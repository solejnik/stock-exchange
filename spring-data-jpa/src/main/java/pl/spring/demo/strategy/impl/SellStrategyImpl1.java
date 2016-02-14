package pl.spring.demo.strategy.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.ownedstock.OwnedStock;
import pl.spring.demo.service.StockService;
import pl.spring.demo.strategy.SellStockStrategy;
import pl.spring.demo.to.StockTo;
@Component("sellStrategy1")
public class SellStrategyImpl1 implements SellStockStrategy {
	@Autowired
	private StockService stockService;

	@Override
	public Map<String, Object> getSellStrategyDecisions(String date, List<OwnedStock> availableStocks) {
		boolean brokeFirstLoop = false;
		Map<String, Object> sellStrategyDecisions = new HashMap<String, Object>();
		Double price = 0.0;
		String name = "";
		Integer amount = 0;
		List<StockTo> foundedStocks = stockService.findStockByDate(date);
		for (StockTo stock : foundedStocks) {
			if(!brokeFirstLoop){
				Double avgOfStock = stockService.countStocksAvgToDateAndDaysAmount(stock.getName(), stock.getDate(),5);
				for (OwnedStock ownedStock : availableStocks) {
					if (stock.getPrice() >= avgOfStock && ownedStock.getName().equals(stock.getName())) {
						amount = ownedStock.getAmount()/1;
						name = ownedStock.getName();
						price = stock.getPrice();
						brokeFirstLoop = true;
						break;
					}
				}
			}
		}
		Double ratio = amount * 0.25;
		amount = ratio.intValue();
		sellStrategyDecisions.put("name", name);
		sellStrategyDecisions.put("price", price);
		sellStrategyDecisions.put("amount", amount);

		return sellStrategyDecisions;
	}

}
