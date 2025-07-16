package com.AttendanceRegister.sdc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AttendanceRegister.sdc.model.AttendanceRecord;
import com.AttendanceRegister.sdc.service.AttendanceRecordService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceRecordController {

    private final AttendanceRecordService attendanceService;

    public AttendanceRecordController(AttendanceRecordService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // ✅ Add a new attendance record
    @PostMapping("/add")
    public ResponseEntity<AttendanceRecord> addRecord(
            @RequestParam Long userId,
            @RequestParam Long subjectId,
            @RequestParam String status,
            @RequestParam String date) {
        AttendanceRecord record = attendanceService.addRecord(userId, subjectId, status, date);
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

    // ✅ Delete a specific attendance record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        attendanceService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}
