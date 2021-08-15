package kr.co.movie.review.model;

import javax.naming.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

public class ReviewDAO {
	// �̱��� ����, Ŀ�ؼ� Ǯ�� ����
	// DAO�� ������ �� getInstance() �޼���

	private DataSource ds;
	private static final int WRITE_SUCCESS = 1;
	private static final int WRITE_FAIL = 0;

	private ReviewDAO() {
		try {
			Context ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ReviewDAO dao = new ReviewDAO();

	public static ReviewDAO getInstance() {
		if (dao == null) {
			dao = new ReviewDAO();
		}
		return dao;
	}

	public int write(ReviewVO review) {
		// connection, preparedStatement ��ü ����
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO review (uid, ) VALUES()";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "");

			pstmt.executeUpdate();
			return WRITE_SUCCESS;

		} catch (Exception e) {
			System.out.println("����: " + e);
			e.printStackTrace();
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return WRITE_FAIL;
	}// end write()

	// ����
	public int delete(String rnum) {
		// ����� ������ ����
		Connection con = null;
		PreparedStatement pstmt = null;
		int resultCode;

		// Ŀ�ؼ� ���� �� ������ ����
		String sql = "DELETE FROM review WHERE rnum = ?";

		try {
			// Ŀ�ؼ� ���� �� pstmt�� ������ �ְ� �ϼ����Ѽ� ������� �ϰ�
			// close()�� �޸�ȸ������ ���ּ���

			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rnum);

			pstmt.executeUpdate();

			System.out.println("���� �����ȣ: " + rnum);
			resultCode = 1;

		} catch (Exception e) {
			System.out.println("����: " + e);
			e.printStackTrace();
			resultCode = 0;

		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return resultCode;
	}// end delete()

	/// ������Ʈ
	public int update(ReviewVO review) {
		// connection, preparedStatement ��ü ����
		Connection con = null;
		PreparedStatement pstmt = null;
		int result;
		// �����ۼ�

		String sql = "UPDATE review SET mTitle = ?, rContent = ?, rDate = ?, bHit = ? " + "WHERE rnum = ?";

		try {
			// Ŀ�ؼ� ���� �� pstmt�� ������ �ְ� �ϼ����Ѽ� ������� �ϰ�
			// close()�� �޸�ȸ������ ���ּ���

			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "");

			pstmt.executeUpdate();
			result = 1;
			System.out.println("���� ������: " + result);

		} catch (Exception e) {
			System.out.println("����: " + e);
			e.printStackTrace();
			result = 0;
			System.out.println("���� ������: " + result);
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return result;
	}// end update()

	// ������ ��ȣ�� �´� �Խù� ��������
	public List<ReviewVO> getReviewList() {
		// ���ο��� ����� ���� ����
		List<ReviewVO> reviewList = new ArrayList<ReviewVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// ������(SELECT����, ����)
		String sql = "SELECT * FROM review ORDER BY rnum DESC";
		try {
			// ���ᱸ���� �� �ۼ����ּ���. ���ϱ�������.
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReviewVO review = new ReviewVO();

//				review.setbId(rs.getInt("bId"));
//				review.setbName(rs.getString("bName"));
//				review.setbTitle(rs.getString("bTitle"));
//				review.setbContent(rs.getString("bContent"));
//				review.setbDate(rs.getTimestamp("bDate"));
//				review.setbHit(rs.getInt("bHit"));

				reviewList.add(review);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return reviewList;
	} // end getReviewList()

	public List<ReviewVO> getPageList(int pageNum) {
		// ���ο��� ����� ���� ����
		List<ReviewVO> reviewList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// ������(SELECT����, ����, 5���� pageNum�� ���缭);
		String sql = "SELECT * FROM review ORDER BY rnum DESC " + "LIMIT ?, 5";
		try {
			// ���ᱸ���� �� �ۼ����ּ���. ���ϱ�������.
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, pageNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReviewVO review = new ReviewVO();

//				review.setbId(rs.getInt("bId"));
//				review.setbName(rs.getString("bName"));
//				review.setbTitle(rs.getString("bTitle"));
//				review.setbContent(rs.getString("bContent"));
//				review.setbDate(rs.getTimestamp("bDate"));
//				review.setbHit(rs.getInt("bHit"));

				reviewList.add(review);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return reviewList;
	} // end getReviewList()

	// ����¡ ó���� ���� DB�� ��ü ������ ���� �˾ƿ���
	public int getReviewCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int countNum = 0;

		String sql = "SELECT COUNT(*) FROM review";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				countNum = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return countNum;
	} // end getReviewCount()

}// end class
