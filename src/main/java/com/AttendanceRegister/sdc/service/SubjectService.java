package com.AttendanceRegister.sdc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.AttendanceRegister.sdc.Repository.SubjectRepository;
import com.AttendanceRegister.sdc.Repository.UserRepository;
import com.AttendanceRegister.sdc.model.Subject;
import com.AttendanceRegister.sdc.model.User;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public SubjectService(SubjectRepository subjectRepository, UserRepository userRepository) {
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
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

    // ✅ Delete a specific subject by its ID
    public void deleteSubject(Long subjectId) {
        if (!subjectRepository.existsById(subjectId)) {
            throw new RuntimeException("Subject not found with ID: " + subjectId);
        }
        subjectRepository.deleteById(subjectId);
    }

    // ✅ Delete all subjects associated with a user
    public void deleteAllSubjectsByUser(Long userId) {
        List<Subject> subjects = subjectRepository.findByUserId(userId);
        subjectRepository.deleteAll(subjects);
    }
}
