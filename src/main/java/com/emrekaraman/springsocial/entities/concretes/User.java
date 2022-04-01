package com.emrekaraman.springsocial.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.swing.text.View;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","flow"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "full_name")
    private String fullname;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "user",targetEntity = Flow.class,fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Flow> flow;
}
