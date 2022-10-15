package com.example.demo.api;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserApi {

    @Autowired
    UserService userService;
    /*
    查找全部信息
     */
    @Operation(summary = "查询全部信息")
    @GetMapping()
    public List<UserDTO> findAll(){
        return userService.findAll();
    }
    /*
    根据id查找
     */
    @Operation(summary = "根据用户id查询信息")
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable String  id){  //@PathVariable  这个注解是路径取值
        return userService.findById(id);
    }
    /*
    根据id删除用户
     */
    @Operation(summary = "删除用户信息")
    @PostMapping("/{id}/delete")
    public void deleteUser(@PathVariable String id){
        userService.deleteById(id);
    }
    /**
     * 更新用户密码
     */
    @Operation(summary = "更新密码")
    @PostMapping("/{id}")
    public String updatePwd(@PathVariable String id,String newPwd){
        return userService.changePwd(id,newPwd);
    }
    /**
     * 添加用户
     */
    @Operation(summary = "添加用户")
    @PostMapping("/add")
    public String addUser(UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    /**
     * 修改用户名和密码
     */
    @Operation(summary = "修改用户名和密码")
    @PostMapping("/{id}/change")
    public String changePwdAndName(@PathVariable String id, UserDTO userDTO){
        return userService.changeAllinfo(id,userDTO);
    }

}