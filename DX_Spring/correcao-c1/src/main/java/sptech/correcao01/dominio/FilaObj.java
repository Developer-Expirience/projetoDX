package sptech.correcao01.dominio;

public class FilaObj <T> {
    private int tamanho;
    private T[] fila;

    // 02) Construtor
    public FilaObj(int capacidade) {
        this.tamanho = 0;
        this.fila = (T[]) new Object[capacidade];
    }

    // 03) Métodos

    public Boolean isEmpty() {
        return tamanho == 0;
    }

    public Boolean isFull() {
        return tamanho == fila.length;
    }

    public void insert(T info) {
        if (!isFull()) {
            fila[tamanho++] = info;
        } else {
            throw new IllegalStateException();
        }
    }

    public T peek() {
        return fila[0];
    }

    public T poll() {
        T primeiro = fila[0];
        if (!isEmpty()) {
            for (int i = 0; i < tamanho-1; i++) {
                fila[i] = fila[i + 1];
            }
            tamanho--;
            return primeiro;
        }
        return primeiro;
    }

    public void exibe() {
        for (int i = 0; i < tamanho; i++) {
            System.out.println(fila[i]);
        }
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public T[] getFila() {
        return fila;
    }

    public void setFila(T[] fila) {
        this.fila = fila;
    }
}
