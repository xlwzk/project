package zx.ffts.web.action.chenshun;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import zx.java.entity.chenshun.ts_menu;
import zx.java.entity.chenshun.ts_order;
import zx.java.entity.chenshun.up_load;

public class ChenShunBase  implements ServletRequestAware,ServletResponseAware{
	
       protected ts_menu tm;  //菜单
       protected ts_order to;  //订单
       protected HttpServletRequest request;
       protected HttpServletResponse response;
       protected HttpSession session;
       protected up_load up;    //上传
	
    
	public ts_menu getTm() {
		return tm;
	}
	public void setTm(ts_menu tm) {
		this.tm = tm;
	}
	public ts_order getTo() {
		return to;
	}
	public void setTo(ts_order to) {
		this.to = to;
	}
	public up_load getUp() {
		return up;
	}
	public void setUp(up_load up) {
		this.up = up;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	    this.session=request.getSession();
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
	    this.response=response;	
	}
       
	
}
