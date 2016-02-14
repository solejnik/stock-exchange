package pl.spring.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "STOCK",uniqueConstraints = { @UniqueConstraint( columnNames = { "NAME", "DATE" } ) })
public class StockEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 50)
	String name;
	@Column(nullable = false, length = 50)
	Double price;
	@Column(nullable = false, length = 50)
	@Temporal(TemporalType.DATE)
	Date date;
	public StockEntity(){
	}
	public StockEntity(Long id,String name, Double price, Date date) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.date = date;
	}
	public StockEntity(String name, Double price, Date date) {
		this.name = name;
		this.price = price;
		this.date = date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	
	
}
