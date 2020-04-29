package com.jokerchen.pinellia.common.model;

import java.util.List;

import lombok.Data;

/**
 * 用于分页查询的封装对象
 * @author Joker Chen
 */
@Data
public class Page {

	/**
	 * 每页显示的行数
	 */
	private Integer pageSize;
	
	/**
	 * 当前在第几页
	 */
	private Integer pageNumber;
	
	/**
	 * 总共的数量
	 */
	private Integer totalElements;
	
	/**
	 * 总页数
	 */
	private Integer totalPages;
	
	/**
	 * 最终要显示的数据
	 */
	private List<?> content;
	
	public Page(){}

	public Page(int pageNumber,int pageSize){
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
}
