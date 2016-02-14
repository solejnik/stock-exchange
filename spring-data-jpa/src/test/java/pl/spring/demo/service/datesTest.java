package pl.spring.demo.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.wallet.Cash;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class datesTest {

	@Resource(name = "initDates")
	private List<Date> dates;
	@Resource(name = "initCash")
	private List<Cash> cash;
	@Autowired
	private StockService stockService;
	

	@Test
	public void testShouldFindBookByCriteriaTitleAuthorLibraryName() {
		List<Date> allDates = stockService.getAllDates();
		System.out.println("All dates "+dates.size());
	}

}