package pl.spring.demo.provider;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonProviderTest-context.xml")
public class DateProviderTest {

	@Autowired
	private DatesProvider dateProvider;

	@Test
	public void testShouldGetDateList() {
		// when
		List<Date> dates = dateProvider.getDates();
		// then
		assertNotNull(dates);
		assertTrue(dates.size() > 0);
	}

}