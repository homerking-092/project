package kr.co.movie.review.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IReviewService {
	// 서비스 인터페이스 내에는 실행 메서드 하나만 만듭니다
	void execute(HttpServletRequest request, HttpServletResponse response);

}
