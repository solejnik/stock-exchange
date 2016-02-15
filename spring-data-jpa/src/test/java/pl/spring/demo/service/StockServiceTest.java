package pl.spring.demo.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class StockServiceTest {

	@Autowired
	private StockService stockService;

	@Test
	public void testShouldGetAvgOfStockTillDateByName() {

		// given
		final String stockname = "TPSA";
		final String date = "2013-01-04";
		// when
		Double avgOfStockBetweenDates = stockService.getMinOfStockBetweenDates(stockname, date);
		// then
		assertNotNull(avgOfStockBetweenDates);
		assertTrue(avgOfStockBetweenDates > 0.0);
	}

}