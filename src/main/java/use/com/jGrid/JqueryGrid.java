package use.com.jGrid;

import use.com.jGrid.Grid.JqGrid;
import use.com.jGrid.gridUtil.GridUtil;
import use.common.dao.IGridDao;


public class  JqueryGrid
{
	
	public IGridDao gridDao()
	{
		return GridUtil.getGridDao();
	}
	
	public JqGrid getGrid()
	{
		return new JqGrid(); 
	}

	
}
