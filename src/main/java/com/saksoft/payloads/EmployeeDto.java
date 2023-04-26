package com.saksoft.payloads;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Data
public class EmployeeDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
}
