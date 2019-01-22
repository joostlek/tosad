package nl.hu.tosad.generator.utils;

import com.google.gson.Gson;


public class Parse_JSON_to_Int {
    private static int[] toArray(String json, Gson parser) {
        return parser.fromJson(json, int[].class);
    }

    public static void Parser(String json) {
        Gson parser = new Gson();
        int[] arr = toArray(json, parser);
        for(long values : arr){
            System.out.println(values);
        }
    }
    public static void main(String[] args) {
        Parser("[1,2,3,4]");
    }
}
