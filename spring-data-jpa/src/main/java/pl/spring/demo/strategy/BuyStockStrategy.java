package pl.spring.demo.strategy;

import java.util.Map;

public interface BuyStockStrategy {
	public Map<String,Object> getBuyStrategyDecisions(String date,Double availableCash);
}
