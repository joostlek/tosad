package nl.hu.tosad.domain.rule;


import nl.hu.tosad.domain.target_database.DbColumn;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "VALUE")
public class Value {
    @Id
    @SequenceGenerator(name = "value_id_generator", sequenceName = "value_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "value_id_generator")
    private Long id;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "POSITION")
    private String position;

    @ManyToOne
    @JoinColumn(name = "fk_business_rule")
    private BusinessRule businessRule;

    @ManyToOne
    @JoinColumn(name = "fk_value_column")
    private DbColumn column;

    public Value() {
    }

    public Value(String position, DbColumn column) {
        this.position = position;
        this.type = "column";
        this.column = column;
    }

    public Value(String value, String type, String position) {
        this.value = value;
        this.type = type;
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public DbColumn getColumn() {
        return column;
    }

    public void setColumn(DbColumn dbColumn) {
        this.column = dbColumn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusinessRule getBusinessRule() {
        return businessRule;
    }

    public void setBusinessRule(BusinessRule businessRule) {
        this.businessRule = businessRule;
    }

    @Override
    public String toString() {
        if (type.equalsIgnoreCase("VARCHAR2")) {
            return "\"" + this.value + "\"";
        } else if (type.equalsIgnoreCase("NUMBER")) {
            return this.value;
        } else if (type.equalsIgnoreCase("COLUMN")) {
            return this.column.getName();
        }
        return this.value;
    }

    public String getLabel() {
        Pattern pattern = Pattern.compile("([a-zA-Z]*)_");
        Matcher matcher = pattern.matcher(this.position);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
