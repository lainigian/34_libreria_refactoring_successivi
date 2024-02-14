/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package console_input;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author gian
 */
public class ConsoleInput 
{
    private InputStreamReader input;
    private BufferedReader reader;
    
    public ConsoleInput()
    {
        input=new InputStreamReader(System.in);
        reader=new BufferedReader(input);
    }
    
    /**
     * Legge da console un numero intero
     * @return
     * @throws IOException se avviene un errore durante la lettura
     * @throws NumberFormatException se il dato inserito non è un numero intero
     */
    public int readInt() throws IOException,NumberFormatException
    {
        return (Integer.parseInt(reader.readLine()));
    }
    
     /**
     * Legge da console un numero intero short
     * @return
     * @throws IOException se avviene un errore durante la lettura
     * @throws NumberFormatException se il dato inserito non è un numero short
     */
    public short readShort() throws IOException,NumberFormatException
    {
        return (Short.parseShort(reader.readLine()));
    }
    
     /**
     * Legge da console un numero intero long
     * @return
     * @throws IOException se avviene un errore durante la lettura
     * @throws NumberFormatException se il dato inserito non è un numero long
     */
    public long readLong() throws IOException,NumberFormatException
    {
        return (Long.parseLong(reader.readLine()));
    }
    
     /**
     * Legge da console un numero float
     * @return
     * @throws IOException se avviene un errore durante la lettura
     * @throws NumberFormatException se il dato inserito non è un numero float
     */
    public float readFloat() throws IOException,NumberFormatException
    {
        return (Float.parseFloat(reader.readLine()));
    }
    
     /**
     * Legge da console un numero double
     * @return
     * @throws IOException se avviene un errore durante la lettura
     * @throws NumberFormatException se il dato inserito non è un numero double
     */
    public double readDouble() throws IOException,NumberFormatException
    {
        return (Double.parseDouble(reader.readLine()));
    }
    
     /**
     * Legge da console un boolean
     * @return
     * @throws IOException se avviene un errore durante la lettura
     * @throws NumberFormatException se il dato inserito non è un boolean
     */
    public boolean readBoolean() throws IOException,NumberFormatException
    {
        return (Boolean.parseBoolean(reader.readLine()));
    }
    
     /**
     * Legge da console una stringa
     * @return
     * @throws IOException se avviene un errore durante la lettura
     */
    public String readString() throws IOException
    {
        return (reader.readLine());
    }
}
