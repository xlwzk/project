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
import zx.ffts.dao.chenkai.TsGiftDao;
import zx.ffts.dao.chenkai.TsRestaurantDao;
import zx.ffts.dao.chenkai.TsUserDao;
import zx.ffts.entity.chenkai.TsGift;
import zx.ffts.entity.chenkai.TsRestaurant;
import zx.ffts.entity.chenkai.TsUser;

public class ChenKaiTsGiftAction extends BaseAction {
	
	private TsGiftDao mygift=new TsGiftDao();
	
	//获得所有礼品
	public void getGiftList() throws IOException{
		String pageStr = req.getParameter("page");
		String rowStr = req.getParameter("rows");
		
		Integer page=1;
		Integer row=2;
		if (pageStr!=null&&!"".equals(pageStr)) {
			page=Integer.parseInt(pageStr);
		}
		if (rowStr!=null&&!"".equals(rowStr)) {
			row=Integer.parseInt(rowStr);
		}	
		giftlist=mygift.getGiftList(page, row);		
		int num=0;
		if (mygift.giftCount()%row==0) {
			num=mygift.giftCount()/row;
		}else{
			num=mygift.giftCount()/row+1;
		}
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("pages", page);
		json.put("total", num);
		json.put("rows", giftlist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//获得所有礼品的集合
	public void getAllGift() throws IOException{
		giftlist=mygift.getAllGift();		
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("rows", giftlist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//添加礼品
	public String addGift() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String gname=req.getParameter("gname");
		String greqscore=req.getParameter("greqscore");
		String gsum=req.getParameter("gsum");
		String gdesc=req.getParameter("gdesc");

	
		if (photoFileName==null) {
			mygift.addGift(gname, null, Integer.parseInt(greqscore),Integer.parseInt(gsum), gdesc);
		}else{
			String path=ServletActionContext.getServletContext().getRealPath("image");
			File newFile=new File(path, photoFileName);
			FileUtils.copyFile(photo, newFile);	
			
			String src="image/"+photoFileName;
			mygift.addGift(gname, src, Integer.parseInt(greqscore),Integer.parseInt(gsum), gdesc);	
		}
		return "success";
	}
	
	//删除礼品
	public void deleGift(){
		Integer id=Integer.parseInt(req.getParameter("id"));	
		mygift.deleteGift(id);
	}

	//通过id查询某个礼品
	public void findGiftById() throws Exception{
		Integer id=Integer.parseInt(req.getParameter("id"));
		TsGift ts=mygift.findGiftById(id);
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("gift", ts);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//修改某个礼品的信息
	public String UpdaGift() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Integer gid=Integer.parseInt(req.getParameter("gid"));
		String gname=req.getParameter("gname");
		String greqscore=req.getParameter("greqscore");
		String gcount=req.getParameter("gcount");
		String gsum=req.getParameter("gsum");
		String gdesc=req.getParameter("gdesc");
		String pic=req.getParameter("pic");
	
		if (photoFileName==null) {
			mygift.updateGift(gname, pic, Integer.parseInt(greqscore), Integer.parseInt(gcount), Integer.parseInt(gsum), gdesc, gid);
		}else{	
			String path=ServletActionContext.getServletContext().getRealPath("image");
			File newFile=new File(path, photoFileName);
			FileUtils.copyFile(photo, newFile);	
			
			String src="image/"+photoFileName;
			mygift.updateGift(gname, src, Integer.parseInt(greqscore), Integer.parseInt(gcount), Integer.parseInt(gsum), gdesc, gid);	
		}
		return "success";
	}
	
}
