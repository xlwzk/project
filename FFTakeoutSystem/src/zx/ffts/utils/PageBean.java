package zx.ffts.utils;


import java.util.List;

/**
 * 分页javabean
 * @author Pain
 *
 * @param <T>
 */
public class PageBean<T> {
	// 1.由servlet保存的部分
	// 当前页码
	private int currentPage;
	// 页面大小(一页几行)
	private int pageSize;
	// 页面链接(不含currentPage的部分)
	private String url;
	
	// 2.由Dao保存的部分
	// 总记录数
	private int totalCounts;
	// 总记录bean
	private List<T> beanList;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCounts() {
		return totalCounts;
	}

	public void setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
	}

	public int getTotalPages() {
		return (int) Math.ceil(totalCounts * 1.0 / pageSize);
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", pageSize="
				+ pageSize + ", totalCounts=" + totalCounts + ", beanList="
				+ beanList + ", url=" + url + "]";
	}

	public PageBean() {
		super();
	}

	public PageBean(int currentPage, int pageSize, int totalCounts,
			List<T> beanList, String url) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCounts = totalCounts;
		this.beanList = beanList;
		this.url = url;
	}

	public PageBean(int currentPage, int pageSize) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
}
