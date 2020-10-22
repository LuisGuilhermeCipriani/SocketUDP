package cliente_servidor;

import java.io.*; // classes para entrada e saída de dados via teclado
import java.net.*; // classes para socket, servidorsocket e clientesocket

public class UDPServidor {
    public static void main(String argv[]) throws Exception {
        // cria socket do servidor com a porta 12000
        DatagramSocket servidorSocket = new DatagramSocket(12000); 
        byte[] dadosRecebidos = new byte[1024];
        byte[] dadosEnviados = new byte[1024];
        // espera operação de algum cliente e trata
        System.out.println("- Esperando Cliente -");
        while(true){
            // declara o pacote a ser recebido
            DatagramPacket pacoteRecebido = new DatagramPacket(dadosRecebidos, dadosRecebidos.length);
            // recebe o pacote do cliente
            servidorSocket.receive(pacoteRecebido); // espera o pacote (pacoteRecebido) chegar na porta informada
            // pega os dados, o endereço IP e a porta do cliente, para mandar a mensagem de volta
            String operacao = new String(pacoteRecebido.getData());
                InetAddress enderecoIP = pacoteRecebido.getAddress();
                int porta = pacoteRecebido.getPort();
            // exibe operação informada pelo cliente
            System.out.println("Operação do cliente: " + operacao);
            // guarda o resultado da operação aritmética
            String resultadoOperacao = Double.toString(trataOperacao(operacao.trim())); 
            dadosEnviados = resultadoOperacao.getBytes(); // converte em bytes
            // monta o pacote com endereço IP e porta
            DatagramPacket pacoteEnviado = new DatagramPacket(dadosEnviados, dadosEnviados.length, enderecoIP, porta);
            // envia ao cliente
            servidorSocket.send(pacoteEnviado);
            // exibe mensagem de confirmação de envio para o cliente
            System.out.println("Resultado enviado ao cliente com sucesso! :");
        }
    }
    // retorna o cálculo da operação aritmética informada pelo cliente
    public static double trataOperacao(String operacao){
        Double resultado = 0.0; // guarda o cálculo da operação aritmética
        if(operacao.contains("+")){ // verifica se o operador é de soma
            String[] valor = operacao.split("\\+"); // guarda o valor da operação aritmética
            resultado = Double.parseDouble(valor[0]) + Double.parseDouble(valor[1]); // guarda o cálculo da soma
        }else 
            if(operacao.contains("-")){ // verifica se o operador é de subtração
                String[] valor = operacao.split("\\-"); // guarda o valor da operação aritmética
                resultado = Double.parseDouble(valor[0]) - Double.parseDouble(valor[1]); // guarda o cálculo da subtração
            }
        else
            if(operacao.contains("*")){ // verifica se o operador é de multiplicação
            String[] valor = operacao.split("\\*"); // guarda o valor da operação aritmética
            resultado = Double.parseDouble(valor[0]) * Double.parseDouble(valor[1]); // guarda o cálculo da multiplicação
        }
        else
            if(operacao.contains("/")){ // verifica se o operador é de divisão
            String[] valor = operacao.split("\\/"); // guarda o valor da operação aritmética
            resultado = Double.parseDouble(valor[0]) / Double.parseDouble(valor[1]); // guarda o cálculo da divisão
        }
        return resultado;
    }
}
        