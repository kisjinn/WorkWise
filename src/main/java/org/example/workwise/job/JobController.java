package org.example.workwise.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
@RestController
public class JobController {
    @Autowired
    private JobService jobService;
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAllJobs(){
        return new ResponseEntity<>(jobService.finaAllJobs(),HttpStatus.OK);
    }
    @PostMapping("/jobs")
    public ResponseEntity<String> addJob(@RequestBody Job job){
        jobService.addJob(job);
        return new ResponseEntity<>("Job Added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/jobs/{id}")
    public ResponseEntity<Job> getJob(@PathVariable Long id){

        Job job= jobService.findJobByid(id);
        if(job==null){
            return new ResponseEntity<>(job, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean delete= jobService.deleteJobbyId(id);
        if(delete==true){
            return new  ResponseEntity<>("job deleted Successfully", HttpStatus.OK);
        } return new  ResponseEntity<>("job couldnt be deleted",HttpStatus.NOT_FOUND);
    }


}
