package zx.ffts.web.action.pain;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import zx.ffts.dao.pain.UserDao;
import zx.ffts.domain.User;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements ServletResponseAware,
		ServletRequestAware {

	private User user;
	private Boolean rememberMe;
	private UserDao userDao = new UserDao();
	private HttpServletRequest request;
	private HttpServletResponse response;

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String willLog() {
		return SUCCESS;
	}

	public String login() {
		if (user != null) {
			User currentUser = userDao.login(user.getUsername(), user.getPwd());
			if (currentUser != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", currentUser);
				if (rememberMe != null && rememberMe) {
					Cookie userCookie = new Cookie("username",
							currentUser.getUsername());
					userCookie.setMaxAge(60 * 60 * 24 * 365 * 10);
					userCookie.setPath("/");
					response.addCookie(userCookie);
				}
			} else {
				request.setAttribute("msg", "loginFailed");
				return "loginFailed";
			}
		}
		return "loginSuccess";
	}

	public void uniqueUsername() throws IOException {
		Integer num = userDao.uniqueUsername(user.getUsername());
		response.getWriter().write("" + num);
	}
}
