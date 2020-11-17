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
        System.out.println("|        1 - Usuários          |");
        System.out.println("|        2 - Veículos          |");
        System.out.println("|        3 - Alugueis          |");
        System.out.println("|        4 - Pagamentos        |");
        System.out.println("________________________________");
        Scanner scanner = new Scanner(System.in);
        Integer nrMenu = scanner.nextInt();

        if (nrMenu.equals(1)) {
            abrirMenuUsuarios();
        } else if (nrMenu.equals(2)) {
            abrirMenuVeiculos();
        } else if (nrMenu.equals(3)) {
            abrirMenuAlugueis();
        } else if (nrMenu.equals(4)) {
            abrirMenuPagamentos();
        }
    }

    private static void abrirMenuUsuarios() throws ParseException {
        System.out.println("________________________________");
        System.out.println("Selecione um submenu de Usuários:");
        System.out.println("|        1 - Cadastrar          |");
        System.out.println("|        2 - Editar             |");
        System.out.println("|        3 - Listar             |");
        System.out.println("|        4 - Apagar             |");
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
        System.out.println("--Cadastrar Usuario--");
        System.out.println("Digite o nome: ");
        String nomeUsuario = scanUsuario.next();
        System.out.println("Digite o sobrenome: ");
        String sobrenomeUsuario = scanUsuario.next();
        System.out.println("Digite a data de nascimento: (dd/MM/aaaa)");
        String strDtNascimentoUsuario = scanUsuario.next();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dtNascimentoUsuario = sdf.parse(strDtNascimentoUsuario);

        Usuario usuario = new Usuario(nomeUsuario, sobrenomeUsuario, dtNascimentoUsuario);

        UsuarioDAO usuarioDao = new UsuarioDAO();
        usuarioDao.salvar(usuario);

    }

    private static void editarUsuario() throws ParseException {
        listarUsuario();

        System.out.println("Digite o código do usuário que desejas editar: ");
        Scanner scanUsuario = new Scanner(System.in);
        Integer codigoUsuario = scanUsuario.nextInt();

        UsuarioDAO usuarioDao = new UsuarioDAO();
        Usuario usuario = usuarioDao.buscarPorId(codigoUsuario);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dtNascFormatada = df.format(usuario.getDtNascimento());

        System.out.println("Dados anteriores: " +
                usuario.getNome() + " " +
                usuario.getSobrenome() + ", " +
                dtNascFormatada);

        System.out.println("Dados novos:");
        System.out.println("Digite o nome: ");
        String nomeUsuario = scanUsuario.next();
        System.out.println("Digite o sobrenome: ");
        String sobrenomeUsuario = scanUsuario.next();
        System.out.println("Digite a data de nascimento: (dd/MM/aaaa)");
        String strDtNascimentoUsuario = scanUsuario.next();

        if(nomeUsuario != null && !nomeUsuario.isEmpty())
            usuario.setNome(nomeUsuario);
        if(sobrenomeUsuario != null && !sobrenomeUsuario.isEmpty())
            usuario.setSobrenome(sobrenomeUsuario);
        if(strDtNascimentoUsuario != null && !strDtNascimentoUsuario.isEmpty()){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dtNascimentoUsuario = sdf.parse(strDtNascimentoUsuario);
            usuario.setDtNascimento(dtNascimentoUsuario);
        }

        usuarioDao.atualizar(usuario);

        listarUsuario();
    }

    private static void listarUsuario() {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        List<Usuario> listaDeUsuarios = usuarioDao.listar();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
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

    private static void apagarUsuario() {
        listarUsuario();

        System.out.println("Digite o código do usuário que desejas apagar: ");
        Scanner scanUsuario = new Scanner(System.in);
        Integer codigoUsuario = scanUsuario.nextInt();

        UsuarioDAO usuarioDao = new UsuarioDAO();
        usuarioDao.apagar(codigoUsuario);

        listarUsuario();
    }

    private static void abrirMenuVeiculos() {

    }

    private static void abrirMenuAlugueis() {

    }

    private static void abrirMenuPagamentos() {

    }
}
