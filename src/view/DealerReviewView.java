package view;

import java.util.Scanner;

import DTO.DealerSession;
import controller.ReviewController;

public class DealerReviewView {
	static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		while(true) {
			
			//딜러의 아이디와 딜러의 평점을 세션으로 출력하는 곳
			DealerSession dealerSession = DealerSession.getInstance();
			
			System.out.println("딜러ID: "+dealerSession.getDealerId());

			
			DealerReviewView.printMenu();
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				ReviewController.selectReviewByNum(); //컨트롤러의 조회함수 호출 //조회
				break;
			case 2 :
				DealerReviewView.registerReply();//답글달기
				break;
			case 3 :
				//나가기
				return;
			default:
				System.out.println("1, 2, 3 중에서만 입력 바랍니다.");
			}
		}
	}

	public static void printMenu() {
		System.out.println("=== DriveX Dealer 후기게시판 ===");
		System.out.println("1. 조회   |   2. 답글달기   |  3. 나가기");
	}
	
	public static void registerReply() {
		System.out.println("몇번글에 답을 다시겠습니까?");
		int replyNo = Integer.parseInt(sc.nextLine());
		System.out.println("내용은?");
		String replyContent = sc.nextLine();
		
		ReviewController.replyInsert(replyNo, replyContent);
	}
}
