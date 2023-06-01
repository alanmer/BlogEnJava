package com.springboot.gateway.sistema.blog.repositorio;

import com.springboot.gateway.sistema.blog.entidades.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPublicacionRepositorio extends JpaRepository <Publicacion,Long> {


}
