package br.com.angin.winxinevolution.service;

import br.com.angin.winxinevolution.dto.FairyDTO;
import br.com.angin.winxinevolution.exception.ResourceNotFoundException;
import br.com.angin.winxinevolution.mapper.FairyModelMapper;
import br.com.angin.winxinevolution.model.FairyModel;
import br.com.angin.winxinevolution.repository.FairyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FairyService {

    @Autowired
    private FairyRepository repository;

    public FairyDTO create(FairyDTO dto){
        FairyModel model = FairyModelMapper.parseObject(dto, FairyModel.class);
        return FairyModelMapper.parseObject(repository.save(model), FairyDTO.class);
    }

    public FairyDTO findById(int id){
        FairyModel model = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(null));
        return FairyModelMapper.parseObject(model, FairyDTO.class);
    }

    public Page<FairyDTO> findAll(Pageable pageable){
        var page = repository.findAll(pageable);
        return page.map(p -> FairyModelMapper.parseObject(p, FairyDTO.class));
    }

    public FairyDTO update(FairyDTO dto){
        FairyModel model = repository.findById(dto.getId()).orElseThrow(
                () -> new ResourceNotFoundException(null));
        model = FairyModelMapper.parseObject(dto, FairyModel.class);
        return FairyModelMapper.parseObject(repository.save(model), FairyDTO.class);
    }

    public void delete(FairyDTO dto){
        FairyModel model = repository.findById(dto.getId()).orElseThrow(
                () -> new ResourceNotFoundException(null)
        );
        repository.delete(model);
    }

    public Page<FairyDTO> findByName(String name, Pageable pageable){
        var page = repository.findByFirstNameStartsWithIgnoreCase(name, pageable);
        return page.map(p -> FairyModelMapper.parseObject(p, FairyDTO.class));
    }

}
