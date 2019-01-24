package nl.hu.tosad.webserver.target_database;

public class ChosenDatabaseDTO {
    private String databaseName;

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    @Override
    public String toString() {
        return "ChosenDatabase{" +
                "databaseName=" + databaseName +
                '}';
    }
}
