package nl.hu.tosad.templates.staticData;

public class ICMPtemplate {
    String ICMPtemplate = "CREATE OR REPLACE TRIGGER trg_icmp\n" +
            "  before INSERT OR UPDATE of STARTSALARIS\n" +
            "  on contract\n" +
            "  FOR EACH ROW\n" +
            "  declare\n" +
            "      v_waarde number;\n" +
            "      e_exception exception;\n" +
            "  begin\n" +
            "    select SALARIS into v_waarde from medewerker where id = 1;\n" +
            "    if (:NEW.STARTSALARIS < v_waarde) THEN\n" +
            "      raise e_exception;\n" +
            "    END IF;\n" +
            "\n" +
            "    exception\n" +
            "    when e_exception then\n" +
            "    raise_application_error(-20000, 'Not possible bits');\n" +
            "  end;";
}
