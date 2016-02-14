package pl.spring.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
public class StockServiceImplTest2 {

	@Autowired
	private StockService stockService  ;
	


	@Test
	public void testShouldFindBookByCriteriaTitleAuthorLibraryName() {
		List<Date> allDates = stockService.getAllDates();
		System.out.println(allDates.size());
	}

}