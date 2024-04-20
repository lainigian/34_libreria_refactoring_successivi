/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany._libreria;

import eccezioni.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author gian
 */
public class ScaffaleTest {
    
    public ScaffaleTest() {
    }
    
    static  Libro l1;
    static Libro l2;
    Scaffale s1;
    
    @BeforeAll
    private static void istanziaLibri()
    {
        l1=new Libro("libro1","autore1",100);
        l2=new Libro("libro2","autore2",200);
    }
    
    @BeforeEach
    private void istanziaScaffale()
    {
        s1=new Scaffale();
    }
    
    
     @Test
    public void testCostruttore() throws Exception 
    {
        int numRipiani=s1.getNumRipiani();
        int libriR0=s1.getNumLibri(0);
        int libriR1=s1.getNumLibri(1);
        int libriR2=s1.getNumLibri(2);
        int libriR3=s1.getNumLibri(3);
        int libriR4=s1.getNumLibri(4);
        
        assertTrue(numRipiani==5 && libriR0==0 && libriR1==0 && libriR2==0 && libriR3==0 && libriR4==0);
    }


    /**
     * Test of setLibro method, of class Scaffale.
     */
    @Test
    public void testSetGetLibro() throws Exception 
    {
       
        
        s1.setLibro(l1, 1, 0);
        Libro l3=s1.getLibro(1, 0);
        
        assertEquals(l1,l3);
    }

    
    @Test
    void testCostruttore1() throws EccezioneRipianoNonValido {
        assertEquals(5, s1.getNumRipiani());
        assertEquals(0, s1.getNumLibri(0));
        assertEquals(0, s1.getNumLibri(1));
        assertEquals(0, s1.getNumLibri(2));
        assertEquals(0, s1.getNumLibri(3));
        assertEquals(0, s1.getNumLibri(4));
    }

    @Test
    void testSetGetLibroInserimentoCorretto1() throws EccezionePosizioneVuota, EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido {
        s1.setLibro(l1, 1, 0);
        Libro l3 = s1.getLibro(1, 0);
        assertEquals(l1, l3);
    }

    // Test case 3
    @Test
    void testSetLibroRipianoNegativo() {
        assertThrows(EccezioneRipianoNonValido.class, () -> {
            s1.setLibro(l1, -1, 0);
        });
    }

    // Test case 4
    @Test
    void testSetLibroRipianoEccessivo() {
        assertThrows(EccezioneRipianoNonValido.class, () -> {
            s1.setLibro(l1, 5, 0);
        });
    }

    // Test case 5
    @Test
    void testSetLibroPosizioneNegativa() {
        assertThrows(EccezionePosizioneNonValida.class, () -> {
            s1.setLibro(l1, 1, -1);
        });
    }

    // Test case 6
    @Test
    void testSetLibroPosizioneEccessiva() {
        assertThrows(EccezionePosizioneNonValida.class, () -> {
            s1.setLibro(l1, 1, 15);
        });
    }

    // Test case 7
    @Test
    void testSetLibroPosizioneOccupata() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido {
        s1.setLibro(l1, 0, 0);
        assertThrows(EccezionePosizioneOccupata.class, () -> {
            s1.setLibro(l2, 0, 0);
        });
    }

    // Test case 8
    @Test
    void testGetLibroRipianoNegativo() throws EccezionePosizioneNonValida, EccezionePosizioneOccupata, EccezioneRipianoNonValido {
        s1.setLibro(l1, 0, 0);
        assertThrows(EccezioneRipianoNonValido.class, () -> {
            s1.getLibro(-1, 0);
        });
    }

    // Test case 9
    @Test
    void testGetLibroRipianoEccessivo() throws EccezionePosizioneNonValida, EccezionePosizioneOccupata, EccezioneRipianoNonValido {
        s1.setLibro(l1, 0, 0);
        assertThrows(EccezioneRipianoNonValido.class, () -> {
            s1.getLibro(5, 0);
        });
    }

     // Test case 10
    @Test
    void testGetLibroPosizioneNegativa() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido {
        s1.setLibro(l1, 0, 0);
        assertThrows(EccezionePosizioneNonValida.class, () -> {
            s1.getLibro(0, -1);
        });
    }
    
    
    
