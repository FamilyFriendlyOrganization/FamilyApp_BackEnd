package com.familyapp.application.service.impl;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.FamilyDto;
import com.familyapp.application.dto.TransactionDto;
import com.familyapp.application.entity.*;
import com.familyapp.application.exception.ResourceNotFoundException;
import com.familyapp.application.mapper.AccountMapper;
import com.familyapp.application.mapper.FamilyMapper;
import com.familyapp.application.mapper.TransactionMapper;
import com.familyapp.application.repository.CategoryRepository;
import com.familyapp.application.repository.FamilyRepository;
import com.familyapp.application.repository.TransactionRepository;
import com.familyapp.application.repository.UserRepository;
import com.familyapp.application.service.FamilyService;
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
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Category category = categoryRepository.findById(transactionDto.getCategoryId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account not found with given Id: " + transactionDto.getCategoryId()));
        Family family = familyRepository.findById(transactionDto.getFamilyId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account not found with given Id: " + transactionDto.getCategoryId()));
        User user = userRepository.findById(transactionDto.getUserId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account not found with given Id: " + transactionDto.getCategoryId()));
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
    public TransactionDto updateTransaction(UUID transactionId, TransactionDto updatedTransactionDto) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction does not exist with given Id: " + transactionId));


        transaction.setTransactionName(updatedTransactionDto.getTransactionName());
        transaction.setDescription(updatedTransactionDto.getDescription());
        transaction.setTransactionType(updatedTransactionDto.getTransactionType());
        transaction.setAmount(updatedTransactionDto.getAmount());

        Category category = categoryRepository.findById(updatedTransactionDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category does not exist with the given Id: " + updatedTransactionDto.getCategoryId()));
        transaction.setCategory(category);

        Family family = familyRepository.findById(updatedTransactionDto.getFamilyId())
                .orElseThrow(() -> new ResourceNotFoundException("Family does not exist with the given Id: " + updatedTransactionDto.getFamilyId()));
        transaction.setTransactionType(updatedTransactionDto.getTransactionType());
        transaction.setAmount(updatedTransactionDto.getAmount());
        family.setBudget(family.getBudget() + updatedTransactionDto.getAmount() - transaction.getAmount());

        Family updatedFamily = familyRepository.save(family);
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
    public TransactionDto handleNewTransaction(TransactionDto transactionDto) {
        Family family = familyRepository.findById(transactionDto.getFamilyId())
                .orElseThrow(() -> new ResourceNotFoundException("Family does not exist with the given Id: " + transactionDto.getFamilyId()));

        if (transactionDto.getTransactionType() == 0) {
            family.setBudget(family.getBudget() + transactionDto.getAmount());
        } else {
            family.setBudget(family.getBudget() - transactionDto.getAmount());
        }

        Family updatedFamily = familyRepository.save(family);
        return createTransaction(transactionDto);
    }


    @Override
    public void deleteTransaction(UUID transactionId) {
        TransactionDto dto = getTransactionbyId(transactionId);

        Family family = familyRepository.findById(dto.getFamilyId())
                .orElseThrow(() -> new ResourceNotFoundException("Family does not exist with the given Id: " + dto.getFamilyId()));


        if (dto.getTransactionType() == 1) {
            family.setBudget(family.getBudget() + dto.getAmount());
        } else {
            family.setBudget(family.getBudget() - dto.getAmount());
        }

        familyRepository.save(family);
        dto.setStatus(1);
        updateTransaction(transactionId, dto);
    }
}
