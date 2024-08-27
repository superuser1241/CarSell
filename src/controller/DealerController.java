package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Car;
import Service.DealerReviewService;
import Service.DealerReviewServiceImpl;
import Service.DealerService;
import Service.DealerServiceImpl;
import Service.MemberReviewService;
import Service.MemberReviewServiceImpl;

public class DealerController {
	static DealerService dealerService = new DealerServiceImpl();
	static MemberReviewService memberReviewService = MemberReviewServiceImpl.getInstance();
	static DealerReviewService dealerReviewService = new DealerReviewServiceImpl();
	
	public static void carQuantityPlusUpdate(String carName,int amount) {
		Car car=null;
		//System.out.println("확인4");
		try {
			
			car = dealerService.carSelectByCarName(carName);
			//System.out.println("확인5");
			dealerService.carQuantityPlusUpdate(car, amount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void carSelectAll(){
		List<Car> listCar = new ArrayList<Car>();
		try {
			listCar = dealerService.carSelectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (listCar.size()==0) {
			System.out.println("조회된 차량이 없습니다.");
		}
		//listCar출력하는 뷰 출력
		for(Car car : listCar) {
			System.out.println(car);
		}
	}
	
	public static void dealerUpdate(String contents) {
		try {
			dealerService.dealerUpdate(contents);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static double selectDealerStarByDealerId() {
		
		double result = 0;
		try {
			result = dealerService.selectDealerStarByDealerId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
