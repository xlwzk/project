package zx.ffts.dao.chenkai;

import java.util.List;

import org.junit.Test;

import zx.ffts.dao.DataDao;
import zx.ffts.entity.chenkai.TsUser;

public class TsUserDao extends DataDao {
	
	
	//登录
	public TsUser login(String name){
		TsUser user=new TsUser();
		String sql="select * from ts_user where username=?";
		TsUser ts=getEntity(sql, user, name);
		return ts;
	}
	//查询所有用户
	public List<TsUser>  getUserList(Integer nowPage,Integer pageSize,String sort,String order){
		String sql="select * from (select t.*,rownum rn from(select * from ts_user)t)where rn between ? and ?";	
		if (sort!=null) {
			sql+=" order by "+sort+" "+order;
		}
		TsUser  user=new TsUser();
		List<TsUser>  list=getEntities(sql,user , (((nowPage-1)*pageSize)+1),(nowPage*pageSize));
		return list;
	}
	
	
	//通过id查询用户
	public TsUser findUserById(Integer id){
		TsUser user=new TsUser();
		String sql="select * from ts_user where userid=?";
		TsUser ts=getEntity(sql, user, id);
		return ts;
	}
	//添加用户
	public Integer addUser(String username,String pwd,String tel,String email,String address,String realname,Double balance,String gender,String phonto){
		String sql="insert into ts_user values(ts_user_seq.nextval,?,?,?,?,?,?,?,0,?,default,1,?)";
		Integer i=update(sql, username,pwd,tel,email,address,realname,balance,gender,phonto);
		return i;
	}
	//删除用户
	public Integer deleteUser(Integer id){
		String sql="delete from ts_user where userid=?";
		Integer i=update(sql, id);
		return i;
	}
	//修改用户
	public Integer updateUser(String username,String pwd,String email,String address,String realname,double balance,Integer score,String gender,String photo,Integer userid){
		String sql="update ts_user set username=?,pwd=?,tel=?,email=?,address=?,realname=?,balance=?,score=?,gender=?,authority=?,photo=? where userid=? ";
		Integer i=update(sql, username,pwd,email,address,realname,balance,score,gender,photo,userid);
		return i;
	}
	//查询所有用户数量
	public Integer userCount(){
		String sql="select count(*) as cou from ts_user";
		Integer i=scalarNumber(sql, null);
		return i;
	}
}
