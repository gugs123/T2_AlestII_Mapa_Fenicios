import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Grafo g = CarregaMatriz.leArquivo(CarregaMatriz.selecionaMapa());
        g = CarregaArestas.ligaArestas(g);
        navegar(g);    
        System.out.println("Tempo de execucao da carga do arquivo mapa ate o final: ");  
        System.out.println(Temporizador.getClockSec() + " segundos");
        System.out.println("FIM!");
    }

    public static void navegar(Grafo g){
        int i = 1;
        int consumo = 0;
        int qtdCaminhamentos = 0;
        do{
            char portoOrigem = (i+"").charAt(0);
            char portoDestino;
            boolean quebraLoop = false;
            try {
                int aux = i+1;
                qtdCaminhamentos++;
                if(aux > g.getQtdPortos() || qtdCaminhamentos >= g.getQtdPortos()) aux = 1/0;
                portoDestino = ((aux)+"").charAt(0);
            } catch (RuntimeException e) {
                portoDestino = '1';
                quebraLoop = true;
                if(portoOrigem == portoDestino) break;
            }
            ArrayList<Vertice> caminho = CaminhoMinimo.encontrarCaminhoMinimo(g, portoOrigem, portoDestino);
            consumo = mostrarCaminho(caminho, portoOrigem, portoDestino, consumo);
            while(caminho == null){
                int portoDestinoInt = Integer.parseInt((portoDestino+""));
                portoDestinoInt++;
                portoDestino = (portoDestinoInt+"").charAt(0);
                caminho = CaminhoMinimo.encontrarCaminhoMinimo(g, portoOrigem, portoDestino);
                consumo = mostrarCaminho(caminho, portoOrigem, portoDestino, consumo);
            }
            if(quebraLoop) break;
            i = Integer.parseInt(portoDestino+"");
        }while(i <= g.getQtdPortos());
        System.out.println("Consumo total: " + consumo);
    }

    public static int mostrarCaminho(ArrayList<Vertice> caminho, char origem, char dest, int consumo){
        System.out.print("Porto " + origem + " para " + dest + " -> ");
        if (caminho != null) {
            System.out.println((caminho.size()-1));
            return consumo += (caminho.size()-1);
        } else {
            System.out.println("Bloqueado");
            return consumo;
        }
    }
}
