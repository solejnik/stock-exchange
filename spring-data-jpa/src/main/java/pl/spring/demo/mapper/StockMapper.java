package pl.spring.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import pl.spring.demo.entity.StockEntity;
import pl.spring.demo.to.StockTo;

public class StockMapper {

    public static StockTo map(StockEntity stockEntity) {
        if (stockEntity != null) {
            return new StockTo(stockEntity.getId(), stockEntity.getName(), stockEntity.getPrice(),stockEntity.getDate().toString());
        }
        return null;
    }

    public static StockEntity map(StockTo stockTo) {
        if (stockTo != null) {
				return new StockEntity(stockTo.getId(),stockTo.getName(),stockTo.getPrice(),java.sql.Date.valueOf(stockTo.getDate()));
        }
        return null;
    }

    public static List<StockTo> map2To(List<StockEntity> stockEntities) {
        return stockEntities.stream().map(StockMapper::map).collect(Collectors.toList());
    }

    public static List<StockEntity> map2Entity(List<StockTo> stockEntities) {
        return stockEntities.stream().map(StockMapper::map).collect(Collectors.toList());
    }

}
