package com.Maher.JobPortal.Service;


import java.util.List;

import com.Maher.JobPortal.JobRepo.Repo;
import com.Maher.JobPortal.Model.JobsPortal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServiceJob {
    @Autowired
    public Repo repo;



    //method to return all JobPosts
    public List<JobsPortal> getAllJobs() {
        return repo.getAllJobs();


    }





    // ***************************************************************************





    // method to add a jobPost
    public void addJob(JobsPortal jobPost) {
        repo.addJob(jobPost);

    }





    public JobsPortal getJob(int postId) {

        return repo.getJob(postId);
    }




    public void updateJob(JobsPortal jobPost) {
        repo.updateJob(jobPost);

    }





    public void deleteJob(int postId) {
        repo.deleteJob(postId);

    }
}