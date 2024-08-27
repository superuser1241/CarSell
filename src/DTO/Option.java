package DTO;

public class Option {
	int optionNo;
	String optionName;
	int optionPrice;
	
	public Option() 	{}
	public Option(int optionNo, String optionName, int optionPrice) {
		super();
		this.optionNo = optionNo;
		this.optionName = optionName;
		this.optionPrice = optionPrice;
	}



	public int getOptionNo() {
		return optionNo;
	}



	public String getOptionName() {
		return optionName;
	}



	public int getOptionPrice() {
		return optionPrice;
	}



	public void setOptionNo(int optionNo) {
		this.optionNo = optionNo;
	}



	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}



	public void setOptionPrice(int optionPrice) {
		this.optionPrice = optionPrice;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[ 옵션번호 = ");
		builder.append(optionNo);
		builder.append(", 옵션명 = ");
		builder.append(optionName);
		builder.append(", 가격 = ");
		builder.append(optionPrice);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
