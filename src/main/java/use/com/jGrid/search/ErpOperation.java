package use.com.jGrid.search;

public class ErpOperation implements Ioperation{

	public String translateSymbolic(String op, String fieldName) {
		if(op.toLowerCase().equals("eq")) {
			return "=";
		} else if(op.toLowerCase().equals("ne")) {
			return "<>";
		} else if(op.toLowerCase().equals("lt")) {
			return "<";
		} else if(op.toLowerCase().equals("le")) {
			return "<=";
		} else if(op.toLowerCase().equals("gt")) {
			return ">";
		} else if(op.toLowerCase().equals("ge")) {
			return ">=";
		} else if(op.toLowerCase().equals("cn")) {
			return "like";
		} else if(op.toLowerCase().equals("nc")) {
			return "not like";
		} else if(op.toLowerCase().equals("in")) {
			return "in";
		} else if(op.toLowerCase().equals("ni")) {
			return "not in";
		} else if(op.toLowerCase().equals("bw")) {
			return "=";
		}
		return "";
	}

}
