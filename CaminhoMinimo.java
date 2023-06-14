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

        Queue<Vertice> fila = new LinkedList<>();
        boolean[] visitado = new boolean[grafo.totalVertices()];
        int[] distancia = new int[grafo.totalVertices()];
        Vertice[] predecessores = new Vertice[grafo.totalVertices()];

        fila.add(verticeOrigem);
        visitado[verticeOrigem.indice] = true;
        distancia[verticeOrigem.indice] = 0;

        while (!fila.isEmpty()) {
            Vertice atual = fila.poll();

            if (atual == verticeDestino) {
                break; // Chegou ao destino
            }

            for (Vertice vizinho : grafo.listaAdjacencia[atual.indice]) {
                if (!visitado[vizinho.indice]) {
                    fila.add(vizinho);
                    visitado[vizinho.indice] = true;
                    distancia[vizinho.indice] = distancia[atual.indice] + 1;
                    predecessores[vizinho.indice] = atual;
                }
            }
        }

        if (!visitado[verticeDestino.indice]) {
            return null; // Não há um caminho entre os vértices
        }

        ArrayList<Vertice> caminhoMinimo = new ArrayList<>();
        Vertice atual = verticeDestino;

        while (atual != null) {
            caminhoMinimo.add(0, atual);
            atual = predecessores[atual.indice];
        }

        return caminhoMinimo;
    }
}


