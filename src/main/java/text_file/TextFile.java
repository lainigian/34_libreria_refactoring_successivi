/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package text_file;

import eccezioni.FileException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *  Classe che rappresenta un file sul quale è possibile
 * leggere e/o scrivere.
 * La modalità di accesso al file (lettira o scrittura) va specificata
 * nel costruttore con una 'R' (read, lettura) o 'W' (write, scrittura) 
 * nel parametro mode.
 * @author gian
 */
public class TextFile 
{
    
    private char mode;
    BufferedReader reader;
    BufferedWriter writer;
    
    /**
     * Costruttore
     * @param fileName pathname del file fisico
     * @param mode  'R'--> apertura in lettura
     *              'W' --> apertura in scrittura
     * @throws FileNotFoundException    Nel caso non fosse possibile trovare il file
     * @throws IOException              Nel caso non fosse possibile accedere al file
     */
    public TextFile(String fileName, char mode) throws FileNotFoundException, IOException
    {
        this.mode='R';
        if (mode=='W' || mode=='w')
            this.mode='W';
        
        if (this.mode=='R')
            reader=new BufferedReader(new FileReader(fileName));
        else
            writer=new BufferedWriter(new FileWriter(fileName));
    }
    
    /**
     * Costruttore con append
     * @param fileName pathname del file fisico
     * @param mode  'R'--> apertura in lettura
     *              'W' --> apertura in scrittura
     * @param append    parametro boolean, nel caso di accesso in scrittura 
     *                  specifica se il file viene scritto in append (true) o no (false)
     * @throws FileNotFoundException    Nel caso non fosse possibile trovare il file
     * @throws IOException              Nel caso non fosse possibile accedere al file
     */
    public TextFile(String fileName, char mode, boolean append) throws FileNotFoundException, IOException
    {
        this.mode='R';
        if (mode=='W' || mode=='w')
            this.mode='W';
        
        if (this.mode=='R')
            reader=new BufferedReader(new FileReader(fileName));
        else
            writer=new BufferedWriter(new FileWriter(fileName,append));
    }
    
    /**
     * Metodo per scrivere una stringa di testo sul file
     * @param s La stringa da scrivere
     * @throws FileException nel caso il file fosse aperto in lettura anzichè in scrittura
     * @throws IOException nel caso non sia possibile accedere al file
     */
    public void toFile(String s) throws FileException, IOException
    {
        if (this.mode=='R')
            throw new FileException("Impossibile scrivere, file aperto in lettura");
        else
        {
            writer.write(s); //Scrive la stringa su file
            writer.newLine();
        }
    }
    
    /**
     * Metodo per leggere una riga dal file di testo
     * @return La riga letta
     * @throws FileException nel caso il file fosse aperto in scrittura anzichè in lettura
     *          viene utilizzata anche per uscire dal ciclo di lettura all termine del file
     * @throws IOException nel caso non sia possibile accedere al file
     */

    public String fromFile() throws FileException, IOException
    {
        String line="";
        if (this.mode=='W')
            throw new FileException("Impossibile leggere, file aperto in scrittura");
        else
        {
            line=reader.readLine();
            if (line==null)
                throw new FileException("Fine del file");
            else 
                return line;
        }
    }
    
    /**
     * Metodo per chiudere il file
     * si quando è aperto in lettura
     * sia quando è aperto in scrittura
     * @throws IOException 
     */
    public void close() throws IOException
    {
        if(mode=='R')
            reader.close();
        else
            writer.close();
    }
}
