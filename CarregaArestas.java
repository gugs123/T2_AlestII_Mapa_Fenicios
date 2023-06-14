public class CarregaArestas {
    public static Grafo ligaArestas(Grafo g){
        int contador = 0;

        while(contador < g.totalVertices()){
            Vertice v = g.getVertice(contador);
            tiposVizinhos tipo;
            if(v.caractere == '*') contador ++;
            else{
                try{
                    tipo = tiposVizinhos.VIZINHO_ESQUERDA;
                    Vertice vizinho = g.getVerticeVizinho(v, tipo);
                    if(vizinho.caractere != '*'){
                        g.adicionarAresta(v, vizinho);
                    }
                    tipo = tiposVizinhos.VIZINHO_BAIXO;
                    vizinho = g.getVerticeVizinho(v, tipo);
                    if(vizinho.caractere != '*' && v != vizinho){
                        g.adicionarAresta(v, vizinho);
                    }
                }
                catch(RuntimeException e){}
                contador++;
            }
        }
        return g;
    }
}
