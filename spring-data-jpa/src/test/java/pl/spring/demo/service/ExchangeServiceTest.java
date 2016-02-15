package pl.spring.demo.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class ExchangeServiceTest {

	@Autowired
	private ExchangeService exchangeService;

	@Test
	public void testShouldCorrectlyChangePlnToEuro() {
		// given
		final Double priceInPln = 100.0;
		// when
		Double changedPlnToEuro = exchangeService.changePlnToEur(priceInPln);
		Double ratio = priceInPln / changedPlnToEuro;
		// then
		assertNotNull(changedPlnToEuro);
		assertTrue(ratio > 3.8 && ratio < 4.2);
	}

	@Test
	public void testShouldCorrectlyChangeEuroToPln() {
		// given
		final Double priceInEuro = 25.0;
		// when
		Double changedEuroToPln = exchangeService.changeEurToPln(priceInEuro);
		Double ratio = changedEuroToPln / priceInEuro;
		// then
		assertNotNull(changedEuroToPln);
		assertTrue(ratio > 3.8 && ratio < 4.2);
	}

}