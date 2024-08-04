package br.com.portifolio.ProjetoBebidas.repository;

import br.com.portifolio.ProjetoBebidas.model.entities.HistoricoEntity;
import br.com.portifolio.ProjetoBebidas.model.entities.TipoBebidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends JpaRepository<HistoricoEntity,Long> {
}
