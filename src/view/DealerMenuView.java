package view;

import java.util.Scanner;

import DTO.DealerSession;
import controller.DealerController;

public class DealerMenuView {
	static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		while(true) {
			
			//딜러의 아이디와 딜러의 평점을 세션으로 출력하는 곳
			
			DealerSession dealerSession = DealerSession.getInstance();
			
			System.out.println("딜러ID: "+dealerSession.getDealerId());

			
			DealerMenuView.printMenu();
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				QuantityView.menu();// 재고관리
				break;
			case 2 :
				 DealerReviewView.menu();// 후기게시판
				break;
			case 3 :
				DealerController.selectDealerStarByDealerId();//컨트롤러.실적조회함수 호출
				break;
			case 4 :
				DealerMenuView.selfUpdate();//딜러정보수정
				break;
			case 5 : 
				System.out.println("안녕히 가십시오 - DriveX");
				dealerSession = null;
				return;
			default:
				System.out.println("1, 2, 3, 4, 5 중에서만 입력 바랍니다.");
			}
		}
	}

	public static void printMenu() {
		System.out.println("=== DriveX Dealer Menu ===");
		System.out.print("1. 재고관리   |   2. 후기게시판   |  3. 실적조회  |  ");
		System.out.println("4. 딜러정보수정   |   5. 로그아웃");
	}
	
	public static void selfUpdate() {
		//원래소개글을 컨트롤러가 함수호출해서 출력할 겁니다...
		System.out.println("어떤 내용으로 자기소개를 고치겠습니까?");
		String self = sc.nextLine();
		//컨트롤러가 답글수정하는 함수 호출(인수: self)
		DealerController.dealerUpdate(self);
	}
}