package com.nttdata.proyectos.pagos.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PathExceptionHandlerTest {

    @InjectMocks
    private PathExceptionHandler pathExceptionHandler;

    public PathExceptionHandlerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleNotFound() {
        String response = pathExceptionHandler.handleNotFound();
        assertEquals("Ruta no encontrada. Por favor verifica la URL.", response);
    }

    @Test
    void testHandleGeneralException() {
        Exception exception = new Exception("Error de prueba");
        String response = pathExceptionHandler.handleGeneralException(exception);
        assertEquals("Se ha producido un error en el servidor. Detalles: Error de prueba", response);
    }

}
