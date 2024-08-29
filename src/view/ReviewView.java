package view;

import java.util.Scanner;

import DTO.MemberSession;

import controller.ReviewController;

public class ReviewView {
	static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		MemberSession session = MemberSession.getInstance();

		while(true) {

			
			ReviewView.printMenu();
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				try{//구매를 했는지 검증먼저 한다
					ReviewController.findBySessionNum(session.getMemberNo());
					
					//구매를 했다면
					ReviewView.register(session.getMemberNo()); // 등록한다.
					
				}catch (Exception e) {
					//일치하는 구매내역이 없을 때 예외 발생. failView출력
				}
				break;
			case 2 :
				ReviewController.reviewSelectAll(); // 게시글 조회
				break;
			case 3 :
				ReviewView.deleteReview(); //리뷰게시글 삭제
				break;
			case 4 : 
				System.out.println("후기게시판을 종료합니다.");
				return;
			default:
				System.out.println("1, 2, 3, 4 중에서만 입력 바랍니다.");
			}
		}
	}

	public static void printMenu() {
		System.out.println("=== DriveX Member Review Board ===");
		System.out.println("1. 등록   |   2. 조회   |  3. 삭제  |  4. 나가기");
	}
	
	
	
	public static void register(int sessionNum) {
		System.out.println("리뷰의 제목을 입력하세요");
		String title = sc.nextLine();
		System.out.println("리뷰의 내용을 입력하세요");
		String content = sc.nextLine();		
		System.out.println("차량의 평점(1부터 5 사이의 정수)을 입력하세요");
		int carStar = Integer.parseInt(sc.nextLine());
		System.out.println("딜러의 평점(1부터 5 사이의 정수)을 입력하세요");
		int dealerStar = Integer.parseInt(sc.nextLine());
		
		//컨트롤러의 register함수 호출
		ReviewController.reviewInsert(title,content,carStar,dealerStar);
	}
	
	public static void deleteReview() {
		System.out.println("삭제할 글번호를 알려주세요");
		int reNo = Integer.parseInt(sc.nextLine());
		//컨트롤러의 삭제함수 호출(인수: reNo)
		ReviewController.reviewDelete(reNo);
	}
	
	
	
}
