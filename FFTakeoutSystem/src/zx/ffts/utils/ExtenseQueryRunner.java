package zx.ffts.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 * 扩展后的QueryRunner类
 * @author Pain
 *
 */
public class ExtenseQueryRunner extends QueryRunner {

	/**
	 * 批处理sql语句
	 */
	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		/*
		 * 1. 得到连接 2. 执行父类方法，传递连接对象 3. 释放连接 4. 返回值
		 */
		Connection con = DbUtils.getConnection();
		int[] result = super.batch(con, sql, params);
		DbUtils.releaseConnection(con);
		return result;
	}

	/**
	 * 过时的方法
	 */
	@Override
	public <T> T query(String sql, Object param, ResultSetHandler<T> rsh)
			throws SQLException {
		Connection con = DbUtils.getConnection();
		T result = super.query(con, sql, param, rsh);
		DbUtils.releaseConnection(con);
		return result;
	}

	/**
	 * 过时的方法
	 */
	@Override
	public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh)
			throws SQLException {
		Connection con = DbUtils.getConnection();
		T result = super.query(con, sql, params, rsh);
		DbUtils.releaseConnection(con);
		return result;
	}

	/**
	 * 处理查询(含参)
	 */
	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		Connection con = DbUtils.getConnection();
		T result = super.query(con, sql, rsh, params);
		DbUtils.releaseConnection(con);
		return result;
	}

	/**
	 * 处理查询(无参)
	 */
	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection con = DbUtils.getConnection();
		T result = super.query(con, sql, rsh);
		DbUtils.releaseConnection(con);
		return result;
	}

	/**
	 * 处理DML(无参)
	 */
	@Override
	public int update(String sql) throws SQLException {
		Connection con = DbUtils.getConnection();
		int result = super.update(con, sql);
		DbUtils.releaseConnection(con);
		return result;
	}

	/**
	 * 处理DML(带1个参)
	 */
	@Override
	public int update(String sql, Object param) throws SQLException {
		Connection con = DbUtils.getConnection();
		int result = super.update(con, sql, param);
		DbUtils.releaseConnection(con);
		return result;
	}

	/**
	 * 处理DML(带多个参)
	 */
	@Override
	public int update(String sql, Object... params) throws SQLException {
		Connection con = DbUtils.getConnection();
		int result = super.update(con, sql, params);
		DbUtils.releaseConnection(con);
		return result;
	}
}
