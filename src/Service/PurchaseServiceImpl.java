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

public class PurchaseServiceImpl implements PurchaseService {

   public static PurchaseServiceImpl instance;

   private DealerDAO dealerDAO= new DealerDAOImpl();// 모두 purchaseDAO에서 이미 함수 만들었음
   private PurchaseDAO purchaseDAO=new PurchaseDAOImpl();
   private MemberDAO memberDAO=new MemberDAOImpl();// 모두 purchaseDAO에서 이미 함수 만들었음

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
   public List<Dealer> DealerChoice() throws SQLException {
      return purchaseDAO.getAllDealers();
   }

   @Override
   public void purchaseInsert(int carNo, int dealerNum, String color, int sunRoof, int coolSeat, int aroundView, int totalPrice) throws SQLException {
      int memberNo = MemberSession.getInstance().getMemberNo();

      // 회원 잔고 업데이트
      purchaseDAO.updateMemberBalance(memberNo, totalPrice);

      // 자동차 수량 감소
      purchaseDAO.updateCarQuantity(carNo, 1);

      // 구매 내역 삽입
      purchaseDAO.purchaseInsert(carNo, dealerNum, color, sunRoof, coolSeat, aroundView, totalPrice);
   }

   //잔고가 충분한지 확인
   @Override
   public boolean checkAndHandleBalance(int totalPrice) throws SQLException {
      int memberNo = MemberSession.getInstance().getMemberNo();
      int currentBalance = purchaseDAO.getBalanceBySessionId(memberNo);
      return currentBalance >= totalPrice;
   }

   //로그인한
   @Override
   public int getBalance() throws SQLException {
      int memberNo = MemberSession.getInstance().getMemberNo();
      return purchaseDAO.getBalanceBySessionId(memberNo);
   }

   @Override
   public void rechargeBalance(int rechargeAmount) throws SQLException {
      int memberNo = MemberSession.getInstance().getMemberNo();
      purchaseDAO.updateMemberBalance(memberNo, -rechargeAmount); // 잔고 충전은 -rechargeAmount로 처리
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
         throw new Exception("Invalid car type");
      }

   }

   @Override
   public int getCarNoByCarName(String carName) throws SQLException
   {
      return purchaseDAO.getCarNoByCarName(carName);
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
   public List<Car> getCarList() throws SQLException
   {
      return purchaseDAO.getCarList();
   }
   
   // 구매 내역 조회
   public Purchase allPurchase() throws SQLException {      
      return purchaseDAO.allPurchase();
   }


}
