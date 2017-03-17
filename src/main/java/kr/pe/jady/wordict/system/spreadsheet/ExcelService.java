package kr.pe.jady.wordict.system.spreadsheet;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * ExcelService
 * 시스템 데이터를 Excel 형태로 upload/download 할때 사용하는 인터페이스
 *
 * //// abstract 에서는 createWorkBook("workbook", addSimpleHeader(createSheet("1st", someResultList), "header1", "header2"), addSimpleHeader(createSheet("2nd", anotherResultList), "header3", "header4")));
 * //// 요런 형태?!
 * @author jhlee7854
 */
public interface ExcelService {
    Sheet createSheet(String sheetName, List<?> dataList);
    Sheet addSimpleHeader(Sheet sheet, String ... headers);
    Sheet addCustomHeader(Sheet sheet);
    Workbook createWorkBook(String workbookName, Sheet ... sheets);
}
