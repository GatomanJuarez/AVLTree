/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Menu;

//import javax.swing.JOptionPane;

/**
 *
 * @author By Gatoman
 *//*
public class ArbolAVL {

    //Creamos la raiz tipo nodo.
    public NodoArbolAVL raiz;

    //Constructor.
    public ArbolAVL() {
        raiz = null;
    }
    
    //Obtener la raiz.
    public NodoArbolAVL obtenerRaiz(){
        return raiz;
    }

    //Buscar nodo.
    public NodoArbolAVL buscar(int dat, NodoArbolAVL ra) {
        //Si la raiz es nula.
        if (raiz == null) {
            return null;
        } //Si el dato es igual al que buscamos.
        else if (ra.dato == dat) {
            return ra;
        } //Si el dato esta a la derecha del arbol.
        else if (ra.dato < dat) {
            //Recursividad.
            return buscar(dat, ra.hijoDer);
        } //Si el dato esta a la izquierda del arbol.
        else //Recursividad.
        {
            return buscar(dat, ra.hijoIzq);
        }
    }

    //Obtener el factor de equilibrio.
    public int obtenerFe(NodoArbolAVL arbol) {
        //
        if (arbol == null) {
            return -1;
        } //Retornamos el factor de equilibrio.
        else {
            return arbol.facEq;
        }
    }

    //Rotaciones.
    //Rotacion simple a la izquierda.
    public NodoArbolAVL rotacionIzquierda(NodoArbolAVL arbol) {
        //Creamos un puntero, una variable auxiliar.
        NodoArbolAVL auxiliar = arbol.hijoIzq;
        //Reacomodamos puntero.
        arbol.hijoIzq = auxiliar.hijoDer;
        //Guardamos las variables.
        auxiliar.hijoDer = arbol;
        //Obtenemos el maximo de un nodo, le sumamos uno porque es la altura real.
        //Obtenemos el factor de equilibrio de los dos lados.
        arbol.facEq = Math.max(obtenerFe(arbol.hijoIzq), obtenerFe(arbol.hijoDer)) + 1;
        //Hacemos lo mismo pero con el auxiliar
        auxiliar.facEq = Math.max(obtenerFe(auxiliar.hijoIzq), obtenerFe(auxiliar.hijoDer)) + 1;
        return auxiliar;
    }

    //Rotacion simple a la derecha.
    public NodoArbolAVL rotacionDerecha(NodoArbolAVL arbol) {
        //Creamos un puntero, una variable auxiliar.
        NodoArbolAVL auxiliar = arbol.hijoDer;
        //Reacomodamos puntero.
        arbol.hijoDer = auxiliar.hijoIzq;
        //Guardamos las variables.
        auxiliar.hijoIzq = arbol;
        //Obtenemos el maximo de un nodo, le sumamos uno porque es la altura real.
        //Obtenemos el factor de equilibrio de los dos lados.
        arbol.facEq = Math.max(obtenerFe(arbol.hijoIzq), obtenerFe(arbol.hijoDer)) + 1;
        //Hacemos lo mismo pero con el auxiliar
        auxiliar.facEq = Math.max(obtenerFe(auxiliar.hijoIzq), obtenerFe(auxiliar.hijoDer)) + 1;
        return auxiliar;
    }

    //Rotacion doble a la derecha.
    public NodoArbolAVL rotacionDobleIzquierda(NodoArbolAVL arbol) {
        //Creamos un puntero, una variable auxiliar.
        NodoArbolAVL auxiliar;
        //Llamamos al metodo.
        arbol.hijoIzq = rotacionDerecha(arbol.hijoIzq);
        //Llamamos al metodo.
        auxiliar = rotacionIzquierda(arbol);
        return auxiliar;
    }

    //Rotacion doble a la izquierda.
    public NodoArbolAVL rotacionDobleDerecha(NodoArbolAVL arbol) {
        //Creamos un puntero, una variable auxiliar.
        NodoArbolAVL auxiliar;
        //Llamamos al metodo.
        arbol.hijoDer = rotacionIzquierda(arbol.hijoDer);
        //Llamamos al metodo.
        auxiliar = rotacionDerecha(arbol);
        return auxiliar;
    }

    //Metodo para insertar arboles
    public NodoArbolAVL insertarAVL(NodoArbolAVL nuevo, NodoArbolAVL subarbol) {
        //Creamos un nuevo arbol.
        NodoArbolAVL nuevoPa = subarbol;
        //Condicion para saber si es un arbol o pieza de arbol.
        if (nuevo.dato < subarbol.dato) {
            //Si el lado izquierdo en nulo o vacio.
            if (subarbol.hijoIzq == null) {
                //Sera el nuevo arbol que nos pasaron.
                subarbol.hijoIzq = nuevo;
            } //El arbol que se creo anteriormente, se pasara a crear un nuevo arbol.
            else {
                subarbol.hijoIzq = insertarAVL(nuevo, subarbol.hijoIzq);
                //Se hace una resta para saber si esta desbalanceado.
                if ((obtenerFe(subarbol.hijoIzq) - (obtenerFe(subarbol.hijoDer))) == 2) {
                    if (nuevo.dato < subarbol.hijoIzq.dato) {
                        //Se creara un nuevo arbol.
                        nuevoPa = rotacionIzquierda(subarbol);
                    } else {
                        //Se creara un nuevo arbol.
                        nuevoPa = rotacionDobleIzquierda(subarbol);
                    }
                }
            }
        } //Si es mayor.
        else if (nuevo.dato > subarbol.dato) {
            //Si el lado derecho en nulo o vacio.
            if (subarbol.hijoDer == null) {
                //Sera el nuevo arbol que nos pasaron.
                subarbol.hijoDer = nuevo;
            } //El arbol que se creo anteriormente, se pasara a crear un nuevo arbol.
            else {
                subarbol.hijoDer = insertarAVL(nuevo, subarbol.hijoDer);
                //Se hace una resta para saber si esta desbalanceado.
                if ((obtenerFe(subarbol.hijoDer) - (obtenerFe(subarbol.hijoIzq))) == 2) {
                    if (nuevo.dato > subarbol.hijoDer.dato) {
                        //Se creara un nuevo arbol.
                        nuevoPa = rotacionDerecha(subarbol);
                    } else {
                        //Se creara un nuevo arbol.
                        nuevoPa = rotacionDobleDerecha(subarbol);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nodo Duplicado");
        }

        //Actualizando el factor de equilibrio.
        //Si el lado izquierdo esta vacio y el derecho no.
        if ((subarbol.hijoIzq == null) && (subarbol.hijoDer != null)) {
            //Actualizamos el factor de equilibrio.
            subarbol.facEq = subarbol.hijoDer.facEq + 1;
        } //Si el lado derecho esta vacio y el izquierdo no.
        else if ((subarbol.hijoDer == null) && (subarbol.hijoIzq != null)) {
            //Actualizamos el factor de equilibrio.
            subarbol.facEq = subarbol.hijoIzq.facEq + 1;
        } else {
            subarbol.facEq = Math.max(obtenerFe(subarbol.hijoIzq), obtenerFe(subarbol.hijoDer)) + 1;
        }
        //Se hace el Balance.
        return nuevoPa;
    }

    //Metodo para insertar datos
    public void insertar(int d) {
        NodoArbolAVL nuevo = new NodoArbolAVL(d);
        if (raiz == null) {
            raiz = nuevo;
        } //Si tiene algo le decimos que lo inserte como la raiz.
        else {
            raiz = insertarAVL(nuevo, raiz);
        }
    }

    //Recorridos.
    public void inOrden(NodoArbolAVL arbol) {
        if (arbol != null) {
            inOrden(arbol.hijoIzq);
            System.out.println(arbol.dato + "");
            inOrden(arbol.hijoDer);
        }
    }

    public void preOrden(NodoArbolAVL arbol) {
        if (arbol != null) {
            System.out.println(arbol.dato + "");
            preOrden(arbol.hijoIzq);
            preOrden(arbol.hijoDer);
        }
    }

    public void postOrden(NodoArbolAVL arbol) {
        if (arbol != null) {
            postOrden(arbol.hijoIzq);
            postOrden(arbol.hijoDer);
            System.out.println(arbol.dato + "");
        }
    }
    
}
*/