package com.genesys.assignment.controller;

import com.genesys.assignment.model.DataFilter;
import com.genesys.assignment.model.Document;
import com.genesys.assignment.repository.DocumentRepository;
import com.genesys.assignment.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/data")
public class DocumentController {

    @Autowired
    private DocumentService documentService;


    @PostMapping("/process-csv")
    public Document processCsv(@RequestBody(required = false) DataFilter dataFilter) throws Exception {
            return documentService.settingParameter(dataFilter);

    }




}
