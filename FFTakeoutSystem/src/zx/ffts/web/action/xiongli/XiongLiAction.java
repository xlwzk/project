package zx.ffts.web.action.xiongli;

import java.util.List;
import java.util.Map;

import zx.ffts.dao.yyq.Ts_restaurant_dao;
import zx.ffts.web.action.yyq.YYQAction;

public class XiongLiAction extends BaseAction{
	/**
	 * 点击购买
	 * @return
	 */
	YYQAction yyq=new YYQAction();
	Ts_restaurant_dao rt=new Ts_restaurant_dao();
	public String addCar(){
		Integer rtid=Integer.parseInt(request.getParameter("shopid"));
		Integer menuid=Integer.parseInt(request.getParameter("menuid"));
		Integer userid=1;
		odao.addOrder(userid, menuid, rtid);
		List<Map<String,Object>> menu=rt.getMenuList(rtid);
		session.setAttribute("MenuList",menu);
		//session.setAttribute("shopid",rtid);
		return "addCar";
	}
	
   /**
    * 查看我的订单
    * @return
    */
	public String searchCar(){
		Integer userid=1;
		Integer shopid=Integer.parseInt(request.getParameter("sid"));
		List<Map<String, Object>> list=odao.getCar(userid, shopid);
		int numprice=0;
		for (Map<String, Object> map : list) {
			numprice+=Integer.parseInt(map.get("muprice").toString())*Integer.parseInt(map.get("ocount").toString());
		}
		request.setAttribute("list", list);
		request.setAttribute("sumMoney", numprice);
		request.setAttribute("sid", shopid);
		return "searchCar";
	}
	
	/**
	 * 点击加号
	 * @return
	 */
	public String addShop(){
		Integer shopid=Integer.parseInt(request.getParameter("sid"));
		Integer menuid=Integer.parseInt(request.getParameter("meuid"));
		Integer userid=1;
		odao.addOrder(userid, menuid, shopid);
		return searchCar();
	}
	/**
	 * 点击减号
	 * @return
	 */
	public String deleteShop(){
		Integer shopid=Integer.parseInt(request.getParameter("sid")); //商店id
		Integer menuid=Integer.parseInt(request.getParameter("meuid")); //菜的id
		Integer num=Integer.parseInt(request.getParameter("num"));
		Integer userid=1;		
			if (num<=1) {				
				odao.deleteShp(userid, menuid, shopid);	
				return searchCar();
			}else {
				odao.deleteOrder(userid, menuid, shopid);
				return searchCar();
			}	
	}
	
	/**
	 * 清除购物车
	 * @return
	 */
	public String deleteCar(){
		Integer shopid=Integer.parseInt(request.getParameter("sid"));
		Integer userid=1;
		odao.deleteCar(userid, shopid);
		session.removeAttribute("MenuList");
		Integer rtid=(Integer) session.getAttribute("shopid");
		List<Map<String,Object>> menu=rt.getMenuList(rtid);
		session.setAttribute("MenuList",menu);
		return "deleteCar";
	}
	
	/**
	 * 点击去结算显示详情信息
	 * @return
	 */
	public String getDetail(){
		Integer shopid=Integer.parseInt(request.getParameter("sid")); //商店id
		Integer userid=1;		
		Map<String, Object> mapdetail=odao.getDetail(userid, shopid);
		List<Map<String, Object>> list=odao.getCar(userid, shopid);
		int numprice=0;
		for (Map<String, Object> map : list) {
			numprice+=Integer.parseInt(map.get("muprice").toString())*Integer.parseInt(map.get("ocount").toString());
		}
		request.setAttribute("list", list);
		request.setAttribute("sumMoney", numprice);
		request.setAttribute("sid", shopid);
		request.setAttribute("mapdetail", mapdetail);
		return "getDetail";
	}
	
	/**
	 * 继续购买
	 * @return
	 */
	public String Back(){		
		Integer rtid=(Integer) session.getAttribute("shopid");
		List<Map<String,Object>> menu=rt.getMenuList(rtid);
		session.setAttribute("MenuList",menu);
		return "addCar";
	}
	
	/**
	 * 
	 * 
	 */
	public String deletes(){
		Integer shopid=Integer.parseInt(request.getParameter("sid")); //商店id
		Integer menuid=Integer.parseInt(request.getParameter("meuid")); //菜的id
		Integer userid=1;
		odao.deletes(userid, menuid, shopid);
		return Back();
	}

}
