package com.genesys.assignment.controller;

import com.genesys.assignment.model.DataFilter;
import com.genesys.assignment.model.Document;
import com.genesys.assignment.service.DocumentService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@WebMvcTest
public class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentService documentService;


    @Test
    public void whenProcessingCsvFile() throws Exception{
        List<Map<String,String>> payload = new ArrayList<>();
        Document document = new Document();
        document.setName("test");
        document.getCsvPayload();

//        Mockito.when(documentService.settingParameter(null)).thenReturn(document);
    }
}
