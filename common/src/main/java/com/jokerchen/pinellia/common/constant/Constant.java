package com.jokerchen.pinellia.common.constant;

/**   
 * @description: 定义一些系统中通用的常量
 * @author Joker Chen 
 * @date 2019-03-18 09:51:18  
 */
public class Constant {
	
	/** 数据状态,0：正常，1：删除，2：停用  */
	public final static int DATA_STATE_NORMAL = 0;
	/** 数据状态,0：正常，1：删除，2：停用  */
	public final static int DATA_STATE_DELETE = 1;
	/** 数据状态,0：正常，1：删除，2：停用  */
	public final static int DATA_STATE_DISABLE = 2;
	
	/** 分页查询，页面传往后台的页码参数  */
	public final static String PAGE_NUMBER_PARAM = "pageNumber";
	/** 分页查询，页面传往后台的每页条数参数  */
	public final static String PAGE_SIZE_PARAM = "pageSize";
	/** 分页查询，默认查询的页码，默认从1开始，保持前后端的一致  */
	public final static int DEFAULT_PAGE_NUMBER = 1;
	/** 分页查询，默认每页查询的数量  */
	public final static int DEFAULT_PAGE_SIZE = 10;

	/**  菜单类型，0：按钮，1：菜单  */
	public final static int MENU_TYPE_BUTTON = 0;
	/** 菜单类型，0：按钮，1：菜单  */
	public final static int MENU_TYPE_MENU = 1;
	
	/** 权限类型，0：无需权限，1：超级管理员、需要权限，2：操作员 */
	public final static int AUTH_TYPE_NO_AUTH= 0;
	/** 权限类型，0：无需权限，1：超级管理员、需要权限，2：操作员 */
	public final static int AUTH_TYPE_SUPER_ADMIN= 1;
	public final static int AUTH_TYPE_NEED_AUTH= 1;
	/** 权限类型，0：无需权限，1：超级管理员、需要权限，2：操作员 */
	public final static int AUTH_TYPE_OPERATOR= 2;

	/** 操作日志类型，0：成功，1：异常 */
	public final static int OPERATION_LOG_SUCCESS = 0;
	public final static int OPERATION_LOG_FAILURE = 1;
	/** 操作日志类型，C：新增，R：查询，U：修改，D：删除，S：保存 */
	public final static String OPERATION_CREATE = "C";
	public final static String OPERATION_READ = "R";
	public final static String OPERATION_UPDATE = "U";
	public final static String OPERATION_DELETE = "D";
	public final static String OPERATION_SAVE = "S";
	
	/** base64 类型的图片格式的前缀 */
//	public final static String base64_image_prefix = "data:image/jpeg;base64,";
	public final static String base64_image_jpeg_prefix = "data:image/jpeg;base64,";
	public final static String base64_image_png_prefix = "data:image/png;base64,";
	/** 临时图片的文件夹 */
	public final static String default_temp_image_folder = "/temp";

}
