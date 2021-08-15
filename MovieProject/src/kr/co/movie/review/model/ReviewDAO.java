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
	// 싱글톤 패턴, 커넥션 풀을 적용
	// DAO의 생성자 및 getInstance() 메서드

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
		// connection, preparedStatement 객체 선언
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
			System.out.println("에러: " + e);
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

	// 삭제
	public int delete(String rnum) {
		// 사용할 변수들 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		int resultCode;

		// 커넥션 연결 및 쿼리문 실행
		String sql = "DELETE FROM review WHERE rnum = ?";

		try {
			// 커넥션 생성 및 pstmt에 쿼리문 넣고 완성시켜서 실행까지 하고
			// close()로 메모리회수까지 해주세욘

			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rnum);

			pstmt.executeUpdate();

			System.out.println("삭제 리뷰번호: " + rnum);
			resultCode = 1;

		} catch (Exception e) {
			System.out.println("에러: " + e);
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

	/// 업데이트
	public int update(ReviewVO review) {
		// connection, preparedStatement 객체 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		int result;
		// 구문작성

		String sql = "UPDATE review SET mTitle = ?, rContent = ?, rDate = ?, bHit = ? " + "WHERE rnum = ?";

		try {
			// 커넥션 생성 및 pstmt에 쿼리문 넣고 완성시켜서 실행까지 하고
			// close()로 메모리회수까지 해주세욘

			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "");

			pstmt.executeUpdate();
			result = 1;
			System.out.println("정보 변경결과: " + result);

		} catch (Exception e) {
			System.out.println("에러: " + e);
			e.printStackTrace();
			result = 0;
			System.out.println("정보 변경결과: " + result);
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

	// 페이지 번호에 맞는 게시물 가져오기
	public List<ReviewVO> getReviewList() {
		// 내부에서 사용할 변수 선언
		List<ReviewVO> reviewList = new ArrayList<ReviewVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 쿼리문(SELECT구문, 역순)
		String sql = "SELECT * FROM review ORDER BY rnum DESC";
		try {
			// 연결구문을 다 작성해주세요. 리턴구문까지.
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
		// 내부에서 사용할 변수 선언
		List<ReviewVO> reviewList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 쿼리문(SELECT구문, 역순, 5개씩 pageNum에 맞춰서);
		String sql = "SELECT * FROM review ORDER BY rnum DESC " + "LIMIT ?, 5";
		try {
			// 연결구문을 다 작성해주세요. 리턴구문까지.
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

	// 페이징 처리를 위해 DB내 전체 데이터 개수 알아오기
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
