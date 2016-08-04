package zx.ffts.web.action.yyq;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import zx.ffts.dao.yyq.ts_restaurant_dao;

public class YYQAction {
	HttpServletRequest req=ServletActionContext.getRequest();
	HttpServletResponse res=ServletActionContext.getResponse();
	HttpSession session=req.getSession();
	ts_restaurant_dao dao=new ts_restaurant_dao();
	//加载商店信息
	public String ShopList(){
		List<Map<String,Object>> shop=dao.getShopList();
		session.setAttribute("shop",shop);
		return "ShopList";
	}
	
	//加载菜单信息
	public String MenuList(){
		Integer rtid=Integer.parseInt(req.getParameter("rtid"));
		System.out.println(rtid);
		List<Map<String,Object>> menu=dao.getMenuList(rtid);
		//菜单类型
		List<Map<String,Object>> type=dao.getMenuType(rtid);
		Map<String,Object> shopinfo=dao.getshopInfo(rtid);
	  
		session.setAttribute("MenuList",menu);
		session.setAttribute("MenuType",type);
		session.setAttribute("shopById",shopinfo);
		session.setAttribute("shopid",rtid);
		
		return "MenuList";
	}

	
}
