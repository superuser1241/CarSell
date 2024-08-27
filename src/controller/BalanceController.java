package controller;

import java.sql.SQLException;
import DTO.MemberSession;
import Service.MemberService;
import Service.MemberServiceImpl;

public class BalanceController {
	static MemberService memberService = new MemberServiceImpl();
	static MemberSession session = new MemberSession(); 
	
	public static void balancePlusUpdate(String memberId ,int amount){

		try {
			memberService.balancePlusUpdate(memberId , amount);
			System.out.println("충전이 완료되었습니다.");
			
		} catch (Exception e) {
			System.out.println("충전에 실패하였습니다.");
		}
	}

	 public static void balanceMinusUpdate(String memberId , int amount2) {
	        try {
	            memberService.balanceMinusUpdate(memberId,amount2);
	            System.out.println("인출이 완료되었습니다.");
	        } catch (SQLException e) {
	            System.out.println("인출 중 오류가 발생했습니다.");
	        }
	    }
	 
	 public static int balanceSelect() {
		 int balance=0;
		try {
			balance = memberService.balanceSelect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return balance;
	 }
	}