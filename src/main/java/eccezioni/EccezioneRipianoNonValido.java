/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eccezioni;

/**
 *
 * @author gian
 */
public class EccezioneRipianoNonValido extends Exception
{
    private String message="Ripiano non valido";
       
       public String toString()
       {
           return message;
       }
}
