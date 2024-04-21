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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import text_file.TextFile;

/**
 *
 * @author gian
 */
public class Scaffale implements Serializable
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
    public Scaffale(Scaffale s) throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezioneRipianoNonValido
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
                    try 
                    {
                        libro=s.getLibro(i, j);
                        setLibro(libro, i, j);
                    } 
                    catch (EccezionePosizioneVuota ex) 
                    {
                          //non fare nulla
                    }           
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
                    if (libro.getAutore().equalsIgnoreCase(autore))
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
                    if (libro.getAutore().equalsIgnoreCase(autore))
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
    
    /**
     * Esporta i libri presenti nello scaffale in un file di testo in formato CSV.
     * Per ogni libro vengono esportate le seguenti informazioni:
     * ripiano;posizione;titolo;autore;numeroPagine.
     * Prima di esportare i libri, cancello dal file CSV tutti i dati presenti
     * @param nomeFile  Pathname del file di testo in cui verranno salvati i dati
     * @throws IOException  Se non è possibile accedere al file
     * @throws FileException    Se il file è aperto in lettura anzichè in scrittura
     */
    public void esportaLibriCSV(String nomeFile) throws IOException, FileException
    {
        Libro lib;
        String libroCSV;
        TextFile f1;
        
        //Prima cancello i libri presenti nel file
        f1=new TextFile(nomeFile, 'W');
        f1.close();
        
        //Poi apro il file i append per aggiungere, uno alla volta, il libri dello scaffale
        f1=new TextFile(nomeFile, 'W',true); //Apro il file in scrittura in append
        for(int i=0;i<getNumRipiani();i++)
        {
            try 
            {
                for(int j=0;j<getNumMaxLibri(i);j++) 
                {
                    try
                    {
                        lib=getLibro(i, j);
                        libroCSV=i+";"+j+";"+lib.getTitolo()+";"+lib.getAutore()+";"+lib.getNumeropagine();
                        f1.toFile(libroCSV);
                    }
                    catch (EccezionePosizioneNonValida | EccezioneRipianoNonValido | EccezionePosizioneVuota ex)
                    { 
                        //In tutti questi casi l'applicazione non fa nulla
                    }
                }
            } 
            catch (EccezioneRipianoNonValido ex) 
            {
                //L'applicazione noin fa nulla. Questa eccezione non si vrrifica mai se il ciclo for è costruito correttamente.
            }
        }
       f1.close();
    }
    
    // 
    /**
     * Legge i libri presenti in un file di testo CSV e li aggiunge allo scaffale,
     * Se il ripiano o la posizione del libro non è valida,il  libro letto dal file non viene caricato
     * Se il ripiano/posizione del libro è già occupato, il libro letto dal file non viene caricato.
     * @param nomeFile pathname del file CSV da cui importare i volumi
     * @throws IOException Se non è possibile accedere al file
     */
    public void importaLibriCSV(String nomeFile) throws IOException
    {
        TextFile f1=new TextFile(nomeFile, 'R'); //Apro il file in lettura
        String libroLetto;
        String[] libroLettoSplit;
        String autore,titolo;
        int ripiano, posizione,numeroPagine;
        
        Libro lib = null;
        
        try 
        {
            //ciclo di lettura da file
            libroLetto=f1.fromFile();  
            while(libroLetto!=null)
            {     
               
                try 
                {
                    libroLettoSplit=libroLetto.split(";");
                    ripiano=Integer.parseInt(libroLettoSplit[0]);
                    posizione=Integer.parseInt(libroLettoSplit[1]);
                    titolo=libroLettoSplit[2];
                    autore=libroLettoSplit[3];
                    numeroPagine=Integer.parseInt(libroLettoSplit[4]);
                    lib=new Libro(titolo,autore,numeroPagine);
                    setLibro(lib, ripiano, posizione);
                } 
                catch (EccezionePosizioneOccupata | EccezionePosizioneNonValida |EccezioneRipianoNonValido | NumberFormatException ex) 
                {
                   //Il libro letto da file viene ignorato in tutti questi casi
                }  
                
                libroLetto=f1.fromFile();   //Leggo il libro successivo
            }
        } 
        catch (FileException ex) 
        {
           //Esce dal ciclo quando il file è terminato oppure è stato apreto in scrittura anzichè in lettura
        }
        f1.close();
    }
    
    /**
     * Salva l'oggetto scaffale this (e tutti gli oggetti in esso contenuti) su un file binario
     * @param nomeFile Nome del file
     * @throws FileNotFoundException Se il file non viene trovato in fase di chiusura
     * @throws IOException Se non è possibile accedere al file
     */
    public void salvaScaffale(String nomeFile) throws FileNotFoundException, IOException
    {
        
        FileOutputStream f1=new FileOutputStream(nomeFile);
        ObjectOutputStream output=new ObjectOutputStream(f1);
        output.writeObject(this);
        output.flush();
        output.close();
    }
    
    /**
     * Restituisce un oggetto scaffale (e tutti gli oggetti in esso contenuti)
     * precedentemente memeorizzato in un file binario
     * 
     * @param nomeFile Pathname del file binario
     * @return
     * @throws FileNotFoundException Se il file non viene trovato in fase di chiusura
     * @throws IOException      Se non è possibile accedere al file
     * @throws ClassNotFoundException Se la struttura dati memorizzata nel file non corrisponde allo scaffale
     */
    public static Scaffale caricaScaffale(String nomeFile) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        FileInputStream f1=new FileInputStream(nomeFile);
        ObjectInputStream input=new ObjectInputStream(f1);
        Scaffale s;    
        s=(Scaffale)input.readObject();
        input.close();
        return s;
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
//    
//    //Restituisce una copia di una mensola
//    public Mensola getMensola(int ripiano)
//    {
//        Mensola m=new Mensola();
//        Libro l;
//        try
//        {
//            for(int i=0;i<this.getNumMaxLibri(ripiano);i++)
//            {
//                try 
//                {
//                    l=getLibro(ripiano, i);
//                    m.setVolume(l, i);
//                } 
//                catch (EccezioneRipianoNonValido ex) 
//                {
//                        //non succede
//                } 
//                catch (EccezionePosizioneNonValida ex) 
//                {
//                    //non succede
//                }
//                catch (EccezionePosizioneVuota ex) 
//                {
//                    //non fare nulla
//                } 
//                catch (EccezionePosizioneOccupata ex) 
//                {
//                   //non succede
//                }
//            }
//        }
//        catch(EccezioneRipianoNonValido e)
//        {
//            //non succede
//        }
       
