package DTO;

public class Reply {

	private int replyNo;
	private int replyReviewNo;
	private String replyContent;
	private String replyDate;

	
	public Reply () {}
	
	
	public Reply(int replyNo, int replyReviewNo, String replyContent, String replyDate) {
		super();
		this.replyNo = replyNo;
		this.replyReviewNo = replyReviewNo;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
	}


	public int getReplyNo() {
		return replyNo;
	}


	public int getReplyReviewNo() {
		return replyReviewNo;
	}


	public String getReplyContent() {
		return replyContent;
	}


	public String getReplyDate() {
		return replyDate;
	}


	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}


	public void setReplyReviewNo(int replyReviewNo) {
		this.replyReviewNo = replyReviewNo;
	}


	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}


	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[답변글번호 = ");
		builder.append(replyNo);
		builder.append(", 리뷰글번호 = ");
		builder.append(replyReviewNo);
		builder.append(", 내용 = ");
		builder.append(replyContent);
		builder.append(", 답변등록일 = ");
		builder.append(replyDate);
		builder.append("]");
		return builder.toString();
	}

	
	
}
