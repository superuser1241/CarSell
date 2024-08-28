package Service;

import java.sql.SQLException;
import java.util.List;

import DTO.Car;
import DTO.Dealer;
import DTO.Purchase;

public interface PurchaseService {

   /**
    * 순서) 1번. 딜러 선택하기일 때
    * Dealer를 고를 때 보여주는 Dealer의 번호와 자기소개
    * @return List<Dealer>
    */
   List<Dealer> DealerChoice() throws Exception;

   /**
    * 순서) 8번.
    * 구매 완료 시 호출되어야 할 DAO:
    *  purchaseDAO에서 insert시킴
    */
   void purchaseInsert(int carNo,int dealerNum, String color, int sunRoof, int coolSeat, int aroundView, int totalPrice) throws Exception;

   // ---------------- 추가된 부분 ----------------

   /*
   전체 자동차들을 가지고 오는 함수
    */
   public List<Car> getCarList()throws Exception;

   /**
    * 차량 리스트를 카테고리(SUV 또는 Sedan)에 따라 가져오는 함수
    * @param type 차량 타입 ("SUV", "Sedan")
    * @return 차량 이름 리스트
    */
   List<String> getCarListByType(String type) throws Exception;

   /**
    * 차량 이름으로 차량 번호를 가져오는 함수
    * @param carName 선택한 차량의 이름
    * @return 차량 번호
    */
   int getCarNoByCarName(String carName) throws Exception;

   /**
    * 총 가격 계산을 위한 함수
    * @param carNo 차량 번호
    * @param sunRoof 썬루프 옵션
    * @param coolSeat 냉각 시트 옵션
    * @param aroundView 어라운드 뷰 옵션
    * @return 총 가격
    */
   int calculateTotalPrice(int carNo, int sunRoof, int coolSeat, int aroundView) throws SQLException;

   /**
    * 잔액 확인 및 부족한 경우 처리하는 함수
    *
    * @param totalPrice 총 가격
    * @return 잔액이 충분한지 여부
    */
   boolean checkAndHandleBalance(int totalPrice) throws Exception;

   /**
    * 현재 사용자의 잔액을 반환하는 함수
    * @return 현재 잔액
    */
   int getBalance() throws Exception;

   /**
    * 사용자의 잔액을 충전하는 함수
    * @param rechargeAmount 충전할 금액
    */
   void rechargeBalance(int rechargeAmount) throws Exception;
   
   /**
    * 사용자의 구매내역을 반환하는 메소드
    */
   Purchase allPurchase() throws Exception;
   
}
