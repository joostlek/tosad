package nl.hu.tosad.templates.staticData;

public class ACMPtemplate {
    String ACMPtemplate = "CREATE OR REPLACE TRIGGER tgr_acmp\n" +
            "before insert or update of kinderen\n" +
            "  on MEDEWERKER\n" +
            "  for each row\n" +
            "  declare\n" +
            "      e_exception exception;\n" +
            "    begin\n" +
            "  if(:NEW.kinderen < 0) then\n" +
            "    raise e_exception;\n" +
            "  end if;\n" +
            "    exception\n" +
            "    when e_exception then\n" +
            "    raise_application_error(-20000, 'Value must be 0 or above');\n" +
            "end;";
}
