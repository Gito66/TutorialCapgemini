package com.ccsw.tutorial.prestamo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.tutorial.prestamo.model.Prestamo;

@ExtendWith(MockitoExtension.class)
public class PrestamoTest {

    public static final Long EXISTS_PRESTAMO_ID = 1L;
    public static final Long NOT_EXISTS_PRESTAMO_ID = 0L;

    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private PrestamoServiceImpl prestamoService;

    @Test
    public void getExistsPrestamoIdShouldReturnPrestamo() {

        Prestamo prestamo = mock(Prestamo.class);
        when(prestamo.getId()).thenReturn(EXISTS_PRESTAMO_ID);
        when(prestamoRepository.findById(EXISTS_PRESTAMO_ID)).thenReturn(Optional.of(prestamo));

        Prestamo prestamoResponse = prestamoService.get(EXISTS_PRESTAMO_ID);

        assertNotNull(prestamoResponse);

        assertEquals(EXISTS_PRESTAMO_ID, prestamoResponse.getId());
    }

    @Test
    public void getNotExistsPrestamoIdShouldReturnNull() {

        when(prestamoRepository.findById(NOT_EXISTS_PRESTAMO_ID)).thenReturn(Optional.empty());

        Prestamo prestamo = prestamoService.get(NOT_EXISTS_PRESTAMO_ID);

        assertNull(prestamo);
    }

}