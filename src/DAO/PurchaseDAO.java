package DAO;

import java.sql.SQLException;
import java.util.List;

import DTO.Car;
import DTO.Dealer;
import DTO.Purchase;

public interface PurchaseDAO {

   /**
    * 순서) 1번. 딜러 선택하기일 때
    * Dealer를 고를 때 보여주는 Dealer 자기소개
    * @return List<Dealer> 딜러 목록
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */

   //DealerDAO에더 같은 함수 있음
   List<Dealer> getAllDealers() throws SQLException;

   //Car테이블에 있는 모든 차들을 가지고 옴
   public List<Car> getCarList() throws SQLException;

   /**
    * 순서) 2번. SUV vs Sedan 고르기할 때
    * 차량의 타입에 따라 (SUV 또는 Sedan) 차량 목록을 가져옴
    * @param type 차량 타입 (SUV, Sedan)
    * @return 차량 이름 목록
    * @throws SQLException 데이터베이스 접근 중 예외
    */
   List<String> getCarListByType(String type) throws SQLException;

   /**
    * 순서) 3번.
    * if SUV를 선택하면 carSelectSuv를 보여주면서 선택
    * 그리고 입력값으로 carName을 받아서
    * carSelectByCarName을 호출, 인수로는 작성자가 입력한
    * carName을 전달
    *
    * 차량 이름으로 차량 번호를 가져옴
    * @param carName 차량 이름
    * @return 차량 번호
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */
   int getCarNoByCarName(String carName) throws SQLException;

   /**
    * 순서) 4번. 색상 선택
    * 색상들의 예시를 띄워주고 고르게 함
    *
    *
    */

   /**
    * 썬루프 옵션에 따른 가격을 가져옴
    * @return 썬루프 옵션 가격
    * @throws SQLException 데이터베이스 접근 중 예외 발생
    */
   int getSunRoofPrice() throws SQLException;

   /**
    * 냉각 시트 옵션에 따른 가격을 가져옴
    * @return 냉각 시트 옵션 가격
    * @throws SQLException 데이터베이스 접근 중 예외 발생
    */
   int getCoolSeatPrice() throws SQLException;

   /**
    * 어라운드뷰 옵션에 따른 가격을 가져옴
    * @return 어라운드뷰 옵션 가격
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */
   int getAroundViewPrice() throws SQLException;

   /**
    * 구매 정보를 데이터베이스에 삽입
    * 사용자 세션 ID
    * @param carNo 차량 번호
    * @param dealerNum 딜러 번호
    * @param color 색상
    * @param sunRoof 썬루프 옵션
    * @param coolSeat 냉각 시트 옵션
    * @param aroundView 어라운드 뷰 옵션
    * @param totalPrice 총 가격
    * @return 삽입된 행의 수 (성공적으로 삽입된 경우 1 이상)
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */
   int purchaseInsert(int carNo, int dealerNum, String color, int sunRoof, int coolSeat, int aroundView, int totalPrice) throws SQLException;

   /**
    * 순서) 7번.
    * 잔액이 얼마 남았는지 보여주고 부족할 경우 예외 처리로 잔고 관리(충전 메뉴)로 들어감
    * 전 단계인 6단계의 상태와 똑같이 견적서를 갖고 있는 상황으로 만들어줌
    * ↓메소드는 잔액 부족으로 충전하러 갔을 때 메소드에서 호출하
    *
    * 차량 번호로 차량의 기본 가격을 가져옴
    * @param carNo 차량 번호
    * @return 차량 기본 가격
    * @throws SQLException 데이터베이스 접근 중 예외 발생 시
    */
   int getBasePriceByCarNo(int carNo) throws SQLException;

   /**
    *
    * 구매 완료 시 호출되어야 할 DAO:
    *  MemberDAO.balanceMinusUpdate(PurchaseDAOImpl.updateMemberBalance)를 호출하는데 인수로 purchase.getTotalPrice 값을 주는 메소드 생성.
    *  DealerDAO.carQuantityMinusUpdate(PurchaseDAOImpl.updateCarQuantity)를 호출.
    */

   // 회원 잔고 업데이트
   int updateMemberBalance(int memberNo, int amount) throws SQLException;

   // 자동차 수량 감소
   int updateCarQuantity(int carNo, int amount) throws SQLException;

   // 회원 잔고 조회
   int getBalanceBySessionId(int memberNo) throws SQLException;
   
   // 구매 내역 조회

   
   Purchase allPurchase() throws SQLException;
}
