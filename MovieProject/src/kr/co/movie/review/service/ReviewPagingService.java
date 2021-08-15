package kr.co.movie.review.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.movie.review.model.ReviewDAO;
import kr.co.movie.review.model.ReviewPageDTO;
import kr.co.movie.review.model.ReviewVO;

public class ReviewPagingService implements IReviewService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// page �Ķ���Ϳ� �ִ� ���� �����ɴϴ�.
		// hint : ?page=��������ȣ
		// page�Ķ���Ͱ� ���ٸ� strPage�� null�� �����
		String strPage = request.getParameter("page");
		// null�� ����� ��Ȳ���� int�� �ٲ��� �� ����
		// �Ķ���Ͱ� ������ �� �⺻ �������� 1��������
		int page = 1;
		if (strPage != null) {
			page = Integer.parseInt(strPage);
		}
		// DAO����
		ReviewDAO dao = ReviewDAO.getInstance();

		// ���� ���� �ִ� �������� ��ü �� ��������
		// �������� �׳� �ѱ��� �ʰ�, ���۹�ȣ�� ����ؼ� �ѱ�.
		List<ReviewVO> reviewList = dao.getPageList((page - 1) * 10);
		System.out.println("����¡ �۵� : " + reviewList);
		// ���� �� ��ü ������ ���� ��ȸ���� ������ ������ DTO�� �Ѱ���.
		int countNum = dao.getReviewCount();

		// DTO�� ������ ������ �ϴܿ� ��ũ���� ������ ����ϴ°�.
		ReviewPageDTO pageDTO = new ReviewPageDTO(countNum, page, reviewList);

		System.out.println("��ũ��ư ���� : " + pageDTO);

		// �������ϱ����� �����ϱ�.
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("pageDTO", pageDTO);
	}

}
