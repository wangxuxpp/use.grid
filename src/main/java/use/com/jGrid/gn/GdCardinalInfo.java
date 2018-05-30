package use.com.jGrid.gn;


import java.lang.reflect.Field;

import use.com.jGrid.Grid.ISetGridConditionAndOperation;
import use.com.jGrid.search.ErpCondition;
import use.com.jGrid.search.ErpOperation;
import use.com.jGrid.search.Icondition;
import use.com.jGrid.search.Ioperation;
import use.common.security.BaseInfo;


@SuppressWarnings("rawtypes")
public class GdCardinalInfo {
	
	private static Ioperation fOp = new ErpOperation();
	private static Icondition fCo = new ErpCondition();
	

	/**
	 * 前台js协议变量，
	 * 要求前后台为统一作用，如果不一致停止发送数据
	 */
	private static final byte [] userListener = new byte [] {99, 116, 99};
	
	/*请求参数*/
	private static String parameterRequestPage ="prm_page";//请求页数参数 prm_page
	private static String parameterRequestRows ="prm_rows";//请求记录数参数 prm_rows
	private static String parameterRequestSort ="prm_sort";//请求排序字段参数
	private static String parameterRequestOrder ="prm_order";//请求排序参数
	private static String parameterRequestSubMainGridId ="subMainGridId";//请求子表查询主表ID
	private static String parameterRequestSubGridId ="subGridId";//请求子表查询GRID-ID
	private static String parameterRequestArgs ="args"; //
	private static String parameterRequestIsSearch ="prm_search";//请求是否为查询请求
	
	/*返回参数*/
	private static String parameterResponseRoot ="json_root";//数据入口json_root
	private static String parameterResponseRowSize ="json_records"; //返回共有页数
	private static String parameterResponseNowPage ="json_page";//返回当前页码
	private static String parameterResponsePageSize ="json_total"; //返回共有页数
	
	/*自定义参数*/
	private static String userNewSql ="newSql";
	private static String userNewArgs ="newArgs";
	private static String userDate ="userdata";

	
	
	public static String getParameterRequestPage() {	
		return parameterRequestPage;
	}

	public static String getParameterRequestRows() {
		return parameterRequestRows;
	}

	public static String getParameterRequestSort() {
		return parameterRequestSort;
	}
	public static String getParameterRequestOrder() {
		return parameterRequestOrder;
	}

	public static String getParameterRequestSubMainGridId() {
		return parameterRequestSubMainGridId;
	}

	public static String getParameterRequestSubGridId() {
		return parameterRequestSubGridId;
	}
	public static String getParameterRequestArgs() {
		return parameterRequestArgs;
	}
	public static String getParameterRequestIsSearch() {
		return parameterRequestIsSearch;
	}
	public static String getParameterResponseRoot() {
		return parameterResponseRoot;
	}
	public static String getParameterResponseRowSize() {
		return parameterResponseRowSize;
	}
	public static String getParameterResponseNowPage() {
		return parameterResponseNowPage;
	}
	public static String getParameterResponsePageSize() {
		return parameterResponsePageSize;
	}
	public static String getUserNewSql() {
		return userNewSql;
	}
	public static String getUserNewArgs() {
		return userNewArgs;
	}
	public static String getUserDate() {
		return userDate;
	}
	public static void intialParameter(ISetGridConditionAndOperation val)
	{
		val.setCondition(fCo);
		val.setOper(fOp);
	}
	
	static{
		if (!(BaseInfo.getSecurityInfo().getInfo() instanceof Boolean))
		{
			try{
				Class a = GdCardinalInfo.class;
				Field fs[] = a.getDeclaredFields();
				for (Field f : fs)
				{
					f.setAccessible(true);
					if (f.get(a) instanceof String)
					{
						String value = String.valueOf(f.get(a));
						f.set(a, value+"_");
					}
				}
			}catch(Exception e)
			{	
			}
		}
	
	}

	public static byte[] getUserListener() {
		return userListener;
	}
}
