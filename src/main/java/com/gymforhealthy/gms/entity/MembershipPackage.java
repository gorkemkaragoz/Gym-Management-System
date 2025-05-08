package com.gymforhealthy.gms.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "membership_packages")
public class MembershipPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "package_total_hour")
    private Integer packageTotalHour;

    @Column(name = "is_unlimited")
    private Boolean isUnlimited;

    @OneToMany(mappedBy = "membershipPackage", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Membership> memberships;

}