//            return m;
//        }
    
    public boolean equals(Object o)
    {
        Scaffale s=(Scaffale) o;
        Libro libroThis,libroS;
        //Faccio passare tutte le posizioni di this
        try
        {
            for (int i=0;i<getNumRipiani();i++)
            {
                for(int j=0;j<getNumMaxLibri(i);j++)
                {
                    try 
                    {
                        
                        libroThis=this.getLibro(i, j);
                        //Se in this c'è un libro, verifico che tale libro ci sia anche in s
                        try
                        {
                            libroS=s.getLibro(i, j);
                            if(!libroThis.equals(libroS))
                                return false; //due libri diversi nella stessa posizione
                        }
                        catch(EccezionePosizioneVuota e)
                        {
                            return false; //non cìè il libro in s
                        }  
                    } 
                    catch (EccezionePosizioneNonValida ex) 
                    {
                      //non succede
                    } 
                    catch (EccezionePosizioneVuota ex) 
                    {
                       //Se la posizione è vuota in This, deve essere vuota anche in s
                        try
                        {
                            libroS=s.getLibro(i, j);
                            return false; //in s c'è un libro e in this non c'è
                        }
                        catch(EccezionePosizioneVuota e)
                        {
                           //ok non cìè il libro in s
                        } 
                        catch (EccezionePosizioneNonValida ex1) 
                        {
                            //mai
                        }  
                    }
                }

            }
        }
        catch (EccezioneRipianoNonValido e)
        {
            //mai
        }
       
        return true;
    }    
}
