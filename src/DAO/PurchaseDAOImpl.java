package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBManager.DBManager;
import DTO.Car;
import DTO.Dealer;
import DTO.MemberSession;
import DTO.Purchase;

public class PurchaseDAOImpl implements PurchaseDAO
{

   private static PurchaseDAOImpl instance;

   public PurchaseDAOImpl() {}

   public static PurchaseDAOImpl getInstance()
   {
      if (instance == null)
      {
         instance = new PurchaseDAOImpl();
      }
      return instance;
   }

   @Override
   public List<Dealer> getAllDealers() throws SQLException
   {
      List<Dealer> dealers = new ArrayList<>();
      String query = "SELECT dealer_name,dealer_no, dealer_id, self, rate FROM Dealer";

      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;

      try
      {
         conn = DBManager.getConnection();
         ps = conn.prepareStatement(query);
         rs = ps.executeQuery();

         while (rs.next())
         {
            Dealer dealer = new Dealer();
            dealer.setDealerName(rs.getString("dealer_name"));
            dealer.setDealerNo(rs.getInt("dealer_no")   );
            dealer.setDealerId(rs.getString("dealer_id"));
            dealer.setSelf(rs.getString("self"));
            dealer.setRate(rs.getInt("rate"));
            dealers.add(dealer);
         }
      }
      finally
      {
         if (rs != null)
         {
            rs.close();
         }
         if (ps != null)
         {
            ps.close();
         }
         if (conn != null)
         {
            conn.close();
         }
      }
      return dealers;
   }

   @Override
   public List<String> getCarListByType(String type) throws SQLException
   {
      List<String> carList = new ArrayList<>();
      String query = "SELECT car_name FROM Car WHERE category = ?";

      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;

      try
      {
         conn = DBManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setString(1, type);
         rs = ps.executeQuery();

         while (rs.next())
         {
            carList.add(rs.getString("car_name"));
         }
      }
      finally
      {
         if (rs != null)
         {
            rs.close();
         }
         if (ps != null)
         {
            ps.close();
         }
         if (conn != null)
         {
            conn.close();
         }
      }
      return carList;
   }

   @Override
   public List<Car> getCarList() throws SQLException {
      List<Car> carList = new ArrayList<>();
      String query = "SELECT car_name, drive,fuel ,horse_power, quantity, price, category FROM Car";

      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;

      try {
         conn = DBManager.getConnection();
         ps = conn.prepareStatement(query);
         rs = ps.executeQuery();

         while (rs.next()) {
            Car car = new Car();
            //carNo는 구매자가 알 필요가 없어서 제외
            car.setCarName(rs.getString("car_name"));
            car.setDrive(rs.getString("drive"));// 구동 방식
            car.setFuel(rs.getString("fuel")); //연비
            car.setHorsePower(rs.getString("horse_power"));// 마력
            car.setQuantity(rs.getInt("quantity"));// 재고량
            car.setPrice(rs.getInt("price"));// 가격
            car.setCategory(rs.getString("category"));// 카테고리
            carList.add(car);
         }
      } finally {
         if (rs != null) {
            rs.close();
         }
         if (ps != null) {
            ps.close();
         }
         if (conn != null) {
            conn.close();
         }
      }
      return carList;
   }


   @Override
   public int getCarNoByCarName(String carName) throws SQLException
   {
      String query = "SELECT car_no FROM Car WHERE car_name = ?";
      int carNo=0;
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;

      try
      {
         conn = DBManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setString(1, carName);
         rs = ps.executeQuery();

         if (rs.next())
         {
            carNo = rs.getInt("car_no");
         }
      }
      finally
      {
         if (rs != null)
         {
            rs.close();
         }
         if (ps != null)
         {
            ps.close();
         }
         if (conn != null)
         {
            conn.close();
         }
      }
      return carNo;
   }

   @Override
   public int purchaseInsert(int carNo, int dealerNum, String color, int sunRoof, int coolSeat, int aroundView, int totalPrice) throws SQLException {
      String query = "INSERT INTO Purchase (purchase_no,member_no,sunroof, seat, around_view, color, purchase_date, price, dealer_no,car_no) " +
            "VALUES (purchase_no_seq.NEXTVAL,?, ?, ?, ?, ?,sysdate,?, ?, ?)";
      int result;
      int carno;
      Connection conn = null;
      PreparedStatement ps = null;

      try
      {
         conn = DBManager.getConnection();
         ps = conn.prepareStatement(query);

         // 세션에서 member_no 가져오기
         int sessionNum = MemberSession.getInstance().getMemberNo();

         ps.setInt(1, sessionNum);//memberNo
         ps.setInt(2, sunRoof);
         ps.setInt(3, coolSeat);
         ps.setInt(4, aroundView);
         ps.setString(5, color);
         ps.setInt(6, totalPrice);
         ps.setInt(7, dealerNum);
         ps.setInt(8, carNo);

         result = ps.executeUpdate();
      }
      finally
      {
         if (ps != null)
         {
            ps.close();
         }
         if (conn != null)
         {
            conn.close();
         }
      }
      return result;
   }

