package ma.gov.prefagadir.application.backend.utils;

import ma.gov.prefagadir.application.backend.models.Citoyen;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CitoyenExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<Citoyen> listCitoyens;

    public CitoyenExcelExporter(List<Citoyen> listCitoyens) {
        this.listCitoyens = listCitoyens;
        workbook = new XSSFWorkbook();
    }

    public void writeHeaderLine(){
        sheet = workbook.createSheet("Citoyens");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(13);
        style.setFont(font);

        createCell(row, 0, "CIN", style);
        createCell(row, 1, "Nom (Fr)", style);
        createCell(row, 2, "Nom (Ar)", style);
        createCell(row, 3, "Prénom (Fr)", style);
        createCell(row, 4, "Prénom (Ar)", style);
        createCell(row, 5, "Date de naissance", style);
        createCell(row, 6, "Lieu de naissance", style);
        createCell(row, 7, "Situation familiale", style);
        createCell(row, 8, "Profession", style);




    }

    public void createCell(Row row, int columnCount, Object value, CellStyle style){
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if(value instanceof Date){
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormatter.format(new Date());
            cell.setCellValue((String) date);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(11);
        style.setFont(font);

        for (Citoyen citoyen : listCitoyens) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, citoyen.getCin(), style);
            createCell(row, columnCount++, citoyen.getNomFr(), style);
            createCell(row, columnCount++, citoyen.getNomAr(), style);
            createCell(row, columnCount++, citoyen.getPrenomFr(), style);
            createCell(row, columnCount++, citoyen.getPrenomAr(), style);
            createCell(row, columnCount++, citoyen.getDateNaissance(), style);
            createCell(row, columnCount++, citoyen.getLieuNaissance(), style);
            createCell(row, columnCount++, citoyen.getSituationFamiliale(), style);
            createCell(row, columnCount++, citoyen.getProfession().getLabelFr(), style);


        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
