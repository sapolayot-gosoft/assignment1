package com.gosoft.assignment.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gosoft.assignment.ecommerce.model.BaseEntity;
import com.gosoft.assignment.ecommerce.model.dto.response.MediaFileResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Cacheable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
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
    private Long size;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    private TbUser tbUser;

    public MediaFileResponseDTO toDTO (){
        return new MediaFileResponseDTO(name, url, contentType, type, size, tbUser.toMediaFileShortDTO());
    }

}
