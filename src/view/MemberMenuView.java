package view;

import java.util.Scanner;

import DTO.MemberSession;
import controller.PurchaseController;

public class MemberMenuView {
   
   static Scanner sc = new Scanner(System.in);
   static MemberSession Session =  MemberSession.getInstance();
   
   public static void menu() {
      while(true) {

         System.out.println("─────────────────────────────────────────────");
         System.out.println("현재 접속중인 아이디 : "+Session.getMemberId());
         System.out.println("─────────────────────────────────────────────");
         
         MemberMenuView.printMenu();
         
         int menu = Integer.parseInt(sc.nextLine());
         switch(menu) {
         case 1 :
            //컨트롤러.purchase(); // 구매
            PurchaseController.startPurchase();
            break;
         case 2 :
            FAQView.printFAQ(); // FAQ 출력
            break;
         case 3 :
            ReviewView.menu(); //후기게시판
            break;
         case 4 :
            PurchaseController.allPurchase(); //컨트롤러.구매내역조회함수 호출
            break;
         case 5 :
            BalanceView.menu();
            break;
         case 6 : 
            System.out.println("안녕히 가십시오 - DriveX");
            return;
         default:
            System.out.println("1, 2, 3, 4, 5, 6 중에서만 입력 바랍니다.");
         }
      }
   }

   public static void printMenu() {
      System.out.println("=== DriveX Member Menu ===");
      System.out.print("1. 구매   |   2. FAQ   |  3. 후기게시판  |  ");
      System.out.println("4. 구매내역조회   |   5. 잔고관리   |  6. 로그아웃");
   }
}
