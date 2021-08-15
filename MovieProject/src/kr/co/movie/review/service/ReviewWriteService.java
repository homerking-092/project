package kr.co.movie.review.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.movie.review.model.ReviewDAO;
import kr.co.movie.review.model.ReviewVO;

public class ReviewWriteService implements IReviewService {

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

		try {

			/// post��� �ѱ۱��� ���� ���ڵ�
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");

			String rContent = request.getParameter("rContent");

			
			// dao ����
			ReviewDAO dao = ReviewDAO.getInstance();

			// VO ����
			ReviewVO review = new ReviewVO();
			review.setrContent(rContent);

//			board.setuId(uId);
			int resultCode = dao.write(review);
			if (resultCode == 1) {
				System.out.println("DB���̺� ������ �ԷµǾ����ϴ�");
			} else if (resultCode == 0) {
				System.out.println("���� �߻����� ���䰡 �Էµ��� �ʾҽ��ϴ�");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// end execute()

}// end class
