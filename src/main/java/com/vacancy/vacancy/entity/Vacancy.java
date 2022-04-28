package com.vacancy.vacancy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vacancy")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vacancyName")
    private String vacancyName;
    @Column(name = "vacancyDescription", columnDefinition = "TEXT")
    private String vacancyDescription;
    @Column(name = "vacancyRequirement", columnDefinition = "TEXT")
    private String vacancyRequirement;
    private Date postDate;
    private String salary;

}
