package nl.hu.tosad.templates.dynamicData;

public class MODItemplate {
    String MODItempalte = "create or replace trigger ARNG\n" +
            "  before update or insert or delete\n" +
            "on <table>, <table>\n" +
            "for each row\n" +
            "declare\n" +
            "begin\n" +
            "  if ()){\n" +
            "  return true;\n" +
            "  }\n" +
            "  else return false;\n" +
            "\n" +
            "  exception when no_data_found then return false;\n" +
            "end;";
}
