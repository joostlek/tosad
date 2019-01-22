package nl.hu.tosad.templates.staticData;

public class ALIStemplate {
    String ALIStemplate = "CREATE OR REPLACE TRIGGER trg_alis\n" +
            "  before INSERT OR UPDATE of naam\n" +
            "  on MEDEWERKER\n" +
            "  FOR EACH ROW\n" +
            "  declare\n" +
            "      e_exception exception;\n" +
            "  begin\n" +
            "    if (:NEW.naam not in ('kek', '420', 'lol')) THEN\n" +
            "      raise e_exception;\n" +
            "    END IF;\n" +
            "\n" +
            "    exception\n" +
            "    when e_exception then\n" +
            "    raise_application_error(-20000, 'Value must be 420, kek or lol');\n" +
            "  end;";
}
