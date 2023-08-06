import com.github.leoarj.algaworks.course.ej.collections.maps.ibge.Cidade;
import com.github.leoarj.algaworks.course.ej.collections.maps.ibge.Estado;

import java.util.*;

public class Principal {

    public static void main(String[] args) {
        Estado mg = new Estado("MG", "Minas Gerais");
        Estado ce = new Estado("CE", "Ceará");
        Estado ro = new Estado("RO", "Rondônia");
        Estado sp = new Estado("SP", "São Paulo");

        Cidade saoPaulo = new Cidade(3550308, "São Paulo", 12_396_372);
        Cidade beloHorizonte = new Cidade(3106200, "Belo Horizonte", 2_530_701);
        Cidade fortaleza = new Cidade(2304400, "Fortaleza", 2_703_391);
        Cidade portoVelho = new Cidade(1100205, "Porto Velho", 548_952);

        // TODO Performance é mais importante (não garante a ordem das chaves)
        //Map<Estado, Cidade> capitais = new HashMap<>();

        // TODO Performance é importante, mas precisa manter ordem de inserção das chaves
        //Map<Estado, Cidade> capitais = new LinkedHashMap<>();

        // TODO Performance é menos importante e precisa manter a ordem natural das chaves
        Map<Estado, Cidade> capitais = new TreeMap<>();

        capitais.put(sp, saoPaulo);
        capitais.put(mg, beloHorizonte);
        capitais.put(ce, fortaleza);
        capitais.put(ro, portoVelho);

        // TODO Iteração nas entradas do mapa (chave/valor)
        // Forma mais recomendada
        for (Map.Entry<Estado, Cidade> entrySetCapital : capitais.entrySet()) {
            System.out.printf("Estado = %s (%s), Cidade = %s (Código IBGE = %d)%n",
                    entrySetCapital.getKey().getNome(),
                    entrySetCapital.getKey().getSigla(),
                    entrySetCapital.getValue().getNome(),
                    entrySetCapital.getValue().getCodigoIbge());
        }

        // Outra forma possível
//            Set<Estado> estados = capitais.keySet();
//            for (Estado estado : estados) {
//                Cidade cidade = capitais.get(estado);
//
//                System.out.printf("Estado = %s (%s), Cidade = %s (Código IBGE = %d)%n",
//                        estado.getNome(),
//                        estado.getSigla(),
//                        cidade.getNome(),
//                        cidade.getCodigoIbge());
//            }
        }

}
