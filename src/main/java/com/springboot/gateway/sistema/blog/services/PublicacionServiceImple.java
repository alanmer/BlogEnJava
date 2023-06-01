package com.springboot.gateway.sistema.blog.services;

import com.springboot.gateway.sistema.blog.dto.PublicacionDto;
import com.springboot.gateway.sistema.blog.entidades.Publicacion;
import com.springboot.gateway.sistema.blog.exceptions.ResourceNotFountException;
import com.springboot.gateway.sistema.blog.repositorio.IPublicacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServiceImple implements IPublicacionService {


    /*  @Override
     public PublicacionDto crearPublicacion(PublicacionDto publicacionDto) {
        //Convertir dto a entidad
         Publicacion publicacion = new Publicacion();
         publicacion.setTitulo(publicacionDto.getTitulo());
         publicacion.setDescription(publicacionDto.getDescription());
         publicacion.setContenido(publicacionDto.getContenido());

         Publicacion nuevaPublicacion = publicacionRepositorio.save(publicacion);
          //convertir de entidad a dto

         PublicacionDto publicacionRespuesta = new PublicacionDto();

         publicacionRespuesta.setId(nuevaPublicacion.getId());
         publicacionRespuesta.setTitulo(nuevaPublicacion.getTitulo());
         publicacionRespuesta.setDescription(nuevaPublicacion.getDescription());
         publicacionRespuesta.setContenido(nuevaPublicacion.getContenido());

         return publicacionRespuesta;
     }
 */
    @Autowired
    private IPublicacionRepositorio publicacionRepositorio;

    @Override
    public PublicacionDto crearPublicacion(PublicacionDto publicacionDto) {
        Publicacion publicacion = mapearEntidad(publicacionDto);

        Publicacion nuevaPublicacion = publicacionRepositorio.save(publicacion);

        PublicacionDto publicacionRespuesta = mapearDto(nuevaPublicacion);
        return publicacionRespuesta;
    }

    @Override
    public List<PublicacionDto> obtenerTodasLasPublicaciones(int numeroDePagina,int medidaDePagina) {
        Pageable pageable = PageRequest.of(numeroDePagina,medidaDePagina);
        Page<Publicacion> publicacions=publicacionRepositorio.findAll(pageable);

        List<Publicacion> listaDePublicaciones= publicacions.getContent();
        return listaDePublicaciones.stream().map(publicacion -> mapearDto(publicacion)).collect(Collectors.toList());
    }

    @Override
    public PublicacionDto obtenerPublicacionporId(Long id) {
        Publicacion publicacion = publicacionRepositorio
                .findById(id).orElseThrow(() -> new ResourceNotFountException("Publicacion", "id", id));
        return mapearDto(publicacion);
    }

    @Override
    public PublicacionDto actualizarPublicacion(PublicacionDto publicacionDto, long id) {
        Publicacion publicacion = publicacionRepositorio
                .findById(id)
                .orElseThrow(() -> new ResourceNotFountException("Publicacion", "id", id));
        publicacion.setTitulo(publicacionDto.getTitulo());
        publicacion.setDescription(publicacionDto.getDescription());
        publicacion.setContenido(publicacionDto.getContenido());
        Publicacion publicacionActualizada=publicacionRepositorio.save(publicacion);
        return mapearDto(publicacionActualizada);
    }

    @Override
    public void eliminarPublicacion(long id) {
        Publicacion publicacion = publicacionRepositorio
                .findById(id)
                .orElseThrow(() -> new ResourceNotFountException("Publicacion", "id", id));
        publicacionRepositorio.delete(publicacion);
    }

    //convierte de entidad a Dto
    private PublicacionDto mapearDto(Publicacion publicacion) {
        PublicacionDto publicacionDto = new PublicacionDto();
        publicacionDto.setId(publicacion.getId());
        publicacionDto.setTitulo(publicacion.getTitulo());
        publicacionDto.setDescription(publicacion.getDescription());
        publicacionDto.setContenido(publicacion.getContenido());
        return publicacionDto;
    }

    //Convierte de dto a entidad
    private Publicacion mapearEntidad(PublicacionDto publicacionDto) {
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(publicacionDto.getTitulo());
        publicacion.setDescription(publicacionDto.getDescription());
        publicacion.setContenido(publicacionDto.getContenido());

        return publicacion;
    }


}
