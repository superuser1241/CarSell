package DTO;

public class Car {
	private String carNo;
	private String carName;
	private String drive;
	private String horsePower;
	private String fuel;
	private int quantity;
	private int price;
	private String category;
	
	
	
	public Car() {}
	public Car(String carNo, String carName, String drive, String horsePower, String fuel, int quantity, int price,
			String category) {
		super();
		this.carNo = carNo;
		this.carName = carName;
		this.drive = drive;
		this.horsePower = horsePower;
		this.fuel = fuel;
		this.quantity = quantity;
		this.price = price;
		this.category = category;
	}

	public String getCarNo() {
		return carNo;
	}
	public String getCarName() {
		return carName;
	}
	public String getDrive() {
		return drive;
	}
	public String getHorsePower() {
		return horsePower;
	}
	public String getFuel() {
		return fuel;
	}
	public int getQuantity() {
		return quantity;
	}
	public int getPrice() {
		return price;
	}
	public String getCategory() {
		return category;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public void setDrive(String drive) {
		this.drive = drive;
	}
	public void setHorsePower(String horsePower) {
		this.horsePower = horsePower;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[차량모델번호 = ");
		builder.append(carNo);
		builder.append(" , 차랑명 = ");
		builder.append(carName);
		builder.append(" , 구동방식 = ");
		builder.append(drive);
		builder.append(" , 최고출력 = ");
		builder.append(horsePower);
		builder.append(" , 연비 = ");
		builder.append(fuel);
		builder.append(" , 재고량 = ");
		builder.append(quantity);
		builder.append(" , 차량가격 = ");
		builder.append(price);
		builder.append(" , 카테고리 = ");
		builder.append(category);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}

