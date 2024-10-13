package com.familyapp.application.service;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.TransactionDto;
import com.familyapp.application.entity.Category;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.User;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transactionDto, Category category, Family family, User user );
    TransactionDto getTransactionbyId(UUID transactionId);
    TransactionDto updateTransaction(UUID transactionId, TransactionDto transactionDto);
    List<TransactionDto> getAllTransaction();
    void deleteTransaction(UUID transactionId);
}
