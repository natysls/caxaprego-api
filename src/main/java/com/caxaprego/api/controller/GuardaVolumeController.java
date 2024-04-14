package com.caxaprego.api.controller;

import com.caxaprego.api.dtos.GuardaVolumeDto;
import com.caxaprego.api.models.GuardaVolume;
import com.caxaprego.api.repositories.GuardaVolumeRepository;
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
public class GuardaVolumeController {

    @Autowired
    GuardaVolumeRepository guardaVolumeRepository;

    @PostMapping("/guarda-volumes")
    public ResponseEntity<GuardaVolume> save(@RequestBody @Valid GuardaVolumeDto dto){
        var obj = new GuardaVolume();
        BeanUtils.copyProperties(dto, obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardaVolumeRepository.save(obj));
    }

    @GetMapping("/guarda-volumes")
    public ResponseEntity<List<GuardaVolume>> getAll(){
        List<GuardaVolume> list = guardaVolumeRepository.findAll();
        if(!list.isEmpty()){
            for(GuardaVolume obj: list){
                UUID id = obj.getId();
                obj.add(linkTo(methodOn(GuardaVolumeController.class).getOne(id)).withSelfRel());

            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/guarda-volumes/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") UUID id){
        Optional<GuardaVolume> op = guardaVolumeRepository.findById(id);
        if(op.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        op.get().add(linkTo(methodOn(GuardaVolumeController.class).getAll()).withRel("Product List"));
        return ResponseEntity.status(HttpStatus.OK).body(op.get());
    }

    @PutMapping("/guarda-volumes/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid GuardaVolumeDto dto){
        Optional<GuardaVolume> op = guardaVolumeRepository.findById(id);
        if(op.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var obj = op.get();
        BeanUtils.copyProperties(dto, obj);
        return ResponseEntity.status(HttpStatus.OK).body(guardaVolumeRepository.save(obj));
    }

    @DeleteMapping("/guarda-volumes/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id){
        Optional<GuardaVolume> op = guardaVolumeRepository.findById(id);
        if(op.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        guardaVolumeRepository.delete(op.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }
}
