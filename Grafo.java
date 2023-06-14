import java.util.ArrayList;

public class Grafo {

    //public boolean[][] matrizAdjacencia;
    public Vertice[] listaVertices;
    private Vertice[] listaPortos = new Vertice[9];
    ArrayList<Vertice> listaAdjacencia[];
    /*public int obterIndiceVertice(String indice) {
        for (int i = 0; i < totalVertices; i++) {
            if(listaVertices[i].indice.equals(indice)) return i;
        }
        return -1;
    }*/
    private int totalVertices;
    private int totalPortos;
    private int totalArestas;
    private int numLinhas;
    private int numColunas;
    public Grafo(int numLinhas, int numColunas) {
        int total = numLinhas * numColunas;
        this.numLinhas = numLinhas;
        this.numColunas = numColunas;
        listaAdjacencia = new ArrayList[total];
        for (int i = 0; i < total; i++) {
            listaAdjacencia[i] = new ArrayList<>();
        }
        listaVertices = new Vertice[total];
        totalVertices = 0;
        totalArestas = 0;
    }

    public ArrayList<Vertice>[] getLista(){
        return listaAdjacencia;
    }

    public Vertice getVertice(int indice){
        return listaVertices[indice];
    }

    public ArrayList<Vertice> getListaAdjacencia(int vertice) {
        return listaAdjacencia[vertice];
    }

    public Vertice getVerticeVizinho(Vertice v, tiposVizinhos tipo){
        switch (tipo) {
            case VIZINHO_ESQUERDA:
                Vertice aux = listaVertices[v.indice+1];
                if(aux.numColuna == v.numColuna+1) return aux;
                else return null;
                
            case VIZINHO_BAIXO:
                int auxIndice = v.indice + this.numColunas;
                Vertice aux2 = listaVertices[auxIndice];
                if(aux2.numLinha == v.numLinha+1) return aux2;
                else return null;
            default:
                return null;
        }
    }

    public int getQtdPortos(){
        return totalPortos;
    }

    public Vertice getPorto(int numPorto){
        char porto = (numPorto+"").charAt(0);
        for (int i = 0; i < listaPortos.length; i++) {
            if(porto == listaPortos[i].caractere) return listaPortos[i];            
        }
        return null;
    }


    public void adicionarVertice(String indice, int linha, int coluna, char caractere) {
        Vertice v = new Vertice();

        v.indice = Integer.parseInt(indice);
        v.numLinha = linha;
        v.numColuna = coluna;
        v.caractere = caractere;
        if(caractere != '*' && caractere != '.'){
            listaPortos[totalPortos] = v;
            totalPortos++;
        }
        listaVertices[totalVertices] = v;
        totalVertices++;
    }

    public void adicionarAresta(Vertice origem, Vertice vizinho) {

        this.listaAdjacencia[origem.indice].add(vizinho);
        this.listaAdjacencia[vizinho.indice].add(origem);
        this.totalArestas++;
    }

    public int totalVertices(){
        return totalVertices;
    }
    public String toDot() {
        String resultado = "digraph G { " + System.lineSeparator();
        for (int i = 0; i < totalVertices; i++) {
            resultado = resultado + "\t" + i + ";" + System.lineSeparator();
        }
        for (int i = 0; i < totalVertices; i++) {
            for (int j = 0; j < listaAdjacencia[i].size(); j++) {
                resultado += "\t" + i + "->" + listaAdjacencia[i].get(j) + ";" + System.lineSeparator();
            }
        }
        resultado += "}";
        return resultado;
    }

    @Override
    public String toString() {
        String resultado = "";
        return resultado;
    }
}
