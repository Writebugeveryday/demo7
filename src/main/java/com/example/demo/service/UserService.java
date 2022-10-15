package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepo userRepo;

    /**
     * 返回DTO是让用户看到他能够看到的信息，其他的不给他看
     *
     * 查所有数据
     */
    public List<UserDTO> findAll() {
        List<UserEntity> userEntities = userRepo.findAllBy();   //用户的实体类列表
        List<UserDTO> userDTOList = new ArrayList<>();          //用户DTO列表
        for (UserEntity userEntity : userEntities) {            //增强for循环等价于  for(int i=0;i<useEntities.size();i++)
            UserDTO userDTO = new UserDTO();                    //新建一个空的DTO用来将数据添加到需要返回的List中
            BeanUtils.copyProperties(userEntity, userDTO);      //一个函数，将userEntity中的值复制给userDTO 但是entity和DTO中的
                                                              // 变量名变量类型必须一致，不一致就不会给他复制
//                                                                也可以这样写
//                                                                userDTO.setId(userEntity.getId());
//                                                                userDTO.setUsername(userEntity.getUsername());
            userDTOList.add(userDTO);                      //将DTO添加到list，然后返回结果
        }
        return userDTOList;
    }

    /**
     * 根据用户Id查找数据
     */
    public UserDTO findById(String id) {
        UserEntity userEntity = userRepo.findAllById(id);           //鼠标中键或者cctrl+鼠标左键findAllById  就鞥看这个咋写的
                                                                    //这个就是用的jpa 你可以看图片上的那些函数都是干啥的
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity, userDTO);
        return userDTO;
    }

    /**
     * 删除用户
     */
    public void deleteById(String id) {
        userRepo.deleteById(id);
    }

    /**
     * 修改用户密码
     */
    public String changePwd(String id, String nemPwd) {
        userRepo.updatePwd(nemPwd,id);
        return "修改成功";
    }

    /**
     * 添加用户
     */
    public String addUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);
        log.info(userDTO + "");          //打印日志
        log.info(userEntity + "");

        userEntity.setCreateTime("20221015");
        userEntity.setUpdateTime("20221015");
        userRepo.save(userEntity);
        return "添加成功";
    }

    /**
     * 修改用户用户名，密码
     */
    public String changeAllinfo(String uid, UserDTO userDTO) {
        UserEntity userEntity = userRepo.findAllById(uid);
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setCreateTime("20221016");
        return "修改成功";
    }
}
