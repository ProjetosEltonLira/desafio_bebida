package br.com.portifolio.ProjetoBebidas.Repository;

import br.com.portifolio.ProjetoBebidas.Model.entities.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BebidaRepository extends JpaRepository<Bebida,Long> {
}
