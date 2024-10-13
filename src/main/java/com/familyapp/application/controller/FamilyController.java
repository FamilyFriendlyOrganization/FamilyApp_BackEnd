package com.familyapp.application.controller;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.FamilyDto;
import com.familyapp.application.entity.Account;
import com.familyapp.application.service.FamilyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/family")

public class FamilyController {
    @Autowired
    private FamilyService familyService;
    @PostMapping
    public ResponseEntity<FamilyDto> createFamily(@RequestBody FamilyDto familyDto, Account account) {
        return new ResponseEntity<>(familyService.createFamily(familyDto, account), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<FamilyDto> getFamilyById(@PathVariable("id") UUID FamilyId){
        FamilyDto FamilyDto = familyService.getFamilybyId(FamilyId);
        return ResponseEntity.ok(FamilyDto);
    }

    @GetMapping
    public ResponseEntity<List<FamilyDto>> getAllFamily(){
        List<FamilyDto> FamilyDtos = familyService.getAllFamily();
        return ResponseEntity.ok(FamilyDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<FamilyDto> updateFamily( @RequestBody FamilyDto FamilyDto,
                                                       @PathVariable("id") UUID FamilyId){
        return ResponseEntity.ok(familyService.updateFamily(FamilyId, FamilyDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFamily(@PathVariable("id") UUID FamilyId){
        familyService.deleteFamily(FamilyId);
        return ResponseEntity.ok("Family deleted successfully");
    }
}
