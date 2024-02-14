/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._libreria;

import Ordinatore.Ordinatore;
import eccezioni.EccezionePosizioneNonValida;
import eccezioni.EccezionePosizioneOccupata;
import eccezioni.EccezionePosizioneVuota;
import eccezioni.EccezioneRipianoNonValido;
import eccezioni.FileException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import text_file.TextFile;

/**
 *
 * @author gian
 */
public class Scaffale 
{
    private Mensola[] ripiani;
    private final int NUM_RIPIANI=5;
    
    /*
    Costruttore: Istanzia l’array ripiani 
    costituito da 5 mensole vuote (anche esse devono essere istanziate).
    */
    public Scaffale()
    {
        ripiani=new Mensola[NUM_RIPIANI];
        for (int i=0;i<NUM_RIPIANI;i++)
            ripiani[i]=new Mensola();
    }
    
    /*
    Costruttore di copia
    */
    public Scaffale(Scaffale s) throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido, EccezionePosizioneVuota
    {
        Libro libro;
        //Istanzio l'array di mensole
        ripiani=new Mensola[s.getNumRipiani()];
        
        //Istanzio le 5 mensole
        // e in ogni mensola copio i libri 
        
        for (int i=0;i<NUM_RIPIANI;i++)
        {
            ripiani[i]=new Mensola();
            {
                for (int j=0;j<ripiani[i].getNumMaxVolumi();j++)
                {
                    libro=s.getLibro(i, j);
                    if (libro!=null)
                        setLibro(libro, i, j);
                }
            }
        }
    }
    
  /**
   * Inserisce il libro nella posizione "posizione" del ripiano “ripiano”. 
   * @param libro Il volume da inserire
   * @param ripiano Il ripiano dello scaffale in cui inserire il libro
   * @param posizione La posizione del ripiano in cui inserire il libro
   * @throws EccezionePosizioneOccupata  Se in quel ripiano-posizione è giù contenuto un libro
   * @throws EccezionePosizioneNonValida Se la posizione non esiste
   * @throws EccezioneRipianoNonValido Se il ripiano non esiste
   */
    //Il metodo può essere void poichè i vari casi sono gestiti con le eccezioni
    public void setLibro(Libro libro, int ripiano, int posizione) throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido
    {
        
        if (ripiano<0 || ripiano>=NUM_RIPIANI)
            throw new eccezioni.EccezioneRipianoNonValido();
        else
        {
            ripiani[ripiano].setVolume(libro, posizione);
        }        
    }
    
   /**
    * Restituisce il volume nella posizione "posizione" del ripiano “ripiano”. 
    * @param ripiano Il ripiano da cui ottenere il volume
    * @param posizione La posizione, all'interno del ripiano, da cui ottenere il volume
    * @return
    * @throws EccezioneRipianoNonValido Se il ripiano non esiste
    * @throws EccezionePosizioneNonValida Se la posizione non esiste
    * @throws EccezionePosizioneVuota Se in quel ripiano-posizione non è presente un volume
    */
    public Libro getLibro(int ripiano, int posizione) throws EccezioneRipianoNonValido, EccezionePosizioneNonValida, EccezionePosizioneVuota
    {
        Libro libro;
        if (ripiano<0 || ripiano>=NUM_RIPIANI)
            throw new EccezioneRipianoNonValido();    //il ripiano non è valido
        libro=ripiani[ripiano].getVolume(posizione);
        return libro;         
    }
    
    /**
     * Elimina il volume nella posizione "posizione" del ripiano “ripiano”. 
     * @param ripiano ripiano da cui rimuovere il volume
     * @param posizione posizione del ripiano da cuio rimuovere il volume
     * @throws EccezioneRipianoNonValido Se il ripiano non esiste
     * @throws EccezionePosizioneNonValida Se la posizione nonesiste
     * @throws EccezionePosizioneVuota se in quel ripiano-posizione non è presente il volume
     */
    public void rimuoviLibro(int ripiano, int posizione) throws EccezioneRipianoNonValido, EccezionePosizioneNonValida, EccezionePosizioneVuota
    {
        
        if (ripiano<0 || ripiano>=NUM_RIPIANI)
            throw new EccezioneRipianoNonValido();
        
        ripiani[ripiano].rimuoviVolume(posizione);
    }
    
    public int getNumRipiani()
    {
        return NUM_RIPIANI;
    }
    
   
    public int getNumMaxLibri()
    {
        int numeroMassimoLibri=0;
        for (int i=0;i<NUM_RIPIANI;i++)
        {
            numeroMassimoLibri=numeroMassimoLibri+ripiani[i].getNumMaxVolumi();
        } /*
        Restituisce il numero massimo di libri nello scaffale
    */
        return numeroMassimoLibri;
    }
    
