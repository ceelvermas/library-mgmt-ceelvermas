package com.lms.domain;

import com.lms.domain.enumaration.MemberType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created By CL Verma on 10/4/20
 */

@Entity
@Table(name = "member")
public class Member implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column( name = "first_name", nullable = false)
    private String firstName;

    @Column( name = "last_name")
    private String lastName;

    @Column( name = "email")
    private String email;

    @NotNull
    @Column( name = "mobile", nullable = false)
    private String mobile;

    @NotNull
    @Column( name = "gender", nullable = false)
    private String gender;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column( name = "member_type", nullable = false)
    private MemberType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id) &&
                Objects.equals(firstName, member.firstName) &&
                Objects.equals(lastName, member.lastName) &&
                Objects.equals(email, member.email) &&
                Objects.equals(mobile, member.mobile) &&
                Objects.equals(gender, member.gender) &&
                Objects.equals(type, member.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, email, mobile, gender, type);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender='" + gender + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
