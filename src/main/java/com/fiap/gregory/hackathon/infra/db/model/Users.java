package com.fiap.gregory.hackathon.infra.db.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "users_tb")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", length = 10, nullable = false)
    String name;

    @Column(name = "email", unique = true, nullable = false)
    String email;

    @Column(name = "password", length = 8, unique = true, nullable = false)
    Integer password;

    @Column(name = "exchange", length = 10, nullable = false)
    String exchange;

}
