package pl.spring.demo.provider;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.StockEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonProviderTest-context.xml")
public class StocksProviderTest {

	@Autowired
	private StocksProvider stocksProvider;
	


	@Test
	public void testShouldBGetStockList() {
		//when
		List<StockEntity> stocks = stocksProvider.getStocks();
		//then
		assertNotNull(stocks);
		assertTrue(stocks.size()>0);
	}
	
	

}