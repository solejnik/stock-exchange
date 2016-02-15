package pl.spring.demo.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.ExchangeEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonBrockerOfficeTest-context.xml")
public class ExchangeDaoImplTest {

    @Autowired
    private ExchangeDao exchangeDao;

    @Test
    public void testShouldFindBookById() {
        // given
        final long id = 1l;
        // when
       ExchangeEntity findOne = exchangeDao.findOne(id);
        // then
        assertNotNull(findOne);
    }


}
