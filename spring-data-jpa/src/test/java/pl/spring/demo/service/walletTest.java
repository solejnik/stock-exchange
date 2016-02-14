package pl.spring.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.StockEntity;
import pl.spring.demo.to.StockTo;
import pl.spring.demo.wallet.Cash;
import pl.spring.demo.wallet.SmartWallet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class walletTest {

	@Autowired
	private StockService stockService  ;
	@Autowired
	private SmartWallet smartWallet;

	@Test
	public void testShouldFindBookByCriteriaTitleAuthorLibraryName() {
		//given
		List<Cash> cash = new ArrayList<Cash>();
		cash.add(new Cash("PLN", 5000.0));
		cash.add(new Cash("EUR", 500.0));
		smartWallet.setCash(cash);
		smartWallet.ballance();
		stockService.initStockService();
		List<Date> allDates = stockService.getAllDates();
		stockService.countStocksAvgToDateAndDaysAmount("TPSA", "2013-01-04", 5);
		Double avgOfStockBetweenDates1 = stockService.getAvgOfStockBetweenDates("TPSA", "2013-01-04");
		Double avgOfStockBetweenDates2 = stockService.getMaxOfStockBetweenDates("TPSA", "2013-01-04");
		Double avgOfStockBetweenDates3 = stockService.getMinOfStockBetweenDates("TPSA", "2013-01-04");
	}

}