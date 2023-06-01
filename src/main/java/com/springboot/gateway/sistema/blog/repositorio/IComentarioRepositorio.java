package com.springboot.gateway.sistema.blog.repositorio;

import com.springboot.gateway.sistema.blog.entidades.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IComentarioRepositorio extends JpaRepository<Comentarios,Long> {


}
