package br.com.Winx.repositories;

import br.com.Winx.model.WinxModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinxRepository extends JpaRepository<WinxModel, Integer> {
    public Page<WinxModel> findAll(Pageable pageable);

    public Page<WinxModel> findByNameStartsWithIgnoreCase(String name, Pageable pageable);
}
