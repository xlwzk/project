package zx.ffts.web.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 编码类Request
 * @author Pain
 *
 */
public class EncodeRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;

	public EncodeRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	public String getParameter(String param) {
		String value = request.getParameter(param);
		if (value != null && !value.isEmpty()) {
			try {
				value = new String(value.getBytes("iso-8859-1"), "utf-8");
				return value;
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		} else {
			return null;
		}
	}
}
