package pl.spring.demo.provider;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.wallet.Cash;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonProviderTest-context.xml")
public class CashProviderTest {

	@Autowired
	private CashProvider cashProvider;
	


	@Test
	public void testShouldGetCashList() {
		//when
		List<Cash> cash = cashProvider.getStocks();
		//then
		assertNotNull(cash);
		assertTrue(cash.size()>0);
	}
	
	

}