package Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.DealerReviewDAO;
import DAO.DealerReviewDAOImpl;
import DTO.Reply;
import DTO.Review;

public class DealerReviewServiceImpl implements DealerReviewService {

	DealerReviewDAO dao = new DealerReviewDAOImpl();
	
	@Override
	public void selectReviewByNum()throws Exception
	{	
			
			dao.selectReviewByNum();
			
			

	}

	@Override
	public void replyInsert(int reviewNo, String content)throws Exception
	{	
		
		int value = 0 ;
		try {
			value = dao.replyInsert(reviewNo, content);
			if(value == 0)
			{
				throw new SQLException();
			}
			
		} catch (Exception e) {
			e.printStackTrace();}
	
		
		
		
	}



	@Override
	public int findBySessionNum(int sessionNum) throws SQLException 
	{	
			int value=0; 
		try {
			value = dao.purchaseNumFindByDealerSessionNum(sessionNum);
			
			if(value == 0 )
			{
				throw new SQLException();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return value; 
	}

}
