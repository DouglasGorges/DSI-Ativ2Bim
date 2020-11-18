package br.edu.up.controller;

import br.edu.up.dao.AluguelDAO;
import br.edu.up.dao.UsuarioDAO;
import br.edu.up.dao.VeiculoDAO;
import br.edu.up.model.Aluguel;
import br.edu.up.model.Usuario;
import br.edu.up.model.Veiculo;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class application {

    private static String DATE_PATTERN = "dd/MM/yyyy";

    public static void main(String[] args) throws SQLException, ParseException {initMenu();}

    private static void initMenu() throws ParseException {
        System.out.println("     Bem vindo ao Bike Rent");
        System.out.println("________________________________");
        System.out.println("Selecione um menu:");
        System.out.println("|        1 - Cadastro           |");
        System.out.println("|        2 - Aluguel            |");
        System.out.println("|        3 - Pagamento          |");
        System.out.println("________________________________");
        Scanner scanner = new Scanner(System.in);
        Integer nrMenu = scanner.nextInt();

        if (nrMenu.equals(1)) {
            abrirMenuUsuarios();
        } else if (nrMenu.equals(2)) {
            abrirMenuAlugueis();
        } else if (nrMenu.equals(3)) {
            abrirMenuPagamentos();
        }
    }

    private static void abrirMenuUsuarios() throws ParseException {
        System.out.println("________________________________");
        System.out.println("Selecione um submenu de Usuários:");
        System.out.println("|        1 - Cadastre-se        |");
        System.out.println("|        2 - Editar cadastro    |");
        System.out.println("|        3 - Apagar cadastro    |");
        System.out.println("________________________________");
        Scanner scanner = new Scanner(System.in);
        Integer nrMenu = scanner.nextInt();

        if (nrMenu.equals(1)) {
            cadastrarUsuario();
        } else if (nrMenu.equals(2)) {
            editarUsuario();
        } else if (nrMenu.equals(3)) {
            apagarUsuario();
        }
    }

    private static void cadastrarUsuario() throws ParseException {
        Scanner scanUsuario = new Scanner(System.in);
        System.out.println("--Novo Cadastro de Usuário--");
        System.out.println("Digite seu nome: ");
        String nomeUsuario = scanUsuario.next();
        System.out.println("Digite seu sobrenome: ");
        String sobrenomeUsuario = scanUsuario.next();
        System.out.println("Digite sua data de nascimento: (dd/MM/aaaa)");
        String strDtNascimentoUsuario = scanUsuario.next();

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        Date dtNascimentoUsuario = sdf.parse(strDtNascimentoUsuario);

        Usuario usuario = new Usuario(nomeUsuario, sobrenomeUsuario, dtNascimentoUsuario);

        UsuarioDAO usuarioDao = new UsuarioDAO();
        usuarioDao.salvar(usuario);

        System.out.println("Cadastro realizado com sucesso!");

        initMenu();

    }

    private static Usuario buscarUsuario() throws ParseException {
        listarUsuario(); //TODO Apagar antes de ir pra PRD

        System.out.println("Digite seus dados:");
        Scanner scanUsuario = new Scanner(System.in);
        System.out.println("Nome: ");
        String nomeUsuario = scanUsuario.next();
        System.out.println("Sobrenome: ");
        String sobrenomeUsuario = scanUsuario.next();
        System.out.println("Data de nascimento: (dd/MM/aaaa)");
        String strDtNascimentoUsuario = scanUsuario.next();

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        Date dtNascimentoUsuario = sdf.parse(strDtNascimentoUsuario);

        Usuario usuario = new Usuario(nomeUsuario, sobrenomeUsuario, dtNascimentoUsuario);
        UsuarioDAO usuarioDao = new UsuarioDAO();

        return usuarioDao.buscar(usuario);
    }

    private static void editarUsuario() throws ParseException {

        Usuario usuarioEncontrado = buscarUsuario();

        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        String dtNascFormatada = df.format(usuarioEncontrado.getDtNascimento());
        System.out.println("Dados encontrados: " +
                usuarioEncontrado.getNome() + " " +
                usuarioEncontrado.getSobrenome() + ", " +
                dtNascFormatada);

        System.out.println("Dados os novos dados:");
        System.out.println("Digite o nome: ");
        Scanner scanUsuario = new Scanner(System.in);
        String novoNomeUsuario = scanUsuario.next();
        System.out.println("Digite o sobrenome: ");
        String novoSobrenomeUsuario = scanUsuario.next();
        System.out.println("Digite a data de nascimento: (dd/MM/aaaa)");
        String strNovaDtNascimentoUsuario = scanUsuario.next();

        if(novoNomeUsuario != null && !novoNomeUsuario.isEmpty())
            usuarioEncontrado.setNome(novoNomeUsuario);
        if(novoSobrenomeUsuario != null && !novoSobrenomeUsuario.isEmpty())
            usuarioEncontrado.setSobrenome(novoSobrenomeUsuario);
        if(strNovaDtNascimentoUsuario != null && !strNovaDtNascimentoUsuario.isEmpty()){
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
            Date novaDtNascimentoUsuario = sdf.parse(strNovaDtNascimentoUsuario);
            usuarioEncontrado.setDtNascimento(novaDtNascimentoUsuario);
        }

        UsuarioDAO usuarioDao = new UsuarioDAO();
        usuarioDao.atualizar(usuarioEncontrado);

        System.out.println("Usuário editado com sucesso!");

        initMenu();
        //listarUsuario();
    }

    private static void listarUsuario() {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        List<Usuario> listaDeUsuarios = usuarioDao.listar();

        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        System.out.println("ID\tNome\t\t\tData Nascimento:");
        for (Usuario usuario : listaDeUsuarios) {
            String dtNascFormatada = df.format(usuario.getDtNascimento());
            System.out.println(
                    usuario.getId() + "\t" +
                            usuario.getNome() + " " +
                            usuario.getSobrenome() + "\t" +
                            dtNascFormatada);
        }
    }

    private static void apagarUsuario() throws ParseException {
        //listarUsuario();

        Usuario usuario = buscarUsuario();

        UsuarioDAO usuarioDao = new UsuarioDAO();
        usuarioDao.apagar(usuario.getId());

        System.out.println("Usuário apagado com sucesso!");
        initMenu();
        //listarUsuario();
    }

    private static void abrirMenuAlugueis() throws ParseException {
        System.out.println("________________________________________________");
        System.out.println("Selecione uma opção:");
        System.out.println("|        1 - Iniciar aluguel                   |");
        System.out.println("|        2 - Consultar aluguel em andamento    |");
        System.out.println("|        3 - Finalizar aluguel                 |");
        System.out.println("________________________________________________");
        Scanner scanner = new Scanner(System.in);
        Integer nrMenu = scanner.nextInt();

        if (nrMenu.equals(1)) {
            iniciarAluguel();
        } else if (nrMenu.equals(2)) {
            //consultarAluguel();
        } else if (nrMenu.equals(3)) {
           //finalizarAluguel();
        }
    }

    private static void iniciarAluguel() throws ParseException {
        Usuario usuario = buscarUsuario();

        VeiculoDAO veiculoDAO = new VeiculoDAO();
        List<Veiculo> listaVeiculos = veiculoDAO.buscarVeiculosDisponiveis();

        System.out.println("________________________________");
        System.out.println("Selecione um Veículo para alugar:");

        System.out.println("Cód\tCor\t\tPreço por Hora");
        Map<Integer, Veiculo> mapaVeiculos = new HashMap<Integer, Veiculo>();
        for(Veiculo veiculo : listaVeiculos){
            mapaVeiculos.put(veiculo.getCodigo(), veiculo);
            System.out.println(veiculo.getCodigo() + "\t" +
                    veiculo.getCor() + "\t\t" +
                    veiculo.getPreco()
            );
        }

        Scanner scanner = new Scanner(System.in);
        Integer codVeiculo = scanner.nextInt();

        Veiculo veiculo = mapaVeiculos.get(codVeiculo);
        Date dtRetirada = new Date();

        Aluguel aluguel = new Aluguel(usuario, veiculo, dtRetirada, veiculo.getPreco());

        AluguelDAO aluguelDAO = new AluguelDAO();
        aluguelDAO.salvar(aluguel);

        veiculo.setIdLocado(true);
        veiculoDAO.atualizar(veiculo);

        System.out.println("Aluguel iniciado com sucesso! Boa viagem!");

        initMenu();
    }

    private static void abrirMenuPagamentos() {

    }
}
