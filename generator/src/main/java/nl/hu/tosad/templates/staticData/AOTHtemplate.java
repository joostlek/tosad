package nl.hu.tosad.templates.staticData;

public class AOTHtemplate {
    String AOTHtemplate = "CREATE OR REPLACE TRIGGER trg_aoth\n" +
            "  before INSERT OR UPDATE of postcode\n" +
            "  on MEDEWERKER\n" +
            "  FOR EACH ROW\n" +
            "  declare\n" +
            "      e_exception exception;\n" +
            "  begin\n" +
            "    if (SUBSTR(:NEW.postcode, 1, 1) not between '1' and '9') THEN\n" +
            "      raise e_exception;\n" +
            "    END IF;\n" +
            "\n" +
            "    exception\n" +
            "    when e_exception then\n" +
            "    raise_application_error(-20000, 'First characters must be a number');\n" +
            "  end;";
}
