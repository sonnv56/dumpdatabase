package pojos;

public class Author {
    private String name;
    private String affiliation;
    private String email;

    public Author(String name, String affiliation) {
        this.name = name;
        this.affiliation = affiliation;
    }

    public Author(String name, String affiliation, String email) {
        this.name = name;
        this.affiliation = affiliation;
        this.email = email;
    }

    public Author(String name) {
        this.name = name;
        this.affiliation = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
