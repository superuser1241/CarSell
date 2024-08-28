package Service;

import java.sql.SQLException;

import DTO.Member;

public interface MemberService {
	/**
	 * 회원가입
	 * 
	 * @return int =>성공여부
	 * @throws Exception 
	 */
	void memberInsert(String memberId, String name, int age, String address, String password) 
			throws Exception;

	/**
	 * 로그인
	 * 
	 * @param memberId 아이디
	 * @param password 패스워드
	 * @return Member객체 => session에 담기 위함.
	 * @throws SQLException 
	 */
	Member login(String memberId, String password) throws Exception;

	/**
	 * ID를 이용해서 해당 Member찾기
	 * 
	 * @param memberId = 회원아이디
	 * @return Member = 회원
	 * @throws Exception 
	 */

	Member selectMemberById(String memberId)throws Exception;

	/**
	 * 충전하기
	 * @throws SQLException 
	 */
	void balancePlusUpdate(String memberId,int amount1) throws Exception;

	/**
	 * 인출하기 or 차량구매 시 잔액
	 * @return 
	 */
	
	void balanceMinusUpdate(String memberId, int Amount2) throws Exception;
	
	int balanceSelect() throws Exception;
}
