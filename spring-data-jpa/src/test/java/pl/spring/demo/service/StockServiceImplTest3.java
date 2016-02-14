package pl.spring.demo.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.ownedstock.OwnedStock;
import pl.spring.demo.provider.CashProvider;
import pl.spring.demo.strategy.BuyStockStrategy;
import pl.spring.demo.strategy.SellStockStrategy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class StockServiceImplTest3 {

	@Autowired
	private StockService stockService  ;
	@Autowired
	private BuyStockStrategy buyStockStrategy;
	@Autowired
	private SellStockStrategy sellStockStrategy;
	@Autowired
	private CashProvider cashProvider;


	@Test
	public void testShouldFindBookByCriteriaTitleAuthorLibraryName() {
		//given
		cashProvider.getStocks();
		stockService.initStockService();
		Double avgOfStockBetweenDates1 = stockService.getAvgOfStockBetweenDates("TPSA", "2013-01-04");
		Double avgOfStockBetweenDates2 = stockService.getMaxOfStockBetweenDates("TPSA", "2013-01-04");
		Double avgOfStockBetweenDates3 = stockService.getMinOfStockBetweenDates("TPSA", "2013-01-04");
		buyStockStrategy.getBuyStrategyDecisions("2013-02-05", 1000.0);
		sellStockStrategy.getSellStrategyDecisions("2013-02-05", new ArrayList<>(Arrays.asList(new OwnedStock("JSW", 10),new OwnedStock("TPSA", 50))));
	}

}