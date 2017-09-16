/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes;

import ifes.codigo.Quadro;

/**
 *
 * @author erickmazzocco
 */
public class Main {    
    public static void main(String[] args) {
        Quadro quadro = new Quadro();
        quadro.gerarQuadroHorario();
        
        System.out.println("Fim");
        
    }
}
