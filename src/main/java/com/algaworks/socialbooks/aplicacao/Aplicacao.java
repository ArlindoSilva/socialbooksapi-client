package com.algaworks.socialbooks.aplicacao;

import com.algaworks.socialbooks.client.domain.Livro;
import com.algaworks.socialbooks.client.domain.LivrosClient;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Aplicacao {

    public static void main (String[] args) throws ParseException {
        LivrosClient cliente = new LivrosClient();

        List<Livro> listaLivros = cliente.listar();

        for (Livro livro : listaLivros) {
            System.out.println("Livro: " + livro.getNome());
        }

        Livro livro = new Livro();
        livro.setNome("Git passo-a-passo");
        livro.setEditora("Algaworks");

        SimpleDateFormat publicacao = new SimpleDateFormat("dd/MM/yyyy");
        livro.setPublicacao(publicacao.parse("10/05/2024"));

        livro.setResumo("Git descomplicado para devs.");
        String localizacao = cliente.salvar(livro);
        System.out.println("URI do livro salvo: " + localizacao);


    }


}
