package com.genesys.assignment.service;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DocumentServiceTest {

    @InjectMocks
    private DocumentService documentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void whenFileDoesNotExists() throws Exception {
        //Given
        List<Path> dummy = new ArrayList<>();

        Mockito.when(documentService.loadCSVFile())
                .thenReturn(dummy);

        Assert.assertEquals(Optional.of(dummy.size()),0);

    }
}
