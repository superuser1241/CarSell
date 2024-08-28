package Service;

import java.sql.SQLException;
import java.util.List;

import DTO.Car;
import DTO.Dealer;
import DTO.Purchase;

public interface PurchaseService {

   /**
    * 딜러를 선택할 때 보여줄 딜러의 번호와 자기소개를 반환
    *
    * @return List<Dealer> 딜러 목록
    * @throws Exception 예외 발생 시 처리
    */
   List<Dealer> DealerChoice() throws Exception;

   /**
    * 차량 구매 완료 시 데이터를 DB에 삽입
    *
    * @param carNo 차량 번호
    * @param dealerNum 딜러 번호
    * @param color 차량 색상
    * @param sunRoof 썬루프 옵션
    * @param coolSeat 냉각 시트 옵션
    * @param aroundView 어라운드 뷰 옵션
    * @param totalPrice 총 가격
    * @throws Exception 예외 발생 시 처리
    */
   void purchaseInsert(int carNo, int dealerNum, String color, int sunRoof, int coolSeat, int aroundView, int totalPrice) throws Exception;

   /**
    * 전체 차량 목록을 반환
    *
    * @return List<Car> 차량 리스트
    * @throws Exception 예외 발생 시 처리
    */
   public List<Car> getCarList() throws Exception;

   /**
    * 특정 카테고리(SUV 또는 Sedan)에 따라 차량 목록을 반환
    *
    * @param type 차량 타입 ("SUV", "Sedan")
    * @return List<String> 해당 카테고리의 차량 이름 리스트
    * @throws Exception 예외 발생 시 처리
    */
   List<String> getCarListByType(String type) throws Exception;

   /**
    * 차량 이름으로 차량 번호를 반환
    *
    * @param carName 선택한 차량의 이름
    * @return int 차량 번호
    * @throws Exception 예외 발생 시 처리
    */
   int getCarNoByCarName(String carName) throws Exception;

   /**
    * 총 가격을 계산
    *
    * @param carNo 차량 번호
    * @param sunRoof 썬루프 옵션
    * @param coolSeat 냉각 시트 옵션
    * @param aroundView 어라운드 뷰 옵션
    * @return int 총 가격
    * @throws SQLException SQL 예외 발생 시 처리
    */
   int calculateTotalPrice(int carNo, int sunRoof, int coolSeat, int aroundView) throws SQLException;

   /**
    * 잔액을 확인하고 부족할 경우 처리
    *
    * @param totalPrice 총 가격
    * @return boolean 잔액이 충분한지 여부
    * @throws Exception 예외 발생 시 처리
    */
   boolean checkAndHandleBalance(int totalPrice) throws Exception;

   /**
    * 현재 잔액을 반환
    *
    * @return int 잔액
    * @throws Exception 예외 발생 시 처리
    */
   int getBalance() throws Exception;

   /**
    * 사용자의 잔액을 충전
    *
    * @param rechargeAmount 충전할 금액
    * @throws Exception 예외 발생 시 처리
    */
   void rechargeBalance(int rechargeAmount) throws Exception;

   /**
    * 사용자의 모든 구매 내역을 반환
    *
    * @return Purchase 사용자의 구매 내역
    * @throws Exception 예외 발생 시 처리
    */
   Purchase allPurchase() throws Exception;

}
