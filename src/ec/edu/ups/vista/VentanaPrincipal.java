/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
/**
 *
 * @author patrick
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
   
    public ImageIcon imgs[];
    public JButton btns[];
    public String msgs[];
    public int ran;
    public int err;
    public String res[];

    public VentanaPrincipal() {
        initComponents();
        imgs = new ImageIcon[6];
        btns = new JButton[27];
        msgs = new String[20];

        //imagenes del joven ahorcado lol
        imgs[0] = new ImageIcon(getClass().getResource("/ec/edu/ups/vista/im1.jpg"));
        imgs[1] = new ImageIcon(getClass().getResource("/ec/edu/ups/vista/im2.jpg"));
        imgs[2] = new ImageIcon(getClass().getResource("/ec/edu/ups/vista/im3.jpg"));
        imgs[3] = new ImageIcon(getClass().getResource("/ec/edu/ups/vista/im4.jpg"));
        imgs[4] = new ImageIcon(getClass().getResource("/ec/edu/ups/vista/im5.jpg"));
        imgs[5] = new ImageIcon(getClass().getResource("/ec/edu/ups/vista/im6.jpg"));

        //botones para las letras
        btns[1] = jbtA;
        btns[2] = jbtB;
        btns[3] = jbtC;
        btns[4] = jbtD;
        btns[5] = jbtE;
        btns[6] = jbtF;
        btns[7] = jbtG;
        btns[8] = jbtH;
        btns[9] = jbtI;
        btns[10] = jbtJ;
        btns[11] = jbtK;
        btns[12] = jbtL;
        btns[13] = jbtM;
        btns[14] = jbtN;
        btns[15] = jbtO;
        btns[16] = jbtP;
        btns[17] = jbtQ;
        btns[18] = jbtR;
        btns[19] = jbtS;
        btns[20] = jbtT;
        btns[21] = jbtU;
        btns[22] = jbtV;
        btns[23] = jbtW;
        btns[24] = jbtX;
        btns[25] = jbtY;
        btns[26] = jbtZ;

        //palabras por advinar, para agregar una nueva palabra sera necesario declararla al inicio
        msgs[0] = "Liverpool".toUpperCase();
        msgs[1] = "Chelsea".toUpperCase();
        msgs[2] = "Tottenham".toUpperCase();
        msgs[3] = "Arsenal".toUpperCase();
        msgs[4] = "Everton".toUpperCase();
        msgs[5] = "Barselona".toUpperCase();
        msgs[6] = "Dormunt".toUpperCase();
        msgs[7] = "Villareal".toUpperCase();
        msgs[8] = "Malaga".toUpperCase();
        msgs[9] = "Sevilla".toUpperCase();
        msgs[10] = "Monterrey".toUpperCase();
        
        

        //se asigna un evento a cada letra para comprobar que exista en la palabra a adivinar
        for (int i = 1; i < 27; i++) {
            btns[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    checarLetra(e);
                }
            });
        }
        iniciar();
    }

    //funcion para comenzar los parametros del juego o iniciar una nueva partida
    public void iniciar() {
        //ERRORES EN 0
        err = 0;
        jButton1.setIcon(imgs[0]);
        jtxPalabras.setText("");
        //para activar las letras del tablero
        for (int i = 1; i < 27; i++) {
            btns[i].setEnabled(true);
        }
        //para generar una palabra aleatoriamente xD
        ran = (int) 0 + (int) (Math.random() * ((msgs.length - 1) + 1));
        //SEPARA EL MENSAJE POR PALABRAS
        String pal[] = msgs[ran].split(" ");
        res = new String[msgs[ran].length() + 1];
        int j = 0;
        // seran los guiones que van debajo de las letras como una separacion_
        for (String pal1 : pal) {
            for (int i = 0; i < pal1.length(); i++) {
                jtxPalabras.setText(jtxPalabras.getText() + "_ ");
                res[j++] = "_";
            }
            jtxPalabras.setText(jtxPalabras.getText() + "\n");
            res[j++] = " ";
        }
    }

    //al presionar una letra, esta se buscara si pertenece a la palabra, de lo contrario la marcara como error 
    public void checarLetra(ActionEvent e) {
        JButton bt = (JButton) e.getSource();
        char c[];
        //busca la letra en la palabra despues de haber sido presionada
        for (int i = 1; i < 27; i++) {
            if (bt == btns[i]) {
                //la tecla es inicializada
                c = Character.toChars(64 + i);
                //busca si la letra esta en la frase
                boolean esta = false;
                for (int j = 0; j < msgs[ran].length(); j++) {
                    if (c[0] == msgs[ran].charAt(j)) {
                        res[j] = c[0] + "";
                        esta = true;
                    }
                }
                //SI LA LETRA ESTA EN EL MENSAJE SE MUESTRA EN EL TEXTPANEL
                if (esta) {
                    jtxPalabras.setText("");
                    for (String re : res) {
                        if (" ".equals(re)) {
                            jtxPalabras.setText(jtxPalabras.getText() + "\n");
                        } else {
                            jtxPalabras.setText(jtxPalabras.getText() + re + " ");
                        }
                    }
                    //hace una comprobacion de las letras restantes y faltantes, en caso de que ya no haya letras sera ganador :D
                    boolean gano = true;
                    for (String re : res) {
                        if (re.equals("_")) {
                            gano = false;
                            break;
                        }
                    }
                    //al ser correcta se muestra un mensaje y se reinicia el juego
                    if (gano) {
                        JOptionPane.showMessageDialog(this, "Completado !!");
                        iniciar();
                        return;
                    }
                    //SI LA LETRA NO ESTA EN EL MENSAGE, SE INCREMENTA EL ERROR Y SE CAMBIA LA IMAGEN
                } else {
                    jButton1.setIcon(imgs[++err]);
                    //SI SE LLEGA A LOS 5 ERRORES ENTONCES SE PIERDE EL JUEGO Y SE MANDA EL MENSAGE DE:
                    if (err == 5) {
                        JOptionPane.showMessageDialog(this, "Intenta con otra Letra la respuesta es: \n" + msgs[ran]);
                        iniciar();
                        return;
                    }
                }
                //esta es la linea que desactiva las letras despues de ser usadas :3
                bt.setEnabled(false);
                break;
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxPalabras = new javax.swing.JTextPane();
        jbtA = new javax.swing.JButton();
        jbtB = new javax.swing.JButton();
        jbtC = new javax.swing.JButton();
        jbtD = new javax.swing.JButton();
        jbtE = new javax.swing.JButton();
        jbtF = new javax.swing.JButton();
        jbtG = new javax.swing.JButton();
        jbtH = new javax.swing.JButton();
        jbtI = new javax.swing.JButton();
        jbtJ = new javax.swing.JButton();
        jbtK = new javax.swing.JButton();
        jbtL = new javax.swing.JButton();
        jbtM = new javax.swing.JButton();
        jbtN = new javax.swing.JButton();
        jbtO = new javax.swing.JButton();
        jbtP = new javax.swing.JButton();
        jbtQ = new javax.swing.JButton();
        jbtR = new javax.swing.JButton();
        jbtS = new javax.swing.JButton();
        jbtT = new javax.swing.JButton();
        jbtU = new javax.swing.JButton();
        jbtV = new javax.swing.JButton();
        jbtW = new javax.swing.JButton();
        jbtX = new javax.swing.JButton();
        jbtY = new javax.swing.JButton();
        jbtZ = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jtxPalabras.setEditable(false);
        jtxPalabras.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(jtxPalabras);

        jbtA.setBackground(new java.awt.Color(255, 255, 255));
        jbtA.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtA.setText("A");

        jbtB.setBackground(new java.awt.Color(255, 255, 255));
        jbtB.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtB.setText("B");

        jbtC.setBackground(new java.awt.Color(255, 255, 255));
        jbtC.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtC.setText("C");

        jbtD.setBackground(new java.awt.Color(255, 255, 255));
        jbtD.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtD.setText("D");

        jbtE.setBackground(new java.awt.Color(255, 255, 255));
        jbtE.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtE.setText("E");

        jbtF.setBackground(new java.awt.Color(255, 255, 255));
        jbtF.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtF.setText("F");

        jbtG.setBackground(new java.awt.Color(255, 255, 255));
        jbtG.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtG.setText("G");

        jbtH.setBackground(new java.awt.Color(255, 255, 255));
        jbtH.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtH.setText("H");

        jbtI.setBackground(new java.awt.Color(255, 255, 255));
        jbtI.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtI.setText("I");

        jbtJ.setBackground(new java.awt.Color(255, 255, 255));
        jbtJ.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtJ.setText("J");

        jbtK.setBackground(new java.awt.Color(255, 255, 255));
        jbtK.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtK.setText("K");

        jbtL.setBackground(new java.awt.Color(255, 255, 255));
        jbtL.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtL.setText("L");

        jbtM.setBackground(new java.awt.Color(255, 255, 255));
        jbtM.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtM.setText("M");

        jbtN.setBackground(new java.awt.Color(255, 255, 255));
        jbtN.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtN.setText("N");

        jbtO.setBackground(new java.awt.Color(255, 255, 255));
        jbtO.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtO.setText("O");

        jbtP.setBackground(new java.awt.Color(255, 255, 255));
        jbtP.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtP.setText("P");

        jbtQ.setBackground(new java.awt.Color(255, 255, 255));
        jbtQ.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtQ.setText("Q");

        jbtR.setBackground(new java.awt.Color(255, 255, 255));
        jbtR.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtR.setText("R");

        jbtS.setBackground(new java.awt.Color(255, 255, 255));
        jbtS.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtS.setText("S");

        jbtT.setBackground(new java.awt.Color(255, 255, 255));
        jbtT.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtT.setText("T");

        jbtU.setBackground(new java.awt.Color(255, 255, 255));
        jbtU.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtU.setText("U");

        jbtV.setBackground(new java.awt.Color(255, 255, 255));
        jbtV.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtV.setText("V");

        jbtW.setBackground(new java.awt.Color(255, 255, 255));
        jbtW.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtW.setText("W");

        jbtX.setBackground(new java.awt.Color(255, 255, 255));
        jbtX.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtX.setText("X");

        jbtY.setBackground(new java.awt.Color(255, 255, 255));
        jbtY.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtY.setText("Y");

        jbtZ.setBackground(new java.awt.Color(255, 255, 255));
        jbtZ.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jbtZ.setText("Z");

        jButton28.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jButton28.setText("Reiniciar");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jLabel3.setText(" ");

        jLabel1.setText("Adivina equipos de Futbol:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(294, 294, 294)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jbtA)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtC))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jbtH)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtI)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtJ)))
                        .addGap(10, 10, 10)
                        .addComponent(jbtK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtN))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(445, 445, 445)
                        .addComponent(jbtD)
                        .addGap(22, 22, 22)
                        .addComponent(jbtE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel3))
                            .addComponent(jbtG)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(97, 97, 97)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(226, 226, 226)
                                .addComponent(jbtO)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jbtV)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtW)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jbtX)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtY)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtZ))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jbtP)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtQ)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jbtR)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtS)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtT)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtU)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(0, 136, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtA)
                    .addComponent(jbtB)
                    .addComponent(jbtC)
                    .addComponent(jbtD)
                    .addComponent(jbtE)
                    .addComponent(jbtF)
                    .addComponent(jbtG))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtH)
                    .addComponent(jbtI)
                    .addComponent(jbtJ)
                    .addComponent(jbtK)
                    .addComponent(jbtL)
                    .addComponent(jbtM)
                    .addComponent(jbtN))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtO)
                    .addComponent(jbtP)
                    .addComponent(jbtQ)
                    .addComponent(jbtR)
                    .addComponent(jbtS)
                    .addComponent(jbtT)
                    .addComponent(jbtU))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtV)
                    .addComponent(jbtW)
                    .addComponent(jbtX)
                    .addComponent(jbtY)
                    .addComponent(jbtZ))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        iniciar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28ActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton28;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtA;
    private javax.swing.JButton jbtB;
    private javax.swing.JButton jbtC;
    private javax.swing.JButton jbtD;
    private javax.swing.JButton jbtE;
    private javax.swing.JButton jbtF;
    private javax.swing.JButton jbtG;
    private javax.swing.JButton jbtH;
    private javax.swing.JButton jbtI;
    private javax.swing.JButton jbtJ;
    private javax.swing.JButton jbtK;
    private javax.swing.JButton jbtL;
    private javax.swing.JButton jbtM;
    private javax.swing.JButton jbtN;
    private javax.swing.JButton jbtO;
    private javax.swing.JButton jbtP;
    private javax.swing.JButton jbtQ;
    private javax.swing.JButton jbtR;
    private javax.swing.JButton jbtS;
    private javax.swing.JButton jbtT;
    private javax.swing.JButton jbtU;
    private javax.swing.JButton jbtV;
    private javax.swing.JButton jbtW;
    private javax.swing.JButton jbtX;
    private javax.swing.JButton jbtY;
    private javax.swing.JButton jbtZ;
    private javax.swing.JTextPane jtxPalabras;
    // End of variables declaration//GEN-END:variables
}
