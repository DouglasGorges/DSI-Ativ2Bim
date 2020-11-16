package br.edu.up.controller;

import br.edu.up.dao.UsuarioDAO;
import br.edu.up.model.Usuario;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class teste {
    public static void main(String[] args) throws SQLException {

        UsuarioDAO uDao = new UsuarioDAO();
        Usuario usuario = new Usuario("DouglasTESTE", "GorgesTESTE", new Date());
        uDao.salvar(usuario);

        List<Usuario> listaUsuarios = uDao.listar();
        for(Usuario itemUsuario : listaUsuarios){
            System.out.printf(itemUsuario.toString());
        }
/*
        String url = "jdbc:sqlite:/Users/douglasgorges/Codes/Faculty/DSI/db_Ativ2Bim/ativ2bim.db";
        Connection con = DriverManager.getConnection(url);

        //Executor
        Statement statement = con.createStatement();
        String sql = "select * from teste";

        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
            int id = rs.getInt("id");
            String nome = rs.getString("nome");

            System.out.println("Nome: " + nome + " - ID: " + id);
        }
        con.close(); */
    }
}
