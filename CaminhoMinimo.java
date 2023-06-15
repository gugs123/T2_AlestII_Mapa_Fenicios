import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CaminhoMinimo {
    public static ArrayList<Vertice> encontrarCaminhoMinimo(Grafo grafo, char caractereOrigem, char caractereDestino) {
        Vertice verticeOrigem = null;
        Vertice verticeDestino = null;

        for (int i = 0; i < grafo.totalVertices(); i++) {
            if (grafo.listaVertices[i].caractere == caractereOrigem) {
                verticeOrigem = grafo.listaVertices[i];
            } else if (grafo.listaVertices[i].caractere == caractereDestino) {
                verticeDestino = grafo.listaVertices[i];
            }
        }

        if (verticeOrigem == null || verticeDestino == null) {
            return null; // Um dos vértices não foi encontrado
        }

        //Queue (fila) que será usada para realizar a busca em largura.
        Queue<Vertice> fila = new LinkedList<>();

        //array booleano que mantém o registro de quais vértices já foram visitados durante a busca.
        boolean[] visitado = new boolean[grafo.totalVertices()];

        //array de inteiros que armazena as distâncias dos vértices em relação ao vértice de origem.
        int[] distancia = new int[grafo.totalVertices()];

        //array de vértices que guarda o vértice predecessor de cada vértice no caminho mínimo.
        Vertice[] predecessores = new Vertice[grafo.totalVertices()];

        //O vértice de origem é adicionado à fila e marcado como visitado. A distância do vértice de origem é definida como 0.
        fila.add(verticeOrigem);
        visitado[verticeOrigem.indice] = true;
        distancia[verticeOrigem.indice] = 0;

        //O loop principal começa e continua até que a fila esteja vazia. 
        //Em cada iteração, um vértice é removido da fila (usando fila.poll()) e atribuído à variável atual.
        while (!fila.isEmpty()) {
            Vertice atual = fila.poll();

            //Se o vértice atual for igual ao vértice de destino, 
            //significa que chegamos ao destino e o loop é interrompido usando break.
            if (atual == verticeDestino) {
                break; // Chegou ao destino
            }

            //Para cada vértice vizinho do vértice atual (encontrado na lista de adjacência do grafo), 
            //verificamos se ele já foi visitado. Se não, o adicionamos à fila, marcamos como visitado, 
            //atualizamos a distância e definimos o vértice atual como predecessor.
            for (Vertice vizinho : grafo.listaAdjacencia[atual.indice]) {
                if (!visitado[vizinho.indice]) {
                    fila.add(vizinho);
                    visitado[vizinho.indice] = true;
                    distancia[vizinho.indice] = distancia[atual.indice] + 1;
                    predecessores[vizinho.indice] = atual;
                }
            }
        }

        //Se, após a busca, o vértice de destino não tiver sido visitado, 
        //isso significa que não há um caminho válido entre os vértices de origem e destino. 
        //Nesse caso, o método retorna null.
        if (!visitado[verticeDestino.indice]) {
            return null; // Não há um caminho entre os vértices
        }

        //Se o vértice de destino foi visitado, é hora de construir o caminho mínimo. 
        //É criado um ArrayList chamado caminhoMinimo para armazenar os vértices do caminho.
        ArrayList<Vertice> caminhoMinimo = new ArrayList<>();

        //A variável atual é inicializada com o vértice de destino.
        Vertice atual = verticeDestino;

        //É iniciado um loop para percorrer os predecessores a partir do vértice de destino até o vértice de origem. 
        //A cada iteração, o vértice atual é adicionado ao início do ArrayList caminhoMinimo usando o método add(0, atual).
        while (atual != null) {
            caminhoMinimo.add(0, atual);

            //O vértice predecessor de atual é obtido do array predecessores e atribuído a atual. 
            //O loop continua até que atual seja null, o que significa que chegamos ao vértice de origem.
            atual = predecessores[atual.indice];
        }
        //Por fim, o método retorna o ArrayList caminhoMinimo, que contém o caminho mínimo entre os vértices de origem e destino.
        return caminhoMinimo;
    }
}


