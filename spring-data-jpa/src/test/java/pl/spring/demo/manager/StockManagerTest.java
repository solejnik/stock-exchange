package pl.spring.demo.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.ownedstock.OwnedStock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonManagerTest-context.xml")
public class StockManagerTest {

	@Autowired
	private StockManager stockManager;

	@Test
	public void testShouldPutStock() {
		// when
		stockManager.addStocks(new OwnedStock("PKO", 55, true));
		// then
		assertTrue(stockManager.getOwnedStocks().size() > 0);
	}

	@Test
	public void testShouldNotPutNotSignedStock() {
		// when
		stockManager.addStocks(new OwnedStock("PKO", 55));
		// then
		assertFalse(stockManager.getOwnedStocks().size() > 0);
	}

	@Test
	public void testShouldTakeStock() {
		// given
		stockManager.addStocks(new OwnedStock("PKO", 55, true));
		final String stockName = "PKO";
		final Integer stockAmount = 40;
		// when
		OwnedStock removedStocks = stockManager.removeStocks(stockName, stockAmount);
		// then
		assertNotNull(removedStocks);
		assertEquals("PKO", removedStocks.getName());
		assertEquals(new Integer(40), removedStocks.getAmount());
		assertTrue(removedStocks.getSigned());
	}
	
	@Test
	public void testShouldNotTakeStockWhichDoNotHave() {
		// given
		stockManager.addStocks(new OwnedStock("PKO", 55, true));
		final String stockName = "LENOVO";
		final Integer stockAmount = 40;
		// when
		OwnedStock removedStocks = stockManager.removeStocks(stockName, stockAmount);
		// then
		assertNull(removedStocks);
	}
	
	@Test
	public void testShouldTakeNoMoreStocksThanHave() {
		// given
		stockManager.addStocks(new OwnedStock("PKO", 55, true));
		final String stockName = "PKO";
		final Integer stockAmount = 56;
		// when
		OwnedStock removedStocks = stockManager.removeStocks(stockName, stockAmount);
		// then
		assertNull(removedStocks);
	}
}