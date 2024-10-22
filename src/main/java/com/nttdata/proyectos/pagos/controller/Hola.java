package com.nttdata.proyectos.pagos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/holaMundo")
public class Hola {

    @GetMapping()
    public String holaMundo(){
        return "Hola Mundo";
    }
}
