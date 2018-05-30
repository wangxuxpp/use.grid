package use.com.jGrid.search;

import java.util.List;

import use.com.jGrid.base.Filters;
import use.com.jGrid.base.Rules;



public class DefaultCondition implements Icondition{

	
	public String getCondition(Filters filters, List<Rules> rulesList,
			Ioperation op , boolean searchAlias) {
		StringBuffer sqlWhere = new StringBuffer();
		String andOr = filters.getGroupOp();
		for(Rules rules: rulesList) {
			//删除字段查询
			if(rules.getField().toLowerCase().equals("i_del") || 
					rules.getField().toLowerCase().equals("c_del")) {      
				if(rules.getData().equals("0")) {         
					sqlWhere.append(" and i_del <> 1 ");
				} else if(rules.getData().equals("1")) {
					sqlWhere.append(" and i_del = 1 ");   
				} else if(rules.getData().equals("2")) {
					sqlWhere.append(" and i_del != -99 ");   
				}     
				continue; 
			 }
			//名字字段查询
			if(rules.getField().toLowerCase().equals("c_name")) {
				sqlWhere.append(" ").append(andOr).append(" (").append(rules.getField())
				.append(" like '%").append(rules.getData()).append("%' ");
				
		        if(searchAlias)
		        {
		        	sqlWhere.append(" or c_aliascode like '%")
		        			.append(rules.getData().toLowerCase()).append("%'");
		        }
		        sqlWhere.append(")");
				continue;      
			}		
			//组合查询条件
			if(rules.getField().toLowerCase().startsWith("d_")) {
				sqlWhere.append(" ").append(andOr).append(" trunc(").append(rules.getField()).append(") ").append(op.translateSymbolic(rules.getOp(),""))
				.append(" to_date('").append(rules.getData()).append("','yyyy-mm-dd')");
			} else {
				sqlWhere.append(" ").append(andOr).append(" ").append(rules.getField()).append(" ").append(op.translateSymbolic(rules.getOp(),""));
				
				if(rules.getOp().toLowerCase().equals("cn") || rules.getOp().toLowerCase().equals("nc")) {
					sqlWhere.append(" '%").append(rules.getData()).append("%'");
				} else if(rules.getOp().toLowerCase().equals("in") || rules.getOp().toLowerCase().equals("ni")) {
					sqlWhere.append(" ('").append(rules.getData()).append("')");                 
				} else {
					sqlWhere.append(" '").append(rules.getData()).append("'");
				}
			}
			
		}
		
		return "and (1=1 "+sqlWhere.toString()+")";
	}
}
