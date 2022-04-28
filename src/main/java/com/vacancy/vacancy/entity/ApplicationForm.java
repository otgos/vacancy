package com.vacancy.vacancy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="application")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationForm{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="email", unique = true)
    private String email;
    @Column(name="fullname")
    private String fullName;
    @Column(name="phonenumber")
    private String phoneNumber;
    private String profession;
    private Date applyDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private Vacancy vacancy;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
