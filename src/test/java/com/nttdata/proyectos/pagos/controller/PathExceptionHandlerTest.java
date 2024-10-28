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
        // Act
        String response = pathExceptionHandler.handleNotFound();

        // Assert
        assertEquals("Ruta no encontrada. Por favor verifica la URL.", response);
    }

    @Test
    void testHandleGeneralException() {
        // Arrange
        Exception exception = new Exception("Error de prueba");

        // Act
        String response = pathExceptionHandler.handleGeneralException(exception);

        // Assert
        assertEquals("Se ha producido un error en el servidor. Detalles: Error de prueba", response);
    }

}
