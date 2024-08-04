package br.com.portifolio.ProjetoBebidas.repository;

import br.com.portifolio.ProjetoBebidas.model.entities.BebidaSecaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecaoBebidaRepository extends JpaRepository<BebidaSecaoEntity,Long> {
}
