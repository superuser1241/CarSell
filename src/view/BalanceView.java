package view;

import java.util.Scanner;
import DTO.MemberSession;
import controller.BalanceController;
import oracle.net.ns.SessionAtts;

public class BalanceView {
   static Scanner sc = new Scanner(System.in);
   MemberSession session = MemberSession.getInstance();

   public static void menu() {
      while (true) {
   
         System.out.println();
         
         BalanceView.printMenu();

         int menu = Integer.parseInt(sc.nextLine());
         switch (menu) {
         case 1:
            BalanceView.balancePlusUpdate();// 충전하기
            break;
         case 2:
            BalanceView.balanceMinusUpdate();// 인출하기
            break;
         case 3:
            return;
         default:
            System.out.println("1, 2, 3 중에서만 입력 바랍니다.");
         }
      }
   }

   public static void balancePlusUpdate() {

      int balance = 0;
      int amount = 0;
      String memberId = null;

      while (true) {
         System.out.print("충전할 금액을 입력하세요 : ");
         amount = Integer.parseInt(sc.nextLine());

         System.out.print("충전할 아이디를 입력하세요 : ");
         memberId = sc.nextLine();
         if (amount > 0)
            break;
         System.out.println("마이너스 금액입니다. 다시 입력해 주세요");
      }
      balance = BalanceController.balanceSelect(); // DB에서 현재 잔액 조회

      System.out.print("현재금액 : " + balance); // 충전 전 금액

      BalanceController.balancePlusUpdate(memberId, amount); // 충전->DB가 바뀜

      balance = BalanceController.balanceSelect(); // DB에서 현재 잔액 조회->충전 된 금액
      System.out.print("충전 후 금액 : " + balance);
   }

   public static void balanceMinusUpdate() {
      
      int balance = 0;
      int amount1 = 0;
      String memberId = null;

      while (true) {
         System.out.print("인출할 금액을 입력하세요 : ");
         amount1 = Integer.parseInt(sc.nextLine());
         
         System.out.print("인출할 아이디를 입력해 주세요 : ");
         memberId = sc.nextLine();

         if (amount1 > 0)
            break;
         System.out.println("마이너스 금액입니다. 다시 입력해 주세요");
      }
   
      
      balance = BalanceController.balanceSelect(); // DB에서 현재 잔액 조회
      System.out.println("현재금액 : " + balance); // 충전 전 금액
      BalanceController.balanceMinusUpdate(memberId, amount1);

      balance = BalanceController.balanceSelect(); // DB에서 현재 잔액 조회->충전 된 금액
      System.out.println("인출 후 금액 : " + balance);

   }

   public static void printMenu() {
      System.out.println("=== DriveX Member Menu ===");
      System.out.print("1. 충전하기   |   2. 인출하기   |  3. 나가기");
   }
}
