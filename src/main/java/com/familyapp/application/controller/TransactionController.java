package com.familyapp.application.controller;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.FamilyDto;
import com.familyapp.application.dto.TransactionDto;
import com.familyapp.application.entity.Category;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.User;
import com.familyapp.application.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/transaction")

public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @PostMapping("{handle}")
    public ResponseEntity<TransactionDto> handleNewTransaction(@RequestBody TransactionDto transactionDto ) {
        return new ResponseEntity<>(transactionService.createTransaction(transactionDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable("id") UUID transactionId){
        TransactionDto TransactionDto = transactionService.getTransactionbyId(transactionId);
        return ResponseEntity.ok(TransactionDto);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransaction(){
        List<TransactionDto> transactionDtos = transactionService.getAllTransaction();
        return ResponseEntity.ok(transactionDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<TransactionDto> updateTransaction( @RequestBody TransactionDto transactionDto,
                                                       @PathVariable("id") UUID TransactionId){
        return ResponseEntity.ok(transactionService.updateTransaction(TransactionId, transactionDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("id") UUID transactionId){
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.ok("Transaction deleted successfully");
    }
}
