package zx.ffts.web.action.yyq;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import zx.ffts.dao.yyq.Ts_restaurant_dao;
import zx.ffts.dao.yyq.findpage;
import zx.ffts.dao.yyq.pagelist;

public class YYQAction {
	HttpServletRequest req = ServletActionContext.getRequest();
	HttpServletResponse res = ServletActionContext.getResponse();
	HttpSession session = req.getSession();
	Ts_restaurant_dao dao = new Ts_restaurant_dao();
	findpage pg = new findpage();

	// 加载所有商店信息
	public String ShopList() {

		Integer nowpage = 1;
		String nw = req.getParameter("nowpage");
		if (nw != null && !"".equals(nw)) {
			nowpage = Integer.parseInt(nw);
		}
		pagelist lis = pg.findShop(nowpage);
		session.setAttribute("shop", lis);
		return "ShopList";
	}

	// 加载所有菜单信息
	public String MenuList() {
		Integer rtid = Integer.parseInt(req.getParameter("rtid"));
		System.out.println(rtid);
		List<Map<String, Object>> menu = dao.getMenuList(rtid);
		// 菜单类型
		List<Map<String, Object>> type = dao.getMenuType(rtid);
		Map<String, Object> shopinfo = dao.getshopInfo(rtid);

		session.setAttribute("MenuList", menu);
		session.setAttribute("MenuType", type);
		session.setAttribute("shopById", shopinfo);
		session.setAttribute("shopid", rtid);

		return "MenuList";
	}

	// 商店详细信息
	public String shangdian() {
		Integer rtid = Integer.parseInt(req.getParameter("rtid"));
		Map<String, Object> shopf = dao.getshopInfo(rtid);
		session.setAttribute("sd", shopf);
		return "sd";
	}

	// 菜单评价
	public String MenuMessage() {
		Integer nowpage = 1;
		String nw = req.getParameter("nowpage");
		if (nw != null && !"".equals(nw)) {
			nowpage = Integer.parseInt(nw);
		}
		Integer muid = Integer.parseInt(req.getParameter("muid"));
		pagelist lis = pg.findMenuMess(muid, nowpage);
		Map<String, Object> info = dao.GetMenuInfo(muid);
		session.setAttribute("menuInfo", info);
		session.setAttribute("menuMess", lis);
		return "MenuMessage";
	}

	// 店铺评价
	public String ShopMessage() {
		Integer rtid = Integer.parseInt(req.getParameter("rtid"));
		List<Map<String, Object>> shop = dao.ShopMessage(rtid);
		session.setAttribute("ShopMesById", shop);
		return "shopmessage";
	}

}
