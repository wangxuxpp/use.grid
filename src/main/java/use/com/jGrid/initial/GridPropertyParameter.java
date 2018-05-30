package use.com.jGrid.initial;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import use.common.util.propertyFile.ReadPropertyValue;

public class GridPropertyParameter {
	
	protected final Logger log = LoggerFactory.getLogger(GridPropertyParameter.class);
	
	private boolean fileExists = false;
	private boolean delMapFile = false;
	private boolean enableExprot = false;
	private String exportPatch = "";
	private String sourceEncode = "";
	private String destinationEncode ="";
	
	
	public String getSourceEncode() {
		return sourceEncode;
	}
	public String getDestinationEncode() {
		return destinationEncode;
	}
	public boolean isFileExists() {
		return fileExists;
	}
	public boolean isDelMapFile() {
		return delMapFile;
	}
	public boolean isEnableExprot() {
		return enableExprot;
	}
	public String getExportPatch() {
		return exportPatch;
	}
	public URL getClassPath()
	{
		return GridPropertyParameter.class.getResource("/");
	}
	public GridPropertyParameter(String file)
	{
		URL uPath = this.getClassPath();
		File f = new File(uPath.getFile() , file);
		if(f.exists())
		{
			fileExists = true;
			InputStream in = null;
			Properties prop = new Properties();
			try
			{
				in = new FileInputStream(f);
	    		prop.load(in);
	    		delMapFile = ReadPropertyValue.getBoolean(prop , "inital.map" , false);
	    		enableExprot = ReadPropertyValue.getBoolean(prop , "inital.enableExport" , false);
	    		exportPatch = ReadPropertyValue.getStr(prop, "inital.exportPath", "");
	    		if (exportPatch.equals(""))
	    		{
	    			exportPatch = this.getClassPath().getFile()+"exportXLS"+File.separator ;
	    		}
	    		sourceEncode = ReadPropertyValue.getStr(prop, "inital.sourceEncode", "");
	    		destinationEncode = ReadPropertyValue.getStr(prop, "inital.destinationEncode", "");
			}catch(Exception er)
			{
				log.error("jGrid initial property File error , result:"+er.getMessage());
			}
			finally{
				try {
					if (in != null)
					{
						in.close();
					}
				} catch (Exception e) 
				{
					log.error("jGrid destroy property File error , result:"+e.getMessage());
				}
			}
		}
	}
}
