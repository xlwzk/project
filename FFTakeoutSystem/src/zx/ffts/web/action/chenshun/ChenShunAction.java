package zx.ffts.web.action.chenshun;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import zx.ffs.dao.chenshun.ChenShunDao;
import zx.java.entity.chenshun.ts_menu;

import net.sf.json.JSONObject;

public class ChenShunAction extends ChenShunBase {
	JSONObject json = new JSONObject();
	ChenShunDao cs = new ChenShunDao();

	/**
	 * 加载自己的信息
	 * 
	 * @return
	 */
	public String LoadUser() {

		return "LoadUser";
	}

	/*************************************************** 对菜的操作 *********************************/

	/**
	 * 加载所有菜单
	 * 
	 * @return
	 * @throws IOException
	 */
	public void LoadMenu() throws IOException {
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		if (price == null || price.equals("")) {
			price = "0";
		}
		String type = request.getParameter("type");
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		List<Map<String, Object>> list = cs.MenuList(1, page, rows, type,
				Integer.parseInt(price), name, sort, order);
		json.put("total", cs.MenuNum(1, null, null, null));

		json.put("rows", list);
		out.write(json.toString());
		session.setAttribute("cai", list);
		out.flush();
		out.close();

	}

	/**
	 * 加载所有菜的品类(下拉列表用)
	 * 
	 * @throws IOException
	 */
	public void LoadType() throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write(cs.MuType().toString());
		out.flush();
		out.close();

	}

	/**
	 * 删除一道菜
	 * 
	 * @return
	 * @throws IOException
	 */
	public void DeleteMenu() throws IOException {
		int fiag = -1;
		String ids = request.getParameter("ids");
		if (ids != null) {
			String[] id = ids.split(",");
			for (String i : id) {
				System.out.println(">>>>>>>>>>>进入删除方法" + i);
				fiag = cs.DeleteMenu(Integer.parseInt(i));
			}
			PrintWriter out = response.getWriter();
			json.put("deletefiag", fiag);
			System.out.println(fiag);
			out.write(json.toString());
			out.flush();
			out.close();
		}

	}

	/**
	 * 添加一道菜
	 * 
	 * @throws IOException
	 */
	public void AddMenu() throws IOException {
		System.out.println(">>>>>>>>>>>>>");
    
		String muname = request.getParameter("muname");
		String muprice = request.getParameter("muprice");
        String status=request.getParameter("mustatus");  //菜状态
		String mutype = request.getParameter("mutype");
		String mudesc = request.getParameter("mudesc");
      
		      
		String filelength="";  //文件长度
     
      String path = ServletActionContext.getServletContext().getRealPath(
	    "upload");
      System.out.println(path);
   	 System.out.println(up.getAbcFileName());
		File newFile = new File(path, up.getAbcFileName());
		FileUtils.copyFile(up.getAbc(), newFile);
	
	
		String mupic = up.getAbcFileName();
		ts_menu ts = new ts_menu();
		ts.setMurtid(1); // 手动添加数据，纯为测试
		ts.setMuname(muname);
		ts.setMuprice(Integer.parseInt(muprice));
		ts.setMupic(mupic);
		ts.setMutype(mutype);
		ts.setMudesc(mudesc);
		PrintWriter out = response.getWriter();
		json.put("addfiag", cs.AddMenu(ts));
		out.write(json.toString());
		out.flush();
		out.close();

	}

	/**
	 * 修改一道菜
	 * 
	 * @throws IOException
	 */
	public void UpdateMenu() throws IOException {
		String muid = request.getParameter("muid");
		String muname = request.getParameter("muname");
		String muprice = request.getParameter("muprice");
	
		String mutype = request.getParameter("mutype");
		String mudesc = request.getParameter("mudesc");
		String mustatus = request.getParameter("mustatus");
		
		
		String mupic = request.getParameter("MUPIC"); //图片暂未做
		
		ts_menu ts = new ts_menu();
		ts.setMuid(Integer.parseInt(muid));
		ts.setMuname(muname);
		ts.setMuprice(Integer.parseInt(muprice));
		ts.setMupic(mupic);
		ts.setMutype(mutype);
		ts.setMudesc(mudesc);
		ts.setMustatus(Integer.parseInt(mustatus));

		PrintWriter out = response.getWriter();
		json.put("updatefiag", cs.UpdateMenu(ts));
		out.write(json.toString());
		out.flush();
		out.close();

	}

	/********************************************** 对订单的操作 ********************************************/

	/**
	 * 加载自己的所有订单
	 * 
	 * @return
	 * @throws IOException
	 */
	Integer status = -1;

	public void LoadOrder() throws IOException {

		if (request.getParameter("status") == null) {
			status = -1;
		} else {
			status = Integer.parseInt(request.getParameter("status"));
		}

		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		List<Map<String, String>> list = cs.OrderList(rows, 1, page, status,
				sort, order); // 所有数据
		session.setAttribute("list", list);
		int num = cs.OrderNum(1, status); // 数量
		json.put("total", num); // 店主的id此处是自己设的
		json.put("rows", list);// 店主的id此处是自己设的

		out.write(json.toString());
		out.flush();
		out.close();

	}
	/**
	 * 未处理的订单信息
	 * @throws IOException 
	 */
	public void LoadOrderNo() throws IOException{
	   
		PrintWriter out = response.getWriter();
		List<Map<String, String>> list = cs.OrderListNo(1);  //商店的id
		int num = cs.OrderNumNo(1);   
		json.put("total", num); // 店主的id此处是自己设的
		json.put("rows", list);// 店主的id此处是自己设的

		out.write(json.toString());
		out.flush();
		out.close();
		
	}
	
	
	/**
	 * 已处理的订单信息
	 * @throws IOException 
	 */
	public void LoadOrderYes() throws IOException{
		PrintWriter out = response.getWriter();
		List<Map<String, String>> list = cs.OrderListYes(1);  //商店的id
		int num = cs.OrderNumNo(1);   
		json.put("total", num); // 店主的id此处是自己设的
		json.put("rows", list);// 店主的id此处是自己设的
		out.write(json.toString());
		out.flush();
		out.close();
		
		
		
	}
	
	/**
	 * 单机接单时
	 * @throws IOException 
	 */
	public void JieDanOrder() throws IOException{
		System.out.println("进入接单方法");
		String id=request.getParameter("id");   //获取订单的主键
		System.out.println(id);
		int fiag=cs.JieDanOrder(Integer.parseInt(id));
		System.out.println(fiag);
		PrintWriter out = response.getWriter();
		json.put("jiedan", fiag);// 店主的id此处是自己设的
		out.write(json.toString());
		out.flush();
		out.close();
		
	}
	
	
	
	
	

	/************************************************** 上传 ***************************************/
	public String up_load() throws IOException {
       
		return "";
	}

	/******************************** 导出数据(电子表格 ) ********************************/
	/**
	 * 订单的
	 */
	public String ExportTable() throws RowsExceededException, WriteException,
			IOException {
		// 设置文件名
		String fname = "订单数据表.xls";
		String fileName = URLEncoder.encode(fname, "utf-8");
		// 弹出下载的面板---用于下载xls文件
		response.setContentType("application/vnd.ms-excle");
		response.setHeader("Content-disposition", "attachment;fileName="
				+ fileName);
		// 产生输出流，用于将服务端的信息，以电子文档的方式，输出到客户端
		OutputStream out = response.getOutputStream();

		// 产生电子文档
		WritableWorkbook wb = Workbook.createWorkbook(out);
		// 产生表单
		WritableSheet st = wb.createSheet("本店订单", 0);

		/************************* 设置显示样式 ****************************************/
		st.getSettings().setDefaultColumnWidth(20); // 设置列宽
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 18,
				WritableFont.BOLD);// 创建可以用输出的字体格式(字体类型，字体大小，字体样式)

		// 创建一种显示样式，用于设置单元格，以什么样式来显示数据
		WritableCellFormat wcf = new WritableCellFormat(wf);// 设置单元格里面的内容，以什么字体来显示
		wcf.setAlignment(Alignment.CENTRE);// 设置显示方式
		wcf.setWrap(true);// 当内容显示不下的时候，自动换行
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);// 设置边框
		/*****************************************************************/

		/************************ 增加标题行 *****************************************/
		Label labTitle = new Label(0, 0, "订单数据", wcf);
		st.addCell(labTitle);
		st.mergeCells(0, 0, 7, 0);// 合并单元格

		/*****************************************************************/

		// 从session中，取得list
		List<Map<String, Object>> list = cs.GetListOrder(1);     //加载所有的订单，值是店主的id值

		// 创建标签，用于显示数据
		Label labId = new Label(0, 1, "编号", wcf);
		Label labName = new Label(1, 1, "用户姓名", wcf);
		Label labAge = new Label(2, 1, "菜单名称", wcf);
		Label labCity = new Label(3, 1, "菜单价格", wcf);
		Label labdate = new Label(4, 1, "订单时间", wcf);
		Label labtype = new Label(5, 1, "订单状态", wcf);
		Label labnum = new Label(6, 1, "菜单数量", wcf);
		Label labmoney = new Label(7, 1, "金额", wcf);
		// 把标签添加到表单中
		st.addCell(labId);
		st.addCell(labName);
		st.addCell(labAge);
		st.addCell(labCity);
		st.addCell(labdate);
		st.addCell(labtype);
		st.addCell(labnum);
		st.addCell(labmoney);

		for (int i = 0; i < list.size(); i++) {// 对list循环
			Map<String, Object> m = list.get(i);
			Label id = new Label(0, i + 2, m.get("OID").toString(), wcf);
			Label name = new Label(1, i + 2, m.get("USERNAME").toString(), wcf);
			Label age = new Label(2, i + 2, m.get("MUNAME").toString(), wcf);
			Label city = new Label(3, i + 2, m.get("MUPRICE").toString(), wcf);
			Label date = new Label(4, i + 2, m.get("ODATE").toString(), wcf);
			
			String statustype="";
			if(m.get("OSTATUS").toString().equals("0")){
				statustype="购物车中";
			}else if(m.get("OSTATUS").toString().equals("1")){
				statustype="已下单(未支付)";
			}else if(m.get("OSTATUS").toString().equals("2")){
				statustype="已付款";
				
			}else if(m.get("OSTATUS").toString().equals("3")){
				statustype="已接单";
			}else if(m.get("OSTATUS").toString().equals("4")){
				statustype="配送中";
			}else{
				statustype="交易成功";
			}
			  
			Label status = new Label(5, i + 2, statustype, wcf);
			Label count = new Label(6, i + 2, m.get("OCOUNT").toString(), wcf);
			Label sum = new Label(7, i + 2, m.get("SUM").toString(), wcf);
			st.addCell(id);
			st.addCell(name);
			st.addCell(age);
			st.addCell(city);
			st.addCell(date);
			st.addCell(status);
			st.addCell(count);
			st.addCell(sum);

		}
		wb.write();
		wb.close();
		out.close();
		return "";
	}

	/**
	 * 菜单的
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ExportMenu() throws Exception {
		// 设置文件名
		String fname = "菜单数据表.xls";
		String fileName = URLEncoder.encode(fname, "utf-8");
		// 弹出下载的面板---用于下载xls文件
		response.setContentType("application/vnd.ms-excle");
		response.setHeader("Content-disposition", "attachment;fileName="
				+ fileName);
		// 产生输出流，用于将服务端的信息，以电子文档的方式，输出到客户端
		OutputStream out = response.getOutputStream();

		// 产生电子文档
		WritableWorkbook wb = Workbook.createWorkbook(out);
		// 产生表单
		WritableSheet st = wb.createSheet("本店菜单", 0);

		/************************* 设置显示样式 ****************************************/
		st.getSettings().setDefaultColumnWidth(20); // 设置列宽
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 18,
				WritableFont.BOLD);// 创建可以用输出的字体格式(字体类型，字体大小，字体样式)

		// 创建一种显示样式，用于设置单元格，以什么样式来显示数据
		WritableCellFormat wcf = new WritableCellFormat(wf);// 设置单元格里面的内容，以什么字体来显示
		wcf.setAlignment(Alignment.CENTRE);// 设置显示方式
		wcf.setWrap(true);// 当内容显示不下的时候，自动换行
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);// 设置边框
		/*****************************************************************/

		/************************ 增加标题行 *****************************************/
		Label labTitle = new Label(0, 0, "菜单数据", wcf);
		st.addCell(labTitle);
		st.mergeCells(0, 0, 7, 0);// 合并单元格

		/*****************************************************************/

		// 从session中，取得list
		List<Map<String, Object>> list = cs.GetMenuList(1);   //返回某家店的所有菜      id是店主的id

		// 创建标签，用于显示数据
		Label labId = new Label(0, 1, "菜单编号", wcf);
		Label labName = new Label(1, 1, "菜单名称", wcf);
		Label labAge = new Label(2, 1, "菜单价格", wcf);
		Label labCity = new Label(3, 1, "菜单类型", wcf);
		Label labdate = new Label(4, 1, "菜单描述", wcf);
		Label labtype = new Label(5, 1, "菜单销量", wcf);
		Label labnum = new Label(6, 1, "菜单状态", wcf);

		// 把标签添加到表单中
		st.addCell(labId);
		st.addCell(labName);
		st.addCell(labAge);
		st.addCell(labCity);
		st.addCell(labdate);
		st.addCell(labtype);
		st.addCell(labnum);

		for (int i = 0; i < list.size(); i++) {// 对list循环
			Map<String, Object> m = list.get(i);
			Label id = new Label(0, i + 2, m.get("MUID").toString(), wcf);
			Label name = new Label(1, i + 2, m.get("MUNAME").toString(), wcf);
			Label age = new Label(2, i + 2, m.get("MUPRICE").toString(), wcf);
			Label city = new Label(3, i + 2, m.get("MUTYPE").toString(), wcf);
			Label date = new Label(4, i + 2, m.get("MUDESC").toString(), wcf);
			Label status = new Label(5, i + 2, m.get("MUSALE").toString(), wcf);
			String statustype="";
			if( m.get("MUSTATUS").toString().equals("0")){
				statustype="销售中";
			}else if( m.get("MUSTATUS").toString().equals("1")){
				statustype="已下架";
			}
			Label count = new Label(6, i + 2,statustype, wcf);

			st.addCell(id);
			st.addCell(name);
			st.addCell(age);
			st.addCell(city);
			st.addCell(date);
			st.addCell(status);
			st.addCell(count);

		}
		wb.write();
		wb.close();
		out.close();
		return "daochu";
	}

	/********************************** 查看数据视图 *********************************/
	/**
	 * 菜单的
	 */
	public void showBar() throws Exception {

		response.setContentType("text/html;charset=utf-8");

		Map<String, Object> map = cs.getList(1); // 此处是用户的id
		JSONObject json = new JSONObject();

		json.put("columnsName", map.keySet());// 把所有的键的名称,放到set集合，返回
		json.put("vals", map.values());// 把所有的值放到Collection集合中，返回

		PrintWriter out = response.getWriter();
		out.write(json.toString());// 输出json
		out.flush();
		out.close();

	}

}
