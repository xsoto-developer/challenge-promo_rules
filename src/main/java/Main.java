import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Reto: Implementación de Reglas de Promoción");

        // Crear promociones de ejemplo
        Date hoy = new Date(); // Fecha actual para pruebas
        Date inicioPromo1 = new Date(hoy.getTime() - 86400000); // Ayer
        Date finPromo1 = new Date(hoy.getTime() + 86400000); // Mañana
        Promocion promo1 = new Promocion("Descuento Electronica 10%", "Electronica", 0.10, inicioPromo1, finPromo1);

        Date inicioPromo2 = new Date(hoy.getTime() - 86400000 * 2); // Anteayer
        Date finPromo2 = new Date(hoy.getTime()); // Hoy
        Promocion promo2 = new Promocion("Descuento Electronica 15%", "Electronica", 0.15, inicioPromo2, finPromo2);

        Date inicioPromo3 = new Date(hoy.getTime()); // Hoy
        Date finPromo3 = new Date(hoy.getTime() + 86400000 * 2); // Pasado mañana
        Promocion promo3 = new Promocion("Descuento Ropa 20%", "Ropa", 0.20, inicioPromo3, finPromo3);

        List<Promocion> promociones = List.of(promo1, promo2, promo3);

        // Crear productos
        Producto producto1 = new Producto("Laptop", "Electronica", 1000.0);
        Producto producto2 = new Producto("Camisa", "Ropa", 50.0);

        // Aplicar promociones y mostrar precios finales (casos de prueba)
        System.out.println("Precio final para " + producto1.getNombre() + ": " +
                AplicadorPromociones.aplicarPromociones(producto1, promociones, hoy));
        // Esperado: 850.0 (max de 10% y 15%, pero promo2 termina hoy, assuming inclusive; max es 15%)

        System.out.println("Precio final para " + producto2.getNombre() + ": " +
                AplicadorPromociones.aplicarPromociones(producto2, promociones, hoy));
        // Esperado: 40.0 (20%)
    }
}

class Promocion {
    private String nombre;
    private String categoria;
    private double descuento; // Porcentaje, e.g., 0.10 para 10%
    private Date fechaInicio;
    private Date fechaFin;

    public Promocion(String nombre, String categoria, double descuento, Date fechaInicio, Date fechaFin) {
        if (descuento < 0 || descuento > 1) {
            throw new IllegalArgumentException("Descuento debe estar entre 0 y 1");
        }
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
}

class Producto {
    private String nombre;
    private String categoria;
    private double precio;

    public Producto(String nombre, String categoria, double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("Precio no puede ser negativo");
        }
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
}

class AplicadorPromociones {
    public static double aplicarPromociones(Producto producto, List<Promocion> promociones, Date fecha) {
        double precioFinal = producto.getPrecio();
        double descuentoMaximo = 0.0;

        for (Promocion promocion : promociones) {
            if (producto.getCategoria().equals(promocion.getCategoria()) && promocion.esValidaEnFecha(fecha)) {
                descuentoMaximo = Math.max(descuentoMaximo, promocion.getDescuento());
            }
        }

        if (descuentoMaximo > 0) {
            precioFinal *= (1 - descuentoMaximo);
        }
        return precioFinal;
    }
}

// Agrega al final de Main.java

interface DiscountStrategy {
    double apply(double precio, double descuento);
}

class PercentageDiscountStrategy implements DiscountStrategy {
    @Override
    public double apply(double precio, double descuento) {
        return precio * (1 - descuento);
    }
}
//
//// En AplicadorPromociones, usa strategy
//class AplicadorPromociones {
//    private static final DiscountStrategy strategy = new PercentageDiscountStrategy(); // Inyectable en futuro
//
//    public static double aplicarPromociones(Producto producto, List<Promocion> promociones, Date fecha) {
//        double precioFinal = producto.getPrecio();
//        double descuentoMaximo = 0.0;
//
//        for (Promocion promocion : promociones) {
//            if (producto.getCategoria().equals(promocion.getCategoria()) && promocion.esValidaEnFecha(fecha)) {
//                descuentoMaximo = Math.max(descuentoMaximo, promocion.getDescuento());
//            }
//        }
//
//        if (descuentoMaximo > 0) {
//            precioFinal = strategy.apply(precioFinal, descuentoMaximo);
//        }
//        return precioFinal;
//    }
//}
//
//class PromocionFactory {
//    public static Promocion create(String nombre, String categoria, double descuento, Date inicio, Date fin) {
//        return new Promocion(nombre, categoria, descuento, inicio, fin);
//    }
//}
//
//// En main, usa factory: Promocion promo1 = PromocionFactory.create(...);