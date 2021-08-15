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
		// 세션 쓰는법
		HttpSession session = null;
		session = request.getSession();
		String idSession = (String) session.getAttribute("i_s");

		if (idSession == null) {
			try {
				// 서비스 내부에서 포워딩을 시키면
				// 리다이렉트가 아니기 떄문에 실행됨
				String ui = "users/user_login.jsp";
				RequestDispatcher dp = request.getRequestDispatcher(ui);
				dp.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {

			/// post방식 한글깨짐 방지 인코딩
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");

			String rContent = request.getParameter("rContent");

			
			// dao 생성
			ReviewDAO dao = ReviewDAO.getInstance();

			// VO 생성
			ReviewVO review = new ReviewVO();
			review.setrContent(rContent);

//			board.setuId(uId);
			int resultCode = dao.write(review);
			if (resultCode == 1) {
				System.out.println("DB테이블에 리뷰이 입력되었습니다");
			} else if (resultCode == 0) {
				System.out.println("에러 발생으로 리뷰가 입력되지 않았습니다");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// end execute()

}// end class
