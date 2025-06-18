package com.Maher.JobPortal.Controller;

import com.Maher.JobPortal.Service.ServiceJob;
import com.Maher.JobPortal.Model.JobsPortal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for handling job post-related operations.
 * Maps all endpoints under the base path for job posts.
 */
@RestController
public class JobController {

    // Automatically injects the ServiceJob dependency
    @Autowired
    private ServiceJob Service;

    /**
     * GET endpoint to retrieve all job posts
     *
     * @return List of all job postings
     */
    @GetMapping("jobPosts")
    public List<JobsPortal> getAllJobs() {
        return Service.getAllJobs();
    }

    /**
     * GET endpoint to retrieve a specific job post by ID
     *
     * @param postId The ID of the job post to retrieve
     * @return The requested job posting
     */
    @GetMapping("jobPosts/{postId}")
    public JobsPortal getJob(@PathVariable int postId) {
        return Service.getJob(postId);
    }

    /**
     * POST endpoint to create a new job post
     *
     * @param jobPortal The job post data to create (received as JSON in request body)
     */
    @PostMapping("JobPosts")
    public void AddNewJob(@RequestBody JobsPortal jobPortal) {
        Service.addJob(jobPortal);
    }

    /**
     * PUT endpoint to update an existing job post
     *
     * @param jobPortal The updated job post data (received as JSON in request body)
     */
    @PutMapping("JobPosts")
    public void UpdateJobPost(@RequestBody JobsPortal jobPortal) {
        Service.updateJob(jobPortal);
    }

    /**
     * DELETE endpoint to remove a job post by ID
     *
     * @param postId The ID of the job post to delete
     */
    @DeleteMapping("jobPosts/{postId}")
    public void DeleteJob(@PathVariable int postId) {
        Service.deleteJob(postId);
    }
}


