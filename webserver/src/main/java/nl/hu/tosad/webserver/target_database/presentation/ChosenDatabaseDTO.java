package nl.hu.tosad.webserver.target_database.presentation;

public class ChosenDatabaseDTO {
    private Long databaseId;

    public Long getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(Long databaseId) {
        this.databaseId = databaseId;
    }

    @Override
    public String toString() {
        return "ChosenDatabase{" +
                "databaseId=" + databaseId +
                '}';
    }
}
