package com.Survey.FullSurveyProject.Users;

import com.Survey.FullSurveyProject.Models.Users;
import jakarta.persistence.NamedQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer>
{
    public Optional<Users> findByUserEmail(String Email);
    @Modifying
    @Query
            (
                    value = "UPDATE users_data SET name=:Name, " +
                            "password=:Password," +
                            "image=: Image, "+
                            "iamgename=:ImageName," +
                            "size=:ImageSIze, " +
                            "image_type:=Image_Type",
                    nativeQuery = true
            )
    @Transactional
    public void UpdateUserData
            (
                    @Param("Name") String Name,
                    @Param("Password") String Password,
                    @Param("Image") String Image,
                    @Param("ImageName") String ImageName,
                    @Param("ImageSize") Integer ImageSize,
                    @Param("Image_Type") String Image_Type
            );
}
