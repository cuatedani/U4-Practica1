/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER}
 */
public class Grafo {
    NodoGrafo Ini;
    NodoGrafo Fin;
    
    public boolean CrearNodo(char valor){
        NodoGrafo temp = new NodoGrafo (valor);
        if(temp==null){
            return false;
        }
        if(Ini==null && Fin==null){
            Ini=temp;
            Fin=temp;
            return true;
        }
        Fin.Sig=temp;
        temp.Ant=Fin;
        Fin=temp;
        return true;
    }
    
    public boolean CrearArista(char O, char D){
        NodoGrafo Ori=null;
        NodoGrafo Dir=null;
        for(NodoGrafo temp=Ini; temp!=null; temp=temp.Sig){
            if(temp.valor==O){
                Ori=temp;
            }
            if(temp.valor==D){
                Dir=temp;
            }
        }
        if(Ori==null || Dir==null){
            return false;
        }
        if(Ori.Ari==null){
            NodoArista t1= new NodoArista(Dir);
            Ori.Ari=t1;
            return true;
        }
        if(Ori.Ari.Sig==null){
            NodoArista t1= new NodoArista(Dir);
            Ori.Ari.Sig=t1;
            t1.Ant=Ori.Ari;
            return true;
        }
        NodoArista t1= new NodoArista(Dir);
        for(NodoArista t2=Ori.Ari; t2!=null; t2=t2.Sig){
            if(t2.Sig==null){
                t2.Sig=t1;
                t1.Ant=t2;
                return true;
            }
        }
        return true;
    }
    
    private NodoGrafo BuscarNG(char v){
        for(NodoGrafo t1=Ini; t1!=null; t1=t1.Sig){
            if(t1.valor==v){
                return t1;
            }
        }
        return null;
    }
    
    public boolean EliminarNodoA(char O, char D){
        NodoGrafo Ori= BuscarNG(O);
        
        if(Ori==null){
            return false;
        }
        
        if(Ori.Ari==null){
            return false;
        }
        
        if(Ori.Ari.Sig==null){
            if(Ori.Ari.Dir.valor==D){
                Ori.Ari=null;
            }
        }
        
        for(NodoArista t1=Ori.Ari; t1!=null; t1=t1.Sig){
            if(t1.Dir.valor==D){
                if(t1.Ant==null){
                    Ori.Ari=t1.Sig;
                    t1.Sig.Ant=null;
                    return true;
                }
                if(t1.Sig==null){
                    t1.Ant.Sig=null;
                    t1.Ant=null;
                    return true;
                }
                t1.Sig.Ant=t1.Ant;
                t1.Ant.Sig=t1.Sig;
                return true;
            }
        }
        
        return false;
    }
    
    public boolean EliminarNG(char v){
        if(Ini==null && Fin==null){
            return false;
        }
        
        NodoGrafo Eli=BuscarNG(v);
        
        if(Eli==null){
            return false;
        }
        
        if(Eli.Ari!=null){
            return false;
        }
        
        for(NodoGrafo t1=Ini; t1!=null; t1=t1.Sig){
            if(EncontrarNA(t1, Eli)==true){
                return false;
            }
        }
        
        if(Ini==Fin && Ini==Eli){
            Ini=Fin=null;
            return true;
        }
        
        if(Ini==Eli){
            NodoGrafo t1=Ini.Sig;
            Ini.Sig=null;
            t1.Ant=null;
            Ini=t1;
            return true;
        }
        
        if(Fin==Eli){
            NodoGrafo t1=Fin.Ant;
            t1.Sig=null;
            Fin.Ant=null;
            Fin=t1;
            return true;
        }

        Eli.Ant.Sig=Eli.Sig;
        Eli.Sig.Ant=Eli.Ant;
        Eli.Sig=Eli.Ant=null;
        return true;
    }
    
    private boolean EncontrarNA(NodoGrafo ng, NodoGrafo ne){
        for(NodoArista t2=ng.Ari; t2!=null; t2=t2.Sig){
            if(t2.Dir==ne){
                return true;
            }
        }
        return false;
    }
    
    public boolean EncontrarNA(char O, char D){
        NodoGrafo Origen=BuscarNG(O);
        NodoGrafo Destino=BuscarNG(D);
        if(Origen!=null && Destino!=null){
            return EncontrarNA(BuscarNG(O), BuscarNG(D));
        }
        return false;
    }
    
    public String MostarGrafo(){
        String Cadena="";
        
        if(Ini==null){
            return "No hay nodos";
        }
        if(Ini==Fin){
            return "- "+Ini.valor+" -";
        }
        for(NodoGrafo temp=Ini; temp.Sig!=null; temp=temp.Sig){
            Cadena+=" - "+temp.valor;
        }
        return Cadena+" - "+Fin.valor+" -";
    }
    
    public String MostrarNodo(char v){
        NodoGrafo N=BuscarNG(v);
        String Cadena="";
        
        if(N.Ari==null){
            Cadena+="Nodo: "+N.valor+"\nNo tiene aristas";
            return Cadena;
        }
        
        if(N.Ari.Sig==null){
            Cadena+="Nodo: "+N.valor+"\n- "+N.Ari.Dir.valor+" -";
            return Cadena;
        }
        Cadena+="Nodo: "+N.valor+"\n- ";
        for(NodoArista temp=N.Ari; temp!=null; temp=temp.Sig){
            Cadena+=temp.Dir.valor+" - ";
        }
        return Cadena;
    }
}
