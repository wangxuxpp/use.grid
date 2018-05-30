package use.com.jGrid.gridUtil;

import java.io.UnsupportedEncodingException;

import com.fasterxml.jackson.databind.ObjectMapper;

import use.com.jGrid.Grid.JqGrid;
import use.com.jGrid.export.ExportXls;
import use.com.jGrid.initial.GridPropertyParameter;
import use.common.dao.IGridDao;
import use.common.dao.IdaoUpper;
import use.common.dao.MysqlGridDao;

public class GridUtil {
	
	public static String userL = "gridtest";

	private static ObjectMapper objMap = null;
	
	public static ObjectMapper getJackson()
	{
		if (objMap == null)
		{
			objMap = new ObjectMapper();
		}
		return objMap;
	}

	private static IGridDao myDao = new MysqlGridDao();
	public static IGridDao getGridDao() {
		return myDao;
	}
	public static void setGridDao(IGridDao a) {
		myDao = a;
	}
	public static JqGrid getGrid()
	{
		return new JqGrid(true);
	}
	

	/**
	 * 导出XLS使用DAO 如果为空将不导出
	 */
	public static IdaoUpper fDao = null;
	
	public static ExportXls getExportXls = null;
	
	private static GridPropertyParameter gPropertyParameter = null;
	public static GridPropertyParameter getPropertyParameter()
	{
		return gPropertyParameter;
	}
	public static void setPropertyParameter(GridPropertyParameter v)
	{
		gPropertyParameter = v;
	}
	
	
	
	//--------------------------------------------------------------------
	public static boolean strIsEmpty(CharSequence str) {
		return (str == null) || (str.toString().trim().equals(""));
	}
	public static int objInt(Object obj) {
		try {
			if(obj == null) {
				return 0;
			}
			return Integer.parseInt(obj.toString());
		} catch (Exception e) {
			return 0;
		}	
	}
    public static String objStr(Object obj) {
    	try {
    		if(obj == null) {
    			return "";
    		}
    		return obj.toString();
    	} catch (Exception e) {
    		return "";
    	}
    }
	public static boolean objBoolean(Object obj) {
		try {
			if(obj == null) {
				return false;
			}
			return Boolean.parseBoolean(obj.toString());
		} catch (Exception e) {
			return false;
		}
	}
    public static double objDouble(Object obj) {
    	try {
    		if(obj == null) {
        		return 0D;
    		}
    		return Double.parseDouble(obj.toString());
    	} catch(Exception e) {
    		return 0D;
    	}
    }
    
	public static String encoding(String val , String se , String de) throws UnsupportedEncodingException
	{
		return new String(val.getBytes(se) ,de);
	}
}
