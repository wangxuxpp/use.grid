package use.com.jGrid.search;


import java.util.List;

import use.com.jGrid.base.Filters;
import use.com.jGrid.base.Rules;


public class ErpCondition implements Icondition{

	public String getCondition(Filters filters, List<Rules> rulesList , Ioperation op , boolean searchAlias) {
		StringBuffer sqlWhere = new StringBuffer("");
		String andOr = filters.getGroupOp();
		StringBuffer prefixWher = new StringBuffer("");
		
		for(Rules rules: rulesList) {
			//删除字段查询
			if(rules.getField().toLowerCase().equals("i_del") || 
					rules.getField().toLowerCase().equals("c_del")) {      
				if(rules.getData().equals("0")) {         
					prefixWher.append(" and i_del <> 1 ");
				} else if(rules.getData().equals("1")) {
					prefixWher.append(" and i_del = 1 ");   
				} else if(rules.getData().equals("2")) {
					prefixWher.append(" and 1 = 1 ");   
				}     
				continue; 
			 }
			//审批标志字段查询
			if(rules.getField().toLowerCase().equals("i_state_flag") || 
					rules.getField().toLowerCase().equals("c_state_flag")) {
				if(rules.getData().equals("0")) {         
					prefixWher.append(" and i_state_flag = 0 ");
				} else if(rules.getData().equals("1")) {
					prefixWher.append(" and i_state_flag = 1 ");   
				} else if(rules.getData().equals("2")) {
					prefixWher.append(" and i_state_flag = 2 ");   
				} else if(rules.getData().equals("3")) {
					prefixWher.append(" and i_state_flag = 3 ");  
				} else if(rules.getData().equals("4")) {
					prefixWher.append(" and 1 = 1 ");   
				}     
				continue; 
			}
			//关闭标志字段查询
			if(rules.getField().toLowerCase().equals("i_close_flag") || 
					rules.getField().toLowerCase().equals("c_close_flag")) {
				if(rules.getData().equals("0")) {         
					prefixWher.append(" and i_close_flag = 0 ");
				} else if(rules.getData().equals("1")) {
					prefixWher.append(" and i_close_flag = 1 ");
				} else if(rules.getData().equals("2")) {
					prefixWher.append(" and 1 = 1 ");   
				}     
				continue; 
			}

			//名字字段查询
			if(rules.getField().toLowerCase().equals("c_name")) {
				sqlWhere.append(" ").append(andOr).append(" (").append(rules.getField())
					.append(" like '%").append(rules.getData()).append("%'");
				if (searchAlias)
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
		
		String str ="";
		
		if (!prefixWher.toString().equals(""))
		{
			str += prefixWher.toString();
		}
		if(!sqlWhere.toString().equals(""))
		{
			str += " and (" + sqlWhere.toString().replaceFirst(andOr, "")+")";
		}
		return str;
	}
}
