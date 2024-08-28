package DTO;

import java.util.List;

public class Review {

	private int reviewNo;
	private int purchaseNo;
	private String reviewTitle;
	private String reviewContent;
	private int carStar;
	private int dealerStar;
	private String reviewDate;
	
	
	private List<Reply> replyList;

	public Review() {}
	public List<Reply> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}
	
	
	
	public Review(int reviewNo, int purchaseNo, String reviewTitle, String reviewContent, int carStar, int dealerStar,
			String reviewDate) {
		super();
		this.reviewNo = reviewNo;
		this.purchaseNo = purchaseNo;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.carStar = carStar;
		this.dealerStar = dealerStar;
		this.reviewDate = reviewDate;

	}
	
	
	public int getReviewNo() {
		return reviewNo;
	}
	public int getPurchaseNo() {
		return purchaseNo;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public int getCarStar() {
		return carStar;
	}
	public int getDealerStar() {
		return dealerStar;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public void setCarStar(int carStar) {
		this.carStar = carStar;
	}
	public void setDealerStar(int dealerStar) {
		this.dealerStar = dealerStar;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[리뷰번호 = ");
		builder.append(reviewNo);
		builder.append(", 구매번호 = ");
		builder.append(purchaseNo);
		builder.append(", 제목 = ");
		builder.append(reviewTitle);
		builder.append(", 내용 = ");
		builder.append(reviewContent);
		builder.append(", 차량평점 = ");
		builder.append(carStar);
		builder.append(", 딜러평점 = ");
		builder.append(dealerStar);
		builder.append(", 등록일=");
		builder.append(reviewDate);
		builder.append("]\n");
		builder.append("답변글: \n");
		builder.append(replyList+"\n");
		builder.append("\n");
		return builder.toString();
	}
	
	

	
	
}
