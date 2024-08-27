package Service;

import java.sql.SQLException;

import DAO.MemberDAO;
import DAO.MemberDAOImpl;
import DTO.Member;
import DTO.MemberSession;

public class MemberServiceImpl implements MemberService {
	MemberDAO memberDAO = new MemberDAOImpl();
	MemberSession session = MemberSession.getInstance(); 



	@Override
	public void memberInsert(String memberId, String name, int age, String address, String password)
			throws SQLException {
		int result = memberDAO.memberInsert(memberId, name, age, address, password);

		if (result == 0) {
			throw new SQLException("회원가입 오류입니다.");
		}
	}

	/**
	 * 로그인
	 * 
	 * @param memberId 아이디
	 * @param password 패스워드
	 * @throws SQLException
	 */
	@Override
	public Member login(String memberId, String password) throws SQLException {
		Member member = memberDAO.login(memberId, password);

		if (member == null) {
			throw new SQLException("로그인 실패: 아이디나 비밀번호가 잘못되었습니다.");
		}

		return member;
	}


	public Member selectMemberById(String memberId) throws SQLException {
		Member member = memberDAO.selectMemberById(memberId);

		if (!member.equals(memberId)) {
			throw new SQLException("정보가 일치하지 않습니다.");
		}

		return member;
	}

	@Override
	public void balancePlusUpdate(String memberId, int Amount) throws Exception {
		memberDAO.balancePlusUpdate(memberId, Amount);
		
		if(Amount < 0) {
			throw new Exception("터무니 없는 금액입니다.");
		
		}

	}

	@Override
	public void balanceMinusUpdate(String memberId, int Amount2) throws SQLException {
		memberDAO.balanceMinusUpdate(memberId, Amount2);
	}

	@Override
	public int balanceSelect() throws SQLException {

		return memberDAO.balanceSelect(session.getMemberId());

	}


}
