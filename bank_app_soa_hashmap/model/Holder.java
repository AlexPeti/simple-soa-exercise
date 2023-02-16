package gr.bank_app_soa_hashmap.model;

public class Holder extends AbstractEntity {
    private String firstname;
    private String lastname;
    private String ssn;

    public Holder() {}

    public Holder(String firstname, String lastname, String ssn) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Holder holder = (Holder) o;

        if (!getFirstname().equals(holder.getFirstname())) return false;
        if (!getLastname().equals(holder.getLastname())) return false;
        return getSsn().equals(holder.getSsn());
    }

    @Override
    public int hashCode() {
        int result = getFirstname().hashCode();
        result = 31 * result + getLastname().hashCode();
        result = 31 * result + getSsn().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Holder{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", ssn='" + ssn + '\'' +
                '}';
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }
}
