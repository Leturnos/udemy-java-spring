package com.udemy.spring_boot.file.importer.contract;

import com.udemy.spring_boot.data.dto.PersonDTO;

import java.io.InputStream;
import java.util.List;

public interface FileImporter {

    List<PersonDTO> importFile(InputStream inputStream) throws Exception;
}
