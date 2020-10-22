package cliente_servidor;

import java.io.*; // classes para entrada e saída de dados
import java.net.*; // classes para socket, servidorsocket e clientesocket

public class UDPCliente {
    public static void main(String argv[]) throws Exception {
        // cria o stream do teclado
        BufferedReader cadeiaUsuario = new BufferedReader(new InputStreamReader(System.in));
        // declara socket cliente
        DatagramSocket clienteSocket = new DatagramSocket(); // apenas declarado, ainda não existe conexão com o servidor, pois ainda não foi informado
        // obtêm endereço IP do servidor com o DNS
        InetAddress enderecoIP = InetAddress.getByName("localhost"); // localhost = 127.0.0.1
        byte[] enviaDados = new byte[1024];
        byte[] recebeDados = new byte[1024];
        // solicita uma operação aritmética ao cliente
        System.out.print("\nQual a operação aritmética?: ");
        // lê uma operação do teclado
        String operacao = cadeiaUsuario.readLine();
        enviaDados = operacao.getBytes(); // converte em bytes
        // cria pacote com o dado, o endereço e a porta do servidor
        DatagramPacket enviaPocote = new DatagramPacket(enviaDados, enviaDados.length, enderecoIP, 12000);
        // envia o pacote
        clienteSocket.send(enviaPocote);
        // declara o pacote a ser recebido
        DatagramPacket recebePacote = new DatagramPacket(recebeDados, recebeDados.length);
        // recebe pacote do servidor
        clienteSocket.receive(recebePacote);
        // separa somente o dado recebido
        String resultadoOperacao = new String(recebePacote.getData());
        // exibe o reultado da operação aritmética na tela
        System.out.println("Resultado da operação: " + resultadoOperacao);
        // fecha o cliente
        clienteSocket.close();
    }
}