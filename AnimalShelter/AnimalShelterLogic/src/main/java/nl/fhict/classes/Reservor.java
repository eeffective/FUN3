package nl.fhict.classes;

import java.time.LocalDate;

public class Reservor {
    public String name;
    public LocalDate ReservedAt;

    public Reservor(String name, LocalDate reservedAt) {
        this.name = name;
        ReservedAt = reservedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReservedAt() {
        return ReservedAt;
    }

    public void setReservedAt(LocalDate reservedAt) {
        ReservedAt = reservedAt;
    }
}
