package zx.ffts.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * 处理全站编码过滤器
 * 
 * @author Pain
 * 
 */
public class RootFilter implements Filter {

	@Override
	public void destroy() {
	}

	/**
	 * 处理全站编码问题
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 如果是get请求，将request替换为编码后的request
		if (((HttpServletRequest) request).getMethod().equalsIgnoreCase("GET")) {
			request = new EncodeRequest((HttpServletRequest) request);
		}

		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
