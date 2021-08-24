package Arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LeituraNN {

    Rotas rotas = new Rotas();

    public void ler_rota(String rota1, String rota2) { // rota1 - processado, rota2- não processado
        try {
            String linha = null, valido, aux_vetor[] = null;
            String[] nos = new String[50];
            nos[0] = null;
            boolean qtde_conexoes = false;
            int n = 1, valor = 0, pesos = 0, soma_pesos = 0, contador_conexoes = 0, contador_pesos = 0;
            File arq = new File("");
            arq = Gerar_Arq(n, arq);
            while (arq != null) {
                valor = 0;
                pesos = 0;
                char palavra[] = null, aux_palavra[] = null, p, p1 = 0;
                String aux = null;
                boolean nos_valido, soma_valida, Header_invalido = true, conexoes = true, linhas_conex = true, linhas_pesos = true, linhas_ps_cx = true, verificador, Rconex = true;
                InputStream is = new FileInputStream(arq);
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader buffer = new BufferedReader(isr);
                //linha=buffer.readLine();
                while ((linha = buffer.readLine()) != null) {
                    palavra = linha.toCharArray();
                    if (linha.startsWith("00")) { // seleciona a linha que começa com 00. - Headder
                        valor = rotas.HeaderNN(palavra); // retorna o valor de nos que foi passado no arquivo.
                        pesos = rotas.PesosNN(palavra); //- retorna o valor de pesos que foi passado no arquivo.
                        Header_invalido = rotas.Header_Indent(palavra);// Verifica se o Header possui a quantidade de caracteres correta.
                    }
                    if (linha.startsWith("01")) { // seleciona a linha que começa com 01. - Resumo de conexoes
                        contador_conexoes++;
                        aux_vetor = linha.split("01");
                        aux = aux_vetor[1];
                        aux_palavra = aux.toCharArray();
                        rotas.testar_nos(nos, aux_palavra); //- lê o arquivo e coloca os nos num vetor pra validar a quantidade.
                        conexoes = rotas.testa_conexoes(palavra); //- verifica se a linha está com a quantidade de caracteres correta.
                        Rconex = rotas.validar_palavra(palavra); //- Valida se a sintaxe da linha está correta.
                        if (conexoes) {
                            qtde_conexoes = true;
                        }
                    }
                    if (linha.startsWith("02")) { //  seleciona a linha que começa com 02. - Resumo de Pesos
                        contador_pesos++;
                        aux_vetor = linha.split("=");
                        aux = aux_vetor[1];
                        soma_pesos = soma_pesos + Integer.parseInt(aux); //- Soma a quantiade de pesos para verificar se está correto.
                    }
                    if (linha.startsWith("09")) {
                        linhas_conex = rotas.linhas_conexao(contador_conexoes, palavra); // Verifica se as linhas do resumo de conexoes é igual a quantidade de linhas passada no trailer.
                        linhas_pesos = rotas.linhas_pesos(contador_pesos, palavra); // Verifica se a quantidade de linhas do resumo de pesos é igual a quantidade passada no trailer.
                    }
                }

                buffer.close();
                nos_valido = rotas.contar_nos(nos, valor);//valida a quantidade de nos
                soma_valida = verifica_soma_pesos(soma_pesos, pesos);//verifica se a soma dos pesos é válida.
                verificador = verifica_arquivo(Rconex, linhas_pesos, linhas_conex, qtde_conexoes, Header_invalido, nos_valido, soma_valida, arq);//- verifica os teste e retorna um valor booleano
                if (verificador) {
                    Mover_Arq(arq, rota2); //move para não processado!
                    System.out.println("Arquivo: " + arq.getName() + ", movido para não processado");
                } else {
                    Mover_Arq(arq, rota1); //move para processado! 
                    System.out.println("Arquivo: " + arq.getName() + ", movido para processado");
                }
                n++;
                arq = Gerar_Arq(n, arq);    // função testa se o arquivo existe;
                contador_conexoes = 0;
                contador_pesos = 0;
                soma_pesos = 0;
                pesos = 0;
                valor = 0;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File Gerar_Arq(int n, File Arq) {//Localiza o arquivo e devolve caso exista.
        if (n < 10) {
            File arq = new File("C:\\Teste\\rotas0" + n + ".txt");
            if (arq.exists()) {
                n++;
                return arq;
            }
        } else {
            File arq = new File("C:\\Teste\\rotas" + n + ".txt");
            if (arq.exists()) {
                n++;
                return arq;
            }
        }
        return Arq = null;
    }

    public void Mover_Arq(File arq, String rota) { //- move o arquivo para uma rota.
        arq.renameTo(new File(rota, arq.getName()));
    }

    public boolean verifica_soma_pesos(int soma, int pesos) { // verifica se a soma dos pesos  passado no header e no resumo de pesos está correto.
        if (soma == pesos) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verifica_arquivo(boolean Rconex, boolean linhas_pesos, boolean linhas_conex, boolean qtde_conexoes, boolean Header_invalido, boolean nos_valido, boolean soma_valida, File arq) { //- retorna os testes dos roteiro
        if (!Rconex) {
            System.out.println("Arquivo: " + arq.getName() + ", Resumo de conexões inválido. ");
        }
        if (linhas_pesos) {
            System.out.println("Arquivo: " + arq.getName() + ", Número de linhas de pesos difeente da quantidade do Trailer");
        }/*else{
            System.out.println("Arquivo: "+arq.getName()+", ok! ,Numero de linhas de pesos iguais a quantidade do Trailer");
        }*/
        if (linhas_conex) {
            System.out.println("Arquivo: " + arq.getName() + ", Número de linhas de conexões difeente da quantidade do Trailer");
        }/*else{
            System.out.println("Arquivo: "+arq.getName()+", ok! ,Numero de linhas de conexões iguais a quantidade do Trailer");
        }*/
        if (Header_invalido) {
            System.out.println("Arquivo: " + arq.getName() + ", Header inválido.");
        }
        if (!nos_valido) {
            System.out.println("Arquivo: " + arq.getName() + ", Número totais de nós inválido.");
        }
        if (!soma_valida) {
            System.out.println("Arquivo: " + arq.getName() + ", Soma dos pesos diferente, valor do Header e soma do pesos.");
        }
        if (linhas_pesos || linhas_conex || !Rconex || Header_invalido || !nos_valido || !soma_valida) {
            return true;
        } else {
            return false;
        }
    }
}
