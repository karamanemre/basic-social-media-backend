package com.emrekaraman.springsocial.business.dtos;

import com.emrekaraman.springsocial.entities.concretes.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowDto {

    private Long id;

    @NotEmpty
    private String content;

    private User user;
}
