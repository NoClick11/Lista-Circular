class No {
    public static void main(String[] args) {

        ListaCircular lista = new ListaCircular();

        lista.inserirInicio(10);
        lista.inserirFim(20);
        lista.inserirFim(30);
        lista.mostrar(); // Exibe a lista após inserções

        lista.removerInicio();
        lista.mostrar(); // Exibe a lista após remover o primeiro elemento

        lista.inserirPosicao(15, 1);
        lista.mostrar(); // Exibe a lista após inserir na posição 1

        lista.removerPosicao(2);
        lista.mostrar(); // Exibe a lista após remover da posição 2

        lista.removerFim();
        lista.mostrar(); // Exibe a lista após remover o último elemento
    }

    int dado; // Armazena o valor do nó
    No prox; // Referência para o próximo nó

    // Construtor do nó
    No(int dado) {
        this.dado = dado;
        this.prox = null;
    }
}

// Classe que implementa a lista circular
public class ListaCircular {

    private No ultimo; // Aponta para o último nó da lista
    private int tamanho; // Mantém o tamanho da lista

    // Construtor da lista circular
    public ListaCircular() {
        this.ultimo = null;
        this.tamanho = 0;
    }

    // Insere um novo nó no início da lista
    public void inserirInicio(int dado) {
        No novo = new No(dado);
        if (ultimo == null) { // Se a lista estiver vazia
            ultimo = novo;
            ultimo.prox = novo; // Aponta para si mesmo
        } else {
            novo.prox = ultimo.prox; // Novo nó aponta para o primeiro
            ultimo.prox = novo; // Último agora aponta para o novo primeiro
        }
        tamanho++;
    }

    // Insere um novo nó no final da lista
    public void inserirFim(int dado) {
        inserirInicio(dado); // Insere no início
        ultimo = ultimo.prox; // Move o ponteiro `ultimo` para o novo último nó
    }

    // Remove o primeiro nó da lista
    public void removerInicio() {
        if (ultimo == null) { // Se a lista estiver vazia
            return;
        }
        if (ultimo == ultimo.prox) { // Se houver apenas um elemento
            ultimo = null;
        } else {
            ultimo.prox = ultimo.prox.prox; // Aponta o último nó para o segundo nó
        }
        tamanho--;
    }

    // Remove o último nó da lista
    public void removerFim() {
        if (ultimo == null) { // Se a lista estiver vazia
            return;
        }
        if (ultimo == ultimo.prox) { // Se houver apenas um nó
            ultimo = null;
        } else {
            No atual = ultimo.prox; // Começa do primeiro nó
            while (atual.prox != ultimo) { // Percorre até o penúltimo nó
                atual = atual.prox;
            }
            atual.prox = ultimo.prox; // Penúltimo nó passa a apontar para o primeiro
            ultimo = atual; // Atualiza `ultimo` para o novo último nó
        }
        tamanho--;
    }

    // Insere um nó em uma posição específica da lista
    public void inserirPosicao(int dado, int pos) {
        if (pos < 0 || pos > tamanho) { // Verifica se a posição é válida
            throw new IndexOutOfBoundsException("Posição inválida");
        }
        if (pos == 0) { // Se for a posição 0, insere no início
            inserirInicio(dado);
        } else if (pos == tamanho) { // Se for a última posição, insere no final
            inserirFim(dado);
        } else {
            No novo = new No(dado);
            No atual = ultimo.prox; // Começa do primeiro nó
            for (int i = 0; i < pos - 1; i++) { // Percorre até o nó anterior à posição desejada
                atual = atual.prox;
            }
            novo.prox = atual.prox; // O novo nó aponta para o próximo nó da posição
            atual.prox = novo; // O nó anterior aponta para o novo nó
            tamanho++;
        }
    }

    // Remove um nó de uma posição específica da lista
    public void removerPosicao(int pos) {
        if (pos < 0 || pos >= tamanho) { // Verifica se a posição é válida
            throw new IndexOutOfBoundsException("Posição inválida");
        }
        if (pos == 0) { // Se for a posição 0, remove do início
            removerInicio();
        } else {
            No atual = ultimo.prox; // Começa do primeiro nó
            for (int i = 0; i < pos - 1; i++) { // Percorre até o nó anterior à posição desejada
                atual = atual.prox;
            }
            atual.prox = atual.prox.prox; // Remove o nó da posição
            if (pos == tamanho - 1) { // Se for o último nó, atualiza `ultimo`
                ultimo = atual;
            }
            tamanho--;
        }
    }

    // Exibe os elementos da lista circular
    public void mostrar() {
        if (ultimo == null) { // Se a lista estiver vazia
            System.out.println("Lista vazia");
            return;
        }
        No atual = ultimo.prox; // Começa pelo primeiro nó
        do {
            System.out.print(atual.dado + " -> "); // Exibe o valor do nó
            atual = atual.prox;
        } while (atual != ultimo.prox); // Continua até voltar ao primeiro nó
        System.out.println("(circular)");
    }

}
