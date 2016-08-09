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
import zx.ffts.dao.chenkai.TsGiftRecordDao;
import zx.ffts.dao.chenkai.TsMenumsgDao;
import zx.ffts.dao.chenkai.TsMessageDao;
import zx.ffts.dao.chenkai.TsRestaurantDao;
import zx.ffts.dao.chenkai.TsUserDao;
import zx.ffts.entity.chenkai.TsGiftRecord;
import zx.ffts.entity.chenkai.TsMenuMsg;
import zx.ffts.entity.chenkai.TsMessage;
import zx.ffts.entity.chenkai.TsRestaurant;
import zx.ffts.entity.chenkai.TsUser;

public class ChenKaiTsGiftRecordAction extends BaseAction {
	
	private TsGiftRecordDao mygrec=new TsGiftRecordDao();
	
	//获得所有礼品兑换记录的集合
	public void getGiftRecordList() throws IOException{
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
		greclist=mygrec.getTsGiftRecordList(page, row);
		int num=0;
		if (mygrec.GiftRecordCount()%row==0) {
			num=mygrec.GiftRecordCount()/row;
		}else{
			num=mygrec.GiftRecordCount()/row+1;
		}
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("pages", page);
		json.put("total", num);
		json.put("rows", greclist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	
	//删除礼品兑换记录
	public void deleGiftRecord(){
		Integer id=Integer.parseInt(req.getParameter("id"));	
		mygrec.deleteGiftRecord(id);
	}

	//通过id查询某个礼品兑换记录
	public void FindGiftRecordbyId() throws Exception{
		
		Integer id=Integer.parseInt(req.getParameter("id"));
		TsGiftRecord ts=mygrec.findGiftRecordById(id);
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("grec", ts);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//修改某个礼品兑换记录
	public String UpdaGiftRecord() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Integer grid=Integer.parseInt(req.getParameter("grid"));
		String grgid=req.getParameter("grgid");
		String gruserid=req.getParameter("gruserid");
		String grnum=req.getParameter("grnum");
		String grdate=req.getParameter("grdate");
		String grstatus=req.getParameter("grstatus");
	
		System.out.println(grid+"\t"+grgid+"\t"+gruserid+"\t"+grnum+"\t"+grdate+"\t"+grstatus);
		mygrec.updateGiftRecord(Integer.parseInt(grgid), Integer.parseInt(gruserid), Integer.parseInt(grnum), grdate, Integer.parseInt(grstatus), grid);
		return "success";
	}
	
}
