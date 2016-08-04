package zx.ffts.dao.chenkai;

import java.util.List;

import zx.ffts.dao.DataDao;
import zx.ffts.entity.chenkai.TsMenu;

public class TsMenuDao extends DataDao {
	
	//查询所有菜单
	public List<TsMenu>  getMenuList(Integer nowPage,Integer pageSize,String sort,String order){
		String sql="select * from (select t.*,rownum rn from(select a.*,b.rtname from ts_menu a,ts_restaurant b where a.murtid=b.rtid )t)where rn between ? and ?";	
		if (sort!=null) {
			sql+=" order by "+sort+" "+order;
		}
		TsMenu  menu=new TsMenu();
		List<TsMenu>  list=getEntities(sql,menu , (((nowPage-1)*pageSize)+1),(nowPage*pageSize));
		return list;
	}
	
	
	//通过id查询兑换记录
	public TsMenu findMenuById(Integer id){
		TsMenu pay=new TsMenu();
		String sql="select a.*,b.rtname from ts_menu a,ts_restaurant b where a.murtid=b.rtid and a.muid=?";
		TsMenu ts=getEntity(sql, pay, id);
		return ts;
	}
	//添加兑换记录
	public Integer addMenu(Integer murtid,String muname,Integer muprice,String mupic,String mutype,String mudesc,Integer musale,Integer mustatus){
		String sql="insert into ts_menu values(ts_menu_seq.nextval,?,?,?,?,?,?,?,?)";
		Integer i=update(sql,murtid,muname,muprice,mupic,mutype,mudesc,musale,mustatus);
		return i;
	}
	//删除兑换记录
	public Integer deleteMenu(Integer id){
		String sql="delete from ts_menu where muid=?";
		Integer i=update(sql, id);
		return i;
	}
	//修改兑换记录
	public Integer updateMenu(Integer murtid,String muname,Integer muprice,String mupic,String mutype,String mudesc,Integer musale,Integer mustatus,Integer muid){
		String sql="update ts_menu set murtid=?,muname=?,muprice=?,mupic=?,mutype=?,mudesc=?,musale=?,mustatus=? where muid=? ";
		Integer i=update(sql,murtid,muname,muprice,mupic,mutype,mudesc,musale,mustatus,muid);
		return i;
	}
	//查询所有兑换记录数量
	public Integer GiftMenu(){
		String sql="select count(*) as cou from ts_menu";
		Integer i=scalarNumber(sql);
		return i;
	}
}
