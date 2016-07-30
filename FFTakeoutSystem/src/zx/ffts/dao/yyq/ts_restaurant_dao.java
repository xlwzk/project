package zx.ffts.dao.yyq;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import zx.ffts.dao.DataDao;

public class ts_restaurant_dao extends DataDao{
//加载所有的商店信息
	public List<Map<String,Object>> getShopList(){
		String sql="select * from ts_restaurant";
		return getMapList(sql);
		
	}
	//根据商家id加载所以的菜单信息
	public List<Map<String,Object>> getMenuList(Integer rtid){
		String sql="select * from ts_menu where murtid=?  order by mutype";
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
	
}
