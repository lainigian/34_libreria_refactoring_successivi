/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._libreria;

import static java.lang.Math.*;

/**
 *
 * @author gian
 */
public class MieFunzioni 
{
    public static double arrotonda(double x, int numeroCifreDecimali)
    {
        double y;
        y=x*pow(10,numeroCifreDecimali);
        y=round(y);
        y=y/pow(10, numeroCifreDecimali);
        return y;
    }
}
