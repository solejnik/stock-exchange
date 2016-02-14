package pl.spring.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.wallet.Cash;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class listTest {

	@Resource(name="initCash")
	private List<Cash> list  ;
	

	@Test
	public void testShouldFindBookByCriteriaTitleAuthorLibraryName() {
		//given
		list.size();
	}

}