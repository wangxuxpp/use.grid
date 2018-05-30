package use.com.jGrid.export;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

import use.com.jGrid.gridUtil.GridUtil;
import use.common.util.file.upAndDown.FileUpAndDownUtil;

public class ExportXlsServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	protected final Logger log = LoggerFactory.getLogger(ExportXlsServlet.class);

	protected void service(HttpServletRequest req, HttpServletResponse resp)
	{
		
		try
		{
			downXls(req , resp);
		}catch(Exception er)
		{
			//log.error("导出xls异常：异常原因："+er.getMessage());
			log.error("导出xls异常：异常原因：", er);
		}
		
	}
	
	private String getValue(String val) throws UnsupportedEncodingException
	{
		return GridUtil.encoding(val, 
				GridUtil.getPropertyParameter().getSourceEncode(), 
				GridUtil.getPropertyParameter().getDestinationEncode());
	}
	
	private void downXls(HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		if (GridUtil.fDao == null)
		{
			return ;
		}
		ModelMap m = new ModelMap();
	
		m.put("newSql",  getValue(req.getParameter("newSql").toString().trim()));
		m.put("newArgs", getValue(req.getParameter("newArgs").toString().trim()));
		m.put("column", getValue(req.getParameter("column").toString().trim()));
		m.put("title", getValue(req.getParameter("title").toString().trim()));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String file = FileUpAndDownUtil.getSavePath() + sdf.format(new Date())+".xls";
		
		GridUtil.getExportXls.exprotXls(resp , m, file, GridUtil.fDao);
		//FileUpAndDownUtil.getUpAndDown().fileDownLoad(resp, file);
		
	}
	
}
