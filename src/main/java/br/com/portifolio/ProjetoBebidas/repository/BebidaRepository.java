package br.com.portifolio.ProjetoBebidas.repository;

import br.com.portifolio.ProjetoBebidas.model.entities.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BebidaRepository extends JpaRepository<Bebida,Long> {
}
