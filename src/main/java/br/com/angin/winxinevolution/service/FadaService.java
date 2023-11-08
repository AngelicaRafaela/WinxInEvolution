package br.com.angin.winxinevolution.service;

import br.com.angin.winxinevolution.dto.FadaDTO;
import br.com.angin.winxinevolution.mapper.CustomModelMapper;
import br.com.angin.winxinevolution.model.FadaModel;
import br.com.angin.winxinevolution.repositories.FadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class FadaService {
    @Autowired
    private FadaRepository repository;

    public FadaDTO create(FadaDTO dto){
        FadaModel model = CustomModelMapper.parseObject(dto, FadaModel.class);
        return CustomModelMapper.parseObject(repository.save(model), FadaDTO.class);
    }

    public FadaDTO findById(int id){
        FadaModel model = repository.findById(id).orElseThrow(
                ()-> new br.com.angin.winxinevolution.exception.ResourceNotFoundException(null));
        return CustomModelMapper.parseObject(model, FadaDTO.class);
    }

    public Page<FadaDTO> findAll(Pageable pageable){
        var page = repository.findAll(pageable);
        return page.map(p -> CustomModelMapper.parseObject(p, FadaDTO.class));
    }

    public FadaDTO update(FadaDTO dto){
        FadaModel model = repository.findById(dto.getId()).orElseThrow(
                () -> new br.com.angin.winxinevolution.exception.ResourceNotFoundException(null));
        model = CustomModelMapper.parseObject(dto, FadaModel.class);
        return CustomModelMapper.parseObject(repository.save(model), FadaDTO.class);
    }

    public void delete(FadaDTO dto){
        FadaModel model = repository.findById(dto.getId()).orElseThrow(
                () -> new br.com.angin.winxinevolution.exception.ResourceNotFoundException(null)
        );
        repository.delete(model);
    }

    public Page<FadaDTO> findByName(String name, Pageable pageable){
        var page = repository.findByNameStartsWithIgnoreCase(name, pageable);
        return page.map(p -> CustomModelMapper.parseObject(p, FadaDTO.class));
    }
}
