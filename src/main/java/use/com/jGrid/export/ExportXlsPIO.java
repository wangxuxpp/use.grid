package use.com.jGrid.export;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import use.common.util.file.upAndDown.FileUpAndDownUtil;

@SuppressWarnings({ "rawtypes", "deprecation" })
public class ExportXlsPIO {

	public static void exportXLS(HttpServletResponse response , List<Map> l , JSONArray col , String title) throws IOException
	{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(title);
		
		HSSFDataFormat format = workbook.createDataFormat();
		
		HSSFCellStyle cellStyleText = workbook.createCellStyle();
		cellStyleText.setDataFormat(format.getFormat("@"));
		cellStyleText.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居右
		cellStyleText.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		cellStyleText.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyleText.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyleText.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyleText.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		
//		HSSFCellStyle cellStyleTextNo = workbook.createCellStyle();
//		cellStyleTextNo.setDataFormat(format.getFormat("@"));
//		cellStyleTextNo.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 居右
//		cellStyleTextNo.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
//		cellStyleTextNo.setBorderBottom(HSSFCellStyle.BORDER_THICK); // 下边框
//		cellStyleTextNo.setBorderLeft(HSSFCellStyle.BORDER_THICK);// 左边框
//		cellStyleTextNo.setBorderTop(HSSFCellStyle.BORDER_THICK);// 上边框
//		cellStyleTextNo.setBorderRight(HSSFCellStyle.BORDER_THICK);// 右边框
		
		HSSFRow row = sheet.createRow(0); 
		CellRangeAddress  cra = new CellRangeAddress(0, 0, 0, col.size()-1);
		sheet.addMergedRegion(cra); 
		HSSFCell cell = null;
		cell = row.createCell(0);  
		cell.setCellStyle(cellStyleText);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue(title);
	
		
		generateXLS(col , sheet , cellStyleText , l);
		FileUpAndDownUtil.getUpAndDown().setResponseHead(response, "downfile.xls");
		workbook.write(response.getOutputStream());
	}
	
	
	public static void generateXLS(JSONArray col , HSSFSheet sheet, HSSFCellStyle cellStyleText ,  List<Map> l)
	{
		int rowIndex = 1;
		HSSFRow row = sheet.createRow(1);
		for (int j = 0 ; j<col.size() ; j++)
		{
			JSONObject a = col.getJSONObject(j);

			HSSFCell cell = null;
			cell = row.createCell(j);  
			cell.setCellStyle(cellStyleText);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(a.get("label").toString());
			int width = 0;
			try
			{
				width = Integer.parseInt(a.get("fixMinWidth").toString())/10;
			}catch(Exception er) {width = 0;}
			width = width == 0 ? 5000 : width;
			sheet.setColumnWidth(j, width);
		}
		
		rowIndex += 1;
		for(Map m : l)
		{
			row = sheet.createRow(rowIndex);
			for (int j = 0 ; j<col.size() ; j++)
			{
				JSONObject a = col.getJSONObject(j);
				if (m.containsKey(a.get("name").toString()))
				{
					String value = "";
					try
					{
						value = m.get(a.get("name").toString()).toString().trim();
					}catch(Exception er){}
					HSSFCell cell = null;
					cell = row.createCell(j);  
					cell.setCellStyle(cellStyleText);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(value);
				}
			}
			rowIndex++;
		}
	}
	
}
