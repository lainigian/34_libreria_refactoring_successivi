/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eccezioni;

/**
 *
 * @author gian
 */
public class FileException extends Exception
{
    private String message;
    public FileException(String message) 
    {
        this.message=message;
    }
    
    public String toString()
    {
        return message;
    }
    
}
