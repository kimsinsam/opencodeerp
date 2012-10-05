package open.accounting.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.springframework.web.servlet.view.document.AbstractJExcelView;

public class ExcelGridView extends AbstractJExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> arg0,
			WritableWorkbook arg1, HttpServletRequest arg2,
			HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
	}

}
