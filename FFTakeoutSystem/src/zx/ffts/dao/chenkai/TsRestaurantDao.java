package zx.ffts.dao.chenkai;

import java.util.List;
import java.util.Map;

import zx.ffts.dao.DataDao;
import zx.ffts.entity.chenkai.TsRestaurant;

public class TsRestaurantDao extends DataDao {
	
	//查询所有店家
	public List<TsRestaurant>  getRestList(Integer nowPage,Integer pageSize){
		TsRestaurant rest=new TsRestaurant();
		String sql="select * from (select t.*,rownum rn from(select a.*,b.username from ts_restaurant a,ts_user b where a.rtowner =b.userid )t)where rn between ? and ?";	
		List<TsRestaurant> list=getEntities(sql,rest,(((nowPage-1)*pageSize)+1),(nowPage*pageSize));
		return list;
	}
	//通过id查询店家
	public TsRestaurant findRestById(Integer id){
		TsRestaurant rest=new TsRestaurant();
		String sql="select a.*,b.username from ts_restaurant a,ts_user b where a.rtowner =b.userid and rtid=?";
		TsRestaurant ts=getEntity(sql, rest, id);
		return ts;
	}
	//添加店家
	public Integer addRest(String rtname,String rtaddr,Integer rtowner,String rtpic,String rtcontent,String rtdate,String rtonbuz,String rtstatus){
		String sql="insert into ts_restaurant values(ts_restaurant_seq.nextval,?,?,?,?,?,?,?,?)";
		Integer i=update(sql,rtname,rtaddr,rtowner,rtpic,rtcontent,rtdate,rtonbuz,rtstatus);
		return i;
	}
	//删除店家
	public Integer deleteRest(Integer id){
		String sql="delete from ts_restaurant where rtid=?";
		Integer i=update(sql, id);
		return i;
	}
	//修改店家
	public Integer updateRest(String rtname,String rtaddr,String rtowner,String rtpic,String rtcontent,String rtdate,String rtonbuz,Integer rtstatus,Integer rtid){
		String sql="update ts_restaurant set rtname=?,rtaddr=?,rtowner=?,rtpic=?,rtcontent=?,rtdate=?,rtonbuz=?,rtstatus=? where rtid=? ";
		Integer i=update(sql, rtname,rtaddr,rtowner,rtpic,rtcontent,rtdate,rtonbuz,rtstatus,rtid);
		return i;
	}
	//查询所有店家数量
	public Integer restCount(){
		String sql="select count(*) as cou from ts_restaurant";
		Integer i=scalarNumber(sql);
		return i;
	}
}
