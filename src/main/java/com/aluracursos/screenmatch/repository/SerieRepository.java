package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.dto.EpisodioDTO;
import com.aluracursos.screenmatch.model.Categoria;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository <Serie, Long> {

    //Ejemplo de derivesQuerry
    Optional<Serie> findByTituloContainsIgnoreCase(String nombreSerie);

    List<Serie> findTop5ByOrderByEvaluacionDesc();

    List<Serie> findByGenero(Categoria categoria);


    /*Es la misma consulta con distinta metodologia, derivesQuerry, query nativa y y JPQL*/

    //List<Serie> findByTotalTemporadasLessThanEqualAndEvaluacionGreaterThanEqual(int totalTemporadas, Double evaluaciones);

    //Ejemplo de query nativa (cpnsulta a nive de base de datos)
    //@Query(value = "SELECT * FROM series WHERE series.total_temporadas <= 6 AND series.evaluacion >= 7.5", nativeQuery = true)
    //List<Serie> seriesPorTemporadaYEvaluacion();

    //Ejemplo de JPQL
    @Query("SELECT s FROM Serie  s WHERE s.totalTemporadas <= :totalTemporadas AND s.evaluacion >= :evaluaciones")
    List<Serie> seriesPorTemporadaYEvaluacion(int totalTemporadas, Double evaluaciones);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:nombreEpisodio%")
    List<Episodio> episodiosPorNombre(String nombreEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.evaluacion DESC LIMIT 5")
    List<Episodio> top5Episodios(Serie serie);

    @Query("SELECT s FROM Serie s " + "JOIN s.episodios e " + "GROUP BY s " + "ORDER BY MAX (e.fechaDeLanzamiento) DESC LIMIT 5")
    List<Serie> lanzamientosMasRecientes();

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :id AND e.temporada = :numeroDeTemporada")
    List<Episodio> obtenerTemporadaPorNumero(Long id, Long numeroDeTemporada);
}