    /**
     * Restituisce un array contenente  titoli dei libri di uno specifico autore
     * presente nello scaffale
     * @param autore L'autore per il quale effettuare la ricerca
     * @return
     * @throws EccezioneRipianoNonValido 
     * @throws EccezionePosizioneNonValida 
     */
    public String[] elencoTitoliAutore(String autore) throws EccezioneRipianoNonValido, EccezionePosizioneNonValida
    {
        Libro libro;
        String[] elencoTitoli;
        
        int contaLibriAutore=0;
        //conto il numero di libri di un autore presenti
        //per dimensionare l'array "elencoTitoli"
        for(int i=0;i<NUM_RIPIANI;i++)
        {
            for(int j=0;j<ripiani[i].getNumMaxVolumi();j++)
            {
                try 
                {
                    libro=getLibro(i, j);
                    if (libro.getAutore().equals(autore))
                        contaLibriAutore++;
                } 
                catch (EccezionePosizioneVuota ex) 
                {
                    
                }
            }
        }
        if (contaLibriAutore==0)
            return null; //nessun libro dell'autore presente
        
        elencoTitoli=new String[contaLibriAutore];  //istanzio l'array della dimensione giusta
        //Rufaccio il ciclo nidificato precedente per popolare l'array di titoli
        int posizioneTitolo=0;
         for(int i=0;i<NUM_RIPIANI;i++)
        {
            for(int j=0;j<ripiani[i].getNumMaxVolumi();j++)
            {
                try 
                {
                    libro=getLibro(i, j);
                    if (libro.getAutore().equals(autore))
                    {
                        elencoTitoli[posizioneTitolo]=libro.getTitolo();
                        posizioneTitolo++;
                    }
                } 
                catch (EccezionePosizioneVuota ex) 
                {
                   
                }
            }
        }
         return elencoTitoli;   //restituisco l'array di titoli
    }
    
     /*
        Restituisce il numero di volumi presenti nello specifico ripiano
    */
    public int getNumMaxLibri(int ripiano) throws EccezioneRipianoNonValido
    {
        if (ripiano<0 || ripiano>NUM_RIPIANI)
            throw new EccezioneRipianoNonValido();
        return ripiani[ripiano].getNumMaxVolumi();
    }
    
     /*
        Restituisce il numero di volumi presenti nel ripiano specificato 
        come parametro
    */
    public int getNumLibri(int ripiano) throws EccezioneRipianoNonValido
    {
        if (ripiano<0 || ripiano>NUM_RIPIANI)
            throw new EccezioneRipianoNonValido();
        int numeroLibriPresenti;
        if (ripiano<0 || ripiano>=NUM_RIPIANI)
            return -1;   //ripiano non valido
        
        numeroLibriPresenti=ripiani[ripiano].getNumVolumi();
        return numeroLibriPresenti;
    }
    
    public Libro[] elencoOrdinatoLibriPresenti() throws EccezioneRipianoNonValido, EccezionePosizioneNonValida
    {
        int nLibriPresenti=0;
        Libro[] elencoLibriPresenti;
        Mensola ripiano;
        Libro volume;
        int contatore=0;
        //Calcolo numero libri presenti
        for (int i=0;i<getNumRipiani();i++)
        {
            nLibriPresenti+=getNumLibri(i);
        }
        //creo array
        elencoLibriPresenti=new Libro[nLibriPresenti];
       
        for(int i=0;i<getNumRipiani();i++)
        {
            ripiano=ripiani[i];
            for (int j=0;j<ripiano.getNumMaxVolumi();j++)
            {
                try 
                {
                    volume=getLibro(i, j);
                    elencoLibriPresenti[contatore]=new Libro(volume);
                    contatore++;
                } 
                catch (EccezionePosizioneVuota ex) 
                {
                   
                }                 
            }
        }
        
        elencoLibriPresenti=Ordinatore.selectionSortCrescenteTitolo(elencoLibriPresenti);
        return elencoLibriPresenti;
    }
    
    public void esportaLibriCSV(String nomeFile) throws EccezioneRipianoNonValido, EccezionePosizioneNonValida, IOException, FileException
    {
        Libro lib;
        String libroCSV;
        TextFile f1;
        
        f1=new TextFile(nomeFile, 'W',true); //Apro il file in scrittura in append
        for(int i=0;i<getNumRipiani();i++)
        {
            for(int j=0;j<getNumMaxLibri(i);j++)
            {
                try 
                {
                    lib=getLibro(i, j);
                    libroCSV=i+";"+j+";"+lib.getTitolo()+";"+lib.getAutore()+";"+lib.getNumeropagine();
                    f1.toFile(libroCSV);
                } 
                catch (EccezionePosizioneVuota ex) 
                {
                    //Se la posizione è vuota non fa nulla
                } 
            }
        }
       f1.close();
    }
    
    public String toString()
    {
        String s="";
        for (int i=0;i<NUM_RIPIANI;i++)
        {
            s+="\nRIPIANO "+i+"\n"+ripiani[i].toString();
            
        }
        return s;
    }
}
