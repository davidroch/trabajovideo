package modelo;

import static controlador.Controlador.AccionMVC.CboxPelicula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Pelicula;
import javax.swing.JComboBox;
import modelo.Categoria;


public class Modelo extends Conexion {
    static Statement sentencia;
    static ResultSet resultado;
    private ResultSet rs;
    private Statement st;
     public boolean NuevoProducto(int codigo, int precio, int id_categoria, String formato4k, String nombre) {
//         ----> Validaciones que deben agregarse posteriormente. <--------
        if (valida_datos(codigo, precio, id_categoria, formato4k, nombre)) {

//            Se arma la consulta
            String q = " INSERT INTO pelicula (codigo, precio,id_categoria,formato4k,nombre) "
                    + "VALUES ( " + codigo + "," + precio + ", " + id_categoria
                    + ",'" + formato4k + "','" + nombre + "') ";
//            se ejecuta la consulta

            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;

            } catch (SQLException e) {
                System.err.println(e.getMessage());

            }
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese datos correctamente");
        }
        return false;
    } 
     
          public boolean NuevoCategoria(int idcategoria, String descripcion) {
//         ----> Validaciones que deben agregarse posteriormente. <--------
     
//            Se arma la consulta
            String q = " INSERT INTO categoria (id, descripcion) "
                    + "VALUES ( " + idcategoria + ",'" + descripcion+ "') ";
//            se ejecuta la consulta

            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;

            } catch (SQLException e) {
                System.err.println(e.getMessage());

            }
            return false;
          }
         
      
     
         //Metodo para validar datos
    private boolean valida_datos(int codigo, int precio, int id_categoria, String formato4k, String nombre) {
        if (codigo > 99999 && codigo < 10000) {
            return false;
        } else if (nombre.length() > 2 && precio > 1000 && (formato4k.equals("S")||formato4k.equals("O"))) {

            return true;
        } else {
            return false;
        }
    }
     
         //Modificar producto seleccionado
    public boolean modificaPelicula(int codigo, int precio, int id_categoria, String formato4k, String nombre) {
        String q = "UPDATE tiendapelicula.pelicula SET precio=" + precio + ", id_categoria=" + id_categoria + ", formato4k='" + formato4k
                + "', nombre='" +nombre+  "'" + " WHERE codigo='" + codigo + "' ";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    
    //eliminar pelicula
     public boolean eliminarPelicula(int codigo) {
        boolean res = false;
        String q = " DELETE FROM tiendapelicula.pelicula WHERE codigo=" + codigo + " ";
        try {
            PreparedStatement pstm = getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            res = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return res;
    }
     
      public ArrayList<Pelicula> buscarPorCodigo(String codigo) {
        ArrayList<Pelicula> listaPelicula = new ArrayList<Pelicula>();
        try {
            Connection conexion = getConexion();
            String query = "SELECT codigo,precio,id_categoria,formato4k,nombre From pelicula where codigo=?";
            PreparedStatement buscarPorCodigo = conexion.prepareStatement(query);
            buscarPorCodigo.setString(1, codigo);
            ResultSet rs = buscarPorCodigo.executeQuery();
            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setCodigo(rs.getInt("codigo"));
                pelicula.setPrecio(rs.getInt("precio"));
                pelicula.setId_categoria(rs.getInt("id_categoria"));
                pelicula.setFormato4k(rs.getString("formato4k"));
                pelicula.setNombre(rs.getString("nombre"));
                listaPelicula.add(pelicula);
            }
        } catch (SQLException s) {
            System.out.println("Error SQL al listar pelicula" + s.getMessage());
        } catch (Exception e) {
            System.out.println("Error al listar pelicula" + e.getMessage());
        }
        return listaPelicula;
    }

     
  public DefaultTableModel ListadoProducto() {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        
        String[] columNames = {"Codigo", "Precio", "Categoria", "Formato4k", "Nombre"};
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(*) as total FROM pelicula");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        Object[][] data = new String[registros][9];
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM pelicula");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("codigo");
                data[i][1] = res.getString("precio");
                data[i][2] = res.getString("id_categoria");
                data[i][3] = res.getString("formato4k");
                data[i][4] = res.getString("nombre");
                i++;
            }
            res.close();
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;
    } 


