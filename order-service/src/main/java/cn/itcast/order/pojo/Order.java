package cn.itcast.order.pojo;

import com.xiazs.API.pojo.User;
import lombok.Data;

@Data
public class Order {
    private Long id;
    private Long price;
    private String name;
    private Integer num;
    private Long userId;
    private User user;

    public void setUser(User user) {
    }
}