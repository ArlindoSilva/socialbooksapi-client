package com.algaworks.socialbooks.client.domain;

import com.sun.jdi.VoidType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class LivrosClient {

    private RestTemplate restTemplate;
    private String URI_BASE;
    private String URN_BASE = "/livros";
    private String credencial;

    public LivrosClient(String url, String usuario, String senha){
        restTemplate = new RestTemplate();

        URI_BASE = url.concat(URN_BASE);

        String credencialAux = usuario + ":" + senha;

        credencial = "Basic " + Base64.getEncoder()
                .encodeToString(credencialAux.getBytes());
    }

    public List<Livro> listar(){
        RequestEntity<Void> request = RequestEntity
                .get(URI.create(URI_BASE))
                .header("Authorization", credencial)
             .build();

        ResponseEntity<Livro[]> response = restTemplate.exchange(request, Livro[].class);

        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public String salvar(Livro livro) {
        RequestEntity<Livro> request = RequestEntity
                .post(URI.create(URI_BASE))
                .header("Authorization", credencial)
                .body(livro);

        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        return Objects.requireNonNull(response.getHeaders().getLocation()).toString();
    }

    public Livro buscar(String uri){
        RequestEntity<Void> request = RequestEntity
                .get(URI.create(uri))
                .header("Authorization", credencial)
                .build();

        ResponseEntity<Livro> response = restTemplate.exchange(request, Livro.class);

        return response.getBody();
    }

}
