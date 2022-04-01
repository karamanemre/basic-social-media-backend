package com.emrekaraman.springsocial.business.dtos;

import com.emrekaraman.springsocial.entities.concretes.Flow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowImageDto {
    private Long id;
    private String imageUrl;
    private Flow flow;
}
