package com.familyapp.application.mapper;

import com.familyapp.application.dto.TransactionDto;
import com.familyapp.application.entity.Category;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.Transaction;
import com.familyapp.application.entity.User;

public class TransactionMapper {
    public static TransactionDto toDto(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        return new TransactionDto(
                transaction.getTransactionId(),
                transaction.getTransactionName(),
                transaction.getDescription(),
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getCategory().getCategoryId(),
                transaction.getFamily().getFamilyId(),
                transaction.getUser().getUserId()
        );
    }
    public static Transaction toEntity(TransactionDto transactionDto, Category category, Family family, User user) {
        if (transactionDto == null) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionDto.getTransactionId());
        transaction.setTransactionName(transactionDto.getTransactionName());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setCategory(category);
        transaction.setFamily(family);
        transaction.setUser(user);
        return transaction;
    }
}
