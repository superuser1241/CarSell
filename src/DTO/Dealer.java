package DTO;

public class Dealer {

	private int dealerNo;
	private String dealerName;
	private String dealerId;
	private String dealerPass;
	private String dealerOption;
	private String self;
	private int rate;
	
	
	public Dealer () {}

	public Dealer(int dealerNo, String dealerName, String dealerId, String dealerPass, String dealerOption, String self,
			int rate) {
		super();
		this.dealerNo = dealerNo;
		this.dealerId = dealerId;
		this.dealerName = dealerName;
		this.dealerPass = dealerPass;
		this.dealerOption = dealerOption;
		this.self = self;
		this.rate = rate;
	}

	public int getDealerNo() {
		return dealerNo;
	}

	public String getDealerId() {
		return dealerId;
	}

	public String getDealerName() {return dealerName;}

	public String getDealerPass() {
		return dealerPass;
	}

	public String getDealerOption() {
		return dealerOption;
	}

	public String getSelf() {
		return self;
	}

	public int getRate() {
		return rate;
	}

	public void setDealerNo(int dealerNo) {
		this.dealerNo = dealerNo;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}

	public void setDealerPass(String dealerPass) {
		this.dealerPass = dealerPass;
	}

	public void setDealerOption(String dealerOption) {
		this.dealerOption = dealerOption;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	
	
	
	
	
}