    //Verificati OK
    
    

    // Test case 11
    @Test
    void testGetLibroPosizioneEccessiva() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido {
        s1.setLibro(l1, 0, 0);
        assertThrows(EccezionePosizioneNonValida.class, () -> {
            s1.getLibro(0, 15);
        });
    }

    // Test case 12
    @Test
    void testGetLibroPosizioneVuota() throws EccezionePosizioneNonValida, EccezionePosizioneOccupata, EccezioneRipianoNonValido {
        s1.setLibro(l1, 0, 0);
        assertThrows(EccezionePosizioneVuota.class, () -> {
            s1.getLibro(0, 1);
        });
    }

    // Test case 13
    @Test
    void testGetNumRipiani() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido {
        s1.setLibro(l1, 0, 0);
        s1.setLibro(l2, 0, 1);
        assertEquals(5, s1.getNumRipiani());
    }

    // Test case 14
    @Test
    void testGetNumMaxLibriComplessivo() {
        assertEquals(75, s1.getNumMaxLibri());
    }

    // Test case 15
    @Test
    void testGetNumMaxLibriPerOgniRipiano() throws EccezioneRipianoNonValido {
        assertEquals(15, s1.getNumMaxLibri(0));
        assertEquals(15, s1.getNumMaxLibri(1));
        assertEquals(15, s1.getNumMaxLibri(2));
        assertEquals(15, s1.getNumMaxLibri(3));
        assertEquals(15, s1.getNumMaxLibri(4));
    }

    // Test case 16
    @Test
    void testGetNumLibriPerOgniRipiano() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido {
        s1.setLibro(l1, 0, 0);
        s1.setLibro(l2, 0, 1);
        s1.setLibro(l1, 1, 0);
        s1.setLibro(l2, 2, 1);
        s1.setLibro(l1, 4, 0);
        try
        {
            s1.setLibro(l2, 0, 10);
        }
        catch(EccezionePosizioneOccupata e)
        {
            
        }

        assertEquals(3, s1.getNumLibri(0));
        assertEquals(1, s1.getNumLibri(1));
        assertEquals(1, s1.getNumLibri(2));
        assertEquals(0, s1.getNumLibri(3));
        assertEquals(1, s1.getNumLibri(4));
    }

    // Test case 17
    @Test
    void testElencoTitoliAutoreConAutorePresente() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido {
        s1.setLibro(l1, 0, 0);
        s1.setLibro(l2, 0, 1);
        s1.setLibro(l1, 1, 0);
        s1.setLibro(l2, 2, 1);
        s1.setLibro(l1, 4, 0);
        s1.setLibro(l2, 0, 10);

        String[] titoli = s1.elencoTitoliAutore("autore1");
        assertEquals(3, titoli.length);
        assertEquals("libro1", titoli[0]);
        assertEquals("libro1", titoli[1]);
        assertEquals("libro1", titoli[2]);
    }

    // Test case 18
    @Test
    void testElencoTitoliAutoreConAutoreNonPresente() throws EccezioneRipianoNonValido, EccezionePosizioneNonValida {
        String[] titoli = s1.elencoTitoliAutore("autore3");
        assertEquals(null, titoli);
    }

    // Test case 19
    @Test
    void testElencoOrdinatoLibriPresentiConLibriPresenti() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido {
        s1.setLibro(l1, 0, 0);
        s1.setLibro(l2, 0, 1);
        s1.setLibro(l1, 1, 0);
        s1.setLibro(l2, 2, 1);
        s1.setLibro(l1, 4, 0);
        s1.setLibro(l2, 0, 10);

        Libro[] elencoLibri = s1.elencoOrdinatoLibriPresenti();
        assertEquals(6, elencoLibri.length);
        assertEquals(l1, elencoLibri[0]);
        assertEquals(l1, elencoLibri[1]);
        assertEquals(l1, elencoLibri[2]);
        assertEquals(l2, elencoLibri[3]);
        assertEquals(l2, elencoLibri[4]);
        assertEquals(l2, elencoLibri[5]);
    }

