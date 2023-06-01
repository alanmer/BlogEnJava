package com.springboot.gateway.sistema.blog.services;

import com.springboot.gateway.sistema.blog.dto.ComentarioDTO;

public interface IComentariosService {
    public ComentarioDTO crearComentario(long publicacionId,ComentarioDTO comentarioDTO);
}
