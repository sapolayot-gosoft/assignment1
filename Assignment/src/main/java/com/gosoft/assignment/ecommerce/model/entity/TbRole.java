package com.gosoft.assignment.ecommerce.model.entity;

import com.gosoft.assignment.ecommerce.model.BaseEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Cacheable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbRole extends BaseEntity {
    @Column
    private String name;
}
