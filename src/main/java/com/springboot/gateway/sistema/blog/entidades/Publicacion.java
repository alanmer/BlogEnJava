package com.springboot.gateway.sistema.blog.entidades;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publicaciones", uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo"})})
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "contenido", nullable = false)
    private String contenido;

    @OneToMany(mappedBy = "publicacion",cascade = CascadeType.ALL,orphanRemoval = true )
    private Set<Comentarios> comentarios=new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Publicacion(Long id, String titulo, String description, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.description = description;
        this.contenido = contenido;
    }

    public Publicacion() {

    }
}
