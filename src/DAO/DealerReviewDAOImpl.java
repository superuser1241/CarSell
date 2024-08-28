package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBManager.DBManager;
import DTO.DealerSession;
import DTO.MemberSession;
import DTO.Reply;
import DTO.Review;

public class DealerReviewDAOImpl implements DealerReviewDAO {
	
	MemberSession memberSession = MemberSession.getInstance();
	DealerSession dealerSession = DealerSession.getInstance();
	
	MemberReviewDAO memberRiviewDAO = new MemberReviewDAOImpl();
	

	@Override
	public void selectReviewByNum() throws Exception 
	{	
		int sessionNum = dealerSession.getDealerNo();
		Review re = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Review> list = new ArrayList<Review>();
		List<Reply> list2 = new ArrayList<Reply>();
		String sql = "SELECT * FROM REVIEW WHERE RE_NO = ?";
		
		
		int purchaseNo = purchaseNumFindByDealerSessionNum(sessionNum);
		
		int reviewNo  = ReviewFindByPurchaseNum(purchaseNo);
		
		
		
		
		try {
					con = DBManager.getConnection();
					ps = con.prepareStatement(sql);
					ps.setInt(1, reviewNo);
					rs = ps.executeQuery();
					
					list2 = selectReplyByNum(con, reviewNo);
					
					while(rs.next())
					{	re=new Review(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getString(7));
						re.setReplyList(list2);
						list.add(re);
						
						
					}
					System.out.println(list);
					
		}	
		 finally {
			DBManager.dbClose(con, ps, rs);
		}
		
	}

	@Override
	public List<Reply> selectReplyByNum(Connection con, int reviewNum) throws Exception {
		
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Reply> list = new ArrayList<Reply>();
		String sql = "SELECT * FROM REPLY WHERE RE_NO = ?";
		
		
		int replyNum = replyNumFindBuReviewNo(reviewNum);
		
		
		
		try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, reviewNum);
				rs = ps.executeQuery();
				
				while(rs.next())
				{
					list.add(new Reply(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4)));
					
				}

					
					
			}

		finally 
		{
			DBManager.dbClose(null, ps, rs);

		}
		return list;
	}

	@Override
	public int purchaseNumFindByDealerSessionNum(int sessionNum) throws SQLException 
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT PURCHASE_NO FROM PURCHASE WHERE DEALER_NO = ?";
		int purchaseNo = 0;

		try {
			
				con = DBManager.getConnection();
				ps = con.prepareStatement(sql);
				ps.setInt(1, sessionNum);
				rs = ps.executeQuery();
				

				if(rs.next())
				{
					purchaseNo = rs.getInt(1);
				}
		}
		finally {
			DBManager.dbClose(con, ps, rs);
		}
		return purchaseNo;
		
	}



	@Override
	public int replyInsert(int reviewNo,String content) throws Exception
	{
		int result = 0;
		int replyNo2 = 0;
		int sessionNum = dealerSession.getDealerNo();
		replyNo2 = replyNumFindBuReviewNo(reviewNo);
		if(replyNo2==0  )
		{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO REPLY(REPLY_NO,RE_NO,REPLY_CONTENT,REPLY_DATE)VALUES(REPLY_NO_SEQ.NEXTVAL ,?,?,SYSDATE)";
		
		
		try
		{	
			
				
				con=DBManager.getConnection();
				ps = con.prepareStatement(sql);
				ps.setInt(1, reviewNo);
				ps.setString(2, content);
				
				
				result = ps.executeUpdate();
				
		
		}
		
		finally
		{
			DBManager.dbClose(con, ps, null);
		}
		}else throw new Exception("이미 등록된 답글이 있습니다");
		
		return result;
	}

	@Override
	public int replyDuplication() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Review review = reviewDuplication();
		int reviewNum = review.getReviewNo();
		int replyNum = 0;
		
		String sql = "SELECT REPLY_NO FROM REPLY WHERE RE_NO = ?";

		try {
			
			
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, reviewNum);
			rs = ps.executeQuery();

			if(rs.next())
			{
				replyNum = rs.getInt(1);
			}

		} finally {
			DBManager.dbClose(con, ps, rs);

		}
		return replyNum;

	}
	
	@Override
	public Review reviewDuplication() throws Exception{
		String sql = "select * from review where purchase_no = ?";
		Review review = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int dealerNo=dealerSession.getDealerNo();
		try {
			
		int purchaseNo = purchaseNumFindByDealerSessionNum(dealerNo);
		
		
		
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, purchaseNo);
			rs = ps.executeQuery();
			if(purchaseNo==0) {
				if (rs.next()) {
					review = new Review(
							rs.getInt(1),
							rs.getInt(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5),
							rs.getInt(6),
							rs.getString(7)
					);
				return review;
				}
					
			}
			
		} 
		finally {
			DBManager.dbClose(con, ps, rs);
		}
		return null;
	}

	
	
	
	@Override
	public int replyNumFindBuReviewNo(int reviewNo) throws Exception 
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="SELECT REPLY_NO FROM REPLY WHERE RE_NO = ?";
		int replyNo = 0;
		
		try{
			
		
				con = DBManager.getConnection();
				ps = con.prepareStatement(sql);
				ps.setInt(1, reviewNo);
				rs = ps.executeQuery();
				
				if(rs.next())
				{
					replyNo = rs.getInt(1);
					
				}
				
				
		}
		finally
		{
			DBManager.dbClose(con, ps, rs);
		}
		return replyNo;
	

	
}

	@Override
	public int ReviewFindByPurchaseNum(int purchaseNo) throws Exception 
	{	
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT RE_NO FROM REVIEW WHERE PURCHASE_NO = ?";
		int reviewNo = 0;
		
		try
		{
		con = DBManager.getConnection();
		ps = con.prepareStatement(sql);
		ps.setInt(1, purchaseNo);
		rs = ps.executeQuery();
		
		if(rs.next())
		{
			reviewNo = rs.getInt(1);
		}
		}
		finally
		{
			DBManager.dbClose(con, ps, rs);
		}
		return reviewNo;
	}

	
}
