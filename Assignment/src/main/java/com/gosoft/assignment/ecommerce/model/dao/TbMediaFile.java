package com.gosoft.assignment.ecommerce.model.dao;

import com.gosoft.assignment.ecommerce.model.BaseEntity;
import com.gosoft.assignment.ecommerce.model.dto.response.MediaFileResponseDTO;
import jakarta.persistence.*;

@Entity
@Table
@Cacheable
public class TbMediaFile extends BaseEntity {

    @Column
    private String name;

    @Column
    private String url;

    @Column
    private String contentType;

    @Column
    private String type;

    @Column
    private String size;

    @ManyToOne
    private TbUser tbUser;

    public MediaFileResponseDTO toDTO (){
        return new MediaFileResponseDTO(name, url, contentType, type, tbUser.toMediaFileShortDTO());
    }

}
