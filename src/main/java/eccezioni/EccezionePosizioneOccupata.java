/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eccezioni;

/**
 *
 * @author gian
 */
public class EccezionePosizioneOccupata extends Exception
{
    private String message="Posizione occupata";
       
       public String toString()
       {
           return message;
       }
    
}
