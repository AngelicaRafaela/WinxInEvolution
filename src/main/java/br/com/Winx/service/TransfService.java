package br.com.Winx.service;

import br.com.Winx.dto.TransfDTO;
import br.com.Winx.mapper.CustomModelMapper;
import br.com.Winx.model.TransfModel;
import br.com.Winx.repositories.TransfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransfService {
    @Autowired
    private TransfRepository repository;

    public TransfDTO create(TransfDTO dto){
        TransfModel model = CustomModelMapper.parseObject(dto, TransfModel.class);
        return CustomModelMapper.parseObject(repository.save(model), TransfDTO.class);
    }

    public TransfDTO findById(int id){
        TransfModel model = repository.findById(id).orElseThrow(
                ()-> new br.com.Winx.exception.ResourceNotFoundException(null));
        return CustomModelMapper.parseObject(model, TransfDTO.class);
    }

    public Page<TransfDTO> findAll(Pageable pageable){
        var page = repository.findAll(pageable);
        return page.map(p -> CustomModelMapper.parseObject(p, TransfDTO.class));
    }

    public TransfDTO update(TransfDTO dto){
        TransfModel model = repository.findById(dto.getId()).orElseThrow(
                () -> new br.com.Winx.exception.ResourceNotFoundException(null));
        model = CustomModelMapper.parseObject(dto, TransfModel.class);
        return CustomModelMapper.parseObject(repository.save(model), TransfDTO.class);
    }

    public void delete(TransfDTO dto){
        TransfModel model = repository.findById(dto.getId()).orElseThrow(
                () -> new br.com.Winx.exception.ResourceNotFoundException(null)
        );
        repository.delete(model);
    }

    public Page<TransfDTO> findByName(String name, Pageable pageable){
        var page = repository.findByNameStartsWithIgnoreCase(name, pageable);
        return page.map(p -> CustomModelMapper.parseObject(p, TransfDTO.class));
    }
}