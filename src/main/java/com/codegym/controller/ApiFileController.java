package com.codegym.controller;

import com.codegym.model.Book;
import com.codegym.model.FileForm;
import com.codegym.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class ApiFileController {
    @Autowired
    private BookService bookService;

    @Autowired
    Environment env;

    @PostMapping(value = "/api/file/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<Void> uploadFile(@ModelAttribute FileForm fileForm, BindingResult result, @PathVariable Long id) {
        if(result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        MultipartFile multipartFile = fileForm.getFile();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();

        try {
            FileCopyUtils.copy(fileForm.getFile().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Book book = bookService.findById(id);
        book.setImage(fileName);
        bookService.save(book);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
