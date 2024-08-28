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
			throws Exception {
		memberDAO.memberInsert(memberId, name, age, address, password);
	}

	/**
	 * 로그인
	 * 
	 * @param memberId 아이디
	 * @param password 패스워드
	 * @throws SQLException
	 */
	@Override
	public Member login(String memberId, String password) throws Exception {
		Member member = memberDAO.login(memberId, password);

		return member;
	}


	public Member selectMemberById(String memberId) throws Exception {
		Member member = memberDAO.selectMemberById(memberId);

		return member;
	}

	@Override
	public void balancePlusUpdate(String memberId, int Amount) throws Exception {
		memberDAO.balancePlusUpdate(memberId, Amount);

	}

	@Override
	public void balanceMinusUpdate(String memberId, int Amount2) throws Exception {
		memberDAO.balanceMinusUpdate(memberId, Amount2);
	}

	@Override
	public int balanceSelect() throws Exception {

		return memberDAO.balanceSelect(session.getMemberId());

	}


}