    // Test case 20
    @Test
    void testElencoOrdinatoLibriPresentiSenzaLibriPresenti() throws EccezioneRipianoNonValido, EccezionePosizioneNonValida {
        Libro[] elencoLibri = s1.elencoOrdinatoLibriPresenti();
        assertEquals(0, elencoLibri.length);
    }
    
    
    
    
    
    
    //verificare
    // Test case 21
@Test
void testRimuoviLibroRimozioneCorretta() throws EccezionePosizioneVuota, EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido {
    s1.setLibro(l1, 0, 0);
    s1.setLibro(l2, 0, 1);
    s1.setLibro(l1, 1, 0);
    s1.setLibro(l2, 2, 1);
    s1.setLibro(l1, 4, 0);
    
    s1.rimuoviLibro(0, 1);
    
    assertThrows(EccezionePosizioneVuota.class, () -> s1.getLibro(0, 1));
}

// Test case 22
@Test
void testRimuoviLibroPosizioneVuota() {
    assertThrows(EccezionePosizioneVuota.class, () -> s1.rimuoviLibro(4, 4));
}

// Test case 23
@Test
void testRimuoviLibroRipianoNegativo() {
    assertThrows(EccezioneRipianoNonValido.class, () -> s1.rimuoviLibro(-1, 0));
}

// Test case 24
@Test
void testRimuoviLibroRipianoEccessivo() {
    assertThrows(EccezioneRipianoNonValido.class, () -> s1.rimuoviLibro(5, 0));
}

// Test case 25
@Test
void testRimuoviLibroPosizioneNegativa() {
    assertThrows(EccezionePosizioneNonValida.class, () -> s1.rimuoviLibro(0, -1));
}

// Test case 26
@Test
void testRimuoviLibroPosizioneEccessiva() {
    assertThrows(EccezionePosizioneNonValida.class, () -> s1.rimuoviLibro(0, 15));
}

// Test case 27
@Test
void testCostruttoreDiCopia() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido, EccezionePosizioneVuota {
    
    s1.setLibro(l1, 0, 0);
    s1.setLibro(l2, 0, 1);
    s1.setLibro(l1, 1, 0);
    s1.setLibro(l2, 2, 1);
    s1.setLibro(l1, 4, 0);
    s1.setLibro(l2, 0, 10);
    
    Scaffale s2 = new Scaffale(s1);
    
    assertTrue(s1.equals(s2));
}

// Test case 28
@Test
void testIndipendenzaOggettiCostruttoreDiCopia() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido, EccezionePosizioneVuota {
    s1.setLibro(l1, 0, 0);
    s1.setLibro(l2, 0, 1);
    s1.setLibro(l1, 1, 0);
    s1.setLibro(l2, 2, 1);
    s1.setLibro(l1, 4, 0);
    s1.setLibro(l2, 0, 10);
    
    Scaffale s2 = new Scaffale(s1);
    s2.rimuoviLibro(0, 0);
    assertFalse(s1.equals(s2));
}



//Verificare da qui

// Test case 29
@Test
void testEsportaLibriCSVImportaLibriCSVCorretta() throws IOException, EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido, EccezionePosizioneVuota, FileException {
    s1.setLibro(l1, 0, 0);
    s1.setLibro(l2, 0, 1);
    s1.setLibro(l1, 1, 0);
    s1.setLibro(l2, 2, 1);
    s1.setLibro(l1, 4, 0);
    s1.setLibro(l2, 0, 10);

    s1.esportaLibriCSV("volumi.csv");
    Scaffale s2 = new Scaffale();
    s2.importaLibriCSV("volumi.csv");

    assertTrue(s1.equals(s2));
}

// Test case 30
@Test
void testEsportaLibriCSVVersoPercorsoInesistente() {
    assertThrows(IOException.class, () -> s1.esportaLibriCSV("z:\\volumi.csv"));
}

// Test case 31
@Test
void testImportaLibriCSVDaFileInesistente() {
    assertThrows(IOException.class, () -> s1.importaLibriCSV("fileCheNonEsiste.csv"));
}

// Test case 32
@Test
void testImportaLibriCSVDaFileEsistenteMaVuoto() throws EccezioneRipianoNonValido, IOException {
    s1.importaLibriCSV("fileVuoto.csv");
    assertEquals(0, s1.getNumLibri(0) + s1.getNumLibri(1) + s1.getNumLibri(2) + s1.getNumLibri(3) + s1.getNumLibri(4));
}

// Test case 33
@Test
void testImportaLibriCSVDaFileConDatiNonCorretti() {
    assertThrows(NumberFormatException.class, () -> s1.importaLibriCSV("fileNonCorretto.csv"));
}

// Test case 34
@Test
void testSalvaScaffaleCaricaScaffaleCorretta() throws IOException, FileNotFoundException, ClassNotFoundException, EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido {
    s1.setLibro(l1, 0, 0);
    s1.setLibro(l2, 0, 1);
    s1.setLibro(l1, 1, 0);
    s1.setLibro(l2, 2, 1);
    s1.setLibro(l1, 4, 0);

    s1.salvaScaffale("scaffale.bin");
    Scaffale s2 = new Scaffale();
    s2=s1.caricaScaffale("scaffale.bin");

    assertTrue(s1.equals(s2));
}

// Test case 35
@Test
void testSalvaScaffaleVersoPercorsoInesistente() {
    assertThrows(IOException.class, () -> s1.salvaScaffale("z:\\scaffale.bin"));
}

// Test case 36
@Test
void testCaricaScaffaleDaPercorsoInesistente() {
    assertThrows(FileNotFoundException.class, () -> s1.caricaScaffale("z:\\scaffale.bin"));
}

// Test case 37
@Test
void testCaricaScaffaleDaFileEsistenteVuoto() throws EccezioneRipianoNonValido, IOException, FileNotFoundException, ClassNotFoundException {
    s1.salvaScaffale("scaffale.bin");
    Scaffale s2 = new Scaffale();
    s2.caricaScaffale("scaffale.bin");

    assertEquals(0, s2.getNumLibri(0) + s2.getNumLibri(1) + s2.getNumLibri(2) + s2.getNumLibri(3) + s2.getNumLibri(4));
}

// Test case 38
@Test
void testCaricaScaffaleDaFileEsistenteConDatiNonCorretti() {
    assertThrows(IOException.class, () -> s1.caricaScaffale("scaffaleCorrotto.bin"));
}

// Test case 39
@Test
void testToString() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido, IOException {
    s1.setLibro(l1, 0, 0);
    String stringaAttesa;// = "\nRIPIANO 0\n\nPosizione: 0\t\nTitolo: libro1\nAutore: autore1\nNumero pagine: 100\n\nPosizione: 5\t\nTitolo: libro1\nAutore: autore1\nNumero pagine: 100\n\nRIPIANO 1\nRIPIANO 2\nRIPIANO 3\nRIPIANO 4";
  //  stringaAttesa="\nRIPIANO 0\n\nPosizione: "+i+"\t"+"\nTitolo: "+getTitolo()+"\nAutore:"+getAutore()+"\nNumero pagine="+getNumeropagine()+"\n";
    
    stringaAttesa="\nRIPIANO "+0+"\n"+"\nPosizione: "+0+"\t"+"\nTitolo: "+"libro1"+"\nAutore:"+"autore1"+"\nNumero pagine="+100+"\n"+"\nRIPIANO "+1+"\n"+"\nRIPIANO "+2+"\n"+"\nRIPIANO "+3+"\n"+"\nRIPIANO "+4+"\n";
    assertEquals(stringaAttesa, s1.toString());
}
    
   // Test case 40
@Test
void testEqualsUnLibroUgualeInPosizioniDiverse() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido
{
    s1.setLibro(l1, 0, 0);
    Scaffale s2=new Scaffale();
    s2.setLibro(l1, 0, 1);
    assertFalse(s1.equals(s2));
} 

@Test
void testEqualsLibroDiversoNellaStessaPosizione() throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido
{
    s1.setLibro(l1, 0, 0);
    Scaffale s2=new Scaffale();
    s2.setLibro(l2, 0, 0);
    assertFalse(s1.equals(s2));
} 
    
}

