package zx.ffts.dao.chenkai;

import java.util.List;

import zx.ffts.dao.DataDao;
import zx.ffts.entity.chenkai.TsOrder;
import zx.ffts.entity.chenkai.TsRestaurant;

public class TsOrderDao extends DataDao {
	
	//查询所有订单
	public List<TsOrder>  getOrderList(Integer nowPage,Integer pageSize){
		TsOrder rest=new TsOrder();
		String sql="select * from (select t.*,rownum rn from(select oid,(select username from ts_user b where b.userid=a.ouserid ) as username,(select muname from ts_menu c where c.muid=a.omuid) as muname, (select rtname from ts_restaurant d where d.rtid=a.ortid) as rtname,ocount,(select username from ts_user b where b.userid=a.osender ) as sender,odate,ouuid,ostatus from ts_order a)t)where rn between ? and ?";	
		List<TsOrder> list=getEntities(sql,rest,(((nowPage-1)*pageSize)+1),(nowPage*pageSize));
		return list;
	}
	//通过id查询订单
	public TsOrder findOrderById(Integer id){
		TsOrder order=new TsOrder();
		String sql="select oid,(select username from ts_user b where b.userid=a.ouserid ) as username,(select muname from ts_menu c where c.muid=a.omuid) as muname, (select rtname from ts_restaurant d where d.rtid=a.ortid) as rtname,ocount,(select username from ts_user b where b.userid=a.osender ) as sender,odate,ouuid,ostatus from ts_order a where oid=?";
		TsOrder ts=getEntity(sql, order, id);
		return ts;
	}
	
	//删除订单
	public Integer deleteOrder(Integer id){
		String sql="delete from ts_order where oid=?";
		Integer i=update(sql, id);
		return i;
	}
	//修改订单
	public Integer updateOrder(Integer ouserid,Integer omuid,Integer ortid,Integer ocount,Integer osender,String odate,String ouuid,Integer ostatus){
		String sql="update ts_order set ouserid=?,omuid=?,ortid=?,ocount=?,osender=?,odate=?,ouuid=?,ostatus=? where oid=? ";
		Integer i=update(sql,ouserid,omuid,ortid,ocount,osender,odate,ouuid,ostatus);
		return i;
	}
	//查询所有订单数量
	public Integer orderCount(){
		String sql="select count(*) as cou from ts_order";
		Integer i=scalarNumber(sql);
		return i;
	}
}
