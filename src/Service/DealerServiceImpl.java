package Service;

import java.sql.SQLException;
import java.util.List;

import DAO.DealerDAO;
import DAO.DealerDAOImpl;
import DTO.Car;
import DTO.Dealer;
import DTO.DealerSession;

public class DealerServiceImpl implements DealerService {
	
	DealerDAO dealerDAO = new DealerDAOImpl();
	DealerSession dealerSession = DealerSession.getInstance();
	
	@Override
	public void login(String dealerId, String password) throws Exception {
		dealerDAO.login(dealerId, password);
	}

	@Override
	public Dealer selectMemberById(String dealerId) {
		Dealer dealer = dealerDAO.selectDealerById(dealerId);
		return dealer;
	}

	@Override
	public void dealerUpdate(String contents) throws SQLException {
		dealerDAO.dealerUpdate(dealerSession.getDealerId(), contents);
	}

	@Override
	public void carQuantityPlusUpdate(Car car, int amount) throws SQLException {
		//System.out.println("확인6");
		dealerDAO.carQuantityPlusUpdate(car, amount);
	}

	@Override
	public void carQuantityMinusUpdate(Car car, int amount) throws SQLException {
		dealerDAO.carQuantityMinusUpdate(car, amount);
	}

	@Override
	public List<Car> carSelectSuv() throws SQLException {
		List<Car> listCar = dealerDAO.carSelectSuv();
		return listCar;
	}

	@Override
	public List<Car> carSelectSedan() throws SQLException {
		List<Car> listCar = dealerDAO.carSelectSedan();
		return listCar;
	}

	@Override
	public List<Car> carSelectAll() throws SQLException {
		List<Car> listCar = dealerDAO.carSelectAll();
		return listCar;
	}

	@Override
	public Car carSelectByCarName(String CarName) throws SQLException {
		Car car= dealerDAO.carSelectByCarName(CarName);
		return car;
	}

	@Override
	public double selectDealerStarByDealerId() throws Exception {
		
		double result = dealerDAO.selectDealerStarByDealerId();
		return result;
	}

	

}
