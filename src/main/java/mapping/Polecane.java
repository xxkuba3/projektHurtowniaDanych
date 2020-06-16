package mapping;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Polecane {
    private int idPolecane;
    private Klienci klienciByIdKlienta;
    private Obuwie obuwieByIdObuwia;

    @Id
    @Column(name = "id_polecane", nullable = false)
    public int getIdPolecane() {
        return idPolecane;
    }

    public void setIdPolecane(int idPolecane) {
        this.idPolecane = idPolecane;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polecane polecane = (Polecane) o;
        return idPolecane == polecane.idPolecane;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPolecane);
    }

    @ManyToOne
    @JoinColumn(name = "id_klienta", referencedColumnName = "id_klienta", nullable = false)
    public Klienci getKlienciByIdKlienta() {
        return klienciByIdKlienta;
    }

    public void setKlienciByIdKlienta(Klienci klienciByIdKlienta) {
        this.klienciByIdKlienta = klienciByIdKlienta;
    }

    @ManyToOne
    @JoinColumn(name = "id_obuwia", referencedColumnName = "id_obuwia", nullable = false)
    public Obuwie getObuwieByIdObuwia() {
        return obuwieByIdObuwia;
    }

    public void setObuwieByIdObuwia(Obuwie obuwieByIdObuwia) {
        this.obuwieByIdObuwia = obuwieByIdObuwia;
    }
}
