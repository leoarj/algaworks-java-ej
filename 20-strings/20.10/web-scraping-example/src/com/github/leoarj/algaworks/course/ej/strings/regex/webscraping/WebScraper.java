package com.github.leoarj.algaworks.course.ej.strings.regex.webscraping;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebScraper {

    public static void main(String[] args) {
        String html = "<a href=\"mailto:joao@gmail.com\">\n   joao@gmail.com  </a>\n" +
                "<a>\n   abc@algaworks.com</a><a>xyz@algaworks.com</a>" +
                "<strong>maria@algaworks.com</strong>";

        String regex = "<.*?>\\s*(?<email>[\\w.-]+@[a-z\\d.-]+\\.[a-z]{2,3})\\s*</.*?>";
        Pattern pattern = Pattern.compile(regex); // Representação compilada de uma regex.
        Matcher matcher = pattern.matcher(html); // Permite a extração de grupos de uma sequência de caracteres
                                                // com base na compilação da regex anterior.

        while (matcher.find()) {
            //System.out.println(matcher.group(1)); // Grupo começa de 1.
            System.out.println(matcher.group("email")); // Ou pode se nomear o grupo e chamar pelo nome.
        }
    }
}
