package test.accounting;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;

public class HwpfTest {

	@Test
	public void test() throws Exception {
		String fileName = "D:/workspace/openAccounting/web/WEB-INF/docData/empty.doc";
		String fileName2 = "D:/workspace/openAccounting/web/WEB-INF/docData/table5.doc";
		XWPFDocument document = new XWPFDocument();
		XWPFTable table = document.createTable(3, 3);

		XWPFParagraph p1 = document.createParagraph();

		table.getRow(0).getCell(0).setText("hi0");
		table.getRow(0).getCell(1).setText("Hi!0");
		table.getRow(0).getCell(2).setText("EXAMPLE OF TABLE0");

		table.getRow(1).getCell(0).setText("EXAMPLE OF TABLE1");
		table.getRow(1).getCell(1).setText("only text1");
		table.getRow(1).getCell(2).setText("More text1");

		table.getRow(2).getCell(0).setText("EXAMPLE OF TABLE2");
		table.getRow(2).getCell(1).setText("only text2");
		table.getRow(2).getCell(2).setText("More text2");

		// second attempt
		XWPFTable t2 = document.createTable(2, 2);

		XWPFTableRow tableOneRowOne = t2.getRow(0);
		tableOneRowOne.getCell(0).setText(" Author1: ");
		tableOneRowOne.getCell(1).setText(" Author2: ");

		XWPFTableRow tableOneRowTwo = t2.getRow(1);
		tableOneRowTwo.getCell(0).setText(" Roger Nichols ");
		tableOneRowTwo.getCell(1).setText(" Roger M. Nichols ");

		FileOutputStream out = new FileOutputStream(new File(fileName2));
		document.write(out);
		out.close();

	}

}