   @Override
   public int getBasePriceByCarNo(int carNo) throws SQLException
   {
      int basePrice = 0;

      String query = "SELECT price FROM Car WHERE car_no = ?";

      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;

      try
      {
         conn = DBManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setInt(1, carNo);
         rs = ps.executeQuery();

         if (rs.next())
         {
            basePrice = rs.getInt("price");
         }
      }
      finally
      {
         if (rs != null)
         {
            rs.close();
         }
         if (ps != null)
         {
            ps.close();
         }
         if (conn != null)
         {
            conn.close();
         }
      }
      return basePrice;//차량 기본 가격
   }

   // 옵션 이름에 따라 옵션 가격을 가져옴
   @Override
   public int getSunRoofPrice() throws SQLException
   {
      return getOptionPriceByName("sunroof");
   }

   @Override
   public int getCoolSeatPrice() throws SQLException
   {
      return getOptionPriceByName("seat");
   }

   @Override
   public int getAroundViewPrice() throws SQLException
   {
      return getOptionPriceByName("around_view");
   }

   // 옵션 이름에 따라 값을 가져오는 함수
   private int getOptionPriceByName(String optionName) throws SQLException
   {
      int optionPrice = 0;
      String query = "SELECT option_price FROM Car_Option WHERE option_name = ?";

      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;

      try
      {
         conn = DBManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setString(1, optionName);
         rs = ps.executeQuery();

         if (rs.next())
         {
            optionPrice = rs.getInt("option_price");
         }
      }
      finally
      {
         if (rs != null)
         {
            rs.close();
         }
         if (ps != null)
         {
            ps.close();
         }
         if (conn != null)
         {
            conn.close();
         }
      }
      return optionPrice;
   }

   //구매하면 balance가 줄고 충전하면 -값이 들어와서 balance가 늘어나는 원리
   @Override
   public int updateMemberBalance(int memberNo, int amount) throws SQLException
   {
      String query = "UPDATE Member SET balance = balance - ? WHERE member_no = ?";
      Connection conn = null;
      PreparedStatement ps = null;

      try
      {
         conn = DBManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setInt(1, amount);
         ps.setInt(2, memberNo);
         return ps.executeUpdate();
      }
      finally
      {
         if (ps != null)
         {
            ps.close();
         }
         if (conn != null)
         {
            conn.close();
         }
      }
   }

   //차량 구매하면 수량 감소
   @Override
   public int updateCarQuantity(int carNo, int amount) throws SQLException
   {
      String query = "UPDATE Car SET quantity = quantity - ? WHERE car_no = ?";
      Connection conn = null;
      PreparedStatement ps = null;

      try
      {
         conn = DBManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setInt(1, amount);
         ps.setInt(2, carNo);
         return ps.executeUpdate();
      }
      finally
      {
         if (ps != null)
         {
            ps.close();
         }
         if (conn != null)
         {
            conn.close();
         }
      }
   }

   @Override
   public int getBalanceBySessionId(int memberNo) throws SQLException
   {
      String query = "SELECT balance FROM Member WHERE member_no = ?";
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;

      try
      {
         conn = DBManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setInt(1, memberNo);
         rs = ps.executeQuery();

         if (rs.next())
         {
            return rs.getInt("balance");
         }
      }
      finally
      {
         if (rs != null)
         {
            rs.close();
         }
         if (ps != null)
         {
            ps.close();
         }
         if (conn != null)
         {
            conn.close();
         }
      }
      return 0;
   }
   
   @Override
   public Purchase allPurchase() throws SQLException {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM purchase where member_no = ?";
      Purchase purchase = null;
   
      int sessionNum = MemberSession.getInstance().getMemberNo();
      System.out.println("세선넘버 : "+sessionNum);
      
      try {
         con = DBManager.getConnection();
         ps = con.prepareStatement(sql);
         ps.setInt(1, sessionNum);
         rs = ps.executeQuery();
         while(rs.next()) { 
            purchase = new Purchase(
            rs.getInt(1),
            rs.getInt(2),
            rs.getInt(3),
            rs.getInt(4),
            rs.getInt(5),
            rs.getString(6),
            rs.getString(7),
            rs.getInt(8),
            rs.getInt(9),
            rs.getString(10));
            }
         
      } catch (SQLException e) {
         e.printStackTrace();
   } finally {
      DBManager.dbClose(con, ps, rs);
   }
   
      return purchase;
   }



   
}
