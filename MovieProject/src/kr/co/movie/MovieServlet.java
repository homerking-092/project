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

//*.do로 잡힌 패턴은 .do로 끝나는 접속주소를 모두 잡아옵니다
///를 빼고 작성합니다
@WebServlet("*.do")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MovieServlet() {
		super();
		System.out.println("확장자 패턴 생성");
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("확장자 패턴 서버 연결");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("확장자 패턴 소멸");
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

	// 요청 메서드 통일(get, post)
	protected void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 서비스 호출을 위한 인터페이스
		IReviewService sv = null;

		// 세션 쓰는법
		HttpSession session = null;
		session = request.getSession();

		// 해당 로직을 실행한 뒤에 널어갈 .jsp 파일명칭/경로 지정
		String ui = null;

		// doGet에 있던 모든 코드 //
		// 확장자 패턴에서 확장자를 포함한 주소값을 가져오기 위해서 아래 코드를 사용합니다
		String uri = request.getRequestURI();
		System.out.println("uri 패턴: " + uri);

		// 콘솔이 아닌 사용자가 확인할 수 있도록 .jsp 화면에
		// 변수에 담긴 코드를 찍는 out.print(); 사용을 위한 사전준비
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// jsp페이지가 html형식으로 이루어져 있음을 알려주는 코드
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		///////////////////////////////////////////////////////////////////////////////////////////////
		// 리뷰쓰기
		if (uri.equals("/MovieProject/reviewwrite.do")) {
			sv = new ReviewWriteService();
			sv.execute(request, response);
			ui = "/reviewselect.do";

		}

		// 리뷰 수정
		else if (uri.equals("/MovieProject/reviewupdateok.do")) {
			sv = new ReviewUpdateService();
			sv.execute(request, response);
			ui = "/";

		}

		// 리뷰수정
		else if (uri.equals("/MovieProject/reviewupdate.do")) {
			sv = new ReviewWriteService();
			sv.execute(request, response);
			ui = "/";

		}

		// 리뷰삭제
		else if (uri.equals("/MovieProject/reviewdelete.do")) {
			sv = new ReviewDeleteService();
			sv.execute(request, response);
			ui = "/reviewselect.do";

		}

		// 리뷰목록
		else if (uri.equals("/MovieProject/reviewselect.do")) {
			sv = new ReviewDeleteService();
			sv.execute(request, response);
			ui = "/review/review_list";

		} else {
			System.out.println("잘못된 패턴입니다");
		}
		System.out.println("");
		
		// 조건문이 모두 작동한 뒤 forward 실행 
		RequestDispatcher dp = request.getRequestDispatcher(ui);
		dp.forward(request, response);
		
				
	}// end doRequest()

}// end class
