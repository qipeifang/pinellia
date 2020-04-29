package com.jokerchen.pinellia.common.util;

import com.jokerchen.pinellia.common.constant.Constant;
import com.jokerchen.pinellia.common.model.Page;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**   
 * @description: 用于获取分页信息
 * 				主要是通过 RequestContextHolder 获取前端request传递过来的pageSize和pageNo，
 * 				 以便在做分页显示数据的时候，不用显式的在各个方法中传入这两个参数。
 * @author Joker Chen 
 * @date 2019-03-18 09:35:09  
 */
public class PageUtil {

	/**
	 * 构建分页对象，如果获取不到request对象，或者request对象中的pageSize和pageNo不存在，则默认为pageSize=10,pageNo=0
	 * @return
	 */
	public static Page getPageRequest() {
		RequestAttributes attribute = RequestContextHolder.getRequestAttributes();
		int pageSize = Constant.DEFAULT_PAGE_SIZE, pageNumber = Constant.DEFAULT_PAGE_NUMBER;
		if(attribute != null) {
			//获取request对象
			HttpServletRequest request = ((ServletRequestAttributes)attribute).getRequest();  
			if(request != null) {
				//从request对象中获取从web传递过来的分页信息
				String size = request.getParameter(Constant.PAGE_SIZE_PARAM);
				String number = request.getParameter(Constant.PAGE_NUMBER_PARAM);
				if(size != null && !size.isEmpty()) {
					pageSize = Integer.parseInt(size);
				}
				if(number != null && !number.isEmpty()) {
					pageNumber = Integer.parseInt(number);
				}
			}
		}
		return getPageRequest(pageNumber,pageSize);
	}
	
	/**
	 * 构建用于分页查询的分页对象
	 * @param pageNumber	查询的页码，第一页从0开始
	 * @param pageSize	每页查询的数据量
	 * @return
	 */
	public static Page getPageRequest(int pageNumber,int pageSize) {
		Page page = new Page(pageNumber,pageSize);
		return page;
	}

	/**
	 * 对pageVO里的数据进行调整
	 * 用于进行分页查询之前，调整当前页码和查询条数
	 */
	public static void init(Page page){
		if(page.getPageNumber() == null || page.getPageNumber() < 1){
			//如果查询的当前页数小于1，则从第一页开始查询
			page.setPageNumber(Constant.DEFAULT_PAGE_NUMBER);
		}
		if(page.getPageSize() == null || page.getPageSize() < 1){
			//如果查询当前页显示的条数小于1，则默认查询10条数据
			page.setPageSize(Constant.DEFAULT_PAGE_SIZE);
		}
	}

	/**
	 * 调整各个参数
	 * 用于查询结束后，对各个参数进行调整
	 */
	public static void adjustData(Page page){
		if(page.getTotalElements() != null && page.getTotalElements() > 0){
			//数据总数大于0，如果能整除，直接相除得到页数，否则相除加一得到页数
			page.setTotalPages(page.getTotalElements()%page.getPageSize()==0 ?
					page.getTotalElements()/page.getPageSize() : (page.getTotalElements()/page.getPageSize()+1));
		}else{
			//总数不大于0，则设置总数位0，总的页数为1，以及当前页数为1
			page.setTotalPages(1);
			page.setTotalElements(0);
			page.setPageNumber(1);
			if(page.getContent() != null && page.getContent().size() > 0){
				//如果查询出来的数据总数大于0，则修改总的数据数量为当前查询出的数据数量
				page.setTotalElements(page.getContent().size());
			}
		}
		if(page.getTotalPages() < page.getPageNumber()) {
			//如果总的页数小于当前查询页数，则修改当前查询页数为总的页数
			page.setPageNumber(page.getTotalPages());
		}
	}
}
