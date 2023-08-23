package com.github.leoarj.algaworks.course.ej.strings.textblocks.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonScraper {

    public static void main(String[] args) {
        // IntelliJ
        String json = """
                {
                    "id": 1,
                    "nome": "João da Silva"
                },
                {
                    "id": 2,
                    "nome":"Maria Abadia"
                },
                {
                    "id": 3,
                    "nome":
                        "Sebastião Carvalho"
                }""";

        //System.out.println(json);
        //String regex = "\{\}";
        String regex = ".*?\"nome\":.*\\s*\"(.*?)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json);

        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }
        /*// Manual
        json = """
                {
                    "id": 1,
                    "nome": "João da Silva"
                },
                {
                    "id": 2,
                    "nome":"Maria Abadia"
                },
                {
                    "id": 3,
                    "nome":
                        "Sebastião Carvalho"
                }""";
    }*/
}