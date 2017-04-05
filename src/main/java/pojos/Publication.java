package pojos;

import java.util.List;

public class Publication {
    private String title;
    private String abstracts;
    private String uri;
    private List<Author> authors;
    private String journalName;
    private String volume;
    private String number;
    private int citedNumber;
    private int year;
    private String source;
    private String createdAt;
    private String modifiedIn;
    private boolean active;
    private boolean usable;
    private String DOI;
    private String clusterId;
    private String citeId;
    private String citedList;

    public Publication(String title, String abstracts, String uri, List<Author> authors, String journalName, String volume,
                       String number, int year, String source, boolean usable, String DOI, String citedList) {
        this.title = title;
        this.abstracts = abstracts;
        this.uri = uri;
        this.authors = authors;
        this.journalName = journalName;
        this.volume = volume;
        this.number = number;
        this.year = year;
        this.source = source;
        this.modifiedIn = this.createdAt;
        this.active = true;
        this.usable = usable;
        this.DOI = DOI;
        this.clusterId = "";
        this.citeId = "";
        this.citedList = citedList;
    }

    public Publication(String title, String abstracts, String uri, List<Author> authors, String journalName, String volume, String number,
                       int year, String source, boolean usable, String DOI, String clusterId, String citeId, String citedList) {
        this.title = title;
        this.abstracts = abstracts;
        this.uri = uri;
        this.authors = authors;
        this.journalName = journalName;
        this.volume = volume;
        this.number = number;
        this.year = year;
        this.source = source;
        this.modifiedIn = this.createdAt;
        this.active = true;
        this.usable = usable;
        this.DOI = DOI;
        this.clusterId = clusterId;
        this.citeId = citeId;
        this.citedList = citedList;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public String getJournalName() {
        return journalName;
    }

    public String getVolume() {
        return volume;
    }

    public String getNumber() {
        return number;
    }

    public int getYear() {
        return year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCitedNumber() {
        return citedNumber;
    }

    public void setCitedNumber(int citedNumber) {
        this.citedNumber = citedNumber;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedIn() {
        return modifiedIn;
    }

    public void setModifiedIn(String modifiedIn) {
        this.modifiedIn = modifiedIn;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public String getDOI() {
        return DOI;
    }

    public void setDOI(String DOI) {
        this.DOI = DOI;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getCiteId() {
        return citeId;
    }

    public void setCiteId(String citeId) {
        this.citeId = citeId;
    }

    public String getCitedList() {
        return citedList;
    }

    public void setCitedList(String citedList) {
        this.citedList = citedList;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "title='" + title + '\'' +
                ", abstracts='" + abstracts + '\'' +
                ", uri='" + uri + '\'' +
                ", authors=" + authors +
                ", journalName='" + journalName + '\'' +
                ", volume='" + volume + '\'' +
                ", number='" + number + '\'' +
                ", citedNumber=" + citedNumber +
                ", year=" + year +
                ", source='" + source + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", modifiedIn='" + modifiedIn + '\'' +
                ", active=" + active +
                ", usable=" + usable +
                ", DOI='" + DOI + '\'' +
                ", clusterId='" + clusterId + '\'' +
                ", citeId='" + citeId + '\'' +
                ", citedList=" + citedList +
                '}';
    }
}
