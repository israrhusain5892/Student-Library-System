package com.driver.services;

import com.driver.models.Card;
import com.driver.models.CardStatus;
import com.driver.models.Student;
import com.driver.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {


    @Autowired
    CardService cardService4;

    @Autowired
    StudentRepository studentRepository4;

    public Student getDetailsByEmail(String email){
        Student student = null;
        student=studentRepository4.findByEmailId(email);
        return student;
    }

    public Student getDetailsById(int id){
        Student student = null;
         student=studentRepository4.findById(id).get();
        return student;
    }

    public void createStudent(Student student){
           
           Card newcard=cardService4.createAndReturn(student);
           student.setCard(newcard);

           studentRepository4.save(student);

    }

    public void updateStudent(Student student){
           studentRepository4.updateStudentDetails(student);

    }

    public void deleteStudent(int id){
        //Delete student and deactivate corresponding card
        studentRepository4.deleteCustom(id);
        Student student=studentRepository4.findById(id).get();
        Card card=student.getCard();
        card.setCardStatus(CardStatus.DEACTIVATED);
        student.setCard(card);
        studentRepository4.save(student);
    }
}
