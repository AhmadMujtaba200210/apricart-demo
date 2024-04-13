package io.engicodes.apricartdemo.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity(name = "`user`")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq"
    )
    @SequenceGenerator(
            name = "user_seq",
            allocationSize = 1
    )
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private boolean isActive;
}

