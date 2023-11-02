public class Platos {
    String nombre;
    String descripcion;
    Tipoplato tipo;
    int costo;
    int tiempoprep;


  
    public Platos(String nombre, String descripcion, Tipoplato tipo, int costo, int tiempoprep){

  
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.tipo = tipo;
        this.costo = costo;
        this.tiempoprep = tiempoprep;

    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Tipoplato getTipo() {
        return tipo;
    }
    public void setTipo(Tipoplato tipo) {
        this.tipo = tipo;
    }
    public int getCosto() {
        return costo;
    }
    public void setCosto(int costo) {
        this.costo = costo;
    }
    public int getTiempoprep() {
        return tiempoprep;
    }
    public void setTiempoprep(int tiempoprep) {
        this.tiempoprep = tiempoprep;
    }
    
}
