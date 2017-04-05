package pojos;

public class Author {
    private String name;
    private String affiliation;

    public Author(String name, String affiliation) {
        this.name = name;
        this.affiliation = affiliation;
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
}
