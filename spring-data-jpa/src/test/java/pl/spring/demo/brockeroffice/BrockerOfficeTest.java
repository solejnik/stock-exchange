package pl.spring.demo.brockeroffice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.brockeroffice.BrockerOffice;
import pl.spring.demo.enums.Currency;
import pl.spring.demo.ownedstock.OwnedStock;
import pl.spring.demo.to.StockTo;
import pl.spring.demo.wallet.Cash;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonBrockerOfficeTest-context.xml")
public class BrockerOfficeTest {

	@Autowired
	private BrockerOffice brockerOffice;

	@Test
	public void testShouldBuySignedStockFromPlayer() {
		// given
		List<StockTo> stockList = new ArrayList<StockTo>(
				Arrays.asList(new StockTo(1l, "IBM", 111.0, "2015-02-02"), new StockTo(2l, "HP", 250.0, "2015-02-02"),
						new StockTo(3l, "LENOVO", 150.0, "2015-02-02"), new StockTo(4l, "NOKIA", 20.0, "2015-02-02")));
		brockerOffice.setDailyStocksTable(stockList);
		OwnedStock stockToSell = new OwnedStock("NOKIA", 150, true);
		// when
		Cash boughtStockFromPlayer = brockerOffice.buyStockFromPlayer(stockToSell);
		// then
		assertNotNull(boughtStockFromPlayer);
		assertTrue(boughtStockFromPlayer.getSigned());
		assertEquals(Currency.getCurrencyByString("PLN"), boughtStockFromPlayer.getCurrency());
		assertEquals(new Double(3000.0), boughtStockFromPlayer.getAmount());
	}

	@Test
	public void testShouldNotBuyNotSignedStockFromPlayer() {
		// given
		List<StockTo> stockList = new ArrayList<StockTo>(
				Arrays.asList(new StockTo(1l, "IBM", 111.0, "2015-02-02"), new StockTo(2l, "HP", 250.0, "2015-02-02"),
						new StockTo(3l, "LENOVO", 150.0, "2015-02-02"), new StockTo(4l, "NOKIA", 20.0, "2015-02-02")));
		brockerOffice.setDailyStocksTable(stockList);
		OwnedStock stockToSell = new OwnedStock("NOKIA", 150);
		// when
		Cash boughtStockFromPlayer = brockerOffice.buyStockFromPlayer(stockToSell);
		// then
		assertNull(boughtStockFromPlayer);
	}

	@Test
	public void testShouldSellStockToPlayer() {
		// given
		List<StockTo> stockList = new ArrayList<StockTo>(
				Arrays.asList(new StockTo(1l, "IBM", 111.0, "2015-02-02"), new StockTo(2l, "HP", 250.0, "2015-02-02"),
						new StockTo(3l, "LENOVO", 150.0, "2015-02-02"), new StockTo(4l, "NOKIA", 20.0, "2015-02-02")));
		brockerOffice.setDailyStocksTable(stockList);
		Map<String, Object> dataToBuy = new HashMap<String, Object>();
		dataToBuy.put("cash", new Cash("PLN", 1000.0, true));
		dataToBuy.put("amount", 4);
		dataToBuy.put("name", "HP");
		// when
		OwnedStock soldStock = brockerOffice.sellStockToPlayer(dataToBuy);
		// then
		assertNotNull(soldStock);
		assertEquals(new Integer(4), soldStock.getAmount());
		assertEquals("HP", soldStock.getName());
		assertTrue(soldStock.getSigned());
	}
	
	@Test
	public void testShouldNotSellStockToPlayerWhenCashNotSigned() {
		// given
		List<StockTo> stockList = new ArrayList<StockTo>(
				Arrays.asList(new StockTo(1l, "IBM", 111.0, "2015-02-02"), new StockTo(2l, "HP", 250.0, "2015-02-02"),
						new StockTo(3l, "LENOVO", 150.0, "2015-02-02"), new StockTo(4l, "NOKIA", 20.0, "2015-02-02")));
		brockerOffice.setDailyStocksTable(stockList);
		Map<String, Object> dataToBuy = new HashMap<String, Object>();
		dataToBuy.put("cash", new Cash("PLN", 1000.0));
		dataToBuy.put("amount", 4);
		dataToBuy.put("name", "HP");
		// when
		OwnedStock soldStock = brockerOffice.sellStockToPlayer(dataToBuy);
		// then
		assertNull(soldStock);
	}

}