package zx.ffs.dao.chenshun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;


import zx.ffts.dao.DataDao;
import zx.java.entity.chenshun.ts_menu;
import zx.java.entity.chenshun.ts_order;


public class ChenShunDao extends DataDao {
	/**
	 * 返回某一家商店所有的菜单信息(分页显示)
	 * 
	 * @param id
	 * 
	 * 
	 * @return
	 */
	public List<Map<String,Object>> MenuList(Integer id,Integer pageindex,Integer pagesize,String type,Integer price,String name,String sort,String order) {
		String sql = "select * from ( select h.*,rownum r from ts_menu h where rownum<=?  and murtid=?";
		if(type!=null && !type.equals("")){
			  sql+=" and mutype='"+type+"'";
		}
		if(price!=null && price!=0){
			sql+=" and muprice>="+price;
		}
		if(name!=null && !name.equals("")){
			sql+="  and muname like '%"+name+"%'";
		}
		sql+="  ) k where k.r>?";
		 if(sort!=null&&!sort.equals("")){
         	 sql+="   order by  "+sort+"  "+order; 	 
          }	return getMapList(sql,pagesize*pageindex,id,(pageindex-1)*pagesize);

	}
	/**
	 * 当前菜单总数
	 * @return
	 */
	public int MenuNum(Integer id,String type,Integer price,String name){
		String sql="select count(*) from ts_menu where murtid=?";
		if(type!=null && !type.equals("")){
			  sql+=" and mutype="+type;
		}
		if(price!=null && price!=0){
			sql+=" and mupic>="+price;
		}
		if(name!=null && !name.equals("")){
			sql+="  and name like '%"+name+"%'";
		}
	
		return scalarNumber(sql,id);
		
	}
	/**
	 * 返回某家店的所有菜
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> GetMenuList(Integer id){
		String sql="select * from ts_menu where murtid=?";
		return getMapList(sql, id);	
	}

	
/**
 * 为某个商店添加一个菜
 * @param t
 * @return
 */
	public int AddMenu(ts_menu t) {
		String sql = "insert into  ts_menu values(ts_menu_seq.nextval,?,?,?,?,?,'正宗"+t.getMutype()+"',0,0)";
		return update(sql, t.getMurtid(), t.getMuname(), t.getMuprice(), t
				.getMupic(), t.getMutype());
    
	}
	/**
	 * 为某个商店删除某道菜
	 * @param muid
	 * @param murtid
	 * @return
	 */
	public int DeleteMenu(Integer muid){
		String sql="delete from ts_menu where muid=?";
		return update(sql, muid);
		
	}
	/**
	 * 为某个商店修改某道菜
	 * @param t
	 * @return
	 */
	public int UpdateMenu(ts_menu t){
		String sql = "update ts_menu set muname=?,muprice=?,mupic=?,mutype=?,mudesc=?,mustatus=? where muid=?";
		return update(sql,t.getMuname(), t.getMuprice(), t
				.getMupic(), t.getMutype(), t.getMudesc(),t.getMustatus(),t.getMuid());

	}
	
	
	/**
	 * 返回所有菜的品类
	 * @return
	 */
	public JSONArray MuType(){
		String sql="select mutype from ts_menu group by mutype ";
		return getJsonArray(sql);
	}
	
	
	/************************************************************对订单的操作************************************/
	/**
	 * 返回某家店的所有订单
	 * 
	 * @param id
	 * @return
	 */
	public List<Map<String, String>> OrderList(Integer pagesize,Integer id,Integer pageindex,Integer status,String sort,String order) {
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		String sql = "select  k.oid,u.username,m.muname,m.muprice,k.odate,k.ostatus,k.ocount,(m.muprice*k.ocount) as sum from ( select t.*,rownum r from ts_order t     where rownum<=?  and ortid=? ";
    
		if(status!=null && status!=-1)
           {
        	   sql+="   and ostatus="+status;    	   
           }
           sql+=") k    inner join ts_menu m  on k.omuid=m.muid  inner join ts_user u  on u.userid=k.ouserid  where k.r>?";
           if(sort!=null&&!sort.equals("")){
           	 sql+=" order by   "+sort+"  "+order; 	 
            }  
           for (Map<String, Object> map : getMapList(sql,pagesize*pageindex,id,(pageindex-1)*pagesize)) {
			    Map<String,String> map2=new HashMap<String, String>();
			    map2.put("OID", map.get("OID").toString());
			    map2.put("USERNAME", map.get("USERNAME").toString());
			    map2.put("MUNAME", map.get("MUNAME").toString());
			    map2.put("MUPRICE", map.get("MUPRICE").toString());
			    map2.put("ODATE", map.get("ODATE").toString());
			    map2.put("OSTATUS", map.get("OSTATUS").toString());
			    map2.put("OCOUNT", map.get("OCOUNT").toString());
			    map2.put("SUM", map.get("SUM").toString());
			    list.add(map2);
			   
		}
        
           return list;
	}
	/**
	 * 加载某个店所有的订单啊
	 * @param id
	 * @return
	 */
	  public List<Map<String,Object>> GetListOrder(Integer id){
		  String sql="select  k.oid,u.username,m.muname,m.muprice,k.odate,k.ostatus,k.ocount,(m.muprice*k.ocount) as sum from ( select t.*,rownum r from ts_order t     where ortid=? ) k    inner join ts_menu m  on k.omuid=m.muid  inner join ts_user u  on u.userid=k.ouserid";
		  return getMapList(sql, id);
		  
	  }
	
