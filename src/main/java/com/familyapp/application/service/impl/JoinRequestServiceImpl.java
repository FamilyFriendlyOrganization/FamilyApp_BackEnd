
package com.familyapp.application.service.impl;
import com.familyapp.application.dto.JoinRequestDto;
import com.familyapp.application.entity.JoinRequest;
import com.familyapp.application.mapper.JoinRequestMapper;
import com.familyapp.application.repository.JoinRequestRepository;  // Assuming you have a repository
import com.familyapp.application.service.JoinRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service  // Mark this class as a Spring service
public class JoinRequestServiceImpl implements JoinRequestService {

    private final JoinRequestRepository joinRequestRepository;  // Inject the repository

    @Autowired
    public JoinRequestServiceImpl(JoinRequestRepository joinRequestRepository) {
        this.joinRequestRepository = joinRequestRepository;
    }

    @Override
    public List<JoinRequestDto> getPendingJoinRequests() {
        // Retrieve the list of pending JoinRequests from the repository
        List<JoinRequest> pendingJoinRequests = joinRequestRepository.findByStatus(JoinRequest.JoinRequestStatus.PENDING);

        // Convert the list of JoinRequest entities to JoinRequestDto using the JoinRequestMapper
        return pendingJoinRequests.stream()
                .map(JoinRequestMapper::toDto) // Use the mapper to convert each JoinRequest to JoinRequestDto
                .collect(Collectors.toList());
    }

}
