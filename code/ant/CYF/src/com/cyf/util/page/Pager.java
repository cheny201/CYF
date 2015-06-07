package com.cyf.util.page;

import java.util.Collection;
import java.util.Iterator;

/**
 * 分页信息
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:33:27
 */
public class Pager<T> {

	/** 默认每页显示数量 */
	public final static int DEFAULT_SIZE=10;
	
	private int pageSize;
	private int pageNo;
	
	private int firstEntityIndex;
	private int lastEntityIndex;

	private Collection<T> data;
	private int entityCount;
	private int pageCount;
	
	public Pager(){}
	
	/**
	 * 获取数据路径
	 */
	private String path;
	
	/**
	 * @param pageSize
	 *            每页记录数
	 * @param pageNo
	 *            页号
	 */
	public Pager(int pageSize, int pageNo) {
		if (pageNo > 1 && pageSize <= 0) {
			throw new IllegalArgumentException(
					"Illegal paging arguments. [pageSize=" + pageSize
							+ ", pageIndex=" + pageNo + "]");
		}

		if (pageSize < 0)
			pageSize = 0;
		if (pageNo < 1)
			pageNo = 1;

		this.pageSize = pageSize;
		this.pageNo = pageNo;
		firstEntityIndex = (pageNo - 1) * pageSize;
		lastEntityIndex = pageNo * pageSize;
	}
	
	/**
	 * @param pageSize
	 *            每页记录数
	 * @param pageNo
	 *            页号
	 */
	public Pager(String path, int pageSize, int pageNo) {
		if (pageNo > 1 && pageSize <= 0) {
			throw new IllegalArgumentException(
					"Illegal paging arguments. [pageSize=" + pageSize
							+ ", pageIndex=" + pageNo + "]");
		}

		this.path = path;
		
		if (pageSize < 0)
			pageSize = 0;
		if (pageNo < 1)
			pageNo = 1;

		this.pageSize = pageSize;
		this.pageNo = pageNo;
		firstEntityIndex = (pageNo - 1) * pageSize;
		lastEntityIndex = pageNo * pageSize;
	}
	
	/**
	 * 返回每一页的大小，即每页的记录数。
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 返回要提取的页的序号，该序号是从1开始计算的。
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 返回当前页中第一条记录对应的序号，该序号是从0开始计算的。<br>
	 * 注意，此处在计算firstEntityIndex是不考虑实际提取过程中当前页是否存在的。
	 */
	public int getFirstEntityIndex() {
		return firstEntityIndex;
	}

	/**
	 * 返回当前页中最后一条记录对应的序号，该序号是从0开始计算的。<br>
	 * 注意，此处在计算lastEntityIndex是不考虑实际提取过程中当前页是否存在或者记录数是否可达到pageSize的。
	 */
	public int getLastEntityIndex() {
		return lastEntityIndex;
	}

	/**
	 * 设置总记录数。
	 * <p>
	 * 此处的总记录数并不是指当页数据的总数，而是指整个结果的总数。 即每一页数据累计的总数。
	 * </p>
	 */
	public int getEntityCount() {
		return entityCount;
	}

	/**
	 * 返回总记录数。
	 * <p>
	 * 此处的总记录数并不是指当页数据的总数，而是指整个结果的总数。即每一页数据累计的总数。
	 * </p>
	 */
	public void setEntityCount(int entityCount) {
		if (entityCount < 0) {
			throw new IllegalArgumentException(
					"Illegal entityCount arguments. [entityCount="
							+ entityCount + "]");
		}

		this.entityCount = entityCount;
		pageCount = ((entityCount - 1) / pageSize) + 1;
	}

	/**
	 * 返回总的记录页数。
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * 返回请求路径
	 */
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 返回当页数据的迭代器。
	 */
	public Iterator<T> iterator() {
		if (data != null) {
			return data.iterator();
		} else {
			return null;
		}
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * 返回当页数据。
	 */
	public Collection<T> getData() {
		return data;
	}

	/**
	 * 设置当页数据。
	 */
	public void setData(Collection<T> data) {
		this.data = data;
	}
	
}
