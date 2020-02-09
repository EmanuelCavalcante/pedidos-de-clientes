package com.pedidosdeclientes.serviceimpl.util;

import java.util.Random;

public class GeradorDeNumeros {

    public static Integer gerarNumeroAleatorio() {
        Random random = new Random();
        return random.nextInt(100);
    }

}
