package com.gosoft.assignment.ecommerce.model.entity;

import com.gosoft.assignment.ecommerce.model.BaseEntity;
import com.gosoft.assignment.ecommerce.model.dto.response.MediaFileUserResponseDTO;
import com.gosoft.assignment.ecommerce.model.dto.response.UserResponseDTO;
import com.gosoft.assignment.ecommerce.security.dao.TbRefreshToken;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @JoinTable
    @ManyToMany(cascade = CascadeType.ALL)
    private List<TbRole> roles;

    @OneToMany(mappedBy = "tbUser", cascade = CascadeType.MERGE)
    private List<TbMediaFile> mediaFile;

    @OneToOne(mappedBy = "tbUser", cascade = CascadeType.ALL)
    private TbRefreshToken refreshToken;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<TbOrder> orderBuyer;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<TbOrder> orderSeller;

    public TbUser(String username, String phone, String password) {
        this.username = username;
        this.phone = phone;
        this.password = password;
    }

    public UserResponseDTO toDTO() {
        TbMediaFile mediaFile = this.mediaFile.stream()
                .filter(file -> file.getType().equals("profile"))
                .findFirst()
                .orElse(new TbMediaFile());
        return new UserResponseDTO(id, username, fullName, phone, mediaFile.getUrl());
    }

    public MediaFileUserResponseDTO toMediaFileShortDTO() {
        return new MediaFileUserResponseDTO(
                username
        );
    }
}
