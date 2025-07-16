package com.AttendanceRegister.sdc.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AttendanceRegister.sdc.model.AttendanceRecord;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
    List<AttendanceRecord> findByUserId(Long userId);
}
