package com.Maher.JobPortal.JobRepo;

import com.Maher.JobPortal.Model.JobsPortal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Repo
{
    private List<JobsPortal>Jobs = new ArrayList<>();
    public Repo()
    {
        Jobs.add(new JobsPortal(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
                List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")));

        // Frontend Developer Job Post
        Jobs.add(new JobsPortal(2, "Frontend Developer", "Experience in building responsive web applications using React",
                3, List.of("HTML", "CSS", "JavaScript", "React")));

        // Data Scientist Job Post
        Jobs.add(new JobsPortal(3, "Data Scientist", "Strong background in machine learning and data analysis", 4,
                List.of("Python", "Machine Learning", "Data Analysis")));

        // Network Engineer Job Post
        Jobs.add(new JobsPortal(4, "Network Engineer",
                "Design and implement computer networks for efficient data communication", 5,
                List.of("Networking", "Cisco", "Routing", "Switching")));

        // Mobile App Developer Job Post
        Jobs.add(new JobsPortal(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android", 3,
                List.of("iOS Development", "Android Development", "Mobile App")));
    }


    // method to return all JobPosts
    public List<JobsPortal> getAllJobs() {
        return Jobs;
    }



    // method to save a job post object into arrayList
    public void addJob(JobsPortal job) {
        Jobs.add(job);

    }




    //method to get a job by postId
    public JobsPortal getJob(int postId) {
        for (JobsPortal job : Jobs) {
            if (job.getID() == postId) {
                return job;
            }
        }

        return null;
    }


    public void updateJob(JobsPortal jobPost) {
        for (JobsPortal jobPost1 : Jobs) {
            if (jobPost1.getID() == jobPost.getID())
            {
                jobPost1.setJobName(jobPost.getJobName());
                jobPost1.setJobDescription(jobPost.getJobDescription());
                jobPost1.setRequiredExperience(jobPost.getRequiredExperience());
                jobPost.setTechStacks(jobPost.getTechStacks());

            }
        }
    }




    public void deleteJob(int postId) {
        for (JobsPortal jobPost : Jobs) {
            if (jobPost.getID() == postId) {
                Jobs.remove(jobPost);

            }
        }
    }
}
