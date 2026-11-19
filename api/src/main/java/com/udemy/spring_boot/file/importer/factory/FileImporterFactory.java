package com.udemy.spring_boot.file.importer.factory;

import com.udemy.spring_boot.exception.BadRequestException;
import com.udemy.spring_boot.file.importer.contract.FileImporter;
import com.udemy.spring_boot.file.importer.impl.CsvImporter;
import com.udemy.spring_boot.file.importer.impl.XlsxImporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component // Para permitir a injeção em outras classes
public class FileImporterFactory {
    private Logger logger = LoggerFactory.getLogger(FileImporterFactory.class);

    @Autowired
    private ApplicationContext context;

    public FileImporter getImporter(String fileName) throws Exception {
        if (fileName == null || fileName.isBlank()) {
            throw new BadRequestException("Invalid file name");
        }

        String normalizedFileName = fileName.toLowerCase(Locale.ROOT);
        if (normalizedFileName.endsWith(".xlsx")) {
            return context.getBean(XlsxImporter.class);
        }
        else if (normalizedFileName.endsWith(".csv")){
            return context.getBean(CsvImporter.class);
        }
        else {
            logger.info("Invalid file format: {}", fileName);
            throw new BadRequestException("Invalid file format: " + fileName);
        }
    }
}
