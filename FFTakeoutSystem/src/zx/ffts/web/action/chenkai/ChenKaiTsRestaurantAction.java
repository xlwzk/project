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
import zx.ffts.dao.chenkai.TsRestaurantDao;
import zx.ffts.dao.chenkai.TsUserDao;
import zx.ffts.entity.chenkai.TsRestaurant;
import zx.ffts.entity.chenkai.TsUser;

public class ChenKaiTsRestaurantAction extends BaseAction {
	
	private TsRestaurantDao myrest=new TsRestaurantDao();
	
	//获得所有商家的集合（分页）
	public void getRestList() throws IOException{
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
		restlist=myrest.getRestList(page, row);		
		int num=0;
		if (myrest.restCount()%row==0) {
			num=myrest.restCount()/row;
		}else{
			num=myrest.restCount()/row+1;
		}
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("pages", page);
		json.put("total", num);
		json.put("rows", restlist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//获得所有商家的集合
	public void getAllRest() throws IOException{
		restlist=myrest.getAllRest();		
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("rows", restlist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	
	//添加店家
	public String addRest() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String rtname=req.getParameter("rtname");
		String rtaddr=req.getParameter("rtaddr");
		String rtowner=req.getParameter("rtowner");
		//String rtpic=req.getParameter("rtpic");
		String rtcontent=req.getParameter("rtcontent");
		//String rtdate=req.getParameter("rtdate");
		String rtonbuz=req.getParameter("rtonbuz");
		String rtstatus=req.getParameter("rtstatus");
	
		if (photoFileName==null) {
			myrest.addRest(rtname, rtaddr, Integer.parseInt(rtowner), null, rtcontent, rtonbuz, rtstatus);	
		}else{
			String path=ServletActionContext.getServletContext().getRealPath("image");
			File newFile=new File(path, photoFileName);
			FileUtils.copyFile(photo, newFile);	
			
			String src="image/"+photoFileName;
			myrest.addRest(rtname, rtaddr, Integer.parseInt(rtowner), src, rtcontent, rtonbuz, rtstatus);	
		}
		return "success";
	}
	
	//删除店家
	public void deleRest(){
		Integer id=Integer.parseInt(req.getParameter("id"));	
		myrest.deleteRest(id);
	}

	//通过id查询某个店家
	public void FindRestbyId() throws Exception{
		Integer id=Integer.parseInt(req.getParameter("id"));
		TsRestaurant ts=myrest.findRestById(id);
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("rest", ts);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//修改某个店家的信息
	public String UpdaRest() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Integer rtid=Integer.parseInt(req.getParameter("rtid"));
		String rtname=req.getParameter("rtname");
		String rtaddr=req.getParameter("rtaddr");
		String rtowner=req.getParameter("rtowner");
		String rtcontent=req.getParameter("rtcontent");
		String rtdate=req.getParameter("rtdate");
		String rtonbuz=req.getParameter("rtonbuz");
		String rtstatus=req.getParameter("rtstatus");
		String pic=req.getParameter("pic");
	
		if (photoFileName==null) {
			myrest.updateRest(rtname, rtaddr, rtowner, pic, rtcontent, rtdate, rtonbuz, Integer.parseInt(rtstatus), rtid);
		}else{	
			String path=ServletActionContext.getServletContext().getRealPath("image");
			File newFile=new File(path, photoFileName);
			FileUtils.copyFile(photo, newFile);	
			
			String src="image/"+photoFileName;
			myrest.updateRest(rtname, rtaddr, rtowner, src, rtcontent, rtdate, rtonbuz, Integer.parseInt(rtstatus), rtid);	
		}
		return "success";
	}
	
}
