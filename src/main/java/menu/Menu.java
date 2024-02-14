/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import console_input.ConsoleInput;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gian
 */
public class Menu 
{
    private String [] elencoVoci;
    private int numeroVoci;
    
    public Menu(String[] elencoVoci)
    {
        //Copio le voci parametro nell'elenco voci attributo
        numeroVoci=elencoVoci.length;
        this.elencoVoci=new String[numeroVoci];
        for(int i=0;i<this.numeroVoci;i++)
            this.elencoVoci[i]=elencoVoci[i];
    }
    
    public void visualizzaMenu()
    {
        System.out.println("MENU:");
        for(int i=0;i<this.numeroVoci;i++)
        {
            System.out.println(i+")\t"+ elencoVoci[i]);
        }
    }
    
    public int sceltaMenu()
    {
        //String sceltaStringa;
        boolean sceltaOk=true;
        int scelta=0;
        //Scanner tastiera=new Scanner(System.in);
        ConsoleInput tastiera=new ConsoleInput();
       
        do
        {
            System.out.println("Scegli --> ");
            sceltaOk=true;
            try 
            {
                
                scelta=tastiera.readInt();
                if (scelta<0 || scelta>=elencoVoci.length)
                {
                    sceltaOk=false;
                    System.out.println("Scelta non valida.");
                }
            } 
            catch (IOException | NumberFormatException eccezione) 
            {
                System.out.println("Input non conforme. Inserire di nuovo la scelta.");
                sceltaOk=false;
            } 
        }while(!sceltaOk);
        
        return scelta;     
    }
}
