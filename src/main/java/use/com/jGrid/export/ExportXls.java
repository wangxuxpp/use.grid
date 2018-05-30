package use.com.jGrid.export;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import net.sf.json.JSONArray;
import use.common.dao.IdaoUpper;

@SuppressWarnings({"unchecked","rawtypes"})
public class ExportXls {

	public  String sql = "newSql";
	public  String args = "newArgs";
	public  String column = "column";
	public  String title = "title";
	
	public void exprotXls(HttpServletResponse response ,ModelMap requestModel , String filePatch , IdaoUpper dao) throws IOException
	{
		
		String sql = requestModel.get(this.sql).toString().trim();
		String args = requestModel.get(this.args).toString().trim();
		String col = requestModel.get(this.column).toString().trim();
		String title = requestModel.get(this.title).toString().trim();
		
		JSONArray c = JSONArray.fromObject(col);
		
		Object arg [] = null;
		if(!(args.equals("") || args.equals(",")))
		{
			arg = args.split(",");
		}	
		List l = dao.executeQuery(sql, arg);
		ExportXlsPIO.exportXLS(response, l, c, title);
		//expXls(l , filePatch , c , title);
	}
	
	
//	private void expXls(List<Map> l , String file , JSONArray col , String title)
//	{
//
//		WritableWorkbook f = null;
//		try
//		{
//			f = this.openExcel(file);
//			WritableSheet nSheet = f.createSheet("grid export xls", 0);
//			nSheet.mergeCells(0, 0, col.size()-1, 0);
//			WritableFont fontTitle = new WritableFont(	WritableFont.ARIAL,14,
//														WritableFont.BOLD,
//														false,
//														UnderlineStyle.NO_UNDERLINE,
//														Colour.BLACK);
//			WritableCellFormat cellFormatTitel = new WritableCellFormat(fontTitle);
//			//设置文字居中对齐方式;  
//			cellFormatTitel.setAlignment(Alignment.CENTRE);  
//			//设置垂直居中;  
//			cellFormatTitel.setVerticalAlignment(VerticalAlignment.CENTRE);  
//			nSheet.addCell(new Label(0, 0, title,cellFormatTitel));
//			
//			WritableFont font1 = new WritableFont(WritableFont.ARIAL,
//													12,
//													WritableFont.BOLD,
//													false,
//													UnderlineStyle.NO_UNDERLINE,
//													Colour.BLACK);  
//			WritableCellFormat cellFormat1 = new WritableCellFormat(font1);  
//			//设置背景颜色;  
//			cellFormat1.setBackground(Colour.GRAY_25);  
//			//设置边框;  
//			cellFormat1.setBorder(Border.ALL, BorderLineStyle.THIN);  
//			//设置自动换行;  
//			cellFormat1.setWrap(true);  
//			//设置文字居中对齐方式;  
//			cellFormat1.setAlignment(Alignment.CENTRE);  
//			//设置垂直居中;  
//			cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);  
//
//			for (int j = 0 ; j<col.size() ; j++)
//			{
//				JSONObject a = col.getJSONObject(j);
//				nSheet.addCell(new Label(j, 1, a.get("label").toString(),cellFormat1));
//				
//				int width = 0;
//				try
//				{
//					width = Integer.parseInt(a.get("width").toString())/10;
//				}catch(Exception er) {width = 0;}
//				nSheet.setColumnView(j, width);
//			}
//			int i = 2;
//			WritableFont fontInfo = new WritableFont(WritableFont.ARIAL,
//													12,
//													WritableFont.NO_BOLD,
//													false,
//													UnderlineStyle.NO_UNDERLINE,
//													Colour.BLACK);
//			WritableCellFormat cellFormatInfo = new WritableCellFormat(fontInfo);
//			cellFormatInfo.setBorder(Border.ALL, BorderLineStyle.THIN);  
//			cellFormatInfo.setWrap(true);  
//			for(Map m : l)
//			{
//				for (int j = 0 ; j<col.size() ; j++)
//				{
//					JSONObject a = col.getJSONObject(j);
//					if (m.containsKey(a.get("name").toString()))
//					{
//						String value = "";
//						try
//						{
//							value = m.get(a.get("name").toString()).toString().trim();
//						}catch(Exception er){}
//						nSheet.addCell(new Label(j, i,value,cellFormatInfo));
//					}
//				}
//				i++;
//			}
//		}catch(Exception e)
//		{
//			throw new ExportXlsException(e.getMessage());
//		}finally
//		{
//			try 
//			{
//				saveQuit(f);
//			} catch (Exception e) 
//			{
//				throw new ExportXlsException(e.getMessage());
//			}
//		}
//	}
//	
//	/**
//	 * 保存并退出
//	 * @param fWorkBook
//	 * @throws IOException
//	 * @throws WriteException
//	 */
//	public void saveQuit(WritableWorkbook fWorkBook) throws IOException, WriteException
//	{
//		if (fWorkBook != null)
//		{
//			fWorkBook.write();
//			fWorkBook.close();
//			fWorkBook = null;
//		}
//	}
//	/**
//	 * 创建写入EXCEL
//	 * @param fileName
//	 * @return
//	 */
//	public WritableWorkbook openExcel(String fileName)
//	{
//		String str = fileName.substring(0 , fileName.lastIndexOf(File.separator));
//		File dir = new File(str+File.separator);
//		if (!dir.exists())
//		{
//			dir.mkdirs();
//		}
//		File f = new File(fileName);
//		WritableWorkbook fWorkBook = null;
//		try {
//			fWorkBook = Workbook.createWorkbook(f);
//		} catch (IOException e) {
//			throw new ExportXlsException(e.getMessage());
//		}
//		return fWorkBook;
//	}
}
