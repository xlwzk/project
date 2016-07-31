package zx.ffts.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import zx.ffts.utils.ExtenseQueryRunner;


public class DataDao {
	
	private QueryRunner qr = new ExtenseQueryRunner();

	/**
	 * 返回JSONObject类型的结果，本质是对Map<String, Object>的json封装
	 * @param sql
	 * @param params
	 * @return
	 */
	protected JSONObject getJsonObject(String sql, Object... params) {
		try {
			Map<String, Object> map = qr.query(sql, new MapHandler(), params);
			return JSONObject.fromObject(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 返回Map<String, Object>，本质是一个对象
	 * @param sql
	 * @param params
	 * @return
	 */
	protected Map<String, Object> getObject(String sql, Object... params) {
		try {
			Map<String, Object> map = qr.query(sql, new MapHandler(), params);
			return map;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 返回JSONArray，本质是对List<Map<String, Object>>的json封装
	 * @param sql
	 * @param params
	 * @return
	 */
	protected JSONArray getJsonArray(String sql, Object... params) {
		try {
			List<Map<String, Object>> list = qr.query(sql,
					new MapListHandler(), params);
			return JSONArray.fromObject(list.toArray());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 返回一个List<Map<String, Object>>类型的结果集，本质是一组对象
	 * @param sql
	 * @param params
	 * @return
	 */
	protected List<Map<String, Object>> getMapList(String sql, Object... params) {
		try {
			List<Map<String, Object>> list = qr.query(sql,
					new MapListHandler(), params);
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 返回单行单列整数结果集
	 * @param sql
	 * @param params
	 * @return
	 */
	protected int scalarNumber(String sql, Object... params) {
		try {
			Number number = (Number) qr.query(sql, new ScalarHandler(), params);
			return number != null ? number.intValue() : -1;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 返回单行单列字符串类型的结果集
	 * @param sql
	 * @param params
	 * @return
	 */
	protected String scalarString(String sql, Object... params) {
		try {
			return (String) qr.query(sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 执行增加、删除、修改的sql语句，参数按照sql语句中问号的顺序依次书写
	 * 例如:update(sql,username,pwd,info....)，可以写N个参数
	 * 返回被修改的行数
	 * @param sql
	 * @param params
	 * @return
	 */
	protected int update(String sql, Object... params) {
		try {
			int number = qr.update(sql, params);
			return number;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取实体对象
	 * @param sql
	 * @param t
	 * @param params
	 * @return
	 */
	protected  <T> T getEntity(String sql,T t,Object... params){
		try {
			return qr.query(sql, new BeanHandler<T>((Class<T>) t.getClass()),params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取实体集合
	 * @param sql
	 * @param t
	 * @param params
	 * @return
	 */
	protected <T> List<T> getEntities(String sql,T t,Object... params){
		try {
			return qr.query(sql, new BeanListHandler<T>((Class<T>) t.getClass()),params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
