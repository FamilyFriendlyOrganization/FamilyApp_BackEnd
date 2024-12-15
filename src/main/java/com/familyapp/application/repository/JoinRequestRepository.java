package com.familyapp.application.repository;

import com.familyapp.application.dto.JoinRequestDto;
import com.familyapp.application.entity.JoinRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoinRequestRepository extends JpaRepository<JoinRequest, Long> {
    List<JoinRequest> findByStatus(JoinRequest.JoinRequestStatus status);

}
