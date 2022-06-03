package com.example.ap2.Dao;

import com.example.ap2.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuariosDao {

    private static List<Usuario> listaUsuariosCadastrados = new ArrayList<>();

    public UsuariosDao(){
        if(listaUsuariosCadastrados.size() == 0){
            preencherListaUsuarios();
        }
    }

    public static List<Usuario> getListaUsuarios(){
        return new ArrayList<>(listaUsuariosCadastrados);
    }

    public static Usuario getUsuario(String email, String senha){

        //Usuario retornado = new Usuario();

        for(Usuario usuario : listaUsuariosCadastrados){

            if(email.equals(usuario.getEmail()) && senha.equals(usuario.getSenha())){
                return usuario;
            }

        }

        return null;

    }

    public static boolean atualizarListaDeDadosUsuario(Usuario usuario){

        Usuario user = getUsuario(usuario.getEmail(), usuario.getSenha());

        if(usuario != null){

            user.setAlunoSalvos(usuario.getAlunoSalvos());
            user.setProdutosSalvos(usuario.getProdutosSalvos());

            return true;

        }
           return false;
    }

    public static boolean salvar(Usuario usuario){
        if(usuario != null){
            listaUsuariosCadastrados.add(usuario);
            return true;
        }
        return false;
    }

    public static void limparListaUsuariosCadastrados(){
        listaUsuariosCadastrados.clear();
    }

    public void preencherListaUsuarios(){
        listaUsuariosCadastrados.add(new Usuario(new String[]{"Bruce Dickinson","bruce@gmail.com","123456"}));
        listaUsuariosCadastrados.add(new Usuario(new String[]{"Arlison Martins","arlison@gmail.com","123456"}));
        listaUsuariosCadastrados.add(new Usuario(new String[]{"Thcylla Sá","thcylla@gmail.com","123456"}));
        listaUsuariosCadastrados.add(new Usuario(new String[]{"Vitor Martins","vitor@gmail.com","123456"}));
    }

}
