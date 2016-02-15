package pl.spring.demo.provider;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonProviderTest-context.xml")
public class TitleProviderTest {

	@Autowired
	private TitleProvider titleProvider;

	@Test
	public void testShouldBGetStockList() {
		// when
		String title = titleProvider.getTitle();
		// then
		assertNotNull(title);
		assertTrue(title.length() > 0);
	}

}