package kr.co.movie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.movie.review.service.IReviewService;
import kr.co.movie.review.service.ReviewDeleteService;
import kr.co.movie.review.service.ReviewUpdateService;
import kr.co.movie.review.service.ReviewWriteService;

/**
 * Servlet implementation class MovieServlet
 */

//*.do�� ���� ������ .do�� ������ �����ּҸ� ��� ��ƿɴϴ�
///�� ���� �ۼ��մϴ�
@WebServlet("*.do")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MovieServlet() {
		super();
		System.out.println("Ȯ���� ���� ����");
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Ȯ���� ���� ���� ����");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("Ȯ���� ���� �Ҹ�");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// ��û �޼��� ����(get, post)
	protected void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���� ȣ���� ���� �������̽�
		IReviewService sv = null;

		// ���� ���¹�
		HttpSession session = null;
		session = request.getSession();

		// �ش� ������ ������ �ڿ� �ξ .jsp ���ϸ�Ī/��� ����
		String ui = null;

		// doGet�� �ִ� ��� �ڵ� //
		// Ȯ���� ���Ͽ��� Ȯ���ڸ� ������ �ּҰ��� �������� ���ؼ� �Ʒ� �ڵ带 ����մϴ�
		String uri = request.getRequestURI();
		System.out.println("uri ����: " + uri);

		// �ܼ��� �ƴ� ����ڰ� Ȯ���� �� �ֵ��� .jsp ȭ�鿡
		// ������ ��� �ڵ带 ��� out.print(); ����� ���� �����غ�
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// jsp�������� html�������� �̷���� ������ �˷��ִ� �ڵ�
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		///////////////////////////////////////////////////////////////////////////////////////////////
		// ���侲��
		if (uri.equals("/MovieProject/reviewwrite.do")) {
			sv = new ReviewWriteService();
			sv.execute(request, response);
			ui = "/reviewselect.do";

		}

		// ���� ����
		else if (uri.equals("/MovieProject/reviewupdateok.do")) {
			sv = new ReviewUpdateService();
			sv.execute(request, response);
			ui = "/";

		}

		// �������
		else if (uri.equals("/MovieProject/reviewupdate.do")) {
			sv = new ReviewWriteService();
			sv.execute(request, response);
			ui = "/";

		}

		// �������
		else if (uri.equals("/MovieProject/reviewdelete.do")) {
			sv = new ReviewDeleteService();
			sv.execute(request, response);
			ui = "/reviewselect.do";

		}

		// ������
		else if (uri.equals("/MovieProject/reviewselect.do")) {
			sv = new ReviewDeleteService();
			sv.execute(request, response);
			ui = "/review/review_list";

		} else {
			System.out.println("�߸��� �����Դϴ�");
		}
		System.out.println("");
		
		// ���ǹ��� ��� �۵��� �� forward ���� 
		RequestDispatcher dp = request.getRequestDispatcher(ui);
		dp.forward(request, response);
		
				
	}// end doRequest()

}// end class