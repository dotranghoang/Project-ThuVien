package com.codegym.controller;

import com.codegym.model.Job;
import com.codegym.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class ApiJobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/api/job")
    public ResponseEntity<List<Job>> getJobList(){
        List<Job> jobs = (List<Job>) jobService.findAll();

        if(jobs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(jobs,HttpStatus.OK);
    }

    @GetMapping("/api/job/{id}")
    public ResponseEntity<Job> getJob(@PathVariable Long id){
        Job job = jobService.findById(id);

        if(job == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(job,HttpStatus.OK);
    }

    @PostMapping("/api/job")
    public ResponseEntity<Void> createJob(@RequestBody Job job) {
        jobService.save(job);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/api/job/{id}")
    public ResponseEntity<Job> editJob(@RequestBody Job job, @PathVariable Long id){
        Job job1 = jobService.findById(id);
        if(job1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        job1.setJob(job.getJob());
        jobService.save(job1);

        return new ResponseEntity<>(job1,HttpStatus.OK);
    }

    @DeleteMapping("/api/job/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id){
        Job job = jobService.findById(id);

        if(job == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        jobService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