//  
//   public DefaultTableModel BuscarCodigo(String codigo) {
//        DefaultTableModel tablemodel = new DefaultTableModel();
//        int registros = 0;
//        
//        String[] columNames = {"Codigo", "Precio", "Categoria", "Formato4k", "Nombre"};
//        try {
//            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(*) as total FROM pelicula");
//            ResultSet res = pstm.executeQuery();
//            res.next();
//            registros = res.getInt("total");
//            res.close();
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//        Object[][] data = new String[registros][9];
//        try {
//            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM pelicula where codigo="+codigo);
//            ResultSet res = pstm.executeQuery();
//            int i = 0;
//            while (res.next()) {
//                data[i][0] = res.getString("codigo");
//                data[i][1] = res.getString("precio");
//                data[i][2] = res.getString("id_categoria");
//                data[i][3] = res.getString("formato4k");
//                data[i][4] = res.getString("nombre");
//                i++;
//            }
//            res.close();
//            tablemodel.setDataVector(data, columNames);
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//        return tablemodel;
//    }    
//  
     public  ArrayList<Categoria> llenar_combo(){
        ArrayList<Categoria> listacategoria = new ArrayList<Categoria>();
        String q = "SELECT descripcion FROM categoria";
        try {
            resultado = sentencia.executeQuery(q);
            System.out.println("Correcto");
        } catch (Exception e) {
            System.out.println("No Correcto");
        }
        try {
            while(resultado.next()){
                 Categoria categoria = new Categoria();
                categoria.setIdcategoria(rs.getInt("id"));
                categoria.setDescripcion(rs.getNString("descripcion"));
            }
        } catch (Exception e) {
        }
        return listacategoria;}
//        
//    }
//     
//     public void completarCbox() throws SQLException{
//         ArrayList<String> resultado;
//         resultado = llenar_combo();
//         
//         for(int i = 0;i<resultado.size();i++);
//         CboxPelicula.addItem(resultado.get(i));
//         
//     }
//     public void agregarCategoria()throws SQLException{
//         ArrayList<String> listacategoria = llenar_combo();
//         for (int i=0;i<listacategoria.size();i++){
//            CboxPelicula.addIem(listacategoria.get(i));
//
//
//     }}
////     
//     public ResultSet traerIDcategoria(){
//        try{
//            String query = "select * from categoria";
//            rs=st.executeQuery(query);
//        } catch (Exception e){
//     }
//        return null;
//}
//     private void cargarCombo(){
//         try{ 
//             Connection conexion = getConexion();
//             ResultSet rs = traerIDcategoria();
//             while(rs.next())
//             {
//                this.vistaagregar.CboxCategoria.addItem(rs.getString(descripcion));
//             }}
//}

    
//  public DefaultComboBoxModel ComboBox() {
//        DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
//        int registros = 0;
// 
//        try {
//            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM categoria");
//            ResultSet res = pstm.executeQuery();
//            res.next();
//            registros = res.getInt("id");
//            res.close();
//            
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//        modeloCombo.addElement("Seleccione una Categoria");
//        ArrayList<String> listacategoria = new ArrayList<String>();
//        try {
//            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM categoria");
//            ResultSet res = pstm.executeQuery();
//            int i = 0;
//            while (res.next()) {
//               listacategoria.add(resultado.getString("id"));
//                i++;
//            }
//            res.close();
//            
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//        return modeloCombo;
     
////////    public String traercategoria() throws SQLException {
////////       Conexion on = new Conexion();
////////       Connection conn = on.getConexion();
////////       ResultSet rs;
////////       String eql ="SELECT * FROM categoria";
////////       PreparedStatement sqls = conn.prepareStatement(eql);
////////       rs = sqls.executeQuery();
////////       while (rs.next(){
////////       
////////    
////////    }   }

// public void listar_departamento(JComboBox box){
//        
//        DefaultComboBoxModel value;
////        Conectar conec = new Conectar();
//        Statement st = null;
//        Connection con = null;
//        ResultSet rs = null;
//        try{
//            con = conec.getConexion();
//            st = con.createStatement();
//            rs = st.executeQuery("SELECT * FROM departamento");
//            value = new DefaultComboBoxModel();
//            box.setModel(value);
//            while(rs.next()){
//                value.addElement(new DepartamentoVO(rs.getInt(1),rs.getString(2)));
//            }
//        }catch(SQLException ex){
//            System.out.println(ex.getMessage());
//        }catch(Exception ex){
//            System.out.println(ex.getMessage());
//        }finally{
//            try{
//                st.close();
//                rs.close();
//                conec.desconectar();
//            }catch(Exception ex){
//            }
//        }
//        
//    }


     }




