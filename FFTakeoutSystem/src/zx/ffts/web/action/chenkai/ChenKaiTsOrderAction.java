package zx.ffts.web.action.chenkai;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import zx.ffts.dao.chenkai.TsOrderDao;
import zx.ffts.entity.chenkai.TsOrder;

import net.sf.json.JSONObject;


public class ChenKaiTsOrderAction extends BaseAction {
	
	private TsOrderDao myorder=new TsOrderDao();
	
	//获得所有订单的集合
	public void getOrderList() throws IOException{
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
		olist=myorder.getOrderList(page, row);	
		int num=0;
		if (myorder.orderCount()%row==0) {
			num=myorder.orderCount()/row;
		}else{
			num=myorder.orderCount()/row+1;
		}
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("pages", page);
		json.put("total", num);
		json.put("rows", olist);
		
		out.write(json.toString());
		out.flush();
		out.close();
	}
	

	//获得所有订单的集合
	public void getAllOrder() throws IOException{
		orderlist=myorder.getAllOrder();		
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("rows", orderlist);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//删除订单
	public void deleOrder(){
		Integer id=Integer.parseInt(req.getParameter("id"));	
		myorder.deleteOrder(id);
	}

	//通过id查询某个订单
	public void FindOrderbyId() throws Exception{
		Integer id=Integer.parseInt(req.getParameter("id"));
		TsOrder ts=myorder.findOrderById(id);
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("order", ts);
		out.write(json.toString());
		out.flush();
		out.close();
	}
	
	//修改某个订单
	public String UpdaOrder() throws Exception{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Integer oid=Integer.parseInt(req.getParameter("oid"));
		String ouserid=req.getParameter("ouserid");
		String omuid=req.getParameter("omuid");
		String ortid=req.getParameter("ortid");
		String ocount=req.getParameter("ocount");
		String osender=req.getParameter("osender");
		String odate=req.getParameter("odate");
		String ouuid=req.getParameter("ouuid");
		String ostatus=req.getParameter("ostatus");

		myorder.updateOrder(Integer.parseInt(ouserid), Integer.parseInt(omuid), Integer.parseInt(ortid), Integer.parseInt(ocount),  Integer.parseInt(osender), odate, ouuid, Integer.parseInt(ostatus),oid);
		
		return "success";
	}
}
