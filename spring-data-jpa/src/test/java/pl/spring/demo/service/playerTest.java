package pl.spring.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.player.Player;
import pl.spring.demo.wallet.SmartWallet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class playerTest {

	@Autowired
	private StockService stockService  ;
	@Autowired
	private SmartWallet smartWallet;
	@Autowired
	private Player player;
	@Autowired

	@Test
	public void testShouldFindBookByCriteriaTitleAuthorLibraryName() {
		//given
		stockService.initStockService();

	}

}