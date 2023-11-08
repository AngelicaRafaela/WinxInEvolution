package br.com.angin.winxinevolution.controller;

import br.com.angin.winxinevolution.dto.EnchantDTO;
import br.com.angin.winxinevolution.service.EnchantService;
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
@RequestMapping("/api/enchantix")
@Tag(name = "Enchantix", description = "This endpoint manages Enchantix")
public class EnchantixController {
    @Autowired
    private EnchantixService service;

    @PostMapping
    @Operation(summary = "Persists a new Enchantix in database", tags = {"Enchantix"}, responses = {
            @ApiResponse(description = "Success!", responseCode = "200", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EnchantixDTO.class))
            })
    })
    public EnchantixDTO create(@RequestBody EnchantixDTO dto){
        return service.create(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a Enchantix using the ID", tags = {"Enchantix"}, responses = {
            @ApiResponse(description = "Success!", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema =
                    @Schema(implementation = EnchantixDTO.class)
                    )
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
    })
    public EnchantixDTO findById(@PathVariable("id") int id){
        EnchantixDTO dto = service.findById(id);
        //..adding HATEOAS link
        buildEntityLink(dto);
        return dto;
    }

    @GetMapping
    public ResponseEntity<PagedModel<EnchantixDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<EnchantixDTO> assembler
    ){
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        Page<EnchantixDTO> Enchantixs = service.findAll(pageable);

        for (EnchantixDTO Enchantix:Enchantixs){
            buildEntityLink(Enchantix);
        }
        return new ResponseEntity(assembler.toModel(Enchantixs), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<PagedModel<EnchantixDTO>> findByName(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<EnchantixDTO> assembler
    ){
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        Page<EnchantixDTO> Enchantixs = service.findByName(name, pageable);

        for (EnchantixDTO Enchantix:Enchantixs){
            buildEntityLink(Enchantix);
        }
        return new ResponseEntity(assembler.toModel(Enchantixs), HttpStatus.OK);
    }

    @PutMapping
    public EnchantixDTO update(@RequestBody EnchantixDTO dto){
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        EnchantixDTO dto = service.findById(id);
        service.delete(dto);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    public void buildEntityLink(EnchantixDTO enchantix){
        //..self link
        enchantix.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                this.getClass()
                        ).findById(enchantix.getId())
                ).withSelfRel()
        );
    }
}