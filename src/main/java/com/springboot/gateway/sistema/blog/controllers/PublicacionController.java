package com.springboot.gateway.sistema.blog.controllers;

import com.springboot.gateway.sistema.blog.dto.PublicacionDto;
import com.springboot.gateway.sistema.blog.services.IPublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {
    @Autowired
    public IPublicacionService publicacionService;

    @GetMapping()
    public List<PublicacionDto> listarPublicaciones(
            @RequestParam(value = "NoPage", defaultValue = "0", required = false) int numeroDePagina,
            @RequestParam(value = "PageSize", defaultValue = "10", required = false) int medidaDePagina) {
        return publicacionService.obtenerTodasLasPublicaciones(numeroDePagina,medidaDePagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDto> obtenerPublicacionPorId(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(publicacionService.obtenerPublicacionporId(id));
    }

    @PostMapping
    public ResponseEntity<PublicacionDto> guardarPublicacion(@RequestBody PublicacionDto publicacionDto) {
        return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDto> actualizarPublicacion(@RequestBody PublicacionDto publicacionDto, @PathVariable(name = "id") long id) {
        PublicacionDto publicacionRespuesta = publicacionService.actualizarPublicacion(publicacionDto, id);
        return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") long id) {
        publicacionService.eliminarPublicacion(id);
        return new ResponseEntity<>("Publicación eliminada con exito", HttpStatus.OK);


    }
}
