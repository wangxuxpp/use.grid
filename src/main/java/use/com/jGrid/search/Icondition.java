package use.com.jGrid.search;


import java.util.List;

import use.com.jGrid.base.Filters;
import use.com.jGrid.base.Rules;


public interface Icondition {
	
	String getCondition(Filters filters, List<Rules> rulesList , Ioperation fop , boolean searchAlias);
}
