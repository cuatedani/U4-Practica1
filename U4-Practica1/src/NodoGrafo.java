/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER}
 */
public class NodoGrafo {
    char valor;
    NodoGrafo Sig;
    NodoGrafo Ant;
    NodoArista Ari;
    
    public NodoGrafo(char v){
        valor=v;
        Sig=null;
        Ant=null;
        Ari=null;
    }
}
