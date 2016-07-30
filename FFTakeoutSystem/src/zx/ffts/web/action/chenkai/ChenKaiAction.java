package zx.ffts.web.action.chenkai;

import java.io.IOException;
import java.io.PrintWriter;

import net.sf.json.JSONObject;
import zx.ffts.dao.chenkai.TsUserDao;
import zx.ffts.entity.chenkai.TsUser;

public class ChenKaiAction extends BaseAction {
	
	private TsUserDao myuser=new TsUserDao();
	public String Login(){
		TsUser us=myuser.login(tsuser.getUsername());
		if (tsuser.getPwd().equals(us.getPwd())&&us.getAuthority()==4) {
			ses.setAttribute("user",us);
			return "success";
		}else if (tsuser.getPwd().equals(us.getPwd())&&us.getAuthority()==5) {
			ses.setAttribute("user",us);
			return "success";
		}else{
			req.setAttribute("error", "对不起，您的用户名，或密码不正确，或者权限不够");
			return "error";
		}	
	}
	
	public void getUserList() throws IOException{
		Integer page = Integer.parseInt(req.getParameter("page"));
		Integer rows = Integer.parseInt(req.getParameter("rows"));
		String sort = req.getParameter("sort");
		String order = req.getParameter("order");
		userlist=myuser.getUserList(page, rows,sort,order);		
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("total", myuser.userCount());
		json.put("rows", userlist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
}
