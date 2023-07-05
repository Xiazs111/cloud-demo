package com.xiazs.API.clients;



import com.xiazs.API.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("userservice")
public interface UserClient {

    @GetMapping("/userservice/{id}")
    User findById(@PathVariable("id") Long id);

}
