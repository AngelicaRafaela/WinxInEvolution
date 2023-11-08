package br.com.angin.winxinevolution.service;

import br.com.angin.winxinevolution.dto.EnchantixDTO;
import br.com.angin.winxinevolution.mapper.CustomModelMapper;
import br.com.angin.winxinevolution.model.EnchantixModel;
import br.com.angin.winxinevolution.repositories.EnchantixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EnchantixService {
    @Autowired
    private EnchantixRepository repository;

    public EnchantixDTO create(EnchantixDTO dto){
        EnchantixModel model = CustomModelMapper.parseObject(dto, EnchantixModel.class);
        return CustomModelMapper.parseObject(repository.save(model), EnchantixDTO.class);
    }

    public EnchantixDTO findById(int id){
        EnchantixModel model = repository.findById(id).orElseThrow(
                ()-> new br.com.angin.winxinevolution.exception.ResourceNotFoundException(null));
        return CustomModelMapper.parseObject(model, EnchantixDTO.class);
    }

    public Page<EnchantixDTO> findAll(Pageable pageable){
        var page = repository.findAll(pageable);
        return page.map(p -> CustomModelMapper.parseObject(p, EnchantixDTO.class));
    }

    public EnchantixDTO update(EnchantixDTO dto){
        EnchantixModel model = repository.findById(dto.getId()).orElseThrow(
                () -> new br.com.angin.winxinevolution.exception.ResourceNotFoundException(null));
        model = CustomModelMapper.parseObject(dto, EnchantixModel.class);
        return CustomModelMapper.parseObject(repository.save(model), EnchantixDTO.class);
    }

    public void delete(EnchantixDTO dto){
        EnchantixModel model = repository.findById(dto.getId()).orElseThrow(
                () -> new br.com.angin.winxinevolution.exception.ResourceNotFoundException(null)
        );
        repository.delete(model);
    }

    public Page<EnchantixDTO> findByName(String name, Pageable pageable){
        var page = repository.findByNameStartsWithIgnoreCase(name, pageable);
        return page.map(p -> CustomModelMapper.parseObject(p, EnchantixDTO.class));
    }
}