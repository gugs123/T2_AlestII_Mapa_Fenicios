import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CarregaMatriz {
    public static File selecionaMapa(){
        menu();
        Scanner in = new Scanner(System.in);
        String escolha = in.nextLine();
        in.close();
        Temporizador.initClock(); //inicia temporizador
        System.out.println();
        File arquivo;
        switch(escolha){
            case "1":
                arquivo = new File("Mapas\\mapa0.txt");
                return arquivo;
            case "2":
                arquivo = new File("Mapas\\mapa1.txt");
                return arquivo;
            case "3":
                arquivo = new File("Mapas\\mapa2.txt");
                return arquivo;
            case "4":
                arquivo = new File("Mapas\\mapa3.txt");
                return arquivo;
            case "5":
                arquivo = new File("Mapas\\mapa_15_80.txt");
                return arquivo;
            case "6":
                arquivo = new File("Mapas\\mapa_30_80.txt");
                return arquivo;
            case "7":
                arquivo = new File("Mapas\\mapa_50_100.txt");
                return arquivo;
            case "8":
                arquivo = new File("Mapas\\mapa_60_50.txt");
                return arquivo;
            case "9":
                arquivo = new File("Mapas\\mapa500_1000.txt");
                return arquivo;
            default:
                System.exit(0);
        }
        return null;

    }
    private static void menu(){
        System.out.println("****MAPA DOS FENICIOS****");
        System.out.println("Feito por: Gustavo Melo");
        System.out.println("Selecione um mapa: ");
        System.out.println("1- mapa0");
        System.out.println("2- mapa1");
        System.out.println("3- mapa2");
        System.out.println("4- mapa3");
        System.out.println("5- mapa 15 x 80");
        System.out.println("6- mapa 30 x 80");
        System.out.println("7- mapa 50 x 100");
        System.out.println("8- mapa 60 x 500");
        System.out.println("9- mapa 500 x 1000");
        System.out.println("0- FINALIZAR PROGRAMA");
        System.out.print("Selecao: ");
    }
    public static Grafo leArquivo(File arquivo){
        try { 
            //File arquivo = new File("Mapas\\mapa_500_1000.txt");
            Scanner in = new Scanner(arquivo);
            
            // Lendo o número de vértices do arquivo
            String linha = in.nextLine();
            String[] valoresVertices = linha.split(" ");
            int valorNumLinha = Integer.parseInt(valoresVertices[0]);
            int valorNumColuna = Integer.parseInt(valoresVertices[1]);

            
            // Criando o grafo valorado
            Grafo g = new Grafo(valorNumLinha, valorNumColuna);
            
            // Lendo os vertices do arquivo
            int numLinha = 0;
            int numColuna = 0;
            int indice = 0;
            while (in.hasNextLine()) {
                linha = in.nextLine();
                String[] valores = linha.split("");

                numColuna = 0;
                for (int i = 0; i < valores.length; i++) {
                    String indiceStr = indice + "";
                    char caractere = valores[i].charAt(0);
                    g.adicionarVertice(indiceStr, numLinha, numColuna, caractere);
                    indice++;
                    numColuna++;
                }
                numLinha++;
             }
            in.close();
            return g;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
