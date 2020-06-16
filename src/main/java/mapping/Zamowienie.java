package mapping;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Zamowienie {
    private int idZamowienia;
    private Date dataRealizacji;
    private Klienci klienciByIdKlienta;
    private Obuwie obuwieByIdObuwia;

    @Id
    @Column(name = "id_zamowienia", nullable = false)
    public int getIdZamowienia() {
        return idZamowienia;
    }

    public void setIdZamowienia(int idZamowienia) {
        this.idZamowienia = idZamowienia;
    }

    @Basic
    @Column(name = "data_realizacji", nullable = false)
    public Date getDataRealizacji() {
        return dataRealizacji;
    }

    public void setDataRealizacji(Date dataRealizacji) {
        this.dataRealizacji = dataRealizacji;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zamowienie that = (Zamowienie) o;
        return idZamowienia == that.idZamowienia &&
                Objects.equals(dataRealizacji, that.dataRealizacji);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idZamowienia, dataRealizacji);
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
