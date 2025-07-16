package com.AttendanceRegister.sdc.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AttendanceRegister.sdc.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByUserId(Long userId);
}
