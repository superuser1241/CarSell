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
		} catch (Exception e) {
			System.out.println("재고 추가에 실패했습니다");
		}
	}
	
	public static void carSelectAll(){
		List<Car> listCar = new ArrayList<Car>();
		try {
			listCar = dealerService.carSelectAll();
		} catch (Exception e) {
			System.out.println("쿼리문을 다시 확인해 주세요");
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
		} catch (Exception e) {
			System.out.println("자기소개 수정에 실패했습니다");
		}
	}
	
	
	public static double selectDealerStarByDealerId() {
		
		double result = 0;
		try {
			result = dealerService.selectDealerStarByDealerId();
		} catch (Exception e) {
			System.out.println("별점 조회에 실패했습니다");
		}
		return result;
	}
}
