package com.udemy.spring_boot.file.importer.impl;

import com.udemy.spring_boot.data.dto.PersonDTO;
import com.udemy.spring_boot.file.importer.contract.FileImporter;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class XlsxImporter implements FileImporter {

    @Override
    public List<PersonDTO> importFile(InputStream inputStream) throws Exception {

        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) rowIterator.next(); // se houver header

            return parseRowsToPersonDTOList(rowIterator);
        }
    }

    private List<PersonDTO> parseRowsToPersonDTOList(Iterator<Row> rowIterator) {
        List<PersonDTO> people = new ArrayList<>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (isRowValid(row)) {
                people.add(parseRowToPersonDTO(row));
            }
        }

        return people;
    }

    private PersonDTO parseRowToPersonDTO(Row row) {
        DataFormatter formatter = new DataFormatter();
        PersonDTO person = new PersonDTO();

        // formatter.formatCellValue(cell) faz retornar "" se a célula for nula
        person.setFirstName(formatter.formatCellValue(row.getCell(0)));
        person.setLastName(formatter.formatCellValue(row.getCell(1)));
        person.setAddress(formatter.formatCellValue(row.getCell(2)));
        person.setGender(formatter.formatCellValue(row.getCell(3)));
        person.setEnabled(true);

        return person;
    }

    private static boolean isRowValid(Row row) {
        return row.getCell(0) != null && row.getCell(0).getCellType() != CellType.BLANK;
    }
}
