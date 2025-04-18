package com.agmc.LibraryManagement.repository;
import com.agmc.LibraryManagement.model.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    public User findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "update users set " +
                        "name = coalesce(:new_name, name), "+
                        "email = coalesce(:new_email, email)," +
                        "password = coalesce(:new_password, password)," +
                        "address = coalesce(:new_address, address) " +
                        "where id = :user_id", nativeQuery = true)
    void updateUser(@Param("user_id") Long id,
                    @Param("new_name") String name,
                    @Param("new_email") String email,
                    @Param("new_password") String password,
                    @Param("new_address") String address);

    @Query(value = "select * from users " +
            "where name like concat('%',:value,'%') " +
            "or email like concat('%',:value,'%')",nativeQuery = true)
    List<User> searchUser(@Param("value") String value);
}
