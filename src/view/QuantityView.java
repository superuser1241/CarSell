package view;

import java.util.Scanner;

import DTO.DealerSession;
import controller.DealerController;

public class QuantityView {
	static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		while(true) {
			
			//딜러의 아이디와 딜러의 평점을 세션으로 출력하는 곳
			
			DealerSession dealerSession = DealerSession.getInstance();
			
			System.out.println("딜러ID: "+dealerSession.getDealerId());
			QuantityView.printMenu();
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				QuantityView.updateQuantity();//수정
				break;
			case 2 :
				DealerController.carSelectAll();//컨트롤러의 재고조회 함수 호출
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
		System.out.println("=== DriveX Dealer 재고관리 ===");
		System.out.println("1. 수정   |   2. 조회   |  3. 나가기");
	}
	
	public static void updateQuantity() {
		System.out.println("어떤 차량의 재고량을 수정하시겠습니까?");
		System.out.println("G70, G80, G90, GV60, GV70, GV80중에서 골라주세요");
		String carName = sc.nextLine();
		System.out.println("몇 대를 추가하시겠습니까?");
		int carQuantity = Integer.parseInt(sc.nextLine());
		//컨트롤러.수정함수(인수: carName, carQuantity)
		DealerController.carQuantityPlusUpdate(carName, carQuantity);
	}
}