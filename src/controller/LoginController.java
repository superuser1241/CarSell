package controller;

import Service.DealerService;
import Service.DealerServiceImpl;
import Service.MemberService;
import Service.MemberServiceImpl;
import view.DealerMenuView;
import view.MemberMenuView;

public class LoginController {
	static MemberService memberService = new MemberServiceImpl();
	static DealerService dealerService = new DealerServiceImpl();

	
	public static void login(String memberId, String password) {
		try {
			if(memberId.startsWith("###")) { //if memberId가 딜러id용 문자인 ###으로 시작하고 있다면->
			dealerService.login(memberId, password);
			DealerMenuView.menu();
			}else {	//else->
			memberService.login(memberId, password);
			MemberMenuView.menu();
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}
}


