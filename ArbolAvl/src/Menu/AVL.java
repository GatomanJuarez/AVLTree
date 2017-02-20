/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import javax.swing.JOptionPane;

/**
 *
 * @author jeobal
 */
public class AVL extends javax.swing.JFrame {

    /**
     * Creates new form AVL
     */
    public AVL() {
        initComponents();
    }

    public class NodoArbolAVL {

        //Creamos nuestra variables iniciales.
        int dato, facEq;
        NodoArbolAVL hijoIzq, hijoDer;

        //Constructor de la clase.
        public NodoArbolAVL(int dat) {
            this.dato = dat;
            //No sabemos cual es el punto de equilibrio.
            this.facEq = 0;
            this.hijoIzq = null;
            this.hijoDer = null;
        }

    }

    public class ArbolAVL {

        //Creamos la raiz tipo nodo.
        public NodoArbolAVL raiz;

        //Constructor.
        public ArbolAVL() {
            raiz = null;
        }

        //Obtener la raiz.
        public NodoArbolAVL obtenerRaiz() {
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
        //Clase para imprimir.
        public void imprimirpreorden() {
            //Le enviamos la raiz.
            txtArea2.setText("Impresion de PreOrden\n");
            ayudantePreorden(raiz);
        }

        public void ayudantePreorden(NodoArbolAVL dat) {
            //raiz, izquierda, derecha
            if (dat == null) {
                return;
            }
            //Imprime el valor.
            txtArea2.append("\t" + dat.dato + "\n");
            System.out.printf("%d ", dat.dato);
            ayudantePreorden(dat.hijoIzq);
            ayudantePreorden(dat.hijoDer);
        }

        public void impririnorden(NodoArbolAVL dat) {
            //Si el nodo tiene algo.
            if (dat != null) {
                //Recursividad para el valor izquierdo, lo recorre hasta el mas izquierdo.
                impririnorden(dat.hijoIzq);
                System.out.println(" " + dat.dato);
                //Recursividad para el valor derecho, lo recorre hasta el mas derecho.
                impririnorden(dat.hijoDer);
            }
        }

        ////////////////////////////////////////
        //Clase para imprimir.
        public void imprimirpost() {
            //Le enviamos la raiz.
            txtArea2.setText("Impresion de PosOrden\n");
            ayudantePost(raiz);
        }

        public void ayudantePost(NodoArbolAVL dat) {
            //izquierda, derecha, raiz
            if (dat == null) {
                return;
            }
            ayudantePost(dat.hijoIzq);
            ayudantePost(dat.hijoDer);
            System.out.printf("%d ", dat.dato);
            txtArea2.append("\t" + dat.dato + "\t\n");
        }

        public void impririnorden2(NodoArbolAVL dat) {
            if (dat != null) { //Hasta que se nos acaben los datos.
                impririnorden2(dat.hijoIzq);
                impririnorden2(dat.hijoDer);
                System.out.println(" " + dat.dato);//La raiz.
            }
        }

        ////////////////////////////////////////
        //Clase para imprimir.
        public void imprimirin() {
            //Le enviamos la raiz.
            txtArea2.setText("Impresion de InOrden\n");
            ayudanteIn(raiz);
        }

        public void ayudanteIn(NodoArbolAVL dat) {
            //izquierda, raiz, derecho
            if (dat == null) {
                return;
            }
            ayudanteIn(dat.hijoIzq);
            System.out.printf("%d ", dat.dato);
            ayudanteIn(dat.hijoDer);
            txtArea2.append("\t" + dat.dato + "\t\n");
        }

        public void impririnorden3(NodoArbolAVL dat) {
            if (dat != null) { //Hasta que se nos acaben los datos.
                impririnorden3(dat.hijoIzq);
                System.out.println(" " + dat.dato);//La raiz.
                impririnorden3(dat.hijoDer);
            }
        }

    }

    ArbolAVL arbol = new ArbolAVL();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        txtnum = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtArea2 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ingresa el maximo de numeros:");

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        jButton5.setText("Salir.");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jButton1.setText("Go");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton4.setText("InOrden");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        txtArea2.setColumns(20);
        txtArea2.setRows(5);
        jScrollPane2.setViewportView(txtArea2);

        jButton3.setText("PosOrden");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton2.setText("PreOrden");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtnum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(51, 51, 51)))
                        .addGap(104, 104, 104)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(26, 26, 26)
                                .addComponent(jButton4)
                                .addGap(30, 30, 30))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(26, 26, 26)
                                .addComponent(jButton5)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        System.exit(1);
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        int numeroMa = Integer.parseInt(txtnum.getText());
        txtArea.setText("Los numeros que ingresaste son:\n");
        int auxiliar = 0;
        for (byte o = 1; o <= numeroMa; o++) {
            auxiliar = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el dato: " + o));
            txtArea.append(auxiliar + "\n");
            arbol.insertar(auxiliar);
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        System.out.println("\nValores Capturados en InOrden:");
        arbol.imprimirin();;
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        System.out.println("\nValores Capturados en PosOrden:");
        arbol.imprimirpost();
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        System.out.println("Valores Capturados en PreOrden:");
        arbol.imprimirpreorden();
    }//GEN-LAST:event_jButton2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AVL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AVL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AVL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AVL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AVL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextArea txtArea2;
    private javax.swing.JTextField txtnum;
    // End of variables declaration//GEN-END:variables
}
