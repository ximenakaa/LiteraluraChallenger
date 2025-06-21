package com.ximenkaa.literalura.controller;


import com.ximenkaa.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/literalura")
public class LibroController {
    @Autowired

    private LibroRepository bookRepository;

}

//