package controller;

import java.util.List;

import DTO.Member;
import Service.MemberService;
import Service.MemberServiceImpl;

public class MemberRegisterController { //회원가입컨트롤러
	
	static MemberService memberService = new MemberServiceImpl();
	
	public static void memberInsert(String memberId, String name, int age, String address, String password) {

		try {
			/**이미 있는 회원인지 중복 체크
			 * 이미 있는 회원이면->예외처리
			 * 없는 회원이면-> 등록 진행
			 **/

			//서비스.등록함수 호출(인수: member)
			memberService.memberInsert(memberId, name, age, address, password);
		
			
			//완료시: 회원가입 성공했다 메시지 출력하는 뷰 호출 
			System.out.println("회원가입 성공");
		}catch (Exception e) {
			System.out.println("회원가입 실패");
		
		}
	}
}
