package com.udemy.spring_boot.file.importer.impl;

import com.udemy.spring_boot.data.dto.PersonDTO;
import com.udemy.spring_boot.file.importer.contract.FileImporter;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class CsvImporter implements FileImporter {

    @Override
    public List<PersonDTO> importFile(InputStream inputStream) throws Exception {
        return List.of();
    }
}
