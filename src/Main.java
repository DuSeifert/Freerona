import dao.Database;
import dao.UsuarioDAO;
import dao.ViagemDAO;
import model.Usuario;
import model.Viagem;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Usuario usuarioLogado = null;
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static final ViagemDAO viagemDAO = new ViagemDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Database.inicializar();
        while (true) {
            if (usuarioLogado == null) {
                menuInicial();
            } else {
                menuUsuario();
            }
        }
    }

    private static void menuInicial() {
        System.out.println("\n--- FREERONA ---");
        System.out.println("1. Fazer Login");
        System.out.println("2. Criar Conta");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
        String op = scanner.nextLine();
        switch (op) {
            case "1":
                fazerLogin();
                break;
            case "2":
                criarConta();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void menuUsuario() {
        System.out.println("\n--- Menu do Usuário ---");
        System.out.println("1. Criar Viagem");
        System.out.println("2. Listar Viagens");
        System.out.println("3. Editar Viagem");
        System.out.println("4. Deletar Viagem");
        System.out.println("5. Aceitar Viagem");
        System.out.println("6. Listar Minhas Viagens Aceitas");
        System.out.println("7. Logout");
        System.out.print("Escolha: ");
        String op = scanner.nextLine();
        switch (op) {
            case "1":
                criarViagem();
                break;
            case "2":
                listarViagens();
                break;
            case "3":
                editarViagem();
                break;
            case "4":
                deletarViagem();
                break;
            case "5":
                aceitarViagem();
                break;
            case "6":
                listarMinhasViagensAceitas();
                break;
            case "7":
                usuarioLogado = null;
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void fazerLogin() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        Usuario u = usuarioDAO.login(email, senha);
        if (u != null) {
            usuarioLogado = u;
            System.out.println("Login realizado com sucesso!");
        } else {
            System.out.println("Email ou senha incorretos!");
        }
    }

    private static void criarConta() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        Usuario u = new Usuario(nome, email, senha);
        if (usuarioDAO.criarConta(u)) {
            System.out.println("Conta criada com sucesso!");
        } else {
            System.out.println("Erro ao criar conta. Tente outro email.");
        }
    }

    private static void criarViagem() {
        System.out.print("Destino: ");
        String destino = scanner.nextLine();
        System.out.print("Data (YYYY-MM-DD): ");
        String data = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        Viagem v = new Viagem(destino, data, descricao, usuarioLogado.getId());
        if (viagemDAO.criarViagem(v)) {
            System.out.println("Viagem criada!");
        } else {
            System.out.println("Erro ao criar viagem.");
        }
    }

    private static void listarViagens() {
        List<Viagem> viagens = viagemDAO.listarViagens();
        System.out.println("\n--- Lista de Viagens ---");
        for (Viagem v : viagens) {
            System.out.printf("ID: %d | Destino: %s | Data: %s | Criador: %d\nDescrição: %s\n",
                v.getId(), v.getDestino(), v.getData(), v.getIdUsuarioCriador(), v.getDescricao());
        }
    }

    private static void editarViagem() {
        listarViagens();
        System.out.print("ID da viagem para editar: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Novo destino: ");
        String destino = scanner.nextLine();
        System.out.print("Nova data (YYYY-MM-DD): ");
        String data = scanner.nextLine();
        System.out.print("Nova descrição: ");
        String descricao = scanner.nextLine();
        Viagem v = new Viagem(id, destino, data, descricao, usuarioLogado.getId());
        if (viagemDAO.editarViagem(v)) {
            System.out.println("Viagem editada!");
        } else {
            System.out.println("Erro ao editar viagem.");
        }
    }

    private static void deletarViagem() {
        listarViagens();
        System.out.print("ID da viagem para deletar: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (viagemDAO.deletarViagem(id)) {
            System.out.println("Viagem deletada!");
        } else {
            System.out.println("Erro ao deletar viagem.");
        }
    }

    private static void aceitarViagem() {
        listarViagens();
        System.out.print("ID da viagem para aceitar: ");
        int idViagem = Integer.parseInt(scanner.nextLine());
        if (viagemDAO.aceitarViagem(usuarioLogado.getId(), idViagem)) {
            System.out.println("Viagem aceita!");
        } else {
            System.out.println("Erro ao aceitar viagem.");
        }
    }

    private static void listarMinhasViagensAceitas() {
        List<Viagem> viagens = viagemDAO.listarViagensPorUsuario(usuarioLogado.getId());
        System.out.println("\n--- Minhas Viagens Aceitas ---");
        for (Viagem v : viagens) {
            System.out.printf("ID: %d | Destino: %s | Data: %s\nDescrição: %s\n",
                v.getId(), v.getDestino(), v.getData(), v.getDescricao());
        }
    }
} 