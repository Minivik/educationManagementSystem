package com.ex.StudentInfromationSystem.Service;

import com.ex.StudentInfromationSystem.Entities.AttendancePeriod;
import com.ex.StudentInfromationSystem.Entities.AttendanceRecord;
import com.ex.StudentInfromationSystem.Entities.Student;
import com.ex.StudentInfromationSystem.Entities.StudentAcademicRecord;

import com.ex.StudentInfromationSystem.Enums.AttendanceStatus;
import com.ex.StudentInfromationSystem.Enums.EnrollmentStatus;
import com.ex.StudentInfromationSystem.Repository.AttendancePeriodRepository;
import com.ex.StudentInfromationSystem.Repository.AttendanceRecordRepo;
import com.ex.StudentInfromationSystem.Repository.StudentScopeEnrollmentRepo;
import com.ex.StudentInfromationSystem.dto.AttendanceDayResponse;
import com.ex.StudentInfromationSystem.dto.AttendanceSummaryResponse;
import com.ex.StudentInfromationSystem.dto.DailyAttendancePercentageResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceServiceImpl {
    private final AttendancePeriodRepository attendancePeriodRepository;
    private final AttendanceRecordRepo attendanceRecordRepo;
    private final StudentScopeEnrollmentRepo academicRepo;


    public AttendanceServiceImpl(AttendancePeriodRepository attendancePeriodRepository,
                                 AttendanceRecordRepo attendanceRecordRepo,
                                 StudentScopeEnrollmentRepo academicRepo) {
        this.attendancePeriodRepository = attendancePeriodRepository;
        this.attendanceRecordRepo=attendanceRecordRepo;
        this.academicRepo=academicRepo;

    }


    public void markAttendance(
            LocalDate date,
            Map<Long, AttendanceStatus> records
    ) {
        for (Map.Entry<Long, AttendanceStatus> entry : records.entrySet()) {

            Long academicRecordId = entry.getKey();
            AttendanceStatus status = entry.getValue();
            StudentAcademicRecord record =
                    academicRepo.findById(academicRecordId)
                            .orElseThrow();

            if (record.getStatus() != EnrollmentStatus.ACTIVE) {
                throw new RuntimeException("Inactive academic record");
            }

            if (academicRepo.existsByAcademicRecordIdAndDate(academicRecordId, date)) {
                throw new RuntimeException("Attendance already marked");
            }
            attendanceRecordRepo.save(
                    new  AttendanceRecord(record.getStudent(), LocalDate.now(), AttendanceStatus.PRESENT)
            );
        }}


        public   List<AttendanceRecord> getForStudentAttendance(Long studentId) {
            StudentAcademicRecord record =
                    academicRepo.findByStudentIdAndAcademicStatus(
                            studentId, EnrollmentStatus.ACTIVE
                    ).orElseThrow();
            return  attendanceRecordRepo.findByAcademicRecordId(record.getId());
        }


    public AttendanceSummaryResponse StdAttendanceSummary(Long studentId) {

        StudentAcademicRecord record =
                academicRepo.findByStudentIdAndAcademicStatus(
                        studentId, EnrollmentStatus.ACTIVE
                ).orElseThrow();

        List<AttendanceDayResponse> days =  attendanceRecordRepo.findByStudentId(studentId)
                .stream()
                .map(a -> new AttendanceDayResponse(a.getDate(), a.getStatus()))
                .toList();

        long present = days.stream().filter(d -> d.status == AttendanceStatus.PRESENT).count();
        long absent = days.size() - present;

        return new AttendanceSummaryResponse(
                studentId,
                days.size(),
                present,
                absent,
                days
        );
    }


    public DailyAttendancePercentageResponse dailyAttendanceTotalPercentage(LocalDate date) {

        long totalActiveStudents =
                academicRepo.countByAcademicStatus(EnrollmentStatus.ACTIVE);

        long presentStudents =
                academicRepo.countByDate_Status(AttendanceStatus.PRESENT,date);

        return new DailyAttendancePercentageResponse(date,
                totalActiveStudents,
                presentStudents);
    }

//    @Transactional
//    public AttendanceRecord markPeriodAttendance(
//            Student student,
//            LocalDate date,
//            List<AttendancePeriod> periods) {
//
//        AttendanceRecord record = attendancePeriodRepository
//                .findByStudentAndAttendanceDate(student, date)
//                .orElse(new AttendanceRecord());
//
//        record.setStudent(student);
//        record.setAttendanceDate(date);
//
//        periods.forEach(p -> p.setAttendanceRecord(record));
//        record.getPeriods().clear();
//        record.getPeriods().addAll(periods);
//
//        return attendancePeriodRepository.save(new AttendancePeriod(record,date,periods));
//    }




}