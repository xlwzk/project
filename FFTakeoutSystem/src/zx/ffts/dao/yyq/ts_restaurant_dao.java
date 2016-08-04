package zx.ffts.dao.yyq;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import zx.ffts.dao.DataDao;

public class Ts_restaurant_dao extends DataDao{
//加载所有的商店信息
	public List<Map<String,Object>> getShopList(){
		String sql="select * from ts_restaurant";
		return getMapList(sql);
		
	}
	//根据商家id加载所以的菜单信息
	public List<Map<String,Object>> getMenuList(Integer rtid){
		String sql="select * from ts_menu tm left outer join ts_order t on tm.muid=t.omuid where murtid=?";
		return getMapList(sql,rtid);	
	}
	
	//根据商家id加载所有的菜单类型
	public List<Map<String,Object>> getMenuType(Integer rtid){
		String sql="select mutype from ts_menu  where murtid=? group by mutype";
		return getMapList(sql,rtid);
	}
	
	@Test
	public void test(){
		getMenuType(1);
	}
	//根据id查询商店的详细信息
	public  Map<String, Object> getshopInfo(Integer rtid){
		String sql="select * from  ts_restaurant where rtid=?";
		return getObject(sql,rtid);
	}
	
	//店铺评价
	public List<Map<String,Object>> ShopMessage(Integer mrtid){
		String sql="select m.mid,m.mcontent,m.mdate,u.username as mm from ts_message m,ts_user u where m.mid=u.userid and  m.mrtid=?";
		return getMapList(sql,mrtid);
		
	}
	//根据菜的id查看所有评价
	public List<Map<String,Object>> MenuMessage(Integer muid){
		String sql="select u.username as mm,m.mmcontent,m.mmdate from ts_menumsg m,ts_user u,ts_menu n where u.userid=m.mmuserid and n.muid=m.mmmuid and n.muid=?";
		return getMapList(sql,muid);
		
	}
	//根据菜单id查看商店现象信息
	public  Map<String, Object> GetMenuInfo(Integer muid){
		String sql="SELECT * FROM TS_MENU WHERE MUID=?";
		return getObject(sql,muid);
	}

	
}
