package com.AttendanceRegister.sdc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.AttendanceRegister.sdc.model.AttendanceRecord;
import com.AttendanceRegister.sdc.service.AttendanceRecordService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceRecordController {

    private final AttendanceRecordService attendanceService;

    public AttendanceRecordController(AttendanceRecordService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // ✅ Add a new attendance record with classNumber
    @PostMapping("/add")
    public ResponseEntity<AttendanceRecord> addRecord(
            @RequestParam Long userId,
            @RequestParam Long subjectId,
            @RequestParam String status,
            @RequestParam String date,
            @RequestParam int classNumber) {

        AttendanceRecord record = attendanceService.addRecord(userId, subjectId, status, date, classNumber);
        return ResponseEntity.ok(record);
    }

    // ✅ Get all attendance records for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AttendanceRecord>> getRecordsByUser(@PathVariable Long userId) {
        List<AttendanceRecord> records = attendanceService.getRecordsByUser(userId);
        return ResponseEntity.ok(records);
    }

    // ✅ Update a specific attendance record
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceRecord> updateRecord(
            @PathVariable Long id,
            @RequestBody AttendanceRecord updatedRecord) {
        AttendanceRecord record = attendanceService.updateRecord(id, updatedRecord);
        return ResponseEntity.ok(record);
    }

    // ✅ Delete a specific attendance record by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        attendanceService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Delete records by subject, date, and class number
    @DeleteMapping("/delete-by-subject-date-class")
    public ResponseEntity<String> deleteBySubjectDateClass(
            @RequestParam Long subjectId,
            @RequestParam String date,
            @RequestParam int classNumber) {

        attendanceService.deleteBySubjectDateAndClassNumber(subjectId, date, classNumber);
        return ResponseEntity.ok("Records deleted for Subject ID: " + subjectId +
                                 ", Date: " + date +
                                 ", Class Number: " + classNumber);
    }
}
