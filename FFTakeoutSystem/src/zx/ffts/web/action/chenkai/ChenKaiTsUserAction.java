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

public class ChenKaiTsUserAction extends BaseAction {
	
	private TsUserDao myuser=new TsUserDao();
	
	//登陆
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
	
	//分页获得所有用户的集合（分页）
	public void getUserList() throws IOException{
		String pageStr = req.getParameter("page");
		String rowStr = req.getParameter("rows");
		
		Integer page=1;
		Integer row=5;
		if (pageStr!=null&&!"".equals(pageStr)) {
			page=Integer.parseInt(pageStr);
		}
		if (rowStr!=null&&!"".equals(rowStr)) {
			row=Integer.parseInt(rowStr);
		}	
		userlist=myuser.getUserList(page, row);		
		int num=0;
		if (myuser.userCount()%row==0) {
			num=myuser.userCount()/row;
		}else{
			num=myuser.userCount()/row+1;
		}
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("pages", page);
		json.put("total", num);
		json.put("rows", userlist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//获得所有用户的集合
	public void getAllUser() throws IOException{
		userlist=myuser.getAllUser();		
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("rows", userlist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//添加新用户
	public String addUser() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String username=req.getParameter("username");
		String pwd=req.getParameter("pwd");
		String tel=req.getParameter("tel");
		String email=req.getParameter("email");
		String address=req.getParameter("address");
		String realname=req.getParameter("realname");
		String balance=req.getParameter("balance");
		String gender=req.getParameter("gender");
		String authority=req.getParameter("authority");
	
		if (photoFileName==null) {
			myuser.addUser(username, pwd, tel, email, address, realname, Integer.parseInt(balance), gender, Integer.parseInt(authority), null);	
		}else{
			String path=ServletActionContext.getServletContext().getRealPath("image");
			File newFile=new File(path, photoFileName);
			FileUtils.copyFile(photo, newFile);	
			
			String src="image/"+photoFileName;
			myuser.addUser(username, pwd, tel, email, address, realname, Integer.parseInt(balance), gender, Integer.parseInt(authority), src);	
		}
		return "success";
	}
	
	//删除用户
	public void deleUser(){
		Integer id=Integer.parseInt(req.getParameter("id"));	
		myuser.deleteUser(id);	
	}

	//通过id查询用户
	public void FindbyId() throws Exception{
		Integer id=Integer.parseInt(req.getParameter("id"));
		TsUser ts=myuser.findUserById(id);
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("user", ts);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//修改某个用户的信息
	public String UpdaUser() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String userid=req.getParameter("userid");
		String username=req.getParameter("username");
		String pwd=req.getParameter("pwd");
		String tel=req.getParameter("tel");
		String email=req.getParameter("email");
		String address=req.getParameter("address");
		String realname=req.getParameter("realname");
		String balance=req.getParameter("balance");
		String score=req.getParameter("score");
		String regdate=req.getParameter("regdate");
		String gender=req.getParameter("gender");
		String authority=req.getParameter("authority");
		String pic=req.getParameter("pic");
	
		if (photoFileName==null) {
			myuser.updateUser(username, pwd, tel, email, address, realname,Double.parseDouble(balance), Integer.parseInt(score), gender, regdate, Integer.parseInt(authority), pic, Integer.parseInt(userid));
		}else{	
			String path=ServletActionContext.getServletContext().getRealPath("image");
			File newFile=new File(path, photoFileName);
			FileUtils.copyFile(photo, newFile);	
			
			String src="image/"+photoFileName;
			myuser.updateUser(username, pwd, tel, email, address, realname,Double.parseDouble(balance), Integer.parseInt(score), gender, regdate, Integer.parseInt(authority), src, Integer.parseInt(userid));	
		}
		return "success";
	}
	
}