	/**
	 * 修改订单
	 * @param t
	 * @return
	 */
	public int UpdateOrder(ts_order t){
		String sql="update ts_order set ocount=?,ostatus=? where ortid=? and ouserid? and omuid=?";
		return update(sql, t.getOcount(),t.getOstatus(),t.getOrtid(),t.getOuserid(),t.getOmuid());
		
}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int DeleteOrder(Integer id){
		String sql="delete from ts_order where ortid=?";
		return update(sql, id);
		
	}
	
	/**
	 * 当前订单总数
	 * @return
	 */
	public int OrderNum(Integer id,Integer status){
		String sql="select count(*) from ts_order where ortid=?";
		 if(status!=null && status!=-1){
      	   sql+="   and ostatus="+status;    	   		
         }	
		return scalarNumber(sql,id);
		
	}
	/**
	 * 柱状图形数据(菜单表)
	 * @return
	 * @throws Exception
	 */
	 public Map<String,Object> getList(Integer id) throws Exception{
		 Map<String,Object> map=new HashMap<String, Object>();
		 String sql="select * from ts_menu where murtid=?";
		 List<Map<String,Object>> list=getMapList(sql, id);
		 for (Map<String, Object> map2 : list) {
			  map.put(map2.get("MUNAME").toString(), map2.get("MUPRICE").toString());

		}
		 return map;
		 
	 }
	 /**
	  * 返回某家店所有还没有处理的订单
	  * @param id
	  * @return
	  */
	 public List<Map<String,String>> OrderListNo(Integer id){  //店的id
		 List<Map<String,String>>  list=new ArrayList<Map<String,String>>();
		 String sql="select  k.oid,u.username,m.muname,m.muprice,k.odate,k.ostatus,k.ocount,(m.muprice*k.ocount) as sum,ouuid from ( select t.*, rownum r from ts_order t     where ortid=? and ostatus =2 )k    inner join ts_menu m  on k.omuid=m.muid  inner join ts_user u  on u.userid=k.ouserid";
         for (Map<String, Object> map : getMapList(sql, id)) {
			   Map<String,String>  map2=new HashMap<String, String>();
			    map2.put("OID", map.get("OID").toString());
			    map2.put("USERNAME", map.get("USERNAME").toString());
			    map2.put("MUNAME", map.get("MUNAME").toString());
			    map2.put("MUPRICE", map.get("MUPRICE").toString());
			    map2.put("ODATE", map.get("ODATE").toString());
			    map2.put("OSTATUS", map.get("OSTATUS").toString());
			    map2.put("OCOUNT", map.get("OCOUNT").toString());
			    map2.put("SUM", map.get("SUM").toString());
			    map2.put("OUUID", map.get("OUUID").toString());
			    list.add(map2);
		}
		 
		 return list;
	 }
	 
	 /**
	  * 没处理的总数
	  * @param id
	  * @return
	  */
		public int OrderNumNo(Integer id){
			String sql="select count(*) from ts_order where ortid=? and ostatus =2";
			return scalarNumber(sql,id);
			
		}
    /**
     * 返回某家店所有已经处理过的订单
     * @param id
     * @return
     */
	 public List<Map<String,String>> OrderListYes(Integer id){
		 List<Map<String,String>>  list=new ArrayList<Map<String,String>>();
		 String sql ="select  k.oid,u.username,m.muname,m.muprice,k.odate,k.ostatus,k.ocount,(m.muprice*k.ocount) as sum,ouuid from ( select t.*, rownum r from ts_order t     where ortid=1 and ostatus in(3,4,5) )k    inner join ts_menu m  on k.omuid=m.muid  inner join ts_user u  on u.userid=k.ouserid";
		 for (Map<String, Object> map : getMapList(sql, id)) {
			   Map<String,String>  map2=new HashMap<String, String>();
			    map2.put("OID", map.get("OID").toString());
			    map2.put("USERNAME", map.get("USERNAME").toString());
			    map2.put("MUNAME", map.get("MUNAME").toString());
			    map2.put("MUPRICE", map.get("MUPRICE").toString());
			    map2.put("ODATE", map.get("ODATE").toString());
			    map2.put("OSTATUS", map.get("OSTATUS").toString());
			    map2.put("OCOUNT", map.get("OCOUNT").toString());
			    map2.put("SUM", map.get("SUM").toString());
			    map2.put("OUUID", map.get("OUUID").toString());
			    list.add(map2);
		}
		 
		 return list;
		 
	 }
	 /**
	  * 已处理订单的总数
	  * @param id
	  * @return
	  */
		public int OrderNumYes(Integer id){
			String sql="select count(*) from ts_order where ortid=? and ostatus in(3,4,5)";
			return scalarNumber(sql,id);
			
		}
		
		
		
		/**
		 * 单机接单的时候
		 * @param id
		 * @return
		 */
	public int JieDanOrder(Integer id){  //订单的主键来修改
		String sql="update ts_order set ostatus=3 where oid=?";
		return update(sql, id);
		
		
	}
	 
	
	
	
	
	

}
