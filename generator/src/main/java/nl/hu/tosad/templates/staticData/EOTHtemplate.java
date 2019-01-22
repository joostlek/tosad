package nl.hu.tosad.templates.staticData;

public class EOTHtemplate {
    String EOTHtemplate = "CREATE OR REPLACE TRIGGER trg_eoth\n" +
            "  before INSERT OR UPDATE\n" +
            "  on medewerker\n" +
            "  FOR EACH ROW\n" +
            "  declare\n" +
            "      v_waarde number;\n" +
            "      e_exception exception;\n" +
            "  begin\n" +
            "    select count(*) into v_waarde from medewerker;\n" +
            "    if (v_waarde > 5) THEN\n" +
            "      raise e_exception;\n" +
            "    END IF;\n" +
            "\n" +
            "    exception\n" +
            "    when e_exception then\n" +
            "    raise_application_error(-20000, 'Not possible bits');\n" +
            "  end;";
}

