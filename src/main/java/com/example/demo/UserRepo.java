package com.example.demo.repo;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,String > {

    //这个是使用原生的sql语句下面的都是用的jpa里面带的函数
    @Modifying
    @Query(value = "update user set password=?1 where id=?2",nativeQuery = true)
    void updatePwd(String pwd,String id);

    List<UserEntity> findAllBy();

    @Transactional
    void deleteById(String id);

    UserEntity findAllById(String id);
}
