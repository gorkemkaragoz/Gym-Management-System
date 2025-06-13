package com.gymforhealthy.gms.dto.responseDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrolledStudentDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private Double bmi;     // ileride eklenecek
    private Double weight;  // ileride eklenecek
}
