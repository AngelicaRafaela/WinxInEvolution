package br.com.angin.winxinevolution.controller;

import br.com.angin.winxinevolution.dto.FadaDTO;
import br.com.angin.winxinevolution.service.FadaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fada")
@Tag(name = "Fada", description = "This endpoint manages Fada")
public class FadaController
{
    @Autowired
    private FadaService service;

    @PostMapping
    @Operation(summary = "Persists a new Fada in database", tags = {"Fada"}, responses = {
            @ApiResponse(description = "Success!", responseCode = "200", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FadaDTO.class))
            })
    })
    public FadaDTO create(@RequestBody FadaDTO dto){
        return service.create(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a Fada using the ID", tags = {"Fada"}, responses = {
            @ApiResponse(description = "Success!", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema =
                    @Schema(implementation = FadaDTO.class)
                    )
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
    })
    public FadaDTO findById(@PathVariable("id") int id){
        FadaDTO dto = service.findById(id);
        //..adding HATEOAS link
        buildEntityLink(dto);
        return dto;
    }

    @GetMapping
    public ResponseEntity<PagedModel<FadaDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<FadaDTO> assembler
    ){
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        Page<FadaDTO> Fadas = service.findAll(pageable);

        for (FadaDTO Fada:Fadas){
            buildEntityLink(Fada);
        }
        return new ResponseEntity(assembler.toModel(Fadas), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<PagedModel<FadaDTO>> findByName(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<FadaDTO> assembler
    ){
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        Page<FadaDTO> Fadas = service.findByName(name, pageable);

        for (FadaDTO Fada:Fadas){
            buildEntityLink(Fada);
        }
        return new ResponseEntity(assembler.toModel(Fadas), HttpStatus.OK);
    }

    @PutMapping
    public FadaDTO update(@RequestBody FadaDTO dto){
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        FadaDTO dto = service.findById(id);
        service.delete(dto);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    public void buildEntityLink(FadaDTO fada){
        //..self link
        fada.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                this.getClass()
                        ).findById(fada.getId())
                ).withSelfRel()
        );
    }
}
