package pl.spring.demo.ownedstock;

public class OwnedStock {
	private String name;
	private Integer amount;
	private Boolean signed;

	public OwnedStock(String name, Integer amount) {
		super();
		this.name = name;
		this.amount = amount;
		this.signed = false;
	}
	
	public OwnedStock(String name, Integer amount,Boolean signed) {
		super();
		this.name = name;
		this.amount = amount;
		this.signed = signed;
	}
	public OwnedStock(OwnedStock oldStock) {
		super();
		this.name = oldStock.getName();
		this.amount = oldStock.getAmount();
		this.signed = oldStock.getSigned();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Boolean getSigned() {
		return signed;
	}

	public void setSigned(Boolean signed) {
		this.signed = signed;
	}

	@Override
	public String toString() {
		return "OwnedStock [name=" + name + ", amount=" + amount + ", signed=" + signed + "]";
	}

}
