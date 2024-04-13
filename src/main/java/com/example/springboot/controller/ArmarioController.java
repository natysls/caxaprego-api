package com.example.springboot.controller;

import com.example.springboot.dtos.ArmarioDto;
import com.example.springboot.models.Armario;
import com.example.springboot.repositories.ArmarioRepository;
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
    public ResponseEntity<Armario> saveArmario(@RequestBody @Valid ArmarioDto armarioDto){
        var armario = new Armario();
        BeanUtils.copyProperties(armarioDto, armario);
        return ResponseEntity.status(HttpStatus.CREATED).body(armarioRepository.save(armario));
    }

    @GetMapping("/armarios")
    public ResponseEntity<List<Armario>> getAllArmarios(){
        List<Armario> armarioList = armarioRepository.findAll();
        if(!armarioList.isEmpty()){
            for(Armario armario: armarioList){
                UUID id = armario.getId();
                armario.add(linkTo(methodOn(ArmarioController.class).getOneArmario(id)).withSelfRel());

            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(armarioList);
    }

    @GetMapping("/armarios/{id}")
    public ResponseEntity<Object> getOneArmario(@PathVariable(value = "id") UUID id){
        Optional<Armario> armarioOp = armarioRepository.findById(id);
        if(armarioOp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        armarioOp.get().add(linkTo(methodOn(ArmarioController.class).getAllArmarios()).withRel("Product List"));
        return ResponseEntity.status(HttpStatus.OK).body(armarioOp.get());
    }

    @PutMapping("/armarios/{id}")
    public ResponseEntity<Object> updateArmario(@PathVariable(value = "id") UUID id, @RequestBody @Valid ArmarioDto armarioDto){
        Optional<Armario> armarioOp = armarioRepository.findById(id);
        if(armarioOp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var armario = armarioOp.get();
        BeanUtils.copyProperties(armarioDto, armario);
        return ResponseEntity.status(HttpStatus.OK).body(armarioRepository.save(armario));
    }

    @DeleteMapping("/armarios/{id}")
    public ResponseEntity<Object> deleteArmario(@PathVariable(value = "id") UUID id){
        Optional<Armario> armarioOp = armarioRepository.findById(id);
        if(armarioOp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        armarioRepository.delete(armarioOp.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

}
