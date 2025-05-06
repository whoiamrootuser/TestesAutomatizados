package dev.whoiamrootuser.atividade_03_funcionarios.domain;

public class Funcionario {
    private static final double SALARIO_MINIMO = 1518.00;
    private static final double SALARIO_MAXIMO = 100000.00;
   
    private String nome;
    private int horasTrabalhadas;
    private double valorHora;

    public Funcionario(String nome, int horasTrabalhadas, double valorHora) {
        this.nome = nome;
        this.horasTrabalhadas = validaHorasTrabalhadas(horasTrabalhadas);
        this.valorHora = validaValorHora(valorHora);
    }

    public double calcularPagamento() {
        double pagamento = horasTrabalhadas * valorHora;
        if (pagamento < SALARIO_MINIMO || pagamento > SALARIO_MAXIMO) {
            throw new IllegalArgumentException("O pagamento deve ser maior ou igual ao salário mínimo de R$ 1518.00.");
        }
        return pagamento;
    }

    private int validaHorasTrabalhadas(int horasTrabalhadas) {
        if (horasTrabalhadas < 20 || horasTrabalhadas > 40) {
            throw new IllegalArgumentException("O número de horas trabalhadas por funcionários próprios deve ser entre 20 e 40.");
        }
        return horasTrabalhadas;
    }

    private double validaValorHora(double valorHora) {
        double valorMinimo = SALARIO_MINIMO * 0.04;
        double valorMaximo = SALARIO_MINIMO * 0.10;
        if (valorHora < valorMinimo || valorHora > valorMaximo) {
            throw new IllegalArgumentException("O valor da hora deve ser entre 4% e 10% do salário mínimo.");
        }
        return valorHora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        this.horasTrabalhadas = validaHorasTrabalhadas(horasTrabalhadas);
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = validaValorHora(valorHora);
    }
}
