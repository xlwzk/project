package zx.ffts.web.action.yyq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import zx.ffts.dao.xiong.OrderFunctionDao;
import zx.ffts.dao.yyq.findpage;
import zx.ffts.dao.yyq.pagelist;
import zx.ffts.dao.yyq.ts_restaurant_dao;
import zx.ffts.domain.User;

public class YYQAction {
	HttpServletRequest req = ServletActionContext.getRequest();
	HttpServletResponse res = ServletActionContext.getResponse();
	HttpSession session = req.getSession();
	ts_restaurant_dao dao = new ts_restaurant_dao();
	private OrderFunctionDao functionDao = new OrderFunctionDao();
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
		List<Map<String, Object>> menu = dao.getMenuList(rtid);
		User user = (User) session.getAttribute("user");
		Integer userid = 0;
		if (user != null) {
			userid = user.getUserid();
		}
		List<Map<String, Object>> counts = functionDao.getUnorderedCount(
				userid, rtid);
		// 菜单类型
		List<Map<String, Object>> type = dao.getMenuType(rtid);
		Map<String, Object> shopinfo = dao.getshopInfo(rtid);
		List<Map<String, Object>> newMenu = new ArrayList<Map<String, Object>>();
		for (Map<String,Object> m1 : menu) {
			for (Map<String,Object> m2 : counts) {
				if(m2.get("omuid").toString().equals(m1.get("muid").toString())){
					m1.put("ocount", m2.get("ocount"));
				}
			}
			newMenu.add(m1);
		}
		session.setAttribute("MenuList", newMenu);
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
