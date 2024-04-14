package com.caxaprego.api.controller;

import com.caxaprego.api.repositories.ArmarioRepository;
import com.caxaprego.api.dtos.ArmarioDto;
import com.caxaprego.api.models.Armario;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ArmarioController {

    @Autowired
    ArmarioRepository armarioRepository;

    @PostMapping("/armarios")
    public ResponseEntity<Armario> save(@RequestBody @Valid ArmarioDto dto){
        var obj = new Armario();
        BeanUtils.copyProperties(dto, obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(armarioRepository.save(obj));
    }

    @GetMapping("/armarios")
    public ResponseEntity<List<Armario>> getAll(){
        List<Armario> list = armarioRepository.findAll();
        if(!list.isEmpty()){
            for(Armario obj: list){
                UUID id = obj.getId();
                obj.add(linkTo(methodOn(ArmarioController.class).getOne(id)).withSelfRel());

            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/armarios/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") UUID id){
        Optional<Armario> op = armarioRepository.findById(id);
        if(op.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        op.get().add(linkTo(methodOn(ArmarioController.class).getAll()).withRel("Product List"));
        return ResponseEntity.status(HttpStatus.OK).body(op.get());
    }

    @PutMapping("/armarios/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid ArmarioDto dto){
        Optional<Armario> op = armarioRepository.findById(id);
        if(op.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var obj = op.get();
        BeanUtils.copyProperties(dto, obj);
        return ResponseEntity.status(HttpStatus.OK).body(armarioRepository.save(obj));
    }

    @DeleteMapping("/armarios/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id){
        Optional<Armario> op = armarioRepository.findById(id);
        if(op.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        armarioRepository.delete(op.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

}
