package nl.hu.tosad.domain.ruletype;

import nl.hu.tosad.domain.target_database.Dialect;

import javax.persistence.*;

@Entity
public class Template {
    @Id
    @SequenceGenerator(name = "template_id_generator", sequenceName = "temp_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "template_id_generator")
    private Long id;
    private String text;

    @ManyToOne
    @JoinColumn(name = "fk_template_dialect")
    private Dialect dialect;

    @ManyToOne
    @JoinColumn(name = "fk_template_type")
    private BusinessRuleType businessRuleType;

    public Template(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Dialect getDialect() {
        return dialect;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    public BusinessRuleType getBusinessRuleType() {
        return businessRuleType;
    }

    public void setBusinessRuleType(BusinessRuleType businessRuleType) {
        this.businessRuleType = businessRuleType;
    }
}
