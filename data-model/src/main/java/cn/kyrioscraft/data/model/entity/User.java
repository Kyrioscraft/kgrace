package cn.kyrioscraft.data.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author kyrioscraft
 */
@Entity
@Data
@Table(name = "t_user")
@JsonIgnoreProperties(value = {"password"})
public class User implements Serializable{

    private static final long serialVersionUID = 6466523457695647396L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    //@ManyToMany注释表示Student是多对多关系的一边，mappedBy属性定义了Student为双向关系的维护端
    //Teacher表是关系的维护者，owner side，有主导权，它有个外键指向Student表。
    @ManyToMany(mappedBy = "roleUsers",fetch = FetchType.EAGER)
    private List<SysRole> roles;

    @Column(name = "create_time")
    private Date createTime;

}
