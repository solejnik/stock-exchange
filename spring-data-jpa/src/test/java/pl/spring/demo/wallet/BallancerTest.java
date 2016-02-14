package pl.spring.demo.wallet;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BallancerTest {

	@Autowired
	private Ballancer ballancer;
	private List<Cash> list  ;
	
	@Before
	public void setUp(){
		list = new ArrayList<Cash>(Arrays.asList(new Cash("PLN",1000.0),new Cash("EUR",1000.0)));
	}

	@Test
	public void testShouldBalanceCashInWalletToPln() {
		//when
		ballancer.balanceToPLN(list);
		//then
		assertTrue(list.get(1).getAmount().doubleValue()==0.0);
	}
	
	@Test
	public void testShouldBalanceCashInWalletToBeEquals() {
		//when
		ballancer.ballanceWallet(list);
		//then
		double ratio = list.get(0).getAmount()/(list.get(1).getAmount()*3.9);
		assertTrue(ratio<1.22 && ratio>0.81);
	}

}