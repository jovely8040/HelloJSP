import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
// 필터도 생명주기를 갖는다
public class EncodingFilter implements Filter {
	// 필터가 실행 되었을 때 초기화 담당
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("EncodingFilter::init");
		Filter.super.init(filterConfig);
	}
	
	// 실제 필터
	@Override
	public void doFilter(ServletRequest req,
			ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Encodingfilter::doChain Begin");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charest=UTF-8");
		// 다음 연결된 필터로 요청과 응답을 전달
		chain.doFilter(req, resp);
		
		System.out.println("EncodingFilter::doChain End");
	}
	
	// 필터의 해제
	@Override
	public void destroy() {
		System.out.println("EncodingFilter::destroy");
		Filter.super.destroy();
	}

}
