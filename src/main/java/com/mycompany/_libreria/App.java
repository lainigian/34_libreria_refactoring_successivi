/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany._libreria;

import console_input.ConsoleInput;
import eccezioni.EccezionePosizioneNonValida;
import eccezioni.EccezionePosizioneOccupata;
import eccezioni.EccezionePosizioneVuota;
import eccezioni.EccezioneRipianoNonValido;
import eccezioni.FileException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import menu.Menu;

/**
 *
 * @author Gian
 */
public class App {

    public static void main(String[] args) 
    {
        
        //Gestione con Menu
        Scaffale s1=new Scaffale();
        int sceltaMenu;
       // Scanner tastiera=new Scanner(System.in);
        ConsoleInput tastiera=new ConsoleInput();
        String vociMenu[]=new String[8];
        {
            vociMenu[0]="Esci";
            vociMenu[1]="Visualizza libri presenti nello scaffale";
            vociMenu[2]="Aggiungi volume";
            vociMenu[3]="Elimina volume (ripiano, posizione)";
            vociMenu[4]="Cerca volume (ripiano, posizione)";
            vociMenu[5]="Cerca libri autore";
            vociMenu[6]="Volumi presenti in ordine alfabetico";
            vociMenu[7]="Esporta tutti i libri in formato CSV";
        }
        
        //Pathname file in cui verranno esportati i libri in formato CSV
        String nomeFileCSV="libriScaffale.txt";
        
        //Istanzio il menu
        Menu menu=new Menu(vociMenu);
        
        do
        {
            menu.visualizzaMenu();
            sceltaMenu=menu.sceltaMenu();
            System.out.println("Scelta: "+sceltaMenu);
            Libro libro=null;
            int esito;
            int ripiano = 0;
            int posizione = 0;
            String autore = null;
            String[] elencoTitoliAutore = null;
            boolean inputOk=true;
            
            switch (sceltaMenu) 
            {
                case 0:
                    System.out.println("Arrivederci.");
                    return;
                case 1:
                    System.out.println(s1.toString());
                    break;
                case 2: // aggiungi volume
                    libro=new Libro();
                    do
                    {
                        inputOk=true;
                        try 
                        {
                            System.out.println("\nRipiano [0..4]--> ");
                            ripiano=tastiera.readInt();
                        } 
                        catch (IOException ex) 
                        {
                            System.out.println("Errore nell'inserimento del dato di input. Inserire di nuovo");
                            inputOk=false;
                        }    
                        catch (NumberFormatException ex) 
                        {
                            System.out.println("Input non conforme. Inserire di nuovo");
                            inputOk=false;
                        }
                        
                    }while(!inputOk);
                    
                    do
                    {
                        try 
                        {
                            inputOk=true;
                            System.out.println("\nPosizione [0..14]--> ");
                            posizione=tastiera.readInt();
                        } 
                        catch (IOException ex) 
                        {
                            System.out.println("Errore nell'inserimento del dato di input. Inserire di nuovo");
                            inputOk=false;
                        } 
                        catch (NumberFormatException ex) 
                        {
                            System.out.println("Input non conforme. Inserire di nuovo");
                            inputOk=false;
                        }
                        
                    }while(!inputOk);
                    
                    //Per le stringhe non creo il ciclo di reinserimento dell'input
                    //poichè non può verificarsi un input non conforme
                    try 
                    {
                        System.out.println("\nTitolo--> ");
                        libro.setTitolo(tastiera.readString());
                    } 
                    catch (IOException ex) 
                    {
                        System.out.println("Errore nell'inserimento del dato di input. Inserire di nuovo");
                    } 

                    //Per le stringhe non creo il ciclo di reinserimento dell'input
                    //poichè non può verificarsi un input non conforme
                    try 
                    {
                        System.out.println("\nAutore--> ");
                        libro.setAutore(tastiera.readString());
                    } 
                    catch (IOException ex) 
                    {
                        System.out.println("Errore nell'inserimento del dato di input. Inserire di nuovo");
                    } 
                    
                    do
                    {
                        try 
                        {
                            inputOk=true;
                            System.out.println("\nNumero pagine--> ");
                            libro.setNumeroPagine(tastiera.readInt());
                        } 
                        catch (IOException ex) 
                        {
                            System.out.println("Errore nell'inserimento del dato di input. Inserire di nuovo");
                            inputOk=false;
                        } 
                        catch (NumberFormatException ex) 
                        {
                            System.out.println("Input non conforme. Inserire di nuovo");
                            inputOk=false;
                        }
                        
                    }while(!inputOk);

                    try 
                    {
                        s1.setLibro(libro, ripiano, posizione);
                        System.out.println("Inserimento avvenuto correttamente");
                    } 
                    catch (EccezionePosizioneOccupata ex) 
                    {
                        System.out.println("Posizione non vuota");
                    } 
                    catch (EccezionePosizioneNonValida ex) 
                    {
                         System.out.println("Posizione non valida");
                    } 
                    catch (EccezioneRipianoNonValido ex) 
                    {
                        System.out.println("Ripiano non valido");
                    } 
                    break;


                case 3: //elimina volume
                    do
                    {
                        inputOk=true;
                        try 
                        {
                            System.out.println("\nRipiano [0..4]--> ");
                            ripiano=tastiera.readInt();
                        } 
                        catch (IOException ex) 
                        {
                            System.out.println("Errore nell'inserimento del dato di input. Inserire di nuovo");
                            inputOk=false;
                        }    
                        catch (NumberFormatException ex) 
                        {
                            System.out.println("Input non conforme. Inserire di nuovo");
                            inputOk=false;
                        }
                        
                    }while(!inputOk);
                    
                    do
                    {
                        try 
                        {
                            inputOk=true;
                            System.out.println("\nPosizione [0..14]--> ");
                            posizione=tastiera.readInt();
                        } 
                        catch (IOException ex) 
                        {
                            System.out.println("Errore nell'inserimento del dato di input. Inserire di nuovo");
                            inputOk=false;
                        } 
                        catch (NumberFormatException ex) 
                        {
                            System.out.println("Input non conforme. Inserire di nuovo");
                            inputOk=false;
                        }
                        
                    }while(!inputOk);
                                             
                    try 
                    {
                        s1.rimuoviLibro(ripiano, posizione);
                        System.out.println("Rimozione avvenuta correttamente"); 
                    } 
                    catch (EccezioneRipianoNonValido ex) 
                    {
                       System.out.println("Ripiano non valido");
                    } 
                    catch (EccezionePosizioneNonValida ex) 
                    {
                        System.out.println("Posizione non valida");
                    } 
                    catch (EccezionePosizioneVuota ex)
                    {
                        System.out.println("Posizione gia' vuota");
                    }
                    break;

                case 4:  //cerca volume
                    do
                    {
                        inputOk=true;
                        try 
                        {
                            System.out.println("\nRipiano [0..4]--> ");
                            ripiano=tastiera.readInt();
                        } 
                        catch (IOException ex) 
                        {
                            System.out.println("Errore nell'inserimento del dato di input. Inserire di nuovo");
                            inputOk=false;
                        }    
                        catch (NumberFormatException ex) 
                        {
                            System.out.println("Input non conforme. Inserire di nuovo");
                            inputOk=false;
                        }
                        
                    }while(!inputOk);
                    
                    do
                    {
                        try 
                        {
                            inputOk=true;
                            System.out.println("\nPosizione [0..14]--> ");
                            posizione=tastiera.readInt();
                        } 
                        catch (IOException ex) 
                        {
                            System.out.println("Errore nell'inserimento del dato di input. Inserire di nuovo");
                            inputOk=false;
                        } 
                        catch (NumberFormatException ex) 
                        {
                            System.out.println("Input non conforme. Inserire di nuovo");
                            inputOk=false;
                        }
                        
                    }while(!inputOk);
                    
                    try 
                    {
                        libro=s1.getLibro(ripiano, posizione);
                        System.out.println("Libro richiesto:\n"+libro.toString());
                    } 
                    catch (EccezioneRipianoNonValido ex) 
                    {
                         System.out.println("Ripiano non valido");
                    } 
                    catch (EccezionePosizioneNonValida ex) 
                    {
                        System.out.println("Posizione non valida");
                    } 
                    catch (EccezionePosizioneVuota ex) 
                    {
                        System.out.println("Nessun volume presente in questo ripiano-posizione");
                    }
                    break;

                case 5:
                    try 
                    {
                       // System.out.println("Premi invio per continuare...");
                       // tastiera.readString();
                        System.out.println("\nAutore--> ");
                        autore=tastiera.readString();
                        elencoTitoliAutore=s1.elencoTitoliAutore(autore); 
                    } 
                    catch (EccezioneRipianoNonValido ex) 
                    {
                         System.out.println("Ripiano non valido");
                    } 
                    catch (EccezionePosizioneNonValida ex) 
                    {
                        System.out.println("Posizione non valida");
                    } 
                    catch (IOException ex) 
                    { 
                         System.out.println("Errore nell'inserimento del dato di input. Inserire di nuovo");
                         inputOk=false;
                    }
                    
                    if (elencoTitoliAutore==null)
                        System.out.println("Nessun titolo presente dell'autore "+autore);
                    else
                    {
                        for(int i=0;i<elencoTitoliAutore.length;i++)
                        {
                            System.out.println(elencoTitoliAutore[i]);
                        }
                    }
                    break;


                case 6:
                    Libro[] elencoLibriOrdinato = null;
                    try 
                    {
                        elencoLibriOrdinato=s1.elencoOrdinatoLibriPresenti();
                    } 
                    catch (EccezioneRipianoNonValido ex) 
                    {
                         System.out.println("Ripiano non valido");
                    } 
                    catch (EccezionePosizioneNonValida ex) 
                    {
                        System.out.println("Posizione non valida");
                    } 
                    for(int i=0;i<elencoLibriOrdinato.length;i++)
                    {
                        System.out.println(elencoLibriOrdinato[i].toString());
                    }
                    break;
                
                case 7:
                {
                    try 
                    {
                        s1.esportaLibriCSV(nomeFileCSV);
                        System.out.println("Esportazione avvenuta correttamente");
                    } 
                    catch (EccezioneRipianoNonValido ex) 
                    {
                       //questa eccezione non dovrebbe mai verificarsi visto che i ripiani e le posizioni 
                        //sono state indicate correttamente nel metodo invocato
                        System.out.println("Ripiano non valido");
                    } 
                    catch (EccezionePosizioneNonValida ex) 
                    {
                       //questa eccezione non dovrebbe mai verificarsi visto che i ripiani e le posizioni 
                        //sono state indicate correttamente nel metodo invocato
                        System.out.println("Posizione non valida");
                    } 
                    catch (IOException ex) 
                    {
                        System.out.println("Errore! Impossibile accedere al file.");
                    } 
                    catch (FileException ex) 
                    {
                        System.out.println(ex.toString());
                    }
                }
                    
                    
                    break;

            }
            
        }while(true);
        
        
    }
    
    
    
}
