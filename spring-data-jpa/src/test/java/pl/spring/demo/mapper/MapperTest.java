package pl.spring.demo.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pl.spring.demo.entity.StockEntity;
import pl.spring.demo.to.StockTo;

public class MapperTest {

	@Test
	public void testMapToToEntity() {
		// given
		StockTo stockToMap = new StockTo(1l, "name", 0.0, "2015-05-05");
		// when
		StockEntity mappedStock = StockMapper.map(stockToMap);
		// then
		assertNotNull(mappedStock);
		assertEquals(new Long(1), mappedStock.getId());
		assertEquals("name", mappedStock.getName());
		assertEquals(new Double(0.0), mappedStock.getPrice());
		assertEquals("2015-05-05", mappedStock.getDate().toString());
	}

	@Test
	public void testMapEntityToTo() {
		// given
		StockEntity stockToMap = new StockEntity(1l, "name", 0.0, java.sql.Date.valueOf("2015-05-05"));
		// when
		StockTo mappedStock = StockMapper.map(stockToMap);
		// then
		assertNotNull(mappedStock);
		assertEquals(new Long(1), mappedStock.getId());
		assertEquals("name", mappedStock.getName());
		assertEquals(new Double(0.0), mappedStock.getPrice());
		assertEquals("2015-05-05", mappedStock.getDate());
	}
}