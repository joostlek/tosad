package nl.hu.tosad.domain.ruletype;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "OPERATOR")
public class Operator {
    @Id
    @SequenceGenerator(name = "operator_id_generator", sequenceName = "operator_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operator_id_generator")
    private Long id;

    @Column(name = "SIGN")
    private String sign;

    @ManyToMany(mappedBy = "availableOperators")
    private List<BusinessRuleType> businessRuleTypes;

    public Operator() {
    }

    public Operator(Long id, String sign) {
        this.id = id;
        this.sign = sign;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<BusinessRuleType> getBusinessRuleTypes() {
        return businessRuleTypes;
    }

    public void setBusinessRuleTypes(List<BusinessRuleType> businessRuleTypes) {
        this.businessRuleTypes = businessRuleTypes;
    }

    @Override
    public String toString() {
        return this.sign;
    }
}
