import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;
import java.time.LocalTime;

void main(String[] args) {

    //MOCK
    //Datos de prueba del administrador: usuario admin, contraseña 1234

    //Declaración de variables
    int opcion, duracionSegundos, diaSemanaInicio, horaInicio, contadorTicket = 1, importeCentimos, opcionMulta, posicionH, posicionI, posicionC, posicionPrimerGuion, posicionSegundoGuion, anioTicket, mesTicket, diaTicket, centimosTicket, contadorAnulaciones = 1, opcionInformacion, opcionAdmin;
    int horasTicket, minutosTicket;
    double importe, importeMulta = 0, ingresosTicket = 0.0, ingresosMultas = 0.0;
    boolean residente, simularFechaHora, importeSuficiente, fechaHoraValida, numeroTicketMultaValido, fechaTicketValida, horaTicketValida, importeTicketValido, contadorTicketTextoValido, parquimetroEncendido = true;
    LocalDateTime fechaHoraInicio, fechaHoraFin, cierreZonaAzul, fechaHoraAnulacion, fechaHoraConsulta;
    long segundosRestantes;
    String nombreDiaInicio = "", nombreMesInicio = "", nombreDiaFin= "", nombreMesFin = "", horaInicioFormateada, horaFinFormateada, expedicion, vencimiento, numeroTicket, respuesta, fechaHoraTexto, numeroTicketMulta, fechaTicket, horaTicket, importeTicket, contadorTicketTexto, referenciaAnulacion, usuarioAdmin, contraseniaAdmin;

    String nombreDiaConsulta = "", nombreMesConsulta = "";


    IO.println("""
            ░█▀█░█▀█░█▀▄░▄▀▄░█░█░▀█▀░█▄█░█▀▀░▀█▀░█▀▄░█▀█░░░░                         \s
            ░█▀▀░█▀█░█▀▄░█\\█░█░█░░█░░█░█░█▀▀░░█░░█▀▄░█░█░░░░                         \s
            ░▀░░░▀░▀░▀░▀░░▀\\░▀▀▀░▀▀▀░▀░▀░▀▀▀░░▀░░▀░▀░▀▀▀░░░░                         \s
            ░█▀▄░█▀▀░░░█▄█░█▀█░█▀▄░▀█▀░█▀█░█▀▀░░░▀▀█░█▀█░█▀█░█▀█░░░█▀█░▀▀█░█░█░█░░░░░░
            ░█░█░█▀▀░░░█░█░█▀█░█▀▄░░█░░█░█░▀▀█░░░▄▀░░█░█░█░█░█▀█░░░█▀█░▄▀░░█░█░█░░░░░░
            ░▀▀░░▀▀▀░░░▀░▀░▀░▀░▀░▀░░▀░░▀▀▀░▀▀▀░░░▀▀▀░▀▀▀░▀░▀░▀░▀░░░▀░▀░▀▀▀░▀▀▀░▀▀▀░░░░""");

    do {

        IO.println("""
        ╔═════════════════════════════════════════════════════════╗
        ║                 PARQUÍMETRO DE MARTOS                   ║
        ║                      MENÚ PRINCIPAL                     ║
        ╠═════════════════════════════════════════════════════════╣
        ║   1. Obtener un ticket.                                 ║
        ║   2. Anular una multa.                                  ║
        ║   3. Consultar fecha y hora, tarifas y horarios.        ║
        ║   4. Menú de administrador.                             ║
        ╚═════════════════════════════════════════════════════════╝
        """);

        opcion = Integer.parseInt(IO.readln("Introduce una opción: "));
        switch (opcion) {
            case 1:
                // 1. Obtener un ticket
                //inicio variables
                importeSuficiente = false;
                duracionSegundos = 0;

                //Eres residente? (S/N)
                do {
                    respuesta = IO.readln("¿Eres residente? (S/N): ");

                    if (!respuesta.equalsIgnoreCase("S")
                            && !respuesta.equalsIgnoreCase("N")) {
                        IO.println("Introduce S o N.");
                    }

                } while (!respuesta.equalsIgnoreCase("S") && !respuesta.equalsIgnoreCase("N"));

                residente = respuesta.equalsIgnoreCase("S");

                //Preguntar si se quiere simular otra fecha y hora distinta a la actual
                do {
                    respuesta = IO.readln("¿Quieres simular una fecha y hora? (S/N): ");

                    if (!respuesta.equalsIgnoreCase("S")&& !respuesta.equalsIgnoreCase("N")) {
                        IO.println("Introduce S o N.");
                    }

                } while (!respuesta.equalsIgnoreCase("S") && !respuesta.equalsIgnoreCase("N"));

                simularFechaHora = respuesta.equalsIgnoreCase("S");

                if (simularFechaHora) {
                    //Preguntar por la fecha y hora a simular y se guarda en fechaHoraInicio
                    do {
                        fechaHoraTexto = IO.readln(
                                "Introduce fecha y hora (ejemplo: 2027-11-21T07:00): ");

                        fechaHoraValida = false;

                        if (fechaHoraTexto.length() == 16) {
                            fechaHoraValida =
                                    fechaHoraTexto.charAt(4) == '-'
                                            && fechaHoraTexto.charAt(7) == '-'
                                            && fechaHoraTexto.charAt(10) == 'T'
                                            && fechaHoraTexto.charAt(13) == ':';
                        }

                        if (!fechaHoraValida) {
                            IO.println("Usa el formato año-mes-díaThora:minutos.");
                        }

                    } while (!fechaHoraValida);

                    fechaHoraInicio = LocalDateTime.parse(fechaHoraTexto);

                } else fechaHoraInicio = LocalDateTime.now();//Se usa la fecha y hora actual

                //avisamos antes de pedir el importe del ticket
                IO.println("La máquina no devuelve cambio.");

                if (residente) {
                    //Preguntar por el importe a pagar, como mínimo 0.10€
                    importe = Double.parseDouble(IO.readln("Introduce el importe (mínimo 0.10€): "));

                    if (importe < 0.10) IO.println("El importe mínimo es de 0.10€.");
                    if (importe >= 0.10) {
                        duracionSegundos = 900; //15 minutos
                        importeSuficiente = true;
                    }
                    if (importe >= 0.20) {
                        duracionSegundos = 1790; //29 minutos y 50 segundos
                    }
                    if (importe >= 0.40) {
                        duracionSegundos = 3571; //59 minutos 31 segundos
                    }
                    if (importe >= 0.60) {
                        duracionSegundos = 5352; //1 hora 29 minutos 12 segundos
                    }
                    if (importe >= 0.80) {
                        duracionSegundos = 7200; //59 minutos 31 segundos
                    }

                } else { //no es residente

                    //Preguntar por el importe a pagar, como mínimo 0.25€
                    importe = Double.parseDouble(IO.readln("Introduce el importe (mínimo 0.25€): "));

                    if (importe < 0.25) IO.println("El importe mínimo es de 0.25€.");
                    if (importe >= 0.25) {
                        duracionSegundos = 900; //15 minutos
                        importeSuficiente = true;
                    }
                    if (importe >= 0.45) {
                        duracionSegundos = 1790; //29 minutos y 50 segundos
                    }
                    if (importe >= 0.85) {
                        duracionSegundos = 3571; //59 minutos 31 segundos
                    }
                    if (importe >= 1.25) {
                        duracionSegundos = 5352; //1 hora 29 minutos 12 segundos
                    }
                    if (importe >= 1.70) {
                        duracionSegundos = 7200; //2 horas
                    }
                }
                if (importeSuficiente) {

                    //cálculo de la horaFechaFin de forma provisional
                    fechaHoraFin = fechaHoraInicio.plusSeconds(duracionSegundos);

                    //extracción de la fechaHora
                    diaSemanaInicio = fechaHoraInicio.getDayOfWeek().getValue(); // lunes = 1, martes = 2 etc
                    horaInicio = fechaHoraInicio.getHour(); //de 0 a 23

                    //validación de la fecha teniendo en cuenta el horario de la zona azul
                    switch (diaSemanaInicio) {
                        case 1, 2, 3, 4, 5: //lunes a viernes 09:00 - 14:00 y 17:00 - 21:00

                            //validación de hora teniendo en cuenta el horario de la zona azul
                            if (horaInicio < 9) {
                                //se compra antes de las 09:00, mientras la zona azul está cerrada
                                //se empieza a contar a partir de las 09:00 y se le suma la duración de segundos establecidos por el importe
                                fechaHoraFin = fechaHoraInicio.withHour(9).withMinute(0).withSecond(0).withNano(0).plusSeconds(duracionSegundos);
                            }
                            if (horaInicio >= 14 && horaInicio < 17) {
                                //Se compra mientras la zona azul está cerrada, entre las 14:00 y las 17:00.
                                //se empieza a contar a partir de las 17:00 y se le suma la duración de segundos establecidos por el importe
                                fechaHoraFin = fechaHoraInicio.withHour(17).withMinute(0).withSecond(0).withNano(0).plusSeconds(duracionSegundos);
                            }
                            if (horaInicio >= 21) {
                                //se compra y su fechaHoraFin coincide con el cierre de la zona azul (21:00)
                                //se empieza a contar a partir de las 09:00 del día siguiente y se le suma la duración de los segundos establecidos por el importe
                                fechaHoraFin = fechaHoraInicio.plusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0).plusSeconds(duracionSegundos);
                            }
                            if (horaInicio >= 9 && horaInicio < 14) {
                                //se compra y su fechaHoraFin coincide con el cierre de la zona azul
                                //se le da el valor provisional a cierraZonaAzul de 14:00
                                cierreZonaAzul = fechaHoraInicio.withHour(14).withMinute(0).withSecond(0).withNano(0);

                                //comprobación del cierre de la zona azul
                                if (fechaHoraFin.isAfter(cierreZonaAzul)) {
                                    //la fechaHoraFin supera la hora de cierreZonaAzul
                                    //se calculan cuántos segundos quedan hasta el cierreZonaAzul y se guardan en segundos restantes
                                    segundosRestantes = ChronoUnit.SECONDS.between(cierreZonaAzul, fechaHoraFin);
                                    //fechaHoraFin se empieza a contar a partir de las 17:00 y se le suma los segundos restantes
                                    fechaHoraFin = cierreZonaAzul.withHour(17).withSecond(0).plusSeconds(segundosRestantes);
                                }
                            }
                            if (horaInicio >= 17 && horaInicio < 21) {
                                //se compra y su fechaHoraFin coincide con el cierre de la zona azul
                                //se le da el valor provisional a cierraZonaAzul de 21:00
                                cierreZonaAzul = fechaHoraInicio.withHour(21).withMinute(0).withSecond(0).withNano(0);

                                //comprobación del cierre de la zona azul
                                if (fechaHoraFin.isAfter(cierreZonaAzul)) {
                                    //la fechaHoraFin supera la hora de cierreZonaAzul
                                    //se calculan cuántos segundos quedan hasta el cierreZonaAzul y se guardan en segundos restantes
                                    segundosRestantes = ChronoUnit.SECONDS.between(cierreZonaAzul, fechaHoraFin);
                                    //fechaHoraFin se empieza a contar a partir de las 09:00 del día siguiente y se le suma los segundos restantes
                                    fechaHoraFin = cierreZonaAzul.plusDays(1).withHour(9).withSecond(0).plusSeconds(segundosRestantes);
                                }
                            }
                            break;
                        case 6: // sábado 09:00 - 14:00

                            //validación de hora teniendo en cuenta el horario de la zona azul
                            if (horaInicio < 9) {
                                //se compra antes de las 09:00
                                //se empieza a contar a partir de las 09:00 y se le suma la duración de segundos establecidos por el importe
                                fechaHoraFin = fechaHoraInicio.withHour(9).withMinute(0).withSecond(0).withNano(0).plusSeconds(duracionSegundos);
                            }
                            if (horaInicio >= 14) {
                                //se compra después de las 14:00, mientras la zona azul está cerrada
                                //se empieza a contar a partir del lunes a las 09:00 y se le suma la duración de segundos establecidos por el importe
                                fechaHoraFin = fechaHoraInicio.plusDays(2).withHour(9).withMinute(0).withSecond(0).withNano(0).plusSeconds(duracionSegundos);
                            }
                            if (horaInicio >= 9 && horaInicio < 14) {
                                //se compra entre las 09:00 y las 14:00
                                //se le da el valor provisional a cierraZonaAzul de 21:00
                                cierreZonaAzul = fechaHoraInicio.withHour(14).withMinute(0).withSecond(0).withNano(0);

                                //comprobación del cierre de la zona azul
                                if (fechaHoraFin.isAfter(cierreZonaAzul)) {
                                    //la fechaHoraFin supera la hora de cierreZonaAzul
                                    //se calculan cuántos segundos quedan hasta el cierreZonaAzul y se guardan en segundos restantes
                                    segundosRestantes = ChronoUnit.SECONDS.between(cierreZonaAzul, fechaHoraFin);
                                    //fechaHoraFin se empieza a contar a partir de las 09:00 del día siguiente y se le suma los segundos restantes
                                    fechaHoraFin = cierreZonaAzul.plusDays(2).withHour(9).withSecond(0).plusSeconds(segundosRestantes);
                                }
                            }
                                break;
                                case 7: // domingo cerrado
                                    //se suma 1 día (lunes), empieza a contar a partir del lunes a las 09:00
                                    fechaHoraFin = fechaHoraInicio.plusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0).plusSeconds(duracionSegundos);
                                    break;
                            }

                    //Se podía dar formato a las fechas con DateTimeFormatter según chat gpt y quedaría más
                    // limpio y optimizado el código pero como en la práctica obligatoria no lo pedía decidí
                    // hacer switch reforzando los conocimientos que hemos visto en clase y cumpliendo con la
                    //regla de oro de no usar elementos de la programación que no hayamos visto en clase.

                    //switch para formatear los días de la semana (inicio)
                    switch (fechaHoraInicio.getDayOfWeek().getValue()) {
                        case 1 -> nombreDiaInicio = "lunes";
                        case 2 -> nombreDiaInicio = "martes";
                        case 3 -> nombreDiaInicio = "miércoles";
                        case 4 -> nombreDiaInicio = "jueves";
                        case 5 -> nombreDiaInicio = "viernes";
                        case 6 -> nombreDiaInicio = "sábado";
                        case 7 -> nombreDiaInicio = "domingo";
                    }

                    //switch para formatear los meses (inicio)
                    switch (fechaHoraInicio.getMonth().getValue()){
                        case 1 -> nombreMesInicio = "enero";
                        case 2 -> nombreMesInicio = "febrero";
                        case 3 -> nombreMesInicio = "marzo";
                        case 4 -> nombreMesInicio = "abril";
                        case 5 -> nombreMesInicio = "mayo";
                        case 6 -> nombreMesInicio = "junio";
                        case 7 -> nombreMesInicio = "julio";
                        case 8 -> nombreMesInicio = "agosto";
                        case 9 -> nombreMesInicio = "septiembre";
                        case 10 -> nombreMesInicio = "octubre";
                        case 11 -> nombreMesInicio = "noviembre";
                        case 12 -> nombreMesInicio = "diciembre";
                    }

                    //switch para formatear los días de la semana (fin)
                    switch (fechaHoraFin.getDayOfWeek().getValue()) {
                        case 1 -> nombreDiaFin = "lunes";
                        case 2 -> nombreDiaFin= "martes";
                        case 3 -> nombreDiaFin = "miércoles";
                        case 4 -> nombreDiaFin = "jueves";
                        case 5 -> nombreDiaFin = "viernes";
                        case 6 -> nombreDiaFin = "sábado";
                        case 7 -> nombreDiaFin = "domingo";
                    }

                    //switch para formatear los meses (fin)
                    switch (fechaHoraFin.getMonth().getValue()){
                        case 1 -> nombreMesFin = "enero";
                        case 2 -> nombreMesFin = "febrero";
                        case 3 -> nombreMesFin = "marzo";
                        case 4 -> nombreMesFin = "abril";
                        case 5 -> nombreMesFin = "mayo";
                        case 6 -> nombreMesFin = "junio";
                        case 7 -> nombreMesFin = "julio";
                        case 8 -> nombreMesFin = "agosto";
                        case 9 -> nombreMesFin = "septiembre";
                        case 10 -> nombreMesFin = "octubre";
                        case 11 -> nombreMesFin = "noviembre";
                        case 12 -> nombreMesFin = "diciembre";
                    }


                    //Formateo de las horas, minutos y segundos
                    horaInicioFormateada = String.format("%02d:%02d",
                            fechaHoraInicio.getHour(),
                            fechaHoraInicio.getMinute());

                    horaFinFormateada = String.format("%02d:%02d",
                            fechaHoraFin.getHour(),
                            fechaHoraFin.getMinute());

                    //formateo de expedición y vencimiento
                    expedicion = nombreDiaInicio + " "
                            + fechaHoraInicio.getDayOfMonth() + " de "
                            + nombreMesInicio + " de "
                            + fechaHoraInicio.getYear() + " a las "
                            + horaInicioFormateada;

                    vencimiento = nombreDiaFin + " "
                            + fechaHoraFin.getDayOfMonth() + " de "
                            + nombreMesFin + " de "
                            + fechaHoraFin.getYear() + " a las "
                            + horaFinFormateada;

                    //cálculo del número de ticket
                    importeCentimos = (int) Math.round(importe * 100);
                    numeroTicket = "M"
                            + String.format("%04d-%02d-%02d",
                            fechaHoraInicio.getYear(),
                            fechaHoraInicio.getDayOfMonth(),
                            fechaHoraInicio.getMonthValue())
                            + "H" + horaInicioFormateada
                            + "I" + importeCentimos
                            + "C" + contadorTicket;
                    contadorTicket++;

                    //sumamos el importe pagado a los ingresos por tickets
                    ingresosTicket += importe;

                    // Muestra una animación sencilla de carga por consola.
                    IO.print("      Cargando ");
                    for (int i = 0; i < 4; i++) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                        IO.print(". ");
                    }
                    IO.println("");

                    //Se imprime el ticket
                    IO.print("""
        ╔═════════════════════════════════════════════════════════╗
        ║                 AYUNTAMIENTO DE MARTOS                  ║
        ║                       ZONA AZUL                         ║
        ║                TICKET DE ESTACIONAMIENTO                ║
        ╠═════════════════════════════════════════════════════════╣
        """);
                    System.out.printf("║ %-55s ║%n", "Nº de ticket: " + numeroTicket);
                    System.out.printf("║ %-55s ║%n", "");
                    System.out.printf("║ %-55s ║%n", "Expedición:");
                    System.out.printf("║ %-55s ║%n", expedicion);
                    System.out.printf("║ %-55s ║%n", "");
                    System.out.printf("║ %-55s ║%n", "Fin de estacionamiento:");
                    System.out.printf("║ %-55s ║%n", vencimiento);
                    System.out.printf("║ %-55s ║%n", "");
                    System.out.printf("║ %-55s ║%n", "Importe pagado: " + String.format("%.2f €", importe));
                    IO.println("""
        ╠═════════════════════════════════════════════════════════╣
        ║              CONSERVE ESTE TICKET VISIBLE               ║
        ║                La máquina no da cambio.                 ║
        ╚═════════════════════════════════════════════════════════╝
        """);

                    // Pausa la ejecución hasta que el usuario pulse Enter.
                    IO.readln("  Pulsa para continuar... ");
                    IO.println("");

                    // Simula una limpieza de pantalla imprimiendo múltiples saltos de línea.
                    for (int i = 0; i < 100; i++) {
                        System.out.println();
                    }
                }
                break;
            case 2:
                // 2.Anular una multa
                do {
                    IO.println("""
        ╔═════════════════════════════════════════════════════════╗
        ║                 PARQUÍMETRO DE MARTOS                   ║
        ║                   ANULACIÓN DE MULTAS                   ║
        ╠═════════════════════════════════════════════════════════╣
        ║   1. Exceder el tiempo permitido — 3,00 €               ║
        ║   2. Carecer de ticket — 6,00 €                         ║
        ║   3. Volver al menú principal.                          ║
        ╚═════════════════════════════════════════════════════════╝
        """);

                    opcionMulta = Integer.parseInt(IO.readln("Elige una opción: "));
                    switch (opcionMulta){
                        case 1: // Exceder el tiempo permitido — 3,00 €

                            //inicializamos la variable a false
                            numeroTicketMultaValido = false;

                            do {
                                //preguntamos por el número del ticket de estacionamiento o la opción de volver
                                numeroTicketMulta = IO.readln(
                                        "Introduce el número del ticket o V para volver: ");

                                if (!numeroTicketMulta.equalsIgnoreCase("V")) { //si no ha escrito v

                                    // Sacamos la posición de cada letra
                                    posicionH = numeroTicketMulta.indexOf("H");
                                    posicionI = numeroTicketMulta.indexOf("I");
                                    posicionC = numeroTicketMulta.indexOf("C");

                                    // Comprobamos el orden y que cada parte tenga contenido
                                    if (numeroTicketMulta.startsWith("M")
                                            && posicionH > 0
                                            && posicionI > posicionH + 1
                                            && posicionC > posicionI + 1
                                            && posicionC < numeroTicketMulta.length() - 1) {

                                        // Ejemplo: M2026-31-10H13:30I170C1
                                        //extraer el texto desde la primera posición hasta la última sin contar la última y separamos por trozos
                                        fechaTicket = numeroTicketMulta.substring(1, posicionH);
                                        horaTicket = numeroTicketMulta.substring(posicionH + 1, posicionI);
                                        importeTicket = numeroTicketMulta.substring(posicionI + 1, posicionC);
                                        contadorTicketTexto = numeroTicketMulta.substring(posicionC + 1);

                                        // Validamos la fecha: año-día-mes
                                        fechaTicketValida = fechaTicket.length() == 10; //si la longitud de fechaTicket coincide es true
                                        posicionPrimerGuion = fechaTicket.indexOf("-"); //extraemos la posición del primer guion 4
                                        //extraemos la posición del segundo guion 7
                                        posicionSegundoGuion = fechaTicket.indexOf(
                                                "-", posicionPrimerGuion + 1);

                                        if (fechaTicketValida) {
                                            //comprobamos que estén colocados según el formato año-día-mes
                                            fechaTicketValida = posicionPrimerGuion == 4
                                                    && posicionSegundoGuion == 7;
                                        }

                                        //comprobar que contiene cifras y no letras
                                        if (fechaTicketValida) {
                                            for (int i = 0; i < fechaTicket.length(); i++) {
                                                //saltamos los guiones para comprobar solo las cifras
                                                if (i != posicionPrimerGuion
                                                        && i != posicionSegundoGuion) {
                                                    if (!Character.isDigit(fechaTicket.charAt(i))) {
                                                        fechaTicketValida = false;
                                                    }
                                                }
                                            }

                                            if (fechaTicketValida) {
                                                //extraer año, mes y día en formato int
                                                anioTicket = Integer.parseInt(
                                                        fechaTicket.substring(0, posicionPrimerGuion));
                                                mesTicket = Integer.parseInt(
                                                        fechaTicket.substring(posicionSegundoGuion + 1));
                                                diaTicket = Integer.parseInt(
                                                        fechaTicket.substring(
                                                                posicionPrimerGuion + 1,
                                                                posicionSegundoGuion));

                                                //se valida que exista la fecha no un 31 de febrero ni un 50 de mayo por ejemplo
                                                //si la fecha no existe, LocalDate.of lanza una excepción
                                                LocalDate.of(anioTicket, mesTicket, diaTicket);

                                                // Validamos la hora
                                                horaTicketValida = horaTicket.length() == 5;

                                                if (horaTicketValida) {
                                                    //comprobamos que los dos puntos estén en la posición 2
                                                    horaTicketValida = horaTicket.charAt(2) == ':';
                                                }

                                                if (horaTicketValida) {
                                                    for (int i = 0; i < horaTicket.length(); i++) {
                                                        if (i != 2) { // Saltamos los dos puntos
                                                            if (!Character.isDigit(horaTicket.charAt(i))) {
                                                                horaTicketValida = false;
                                                            }
                                                        }
                                                    }

                                                    if (horaTicketValida) {
                                                        //convertimos las horas y los minutos del ticket de texto a número
                                                        horasTicket = Integer.parseInt(
                                                                horaTicket.substring(0, 2));
                                                        minutosTicket = Integer.parseInt(
                                                                horaTicket.substring(3, 5));

                                                        //comprobamos que exista la hora; si no existe, se lanza una excepción que no podemos capturar y controlar en este tema
                                                        LocalTime.of(horasTicket, minutosTicket);

                                                        // Validamos el importe en céntimos
                                                        importeTicketValido = !importeTicket.isEmpty(); //comprobamos que el texto del importe no esté vacío

                                                        if (importeTicketValido) {
                                                            //revisamos que cada carácter del importe sea una cifra
                                                            for (int i = 0; i < importeTicket.length(); i++) {
                                                                if (!Character.isDigit(
                                                                        importeTicket.charAt(i))) {
                                                                    importeTicketValido = false;
                                                                }
                                                            }

                                                            if (importeTicketValido) {
                                                                //convertimos el importe a número para comprobar el mínimo en céntimos
                                                                centimosTicket = Integer.parseInt(
                                                                        importeTicket);
                                                                importeTicketValido = centimosTicket >= 10;

                                                                if (importeTicketValido) {
                                                                    // Validamos el contador
                                                                    contadorTicketTextoValido =
                                                                            !contadorTicketTexto.isEmpty();

                                                                    //comprobamos que el contador contenga solo cifras
                                                                    for (int i = 0;
                                                                         i < contadorTicketTexto.length();
                                                                         i++) {
                                                                        if (!Character.isDigit(
                                                                                contadorTicketTexto.charAt(i))) {
                                                                            contadorTicketTextoValido = false;
                                                                        }
                                                                    }

                                                                    if (contadorTicketTextoValido) {
                                                                        //comprobamos que el contador del ticket sea mayor que cero
                                                                        contadorTicketTextoValido =
                                                                                Integer.parseInt(contadorTicketTexto) > 0;

                                                                        if (contadorTicketTextoValido) {
                                                                            //el número ha superado las comprobaciones de formato y valores anteriores
                                                                            numeroTicketMultaValido = true;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    if (!numeroTicketMultaValido) {
                                        IO.println("El número de ticket no es válido.");
                                    }
                                }

                            } while (!numeroTicketMultaValido
                                    && !numeroTicketMulta.equalsIgnoreCase("V")); //se repite mientras el número no es válido y no se haya introducido v

                            // Solo cobramos e imprimimos si ha superado las comprobaciones
                            if (numeroTicketMultaValido) {

                                //avisamos que la máquina no devuelve cambio y cuanto cuesta
                                IO.println(
                                        "La anulación cuesta 3.00 €. La máquina no devuelve cambio.");
                                do {
                                    importeMulta = Double.parseDouble(
                                            IO.readln("Introduce el importe: "));

                                    if (importeMulta < 3.00) {
                                        IO.println("El importe mínimo es de 3.00 €.");
                                    }
                                } while (importeMulta < 3.00);

                                // Elegimos fecha actual o simulada para la anulación
                                do {
                                    respuesta = IO.readln(
                                            "¿Quieres simular una fecha y hora de anulación? (S/N): ");

                                    if (!respuesta.equalsIgnoreCase("S")
                                            && !respuesta.equalsIgnoreCase("N")) {
                                        IO.println("Introduce S o N.");
                                    }

                                } while (!respuesta.equalsIgnoreCase("S")
                                        && !respuesta.equalsIgnoreCase("N"));

                                simularFechaHora = respuesta.equalsIgnoreCase("S");

                                if (simularFechaHora) {
                                    do {
                                        fechaHoraTexto = IO.readln(
                                                "Introduce fecha y hora (ejemplo: 2026-11-12T10:30): ");

                                        fechaHoraValida = false;

                                        if (fechaHoraTexto.length() == 16) {
                                            fechaHoraValida =
                                                    fechaHoraTexto.charAt(4) == '-'
                                                            && fechaHoraTexto.charAt(7) == '-'
                                                            && fechaHoraTexto.charAt(10) == 'T'
                                                            && fechaHoraTexto.charAt(13) == ':';
                                        }

                                        if (!fechaHoraValida) {
                                            IO.println("Usa el formato año-mes-díaThora:minutos.");
                                        }

                                    } while (!fechaHoraValida);

                                    fechaHoraAnulacion = LocalDateTime.parse(fechaHoraTexto);

                                } else {
                                    fechaHoraAnulacion = LocalDateTime.now();
                                }

                                // Referencia del justificante de anulación
                                referenciaAnulacion = "A"
                                        + String.format("%04d-%02d-%02d",
                                        fechaHoraAnulacion.getYear(),
                                        fechaHoraAnulacion.getDayOfMonth(),
                                        fechaHoraAnulacion.getMonthValue())
                                        + "H" + String.format("%02d:%02d",
                                        fechaHoraAnulacion.getHour(),
                                        fechaHoraAnulacion.getMinute())
                                        + "I" + Math.round(importeMulta * 100)//redondeamos para evitar pequeñas diferencias decimales
                                        + "C" + (contadorAnulaciones * 2);

                                contadorAnulaciones++;

                                //sumamos el importe pagado a los ingresos por multas anuladas
                                ingresosMultas += importeMulta;

                                // Muestra una animación sencilla de carga por consola.
                                IO.print("      Cargando ");
                                for (int i = 0; i < 4; i++) {
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        Thread.currentThread().interrupt();
                                        break;
                                    }
                                    IO.print(". ");
                                }
                                IO.println("");

                                // Imprimimos el justificante
                                IO.print("""
                ╔═════════════════════════════════════════════════════════╗
                ║                 AYUNTAMIENTO DE MARTOS                  ║
                ║                       ZONA AZUL                         ║
                ║              JUSTIFICANTE DE ANULACIÓN                  ║
                ╠═════════════════════════════════════════════════════════╣
                """);

                                System.out.printf("║ %-55s ║%n",
                                        "Referencia: " + referenciaAnulacion);
                                System.out.printf("║ %-55s ║%n", "");
                                System.out.printf("║ %-55s ║%n", "Ticket de estacionamiento:");
                                System.out.printf("║ %-55s ║%n", numeroTicketMulta);
                                System.out.printf("║ %-55s ║%n", "");
                                System.out.printf("║ %-55s ║%n",
                                        "Motivo: exceder el tiempo permitido");

                                System.out.printf("║ %-55s ║%n",
                                        "Fecha: " + String.format("%02d/%02d/%04d a las %02d:%02d",
                                                fechaHoraAnulacion.getDayOfMonth(),
                                                fechaHoraAnulacion.getMonthValue(),
                                                fechaHoraAnulacion.getYear(),
                                                fechaHoraAnulacion.getHour(),
                                                fechaHoraAnulacion.getMinute()));

                                System.out.printf("║ %-55s ║%n",
                                        "Importe pagado: " + String.format("%.2f €", importeMulta));

                                IO.println("""
                ╠═════════════════════════════════════════════════════════╣
                ║                     MULTA ANULADA                       ║
                ║               CONSERVE ESTE JUSTIFICANTE                ║
                ╚═════════════════════════════════════════════════════════╝
                """);

                                // Pausa la ejecución hasta que el usuario pulse Enter.
                                IO.readln("  Pulsa para continuar... ");
                                IO.println("");

                                // Simula una limpieza de pantalla imprimiendo múltiples saltos de línea.
                                for (int i = 0; i < 100; i++) {
                                    System.out.println();
                                }
                            }

                            break;
                        case 2: //carecer de ticket — 6,00 €

                            //avisamos que la máquina no devuelve cambio y cuanto cuesta
                            IO.println("La anulación cuesta 6.00 €. La máquina no devuelve cambio.");

                            importeSuficiente = false;

                            //pedimos el importe hasta que sea suficiente
                            //también permitimos escribir V para volver sin anular
                            do {
                                respuesta = IO.readln("Introduce el importe o V para volver: ");

                                if (!respuesta.equalsIgnoreCase("V")) {
                                    importeMulta = Double.parseDouble(respuesta);
                                    importeSuficiente = importeMulta >= 6.00;

                                    if (!importeSuficiente) {
                                        IO.println("El importe mínimo es de 6.00 €.");
                                    }
                                }

                            } while (!importeSuficiente && !respuesta.equalsIgnoreCase("V"));

                            //solo continuamos con la anulación si el importe es suficiente
                            if (importeSuficiente) {

                                // Elegimos fecha actual o simulada para la anulación
                                do {
                                    respuesta = IO.readln(
                                            "¿Quieres simular una fecha y hora de anulación? (S/N): ");

                                    if (!respuesta.equalsIgnoreCase("S")
                                            && !respuesta.equalsIgnoreCase("N")) {
                                        IO.println("Introduce S o N.");
                                    }

                                } while (!respuesta.equalsIgnoreCase("S")
                                        && !respuesta.equalsIgnoreCase("N"));

                                simularFechaHora = respuesta.equalsIgnoreCase("S");

                                if (simularFechaHora) {
                                    do {
                                        fechaHoraTexto = IO.readln(
                                                "Introduce fecha y hora (ejemplo: 2026-11-12T10:30): ");

                                        fechaHoraValida = false;

                                        if (fechaHoraTexto.length() == 16) {
                                            fechaHoraValida =
                                                    fechaHoraTexto.charAt(4) == '-'
                                                            && fechaHoraTexto.charAt(7) == '-'
                                                            && fechaHoraTexto.charAt(10) == 'T'
                                                            && fechaHoraTexto.charAt(13) == ':';
                                        }

                                        if (!fechaHoraValida) {
                                            IO.println("Usa el formato año-mes-díaThora:minutos.");
                                        }

                                    } while (!fechaHoraValida);

                                    fechaHoraAnulacion = LocalDateTime.parse(fechaHoraTexto);

                                } else {
                                    fechaHoraAnulacion = LocalDateTime.now();
                                }

                                //Creamos el número de referencia del justificante de anulación
                                referenciaAnulacion = "A"
                                        + String.format("%04d-%02d-%02d",
                                        fechaHoraAnulacion.getYear(),
                                        fechaHoraAnulacion.getDayOfMonth(),
                                        fechaHoraAnulacion.getMonthValue())
                                        + "H" + String.format("%02d:%02d",
                                        fechaHoraAnulacion.getHour(),
                                        fechaHoraAnulacion.getMinute())
                                        + "I" + Math.round(importeMulta * 100) //redondeamos para evitar pequeñas diferencias decimales
                                        + "C" + (contadorAnulaciones * 2);

                                contadorAnulaciones++;

                                //sumamos el importe pagado a los ingresos por multas anuladas
                                ingresosMultas += importeMulta;

                                // Muestra una animación sencilla de carga por consola.
                                IO.print("      Cargando ");
                                for (int i = 0; i < 4; i++) {
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        Thread.currentThread().interrupt();
                                        break;
                                    }
                                    IO.print(". ");
                                }
                                IO.println("");

                                // Imprimimos el justificante
                                IO.print("""
                ╔═════════════════════════════════════════════════════════╗
                ║                 AYUNTAMIENTO DE MARTOS                  ║
                ║                       ZONA AZUL                         ║
                ║              JUSTIFICANTE DE ANULACIÓN                  ║
                ╠═════════════════════════════════════════════════════════╣
                """);

                                System.out.printf("║ %-55s ║%n",
                                        "Referencia: " + referenciaAnulacion);
                                System.out.printf("║ %-55s ║%n", "");
                                System.out.printf("║ %-55s ║%n",
                                        "Motivo: carecer de ticket");
                                System.out.printf("║ %-55s ║%n", "");

                                System.out.printf("║ %-55s ║%n",
                                        "Fecha: " + String.format("%02d/%02d/%04d a las %02d:%02d",
                                                fechaHoraAnulacion.getDayOfMonth(),
                                                fechaHoraAnulacion.getMonthValue(),
                                                fechaHoraAnulacion.getYear(),
                                                fechaHoraAnulacion.getHour(),
                                                fechaHoraAnulacion.getMinute()));

                                System.out.printf("║ %-55s ║%n",
                                        "Importe pagado: " + String.format("%.2f €", importeMulta));

                                IO.println("""
                ╠═════════════════════════════════════════════════════════╣
                ║                     MULTA ANULADA                       ║
                ║               CONSERVE ESTE JUSTIFICANTE                ║
                ╚═════════════════════════════════════════════════════════╝
                """);

                                // Pausa la ejecución hasta que el usuario pulse Enter.
                                IO.readln("  Pulsa para continuar... ");
                                IO.println("");

                                // Simula una limpieza de pantalla imprimiendo múltiples saltos de línea.
                                for (int i = 0; i < 100; i++) {
                                    System.out.println();
                                }
                            }

                            break;
                        case 3: //volver al menú principal
                            break;
                        default:
                            IO.println("Opción no válida.");
                    }
                }while (opcionMulta != 3);
                break;

            case 3:// 3.Consultar fecha y hora actual, tarifas y horarios

                do {

                    //mostramos el menú de consulta de información
                    IO.println("""
        ╔═════════════════════════════════════════════════════════╗
        ║                 PARQUÍMETRO DE MARTOS                   ║
        ║            INFORMACIÓN DE LA ZONA AZUL                  ║
        ╠═════════════════════════════════════════════════════════╣
        ║   1. Consultar fecha y hora actuales.                   ║
        ║   2. Consultar tarifas.                                 ║
        ║   3. Consultar horarios.                                ║
        ║   4. Volver al menú principal.                          ║
        ╚═════════════════════════════════════════════════════════╝
        """);
                    opcionInformacion = Integer.parseInt(IO.readln("Elige una opción de consulta: "));

                    switch (opcionInformacion){
                        case 1: //fecha y hora actual en formato español
                            //inicializo la variable fechaHoraConsulta
                            fechaHoraConsulta = LocalDateTime.now();
                            // Muestra una animación sencilla de carga por consola.
                            IO.print("      Cargando ");
                            for (int i = 0; i < 4; i++) {
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                    break;
                                }
                                IO.print(". ");
                            }
                            IO.println("");

                            //switch para formatear los días de la semana (consulta)
                            switch (fechaHoraConsulta.getDayOfWeek().getValue()) {
                                case 1 -> nombreDiaConsulta = "lunes";
                                case 2 -> nombreDiaConsulta = "martes";
                                case 3 -> nombreDiaConsulta = "miércoles";
                                case 4 -> nombreDiaConsulta = "jueves";
                                case 5 -> nombreDiaConsulta = "viernes";
                                case 6 -> nombreDiaConsulta = "sábado";
                                case 7 -> nombreDiaConsulta = "domingo";
                            }

                            //switch para formatear los meses (consulta)
                            switch (fechaHoraConsulta.getMonth().getValue()){
                                case 1 -> nombreMesConsulta = "enero";
                                case 2 -> nombreMesConsulta = "febrero";
                                case 3 -> nombreMesConsulta = "marzo";
                                case 4 -> nombreMesConsulta = "abril";
                                case 5 -> nombreMesConsulta = "mayo";
                                case 6 -> nombreMesConsulta = "junio";
                                case 7 -> nombreMesConsulta = "julio";
                                case 8 -> nombreMesConsulta = "agosto";
                                case 9 -> nombreMesConsulta = "septiembre";
                                case 10 -> nombreMesConsulta = "octubre";
                                case 11 -> nombreMesConsulta = "noviembre";
                                case 12 -> nombreMesConsulta = "diciembre";
                            }

                            //mostramos la fecha y hora actuales en formato español
                            IO.print("""
        ╔═════════════════════════════════════════════════════════╗
        ║                  PARQUÍMETRO DE MARTOS                  ║
        ║                  FECHA Y HORA ACTUALES                  ║
        ╠═════════════════════════════════════════════════════════╣
        """);
                            System.out.printf("║ %-55s ║%n",
                                    "%s %d de %s de %d a las %02d:%02d".formatted(
                                            nombreDiaConsulta,
                                            fechaHoraConsulta.getDayOfMonth(),
                                            nombreMesConsulta,
                                            fechaHoraConsulta.getYear(),
                                            fechaHoraConsulta.getHour(),
                                            fechaHoraConsulta.getMinute()));
                            IO.println("""
        ╚═════════════════════════════════════════════════════════╝
        """);

                            // Pausa la ejecución hasta que el usuario pulse Enter.
                            IO.readln("  Pulsa para continuar... ");
                            IO.println("");

                            // Simula una limpieza de pantalla imprimiendo múltiples saltos de línea.
                            for (int i = 0; i < 100; i++) {
                                System.out.println();
                            }
                            break;

                        case 2: //tarifa del parquímetro
                            // Muestra una animación sencilla de carga por consola.
                            IO.print("      Cargando ");
                            for (int i = 0; i < 4; i++) {
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                    break;
                                }
                                IO.print(". ");
                            }
                            IO.println("");

                            //mostramos las tarifas de residentes y no residentes
                            IO.println("""
        ╔═════════════════════════════════════════════════════════╗
        ║                 TARIFAS DE ZONA AZUL                    ║
        ╠═════════════════════════════════════════════════════════╣
        ║ Duración             Residentes       No residentes     ║
        ║ 15 min               0,10 €           0,25 €            ║
        ║ 29 min 50 s          0,20 €           0,45 €            ║
        ║ 59 min 31 s          0,40 €           0,85 €            ║
        ║ 1 h 29 min 12 s      0,60 €           1,25 €            ║
        ║ 2 horas              0,80 €           1,70 €            ║
        ╠═════════════════════════════════════════════════════════╣
        ║               La máquina no devuelve cambio.            ║
        ╚═════════════════════════════════════════════════════════╝
        """);

                            // Pausa la ejecución hasta que el usuario pulse Enter.
                            IO.readln("  Pulsa para continuar... ");
                            IO.println("");

                            // Simula una limpieza de pantalla imprimiendo múltiples saltos de línea.
                            for (int i = 0; i < 100; i++) {
                                System.out.println();
                            }
                            break;

                        case 3://horario de la zona azul
                            // Muestra una animación sencilla de carga por consola.
                            IO.print("      Cargando ");
                            for (int i = 0; i < 4; i++) {
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                    break;
                                }
                                IO.print(". ");
                            }
                            IO.println("");

                            //mostramos los horarios de funcionamiento de la zona azul
                            IO.println("""
        ╔═════════════════════════════════════════════════════════╗
        ║                 HORARIOS DE ZONA AZUL                   ║
        ╠═════════════════════════════════════════════════════════╣
        ║ Lunes a viernes: 09:00 - 14:00 / 17:00 - 21:00          ║
        ║ Sábados:         09:00 - 14:00                          ║
        ║ Domingos:        Cerrado                                ║
        ╠═════════════════════════════════════════════════════════╣
        ║ El tiempo solo se consume durante el horario indicado.  ║
        ╚═════════════════════════════════════════════════════════╝
        """);

                            // Pausa la ejecución hasta que el usuario pulse Enter.
                            IO.readln("  Pulsa para continuar... ");
                            IO.println("");

                            // Simula una limpieza de pantalla imprimiendo múltiples saltos de línea.
                            for (int i = 0; i < 100; i++) {
                                System.out.println();
                            }
                            break;
                        case 4: //volver al menú principal
                            break;
                        default:
                            IO.println("Opción no válida.");
                    }

                }while (opcionInformacion != 4);

                break;
            case 4: //Menú de administrador
                //pedimos las credenciales del administrador
                usuarioAdmin = IO.readln("Introduce el usuario: ");
                contraseniaAdmin = IO.readln("Introduce la contraseña: ");

                //comprobamos que el usuario y la contraseña sean correctos
                if (usuarioAdmin.equals("admin") && contraseniaAdmin.equals("1234")) {
                    IO.println("Acceso concedido.");
                    do {

                        //imprimimos el submenú de administrador
                        IO.println("""
        ╔═════════════════════════════════════════════════════════╗
        ║                 PARQUÍMETRO DE MARTOS                   ║
        ║                  MENÚ DE ADMINISTRADOR                  ║
        ╠═════════════════════════════════════════════════════════╣
        ║   1. Consultar ingresos y número de tickets.            ║
        ║   2. Consultar ingresos y número de multas anuladas.    ║
        ║   3. Apagar el parquímetro.                             ║
        ║   4. Volver al menú principal.                          ║
        ╚═════════════════════════════════════════════════════════╝
        """);
                        opcionAdmin = Integer.parseInt(IO.readln("Elige una opción: "));

                        switch (opcionAdmin) {
                            case 1: //consultar ingresos y número de tickets
                                // Muestra una animación sencilla de carga por consola.
                                IO.print("      Cargando ");
                                for (int i = 0; i < 4; i++) {
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        Thread.currentThread().interrupt();
                                        break;
                                    }
                                    IO.print(". ");
                                }
                                IO.println("");

                                //mostramos el número de tickets emitidos y el total recaudado
                                IO.print("""
        ╔═════════════════════════════════════════════════════════╗
        ║                  PARQUÍMETRO DE MARTOS                  ║
        ║                  INGRESOS POR TICKETS                   ║
        ╠═════════════════════════════════════════════════════════╣
        """);

                                System.out.printf("║ %-55s ║%n", "");
                                System.out.printf("║ %-55s ║%n",
                                        "Tickets emitidos: " + (contadorTicket - 1));
                                System.out.printf("║ %-55s ║%n",
                                        "Total recaudado: " + String.format("%.2f €", ingresosTicket));
                                System.out.printf("║ %-55s ║%n", "");

                                IO.println("""
        ╚═════════════════════════════════════════════════════════╝
        """);

                                // Pausa la ejecución hasta que el usuario pulse Enter.
                                IO.readln("  Pulsa para continuar... ");
                                IO.println("");

                                // Simula una limpieza de pantalla imprimiendo múltiples saltos de línea.
                                for (int i = 0; i < 100; i++) {
                                    System.out.println();
                                }
                                break;
                            case 2: //consultar ingresos y número de multas anuladas

                                // Muestra una animación sencilla de carga por consola.
                                IO.print("      Cargando ");
                                for (int i = 0; i < 4; i++) {
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        Thread.currentThread().interrupt();
                                        break;
                                    }
                                    IO.print(". ");
                                }
                                IO.println("");

                                //mostramos el número de multas anuladas y el total recaudado
                                IO.print("""
        ╔═════════════════════════════════════════════════════════╗
        ║                  PARQUÍMETRO DE MARTOS                  ║
        ║                  INGRESOS POR MULTAS                    ║
        ╠═════════════════════════════════════════════════════════╣
        """);

                                System.out.printf("║ %-55s ║%n", "");
                                System.out.printf("║ %-55s ║%n",
                                        "Multas anuladas: " + (contadorAnulaciones - 1));
                                System.out.printf("║ %-55s ║%n",
                                        "Total recaudado: " + String.format("%.2f €", ingresosMultas));
                                System.out.printf("║ %-55s ║%n", "");

                                IO.println("""
        ╚═════════════════════════════════════════════════════════╝
        """);

                                // Pausa la ejecución hasta que el usuario pulse Enter.
                                IO.readln("  Pulsa para continuar... ");
                                IO.println("");

                                // Simula una limpieza de pantalla imprimiendo múltiples saltos de línea.
                                for (int i = 0; i < 100; i++) {
                                    System.out.println();
                                }
                                break;
                            case 3: //apagar el parquímetro
                                parquimetroEncendido = false;
                                IO.println("Parquímetro apagado.");
                                break;
                            case 4: //volver al menú principal
                                break;
                            default:
                                IO.println("Opción no válida.");
                        }

                    } while (parquimetroEncendido && opcionAdmin != 4);
                } else {
                    IO.println("Usuario o contraseña incorrectos.");
                }
                break;
            default:
                IO.println("Opción no válida.");
        }
    } while (parquimetroEncendido);
}
