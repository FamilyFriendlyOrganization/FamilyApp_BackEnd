package com.familyapp.application.repository;

import com.familyapp.application.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, UUID> {

}
