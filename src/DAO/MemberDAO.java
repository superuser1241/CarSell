package DAO;

import java.sql.SQLException;

import DTO.Member;

public interface MemberDAO 
{	
	
	/**
	 * 회원가입
	 * @return int =>성공여부
	 * @throws SQLException 
	 */
	int memberInsert(String memberId,String name, int age, String address, String password) throws SQLException;
	
	/**
	 * 로그인
	 * @param memberId 아이디
	 * @param password 패스워드 
	 * @return	Member객체 =>session에 담기 위함.
	 */
	Member login(String memberId, String password) throws SQLException;
	
	
	/**
	 * ID를 이용해서 해당 Member찾기
	 * @param memberId = 회원아이디
	 * @return Member = 회원
	 */
	Member selectMemberById(String memberId) throws SQLException;
	
	
	/**
	 * 충전하기 쿼리문으로 (balance = balance + amount)식으로 만들기
	 */
	int balancePlusUpdate(String memberId, int Amount) throws SQLException;
	
	/**
	 * 인출하기 or 차량구매 시 잔액
	 * 쿼리문으로 (balance = balance - amount)식으로 만들기
	 */

	int balanceMinusUpdate(String memberId,int Amount2) throws SQLException;
	/**
	 * 아이디를 이용해서 잔액 조회
	 * @param memberId
	 * @return int (balance)
	 * @throws SQLException
	 */

	int balanceSelect(String memberId) throws SQLException;

	
	
}
