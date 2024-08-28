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
import DTO.DealerSession;

public class DealerDAOImpl implements DealerDAO {
	DealerReviewDAO dealerReviewDAO = new DealerReviewDAOImpl();
	@Override
	public int login(String dealerId, String password) throws SQLException {
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int result = 0;
		
		try {
			con = DBManager.getConnection();
			ps= con.prepareStatement("select * from dealer where dealer_id=? and dealer_password=?");
			ps.setString(1, dealerId);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				DealerSession dealerSession = DealerSession.getInstance();
				dealerSession.setDealerNo(rs.getInt(1));
				dealerSession.setDealerName(rs.getString(2));
				dealerSession.setDealerId(rs.getString(3));
				dealerSession.setDealerPass(rs.getString(4));
				dealerSession.setDealerOption(rs.getString(5));
				dealerSession.setSelf(rs.getString(6));
				dealerSession.setRate(rs.getInt(7));
		
				result = 1;
			}
		}finally {
			DBManager.dbClose(con, ps, rs);
		}
		
		return result;
	}

	@Override
	public Dealer selectDealerById(String dealerId) {
		DealerSession dealerSession = DealerSession.getInstance();
		if (dealerSession.getDealerId().equals(dealerId)) {
			Dealer dealer = new Dealer(dealerSession.getDealerNo(), dealerSession.getDealerName(), dealerSession.getDealerId(), dealerSession.getDealerPass(), dealerSession.getDealerOption(), dealerSession.getSelf(), dealerSession.getRate());
			return dealer;
		}
		return null;
	}

	@Override
	public int dealerUpdate(String sessionId, String contents) throws SQLException {
		DealerSession dealerSession = DealerSession.getInstance();
		int result = 0;
		
		if (dealerSession.getDealerId().equals(sessionId)) {
			Connection con=null;
			PreparedStatement ps=null;
			try {
				con = DBManager.getConnection();
				ps= con.prepareStatement("update dealer set self=? where dealer_id=?");
				ps.setString(1, contents);
				ps.setString(2, sessionId);
				
				ps.executeQuery();
				
				result = 1;
			}finally {
				DBManager.dbClose(con, ps);
			}
		}
		return result;
	}

	@Override
	public int carQuantityPlusUpdate(Car car, int amount) throws SQLException {
		//System.out.println("확인1");
		
		DealerSession dealerSession = DealerSession.getInstance();
		int result = 0;
		
		Connection con=null;
		PreparedStatement ps=null;
		String sql = "update car set quantity=quantity+? where car_name=?";
		
		try {
			//System.out.println("확인2");
			con = DBManager.getConnection();
			ps= con.prepareStatement(sql);
			
			
			ps.setInt(1, amount);
			ps.setString(2, car.getCarName());
			
				
			result = ps.executeUpdate();
			//System.out.println("확인3");
		}finally {
			DBManager.dbClose(con, ps);
		}
		return result;	
	}

	@Override
	public int carQuantityMinusUpdate(Car car, int amount) throws SQLException {
		DealerSession dealerSession = DealerSession.getInstance();
		int result = 0;
		
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con = DBManager.getConnection();
			ps= con.prepareStatement("update car set quantity=? where car_name=?");
			ps.setInt(1, car.getQuantity()-amount);
			ps.setString(2, car.getCarName());
				
			ps.executeQuery();
				
			result = 1;
		}finally {
			DBManager.dbClose(con, ps);
		}
		return result;
	}

	@Override
	public List<Car> carSelectSuv() throws SQLException {
		List<Car> listCar = new ArrayList<Car>();
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps= con.prepareStatement("select * from car where category=?");
			ps.setString(1, "SUV");
				
			rs=ps.executeQuery();
				
			while(rs.next()) {
				Car car = new Car(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
				listCar.add(car);
			}
		}finally {
			DBManager.dbClose(con, ps, rs);
		}
		
		return listCar;
	}

	@Override
	public List<Car> carSelectSedan() throws SQLException {
		List<Car> listCar = new ArrayList<Car>();
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps= con.prepareStatement("select * from car where category=?");
			ps.setString(1, "세단");
				
			rs=ps.executeQuery();
				
			while(rs.next()) {
				Car car = new Car(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
				listCar.add(car);
			}
		}finally {
			DBManager.dbClose(con, ps, rs);
		}
		
		return listCar;
	}

	@Override
	public List<Car> carSelectAll() throws SQLException {
		List<Car> listCar = new ArrayList<Car>();
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps= con.prepareStatement("select * from car");
				
			rs=ps.executeQuery();
				
			while(rs.next()) {
				Car car = new Car(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
				listCar.add(car);
			}
		}finally {
			DBManager.dbClose(con, ps, rs);
		}
		
		return listCar;
	}

	@Override
	public Car carSelectByCarName(String CarName) throws SQLException {
		Car car = null;
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps= con.prepareStatement("select * from car where car_name=?");
			ps.setString(1, CarName);
				
			rs=ps.executeQuery();
				
			while(rs.next()) {
				car = new Car(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
			}
		}finally {
			DBManager.dbClose(con, ps, rs);
		}
		
		return car;
	}
	
	
	
	
	
	@Override
	public double selectDealerStarByDealerId() throws Exception {
		DealerSession dealerSession = DealerSession.getInstance();
		String DealerId = dealerSession.getDealerId();
		int sessionNum = dealerNumFindByDealerId(DealerId);
		
		List<Integer> purchaseNum = dealerReviewDAO.purchaseNumFindByDealerSessionNum2(sessionNum);
		List<Integer> purchaseList = new ArrayList<Integer>();
		
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		double result = 0;
		int count = 0;
		double dealStar = 0;
		String sql = "SELECT DEAL_STAR FROM REVIEW WHERE PURCHASE_NO = ?";
		try {
			con = DBManager.getConnection();
			for( int   purchase  :   purchaseNum)
			{
			ps= con.prepareStatement(sql);
			ps.setInt(1, purchase);
			
			rs = ps.executeQuery();
				if(rs.next())
				{
					result += rs.getInt(1);
					count++;
					
				}
			}
			dealStar = result/count;
			result = dealStar;
		}finally {
			DBManager.dbClose(con, ps, rs);
		}
		System.out.printf("딜러평점: %.2f\n",result);
		return result;
	}
	
	public List<Dealer> getAllDealers() throws SQLException {
		List<Dealer> listDealer = new ArrayList<Dealer>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			ps= con.prepareStatement("select * from ?");
			ps.setString(1, "dealer");
				
			rs=ps.executeQuery();
			
			while(rs.next()) {
				Dealer dealer = new Dealer(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
				listDealer.add(dealer);
			}
		}finally {
			DBManager.dbClose(con, ps, rs);
		}
		
		return listDealer;
	}

	@Override
	public int dealerNumFindByDealerId(String sessionId) throws SQLException 
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int sessionNum = 0;
		String sql = "SELECT DEALER_NO FROM DEALER WHERE DEALER_ID = ?";
		
		try 
		{
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, sessionId);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
			sessionNum = rs.getInt(1);
			}
		}
		finally
		{
			DBManager.dbClose(con, ps, rs);
		}
		
		return sessionNum;
	}


}
