package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.Categoria;
import modelo.Modelo;
import modelo.Pelicula;
import vista.Agregar;
import vista.Eliminar;
import vista.Listar;
import vista.MenuInicial;
import vista.Consultas;

public class Controlador implements ActionListener, MouseListener {

    /**
     * instancia a nuestra interfaz de usuario
     */
    MenuInicial vista;
    Agregar vistaAgregar = new Agregar();
    Listar vistalist = new Listar();
    Eliminar vistaelim = new Eliminar();
    Consultas vistaConsulta = new Consultas();
    
    Pelicula pelicula = new Pelicula();
    /**
     * instancia a nuestro modelo
     */
    Modelo modelo = new Modelo();

    /**
     * Se declaran en un ENUM las acciones que se realizan desde la interfaz de
     * usuario VISTA y posterior ejecución desde el controlador
     */
    public enum AccionMVC {
        MenuAgregar,
        MenuEliminar,
        MenuListar,
        BtnAgregaraAgregar,
        BtnVolverAgregar,
        BtnEliminareliminar,
        BtnBuscarListar,
        BtnListarListar,
        BtnModificar,
        CboxPelicula,
        BtnEliminarListar,
        BtnVoleverListar,
        MenuSalir,
        Btoagregarcategoria,
        BtnConsulta1,
        BtnConsulta2,
        BtnConsulta3,
        BtnConsulta4,
        BtnConsulta5,
        BtnConsulta6,
        BtnConsultaVolver,
        MenuConsulta
       
        

    }

    /**
     * Constrcutor de clase
     *
     * @param vista Instancia de clase interfaz
     */
    public Controlador(MenuInicial vista) {
        this.vista = vista;
    }

    /**
     * Inicia el skin y las diferentes variables que se utilizan
     */
    public void iniciar() {
        // Skin tipo WINDOWS
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            vista.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }

        //declara una acción y añade un escucha al evento producido por el componente
        this.vistaAgregar.BtnAgregaraAgregar.setActionCommand("BtnAgregaraAgregar");
        this.vistaAgregar.BtnAgregaraAgregar.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vistaAgregar.BtnVolverAgregar.setActionCommand("BtnVolverAgregar");
        this.vistaAgregar.BtnVolverAgregar.addActionListener(this);
        
        this.vistaAgregar.Btoagregarcategoria.setActionCommand("Btoagregarcategoria");
        this.vistaAgregar.Btoagregarcategoria.addActionListener(this);

        // ----------------------------------------------------------
        
        //declara una acción y añade un escucha al evento producido por el componente menu
        this.vista.MenuAgregar.setActionCommand("MenuAgregar");
        this.vista.MenuAgregar.addActionListener(this);

        this.vista.MenuEliminar.setActionCommand("MenuEliminar");
        this.vista.MenuEliminar.addActionListener(this);
        
        this.vista.MenuListar.setActionCommand("MenuListar");
        this.vista.MenuListar.addActionListener(this);
        
        this.vista.MenuConsulta.setActionCommand("MenuConsulta");
        this.vista.MenuConsulta.addActionListener(this);

        this.vista.MenuSalir.setActionCommand("MenuSalir");
        this.vista.MenuSalir.addActionListener(this);

        //---------------------------Botoneslistar--------------
        this.vistalist.BtnListarListar.setActionCommand("BtnListarListar");
        this.vistalist.BtnListarListar.addActionListener(this);
//
        this.vistalist.BtnVoleverListar.setActionCommand("BtnVoleverListar");
        this.vistalist.BtnVoleverListar.addActionListener(this);

        this.vistalist.BtnEliminarListar.setActionCommand("BtnEliminarListar");
        this.vistalist.BtnEliminarListar.addActionListener(this);

//        añade e inicia el jtable con un DefaultTableModel vacio
        this.vistalist.TablaListarPelicula.addMouseListener(this);
        this.vistalist.TablaListarPelicula.setModel(new DefaultTableModel());

        this.vistalist.BtnModificar.setActionCommand("BtnModificar");
        this.vistalist.BtnModificar.addActionListener(this);
        
