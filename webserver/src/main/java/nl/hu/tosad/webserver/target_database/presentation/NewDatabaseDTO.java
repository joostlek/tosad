package nl.hu.tosad.webserver.target_database.presentation;

public class NewDatabaseDTO {
    private String name;
    private String jdbcUrl;
    private String username;
    private String password;
    private Long dialectId;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getJdbcUrl(){return jdbcUrl;}

    public void setJdbcUrl(String jdbcUrl) { this.jdbcUrl = jdbcUrl; }

    public String getUsername(){return username;}

    public void setUsername(String username) { this.username = username; }

    public String getPassword(){return password;}

    public void setPassword(String password) { this.password = password; }

    public Long getDialectId(){return dialectId;}

    public void setDialectId(Long dialectId) { this.dialectId = dialectId; }

}
