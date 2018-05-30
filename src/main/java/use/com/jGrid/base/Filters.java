package use.com.jGrid.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import use.com.jGrid.gridUtil.GridUtil;

/**
 * 过滤条件集合
 * 项目名称:com
 * 类型名称:Filters
 * 类型描述:
 * 作者:wx
 * 创建时间:2017年2月24日
 * @version:
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Filters {
	
	/**
	 * 逻辑运算符
	 */
	private String groupOp ="";
	/**
	 * 条件集合MAP
	 */
	private List<Map> rulesMap = null;
	/**
	 * 条件集合Rules
	 */
	private List<Rules> rulesList = new ArrayList<Rules>();
	
	
	public List<Rules> getRulesObject(List<Map> rl)
	{
		rulesList.clear();
		if (rl == null || rl.size() <= 0)
		{
			return rulesList;
		}
		for (int i = 0 ; i< rl.size() ; i++)
		{
			Rules r = new Rules();
			Map m = (Map)rl.get(i);
			r.setData(GridUtil.objStr(m.get("data")));
			r.setField(GridUtil.objStr(m.get("field")));
			r.setOp(GridUtil.objStr(m.get("op")));
			rulesList.add(r);
		}
		return rulesList;
	}
	
	public List<Rules> getRulesObject()
	{
		return getRulesObject(rulesMap); 
	}
	
	public List<Map> getRulesList()
	{
		return rulesMap;
	}
	public void setRules(List rules) {
		this.rulesMap = rules;
	}
	
	public String getGroupOp() {
		return groupOp;
	}
	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}


	
}
