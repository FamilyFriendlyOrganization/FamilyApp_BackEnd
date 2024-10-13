package com.familyapp.application.service.impl;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.TransactionDto;
import com.familyapp.application.entity.*;
import com.familyapp.application.exception.ResourceNotFoundException;
import com.familyapp.application.mapper.AccountMapper;
import com.familyapp.application.mapper.TransactionMapper;
import com.familyapp.application.repository.CategoryRepository;
import com.familyapp.application.repository.FamilyRepository;
import com.familyapp.application.repository.TransactionRepository;
import com.familyapp.application.repository.UserRepository;
import com.familyapp.application.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    private CategoryRepository categoryRepository;
    private FamilyRepository familyRepository;
    private UserRepository userRepository;
    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto, Category category, Family family, User user) {
        Transaction transaction = TransactionMapper.toEntity(transactionDto, category, family, user);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return TransactionMapper.toDto(savedTransaction);
    }

    public TransactionDto getTransactionbyId(UUID transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found " + transactionId));
        return TransactionMapper.toDto(transaction);
    }

    @Override
    public TransactionDto updateTransaction(UUID transactionId,TransactionDto updatedTransactionDto) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Transaction is not exist with given Id: " + transactionId));

        transaction.setTransactionName(updatedTransactionDto.getTransactionName());
        transaction.setDescription(updatedTransactionDto.getDescription());
        transaction.setTransactionType(updatedTransactionDto.getTransactionType());
        transaction.setAmount(updatedTransactionDto.getAmount());
        Category category = categoryRepository.findById(updatedTransactionDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category does not exist with the given Id: " + updatedTransactionDto.getCategoryId()));
        transaction.setCategory(category);
        Family family = familyRepository.findById(updatedTransactionDto.getFamilyId())
                .orElseThrow(() -> new ResourceNotFoundException("Family does not exist with the given Id: " + updatedTransactionDto.getFamilyId()));
        transaction.setFamily(family);
        User user = userRepository.findById(updatedTransactionDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with the given Id: " + updatedTransactionDto.getUserId()));
        transaction.setUser(user);
        Transaction updatedTransaction = transactionRepository.save(transaction);
        return TransactionMapper.toDto(updatedTransaction);
    }

    @Override
    public List<TransactionDto> getAllTransaction() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map((transaction -> TransactionMapper.toDto(transaction)))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteTransaction(UUID transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Transaction is not exist with given Id: " + transactionId));
        transactionRepository.deleteById(transactionId);
    }
}
