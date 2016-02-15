package pl.spring.demo.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.enums.Currency;
import pl.spring.demo.ownedstock.OwnedStock;
import pl.spring.demo.wallet.Cash;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonPlayerTest-context.xml")
public class PlayerTest {

	@Autowired
	@Qualifier("player")
	private Player player;

	@Before
	public void setUp() {
		player.getSmartWallet().clearWallet();
		player.getStockmanager().clearStocks();
	}

	@Test
	public void testShouldFinishBuyWithSignedStock() {
		// given
		OwnedStock boughtStock = new OwnedStock("PKO", 10, true);
		// when
		player.finishBuy(boughtStock);
		// then
		assertTrue(player.getStockmanager().getOwnedStocks().size() > 0);
	}

	@Test
	public void testShouldNotFinishBuyWithNotSignedStock() {
		// given
		OwnedStock boughtStock = new OwnedStock("PKO", 10);
		// when
		player.finishBuy(boughtStock);
		// then
		assertTrue(player.getStockmanager().getOwnedStocks().size() == 0);
	}

	@Test
	public void testShouldFinishSellWithSignedCash() {
		// given
		final String currency = "PLN";
		Cash cashToPush = new Cash(currency, 200.0, true);
		// when
		player.finishSell(cashToPush);
		// then
		Cash pushedCash = player.getSmartWallet().getCash(currency);
		assertNotNull(pushedCash);
		assertEquals(Currency.getCurrencyByString(currency), pushedCash.getCurrency());
		assertEquals(new Double(200), pushedCash.getAmount());
		assertTrue(pushedCash.getSigned());
	}

}