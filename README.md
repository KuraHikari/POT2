# 🅿️ POT2 · Parquímetro de Martos

Manual de usuario de la práctica de Programación (UD02) de 1.º DAM. El programa simula un parquímetro de la zona azul de Martos (Jaén) en una ventana de texto.

## Índice

- [Requisitos](#requisitos)
- [Abrir el programa en Windows](#abrir-el-programa-en-windows)
- [Ejecutar en macOS o Linux](#ejecutar-en-macos-o-linux)
- [Menú principal](#menú-principal)
- [Obtener un ticket](#obtener-un-ticket)
- [Anular una multa](#anular-una-multa)
- [Consultar fecha y hora, tarifas y horarios](#consultar-fecha-y-hora-tarifas-y-horarios)
- [Tarifas y horarios](#tarifas-y-horarios)
- [Menú de administrador](#menú-de-administrador)
- [Prueba completa](#prueba-completa)
- [Limitaciones](#limitaciones)

## Requisitos

- **Java 25 (JDK 25)**, con el comando `java` disponible en la terminal. Comprueba la versión con `java --version`.
- Una terminal interactiva y los archivos del repositorio. No se necesitan bibliotecas externas, Maven ni Gradle.

## Abrir el programa en Windows

Para abrir el parquímetro necesitas tener **Java 25 (JDK 25)** instalado.

1. En GitHub, pulsa el botón verde **Code** y elige **Download ZIP**.
2. Abre **Descargas**, haz clic derecho sobre el ZIP y selecciona **Extraer todo**.
3. Abre la carpeta extraída y haz doble clic en [**POT2.bat**](POT2.bat). Se abrirá el menú del parquímetro.

## Ejecutar en macOS o Linux

Descarga y extrae el ZIP del repositorio, abre una terminal en la carpeta que contiene `README.md` y `src`, y ejecuta:

```sh
java src/Main.java
```

Java ejecuta el código fuente directamente, sin necesidad de IntelliJ.

## Menú principal

Escribe el número de la opción y pulsa **Intro**.

| Opción | Qué puedes hacer |
| --- | --- |
| `1` | Comprar un ticket y ver la hora hasta la que puedes aparcar. |
| `2` | Anular multas por exceder el tiempo (3,00 €) o por carecer de ticket (6,00 €). |
| `3` | Consultar la fecha y hora actuales, las tarifas y los horarios. |
| `4` | Acceder al menú de administrador para consultar los ingresos y los totales de tickets y multas, o apagar el parquímetro. |

Para apagar el parquímetro, accede al menú de administrador y elige la opción `3`.

Cuando una pregunta ofrece `V`, puedes escribirla para volver directamente al menú principal. Si estás comprando un ticket o anulando una multa, esto cancela la operación antes de generar el ticket o justificante.

## Obtener un ticket

1. Selecciona **1. Obtener un ticket**.
2. Responde `S` si eres residente o `N` si no lo eres.
3. Para usar la fecha y hora actuales, responde `N` a la pregunta sobre la simulación. Para probar otro momento, responde `S` y escribe la fecha como `AAAA-MM-DDTHH:MM`, con una `T` entre la fecha y la hora.
4. Introduce el importe con un **punto** para los decimales, por ejemplo `0.25`. El mínimo es de **0,10 €** para residentes y de **0,25 €** para no residentes. La máquina no devuelve cambio.
   Si pagas menos del mínimo, no se emite un ticket y vuelves al menú principal.
5. Lee el ticket en pantalla. Verás su número, la fecha y hora de expedición, el fin del estacionamiento y el importe pagado. Pulsa **Intro** para volver al menú principal.

El número de ticket utiliza el formato `M<AAAA-DD-MM>H<HH:MM>I<céntimos>C<contador>`: la fecha del número va en orden año-día-mes, a diferencia de la fecha de simulación. El contador empieza en `1` cada vez que abres el programa y aumenta por cada ticket emitido.

### Ejemplo de compra

Elige `1`, responde `N` para no residente y `S` para simular. Introduce `2026-11-12T13:50` y paga `0.25`.

El ticket vence el jueves 12 de noviembre a las **17:05**. De los 15 minutos comprados, 10 se cuentan antes del cierre de las 14:00 y los 5 restantes a partir de la reapertura de las 17:00. Si es el primer ticket de la sesión, su número será `M2026-12-11H13:50I25C1`.

## Anular una multa

En el menú principal, elige **2. Anular una multa**.

- **1. Exceder el tiempo permitido (3,00 €).** Introduce el número completo del ticket o escribe `V` para volver. Escribe `3.00` para simular el pago. Si el importe es inferior, el programa vuelve a pedirlo. Después del pago, elige la fecha actual o simulada y lee el justificante.
- **2. Carecer de ticket (6,00 €).** Introduce el importe, elige la fecha actual o simulada y lee el justificante.
- **3. Volver al menú principal.**

Después de leer el justificante, pulsa **Intro** para volver al submenú de multas.

La opción de 3,00 € comprueba el formato del número de ticket, su fecha y hora, un importe de al menos 10 céntimos y un contador positivo. No consulta un registro de tickets emitidos, no comprueba que haya vencido ni impide reutilizar el número. El pago se simula por teclado.

## Consultar fecha y hora, tarifas y horarios

Desde el menú principal, elige **3**. Usa **1** para ver la fecha y hora actuales del equipo, **2** para ver las tarifas y **3** para ver los horarios. Tras mostrar los datos, el programa vuelve al submenú de consultas. La opción **4** vuelve al menú principal.

## Tarifas y horarios

| Tiempo de estacionamiento | No residentes | Residentes |
| --- | ---: | ---: |
| 00:15:00 | 0,25 € | 0,10 € |
| 00:29:50 | 0,45 € | 0,20 € |
| 00:59:31 | 0,85 € | 0,40 € |
| 01:29:12 | 1,25 € | 0,60 € |
| 02:00:00 | 1,70 € | 0,80 € |

Los importes son **umbrales**: se aplica el mayor tramo cuyo precio no supera lo pagado, con un máximo de **2 horas**, aunque pagues más. Por ejemplo, `0.30` para un no residente da 15 minutos. El exceso no se devuelve ni añade tiempo proporcional.

| Día | Horario de la zona azul |
| --- | --- |
| Lunes a viernes | 09:00–14:00 y 17:00–21:00 |
| Sábados | 09:00–14:00 |
| Domingos | Cerrado |

El tiempo comprado se consume durante los tramos de apertura.

## Menú de administrador

Elige **4** en el menú principal e introduce el usuario de prueba `admin` y la contraseña `1234`.

| Opción | Qué puedes hacer |
| --- | --- |
| `1` | Consultar cuántos tickets se han emitido y cuánto se ha recaudado por ellos. |
| `2` | Consultar cuántas multas se han anulado y cuánto se ha recaudado por ellas. |
| `3` | Apagar el parquímetro y terminar la ejecución. |
| `4` | Volver al menú principal. |

Después de consultar un informe, pulsa **Intro** para volver al submenú. Los datos del informe siguen visibles en la consola.

## Prueba completa

Haz toda la prueba en una sola ejecución para comprobar los contadores. Cuando aparezca «Pulsa para continuar», pulsa **Intro**.

1. Compra el ticket de no residente del [ejemplo de compra](#ejemplo-de-compra). Comprueba que vence a las **17:05** y guarda el número.
2. Compra un ticket de residente. Simula `2026-11-12T10:00` y paga `0.10`. Debe vencer a las **10:15**.
3. En el menú de multas, elige **1**, introduce el número del primer ticket y paga `3.00`. Simula la fecha de anulación `2026-11-12T17:10`. Comprueba el motivo y el número de ticket en el justificante.
4. En el mismo submenú, elige **2** y paga `6.00`. Simula `2026-11-12T17:15` y comprueba el justificante. Elige **3** para volver al menú principal.
5. En **Consultas**, abre las opciones **1**, **2** y **3**. Vuelve al menú principal con **4**.
6. En **Administrador**, comprueba que hay **2 tickets y 0,35 €** de ingresos por tickets, y **2 multas anuladas y 9,00 €** de ingresos por multas. Elige **3** para apagar el parquímetro.

## Limitaciones

- No hay persistencia de tickets, multas ni ingresos entre ejecuciones.
- Manejo completo de entradas incorrectas: letras en opciones numéricas, importes mal escritos, fechas inexistentes u horas fuera de rango pueden cerrar el programa con un error. La simulación comprueba la forma del texto antes de convertirlo, pero no captura los errores de conversión.

El código está en `src/Main.java`; también se incluyen configuración de IntelliJ IDEA y un archivo compilado en `out/production/POT2`.
