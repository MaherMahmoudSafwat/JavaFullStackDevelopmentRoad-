package com.example.Spring.Data.Rest.PersonsRepostiroy;

import com.example.Spring.Data.Rest.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonsRepository extends JpaRepository<Person,Integer>
{
}
