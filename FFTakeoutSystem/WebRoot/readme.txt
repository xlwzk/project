1.对DataDao的说明
		在使用Dao层时，封装了常用方法在DataDao中，需要时则在创建具体Dao时继承DataDao(必须)，当遇到date类型时，建议转换为字符串再读，否则不能使用
	DataDao已提供的方法，需要自己书写，下面给出简单示例
	
	//示例类
	public class Demo extends DataDao{
		
		//向数据库插入一条语句，使用继承的方法
		public int addGoods(String pname, String brand, String type, Double price,
				String desc, Integer sales, String picture) {
			//声明sql
			String sql = "insert into product values(product_seq.nextval,?,?,?,?,?,?,?)";
			//调用方法返回结果，参数顺序要和？顺序一致
			return update(sql, pname, brand, type, price, picture, desc, sales);
		}
		
		//从数据库读取数据，使用自定义方法(不常见)
		private JSONArray getJSONArrayTime(String sql, Object... params) {
			//声明三个变量
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				//从连接池获取连接
				conn = DbUtils.getConnection();
				pstmt = conn.prepareStatement(sql);
				int i = 1;
				for (Object obj : params) {
					pstmt.setObject(i++, obj);
				}
				rs = pstmt.executeQuery();
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				//读取数据(此处针对date类型做了处理)
				while (rs.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("newsid", rs.getString(1));
					map.put("title", rs.getString(2));
					map.put("content", rs.getString(3));
					map.put("writedate",
							rs.getDate(4).toString() + " " + rs.getTime(4));
					list.add(map);
				}
				return JSONArray.fromObject(list);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				//关闭连接，注意connection使用的是DbUtils的方法释放连接而不是永久关闭，切记
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					DbUtils.releaseConnection(conn);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
2.关于代码规范
	A、无论是创建一个类，还是创建一个js文件，都要在已有的规定的包中，新建一个以自己名字(拼音，缩写，英文名)命名的文件夹/包
		例如，陈凯要创建一个Dao类，那么需要在zx.ffts.dao.chenkai(此包需要自己创建)包下新建Dao类，其他文件同理(js/css/image)
	B、使用Struts2框架时，需要在Struts.xml中创建package，这里的package同样以自己的名字命名，便于区分(不影响实际使用)

3.