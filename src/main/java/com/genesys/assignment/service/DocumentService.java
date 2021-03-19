package com.genesys.assignment.service;

import com.genesys.assignment.model.DataFilter;
import com.genesys.assignment.model.Document;
import com.genesys.assignment.repository.DocumentRepository;
import com.genesys.assignment.util.CSV;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DocumentService {

    @Value("${read.file.location}")
    private String folderLocation;

    @Autowired
    private DocumentRepository documentRepository;

    public enum Param {
        EVEN,
        ODD,
        ALL
    }

    private Param param ;

    public Document settingParameter(DataFilter dataFilter) throws Exception {
        if (dataFilter == null){
           this.param = Param.ALL;
        }else if (dataFilter.getEvenRecords() != null){
            this.param = Param.EVEN;
        }else if (dataFilter.getOddRecords() != null ){
            this.param = Param.ODD;
        }

        return filterAndPopulateJson();
    }
    public List<Path> loadCSVFile() throws Exception {
        List<Path> result;
        Stream<Path> walk = Files.walk(Paths.get(folderLocation));
        result = walk
                .filter(Files::isRegularFile)   // is a file
                .filter(p -> p.getFileName().toString().endsWith(".csv"))
                .collect(Collectors.toList());


        return result;
    }

    public Document filterAndPopulateJson() throws Exception {
        //walk over the csv file and add it to the list.
        List<Path> files = this.loadCSVFile();

        if (files.size() == 0){
            throw new Exception("No File found");
        }

        //stream the csv file
        try (InputStream in = new FileInputStream(files.get(0).toString());) {
            CSV csv = new CSV(true, ',', in);
            List<String> fieldNames = null;

            if (csv.hasNext()) fieldNames = new ArrayList<>(csv.next());
            List<Map<String, String>> list = new ArrayList<>();

            //maintaining index for filtering as per parameter
            Integer count = 1;
            while (csv.hasNext()) {
                switch (this.param){
                    case EVEN:
                        if(count%2==0) {
                            List<String> x = csv.next();
                            Map<String, String> obj = new LinkedHashMap<>();
                            for (int i = 0; i < fieldNames.size(); i++) {
                                obj.put(fieldNames.get(i), x.get(i));
                            }
                            list.add(obj);
                        }
                        break;
                    case ODD:
                        if(count%2==1) {
                            List<String> x = csv.next();
                            Map<String, String> obj = new LinkedHashMap<>();
                            for (int i = 0; i < fieldNames.size(); i++) {
                                obj.put(fieldNames.get(i), x.get(i));
                            }
                            list.add(obj);
                        }
                        break;
                    case ALL:
                        List<String> x = csv.next();
                        Map<String, String> obj = new LinkedHashMap<>();
                        for (int i = 0; i < fieldNames.size(); i++) {
                            obj.put(fieldNames.get(i), x.get(i));
                        }
                        list.add(obj);
                        break;
                }

                count = count + 1;
            }

            return this.saveToMongo(list);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEncodingException("Error Parsing Csv File.");
        } catch (IOException e) {
            throw new IOException("Error opening file.");
        }
    }

    private Document saveToMongo(List<Map<String, String>> list){
        Document document = new Document("test",list);
        documentRepository.save(document);
        return document;
    }
}
