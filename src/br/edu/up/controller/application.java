package br.edu.up.controller;

import br.edu.up.dao.UsuarioDAO;
import br.edu.up.model.Usuario;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class application {

    private static String DATE_PATTERN = "dd/MM/yyyy";

    public static void main(String[] args) throws SQLException, ParseException {

        initMenu();

        /*
        UsuarioDAO uDao = new UsuarioDAO();
        Usuario usuario = new Usuario("DouglasTESTE", "GorgesTESTE", new Date());
        uDao.apagar(4);

        List<Usuario> listaUsuarios = uDao.listar();
        for(Usuario itemUsuario : listaUsuarios){
            System.out.printf(itemUsuario.toString());
        } */

    }

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
        System.out.println("|        4 - Apagar cadastro    |");
        System.out.println("________________________________");
        Scanner scanner = new Scanner(System.in);
        Integer nrMenu = scanner.nextInt();

        if (nrMenu.equals(1)) {
            cadastrarUsuario();
        } else if (nrMenu.equals(2)) {
            editarUsuario();
        } else if (nrMenu.equals(3)) {
            listarUsuario();
        } else if (nrMenu.equals(4)) {
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
        //listarUsuario();

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

    private static void abrirMenuVeiculos() throws ParseException {
        System.out.println("________________________________");
        System.out.println("Selecione um submenu de Veículos:");
        System.out.println("|        1 - Cadastrar          |");
        System.out.println("|        2 - Editar             |");
        System.out.println("|        3 - Listar             |");
        System.out.println("|        4 - Apagar             |");
        System.out.println("________________________________");
        Scanner scanner = new Scanner(System.in);
        Integer nrMenu = scanner.nextInt();

        if (nrMenu.equals(1)) {
            //cadastrarUsuario();
        } else if (nrMenu.equals(2)) {
            //editarUsuario();
        } else if (nrMenu.equals(3)) {
            listarUsuario();
        } else if (nrMenu.equals(4)) {
            apagarUsuario();
        }
    }

    private static void abrirMenuAlugueis() {

    }

    private static void abrirMenuPagamentos() {

    }
}
