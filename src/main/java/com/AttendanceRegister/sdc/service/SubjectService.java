package com.AttendanceRegister.sdc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.AttendanceRegister.sdc.Repository.AttendanceRecordRepository;
import com.AttendanceRegister.sdc.Repository.SubjectRepository;
import com.AttendanceRegister.sdc.Repository.UserRepository;
import com.AttendanceRegister.sdc.model.Subject;
import com.AttendanceRegister.sdc.model.User;

import jakarta.transaction.Transactional;

@Service
public class SubjectService {

    private final AttendanceRecordRepository attendanceRecordRepository;

    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public SubjectService(SubjectRepository subjectRepository, UserRepository userRepository, AttendanceRecordRepository attendanceRecordRepository) {
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.attendanceRecordRepository = attendanceRecordRepository;
    }

    // ✅ Add a new subject for a user
    public Subject addSubject(Long userId, String subjectName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Subject subject = new Subject(subjectName, user);
        return subjectRepository.save(subject);
    }

    // ✅ Get all subjects for a user
    public List<Subject> getSubjectsByUser(Long userId) {
        return subjectRepository.findByUserId(userId);
    }

    @Transactional
    public void deleteSubjectForUser(Long subjectId, Long userId) {
        if (!subjectRepository.existsById(subjectId)) {
            throw new RuntimeException("Subject not found with ID: " + subjectId);
        }

        // Delete all related attendance records first
        attendanceRecordRepository.deleteBySubjectIdAndUserId(subjectId, userId);

        // Then delete the subject
        subjectRepository.deleteById(subjectId);
    }


    // ✅ Delete all subjects associated with a user
    public void deleteAllSubjectsByUser(Long userId) {
        List<Subject> subjects = subjectRepository.findByUserId(userId);
        subjectRepository.deleteAll(subjects);
    }
}
