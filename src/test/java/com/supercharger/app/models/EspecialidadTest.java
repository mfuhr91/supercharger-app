package com.supercharger.app.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EspecialidadTest {

    @Test
    void getByNombre() {
        Especialidad expected = Especialidad.FRENOS;

        Especialidad result = Especialidad.getByNombre("FRENOS");

        Assertions.assertEquals(expected,result);
    }
}