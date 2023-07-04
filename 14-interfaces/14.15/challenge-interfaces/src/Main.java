import com.github.leoarj.algaworks.course.ej.polimorfism.interfaces.model.Caminhao;
import com.github.leoarj.algaworks.course.ej.polimorfism.interfaces.model.CarroParticular;
import com.github.leoarj.algaworks.course.ej.polimorfism.interfaces.model.ImovelResidencial;
import com.github.leoarj.algaworks.course.ej.polimorfism.interfaces.service.ServicoPropostaSeguro;

public class Main {
    public static void main(String[] args) {
        ServicoPropostaSeguro servicoPropostaSeguro = new ServicoPropostaSeguro();

        CarroParticular carro = new CarroParticular("HR-V", 150_000, 2022);
        Caminhao caminhao = new Caminhao("Actros", 780_000, 2021, 4);
        ImovelResidencial imovel = new ImovelResidencial(2_300_000, 320);

        servicoPropostaSeguro.emitir(carro);
        servicoPropostaSeguro.emitir(caminhao);
        servicoPropostaSeguro.emitir(imovel);
    }
}