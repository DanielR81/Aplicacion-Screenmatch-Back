package com.aluracursos.screenmatch.controller;

import com.aluracursos.screenmatch.dto.EpisodioDTO;
import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.repository.SerieRepository;
import com.aluracursos.screenmatch.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Esta anotación le indica a Spring que este controlador está destinado
 * a manejar peticiones HTTP (como GET, POST, etc.)
 */

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SeriesService servicio;

    @GetMapping
    public List<SerieDTO> obtenerTodasLasSeries(){
       return servicio.obtenerTodasLasSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obtenerTop5Series() {
        return servicio.obtenerTop5();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> obtenerLanzameintos() {
        return servicio.obtenerLanzamientosMasRecientes();
    }

    //utilizamos {} porque decimos que es un valor dinamico
    // y el @PathVariable para decir que el valor nos lo va a proporcionar la URL
    @GetMapping("/{id}")
    public SerieDTO obtenerPorId(@PathVariable Long id) {

        return servicio.obtenerPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obtenerTodasLasTemporadas(@PathVariable Long id){
            return servicio.obtenerTodasLasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numeroTemporada}")
    public List<EpisodioDTO> obtenerEpisodiosDeTemporadas(@PathVariable Long id, @PathVariable Long numeroTemporada){
        return servicio.obtenerEpisodiosDeTemporada(id , numeroTemporada);
    }

    @GetMapping("/categoria/{nombreGenero}")
    public List<SerieDTO> obtenerSeriesPorCategoria(@PathVariable String nombreGenero){
        return servicio.obtenerSeriesPorCategoria(nombreGenero);
    };

    
}
