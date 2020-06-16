package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Marka {
    private int idMarki;
    private String nazwa;
    private Collection<Obuwie> obuwiesByIdMarki;

    @Id
    @Column(name = "id_marki", nullable = false)
    public int getIdMarki() {
        return idMarki;
    }

    public void setIdMarki(int idMarki) {
        this.idMarki = idMarki;
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
        Marka marka = (Marka) o;
        return idMarki == marka.idMarki &&
                Objects.equals(nazwa, marka.nazwa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMarki, nazwa);
    }

    @OneToMany(mappedBy = "markaByIdMarki")
    public Collection<Obuwie> getObuwiesByIdMarki() {
        return obuwiesByIdMarki;
    }

    public void setObuwiesByIdMarki(Collection<Obuwie> obuwiesByIdMarki) {
        this.obuwiesByIdMarki = obuwiesByIdMarki;
    }

    @Override
    public String toString() {
        return this.nazwa;

    }
}
