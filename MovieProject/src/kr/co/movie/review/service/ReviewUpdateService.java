package kr.co.movie.review.service;

import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.movie.review.model.ReviewDAO;
import kr.co.movie.review.model.ReviewVO;

public class ReviewUpdateService implements IReviewService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// ���� ���¹�
		HttpSession session = null;
		session = request.getSession();
		String idSession = (String) session.getAttribute("i_s");

		if (idSession == null) {
			try {
				// ���� ���ο��� �������� ��Ű��
				// �����̷�Ʈ�� �ƴϱ� ������ �����
				String ui = "users/user_login.jsp";
				RequestDispatcher dp = request.getRequestDispatcher(ui);
				dp.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 1. �Ķ���� 6�� �޾ƿ���
		String strrNum = request.getParameter("bId");
		int rNum = Integer.parseInt(strrNum);
//		String strHit = request.getParameter("bHit");
//		int bHit = Integer.parseInt(strHit);
//
//		String strbDate = request.getParameter("bDate");
//		Timestamp bDate = Timestamp.valueOf(strbDate); // strŸ������ ���� Date�� Timestamp�� �ٲٱ�
//
//		String bName = request.getParameter("bName");
//		String bTitle = request.getParameter("title");
//		String bContent = request.getParameter("content");
		// 2. VO�����ؼ� setter �����ϱ�
		ReviewVO review = new ReviewVO();
		review.setrNum(rNum);

		// 3. DAO ���� �� update���� ȣ��(update ������ �����ۼ�)
		ReviewDAO dao = ReviewDAO.getInstance();
		int resultCode = dao.update(review);
		if (resultCode == 1) {
			System.out.println("���� ���� ����");
		} else if (resultCode == 0) {
			System.out.println("������ ���� ���� ���� ����");
		}

	}// end execute()

}// end class