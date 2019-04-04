package org.acme.config;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.runtime.JpaOperations;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Person extends PanacheEntity {

    public String name;
    public LocalDate birth;

//    Person() {
//        super();
//    }
//
//    Person(String name, LocalDate birth) {
//        super();
//        this.name = name;
//        this.birth = birth;
//    }

    @JsonbTransient
    public long getId() {
        return this.id;
    }

    @JsonbTransient
    public boolean isPersistent() {
        return super.isPersistent();
    }

}
