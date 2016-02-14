package pl.spring.demo.strategy.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.service.StockService;
import pl.spring.demo.strategy.BuyStockStrategy;
import pl.spring.demo.to.StockTo;

@Component("buyStrategy2")
public class BuyStrategyImpl2 implements BuyStockStrategy {
	@Autowired
	private StockService stockService;

	@Override
	public Map<String, Object> getBuyStrategyDecisions(String date, Double availableCash) {
		Map<String, Object> decisions = new HashMap<String, Object>();
		Integer amount = 0;
		String name = "";
		Double price = 0.0;
		List<StockTo> findStockByDate = stockService.findStockByDate(date);
		for (StockTo stock : findStockByDate) {
			Double avgOfStock = stockService.countStocksAvgToDateAndDaysAmount(stock.getName(), date, 10);
			if (stock.getPrice() <= avgOfStock) {
				name = stock.getName();
				price = stock.getPrice();
				break;
			}
		}
		Double ratio = price == 0.0 ? 0.0 : (availableCash / price) * 0.8;
		amount = ratio.intValue();
		decisions.put("name", name);
		decisions.put("amount", amount);
		decisions.put("price", price);
		return decisions;
	}

}
