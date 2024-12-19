package com.fabrice.studentregistration.studentregistration.repositories;

import com.fabrice.studentregistration.studentregistration.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<Student, Long> {

}
