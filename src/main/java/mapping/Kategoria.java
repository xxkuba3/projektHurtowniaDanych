package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Kategoria {
    private int idKategorii;
    private String nazwa;
    private Collection<Obuwie> obuwiesByIdKategorii;

    @Id
    @Column(name = "id_kategorii", nullable = false)
    public int getIdKategorii() {
        return idKategorii;
    }

    public void setIdKategorii(int idKategorii) {
        this.idKategorii = idKategorii;
    }

    @Basic
    @Column(name = "nazwa", nullable = false, length = 20)
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kategoria kategoria = (Kategoria) o;
        return idKategorii == kategoria.idKategorii &&
                Objects.equals(nazwa, kategoria.nazwa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKategorii, nazwa);
    }

    @OneToMany(mappedBy = "kategoriaByIdKategorii")
    public Collection<Obuwie> getObuwiesByIdKategorii() {
        return obuwiesByIdKategorii;
    }

    public void setObuwiesByIdKategorii(Collection<Obuwie> obuwiesByIdKategorii) {
        this.obuwiesByIdKategorii = obuwiesByIdKategorii;
    }

    @Override
    public String toString() {
        return this.nazwa;
    }
}

