package com.springboot.gateway.sistema.blog.services;

import com.springboot.gateway.sistema.blog.dto.PublicacionDto;

import java.util.List;

public interface IPublicacionService {
  public PublicacionDto crearPublicacion(PublicacionDto publicacionDto);

  public List<PublicacionDto> obtenerTodasLasPublicaciones(int numeroDePagina,int medidaDePagina);

  public PublicacionDto obtenerPublicacionporId(Long id);

  public PublicacionDto actualizarPublicacion(PublicacionDto publicacionDto, long id);

  public void  eliminarPublicacion(long id );
}
