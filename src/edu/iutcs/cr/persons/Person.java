package edu.iutcs.cr.persons;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String id;
    private String email;

    public Person() {
    }

    public Person(String id) {
        this.id = validateId(id);
    }

    public Person(String id, String name, String email) {
        this.id = validateId(id);
        this.name = validateName(name);
        this.email = validateEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = validateName(name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = validateId(id);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = validateEmail(email);
    }

    private String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is mandatory");
        }
        return name.trim();
    }

    private String validateId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id is mandatory");
        }
        return id.trim();
    }

    private String validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is mandatory");
        }
        return email.trim();
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}