package br.com.portifolio.ProjetoBebidas.repository;

import br.com.portifolio.ProjetoBebidas.model.entities.SecaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecaoRepository extends JpaRepository<SecaoEntity,Long> {
}
