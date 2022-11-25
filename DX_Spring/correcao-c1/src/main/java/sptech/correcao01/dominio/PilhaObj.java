package sptech.correcao01.dominio;

public class PilhaObj <T>{


    // 01) Atributos
    private T[] pilha;
    private int topo;

    // 02) Construtor
    public PilhaObj(int capacidade) {
        this.topo = -1;
        this.pilha = (T[]) new Object[capacidade];
    }

    // 03) Métodos

    public Boolean isEmpty() {
        return topo == -1;
    }

    public Boolean isFull() {
        return topo == pilha.length - 1;
    }

    public void push(T info) {
        if (!isFull()) {
            pilha[++topo] = info;
        } else {
            throw new IllegalStateException();
        }
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return pilha[topo--];
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return pilha[topo];
    }

    public void exibe() {
        for (int i = 0; i < topo; i++) {
            System.out.println(pilha[i]);
        }
    }

    //Getters - Não retirar
    public int getTopo() {
        return topo;
    }

    public T[] getPilha() {
        return pilha;
    }
}
