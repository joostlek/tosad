package nl.hu.tosad.templates.staticData;

public class TOTHtemplate {
    String TOTHtemplate = "CREATE OR REPLACE TRIGGER trg_toth\n" +
            "  before INSERT OR UPDATE of FUNCTIE, SALARIS\n" +
            "  on MEDEWERKER\n" +
            "  FOR EACH ROW\n" +
            "  declare\n" +
            "      e_exception exception;\n" +
            "  begin\n" +
            "    if (:NEW.FUNCTIE = 'POETS' AND :NEW.SALARIS > 2000) THEN\n" +
            "      raise e_exception;\n" +
            "    END IF;\n" +
            "\n" +
            "    exception\n" +
            "    when e_exception then\n" +
            "    raise_application_error(-20000, 'Wat is nu het verschil joh kech');\n" +
            "  end;";
}
