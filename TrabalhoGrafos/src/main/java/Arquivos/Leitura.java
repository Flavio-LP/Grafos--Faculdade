package Arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Leitura {

    private String dir1;
    private String dir2;

    public void set_dir1(String dir_1) {
        this.dir1 = dir_1;
    }

    public void set_dir2(String dir_2) {
        this.dir2 = dir_2;
    }

    public String get_dir1() {
        return dir1;
    }

    public String get_dir2() {
        return dir2;
    }

    public void ler_config() {
        try {
            String dir = null, dir01, dir02, dirA[] = null, dirB[] = null;
            if (new File("C:\\Teste\\Configuracao\\config.txt").exists()) {
                char aux[] = null;
                boolean testar;
                InputStream is = new FileInputStream("C:\\Teste\\Configuracao\\config.txt");
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader buffer = new BufferedReader(isr);

                String linha = null;

                while ((linha = buffer.readLine()) != null) { //- Leitura da linha do bloco de notas
                    dir = linha;

                    if (dir.startsWith("P")) { //- seleciona a linha que começa com "P".
                        aux = dir.toCharArray();
                        testar = testar_linha(aux);
                        if (!testar) { //- testa se a linha possui o caracter pra separar o diretorio
                            buffer.close();
                            System.out.println("Não é possível gerar o diretorio");
                            return;
                        }
                        dirA = dir.split("="); //quebra a linha em "="
                        dir01 = dirA[1];
                        set_dir1(dir01);
                    } else {
                        aux = dir.toCharArray();
                        testar = testar_linha(aux);
                        if (!testar) {//- testa se a linha possui o caracter pra separar o diretorio
                            buffer.close();
                            System.out.println("Não é possível gerar o diretorio");
                            return;
                        }
                        dirB = dir.split("=");
                        dir02 = dirB[1];
                        set_dir2(dir02);
                    }
                }

                buffer.close();
                if (dir == null) {
                    System.out.println("Arquivo em branco!");
                } else {
                    if (dirA != null) {
                        new File(get_dir1()).mkdirs(); // cria o diretorio 1
                    } else {
                        System.out.println("Sem diretorio 1 para criar");
                    }
                    if (dirB != null) {
                        new File(get_dir2()).mkdirs();//cria o diretorio 2
                    } else {
                        System.out.println("Sem diretorio 2 para criar");
                    }
                }

            } else {
                System.out.println("Não existe o arquivo config.txt");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean testar_linha(char linha[]) {// Verifica se possui o caracter '=' e retorna um valor booleano pra validar.

        for (int i = 0; i < linha.length; i++) {
            if (linha[i] == '=') {
                return true;
            }
        }
        return false;

    }
}
