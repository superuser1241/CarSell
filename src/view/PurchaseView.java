package view;

import DTO.Car;
import DTO.Dealer;
import DTO.Purchase;
import controller.PurchaseController;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class PurchaseView {
   static Scanner scan = new Scanner(System.in);

   // 1. 딜러 선택하기
   public static Dealer displayDealersAndSelect(List<Dealer> dealers) {
      System.out.println();
      System.out.println("딜러를 선택하시오");
      Dealer dealer = null;
      for (int i = 0; i < dealers.size(); i++) {
         dealer = dealers.get(i);
         System.out.println((i + 1) + "번 딜러 " + dealer.getDealerName() + " : " + dealer.getSelf() + " 저의 평점은 "
               + dealer.getRate() + "점 입니다");
         // 딜러번호와 딜러 자기소개 출력
      }
      System.out.print("입력 : ");
      int choice = Integer.parseInt(scan.nextLine());
      System.out.println("당신의 딜러는 " + dealer.getDealerName() + "입니다");
      return dealers.get(choice - 1);// 자바에서는 0부터 인덱스가 시작하므로
   }

   // 2. 차량 선택하기(카테고리 및 모델)

   public static int chooseCarType() {
      System.out.println();

      System.out.println("차량 타입(카테고리)를 선택하시오");
      System.out.println("1.SUV 2. Sedan");
      System.out.print("입력 : ");
      int choice = Integer.parseInt(scan.nextLine());
      String message = (choice == 1) ? "당신은 SUV를 골랐습니다" : "당신은 Sedan을 골랐습니다";
      System.out.println(message);
      return choice;
   }

   // 차량 카테고리에 맞는 차량 목록을 보여주고 사용자가 차량을 선택하게 함
   public static String displayCarsAndSelect(List<String> carList) {
      System.out.println();

      System.out.println("차량 모델을 선택하세요");

      for (int i = 0; i < carList.size(); i++) {
         System.out.println((i + 1) + ". " + carList.get(i));
      }
      System.out.print("입력 : ");
      int choice = Integer.parseInt(scan.nextLine());
      String selectedCar = carList.get(choice - 1);
      System.out.println("당신이 선택한 자동차는 " + selectedCar + "입니다");
      return selectedCar;
   }

   // 3. 차량 색상 선택
   public static String chooseColor() {
      System.out.println();

      System.out.println("색상을 선택하세요");
      System.out.println("1,2,3,4 이외의 색상을 선택하시면 기본 색상인 베르비에 화이트가 선택됩니다");
      System.out.println("1. 베르비에 화이트");
      System.out.println("2. 레이싱 블루");
      System.out.println("3. 미드나잇 블랙");
      System.out.println("4. 그라니트 그레이");

      System.out.print("입력 : ");
      int choice = Integer.parseInt(scan.nextLine());

      // 1,2,3이 아닌 색상을 선택하면 베르비에 화이트로 색상을 결정

      switch (choice) {

      case 1:
         System.out.println("당신이 선택한 색상은 베르비에 화이트입니다");
         return "베르비에 화이트";
      case 2:
         System.out.println("당신이 선택한 색상은 레이싱 블루입니다");
         return "레이싱 블루";
      case 3:
         System.out.println("당신이 선택한 색상은 미드나잇 블랙입니다");
         return "미드나잇 블랙";
      case 4:
         System.out.println("당신이 선택한 색상은 그라니트 그레이입니다");
         return "그라니트 그레이";
      default:
         System.out.println("기본 색상인 베르비에 화이트를 고르셨습니다");
         return "베르비에 화이트";
      }
   }

   public static int chooseSunRoofOption() {
      System.out.println();

      System.out.println("썬루프 옵션을 선택하시겠습니까?");
      System.out.println("1. 예");
      System.out.println("2. 아니오");

      System.out.print("입력 : ");
      int choice = Integer.parseInt(scan.nextLine());

      if (choice == 1)
         System.out.println("썬루프 옵션을 선택하셨습니다");
      else
         System.out.println("썬루프 옵션을 선택하시지 않았습니다");

      return (choice == 1 ? 1 : 0); // 선택하면 1반환 선택하지 않으면 0반환(int형 1,0)
   }

   public static int chooseSeatOption() {
      System.out.println();

      System.out.println("통풍시트 옵션을 선택하시겠습니까?");
      System.out.println("1. 예");
      System.out.println("2. 아니오");

      System.out.print("입력 : ");
      int choice = Integer.parseInt(scan.nextLine());

      if (choice == 1)
         System.out.println("통풍시트 옵션을 선택하셨습니다");
      else
         System.out.println("통풍시트 옵션을 선택하시지 않았습니다");

      return (choice == 1 ? 1 : 0); // 선택하면 1반환 선택하지 않으면 0반환
   }

   public static int chooseAroundViewOption() {
      System.out.println("어라운드뷰 옵션을 선택하시겠습니까?");
      System.out.println("1. 예");
      System.out.println("2. 아니오");

      System.out.print("입력 : ");
      int choice = Integer.parseInt(scan.nextLine());

      if (choice == 1)
         System.out.println("어라운드 뷰 옵션을 선택하셨습니다");
      else
         System.out.println("어라운드 뷰 옵션을 선택하시지 않았습니다");

      return (choice == 1 ? 1 : 0); // 선택하면 1반환 선택하지 않으면 0반환
   }

   // 견적서를 출력
   public static void displayTotalPrice(int totalPrice) {
      System.out.println();
      System.out.println("총 가격 : " + totalPrice + "원");
   }

   // 구매확정 문의
   public static boolean confirmPurchase() {
      System.out.println();
      System.out.println("구매를 확정하시겠습니까?");
      System.out.println("1. 예");
      System.out.println("2. 아니오");

      System.out.print("입력 : ");
      int choice = Integer.parseInt(scan.nextLine());
      System.out.println();
//      if (choice == 1)
//         System.out.println("구매확정을 하셨습니다");
//      else
//         System.out.println("구매확정을 하시지 않았습니다");

      return (choice == 1);// 1을 고르면 참을 반환
   }

   // 충전할 금액 입력
   public static boolean confirmRecharge() {
      System.out.println();
      System.out.println("잔액을 충전하시겠습니까?");
      System.out.println("1.예");
      System.out.println("2.아니오");

      System.out.print("입력 : ");
      int choice = Integer.parseInt(scan.nextLine());
      System.out.println();

      if (choice == 1)
         System.out.println("잔액충전을 선택하셨습니다");
      else
         System.out.println("잔액충전을 선택하시지 않았습니다");

      return (choice == 1);
   }

   // 구매 성공 메시지
   public static void displayPurchaseSuccess() {
      System.out.println();
      System.out.println("구매가 성공적으로 완료되었습니다");
   }

   // 구매 취소 메시지
   public static void displayPurchaseCancelled() {
      System.out.println();
      System.out.println("구매가 취소되었습니다");
   }

   // 잔액이 부족한 경우에 나오는 메시지
   public static void displayInSufficientBalance() {
      System.out.println();
      System.out.println("잔액이 부족합니다");
   }

   // 충전하는 경우
   public static int enterRechargeAmount(int totalPrice) {
      System.out.println();
      System.out.println("부족한 금액 : " + totalPrice);
      System.out.println("충전할 금액을 입력하시오");
      System.out.print("입력 : ");
      int inputAmount = Integer.parseInt(scan.nextLine());
      System.out.println();
      System.out.println(inputAmount + "를 충전하겠습니다");
      return inputAmount;
   }

   // 모든 차량을 출력
   public static void displayAllCars(List<Car> getAllcars) {
      System.out.println();
      System.out.println("모든 차량 목록 출력");
      for (Car car : getAllcars) {
         System.out.printf("차량 이름 : %s || 마력 : %s || 연비 : %s || 재고량 : %d || 가격 : %d || 카테고리 : %s\n",
               car.getCarName(), car.getHorsePower(), car.getFuel(), car.getQuantity(), car.getPrice(),
               car.getCategory());
      }
   }

   /**
    * 구매 내역 조회
    */
   public static void purchaseAll(Purchase purchase) {
      int sunRoof = purchase.getSunRoof();
      String sunRoofString;
      if (sunRoof == 1)
         sunRoofString = "Y";
      else
         sunRoofString = "N";

      int seat = purchase.getSeat();
      String seatString = null;
      if (seat == 1)
         seatString = "Y";
      else
         seatString = "N";

      int aroundView = purchase.getAroundView();
      String aroundViewString = null;
      if (aroundView == 1)
         aroundViewString = "Y";
      else
         aroundViewString = "N";

      System.out.printf("구매 번호 : %d || 회원 번호 : %d || 구매일 : %s   || 딜러번호 : %d  || 차량모델번호 : %s\n",
            purchase.getPurchaseNo(), purchase.getMemberNo(), purchase.getPurchaseDate(), purchase.getDealerNo(),
            purchase.getCarNo());

      System.out.printf("선루프 : %s || 시트 : %s || 어라운드뷰 : %s || 색상 : %s || 구매금액 : %d  ", sunRoofString, seatString,
            aroundViewString, purchase.getColor(), purchase.getPrice());
      System.out.println();

   }

}
