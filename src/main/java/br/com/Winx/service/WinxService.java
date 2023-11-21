package br.com.Winx.service;

import br.com.Winx.dto.WinxDTO;
import br.com.Winx.mapper.CustomModelMapper;
import br.com.Winx.model.WinxModel;
import br.com.Winx.repositories.WinxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class WinxService {
    @Autowired
    private WinxRepository repository;

    public WinxDTO create(WinxDTO dto){
        WinxModel model = CustomModelMapper.parseObject(dto, WinxModel.class);
        return CustomModelMapper.parseObject(repository.save(model), WinxDTO.class);
    }

    public WinxDTO findById(int id){
        WinxModel model = repository.findById(id).orElseThrow(
                ()-> new br.com.Winx.exception.ResourceNotFoundException(null));
        return CustomModelMapper.parseObject(model, WinxDTO.class);
    }

    public Page<WinxDTO> findAll(Pageable pageable){
        var page = repository.findAll(pageable);
        return page.map(p -> CustomModelMapper.parseObject(p, WinxDTO.class));
    }

    public WinxDTO update(WinxDTO dto){
        WinxModel model = repository.findById(dto.getId()).orElseThrow(
                () -> new br.com.Winx.exception.ResourceNotFoundException(null));
        model = CustomModelMapper.parseObject(dto, WinxModel.class);
        return CustomModelMapper.parseObject(repository.save(model), WinxDTO.class);
    }

    public void delete(WinxDTO dto){
        WinxModel model = repository.findById(dto.getId()).orElseThrow(
                () -> new br.com.Winx.exception.ResourceNotFoundException(null)
        );
        repository.delete(model);
    }

    public Page<WinxDTO> findByName(String name, Pageable pageable){
        var page = repository.findByNameStartsWithIgnoreCase(name, pageable);
        return page.map(p -> CustomModelMapper.parseObject(p, WinxDTO.class));
    }
}
