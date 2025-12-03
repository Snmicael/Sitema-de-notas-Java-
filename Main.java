import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Aluno {
    private String nome;
    private double nota1;
    private double nota2;
    private double media;
    private String situacao;

    public Aluno(String nome, double nota1, double nota2) {
        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        calcularMedia();
        definirSituacao();
    }

    public String getNome() { return nome; }
    public double getMedia() { return media; }

    private void calcularMedia() {
        this.media = (nota1 + nota2) / 2.0;
    }

    private void definirSituacao() {
        if (media >= 6)
            situacao = "Aprovado";
        else
            situacao = "Reprovado";
    }

    public String toString() {
        return nome + " | Média: " + media + " | Situação: " + situacao;
    }
}

class SistemaNotas {
    private ArrayList<Aluno> alunos = new ArrayList<>();

    public void adicionarAluno(Aluno a) {
        alunos.add(a);
    }

    public void ordenarPorMedia() {
        Collections.sort(alunos, Comparator.comparingDouble(Aluno::getMedia).reversed());
    }

    public void listarAlunos() {
        System.out.println("Lista dos Alunos (Ordenada pela  Média)");
        for (Aluno a : alunos) {
            System.out.println(a);
        }
    }

    public void estatisticas() {
        if (alunos.isEmpty()) return;

        double soma = 0;
        double maior = alunos.get(0).getMedia();
        double menor = alunos.get(0).getMedia();

        for (Aluno a : alunos) {
            double m = a.getMedia();
            soma += m;
            if (m > maior) maior = m;
            if (m < menor) menor = m;
        }

        System.out.println("Estatísticas gerais da Turma");
        System.out.println("Média da turma: " + (soma / alunos.size()));
        System.out.println("Maior média: " + maior);
        System.out.println("Menor média: " + menor);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaNotas sistema = new SistemaNotas();

        System.out.print("Quantos alunos deseja cadastrar? ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("\nAluno " + (i + 1));
            System.out.print("Nome do Aluno: ");
            String nome = sc.nextLine();
            System.out.print("Nota 1: ");
            double n1 = sc.nextDouble();
            System.out.print("Nota 2: ");
            double n2 = sc.nextDouble();
            sc.nextLine();

            sistema.adicionarAluno(new Aluno(nome, n1, n2));
        }
        
        sistema.ordenarPorMedia();
        sistema.listarAlunos();
        sistema.estatisticas();

        sc.close();
    }
}