package use.com.jGrid.base;

/**
 * 
 * 项目名称:grid 查询条件
 * 类型名称:Rules
 * 类型描述:
 * 作者:wx
 * 创建时间:2017年2月24日
 * @version:
 */
public class Rules {
	
	/**
	 * 字段名称
	 */
	private String field;
	/**
	 * 操作
	 */
	private String op;
	/**
	 * 条件值
	 */
	private String data;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
