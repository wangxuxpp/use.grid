package use.com.jGrid.initial;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import use.com.jGrid.export.ExportXls;
import use.com.jGrid.export.ExportXlsServlet;
import use.com.jGrid.gn.GdCardinalInfo;
import use.com.jGrid.gridUtil.GridUtil;
import use.common.util.file.upAndDown.FileUpAndDown;
import use.common.util.file.upAndDown.FileUpAndDownUtil;

public class GridInitial implements ServletContainerInitializer {

	protected final Logger log = LoggerFactory.getLogger(GridInitial.class);
	
	public void onStartup(Set<Class<?>> arg, ServletContext sc) throws ServletException 
	{
		log.info("jGrid initial starting");
		GridUtil.userL = new String(GdCardinalInfo.getUserListener());
    	try 
    	{   
    		GridPropertyParameter param = new GridPropertyParameter("jGrid.properties");
    		GridUtil.setPropertyParameter(param);
    		if(param.isFileExists())
    		{
    			if(param.isEnableExprot())
    			{
    				log.info("jGrid export xls starting");
    				FileUpAndDownUtil.setUpAndDown(new FileUpAndDown());
    				FileUpAndDownUtil.setSavePath(param.getExportPatch());
    				GridUtil.getExportXls = new ExportXls();
    				ServletRegistration.Dynamic servlet = sc.addServlet(ExportXlsServlet.class.getSimpleName(),ExportXlsServlet.class); 
    				servlet.addMapping("/gridExportXls.do"); 
    				log.info("jGrid export xls initialed");
    			}
    			if (param.isDelMapFile())
    			{
    				log.info("jGrid initialed");
        			return;
    			}
    		}
    	}catch(Exception er)
    	{
    		log.error("jGrid initial error , result:"+er.getMessage());
    	}
	}

}
