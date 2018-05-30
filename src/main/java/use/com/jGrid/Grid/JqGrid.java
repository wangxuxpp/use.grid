package use.com.jGrid.Grid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import use.com.jGrid.base.Filters;
import use.com.jGrid.base.Footerrow;
import use.com.jGrid.base.Rules;
import use.com.jGrid.gn.GdCardinalInfo;
import use.com.jGrid.gridUtil.GridUtil;
import use.com.jGrid.search.Icondition;
import use.com.jGrid.search.Ioperation;
import use.common.security.BaseInfo;


/**
 * 设置jqGrid请求和应答参数
 * 
 * 
 * @author wx
 * @version 1.00 $2013-03-26$
 * 
 * 王旭个人所有
 * 
 * history
 * 
 */
@SuppressWarnings({"rawtypes"})
public class JqGrid implements ISetGridConditionAndOperation{
	
	protected final Logger log = LoggerFactory.getLogger(JqGrid.class);
	
	
	private Ioperation fOper = null;
	private Icondition fCondition = null;
	public void setCondition(Icondition val)
	{
		this.fCondition = val;
	}
	public void setOper(Ioperation val)
	{
		this.fOper = val;
	}
	
	
	
	public JqGrid()
	{
	}
	public JqGrid(Boolean isCreate)
	{
		if (BaseInfo.getSecurityInfo().getInfo() instanceof Boolean)
		{
			GdCardinalInfo.intialParameter(this);
		}	
	}
	public JqGrid(String value)
	{
		this();
	}

	
	/**
	 * 设置jqGrid请求参数
	 * 
	 * @param model
	 * @return
	 */
	
	public String setParameter(Map model) 
	{
		if (!(BaseInfo.getSecurityInfo().getInfo() instanceof Boolean))
		{
			return "";
		}
		this.prm_page = GridUtil.objInt(model.get(GdCardinalInfo.getParameterRequestPage()));
		this.prm_rows = GridUtil.objInt(model.get(GdCardinalInfo.getParameterRequestRows()));
		this.sort = GridUtil.objStr(model.get(GdCardinalInfo.getParameterRequestSort()));
		this.order = GridUtil.objStr(model.get(GdCardinalInfo.getParameterRequestOrder()));
		this.subMainGridId = GridUtil.objInt(model.get(GdCardinalInfo.getParameterRequestSubMainGridId()));
		this.subGridId = GridUtil.objInt(model.get(GdCardinalInfo.getParameterRequestSubGridId()));
		this.args = GridUtil.objStr(model.get(GdCardinalInfo.getParameterRequestArgs()));
		this.isSearch = GridUtil.objBoolean(model.get(GdCardinalInfo.getParameterRequestIsSearch()));
		
		this.userListener = GridUtil.objStr(model.get(userListenerKey));
		this.userConditionData = GridUtil.objStr(model.get(userConditionKey));
		//计算开始记录数
		this.start = (this.prm_page - 1) * this.prm_rows;
		this.rows = this.prm_rows;
		
		StringBuffer sql = new StringBuffer();
		//查询过滤条件
		this.filters = GridUtil.objStr(model.get("filters"));
		if(!GridUtil.strIsEmpty(this.filters)) {
			Filters filters = this.getFilters(this.filters);
			List<Rules> rulesList = filters.getRulesObject();
			if(!GridUtil.strIsEmpty(filters.getGroupOp())) {
				sql.append(fCondition.getCondition(filters, rulesList , fOper , this.searchAlias));
			}
		}
		return sql.toString();
	}
	
	private Filters filter = new Filters();
	private Filters getFilters(String filters) 
	{
		try 
		{
			Map m = GridUtil.getJackson().readValue(filters, Map.class);
			filter.setGroupOp(GridUtil.objStr(m.get("groupOp")));
			filter.setRules((List)m.get("rules"));
		} catch (Exception er)
		{
			log.error("grid 解析条件语句异常，异常原因："+er.getMessage());
		}
		return filter;
	}
	
	
	/**
	 * 设置用户数据
	 * 
	 * @param fenyeMap
	 * @return
	 */
	private Map<String, Object> setUserData(Map fenyeMap) {
		Footerrow footerrow = (Footerrow)fenyeMap.get("footerrow");
		Map<String, Object> userData = new HashMap<String, Object>();
		//设置页尾数据行标题
		userData.put(footerrow.getTitleRow(), footerrow.getTitle());
		//设置页尾数据行
		Map<String, Object> row = footerrow.getRow();
		for(String key : row.keySet()) {
			userData.put(key, row.get(key));
		}
		return userData;
	}
	
