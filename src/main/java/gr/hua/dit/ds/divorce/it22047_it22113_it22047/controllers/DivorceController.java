//package gr.hua.dit.ds.divorce.it22047_it22113_it22047.controllers;
//
//import gr.hua.dit.ds.divorce.it22047_it22113_it22047.dao.DivorceDAO;
//import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/divorce")
//public class DivorceController {
//    @Autowired
//    private DivorceDAO divorceDAO;
//
//    @GetMapping("")
//    public List<Divorce> getAllDivorces() {
//        return divorceDAO.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public Divorce getById(@PathVariable int id) {
//        return divorceDAO.findById(id);
//    }
//
//    @PostMapping("")
//    public Divorce save(@RequestBody Divorce divorce) {
//        divorce.setId(0);
//        divorceDAO.save(divorce);
//        return divorce;
//    }
//
//
//    @PostMapping("/{cid}/teacher")
//    public Teacher addTeacher(@PathVariable int cid, @RequestBody Teacher teacher) {
//        int teacherId = teacher.getId();
//        Course course = courseDAO.findById(cid);
//
//        if(course == null) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "entity not found"
//            );
//        }
//
//        if(teacherId != 0) {
//            Teacher ateacher = teacherDAO.findById(teacherId);
//            course.setTeacher(ateacher);
//            teacherDAO.save(teacher);
//            return ateacher;
//        }
//
//        course.setTeacher(teacher);
//        teacherDAO.save(teacher);
//        return teacher;
//
//    }
//
//
//    @PostMapping("/{cid}/review")
//    public Review addReview(@PathVariable int cid, @RequestBody Review review) {
//        Course course = courseDAO.findById(cid);
//
//        if(course == null) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "entity not found"
//            );
//        }
//
//        course.addReview(review);
//        reviewRepository.save(review);
//        return review;
//
//    }
//
//    @GetMapping("/{cid}/reviews")
//    public List<Review> getCourseReviews(@PathVariable int cid) {
//        Course course = courseDAO.findById(cid);
//
//        if(course == null) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "entity not found"
//            );
//        }
//
//        return course.getReviews();
//
//    }
//
//    @PostMapping("/{cid}/student")
//    public Student setStudent(@PathVariable int cid, @RequestBody Student student) {
//        int studentId = student.getId();
//        Course course = courseDAO.findById(cid);
//
//        if(course == null) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "entity not found"
//            );
//        }
//
//        if(studentId != 0) {
//            Student astudent = studentRepository.findById(studentId).orElse(null);
//
//            course.addStudent(astudent);
//            studentRepository.save(astudent);
//            return astudent;
//        }
//
//
//        course.addStudent(student);
//        studentRepository.save(student);
//        return student;
//
//    }
//}
