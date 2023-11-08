package br.com.angin.winxinevolution.service;

import br.com.angin.winxinevolution.dto.EnchantixDTO;
import br.com.angin.winxinevolution.exception.ResourceNotFoundException;
import br.com.angin.winxinevolution.mapper.EnchantModelMapper;
import br.com.angin.winxinevolution.model.EnchantixModel;
import br.com.angin.winxinevolution.repository.FairyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EnchantService {

    @Autowired
    private FairyRepository repository;

    public EnchantixDTO create(EnchantixDTO dto){
        EnchantixModel model = EnchantModelMapper.parseObject(dto, EnchantixModel.class);
        return EnchantModelMapper.parseObject(repository.save(model), EnchantixDTO.class);
    }

    public EnchantixDTO findById(int id){
        EnchantixModel model = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(null));
        return EnchantModelMapper.parseObject(model, EnchantixDTO.class);
    }

    public Page<EnchantixDTO> findAll(Pageable pageable){
        var page = repository.findAll(pageable);
        return page.map(p -> EnchantModelMapper.parseObject(p, EnchantixDTO.class));
    }

    public EnchantixDTO update(EnchantixDTO dto){
        EnchantixModel model = repository.findById(dto.getId()).orElseThrow(
                () -> new ResourceNotFoundException(null));
        model = EnchantModelMapper.parseObject(dto, EnchantixModel.class);
        return EnchantModelMapper.parseObject(repository.save(model), EnchantixDTO.class);
    }

    public void delete(EnchantixDTO dto){
        EnchantixModel model = repository.findById(dto.getId()).orElseThrow(
                () -> new ResourceNotFoundException(null)
        );
        repository.delete(model);
    }

    public Page<EnchantixDTO> findByName(String name, Pageable pageable){
        var page = repository.findByFirstNameStartsWithIgnoreCase(name, pageable);
        return page.map(p -> EnchantModelMapper.parseObject(p, EnchantixDTO.class));
    }

}
