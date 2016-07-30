package zx.ffts.xiong.dao;

import java.util.List;
import java.util.Map;
import zx.ffts.dao.DataDao;

public class OrderDao extends DataDao {

	/**
	 * 点击加号是将菜加入到购物车中
	 * 
	 * @param userid
	 *            当前用户
	 * @param menuid
	 *            那个菜
	 * @param dianid
	 *            那个店
	 * @return
	 */
	public int addOrder(Integer userid, Integer menuid, Integer dianid) {
		String sql = "select count(*) from ts_order where ouserid=? and omuid=? and ortid=? and ostatus=0";
		int num = scalarNumber(sql, userid, menuid, dianid);
		if (num != 0) {
			String updatesql = "update ts_order set ocount=ocount+1 where omuid=? and ortid=?";
			return update(updatesql, menuid, dianid);
		} else {
			String addsql = "insert into ts_order values(ts_order_seq.nextval,?,?,?,1,2,sysdate,null,0)";
			return update(addsql, userid, menuid, dianid);
		}
	}

	/**
	 * 点击减号将菜移除订单中
	 * 
	 * @param userid
	 * @param menuid
	 * @param dianid
	 * @return
	 */
	public int deleteOrder(Integer userid, Integer menuid, Integer dianid) {
		String sql = "select count(*) from ts_order where ouserid=? and omuid=? and ortid=? and ostatus=0";
		int num = scalarNumber(sql, userid, menuid, dianid);
		if (num != 0) {
			String updatesql = "update ts_order set ocount=ocount-1 where omuid=? and ortid=?";
			return update(updatesql, menuid, dianid);
		} else {
			String addsql = "delete from  ts_order where ouserid=? and omuid=? and ortid=? and ostatus=0";
			return update(addsql, userid, menuid, dianid);
		}
	}

	/**
	 * 点了去结算时展现所有的订单信息
	 * 
	 * @param userid
	 * @param restuantid
	 * @return
	 */
	public List<Map<String, Object>> getDetail(Integer userid,
			Integer restuantid) {
		String sql = "select oid,ocount,muname,muprice,rtname,username,gender,tel,address from ts_order o  "
				+ "inner join ts_menu m  on o.omuid=m.muid "
				+ " inner join ts_restaurant r on o.ortid=r.rtid "
				+ "inner join ts_user u on o.ouserid=u.userid "
				+ "  where o.oserid=? and r.rtid=? and ostatus=0";
		return getMapList(sql, userid, restuantid);
	}

	// public static void main(String[] args) {
	// RestaurantDao rd=new RestaurantDao();
	// System.out.println(rd.getShopping().size());
	// for (Map<String,Object> item : rd.getShopping()) {
	// System.out.println(item.get("RTNAME").toString());
	// }
	//    	
	// }
}
