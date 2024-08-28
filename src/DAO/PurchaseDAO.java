package DAO;

import java.sql.SQLException;
import java.util.List;

import DTO.Car;
import DTO.Dealer;
import DTO.Purchase;

public interface PurchaseDAO {

   /**
    * 딜러 선택 시 보여줄 딜러 목록을 반환
    *
    * @return List<Dealer> 딜러 목록
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */
   List<Dealer> getAllDealers() throws SQLException;

   /**
    * 모든 차량의 목록을 반환
    *
    * @return List<Car> 차량 목록
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */
   public List<Car> getCarList() throws SQLException;

   /**
    * 차량 타입에 따라 (SUV 또는 Sedan) 차량 목록을 반환
    *
    * @param type 차량 타입 (SUV, Sedan)
    * @return List<String> 차량 이름 목록
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */
   List<String> getCarListByType(String type) throws SQLException;

   /**
    * 차량 이름으로 차량 번호를 반환
    *
    * @param carName 차량 이름
    * @return int 차량 번호
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */
   int getCarNoByCarName(String carName) throws SQLException;

   /**
    * 썬루프 옵션에 따른 가격을 반환
    *
    * @return int 썬루프 옵션 가격
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */
   int getSunRoofPrice() throws SQLException;

   /**
    * 냉각 시트 옵션에 따른 가격을 반환
    *
    * @return int 냉각 시트 옵션 가격
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */
   int getCoolSeatPrice() throws SQLException;

   /**
    * 어라운드 뷰 옵션에 따른 가격을 반환
    *
    * @return int 어라운드 뷰 옵션 가격
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */
   int getAroundViewPrice() throws SQLException;

   /**
    * 구매 정보를 데이터베이스에 삽입
    *
    * @param carNo 차량 번호
    * @param dealerNum 딜러 번호
    * @param color 차량 색상
    * @param sunRoof 썬루프 옵션
    * @param coolSeat 냉각시트 옵션
    * @param aroundView 어라운드 뷰 옵션
    * @param totalPrice 총 가격
    * @return int 삽입된 행의 수 (성공적으로 삽입된 경우 1 이상)
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */
   int purchaseInsert(int carNo, int dealerNum, String color, int sunRoof, int coolSeat, int aroundView, int totalPrice) throws SQLException;

   /**
    * 차량 번호로 차량의 기본 가격을 반환
    *
    * @param carNo 차량 번호
    * @return int 차량 기본 가격
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */
   int getBasePriceByCarNo(int carNo) throws SQLException;

   /**
    * 회원의 잔고를 업데이트
    *
    * @param memberNo 회원 번호
    * @param amount 변경할 금액
    * @return int 업데이트된 행의 수
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */
   int updateMemberBalance(int memberNo, int amount) throws SQLException;

   /**
    * 차량의 수량을 감소
    *
    * @param carNo 차량 번호
    * @param amount 감소할 수량
    * @return int 업데이트된 행의 수
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */
   int updateCarQuantity(int carNo, int amount) throws SQLException;

   /**
    * 회원의 잔액을 조회
    *
    * @param memberNo 회원 번호
    * @return int 회원 잔액
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */
   int getBalanceBySessionId(int memberNo) throws SQLException;

   /**
    * 사용자의 모든 구매 내역을 조회
    *
    * @return Purchase 사용자의 구매 내역
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */
   Purchase allPurchase() throws SQLException;
}
