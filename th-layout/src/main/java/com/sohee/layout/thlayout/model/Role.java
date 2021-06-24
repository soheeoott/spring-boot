package com.sohee.layout.thlayout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {
    // 데이터베이스에서 정의한 필드들을 정의
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleid;

    private String rolename;

    @ManyToMany(mappedBy = "roles") // User 클래스에 있는 조인 컬럼
    @JsonIgnore
    private List<User> users;
}