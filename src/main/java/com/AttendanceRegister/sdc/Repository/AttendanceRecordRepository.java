package com.AttendanceRegister.sdc.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AttendanceRegister.sdc.model.AttendanceRecord;
import com.AttendanceRegister.sdc.model.Subject;
import com.AttendanceRegister.sdc.model.User;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
    List<AttendanceRecord> findByUserId(Long userId);
    boolean existsByUserAndSubjectAndDateAndClassNumber(User user, Subject subject, String date, int classNumber);
    void deleteBySubjectIdAndDateAndClassNumber(Long subjectId, String date, int classNumber);
    void deleteBySubjectIdAndUserId(Long subjectId, Long userId);

}
