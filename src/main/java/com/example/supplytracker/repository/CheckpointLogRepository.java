package com.example.supplytracker.repository;

import com.example.supplytracker.entity.CheckpointLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckpointLogRepository extends JpaRepository<CheckpointLog, Long> {
}
