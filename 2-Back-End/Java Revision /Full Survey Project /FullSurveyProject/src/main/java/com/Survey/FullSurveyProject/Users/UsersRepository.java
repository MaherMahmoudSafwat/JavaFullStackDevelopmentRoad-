package com.Survey.FullSurveyProject.Users;

import com.Survey.FullSurveyProject.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer>
{
    public List<Users> findByEmail(String Email);
}
