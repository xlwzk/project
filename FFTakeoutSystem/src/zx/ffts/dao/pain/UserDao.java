package zx.ffts.dao.pain;

import org.junit.Test;

import zx.ffts.dao.DataAccessObject;
import zx.ffts.domain.User;

public class UserDao extends DataAccessObject {

	@Test
	public void test() {
		System.out.println(login("root", "1111"));
	}

	public User login(String username, String pwd) {
		String sql = "select * from ts_user where username=? and pwd=?";
		return getEntity(sql, new User(), username, pwd);
	}

	public Integer uniqueUsername(String username) {
		String sql = "select count(*) from ts_user where username=?";
		return executeScalarInteger(sql, username);
	}
	
	public Integer getOrderNumber(Integer userid){
		String sql = "select count(*) from (select ouuid from ts_order where ouserid=? group by ouuid)";
		return executeScalarInteger(sql, userid);
	}
}
