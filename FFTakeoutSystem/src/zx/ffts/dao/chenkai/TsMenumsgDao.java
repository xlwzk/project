package zx.ffts.dao.chenkai;

import java.util.List;

import zx.ffts.dao.DataDao;
import zx.ffts.entity.chenkai.TsMenuMsg;

public class TsMenumsgDao extends DataDao {
	
	//查询所有店铺评论
	public List<TsMenuMsg>  getMenuMsgList(Integer nowPage,Integer pageSize,String sort,String order){
		String sql="select * from (select t.*,rownum rn from(select a.*,b.username,c.muname from ts_menumsg a,ts_user b,ts_menu c where a.mmuserid=b.userid and a.mmmuid=c.muid)t)where rn between ? and ?";	
		if (sort!=null) {
			sql+=" order by "+sort+" "+order;
		}
		TsMenuMsg  pay=new TsMenuMsg();
		List<TsMenuMsg>  list=getEntities(sql,pay , (((nowPage-1)*pageSize)+1),(nowPage*pageSize));
		return list;
	}
	
	
	//通过id查询兑换记录
	public TsMenuMsg findMenuMsgById(Integer id){
		TsMenuMsg pay=new TsMenuMsg();
		String sql="select a.*,b.username,c.muname from ts_menumsg a,ts_user b,ts_menu c where a.mmuserid=b.userid and a.mmmuid=c.muid and a.mmid=?";
		TsMenuMsg ts=getEntity(sql, pay, id);
		return ts;
	}
	//添加兑换记录
	public Integer addMenuMsg(Integer mmuserid,Integer mmmuid,String mmcontent,Integer mmscore,String mmdate){
		String sql="insert into ts_menumsg values(ts_menumsg_seq.nextval,?,?,?,?,?)";
		Integer i=update(sql,mmuserid,mmmuid,mmcontent,mmscore,mmdate);
		return i;
	}
	//删除兑换记录
	public Integer deleteMenuMsg(Integer id){
		String sql="delete from ts_menumsg where mmid=?";
		Integer i=update(sql, id);
		return i;
	}
	//修改兑换记录
	public Integer updateMenuMsg(Integer mmuserid,Integer mmmuid,String mmcontent,String mmscore,String mmdate,Integer mmid){
		String sql="update ts_message set mmuserid=?,mmmuid=?,mmcontent=?,mmscore=?,mmdate=? where mmid=? ";
		Integer i=update(sql,mmuserid,mmmuid,mmcontent,mmscore,mmdate);
		return i;
	}
	//查询所有兑换记录数量
	public Integer GiftMenuMsg(){
		String sql="select count(*) as cou from ts_menumsg";
		Integer i=scalarNumber(sql);
		return i;
	}
}
