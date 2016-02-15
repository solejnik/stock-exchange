package pl.spring.demo.stockexchange;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.stockexchange.StockExchange;
import pl.spring.demo.to.StockTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonStockExchangeTest-context.xml")
public class StockExchangeTest {

	@Autowired
	private StockExchange stockExchange;

	@Test
	public void testShouldGetDates() {
		//when
		List<Date> dates = stockExchange.getDates();
		//then
		assertNotNull(dates);
		assertTrue(dates.size()>0);
	}
	
	@Test
	public void testShouldGetStocks() {
		//when
		List<StockTo> stocks = stockExchange.getStocks();
		//then
		assertNotNull(stocks);
		assertTrue(stocks.size()>0);
	}
	
	@Test
	public void testTwiceGetStocksShouldGetDifferentStocks() {
		//when
		List<StockTo> firstStocks = stockExchange.getStocks();
		List<StockTo> secondStocks = stockExchange.getStocks();
		//then
		assertNotNull(firstStocks);
		assertTrue(firstStocks.size()>0);
		assertNotNull(secondStocks);
		assertTrue(secondStocks.size()>0);
		for(int i=0;i<Math.min(firstStocks.size(), secondStocks.size());i++){
			assertFalse(firstStocks.get(i).equals(secondStocks.get(i)));
		}
	}
}