package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Obuwie {
    private int idObuwia;
    private float cenaButa;
    private int rokProdukcji;
    private String nazwa;
    private String zdjecie;
    private int dostepnaIlosc;
    private Marka markaByIdMarki;
    private Kategoria kategoriaByIdKategorii;
    private Rozmiar rozmiarByIdRozmiaru;
    private Collection<Polecane> polecanesByIdObuwia;
    private Collection<Zamowienie> zamowieniesByIdObuwia;

    @Id
    @Column(name = "id_obuwia", nullable = false)
    public int getIdObuwia() {
        return idObuwia;
    }

    public void setIdObuwia(int idObuwia) {
        this.idObuwia = idObuwia;
    }

    @Basic
    @Column(name = "cena_buta", nullable = false, precision = 0)
    public float getCenaButa() {
        return cenaButa;
    }

    public void setCenaButa(float cenaButa) {
        this.cenaButa = cenaButa;
    }

    @Basic
    @Column(name = "rok_produkcji", nullable = false)
    public int getRokProdukcji() {
        return rokProdukcji;
    }

    public void setRokProdukcji(int rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    @Basic
    @Column(name = "nazwa", nullable = false, length = 15)
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Basic
    @Column(name = "zdjecie", nullable = false, length = 255)
    public String getZdjecie() {
        return zdjecie;
    }

    public void setZdjecie(String zdjecie) {
        this.zdjecie = zdjecie;
    }

    @Basic
    @Column(name = "dostepna_ilosc", nullable = false)
    public int getDostepnaIlosc() {
        return dostepnaIlosc;
    }

    public void setDostepnaIlosc(int dostepnaIlosc) {
        this.dostepnaIlosc = dostepnaIlosc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Obuwie obuwie = (Obuwie) o;
        return idObuwia == obuwie.idObuwia &&
                Float.compare(obuwie.cenaButa, cenaButa) == 0 &&
                rokProdukcji == obuwie.rokProdukcji &&
                dostepnaIlosc == obuwie.dostepnaIlosc &&
                Objects.equals(nazwa, obuwie.nazwa) &&
                Objects.equals(zdjecie, obuwie.zdjecie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idObuwia, cenaButa, rokProdukcji, nazwa, zdjecie, dostepnaIlosc);
    }

    @ManyToOne
    @JoinColumn(name = "id_marki", referencedColumnName = "id_marki", nullable = false)
    public Marka getMarkaByIdMarki() {
        return markaByIdMarki;
    }

    public void setMarkaByIdMarki(Marka markaByIdMarki) {
        this.markaByIdMarki = markaByIdMarki;
    }

    @ManyToOne
    @JoinColumn(name = "id_kategorii", referencedColumnName = "id_kategorii", nullable = false)
    public Kategoria getKategoriaByIdKategorii() {
        return kategoriaByIdKategorii;
    }

    public void setKategoriaByIdKategorii(Kategoria kategoriaByIdKategorii) {
        this.kategoriaByIdKategorii = kategoriaByIdKategorii;
    }

    @ManyToOne
    @JoinColumn(name = "id_rozmiaru", referencedColumnName = "id_rozmiar", nullable = false)
    public Rozmiar getRozmiarByIdRozmiaru() {
        return rozmiarByIdRozmiaru;
    }

    public void setRozmiarByIdRozmiaru(Rozmiar rozmiarByIdRozmiaru) {
        this.rozmiarByIdRozmiaru = rozmiarByIdRozmiaru;
    }

    @OneToMany(mappedBy = "obuwieByIdObuwia")
    public Collection<Polecane> getPolecanesByIdObuwia() {
        return polecanesByIdObuwia;
    }

    public void setPolecanesByIdObuwia(Collection<Polecane> polecanesByIdObuwia) {
        this.polecanesByIdObuwia = polecanesByIdObuwia;
    }

    @OneToMany(mappedBy = "obuwieByIdObuwia")
    public Collection<Zamowienie> getZamowieniesByIdObuwia() {
        return zamowieniesByIdObuwia;
    }

    public void setZamowieniesByIdObuwia(Collection<Zamowienie> zamowieniesByIdObuwia) {
        this.zamowieniesByIdObuwia = zamowieniesByIdObuwia;
    }

    @Override
    public String toString() {
        return this.nazwa;
    }
}
