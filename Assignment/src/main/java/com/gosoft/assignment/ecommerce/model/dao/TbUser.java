package com.gosoft.assignment.ecommerce.model.dao;

import com.gosoft.assignment.ecommerce.model.BaseEntity;
import com.gosoft.assignment.ecommerce.model.dto.response.MediaFileResponseDTO;
import com.gosoft.assignment.ecommerce.model.dto.response.MediaFileUserResponseDTO;
import com.gosoft.assignment.ecommerce.model.dto.response.UserResponseDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@Cacheable
@NoArgsConstructor
public class TbUser extends BaseEntity {

    @Column(unique = true)
    private String username;

    @Column
    private String phone;

    @Column
    private String password;

    @Column
    private String fullName;

    @Column(nullable = true, unique = true)
    private String refreshToken;

    @Column(nullable = true)
    private Date expiryRefreshTokenDate;

    @ManyToMany
    @JoinTable
    private List<TbRole> roles;

    @OneToMany(mappedBy = "tbUser")
    private List<TbMediaFile> mediaFile;


    public TbUser(String username, String phone, String password) {
        this.username = username;
        this.phone = phone;
        this.password = password;
    }

    public UserResponseDTO toDTO () {
        return new UserResponseDTO();
    }

    public MediaFileUserResponseDTO toMediaFileShortDTO() {
        return new MediaFileUserResponseDTO(
                username
        );
    }
}
