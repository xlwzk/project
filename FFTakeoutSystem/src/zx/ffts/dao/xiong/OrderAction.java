package zx.ffts.dao.xiong;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

public class OrderAction implements ServletResponseAware {

	private HttpServletResponse response;
	private Integer userid;
	private Integer rtid;
	private Integer muid;
	private OrderDao orderDao = new OrderDao();
	private OrderFunctionDao functionDao = new OrderFunctionDao();

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getRtid() {
		return rtid;
	}

	public void setRtid(Integer rtid) {
		this.rtid = rtid;
	}

	public Integer getMuid() {
		return muid;
	}

	public void setMuid(Integer muid) {
		this.muid = muid;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void addOrder() throws IOException {
		int line = orderDao.addOrder(userid, muid, rtid);
		if (line > 0) {
			line = functionDao.orderCount(userid, rtid, muid);
			response.getWriter().write("" + line);
		} else {
			response.getWriter().write("fail");
		}
	}

	public void minusOrder() throws IOException {
		int line = orderDao.minusOrder(userid, muid, rtid);
		if (line > 0) {
			line = functionDao.orderCount(userid, rtid, muid);
			response.getWriter().write("" + line);
		} else {
			response.getWriter().write("fail");
		}
	}

	public void clearCart() throws IOException {
		int line = orderDao.clearCart(userid, rtid);
		if (line > 0) {
			response.getWriter().write("success");
		} else {
			response.getWriter().write("fail");
		}
	}
	
	public void getCart() throws IOException {
		response.getWriter().write(functionDao.getCart(userid, rtid).toString());
	}
	
	public String orderNow(){
		return "";
	}
}
