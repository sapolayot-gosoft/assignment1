package com.gosoft.assignment.ecommerce.security.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gosoft.assignment.ecommerce.model.BaseEntity;
import com.gosoft.assignment.ecommerce.model.entity.TbUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@Cacheable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbRefreshToken extends BaseEntity {

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private TbUser tbUser;

    @Column(nullable = false, unique = true)
    private String refreshToken;

    @Column(nullable = false)
    private Date expiryRefreshTokenDate;

}
