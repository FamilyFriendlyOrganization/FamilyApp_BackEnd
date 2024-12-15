package com.familyapp.application.repository;

import com.familyapp.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByAssignedAccountId_AccountIdAndFamily_FamilyId(UUID assignedAccountId, UUID familyId);

    List<User> findAllByFamily_FamilyId(UUID familyId);

}

