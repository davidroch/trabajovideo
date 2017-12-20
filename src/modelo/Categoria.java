package modelo;

public class Categoria {
    //Atributos
    int idcategoria;
    String descripcion;

    //Constructor 
    public Categoria() {
    }

    public Categoria(int idcategoria, String descripcion) {
        this.idcategoria = idcategoria;
        this.descripcion = descripcion;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Categoria{" + "descripcion=" + descripcion + '}';
    }

   
    
    
    
}
