package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Rozmiar {
    private int idRozmiar;
    private double rozmiar;
    private Collection<Obuwie> obuwiesByIdRozmiar;

    @Id
    @Column(name = "id_rozmiar", nullable = false)
    public int getIdRozmiar() {
        return idRozmiar;
    }

    public void setIdRozmiar(int idRozmiar) {
        this.idRozmiar = idRozmiar;
    }

    @Basic
    @Column(name = "rozmiar", nullable = false, precision = 0)
    public double getRozmiar() {
        return rozmiar;
    }

    public void setRozmiar(double rozmiar) {
        this.rozmiar = rozmiar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rozmiar rozmiar1 = (Rozmiar) o;
        return idRozmiar == rozmiar1.idRozmiar &&
                Double.compare(rozmiar1.rozmiar, rozmiar) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRozmiar, rozmiar);
    }

    @OneToMany(mappedBy = "rozmiarByIdRozmiaru")
    public Collection<Obuwie> getObuwiesByIdRozmiar() {
        return obuwiesByIdRozmiar;
    }

    public void setObuwiesByIdRozmiar(Collection<Obuwie> obuwiesByIdRozmiar) {
        this.obuwiesByIdRozmiar = obuwiesByIdRozmiar;
    }
}
