package controller;

import java.sql.SQLException;
import java.util.List;

import DTO.Car;
import DTO.Dealer;
import DTO.MemberSession;
import DTO.Purchase;
import Service.DealerReviewService;
import Service.PurchaseService;
import Service.PurchaseServiceImpl;
import com.sun.tools.javac.Main;
import view.MemberMenuView;
import view.MenuView;
import view.PurchaseView;

public class PurchaseController {
   /**
    * 구매 프로세스를 시작
    */
   public static void startPurchase() {
      try {
         PurchaseService purchaseService = PurchaseServiceImpl.getInstance(); // 싱글톤 인스턴스 사용?

         // 1. 딜러 선택하기
         List<Dealer> dealers = purchaseService.DealerChoice();//모든 딜러 정보들을 가지고 옴
         Dealer selectedDealer = PurchaseView.displayDealersAndSelect(dealers);

         // 2. 차량 타입(카테고리) 및 모델 선택
         //모든 타입의 차량 조회
         List<Car> getAllCars=purchaseService.getCarList();
         PurchaseView.displayAllCars(getAllCars);

         //차량 카테고리를 선택
         int carTypeChoice = PurchaseView.chooseCarType();
         List<String> carCategoryList = (carTypeChoice == 1) ? purchaseService.getCarListByType("SUV") : purchaseService.getCarListByType("Sedan");
         // 1을 고르면 suv 목록을 얻고 나머지 경우에서는 sedan의 정보를 얻음
         String selectedCarName = PurchaseView.displayCarsAndSelect(carCategoryList);

         // 3. 차량 번호 선택, 차량의 이름을 이용하여 carNo를 얻음
         int carNo = purchaseService.getCarNoByCarName(selectedCarName);

         // 4. 색상 선택, 변수에 저장
         String selectedColor = PurchaseView.chooseColor();

         // 5. 옵션 선택
         int sunRoof = PurchaseView.chooseSunRoofOption(); // 옵션 선택하면 1을 반환
         int coolSeat = PurchaseView.chooseSeatOption();
         int aroundView = PurchaseView.chooseAroundViewOption();

         // 6. 총 비용 계산
         int totalPrice = purchaseService.calculateTotalPrice(carNo,sunRoof, coolSeat, aroundView);
         PurchaseView.displayTotalPrice(totalPrice);

         // 7. 구매 과정 처리
         boolean confirmPurchase = PurchaseView.confirmPurchase();
         if (confirmPurchase) {
            boolean balanceSufficient = purchaseService.checkAndHandleBalance(totalPrice);
            if (!balanceSufficient) { // 잔고가 부족한 경우
               PurchaseView.displayInSufficientBalance();

               // 충전 여부 확인
               boolean rechargeDecision = PurchaseView.confirmRecharge();
               if (rechargeDecision)
               { // 충전하는 경우
                  int rechargeAmount = PurchaseView.enterRechargeAmount(totalPrice - purchaseService.getBalance());
                  purchaseService.rechargeBalance(rechargeAmount);

                  balanceSufficient = purchaseService.checkAndHandleBalance(totalPrice); // 전체 금액으로 다시 확인
                  if (balanceSufficient)
                  {
                     finalizePurchase(carNo, selectedDealer.getDealerNo(), selectedColor, sunRoof, coolSeat, aroundView, totalPrice);
                  }
                  
                  else
                  { // 충전했는데도 잔액이 부족하다면 주문 취소
                     PurchaseView.displayInSufficientBalance();
                     PurchaseView.displayPurchaseCancelled();
                     MemberMenuView.menu(); // 상위 메뉴로 올라감
                  }
               }
               else
               { // 충전하지 않는 경우 주문 취소
                  PurchaseView.displayPurchaseCancelled();
                  MemberMenuView.menu(); // 상위 메뉴 or 메인메뉴로
               }
            }
            else
            { // 잔고가 충분한 경우
               finalizePurchase(carNo, selectedDealer.getDealerNo(), selectedColor, sunRoof, coolSeat, aroundView, totalPrice);
               //PurchaseView.displayPurchaseSuccess();
               MemberMenuView.menu();//구매가 끝나도 메인 화면으로
            }
         }
         else
         { // 구매를 취소하는 경우
            PurchaseView.displayPurchaseCancelled();
            MemberMenuView.menu(); // 상위 메뉴 or 메인메뉴로
         }

      } catch (Exception e) {
         // 기타 예외 처리
         System.out.println(e.getMessage());
      }
   }

   /**
    * 구매를 완료하는 메서드, 구매내역에 인서트 되는 경우
    */
   static void finalizePurchase(int carNo, int dealerNum, String color, int sunRoof, int coolSeat, int aroundView, int totalPrice) {
      PurchaseService purchaseService = PurchaseServiceImpl.getInstance();
      MemberSession session=MemberSession.getInstance();
        try
        {
           session.setBalance(session.getBalance() - totalPrice);
           purchaseService.purchaseInsert(carNo, dealerNum, color, sunRoof, coolSeat, aroundView, totalPrice);
           PurchaseView.displayPurchaseSuccess();
        }
        catch(Exception e)
        {
           System.out.println(e.getMessage());
        }
   }
   
   /**
    * 구매내역 조회
    */
   public static void allPurchase() {
      PurchaseService purchaseService = PurchaseServiceImpl.getInstance();
      try
      {
         Purchase purchase = purchaseService.allPurchase();
         PurchaseView.purchaseAll(purchase);
      }
      catch(Exception e)
      {
         System.out.println(e.getMessage());
      }
   }
}