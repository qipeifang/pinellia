package com.jokerchen.pinellia.common.model;

import java.util.ArrayList;
import java.util.List;

/**   
 * @description: 排序字段
 * @author Joker Chen 
 * @date 2019-04-08 15:10:22  
 */
public class Sort {
	
	private List<Order> orders;
	
	public Sort() {orders = new ArrayList<>();}
	
	public Sort(String field) {
		this(field,true);
	}
	
	public Sort(String field, boolean asc ) {
		this();
		addSort(field,asc);
	}
	
	public static Sort createSort(String field) {
		Sort sort = new Sort(field);
		return sort;
	}
	
	public static Sort createSort(String field,boolean asc) {
		Sort sort = new Sort(field,asc);
		return sort;
	}
	
	/**
	 * 添加排序字段
	 * @param field	要排序的字段
	 * @param asc	是否用升序
	 */
	public void addSort(String field, boolean asc) {
		orders.add(new Order(field,asc));
	}
	
	/**
	 * 添加按升序排序的字段
	 * @param field
	 */
	public void addAsc(String field) {
		this.addSort(field, true);
	}
	
	/**
	 * 添加按降序排序的字段
	 * @param field
	 */
	public void addDesc(String field) {
		this.addSort(field, false);
	}
	
	public String orders(){
		if(this.orders != null && orders.size() > 0) {
			StringBuilder string = new StringBuilder();
			this.orders.forEach(item->{
				string.append(" " + item.getField() + " " + item.getOrder() + " ,");
			});
			return " order by " + string.substring(0,string.length()-1);
		}
		return "";
	}
	
	class Order{
		private String field;
		private boolean asc;
		
		Order(String field, boolean asc){
			this.field = field;
			this.asc = asc;
		}
		public String getField() {
			return this.field;
		}
		public String getOrder() {
			return asc?"asc":"desc";
		}
	}
}
