package Service;

import java.sql.SQLException;
import java.util.List;

import DAO.DealerDAO;
import DAO.DealerDAOImpl;
import DAO.MemberDAO;
import DAO.MemberDAOImpl;
import DAO.PurchaseDAO;
import DAO.PurchaseDAOImpl;
import DTO.Car;
import DTO.Dealer;
import DTO.MemberSession;
import DTO.Purchase;

public class PurchaseServiceImpl implements PurchaseService{

   public static PurchaseServiceImpl instance;
   private PurchaseDAO purchaseDAO=new PurchaseDAOImpl();
   

   private PurchaseServiceImpl() {}

   public static PurchaseServiceImpl getInstance()
   {
      if (instance == null)
      {
         instance = new PurchaseServiceImpl();
      }
      return instance;
   }

   //dealerDAO에서 해결
   @Override
   public List<Dealer> DealerChoice() throws Exception
   {
      try
      {
         return purchaseDAO.getAllDealers();
      }
      catch(SQLException e)
      {
         throw new Exception("딜러 목록을 가지고 오지 못 했습니다");
      }
   }

   @Override
   public void purchaseInsert(int carNo, int dealerNum, String color, int sunRoof, int coolSeat, int aroundView, int totalPrice) throws Exception
   {
      try
      {
         int memberNo = MemberSession.getInstance().getMemberNo();
         // 자동차 수량 감소
         purchaseDAO.updateCarQuantity(carNo, 1);
         
         // 회원 잔고 업데이트
         purchaseDAO.updateMemberBalance(memberNo, totalPrice);
         // 구매 내역 삽입
         purchaseDAO.purchaseInsert(carNo, dealerNum, color, sunRoof, coolSeat, aroundView, totalPrice);
      }
      catch(SQLException e)
      {
         throw new Exception("구매 실패했습니다");
      }
   }

   //잔고가 충분한지 확인
   @Override
   public boolean checkAndHandleBalance(int totalPrice) throws Exception
   {
      try
      {
         int memberNo = MemberSession.getInstance().getMemberNo();
         int currentBalance = purchaseDAO.getBalanceBySessionId(memberNo);
         return currentBalance >= totalPrice;
      }
      catch(SQLException e)
      {
         throw new Exception("현재 잔고와 차량 가격 비교에 실패했습니다");
      }

   }

   //로그인한
   @Override
   public int getBalance() throws Exception
   {
      try
      {
         int memberNo = MemberSession.getInstance().getMemberNo();
         return purchaseDAO.getBalanceBySessionId(memberNo);
      }
      catch(SQLException e)
      {
         throw new Exception("현재 계정의 잔고를 불러오지 못 했습니다");
      }
   }

   @Override
   public void rechargeBalance(int rechargeAmount) throws Exception
   {
      try
      {
         int memberNo = MemberSession.getInstance().getMemberNo();
         purchaseDAO.updateMemberBalance(memberNo, -rechargeAmount); // 잔고 충전은 -rechargeAmount로 처리
      }
      catch(SQLException e)
      {
         throw new Exception("잔고 충전에 실패했습니다");
      }
   }

   @Override
   public List<String> getCarListByType(String type) throws Exception
   {
      if(type.equalsIgnoreCase("SUV")) // 대소문자 구분없이
      {
         return purchaseDAO.getCarListByType("SUV");
      }
      else if(type.equalsIgnoreCase("Sedan"))
      {
         return purchaseDAO.getCarListByType("Sedan");
      }
      else
      {
         throw new Exception("해당 타입(카테고리)에 맞는 자동차 리스트를 얻는데 실패했습니다");
      }

   }

   @Override
   public int getCarNoByCarName(String carName) throws Exception
   {
      try
      {
         return purchaseDAO.getCarNoByCarName(carName);
      }
      catch(SQLException e)
      {
         throw new Exception("차 이름으로 차량 번호를 얻지 못 했습니다");
      }

   }

   @Override
   public int calculateTotalPrice(int carNo, int sunRoof, int coolSeat, int aroundView) throws SQLException {
      int basePrice=purchaseDAO.getBasePriceByCarNo(carNo);
      int sunRoofPrice=sunRoof==1 ? purchaseDAO.getSunRoofPrice() : 0; // 썬루프가 선택된 경우에만 디비에서 정보 불러와
      int coolSeatPrice=coolSeat==1 ? purchaseDAO.getCoolSeatPrice() : 0;
      int aroundViewPrice=aroundView==1 ? purchaseDAO.getAroundViewPrice() : 0;
      return basePrice+sunRoofPrice+coolSeatPrice+aroundViewPrice;
   }

   //모든 종류의 차들을 가지고 옴
   @Override
   public List<Car> getCarList() throws Exception
   {
      try
      {
         return purchaseDAO.getCarList();
      }
      catch (SQLException e)
      {
         throw new Exception("모든 종류의 차들을 불러오지 못 했습니다");
      }
   }

   // 구매 내역 조회
   public Purchase allPurchase() throws Exception
   {
      try
      {
         return purchaseDAO.allPurchase();
      }
      catch (SQLException e)
      {
         throw new Exception("구매 내역 조회에 실패했습니다");
      }
   }


}
