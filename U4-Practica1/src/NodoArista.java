/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER}
 */
public class NodoArista {
    NodoGrafo Dir;
    NodoArista Sig;
    NodoArista Ant;
    
    public NodoArista(NodoGrafo D){
        Dir=D;
        Sig=null;
        Ant=null;
    }
}
