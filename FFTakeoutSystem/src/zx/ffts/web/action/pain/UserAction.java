package zx.ffts.web.action.pain;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import zx.ffts.dao.pain.UserDao;
import zx.ffts.dao.transaction.UserCenterTransaction;
import zx.ffts.domain.User;
import zx.ffts.utils.PageBean;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements ServletResponseAware,
		ServletRequestAware {

	private User user;
	private Boolean rememberMe;
	private UserDao userDao = new UserDao();
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private Double ti;
	private Integer page;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	private Integer size;

	public Double getTi() {
		return ti;
	}

	public void setTi(Double ti) {
		this.ti = ti;
	}

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
		this.session = request.getSession();
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

	public String logout() {
		session.removeAttribute("user");
		return "loginFailed";
	}

	public String getPageBean() {
		PageBean<Map<String, Object>> bean = new PageBean<Map<String, Object>>(
				page, size);
		User currentuser = (User) session.getAttribute("user");
		bean.setBeanList((List<Map<String, Object>>) userDao.doTransaction(new UserCenterTransaction(),
				currentuser.getUserid(), (ti / 60 / 24), ((page - 1) * size + 1),
				(page * size)));
		bean.setTotalCounts(userDao.getOrderNumber(currentuser.getUserid()));
		request.setAttribute("bean", bean);
		return "centerComplete";
	}
}
