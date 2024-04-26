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
    

    
    public Mensola(Mensola m) throws EccezionePosizioneNonValida
    {
        volumi=new Libro[NUM_MAX_VOLUMI];
        Libro libro = null;
        for(int i=0;i<m.getNumMaxVolumi();i++)
        {
            try 
            {
                libro=m.getVolume(i);
                setVolume(libro, i);
                
            } 
            catch (EccezionePosizioneVuota ex) 
            {
                //Non fare nulla
            } 
            catch (EccezionePosizioneOccupata ex) 
            {
                //Non succederà mai
            }
            
            
        }
    }
    

  
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
    
   
    public void rimuoviVolume(int posizione) throws EccezionePosizioneNonValida, EccezionePosizioneVuota
    {
        
        if (posizione>=NUM_MAX_VOLUMI || posizione<0)
            throw new EccezionePosizioneNonValida();
           
         if (volumi[posizione]==null)
               // return -2;      //posizione vuota
                throw new EccezionePosizioneVuota();
         
         volumi[posizione]=null;
   
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
    
    public boolean presenzaTitolo(String titolo) throws  EccezionePosizioneNonValida
    {
        Libro libro = null;
        for(int i=0;i<NUM_MAX_VOLUMI;i++)
        {
            try 
            {
                libro=getVolume(i);
            } 
            catch (EccezionePosizioneVuota ex) 
            {
                //non fare nulla
            }
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
    
    
}
