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
import zx.ffts.dao.chenkai.TsMenuDao;
import zx.ffts.dao.chenkai.TsMessageDao;
import zx.ffts.dao.chenkai.TsRestaurantDao;
import zx.ffts.dao.chenkai.TsUserDao;
import zx.ffts.entity.chenkai.TsMenu;
import zx.ffts.entity.chenkai.TsMessage;
import zx.ffts.entity.chenkai.TsRestaurant;
import zx.ffts.entity.chenkai.TsUser;

public class ChenKaiTsMenuAction extends BaseAction {
	
	private TsMenuDao mymenu=new TsMenuDao();
	
	//获得所有菜单的集合
	public void getMenuList() throws IOException{
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
		menulist=mymenu.getMenuList(page, row);		
		int num=0;
		if (mymenu.menuCount()%row==0) {
			num=mymenu.menuCount()/row;
		}else{
			num=mymenu.menuCount()/row+1;
		}
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("pages", page);
		json.put("total", num);
		json.put("rows", menulist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	

	//获得所有菜单的集合
	public void getAllMenu() throws IOException{
		menulist=mymenu.getAllMenu();		
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("rows", menulist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//删除菜单
	public void deleMenu(){
		Integer id=Integer.parseInt(req.getParameter("id"));	
		mymenu.deleteMenu(id);
	}

	//通过id查询某个菜单
	public void FindMenubyId() throws Exception{
		Integer id=Integer.parseInt(req.getParameter("id"));
		TsMenu ts=mymenu.findMenuById(id);
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("menu", ts);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//修改某个菜单
	public String UpdaMenu() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Integer muid=Integer.parseInt(req.getParameter("muid"));
		String murtid=req.getParameter("murtid");
		String muname=req.getParameter("muname");
		String muprice=req.getParameter("muprice");
		String pic=req.getParameter("pic");
		String mutype=req.getParameter("mutype");
		String mudesc=req.getParameter("mudesc");
		String musale=req.getParameter("musale");
		String mustatus=req.getParameter("mustatus");

		
		System.out.println(muid+"\t"+murtid+"\t"+muname+"\t"+muprice+"\t"+pic+"\t"+mutype+"\t"+mudesc+"\t"+musale+"\t"+mustatus);
		if (photoFileName==null) {
			mymenu.updateMenu(Integer.parseInt(murtid), muname, Integer.parseInt(muprice), pic, mutype, mudesc, Integer.parseInt(musale), Integer.parseInt(mustatus), muid);
		}else{	
			String path=ServletActionContext.getServletContext().getRealPath("image");
			File newFile=new File(path, photoFileName);
			FileUtils.copyFile(photo, newFile);	
			
			String src="image/"+photoFileName;
			mymenu.updateMenu(Integer.parseInt(murtid), muname,  Integer.parseInt(muprice), src, mutype, mudesc, Integer.parseInt(musale), Integer.parseInt(mustatus), muid);
		}
		return "success";
	}
}
