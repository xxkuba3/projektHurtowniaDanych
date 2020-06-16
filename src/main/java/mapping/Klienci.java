package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Klienci {
    private int idKlienta;
    private String login;
    private String haslo;
    private String ulica;
    private String nrDomu;
    private String nazwisko;
    private String miejscowosc;
    private String imie;
    private String kodPocztowy;
    private String pesel;
    private String telefon;
    private int wiek;
    private String plec;
    private Collection<Polecane> polecanesByIdKlienta;
    private Collection<Zamowienie> zamowieniesByIdKlienta;

    @Id
    @Column(name = "id_klienta", nullable = false)
    public int getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(int idKlienta) {
        this.idKlienta = idKlienta;
    }

    @Basic
    @Column(name = "login", nullable = false, length = 10)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "haslo", nullable = false, length = 10)
    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    @Basic
    @Column(name = "ulica", nullable = false, length = 20)
    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    @Basic
    @Column(name = "nr_domu", nullable = false, length = 10)
    public String getNrDomu() {
        return nrDomu;
    }

    public void setNrDomu(String nrDomu) {
        this.nrDomu = nrDomu;
    }

    @Basic
    @Column(name = "nazwisko", nullable = false, length = 20)
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Basic
    @Column(name = "miejscowosc", nullable = false, length = 25)
    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    @Basic
    @Column(name = "imie", nullable = false, length = 20)
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Basic
    @Column(name = "kod_pocztowy", nullable = false, length = 20)
    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    @Basic
    @Column(name = "pesel", nullable = false, length = -1)
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Basic
    @Column(name = "telefon", nullable = false, length = -1)
    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Basic
    @Column(name = "wiek", nullable = false)
    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    @Basic
    @Column(name = "plec", nullable = false, length = 1)
    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Klienci klienci = (Klienci) o;
        return idKlienta == klienci.idKlienta &&
                wiek == klienci.wiek &&
                Objects.equals(login, klienci.login) &&
                Objects.equals(haslo, klienci.haslo) &&
                Objects.equals(ulica, klienci.ulica) &&
                Objects.equals(nrDomu, klienci.nrDomu) &&
                Objects.equals(nazwisko, klienci.nazwisko) &&
                Objects.equals(miejscowosc, klienci.miejscowosc) &&
                Objects.equals(imie, klienci.imie) &&
                Objects.equals(kodPocztowy, klienci.kodPocztowy) &&
                Objects.equals(pesel, klienci.pesel) &&
                Objects.equals(telefon, klienci.telefon) &&
                Objects.equals(plec, klienci.plec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKlienta, login, haslo, ulica, nrDomu, nazwisko, miejscowosc, imie, kodPocztowy, pesel, telefon, wiek, plec);
    }

    @OneToMany(mappedBy = "klienciByIdKlienta")
    public Collection<Polecane> getPolecanesByIdKlienta() {
        return polecanesByIdKlienta;
    }

    public void setPolecanesByIdKlienta(Collection<Polecane> polecanesByIdKlienta) {
        this.polecanesByIdKlienta = polecanesByIdKlienta;
    }

    @OneToMany(mappedBy = "klienciByIdKlienta")
    public Collection<Zamowienie> getZamowieniesByIdKlienta() {
        return zamowieniesByIdKlienta;
    }

    public void setZamowieniesByIdKlienta(Collection<Zamowienie> zamowieniesByIdKlienta) {
        this.zamowieniesByIdKlienta = zamowieniesByIdKlienta;
    }
}
