package kr.co.movie.review.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.movie.review.model.ReviewDAO;

public class ReviewDeleteService implements IReviewService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		// rnum �Ķ����
		String rNum = request.getParameter("rNum");

		// DAO ����
		ReviewDAO dao = ReviewDAO.getInstance();

		System.out.println("���� ���� ��ȣ: " + rNum);

		// delete ������ rNum �־ ����
		int resultCode = dao.delete(rNum);
		if (resultCode == 1) {
			System.out.println("db review ���̺��� ���䰡 �����Ǿ����ϴ�");
		} else if (resultCode == 0) {
			System.out.println("���� �߻����� ���䰡 �������� �ʾҽ��ϴ�");
		}

	}

}
