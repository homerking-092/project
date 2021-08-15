package kr.co.movie.review.service;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.movie.review.model.ReviewDAO;
import kr.co.movie.review.model.ReviewVO;

public class ReviewListService implements IReviewService {

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
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		List<ReviewVO> reviewList = dao.getReviewList();
		
		request.setAttribute("reviewList", reviewList);
		System.out.println("service �Խù� ������: " + reviewList);
		
		
		
		

	}// end execute()

}// end class
