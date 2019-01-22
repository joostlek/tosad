package nl.hu.tosad.templates.staticData;

public class ARNGtemplate {
    String ARNGtemplate = "create or replace trigger trg_arng\n" +
            "before INSERT OR UPDATE ON MEDEWERKER\n" +
            "    FOR EACH ROW\n" +
            "declare\n" +
            "    e_exception exception;\n" +
            "begin\n" +
            "  if (:NEW.ID not between 1 AND 10) THEN\n" +
            "    raise e_exception;\n" +
            "  END IF;\n" +
            "\n" +
            "  exception\n" +
            "  when e_exception then\n" +
            "  raise_application_error(-20000, 'Value must be between 1 and 10');\n" +
            "end;";
}