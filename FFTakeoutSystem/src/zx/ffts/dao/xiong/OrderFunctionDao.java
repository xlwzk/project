package zx.ffts.dao.xiong;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import net.sf.json.JSONArray;

import zx.ffts.dao.DataAccessObject;

public class OrderFunctionDao extends DataAccessObject {
	
	@Test
	public void test(){
		System.out.println(getCart(5, 2));
	}

	public Integer orderCount(Integer userid, Integer rtid, Integer muid) {
		String sql = "select ocount from ts_order where ouserid=? and ortid=? and omuid=? and ostatus=0";
		return executeScalarInteger(sql, userid, rtid, muid);
	}

	public List<Map<String, Object>> getUnorderedCount(Integer userid,
			Integer rtid) {
		String sql = "select ocount,omuid from ts_order where ouserid=? and ortid=? and ostatus=0";
		return getMapList(sql, true, userid, rtid);
	}

	public JSONArray getCart(Integer userid, Integer rtid) {
		String sql = "select muname,muprice,ocount from ts_order o,ts_menu m where o.omuid=m.muid and ouserid=? and ortid=? and ostatus=0 and ocount>0";
		return getJsonArray(sql, true, userid, rtid);
	}
	
	public Integer orderNow(Integer userid,Integer rtid){
		String sql = "";
		return 1;
	}
}
