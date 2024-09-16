package br.com.portifolio.ProjetoBebidas.controller;


import br.com.portifolio.ProjetoBebidas.controllers.BebidaController;
import br.com.portifolio.ProjetoBebidas.service.BebidaService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("TipoBebidaControllerTest")
public class BebidaControllerTest {

    @InjectMocks
    BebidaController bebidaController;


    @Mock
    private BebidaService BebidaService;

}
