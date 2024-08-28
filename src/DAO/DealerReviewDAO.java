package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import DTO.Reply;
import DTO.Review;

public interface DealerReviewDAO 
{
	/**
	 * purchaseNumFindBySessionNum() 호출후 구매번호를 받는다.
	 * 리뷰안에 구매번호와 리턴받은 구매번호중 일치하는것이 있는지없는지
	 * 확인하고 있다면 리뷰+답글을 리턴.
	 * 답글은 selectReplyByNum()를 호출 후 connection 같이 닫기.
	 * @param dealerId
	 * @return List<Review>
	 */
	void selectReviewByNum()throws Exception;
	
	/**
	 * purchaseNumFindBySessionNum() 호출후 구매번호를 받는다.
	 * 리뷰안에 구매번호와 리턴받은 구매번호중 일치하는것이 있는지없는지
	 * 확인하고 있다면 리뷰번호 유무체크 후 있다면 그 리뷰번호에 대한
	 * 답글을 리턴.
	 * @param con
	 * @param dealerNo
	 * @return Reply리스트를 리턴
	 */
	List<Reply> selectReplyByNum(Connection con,int sessionNum)throws Exception;
	
	/**
	 * 회원or딜러 번호로 구매번호를 찾기
	 * 만약 일치하는 회원or딜러번호가 없다면 exception날리기
	 * @param int sessionNum = 현재 로그인된 회원과 딜러의 번호를 인수로 받는다
	 * @return 번호가 왔을때 구매내역과 비교후 구매번호를 찾아내서 리턴하는 메소드
	 *   
	 */
	int purchaseNumFindByDealerSessionNum(int sessionNum)throws Exception;
	
	 
	
	/**
	 * replyDuplication()호출 후 리뷰를 리턴 받고,
	 * 리뷰의 리뷰번호를 갖고 Insert한다. 
	 */
	int replyInsert(int replyNo,String content)throws Exception;
	
	
	/**
	 * MemberReviewDAO.reviewDuplication()호출 후 리뷰를 리턴을 받고
	 * 해당 리뷰번호와 reply의 리뷰번호가 같은게 있는지 여부 확인
	 * 만약 reply에 해당 구매번호가 없다면 reviewNum리턴.
	 * 있다면 이셉션 날려도됨.  
	 * @throws Exception 
	 */

	

	int replyDuplication() throws Exception;
	/**
	 * 딜러에대한 리뷰를 중복 체크 해준다.
	 * 
	 * @return Review()
	 * @throws Exception
	 */

	Review reviewDuplication() throws Exception;
	

	/**
	 * 구매번호를 이용해서 리뷰번호를 찾는다.
	 * 
	 * @param purchaseNo
	 * @return int (리뷰번호)
	 * @throws Exception
	 */

	int ReviewFindByPurchaseNum(int purchaseNo) throws Exception;

	/**
	 * 리뷰번호를 통해 답글 번호를 찾는다.
	 * @param reviewNo
	 * @return int (답글번호)
	 * @throws Exception
	 */

	int replyNumFindBuReviewNo(int reviewNo) throws Exception;

	List<Integer> purchaseNumFindByDealerSessionNum2(int  sessionNum) throws Exception ;

	List<Integer> ReviewFindByPurchaseNum2(List<Integer> purchaseNo) throws Exception;
	
	

}