	/**
	 * 设置jqGrid应答参数
	 *
	 * @param Map fenyeMap
	 */
	public Map getParameter(Map fenyeMap) 
	{
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();	
		if (!(BaseInfo.getSecurityInfo().getInfo() instanceof Boolean))
		{
			return jsonMap;
		}
		//if (this.userListener.equals(GridUtil.userL))
		//{
		jsonMap.put(GdCardinalInfo.getParameterResponseRoot(), fenyeMap.get("fenyeList")); //表示实际模型数据的入口
		jsonMap.put(GdCardinalInfo.getParameterResponseRowSize(), fenyeMap.get("recordSize")); //表示数据行总数的参数
		//}
		jsonMap.put(GdCardinalInfo.getParameterResponseNowPage(), prm_page); //表示当前页码的参数
		
		//计算页码总数
		int json_total = (int)Math.ceil(GridUtil.objDouble(fenyeMap.get("recordSize")) / GridUtil.objDouble(rows));
		jsonMap.put(GdCardinalInfo.getParameterResponsePageSize(), json_total);//表示页码总数的参数 
		
		//页尾数据行
		if(fenyeMap.containsKey("footerrow") && fenyeMap.get("footerrow") != null) {
			Map<String, Object> userdata = setUserData(fenyeMap);
			userdata.put("newSql", fenyeMap.get("newSql"));
			userdata.put("newArgs", fenyeMap.get("newArgs"));
			jsonMap.put("userdata", userdata);
	    //用户数据	
		} else {
			Map<String, Object> userdata = new HashMap<String, Object>();
			userdata.put("newSql", fenyeMap.get("newSql"));
			userdata.put("newArgs", fenyeMap.get("newArgs"));
			jsonMap.put("userdata", userdata); 
		}
		
		return jsonMap;
	}

	public String getCondition(Filters filters, List<Rules> rulesList,
			Ioperation fop , boolean searchAlias) {
		return "";
	}
	public String translateSymbolic(String op, String fieldName) {
		return "=";	
	}
	public Map getUserConditionData() throws Exception 
	{
		return GridUtil.getJackson().readValue(this.userConditionData , Map.class);
	}

	/**
	 * 是否需要查询aliascode辅助名字段
	 * true:查询C_NAME字段时自动关联 c_aliascode
	 * false:查询不关联c_aliascode
	 */
	private boolean searchAlias = false;
	//请求参数
	private int prm_page = 0; //表示请求页码的参数
	private int prm_rows = 0; //表示请求行数的参数
	private String filters = ""; //表示请求查询过滤条件参数
	
	private String sort = ""; //表示用于排序的列名的参数
	private String order = ""; //表示采用的排序方式的参数
	
	//查询数据库使用的参数
	private int start = 0; //开始记录数
	private int rows = 0; //每页行数
	
	private int subMainGridId = 0; //Grid请求带的一些其他参数
	private int subGridId = 0;
	private String args = "";
	private boolean isSearch = false;
	
	
	//用户协议字符串
	private String userListener = "";
	//用户查询字符串
	private String userConditionData = "";
	//GRID用户协议字符串KEY
	private final String userListenerKey = "userListener";
	//GRID用户查询字符串KEY
	private final String userConditionKey = "userPostConditon";
	
	
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}
	public boolean isSearchAlias() {
		return searchAlias;
	}
	public void setSearchAlias(boolean searchAlias) {
		this.searchAlias = searchAlias;
	}
	public int getPrm_page() {
		return prm_page;
	}
	public void setPrm_page(int prm_page) {
		this.prm_page = prm_page;
	}
	public int getPrm_rows() {
		return prm_rows;
	}
	public void setPrm_rows(int prm_rows) {
		this.prm_rows = prm_rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getSubMainGridId() {
		return subMainGridId;
	}
	public void setSubMainGridId(int subMainGridId) {
		this.subMainGridId = subMainGridId;
	}
	public int getSubGridId() {
		return subGridId;
	}
	public void setSubGridId(int subGridId) {
		this.subGridId = subGridId;
	}
	public boolean isSearch() {
		return isSearch;
	}
	public void setSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}
	public String getUserConditionKey() {
		return userConditionKey;
	}
	public void setUserConditionData(String userConditionData) {
		this.userConditionData = userConditionData;
	}

	
	
}
