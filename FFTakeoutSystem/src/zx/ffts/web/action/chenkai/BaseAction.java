package zx.ffts.web.action.chenkai;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import zx.ffts.entity.chenkai.TsUser;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	protected HttpServletRequest req;
	protected HttpSession ses;
	protected HttpServletResponse res;
	protected TsUser tsuser;
	protected List<TsUser> userlist;

	public TsUser getTsuser() {
		return tsuser;
	}

	public void setTsuser(TsUser tsuser) {
		this.tsuser = tsuser;
	}

	

	public List<TsUser> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<TsUser> userlist) {
		this.userlist = userlist;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		this.req=req;
		this.ses=req.getSession();
	}

	@Override
	public void setServletResponse(HttpServletResponse res) {
		// TODO Auto-generated method stub
		this.res=res;
	}

}
