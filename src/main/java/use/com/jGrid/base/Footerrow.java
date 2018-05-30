package use.com.jGrid.base;

import java.util.HashMap;
import java.util.Map;


public class Footerrow {
	
	private String title;
	private String titleRow;
	private Map<String, Object> row = new HashMap<String, Object>();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleRow() {
		return titleRow;
	}
	public void setTitleRow(String titleRow) {
		this.titleRow = titleRow;
	}
	public Map<String, Object> getRow() {
		return row;
	}
	public void setRow(Map<String, Object> row) {
		this.row.putAll(row);
	}
	public void setRow(String key, Object value) {
		this.row.put(key, value);
	}
	
}
