package zx.ffts.dao.chenkai;

import java.util.List;

import zx.ffts.dao.DataDao;
import zx.ffts.entity.chenkai.TsGiftRecord;
import zx.ffts.entity.chenkai.TsPay;

public class TsGiftRecordDao extends DataDao {
	
	//查询所有兑换记录
	public List<TsGiftRecord>  getTsGiftRecordList(Integer nowPage,Integer pageSize,String sort,String order){
		String sql="select * from (select t.*,rownum rn from(select * from ts_giftrecord)t)where rn between ? and ?";	
		if (sort!=null) {
			sql+=" order by "+sort+" "+order;
		}
		TsGiftRecord  pay=new TsGiftRecord();
		List<TsGiftRecord>  list=getEntities(sql,pay , (((nowPage-1)*pageSize)+1),(nowPage*pageSize));
		return list;
	}
	
	
	//通过id查询兑换记录
	public TsGiftRecord findGiftRecordById(Integer id){
		TsGiftRecord pay=new TsGiftRecord();
		String sql="select * from ts_giftrecord where pid=?";
		TsGiftRecord ts=getEntity(sql, pay, id);
		return ts;
	}
	//添加兑换记录
	public Integer addGiftRecord(Integer grid,Integer gruserid,Integer grnum,String grdate,Integer grstatus){
		String sql="insert into ts_giftrecord values(ts_giftrecord_seq.nextval,?,?,?,?,?)";
		Integer i=update(sql,grid,gruserid,grnum,grdate,grstatus);
		return i;
	}
	//删除兑换记录
	public Integer deleteGiftRecord(Integer id){
		String sql="delete from ts_giftrecord where grid=?";
		Integer i=update(sql, id);
		return i;
	}
	//修改兑换记录
	public Integer updateGiftRecord(Integer grgid,Integer gruserid,Integer grnum,String grdate,Integer grstatus,Integer grid){
		String sql="update ts_giftrecord set grgid=?,gruserid=?,grnum=?,grdate=?,grstatus=? where grid=? ";
		Integer i=update(sql,grgid,gruserid,grnum,grdate,grstatus,grid);
		return i;
	}
	//查询所有兑换记录数量
	public Integer GiftRecordCount(){
		String sql="select count(*) as cou from ts_giftrecord";
		Integer i=scalarNumber(sql);
		return i;
	}
}
