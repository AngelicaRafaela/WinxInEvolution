package br.com.Winx.repositories;

import br.com.Winx.model.TransfModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransfRepository extends JpaRepository<TransfModel, Integer> {
    public Page<TransfModel> findAll(Pageable pageable);

    public Page<TransfModel> findByNameStartsWithIgnoreCase(String name, Pageable pageable);
}
