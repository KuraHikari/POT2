import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;
import java.time.LocalTime;

void main(String[] args) {

    //Declaración de variables
    int opcion, duracionSegundos, diaSemanaInicio, horaInicio, contadorTicket = 1, importeCentimos, opcionMulta, posicionH, posicionI, posicionC, posicionPrimerGuion, posicionSegundoGuion, anioTicket, mesTicket, diaTicket, centimosTicket;
    double importe, importeMulta;
    boolean residente, simularFechaHora, importeSuficiente, fechaHoraValida, numeroTicketMultaValido, fechaTicketValida, horaTicketValida, importeTicketValido, contadorTicketTextoValido;
    LocalDateTime fechaHoraInicio, fechaHoraFin, cierreZonaAzul, fechaHoraAnulacion;
    long segundosRestantes;
    String nombreDiaInicio = "", nombreMesInicio = "", nombreDiaFin= "", nombreMesFin = "", horaInicioFormateada, horaFinFormateada, expedicion, vencimiento, numeroTicket, respuesta, fechaHoraTexto, numeroTicketMulta, fechaTicket, horaTicket, importeTicket, contadorTicketTexto;

    //Todo ingresos por ticket, validar importe, validar fecha, avisar de pagar que no devuelve cambio

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
                }
                break;
            case 2:
                // 2.Anular una multa
                do {
                    IO.println("""
        1. Exceder el tiempo permitido — 3,00 €
        2. Carecer de ticket — 6,00 €
        3. Volver al menú principal
        """);

                    opcionMulta = Integer.parseInt(IO.readln("Elige una opción: "));
                    switch (opcionMulta){
                        case 1://exceder el tiempo permitido — 3,00 €

                            //inicializamos la variable a false
                            numeroTicketMultaValido = false;
                            do {
                                //preguntamos por el número de la multa
                                numeroTicketMulta = IO.readln("Introduce el número de tu ticket de estacionamiento: ");

                                //Sacamos la posición de cada letra
                                posicionH = numeroTicketMulta.indexOf("H"); //saca la posición de la H
                                posicionI = numeroTicketMulta.indexOf("I"); //saca la posición de la I
                                posicionC = numeroTicketMulta.indexOf("C"); //saca la posición de la C

                                //Comprobamos el orden de las posiciones y validamos
                                if (numeroTicketMulta.startsWith("M") && posicionH > 0
                                        && posicionI > posicionH + 1
                                        && posicionC > posicionI + 1
                                        && posicionC < numeroTicketMulta.length() - 1) {

                                    //M2026-31-10H13:30I170C1 número de ejemplo

                                    //extraer el texto desde la primera posición hasta la última sin contar la última y separamos por trozos
                                    fechaTicket = numeroTicketMulta.substring(1, posicionH); //coge 2026-31-10
                                    horaTicket = numeroTicketMulta.substring(posicionH + 1, posicionI); //coge 13:30
                                    importeTicket = numeroTicketMulta.substring(posicionI + 1, posicionC); //coge 170
                                    contadorTicketTexto = numeroTicketMulta.substring(posicionC + 1); //coge 1

                                    //fecha Ticket = 2026-31-10 (los guiones están en la posición 4 y 7)
                                    //horaTicket = 13:30
                                    //importeTicket = 170
                                    //contadorTicketTexto = 1

                                    //Validamos fechaTicket
                                    fechaTicketValida = fechaTicket.length() == 10; //si la longitud de fechaTicket coincide es true
                                    posicionPrimerGuion = fechaTicket.indexOf("-"); //extraemos la posición del primer guion 4
                                    posicionSegundoGuion = fechaTicket.indexOf("-", posicionPrimerGuion + 1); //extraemos la posición del segundo guion 7

                                    if (fechaTicketValida) {
                                        fechaTicketValida = posicionPrimerGuion == 4 && posicionSegundoGuion == 7; //comprobamos que estén colocados según el formato "año-día-mes
                                    }

                                    //comprobar que contiene cifras y no letras
                                    if (fechaTicketValida) {
                                        for (int i = 0; i < fechaTicket.length(); i++) {
                                            if (i != posicionPrimerGuion && i != posicionSegundoGuion) {
                                                if (!Character.isDigit(fechaTicket.charAt(i))) {
                                                    fechaTicketValida = false;
                                                }
                                            }
                                        }
                                        if (fechaTicketValida) {
                                            //extraer año, mes y día en formato int
                                            anioTicket = Integer.parseInt(fechaTicket.substring(0, posicionPrimerGuion));
                                            mesTicket = Integer.parseInt(fechaTicket.substring(posicionSegundoGuion + 1));
                                            diaTicket = Integer.parseInt(fechaTicket.substring(posicionPrimerGuion + 1, posicionSegundoGuion));

                                            //se valida que exista la fecha no un 31 de febrero ni un 50 de mayo por ejemplo
                                            LocalDate.of(anioTicket, mesTicket, diaTicket);

                                            //validamos horaTicket
                                            horaTicketValida = horaTicket.length() == 5;

                                            if (horaTicketValida){
                                                horaTicketValida = horaTicket.charAt(2) == ':';

                                            if (horaTicketValida) {

                                                for (int i = 0; i < horaTicket.length(); i++) {
                                                    if (i != 2) { // Saltamos los dos puntos
                                                        if (!Character.isDigit(horaTicket.charAt(i))) {
                                                            horaTicketValida = false;
                                                        }
                                                    }
                                                }
                                                if (horaTicketValida) {
                                                    int horasTicket = Integer.parseInt(horaTicket.substring(0, 2));
                                                    int minutosTicket = Integer.parseInt(horaTicket.substring(3, 5));

                                                    LocalTime.of(horasTicket, minutosTicket);

                                                    //validamos importe Ticket
                                                    importeTicketValido = !importeTicket.isEmpty(); //si la longitud es superior que 0 es true

                                                    if (importeTicketValido){
                                                        for (int i = 0; i < importeTicket.length(); i++) {
                                                            if (!Character.isDigit(importeTicket.charAt(i))) { //si no es número el formato no es válido
                                                                importeTicketValido = false;
                                                            }
                                                        }
                                                        if (importeTicketValido) {
                                                            centimosTicket = Integer.parseInt(importeTicket);
                                                            importeTicketValido = centimosTicket >= 10;

                                                            if (importeTicketValido) {
                                                                contadorTicketTextoValido = !contadorTicketTexto.isEmpty(); //si no está vacío es válido

                                                                for (int i = 0; i < contadorTicketTexto.length(); i++) {
                                                                    if (!Character.isDigit(contadorTicketTexto.charAt(i))) {
                                                                        contadorTicketTextoValido = false;
                                                                    }
                                                                }

                                                                if (contadorTicketTextoValido) {

                                                                    int contadorLeido = Integer.parseInt(contadorTicketTexto);
                                                                    contadorTicketTextoValido = contadorLeido > 0;

                                                                    if (contadorTicketTextoValido) {
                                                                        // Si eso se cumple el número introducido es válido
                                                                        numeroTicketMultaValido = true;
                                                                        IO.println("correcto");
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
                                } if (!numeroTicketMultaValido) IO.println("El formato del número de ticket no es válido."); //si no se cumple la variable

                            }while (!numeroTicketMultaValido);

                            //pedimos los 3 euros y no da cambio
                            IO.println("La anulación cuesta 3.00 €. La máquina no devuelve cambio.");

                            do {
                                importeMulta = Double.parseDouble(
                                        IO.readln("Introduce el importe: "));

                                if (importeMulta < 3.00) {
                                    IO.println("El importe mínimo es de 3.00 €.");
                                }
                            } while (importeMulta < 3.00);

                            fechaHoraAnulacion = LocalDateTime.now();



                            break;
                        case 2: //carecer de ticket — 6,00 €
                            break;
                        case 3: //volver al menú principal
                            break;
                        default:
                            IO.println("Opción no válida.");
                    }
                }while (opcionMulta != 3);
                break;
            case 3:
                // 3.Consultar fecha y hora actual, tarifas y horarios
                break;
            case 4:
                // 4.Menú de administrador
                break;
            default:
                IO.println("Opción no válida.");
        }
    } while (opcion != 5);
}
