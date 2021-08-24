package Arquivos;

public class Main {

    public static void main(String[] args) {
        Leitura leitura = new Leitura();
        LeituraNN leiturann = new LeituraNN();

        leitura.ler_config();
        leiturann.ler_rota(leitura.get_dir1(), leitura.get_dir2());
    }
}

// Desenvolvido por Flávio Leandro Pirola e João Vitor Silva de Bitencourt