        this.vistalist.BtnBuscarListar.setActionCommand("BtnBuscarListar");
        this.vistalist.BtnBuscarListar.addActionListener(this);

//        ---------------------------BotonesEleminar------------------
        this.vistaelim.BtnEliminareliminar.setActionCommand("BtnEliminareliminar");
        this.vistaelim.BtnEliminareliminar.addActionListener(this);
//        this.vistalist.TxtCodigo.setEnabled(false);

this.vistaAgregar.CboxPelicula.setActionCommand("CboxPelicula");
this.vistaAgregar.CboxPelicula.addActionListener(this);
this.vistaAgregar.CboxPelicula.addItem("Seleccione categoría") ;
//this.vistaAgregar.CboxPelicula.addItem();
//this.vistalist.CboxCategoriaListar.removCategoriaeAllItems();
////        ArrayList<String> listacategoria = new ArrayList<String>();
////        listacategoria = modelo.llenar_combo();
////        for(int i = 0; i<listacategora.size();i++){
////this.vistaAgregar.CboxPelicula.addItem(listacategoria.toString());
////this.vistaAgregar.CboxPelicula.setModel( new DefaultComboBoxModel());
//
//this.vistaAgregar.CboxPelicula.setModel(this.modelo.ComboBox());

// Botones de la vista consulta  
this.vistaConsulta.BtnConsulta1.setActionCommand("BtnConsulta1");
this.vistaConsulta.BtnConsulta1.addActionListener(this);    

this.vistaConsulta.BtnConsulta2.setActionCommand("BtnConsulta2");
this.vistaConsulta.BtnConsulta2.addActionListener(this);  
    
this.vistaConsulta.BtnConsulta3.setActionCommand("BtnConsulta3");
this.vistaConsulta.BtnConsulta3.addActionListener(this);
this.vistaConsulta.BtnConsulta4.setActionCommand("BtnConsulta4");
this.vistaConsulta.BtnConsulta4.addActionListener(this);
this.vistaConsulta.BtnConsulta5.setActionCommand("BtnConsulta5");
this.vistaConsulta.BtnConsulta5.addActionListener(this);
this.vistaConsulta.BtnConsulta6.setActionCommand("BtnConsulta6");
this.vistaConsulta.BtnConsulta6.addActionListener(this);

this.vistaConsulta.BtnConsultaVolver.setActionCommand("BtnConsultaVolver");
this.vistaConsulta.BtnConsultaVolver.addActionListener(this);
    }
    
    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        

        if (e.getButton() == 1)//boton izquierdo
        {
            
            //Muestro datos de producto a modificar
            int fila = this.vistalist.TablaListarPelicula.rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.vistalist.TxtCodigoListar.setText(String.valueOf(this.vistalist.TablaListarPelicula.getValueAt(fila, 0)));
                this.vistalist.TxtPrecioListar.setText(String.valueOf(this.vistalist.TablaListarPelicula.getValueAt(fila, 1)));
                this.vistalist.CboxCategoriaListar.setSelectedItem(String.valueOf(this.vistalist.TablaListarPelicula.getValueAt(fila, 2)));
                this.vistalist.TxtFormato4kListar.setText(String.valueOf(this.vistalist.TablaListarPelicula.getValueAt(fila, 3)));
                this.vistalist.TxtnombreListar.setText(String.valueOf(this.vistalist.TablaListarPelicula.getValueAt(fila, 4)));
             
            }
        }
    }
    
    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) { }

    //Control de eventos de los controles que tienen definido un "ActionCommand"
    public void actionPerformed(ActionEvent e) {
//        String cod1;
        switch (AccionMVC.valueOf(e.getActionCommand())) {

            case MenuAgregar:
                this.vistaAgregar.setVisible(true);
                this.vista.setVisible(false);
                break;
                
                 case MenuConsulta:
                this.vistaConsulta.setVisible(true);
                this.vista.setVisible(false);
                break;
                
            case MenuSalir:
                this.vista.dispose();
                System.exit(0);
                break;
                
          
                
            case MenuListar:
                this.vistalist.setVisible(true);
                this.vistalist.TablaListarPelicula.setModel(this.modelo.ListadoProducto());
                break;

            case BtnListarListar:
                //obtiene del modelo los registros en un DefaultTableModel y lo asigna en la vista
                this.vistalist.TablaListarPelicula.setModel(this.modelo.ListadoProducto());
                break;

            //activa botob volver de la interfaz agregar
            case BtnVolverAgregar:
                this.vistaAgregar.setVisible(false);
                this.vista.setVisible(true);
                break;
//
            case BtnModificar:

                if (this.modelo.modificaPelicula(
                        Integer.parseInt(this.vistalist.TxtCodigoListar.getText()),
                        Integer.parseInt(this.vistalist.TxtPrecioListar.getText()),
                        Integer.parseInt(this.vistalist.CboxCategoriaListar.getSelectedItem().toString()),
                        this.vistalist.TxtFormato4kListar.getText(),
                        this.vistalist.TxtnombreListar.getText())) {
                    this.vistalist.TablaListarPelicula.setModel(this.modelo.ListadoProducto()); //actualiza JTable
                    JOptionPane.showMessageDialog(null, "Producto actualizado");
                    //Limpiamos textField
                    this.vistalist.TxtCodigoListar.setText("");
                    this.vistalist.TxtPrecioListar.setText("");
                    this.vistalist.CboxCategoriaListar.setSelectedIndex(0);
                    this.vistalist.TxtFormato4kListar.setText("");
                    this.vistalist.TxtnombreListar.setText("");

                }
                break;

            case BtnAgregaraAgregar:
                //añade un nuevo registro

                if (this.modelo.NuevoProducto(
                        Integer.parseInt(this.vistaAgregar.TxtCodigoPelicula.getText()),
                        Integer.parseInt(this.vistaAgregar.TxtPrecioPelicula.getText()),
                        this.vistaAgregar.CboxPelicula.getSelectedItem().toString(),
                        this.vistaAgregar.TxtFormato4kPelicula.getText(),
                        this.vistaAgregar.TxtNombrePelicula.getText())) {
                    JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
                }

                this.vistaAgregar.TxtCodigoPelicula.setText("");
                this.vistaAgregar.TxtPrecioPelicula.setText("");
                this.vistaAgregar.TxtFormato4kPelicula.setText("");
                this.vistaAgregar.TxtNombrePelicula.setText("");
                this.vistaAgregar.CboxPelicula.setSelectedIndex(0);
                break;
                
            case Btoagregarcategoria:
                 if (this.modelo.NuevoCategoria(
                        Integer.parseInt(this.vistaAgregar.TxtidCAT.getText()),
                        this.vistaAgregar.TxtcategoriaCAT.getText()
                        )) {
                    JOptionPane.showMessageDialog(null, "Categoria agregado correctamente");
                }

                this.vistaAgregar.TxtidCAT.setText("");
                this.vistaAgregar.TxtcategoriaCAT.setText("");

                break;

                

            case BtnEliminareliminar:
                int codigo1;
                codigo1 = Integer.parseInt(this.vistaelim.Txtcodigoeliminar.getText());
                this.modelo.eliminarPelicula(codigo1);
                break;

            case BtnEliminarListar:
                int codigo2;
                codigo2 = Integer.parseInt(this.vistalist.TxtCodigoListar.getText());
                this.modelo.eliminarPelicula(codigo2);
                 {
                    this.vistalist.TablaListarPelicula.setModel(this.modelo.ListadoProducto()); //actualiza JTable
                    JOptionPane.showMessageDialog(null, "Producto actualizado");
                    //Limpiamos textField
                    this.vistalist.TxtCodigoListar.setText("");
                    this.vistalist.TxtPrecioListar.setText("");
                    this.vistalist.TxtFormato4kListar.setText("");
                    this.vistalist.TxtnombreListar.setText("");
                    this.vistaAgregar.CboxPelicula.setSelectedIndex(0);

                }

                break;

            case BtnVoleverListar:
                this.vistalist.setVisible(false);
                this.vista.setVisible(true);
                break;

            case BtnBuscarListar:
                try {
                    String codigo = this.vistalist.TxtCodigoListar.getText();

                    DefaultTableModel modeloT = new DefaultTableModel();
                    vistalist.TablaListarPelicula.setModel(modeloT);

                    modeloT.addColumn("Codigo");
                    modeloT.addColumn("Precio");
                    modeloT.addColumn("Categoria");
                    modeloT.addColumn("Formato4K");
                    modeloT.addColumn("Nombre");

                    Object[] columna = new Object[5];

                    int numRegistros = modelo.buscarPorCodigo(codigo).size();

                    for (int i = 0; i < numRegistros; i++) {
                        columna[0] = modelo.buscarPorCodigo(codigo).get(i).getCodigo();
                        columna[1] = modelo.buscarPorCodigo(codigo).get(i).getPrecio();
                        columna[2] = modelo.buscarPorCodigo(codigo).get(i).getId_categoria();
                        columna[3] = modelo.buscarPorCodigo(codigo).get(i).getFormato4k();
                        columna[4] = modelo.buscarPorCodigo(codigo).get(i).getNombre();
                        modeloT.addRow(columna);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Pelicula no encontrada");
                }
                break;
            case BtnConsulta1:
                 if (this.modelo.NuevoProducto(10065,5000,"Drama","S","Titanic")
                         ) {
                    JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
                }else {JOptionPane.showMessageDialog(null, "Consulta no pudo realizarse");
                
                 }
                break;
                 
            case BtnConsulta2:
                 if (this.modelo.NuevoProducto(10055,5000,"Comedia","S","Pinguino")
                         ) {
                    JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
                }else {JOptionPane.showMessageDialog(null, "Consulta no pudo realizarse");    
  
                break;

        }
                 
            case BtnConsulta4:
                this.vistalist.setVisible(true);
                this.vistaConsulta.setVisible(false);
                                try {
                    String Categoria = "Romance";

                    DefaultTableModel modeloT = new DefaultTableModel();
                    vistalist.TablaListarPelicula.setModel(modeloT);

                    modeloT.addColumn("Codigo");
                    modeloT.addColumn("Precio");
                    modeloT.addColumn("Categoria");
                    modeloT.addColumn("Formato4K");
                    modeloT.addColumn("Nombre");

                    Object[] columna = new Object[5];

                    int numRegistros = modelo.buscarPorCategoria(Categoria).size();

                    for (int i = 0; i < numRegistros; i++) {
                        columna[0] = modelo.buscarPorCategoria(Categoria).get(i).getCodigo();
                        columna[1] = modelo.buscarPorCategoria(Categoria).get(i).getPrecio();
                        columna[2] = modelo.buscarPorCategoria(Categoria).get(i).getId_categoria();
                        columna[3] = modelo.buscarPorCategoria(Categoria).get(i).getFormato4k();
                        columna[4] = modelo.buscarPorCategoria(Categoria).get(i).getNombre();
                        modeloT.addRow(columna);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Pelicula no encontrada");
                }
                break;
                
            case BtnConsulta5:
                int precio1 = 2000;
                this.modelo.eliminarPrecio(precio1);
                  {
                    this.vistalist.TablaListarPelicula.setModel(this.modelo.ListadoProducto()); //actualiza JTable
                    JOptionPane.showMessageDialog(null, "Producto eliminar");}
                break;
                                  
////            String codigo3 = vistalist.TxtCodigoListar.getText();            
////    if ("".equals(codigo3)){
////        JOptionPane.showMessageDialog(null, "Debe Ingresar un codigo");}
//                        this.modelo.buscarPorCodigo(codigo3);
//                this.vistalist.TxtCodigoListar.setText(this.modelo.listapelicula.getNombre());
//                this.vistalist.TxtPrecioListar.setText(String.valueOf(this.vistalist.TablaListarPelicula.getValueAt(fila, 1)));
//                this.vistalist.CboxCategoriaListar.setSelectedItem(String.valueOf(this.vistalist.TablaListarPelicula.getValueAt(fila, 2)));
//                this.vistalist.TxtFormato4kListar.setText(String.valueOf(this.vistalist.TablaListarPelicula.getValueAt(fila, 3)));
//                this.vistalist.TxtnombreListar.setText(String.valueOf(this.vistalist.TablaListarPelicula.getValueAt(fila, 4)));

//                        Integer.parseInt(this.vistalist.TxtCodigoListar.getText()),
//                        Integer.parseInt(this.vistalist.TxtPrecioListar.getText()),
//                        Integer.parseInt(this.vistalist.CboxCategoriaListar.getSelectedItem().toString()),
//                        this.vistalist.TxtFormato4kListar.getText(),
//                        this.vistalist.TxtnombreListar.getText()))l
                        
//    }
//    try {
//        int id = Integer.parseInt(idStr);
//        for (Pelicula pelicula : ){
//            if (trabajador.getId()==id){
//                jtNombre.setText(trabajador.getNombre());
//                jtDireccion.setText(trabajador.getDireccion());
//                jtTelefono.setText(trabajador.getTelefono());
//                jtSueldo.setText(String.valueOf(trabajador.getSueldo()));
//                
//
//                ComboBoxModel cbm = this.cbRegion.getModel();
//                for (int i = 0; i<cbm.getSize();i++){
//                if (cbm.getElementAt(i).toString().equals(trabajador.getRegion())){
//                   this.cbRegion.setSelectedIndex(i);
//                   return;
//                }
//            }
//        }
//     }
//       
//        this.jtID.setText(idStr);
//        mostrarMensaje("ID no encontrado");
//    } catch(Exception e){
//        
//    }
//
        }
        
   }
}


