package zx.ffts.web.action.chenkai;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

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
	
	public void inio() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String path=ServletActionContext.getServletContext().getRealPath("image");
		File newFile=new File(path, abcFileName);
		System.out.println(abc);
		FileUtils.copyFile(abc, newFile);	
	}
	
	
	public String addUser() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String username=req.getParameter("username");
		String pwd=req.getParameter("pwd");
		String photo=req.getParameter("photo");
		String tel=req.getParameter("tel");
		String email=req.getParameter("email");
		String address=req.getParameter("address");
		String realname=req.getParameter("realname");
		String balance=req.getParameter("balance");
		String score=req.getParameter("score");
		String gender=req.getParameter("gender");
		String regdate=req.getParameter("regdate");
		String authority=req.getParameter("authority");
		
		System.out.println(photo);
		String image=photo.substring(photo.lastIndexOf("\\")+1);
		
		System.out.println("username \t pwd \t photo \t tel \t email \t address \t realname \t balance \t score \t gender \t regdate \t authority");
		System.out.println(username +"\t"+ pwd +"\t"+ image +"\t"+ tel+"\t"+ email+" \t"+ address +"\t"+ realname+"\t"+ balance +"\t "+score +"\t"+ gender +"\t"+ regdate+"\t"+authority);
		return "123";
	}
	
	public String deleUser(){
		Integer id=Integer.parseInt(req.getParameter("id"));
		
		Integer i=myuser.deleteUser(id);
		
		System.out.println("id:"+id+"   i"+i);
		if (i>0) {
			return "desuccess";
		}else{
			return "deerror";
		}
	}
}
