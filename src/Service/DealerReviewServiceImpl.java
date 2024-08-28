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
		dao.replyInsert(reviewNo, content);

	}



	@Override
	public int findBySessionNum(int sessionNum) throws Exception 
	{	
		int value=dao.purchaseNumFindByDealerSessionNum(sessionNum);
		
		return value; 
	}

}
