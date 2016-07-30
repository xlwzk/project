package zx.ffts.xiong.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;



import net.sf.json.JSONObject;
import zx.ffts.dao.DataDao;

public class RestaurantDao extends DataDao{
	
	/**
	 * 查询商店信息
	 * @return
	 */
	public List<Map<String, Object>> getShopping (){
		String sql="select * from ts_restaurant";
		return getMapList(sql);
	}

}
