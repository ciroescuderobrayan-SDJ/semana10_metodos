package com.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // 1) Locale “es_CO” para separadores de miles en printf (%,.0f)
        Locale.setDefault(new Locale("es", "CO"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            menu();
            // Validación simple por si el usuario mete algo que no es número
            if (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingresa un número de opción.");
                scanner.next(); // limpiar token no numérico
                continue;
            }
            int opcion = scanner.nextInt();

            if (opcion == 0) {
                break;
            }
            switch (opcion) {
                case 1 -> ejercicio1(scanner);
                case 2 -> ejercicio2(scanner);
                case 3 -> ejercicio3(scanner);
                case 4 -> ejercicio4(scanner);
                case 5 -> ejercicio5(scanner);
                case 6 -> ejercicio6(scanner);
                case 7 -> ejercicio7(scanner);
                case 8 -> ejercicio8(scanner);
                case 9 -> ejercicio9(scanner);
                case 10 -> ejercicio10(scanner);
                default -> System.out.println("Opción Inválida");
            }

            esperarEnter(scanner);
        }

        System.out.println("Fin del programa");
        scanner.close();
    }

    public static void menu() {
        System.out.println("--------------------------------");
        System.out.println("Bienvenido a la aplicación de la semana 10");
        System.out.println("1. Ejercicio 1 - Descuentos en Tienda");
        System.out.println("2. Ejercicio 2 - Calificación Crediticia");
        System.out.println("3. Ejercicio 3 - Control de Inventario");
        System.out.println("4. Ejercicio 4 - Tiempo de Viaje");
        System.out.println("5. Ejercicio 5 - Gestión de Vuelos");
        System.out.println("6. Ejercicio 6 - Rutas de Entrega");
        System.out.println("7. Ejercicio 7 - Rutina de Ejercicios");
        System.out.println("8. Ejercicio 8 - Triaje Hospitalario");
        System.out.println("9. Ejercicio 9 - Notas y Promedio");
        System.out.println("10. Ejercicio 10 - Riego Automático");
        System.out.println("0. para salir");
        System.out.print("Selecciona una opción: ");
        System.out.println("\n--------------------------------");
    }

    // =========================
    // Ejercicio 1: Descuentos
    // =========================
    public static void ejercicio1(Scanner sc) {
        System.out.println("Ejercicio 1: Calculadora de descuentos en Tienda");
        System.out.print("Ingresa la cantidad de productos: ");
        int cantidad = leerEnteroNoNegativo(sc);

        System.out.print("Ingresa el precio unitario: ");
        double precioUnit = leerDoubleNoNegativo(sc);

        double subtotal = cantidad * precioUnit;
        double descuentoVolumen = 0.0;

        if (cantidad > 20) descuentoVolumen = 0.20;
        else if (cantidad > 10) descuentoVolumen = 0.15;
        else if (cantidad > 5) descuentoVolumen = 0.10;

        double totalConVolumen = subtotal * (1 - descuentoVolumen);

        // Descuento adicional por total alto (después del volumen)
        double descuentoAdicional = (totalConVolumen > 100_000) ? 0.05 : 0.0;
        double totalFinal = totalConVolumen * (1 - descuentoAdicional);

        System.out.printf("Subtotal: $%,.0f%n", subtotal);
        System.out.printf("Descuento por volumen: %.0f%%%n", descuentoVolumen * 100);
        System.out.printf("Descuento adicional por total > 100.000: %.0f%%%n", descuentoAdicional * 100);
        System.out.printf("Precio final a pagar: $%,.0f%n", totalFinal);
    }

    // ===================================
    // Ejercicio 2: Calificación Crediticia
    // ===================================
    public static void ejercicio2(Scanner sc) {
        System.out.println("Ejercicio 2: Sistema de Calificación Crediticia");
        System.out.print("Ingresa tus ingresos mensuales: ");
        double ingresos = leerDoubleNoNegativo(sc);

        System.out.print("Ingresa tu edad: ");
        int edad = leerEnteroPositivo(sc); // positiva; el criterio real lo validamos abajo

        System.out.print("Ingresa tus gastos mensuales: ");
        double gastos = leerDoubleNoNegativo(sc);

        boolean ingresosOk = ingresos >= 2_000_000;
        boolean edadOk = (edad >= 18 && edad <= 65);
        boolean gastosOk = gastos <= ingresos * 0.70;

        if (ingresosOk && edadOk && gastosOk) {
            double montoMax = ingresos * 5;
            System.out.println("Crédito APROBADO.");
            System.out.printf("Monto máximo del crédito: $%,.0f%n", montoMax);
        } else {
            System.out.println("Crédito NO aprobado. Motivos:");
            if (!ingresosOk) System.out.println("- Ingresos menores a $2,000,000");
            if (!edadOk) System.out.println("- Edad fuera del rango 18–65 años");
            if (!gastosOk) System.out.println("- Gastos superan el 70% de los ingresos");
        }
    }

    // ===================================
    // Ejercicio 3: Control de Inventario
    // ===================================
    public static void ejercicio3(Scanner sc) {
        System.out.println("Ejercicio 3: Control de Inventario de Mercado (7 días)");
        sc.nextLine(); // limpiar salto pendiente de la opción del menú
        String[] frutas = {"Manzana", "Banano", "Mango"};
        int[] stock = new int[frutas.length];

        // Stock inicial
        for (int i = 0; i < frutas.length; i++) {
            System.out.print("Stock inicial de " + frutas[i] + ": ");
            stock[i] = leerEnteroPositivo(sc);
        }

        // 5) Semilla fija para resultados reproducibles
        Random rnd = new Random(123);
        for (int dia = 1; dia <= 7; dia++) {
            System.out.println("\nDía " + dia + " ------------------");
            for (int i = 0; i < frutas.length; i++) {
                int ventas = rnd.nextInt(21);   // 0..20
                int llegadas = rnd.nextInt(31); // 0..30
                stock[i] = Math.max(0, stock[i] - ventas); // evitar negativos
                stock[i] += llegadas;

                if (stock[i] < 10) {
                    stock[i] += 50; // pedido de reposición
                    System.out.println(frutas[i] + " bajó de 10. Se repusieron +50.");
                }
                System.out.println(frutas[i] + " | Ventas:" + ventas + " Llegadas:" + llegadas + " -> Stock:" + stock[i]);
            }
        }
        System.out.println("\nStock final de la semana:");
        for (int i = 0; i < frutas.length; i++) {
            System.out.println("- " + frutas[i] + ": " + stock[i]);
        }
    }

    // =============================================
    // Ejercicio 4: Calculadora de Tiempo de Viaje
    // =============================================
    public static void ejercicio4(Scanner sc) {
        System.out.println("Ejercicio 4: Tiempo de Viaje en Transporte Público");
        boolean traficoPesado = leerSiNo(sc, "¿Hay tráfico pesado? (s/n): ");
        boolean llueve = leerSiNo(sc, "¿Está lloviendo? (s/n): ");

        int bus = 20;
        int metro = 15;
        int caminar = 10;

        if (traficoPesado) bus += bus / 2; // +50%
        if (llueve) caminar *= 2;          // +100%

        int total = bus + metro + caminar;

        System.out.println("Detalle:");
        System.out.println("- Bus: " + bus + " min");
        System.out.println("- Metro: " + metro + " min");
        System.out.println("- Caminar: " + caminar + " min");
        System.out.println("Tiempo total: " + total + " minutos");
    }

    // ================================
    // Ejercicio 5: Gestión de Vuelos
    // ================================
    public static void ejercicio5(Scanner sc) {
        System.out.println("Ejercicio 5: Sistema de Gestión de Vuelos");
        boolean malClima = leerSiNo(sc, "¿Hay mal clima? (s/n): ");

        int vuelos = 5;
        int[] retrasosBase = new int[vuelos];
        for (int i = 0; i < vuelos; i++) {
            System.out.print("Minutos de retraso del vuelo " + (i + 1) + " (-1 si está cancelado): ");
            retrasosBase[i] = sc.nextInt();
        }

        System.out.println("\nResultados:");
        for (int i = 0; i < vuelos; i++) {
            if (retrasosBase[i] < 0) {
                System.out.println("Vuelo " + (i + 1) + ": CANCELADO");
                continue;
            }
            int retraso = retrasosBase[i] + (malClima ? 30 : 0);
            // 3) Regla estricta del enunciado: a tiempo vs retrasado (>15)
            String estado = (retraso > 15) ? "RETRASADO" : "A TIEMPO";
            System.out.println("Vuelo " + (i + 1) + ": " + estado + " | Retraso total: " + retraso + " min");
        }
    }

    // ====================================
    // Ejercicio 6: Optimizador de Rutas
    // ====================================
    public static void ejercicio6(Scanner sc) {
        System.out.println("Ejercicio 6: Optimizador de Rutas (jornada máx. 8h)");
        int minutosDia = 8 * 60; // 480

        System.out.print("Entregas en CENTRO (30 min c/u): ");
        int c = leerEnteroNoNegativo(sc);
        System.out.print("Entregas en PERIFERIA (45 min c/u): ");
        int p = leerEnteroNoNegativo(sc);
        System.out.print("Entregas en RURAL (60 min c/u): ");
        int r = leerEnteroNoNegativo(sc);

        // Construimos una lista de duraciones y aplicamos un greedy por menor duración
        List<Integer> tareas = new ArrayList<>();
        for (int i = 0; i < c; i++) tareas.add(30);
        for (int i = 0; i < p; i++) tareas.add(45);
        for (int i = 0; i < r; i++) tareas.add(60);
        Collections.sort(tareas); // 30, 45, 60

        int usadas = 0, entregasHechas = 0;
        for (int t : tareas) {
            if (usadas + t <= minutosDia) {
                usadas += t;
                entregasHechas++;
            } else break;
        }

        System.out.println("\nResultado:");
        System.out.println("- Entregas posibles hoy: " + entregasHechas + " (de las " + tareas.size() + " solicitadas)");
        System.out.println("- Tiempo utilizado: " + usadas + " min de " + minutosDia + " min");
        System.out.println("- Máximo teórico si todas fueran de 30 min: " + (minutosDia / 30));
    }

    // =========================================
    // Ejercicio 7: Monitor de Rutina de Ejercicio
    // =========================================
    public static void ejercicio7(Scanner sc) {
        System.out.println("Ejercicio 7: Rutina de Ejercicios");
        System.out.print("Minutos de CARDIO (10 cal/min): ");
        int cardio = leerEnteroNoNegativo(sc);
        System.out.print("Minutos de PESAS (8 cal/min): ");
        int pesas = leerEnteroNoNegativo(sc);
        System.out.print("Minutos de YOGA (5 cal/min): ");
        int yoga = leerEnteroNoNegativo(sc);

        int calorias = cardio * 10 + pesas * 8 + yoga * 5;
        int puntos = (calorias > 500) ? 50 : 0;

        System.out.println("Calorías totales: " + calorias);
        System.out.println("Puntos ganados: " + puntos);
    }

    // ==================================
    // Ejercicio 8: Sistema de Triaje
    // ==================================
    public static void ejercicio8(Scanner sc) {
        System.out.println("Ejercicio 8: Triaje Hospitalario (5 pacientes)");
        int pacientes = 5;

        for (int i = 1; i <= pacientes; i++) {
            System.out.println("\nPaciente " + i);
            System.out.print("Temperatura (°C): ");
            double temp = sc.nextDouble();
            System.out.print("Presión sistólica (mmHg): ");
            int sis = sc.nextInt();
            System.out.print("Presión diastólica (mmHg): ");
            int dia = sc.nextInt();
            System.out.print("Dolor (0 a 10): ");
            int dolor = sc.nextInt();

            // Reglas del enunciado (urgente)
            boolean urgente = (temp > 38.5) || (sis > 140 || dia > 90) || (dolor >= 8);

            // Reglas asumidas para "prioritario" (para dar más granularidad)
            boolean prioritario = (temp > 37.5) || (sis > 130 || dia > 85) || (dolor >= 5);

            String clasificacion;
            if (urgente) clasificacion = "URGENTE";
            else if (prioritario) clasificacion = "PRIORITARIO";
            else clasificacion = "NORMAL";

            System.out.println("Clasificación: " + clasificacion);
        }
    }

    // ===============================================
    // Ejercicio 9: Calculadora de Notas y Promedios
    // ===============================================
    public static void ejercicio9(Scanner sc) {
        System.out.println("Ejercicio 9: Notas y Promedio (3 estudiantes)");
        int n = 3;
        for (int i = 1; i <= n; i++) {
            System.out.println("\nEstudiante " + i + " (notas de 0 a 5)");
            double parciales = leerNota(sc, "Parciales (30%): ");
            double proyecto = leerNota(sc, "Proyecto final (40%): ");
            double particip = leerNota(sc, "Participación (30%): ");

            double promedio = parciales * 0.30 + proyecto * 0.40 + particip * 0.30;
            String estado = (promedio >= 4.0) ? "APRUEBA" : (promedio >= 3.5 ? "SUPLETORIO" : "REPRUEBA");

            System.out.printf("Promedio: %.2f -> %s%n", promedio, estado);
        }
    }

    // ===========================================
    // Ejercicio 10: Sistema de Riego Automático
    // ===========================================
    public static void ejercicio10(Scanner sc) {
        System.out.println("Ejercicio 10: Riego Automático (7 días)");
        for (int dia = 1; dia <= 7; dia++) {
            System.out.println("\nDía " + dia);
            System.out.print("Humedad del suelo (%): ");
            int humedad = leerEnteroRango(sc, 0, 100);
            System.out.print("Temperatura (°C): ");
            double temp = sc.nextDouble();
            boolean llovio = leerSiNo(sc, "¿Llovió en las últimas 24h? (s/n): ");

            int minutosRiego = 0;
            if (llovio) {
                minutosRiego = 0; // no riega si llovió
            } else if (humedad < 30) {
                minutosRiego = 60;
            } else if (temp > 25 && humedad < 50) {
                minutosRiego = 30;
            } else {
                minutosRiego = 0;
            }
            System.out.println("Riego programado: " + minutosRiego + " minutos");
        }
    }

    // ======================
    // Helpers de validación
    // ======================

    // 4) Pausa más limpia
    private static void esperarEnter(Scanner sc) {
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine(); // limpia posible salto pendiente
        sc.nextLine(); // espera ENTER real
    }

    private static int leerEnteroPositivo(Scanner sc) {
        int v;
        while (true) {
            if (!sc.hasNextInt()) {
                System.out.print("Ingresa un entero válido: ");
                sc.next();
                continue;
            }
            v = sc.nextInt();
            if (v > 0) return v;
            System.out.print("Debe ser > 0. Intenta de nuevo: ");
        }
    }

    private static int leerEnteroNoNegativo(Scanner sc) {
        int v;
        while (true) {
            if (!sc.hasNextInt()) {
                System.out.print("Ingresa un entero válido: ");
                sc.next();
                continue;
            }
            v = sc.nextInt();
            if (v >= 0) return v;
            System.out.print("Debe ser >= 0. Intenta de nuevo: ");
        }
    }

    private static int leerEnteroRango(Scanner sc, int min, int max) {
        int v;
        while (true) {
            if (!sc.hasNextInt()) {
                System.out.print("Ingresa un entero válido: ");
                sc.next();
                continue;
            }
            v = sc.nextInt();
            if (v >= min && v <= max) return v;
            System.out.print("Debe estar entre " + min + " y " + max + ". Intenta de nuevo: ");
        }
    }

    private static boolean leerSiNo(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        String s = sc.next().trim().toLowerCase();
        while (!s.equals("s") && !s.equals("n")) {
            System.out.print("Responde 's' o 'n': ");
            s = sc.next().trim().toLowerCase();
        }
        return s.equals("s");
    }

    private static double leerNota(Scanner sc, String msg) {
        System.out.print(msg);
        while (true) {
            if (!sc.hasNextDouble()) {
                System.out.print("Ingresa un número (0 a 5): ");
                sc.next();
                continue;
            }
            double n = sc.nextDouble();
            if (n >= 0 && n <= 5) return n;
            System.out.print("La nota debe estar entre 0 y 5. Intenta de nuevo: ");
        }
    }

    private static double leerDoubleNoNegativo(Scanner sc) {
        while (true) {
            if (!sc.hasNextDouble()) {
                System.out.print("Ingresa un número válido (>= 0): ");
                sc.next();
                continue;
            }
            double n = sc.nextDouble();
            if (n >= 0) return n;
            System.out.print("Debe ser >= 0. Intenta de nuevo: ");
        }
    }
}
