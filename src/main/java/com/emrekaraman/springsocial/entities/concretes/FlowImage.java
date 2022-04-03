package com.emrekaraman.springsocial.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "flow_images")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlowImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER ,targetEntity = Flow.class)
    @JoinColumn(name = "flow_id")
    private Flow flow;
}
