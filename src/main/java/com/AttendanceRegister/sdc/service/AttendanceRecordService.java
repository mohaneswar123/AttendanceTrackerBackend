package com.AttendanceRegister.sdc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.AttendanceRegister.sdc.Repository.AttendanceRecordRepository;
import com.AttendanceRegister.sdc.Repository.SubjectRepository;
import com.AttendanceRegister.sdc.Repository.UserRepository;
import com.AttendanceRegister.sdc.model.AttendanceRecord;
import com.AttendanceRegister.sdc.model.Subject;
import com.AttendanceRegister.sdc.model.User;

@Service
public class AttendanceRecordService {

    private final AttendanceRecordRepository attendanceRecordRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    public AttendanceRecordService(
            AttendanceRecordRepository attendanceRecordRepository,
            UserRepository userRepository,
            SubjectRepository subjectRepository
    ) {
        this.attendanceRecordRepository = attendanceRecordRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
    }

 // ✅ Add a new attendance record (with classNumber)
    public AttendanceRecord addRecord(Long userId, Long subjectId, String status, String date, int classNumber) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));

        // ❌ Remove duplicate check
        // boolean exists = attendanceRecordRepository.existsByUserAndSubjectAndDateAndClassNumber(user, subject, date, classNumber);
        // if (exists) {
        //     throw new RuntimeException("Attendance already submitted for this class number.");
        // }

        AttendanceRecord record = new AttendanceRecord(status, date, classNumber, user, subject);
        return attendanceRecordRepository.save(record);
    }


    // ✅ Get all attendance records for a user
    public List<AttendanceRecord> getRecordsByUser(Long userId) {
        return attendanceRecordRepository.findByUserId(userId);
    }

    // ✅ Optional: Get all attendance records
    public List<AttendanceRecord> getAllRecords() {
        return attendanceRecordRepository.findAll();
    }
    
    
    // ✅ Update a specific record
    public AttendanceRecord updateRecord(Long id, AttendanceRecord updated) {
        AttendanceRecord existing = attendanceRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with ID: " + id));

        existing.setStatus(updated.getStatus());
        existing.setDate(updated.getDate());
        existing.setSubject(updated.getSubject());
        existing.setClassNumber(updated.getClassNumber());

        return attendanceRecordRepository.save(existing);
    }
    
    public void deleteBySubjectDateAndClassNumber(Long subjectId, String date, int classNumber) {
        if (!subjectRepository.existsById(subjectId)) {
            throw new RuntimeException("Subject not found with ID: " + subjectId);
        }
        attendanceRecordRepository.deleteBySubjectIdAndDateAndClassNumber(subjectId, date, classNumber);
    }


    // ✅ Delete a record by ID
    public void deleteRecord(Long id) {
        if (!attendanceRecordRepository.existsById(id)) {
            throw new RuntimeException("Record not found with ID: " + id);
        }
        attendanceRecordRepository.deleteById(id);
    }

    // ✅ Delete all records of a user
    public void deleteAllRecordsByUser(Long userId) {
        List<AttendanceRecord> records = attendanceRecordRepository.findByUserId(userId);
        attendanceRecordRepository.deleteAll(records);
    }
}
