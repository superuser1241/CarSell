package controller;

import java.util.ArrayList;
import java.util.List;

import DTO.Review;
import Service.DealerReviewService;
import Service.DealerReviewServiceImpl;
import Service.MemberReviewService;
import Service.MemberReviewServiceImpl;

public class ReviewController {
	static DealerReviewService dealerReviewService = new DealerReviewServiceImpl();
	static MemberReviewService memberReviewService = new MemberReviewServiceImpl();
	
	public static void findBySessionNum(int sessionNum) throws Exception {
		int result=0;
		
		//현재 로그인된 세션넘버가 구매내역테이블의 세션과 일치하는지 확인해야 한다->
		try {
			result = memberReviewService.purchaseNumFindByMemberSessionNum(sessionNum);
		} catch (Exception e) {
			throw new Exception("구매 내역을 확인하는 중 오류가 발생했습니다.");
		}
		if(result==0) {
			throw new Exception("일치하는 구매 내역이 없습니다.");
		}
		
	}
	
	public static void reviewSelectAll() {
		try {
			List<Review> listReview = new ArrayList<>();
			
			listReview = memberReviewService.reviewSelectAll();
			for(Review re : listReview) {
				System.out.println(re);
			}
		}catch(Exception e) {
			System.out.println("등록된 리뷰가 없습니다");
		}
		
	}
	
	
	
	public static void reviewInsert(String title,String content,int carStar, int dealerStar) {
		try {
            memberReviewService.reviewInsert(title, content, carStar, dealerStar);
            System.out.println("리뷰가 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            System.out.println("리뷰 등록에 실패했습니다 "+e.getMessage());
        }
		
	}
	
	public static void reviewDelete(int sessionNum) {
		try {
			memberReviewService.reviewDelete(sessionNum);
		}catch (Exception e) {
			System.out.println("삭제하려는 번호의 리뷰글이 없습니다");
		}
	}
	
	public static void selectReviewByNum()
	{	
		try {
			dealerReviewService.selectReviewByNum();
		} catch (Exception e) {
			System.out.println("해당하는 리뷰넘버가 없습니다");
		}
				
	}
	
	public static void replyInsert(int reviewNo,String content) {
		try {
			dealerReviewService.replyInsert(reviewNo, content);
		} catch (Exception e) {
			System.out.println("답글 등록에 실패했습니다 "+e.getMessage());
		}
	}

	
	
}
