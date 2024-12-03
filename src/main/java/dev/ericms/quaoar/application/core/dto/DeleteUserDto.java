package dev.ericms.quaoar.application.core.dto;

import java.util.Objects;

public class DeleteUserDto {

    private String email;

    public DeleteUserDto() {
    }

    public DeleteUserDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DeleteUserDto that = (DeleteUserDto) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public String toString() {
        return "DeleteUserDto{" +
                "email='" + email + '\'' +
                '}';
    }
}
