package br.com.angin.winxinevolution.repository;

import br.com.angin.winxinevolution.model.EnchantixModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnchantixRepository extends JpaRepository<EnchantixModel, Integer> {
    public Page<EnchantixModel> findAll(Pageable pageable);

    public Page<EnchantixModel> findByNameStartsWithIgnoreCase(String name, Pageable pageable);
}


