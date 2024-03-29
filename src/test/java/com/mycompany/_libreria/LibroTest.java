/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany._libreria;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author gian
 */
class LibroTest {
    
    Libro libro;

    @BeforeEach
    void setUp() throws Exception {
        libro = new Libro();
    }

    @AfterEach
    void tearDown() throws Exception {
        libro = null;
    }

    @Test
    void testCostruttoreConNumeroPagineNegativo() {
        libro = new Libro("titolo1", "Autore1", -1);
        assertEquals("titolo1", libro.getTitolo());
        assertEquals("Autore1", libro.getAutore());
        assertEquals(0, libro.getNumeropagine());
    }

    @Test
    void testCostruttoreCorrettamenteFunzionante() {
        libro = new Libro("titolo1", "Autore1", 100);
        assertEquals("titolo1", libro.getTitolo());
        assertEquals("Autore1", libro.getAutore());
        assertEquals(100, libro.getNumeropagine());
    }

    @Test
    void testCostruttoreDiDefault() {
        assertEquals("", libro.getTitolo());
        assertEquals("", libro.getAutore());
        assertEquals(0, libro.getNumeropagine());
    }

    @Test
    void testCostruttoreDiCopia() {
        Libro l1 = new Libro("titolo1", "Autore1", 100);
        Libro l2 = new Libro(l1);
        assertEquals(l1.getTitolo(), l2.getTitolo());
        assertEquals(l1.getAutore(), l2.getAutore());
        assertEquals(l1.getNumeropagine(), l2.getNumeropagine());
    }

    @Test
    void testIndipendenzaOggettoIstanziatoConCostruttoreDiCopia() {
        Libro l1 = new Libro("titolo1", "Autore1", 100);
        Libro l2 = new Libro(l1);
        l1 = null;
        assertEquals("titolo1", l2.getTitolo());
        assertEquals("Autore1", l2.getAutore());
        assertEquals(100, l2.getNumeropagine());
    }

    @Test
    void testSetTitolo() {
        libro.setTitolo("titolo2");
        assertEquals("titolo2", libro.getTitolo());
    }

    @Test
    void testSetAutore() {
        libro.setAutore("Autore2");
        assertEquals("Autore2", libro.getAutore());
    }

    @Test
    void testSetNumeroPagineConNumeroDiPagineNegativo() {
        libro.setNumeroPagine(-1);
        assertEquals(0, libro.getNumeropagine());
    }

    @Test
    void testSetNumeroPagineConNumeroDiPaginePositivo() {
        libro.setNumeroPagine(200);
        assertEquals(200, libro.getNumeropagine());
    }

    @Test
    void testGetCostoPagina() {
        assertEquals(0.05, Libro.getCostoPagina());
    }

    @Test
    void testSetCostoPaginaConValorePositivo() {
        Libro.setCostoPagina(0.1);
        assertEquals(0.1, Libro.getCostoPagina());
        Libro.setCostoPagina(0.05); // Ripristino il valore di default
    }

    @Test
    void testSetCostoPaginaConValoreNegativo() {
        Libro.setCostoPagina(-0.1);
        assertEquals(0.05, Libro.getCostoPagina()); // Dovrebbe rimanere invariato
    }

    @Test
    void testPrezzoConNumeroDiPagineNegativo() {
        libro = new Libro("titolo1", "Autore1", -1);
        assertEquals(5.5, libro.prezzo());
    }

    @Test
    void testPrezzoConNumeroDiPagineCorretto() {
        libro = new Libro("titolo1", "Autore1", 100);
        assertEquals(10.5, libro.prezzo());
    }
    
     @Test
    void testToString() {
        libro = new Libro("titolo1", "Autore1", 100);
        assertEquals("\nTitolo: titolo1\nAutore:Autore1\nNumero pagine=100\n", libro.toString());
    }

    @Test
    void testToStringConLibroDiDefault() {
        assertEquals("\nTitolo: \nAutore:\nNumero pagine=0\n", libro.toString());
    }
}