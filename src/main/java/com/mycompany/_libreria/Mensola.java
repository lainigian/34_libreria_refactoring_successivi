/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._libreria;

import eccezioni.*;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Gian
 */
public class Mensola implements Serializable
{
    private Libro[] volumi;
    private final int NUM_MAX_VOLUMI=15;
    
    /*
    Costruttore
    */
    public Mensola()
    {
        volumi=new Libro[NUM_MAX_VOLUMI];
    }
    
    /**
     * 
     * @param m
     * @throws EccezionePosizioneOccupata
     * @throws EccezionePosizioneNonValida
     * @throws EccezionePosizioneVuota 
     */
    
    public Mensola(Mensola m) throws EccezionePosizioneOccupata, EccezionePosizioneNonValida, EccezionePosizioneVuota
    {
        volumi=new Libro[NUM_MAX_VOLUMI];
        Libro libro;
        for(int i=0;i<m.getNumMaxVolumi();i++)
        {
            libro=m.getVolume(i);
            if (libro!=null)
                setVolume(libro, i);
        }
    }
    
    /**
     * 
     * @param elencoLibri 
     */
  /*  public Mensola(Libro[] elencoLibri)
    {
        volumi=new Libro[NUM_MAX_VOLUMI];
        int contaLibri=0;
        Libro libro;
        
       // if (elencoLibri.length==0)
        //    return;     //Se elencoLibri è vuota il costruttore costruisce una mensola vuota e termina qui
        
        for(int i=0;i<elencoLibri.length;i++)
        {
            libro=elencoLibri[i];
            if (libro!=null)
                volumi[i]=new Libro(libro);
        }
    }
    
   /**
    * Aggiunge un volume alla mensola 
    * @param volume Volume da aggiungere
    * @param posizione Posizione in cui aggiungere il volume
    * @return
    * @throws EccezionePosizioneOccupata La posizione in cui si cerca di aggiungere un volume è occupata, il volume non verrà aggiunto
    * @throws EccezionePosizioneNonValida La posizione non esiste
    */
    public void setVolume(Libro volume, int posizione) throws EccezionePosizioneOccupata, EccezionePosizioneNonValida
    {
        try
        {
            if (volumi[posizione]!=null)
                throw new EccezionePosizioneOccupata();
            volumi[posizione]=new Libro(volume); //aggiungo una COPIA di volume
           // return posizione;
        }
        catch(IndexOutOfBoundsException e)
        {
            //return -1;
            throw new EccezionePosizioneNonValida();        
        }
      
    }
    
   /**
    * Restituisce il libro che si trova in una determinata posizione
    * @param posizione  La posizione in cui cercare il libro
    * @return
    * @throws EccezionePosizioneVuota La posizione non contiene un volume
    * @throws EccezionePosizioneNonValida La posizione non esiste
    */
    public Libro getVolume(int posizione) throws EccezionePosizioneVuota, EccezionePosizioneNonValida
    {
        try
        {
            return new Libro(volumi[posizione]);
        }
        catch(NullPointerException e)
        {
            throw new EccezionePosizioneVuota();
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            throw new EccezionePosizioneNonValida();
        }
         
    }
    
    /**
     * Libera (inserendo null) la posizione "posizione" 
     * @param posizione La posizione da cui eliminare il volume
     * @return
     * @throws EccezionePosizioneNonValida La posizione non esiste
     * @throws EccezionePosizioneVuota La posizione non contien un volume
     */
    public void rimuoviVolume(int posizione) throws EccezionePosizioneNonValida, EccezionePosizioneVuota
    {
        /*
        if (posizione>=NUM_MAX_VOLUMI || posizione<0)
            return -1;
           */
         if (volumi[posizione]==null)
               // return -2;      //posizione vuota
                throw new EccezionePosizioneVuota();
        try
        {
           
            volumi[posizione]=null;
            //return posizione;
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            throw new EccezionePosizioneNonValida();
        }   
    }
    
    public int getNumMaxVolumi()
    {
        return NUM_MAX_VOLUMI;
    }
    
    public int getNumVolumi()
    {
        int contatore=0;
        for (int i=0;i<NUM_MAX_VOLUMI;i++)
        {
            if (volumi[i]!=null)
                contatore++;
        }
        return contatore;
    }
    
    public boolean presenzaTitolo(String titolo) throws EccezionePosizioneVuota, EccezionePosizioneNonValida
    {
        Libro libro;
        for(int i=0;i<NUM_MAX_VOLUMI;i++)
        {
            libro=getVolume(i);
            if (libro!=null)
            {
                if (libro.getTitolo().equals(titolo))
                    return true;
            }
        }
        return false;
    }
    
    public String toString()
    {
        String s="";
        for (int i=0;i<getNumMaxVolumi();i++)
        {
            if (volumi[i]!=null)
            {
                s+="\nPosizione: "+i+"\t"+volumi[i].toString();
            }
        }
       return s;     
    }
    
    public boolean equals(Object o)
    {
        Mensola m=(Mensola) o;
       
        //Controllo prima che le due mensole contengano lo stesso numero di volumi
        if (getNumVolumi()!=m.getNumVolumi())
            return false;
        //Ora controllo che i volumi presenti siano uguali
        for(int i=0;i<getNumMaxVolumi();i++)
        {
            try 
            {
                if (!getVolume(i).equals(m.getVolume(i)))
                    return false;
            } 
            catch (EccezionePosizioneVuota ex) 
            {
               //Non fare nulla
            } 
            catch (EccezionePosizioneNonValida ex) 
            {
                //non succederà mai
            }
        }
        
        return true;
    }
}
