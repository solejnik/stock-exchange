package pl.spring.demo.wallet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonWalletTest-context.xml")
public class SmartWalletTest {

	private SmartWallet smartWallet;
	private List<Cash> list  ;
	
	@Before
	public void setUp(){
		smartWallet = new SmartWallet();
		list = new ArrayList<Cash>(Arrays.asList(new Cash("PLN",1000.0)));
		smartWallet.setCash(list);
	}

	@Test
	public void testShouldPushCashToWallet() {
		//given
		Cash cashToPush = new Cash("PLN",500.0,true);
		//when
		smartWallet.pushCashToWallet(cashToPush);
		//then
		assertTrue(smartWallet.getCash("PLN").getAmount().doubleValue()==1500.0);
	}
	
	@Test
	public void testShouldNotPushNotSignedCashToWallet() {
		//given
		Cash cashToPush = new Cash("PLN",500.0);
		//when
		smartWallet.pushCashToWallet(cashToPush);
		//then
		assertFalse(cashToPush.getSigned());
		assertTrue(smartWallet.getCash("PLN").getAmount().doubleValue()==1000.0);
	}
	
	@Test
	public void testShouldTakeCashFromWallet() {
		//given
		Double amountToTake = 500.0;
		//when
		Cash cashTookedFromWallet = smartWallet.takeCashFromWallet(amountToTake);
		//then
		assertNotNull(cashTookedFromWallet);
		assertTrue(smartWallet.getCash("PLN").getAmount().doubleValue()==500);
	}

}