package zx.ffts.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;


import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DbUtils {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	/**
	 * 事务专用连接
	 */
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	/**
	 * 获取连接对象
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection connection = tl.get();
		if (connection != null)
			return connection;
		return dataSource.getConnection();
	}

	/**
	 * 获取连接池对象
	 * 
	 * @return
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 开启事务 1.获取一个connection，设置它的setAutoCommit(false) 2.还要保证DAO中使用的连接是刚刚创建的
	 * 
	 * @throws SQLException
	 */
	public static void beginTransaction() throws SQLException {
		Connection connection = tl.get();
		if (connection != null)
			throw new SQLException("已经开启了事务，不要重复开启");
		// 创建了连接
		connection = getConnection();
		connection.setAutoCommit(false);
		tl.set(connection);
	}

	/**
	 * 提交事务
	 * 
	 * @throws SQLException
	 */
	public static void commitTransaction() throws SQLException {
		Connection connection = tl.get();
		if (connection == null)
			throw new SQLException("还没有开启事务，不能提交");
		connection.commit();
		connection.close();
		tl.remove();
	}

	/**
	 * 回滚事务
	 * 
	 * @throws SQLException
	 */
	public static void rollbackTransaction() throws SQLException {
		Connection connection = tl.get();
		if (connection == null)
			throw new SQLException("还没有开启事务，不能回滚");
		connection.rollback();
		connection.close();
		tl.remove();
	}

	/**
	 * 释放连接
	 * @param connection
	 * @throws SQLException
	 */
	public static void releaseConnection(Connection connection)
			throws SQLException {
		Connection tlconnection = tl.get();
		if (tlconnection == null)
			connection.close();
		if (connection != tlconnection)
			connection.close();
	}
}
