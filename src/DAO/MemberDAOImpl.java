package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBManager.DBManager;
import DTO.Member;
import DTO.MemberSession;


public class MemberDAOImpl implements MemberDAO {
	MemberSession memberSession = MemberSession.getInstance();
	@Override
	public int memberInsert(String memberId, String name, int age, String address, String password) throws SQLException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    int result = 0;

		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(
					"INSERT INTO member (member_no, member_id, name, age, address, password, balance) "
							+ "VALUES (member_no_seq.nextval, ?, ?, ?, ?, ?, 0)");

			ps.setString(1, memberId);
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setString(4, address);
			ps.setString(5, password);

			result = ps.executeUpdate();
		} finally {
			DBManager.dbClose(con, ps);
		}


		return result;
	}


	@Override
	public Member login(String memberId, String password) throws SQLException {//
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Member member = null;
		String sql = "select * from Member where member_Id = ? and password = ?";
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, memberId);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (rs.next()) {
				member = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
			}

			
			MemberSession ms = MemberSession.getInstance();
            ms.setMemberNo(member.getMemberNo());
            ms.setMemberId(member.getMemberId());
            ms.setName(member.getName());
            ms.setAge(member.getAge());
            ms.setAddress(member.getAddress());
            ms.setPassword(member.getPassword());
            ms.setBalance(member.getBalance());
			
			
			
		}catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBManager.dbClose(con, ps, rs);
		}
		return member;
	}

	/**
	 * ID를 이용해서 해당 Member찾기
	 * 
	 * 
	 * // 호석님이 도와주셨음.
	 * 
	 * @param memberId = 회원아이디
	 * @return Member = 회원
	 */
	@Override
	public Member selectMemberById(String memberId) throws SQLException {//
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Member member = null;

		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("select * from Member where memberId=?");
			ps.setString(1, memberId);

			rs = ps.executeQuery();

			if (rs.next()) {
				member = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBManager.dbClose(con, ps, rs);
		}

		return member;
	}

	/**
	 * 충전하기 쿼리문으로 (balance = balance + amount)식으로 만들기
	 */

	@Override
	public int balancePlusUpdate(String memberId ,int Amount) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;		
		int total = 0;
		Member member = null;
		
		String sql = "update member set balance = balance + ? where member_id = ?";

		try {
			
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
		
			ps.setInt(1, Amount);
			ps.setString(2, memberId);
			
			total = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.dbClose(con, ps);
		}

		return total;
	}

	/**
	 * 인출하기 or 차량구매 시 잔액 쿼리문으로 (balance = balance - amount)식으로 만들기
	 */

	@Override
	public int balanceMinusUpdate(String memberId ,int Amount) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;		
		int total = 0;
		
		String sql = "update member set balance = balance - ? where member_id = ?";

		try {
			
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
		
			ps.setInt(1, Amount);
			ps.setString(2, memberId);
			
			total = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.dbClose(con, ps);
		}

		return total;

	}
	@Override
	public int balanceSelect(String memberId) throws SQLException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;		
		int total = 0;
		
		String sql = "select balance from member where member_id = ?";
		
		try {
			
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, memberId);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.dbClose(con, ps, rs);
		}
		
		return total;
		
	}

	
	
}
