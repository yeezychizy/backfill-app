package com.chizu.backfill.service;

import com.chizu.backfill.model.*;
import com.chizu.backfill.repository.AbsenceRepository;
import com.chizu.backfill.repository.BackfillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : chizubeokwuosa
 * @Description :
 * @created : chizubeokwuosa, Tuesday
 **/
@Service
public class BackfillService {
    public static final String EMAIL_ADDRESS_REGEXP = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_REGEXP = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$";
    private final BackfillRepository backfillRepository;
    private final AbsenceRepository absenceRepository;

    public BackfillService(BackfillRepository backfillRepository, AbsenceRepository absenceRepository) {
        this.backfillRepository = backfillRepository;
        this.absenceRepository = absenceRepository;
    }

    public Staff signup(SignupDto signup) {
        if(signup.getEmail().isBlank()){
            throw new IllegalArgumentException("Email address cannot be empty");
        }
        if(signup.getPassword().isBlank()){
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if(backfillRepository.existsByEmail(signup.getEmail())){
            throw new IllegalArgumentException("Email address already exists");
        }
        if (!signup.getEmail().matches(EMAIL_ADDRESS_REGEXP)){
            throw new IllegalArgumentException("Invalid email address");
        }
        if (!signup.getPassword().matches(PASSWORD_REGEXP)){
            throw new IllegalArgumentException("Invalid password. Password must contain at least 8 characters, including uppercase, lowercase, number, and special character");
        }
        if (!signup.getPassword().equals(signup.getConfirmPassword())){
            throw new IllegalArgumentException("Passwords do not match");
        }
        Staff staff = new Staff();
        staff.setFirstName(signup.getFirstName());
        staff.setLastName(signup.getLastName());
        staff.setEmail(signup.getEmail());
        staff.setPassword(signup.getPassword());
        staff.setConfirmPassword(signup.getConfirmPassword());
        staff.setSignedUp(true);
        if (signup.getRole().equalsIgnoreCase("HR")){
            staff.setRole("HR");
        } else if (signup.getRole().equalsIgnoreCase("TEACHER")) {
            staff.setRole("TEACHER");
        }
        else staff.setRole("ROLE_USER");

        backfillRepository.save(staff);
        staff.setPassword(null);
        staff.setConfirmPassword(null);

        return staff;
    }

    public Staff signin(SigninDto signin) {
        Staff staff = backfillRepository.findByEmail(signin.getEmail()).orElse(null);
        if (staff == null){
            throw new IllegalArgumentException("Email address not found");
        }
        if (!staff.isSignedUp()){
            throw new IllegalArgumentException("Account has not been activated yet");
        }

        if (!staff.getPassword().equals(signin.getPassword())){
            throw new IllegalArgumentException("Incorrect password");
        }
        staff.setSignedIn(true);
        backfillRepository.save(staff);
        staff.setPassword(null);
        staff.setConfirmPassword(null);
        return staff;
    }

    public Absence backfill(AbsenceDto absenceDto) {
        Staff staff = signedInOrSignedUp(absenceDto.getEmail());
        Absence absence = new Absence();
        if (staff.getRole().equalsIgnoreCase("TEACHER")){

            absence.setStartDate(absenceDto.getStartDate());
            absence.setEndDate(absenceDto.getEndDate());
            absence.setReason(absenceDto.getReason());
            absence.setClassName(absenceDto.getClassName());
            absence.setEmail(absenceDto.getEmail());
            absence.setFullName(staff.getFirstName() + " " + staff.getLastName());
            absence.setStatus("Pending");
            absenceRepository.save(absence);

        }
        return absence;
    }

    public List<AbsenceDto> getAllAbsences(StaffDto staffDto) {
        signedInOrSignedUp(staffDto.getEmail());
        if (staffDto.getRole().equalsIgnoreCase("HR")) {
            List<Absence> absences = absenceRepository.findAll();
            return absences.stream().map(absence -> new AbsenceDto(absence.getStartDate(), absence.getEndDate(), absence.getReason(), absence.getClassName(), absence.getEmail(), absence.getFullName(), absence.getStatus())).collect(Collectors.toList());
        }
        return null;
    }

    public List<StaffDto> getReplacements(StaffDto staffDto) {
        signedInOrSignedUp(staffDto.getEmail());
        if (staffDto.getRole().equalsIgnoreCase("HR")) {
            List<Staff> staffs = backfillRepository.findAll().stream()
                   .filter(s -> s.getRole().equalsIgnoreCase("TEACHER"))
                   .filter(s ->!s.getEmail().equalsIgnoreCase(staffDto.getEmail()))
                    .filter(s -> s.getSpecialisation().contains(staffDto.getSpecialisation()))
                   .toList();
            return staffs.stream().map(staff1 -> new StaffDto(staff1.getFirstName(), staff1.getLastName(), staff1.getEmail(), null, null, staff1.getRole(), staff1.getSpecialisation())).collect(Collectors.toList());
        }
        return null;
    }

    public String getSelectedAbsences(StaffDto staffDto) {
        signedInOrSignedUp(staffDto.getEmail());
        if (staffDto.getRole().equalsIgnoreCase("HR")) {
            List<Absence> absences = absenceRepository.findAll().stream()
                   .filter(a -> a.getStatus().equalsIgnoreCase("Pending"))
                   .filter(a -> a.getEmail().equalsIgnoreCase(staffDto.getEmail()))
                   .toList();
            return absences.stream().map(Absence::getFullName).collect(Collectors.joining(", "));
        }
        return null;
    }

    private Staff signedInOrSignedUp(String staffDto) {
        Staff staff = backfillRepository.findByEmail(staffDto).orElse(null);
        if (staff == null) {
            throw new IllegalArgumentException("Email address not found");
        }
        if (!staff.isSignedUp()) {
            throw new IllegalArgumentException("Account has not been activated yet");
        }
        if (!staff.isSignedIn()) {
            throw new IllegalArgumentException("Account has not been activated yet");
        }
        return staff;
    }
}
