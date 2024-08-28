package Service;

import java.util.List;

import DAO.MemberReviewDAO;
import DAO.MemberReviewDAOImpl;
import DTO.Reply;
import DTO.Review;

public class MemberReviewServiceImpl implements MemberReviewService {
	private static MemberReviewService instance = new MemberReviewServiceImpl();
	MemberReviewDAO memberReviewDAO = new MemberReviewDAOImpl();
			
	private MemberReviewServiceImpl() {}
    public static MemberReviewService getInstance() {
		return instance;
	}
	@Override
	public void reviewInsert(String title, String content, int carStar, int dealerStar) throws Exception{
            memberReviewDAO.reviewInsert(title, content, carStar, dealerStar);

	}

	@Override
	public List<Review> reviewSelectAll() throws Exception{
		List<Review> list = memberReviewDAO.reviewSelectAll();
		return list;
	}

	

	@Override
	public void reviewDelete(int sessionNum) throws Exception{
		int result = memberReviewDAO.reviewDelete(sessionNum);
	}
	@Override
	public int purchaseNumFindByMemberSessionNum(int sessionNum) throws Exception{
		int purchaseNo = memberReviewDAO.purchaseNumFindByMemberSessionNum(sessionNum);
		
		return purchaseNo;
	}
	

}
