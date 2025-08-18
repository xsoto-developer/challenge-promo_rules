public class Main {
    public static void main(String[] args) {
        // Reto para el desarrollador:
        // 1. Implementar las clases Promocion y Producto.
        // 2. Implementar la lógica en AplicadorPromociones para aplicar las promociones
        //    correctamente, teniendo en cuenta las fechas y la categoría del producto.
        // 3. Implementar la resolución de conflictos entre promociones.
        // 4. Crear casos de prueba para verificar que las promociones se aplican correctamente.

        System.out.println("Reto: Implementación de Reglas de Promoción");

        // Ejemplo de uso (debe ser completado por el desarrollador)
        // Crear promociones
        // Crear productos
        // Aplicar promociones y mostrar el precio final
    }
}

// TODO: Implementar la clase Promocion
class Promocion {
    private String nombre;
    private String categoria;
    private double descuento;
    private Date fechaInicio;
    private Date fechaFin;

    public Promocion(String nombre, String categoria, double descuento, Date fechaInicio, Date fechaFin) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.descuento = descuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getDescuento() {
        return descuento;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public boolean esValidaEnFecha(Date fecha) {
        return !fecha.before(fechaInicio) && !fecha.after(fechaFin);
    }
    // TODO: Implementar los métodos y atributos necesarios
}

// TODO: Implementar la clase Producto
class Producto {
    private String nombre;
    private String categoria;
    private double precio;

    public Producto(String nombre, String categoria, double precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }
    // TODO: Implementar los métodos y atributos necesarios
}

class AplicadorPromociones {
    // TODO: Implementar este método para aplicar las promociones a un producto
    //       teniendo en cuenta la fecha y la categoría.
    //       También debe resolver los conflictos entre promociones.
    public static double aplicarPromociones(Producto producto, List<Promocion> promociones, Date fecha) {
        double precioFinal = producto.getPrecio();
        double descuentoMaximo = 0.0;

        for (Promocion promocion : promociones) {
            if (producto.getCategoria().equals(promocion.getCategoria()) && promocion.esValidaEnFecha(fecha)) {
                descuentoMaximo = Math.max(descuentoMaximo, promocion.getDescuento());
            }
        }

        precioFinal = precioFinal * (1 - descuentoMaximo);
        return precioFinal;
    }
}