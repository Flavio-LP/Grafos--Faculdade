package Arquivos;

public class Rotas {

    int i;
    String retorno;

    public boolean Header_Indent(char palavra[]) { // Testa o cabeçalho
        if (palavra.length <= 9) {
            //System.out.println("Cabeçalho inválido");
            return false;
        } else {
            return true;
        }
    }

    public int PesosNN(char palavra[]) { //- Retorna a quantidade de pesos que foram passados no header do arquivo.
        int tam_palavra = palavra.length;
        String aux;
        if (tam_palavra == 5) {
            return Integer.parseInt(String.valueOf(palavra[4]));
        } else if (tam_palavra == 6) {
            aux = String.valueOf(palavra[4]) + String.valueOf(palavra[5]);
            return Integer.parseInt(aux);
        } else if (tam_palavra == 7) {
            aux = String.valueOf(palavra[4]) + String.valueOf(palavra[5]) + String.valueOf(palavra[6]);
            return Integer.parseInt(aux);
        } else if (tam_palavra == 8) {
            aux = String.valueOf(palavra[4]) + String.valueOf(palavra[5]) + String.valueOf(palavra[6] + String.valueOf(palavra[7]));
            return Integer.parseInt(aux);
        } else {
            aux = String.valueOf(palavra[4]) + String.valueOf(palavra[5]) + String.valueOf(palavra[6] + String.valueOf(palavra[7]) + String.valueOf(palavra[8]));
            return Integer.parseInt(aux);
        }
    }

    public int HeaderNN(char palavra[]) { // Retorna o número de nós dos grafos
        int valor ;
        String aux;
        if (palavra[2] == '0') {
            return Integer.parseInt(String.valueOf(palavra[3]));
        } else {
            aux = String.valueOf(palavra[2]) + String.valueOf(palavra[3]);
            valor = Integer.parseInt(aux);
            return valor;
        }
    }

    public String[] testar_nos(String nos[], char aux_palavra[]) { //- coloca em um vetor os nós passados no arquivo.txt 
        char p, p1 = 0;

        if (nos[0] == null) {
            p = aux_palavra[0];
            nos[0] = String.valueOf(p);
            p = aux_palavra[2];
            nos[1] = String.valueOf(p);
            return nos;
        } else {
            int i = 0, tam = 0, prox, v1 = 0, v2 = 0;
            while (nos[i] != null) {
                tam++;
                i++;

            }
            prox = tam;
            p = aux_palavra[0];
            for (i = 0; i < tam; i++) {
                p1 = aux_palavra[2];
                if (nos[i].equals(String.valueOf(p))) {
                    v1 = 1;
                }
                if (nos[i].equals(String.valueOf(p1))) {
                    v2 = 1;
                }
            }
            if (v1 != 1) {
                nos[prox++] = String.valueOf(p);
            }
            if (v2 != 1) {
                nos[prox++] = String.valueOf(p1);
            }
        }
        return nos;
    }

    public boolean linhas_conexao(int linhas, char palavra[]) { //- testa se a quantidade de linhas do resumo de conexoes é igual a quantidade de linhas passada no trailer.
        String aux;
        if (palavra[2] == '0') {
            if (linhas != Integer.parseInt(String.valueOf(palavra[3]))) {
                return true;
            } else {
                return false;
            }
        } else {
            aux = String.valueOf(palavra[2]) + String.valueOf(palavra[3]);
            if (linhas != Integer.parseInt(aux)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean linhas_pesos(int contador, char palavra[]) { //- Verifica se a quantidade de linhas do resumo de pesos é igual a quantidade passada no trailer.
        String aux;
        if (palavra[5] == '0') {
            if (contador != Integer.parseInt(String.valueOf(palavra[6]))) {
                return true;
            } else {
                return false;
            }
        } else {
            aux = String.valueOf(palavra[5]) + String.valueOf(palavra[6]);
            if (contador != Integer.parseInt(aux)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean contar_nos(String nos[], int valor) { // conta os nós para validar.
        int i = 0, contador = 0;
        while (nos[i] != null) {
            contador++;
            i++;
        }
        if (contador != valor) {
            return false;
        } else {
            return true;
        }
    }

    public boolean testa_conexoes(char palavra[]) { //- Testar a quantidade de caracteres de conexoes
        if (palavra.length > 6) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validar_palavra(char palavra[]) {//- Testa se o remuso de conexões está valido
        int i = 0;
        for (i = 0; i < palavra.length; i++) {
            if (palavra[i] == '=') {
                return true;
            }
        }
        return false;
    }
}
