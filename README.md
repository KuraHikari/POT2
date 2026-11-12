# 🅿️ POT2 · Parquímetro de Martos

Manual de usuario de la práctica de Programación (UD02) de 1.º DAM. El programa simula un parquímetro de la zona azul de Martos (Jaén) en una ventana de texto.

## Índice

- [Abrir el programa en Windows](#abrir-el-programa-en-windows)
- [Menú principal](#menú-principal)
- [Obtener un ticket](#obtener-un-ticket)
- [Anular una multa](#anular-una-multa)
- [Tarifas y horarios](#tarifas-y-horarios)
- [Estado de la práctica](#estado-de-la-práctica)

## Abrir el programa en Windows

El archivo `.bat` para abrir el programa con doble clic está pendiente de incorporarse al repositorio. Mientras tanto, puedes probar la versión publicada sin IntelliJ con el **JDK 25** instalado y disponible en la terminal de Windows.

1. En GitHub, pulsa el botón verde **Code** y elige **Download ZIP**.
2. Abre **Descargas**, haz clic derecho sobre el ZIP y selecciona **Extraer todo**.
3. Abre la carpeta extraída donde están este README y la carpeta `src`.
4. Haz clic en la barra de dirección del Explorador, escribe `cmd` y pulsa **Intro**.
5. En la ventana que se abre, escribe `java src/Main.java` y pulsa **Intro**. Aparecerá el menú del parquímetro.

## Menú principal

Escribe el número de la opción y pulsa **Intro**.

| Opción | Qué puedes hacer en la versión publicada |
| --- | --- |
| `1` | Comprar un ticket y ver la hora hasta la que puedes aparcar. |
| `2` | Abrir el menú de multas y simular el pago de 3,00 € por exceder el tiempo. |
| `3` | Consulta de fecha, tarifas y horarios. Pendiente de implementar en el programa. |
| `4` | Menú de administrador. Pendiente de implementar. |

Para salir, escribe `5` en el menú principal. En esta versión, el número aún no aparece en el menú y se muestra «Opción no válida» justo antes de cerrar el programa.

## Obtener un ticket

1. Selecciona **1. Obtener un ticket**.
2. Responde `S` si eres residente o `N` si no lo eres.
3. Para usar la fecha y hora actuales, responde `N` a la pregunta sobre la simulación. Para probar otro momento, responde `S` y escribe la fecha como `AAAA-MM-DDTHH:MM`, con una `T` entre la fecha y la hora.
4. Introduce el importe con un **punto** para los decimales, por ejemplo `0.25`. El mínimo es de **0,10 €** para residentes y de **0,25 €** para no residentes. La máquina no devuelve cambio.
5. Lee el ticket en pantalla. Verás su número, la fecha y hora de expedición, el fin del estacionamiento y el importe pagado.

### Ejemplo de compra

Elige `1`, responde `N` para no residente y `S` para simular. Introduce `2026-11-12T13:50` y paga `0.25`.

El ticket vence el jueves 12 de noviembre a las **17:05**. De los 15 minutos comprados, 10 se cuentan antes del cierre de las 14:00 y los 5 restantes a partir de la reapertura de las 17:00. Si es el primer ticket de la sesión, su número será `M2026-12-11H13:50I25C1`.

## Anular una multa

En el menú principal, elige **2. Anular una multa**.

- **1. Exceder el tiempo permitido (3,00 €).** Introduce el número completo del ticket que figura en pantalla y escribe `3.00` para simular el pago. Si el importe es inferior, el programa vuelve a pedirlo. En la versión publicada, el programa vuelve al submenú tras el pago y el justificante aún está en desarrollo.
- **2. Carecer de ticket (6,00 €).** La opción figura en el submenú y su funcionamiento está pendiente de publicar.
- **3. Volver al menú principal.**

## Tarifas y horarios

| Tiempo de estacionamiento | No residentes | Residentes |
| --- | ---: | ---: |
| 00:15:00 | 0,25 € | 0,10 € |
| 00:29:50 | 0,45 € | 0,20 € |
| 00:59:31 | 0,85 € | 0,40 € |
| 01:29:12 | 1,25 € | 0,60 € |
| 02:00:00 | 1,70 € | 0,80 € |

| Día | Horario de la zona azul |
| --- | --- |
| Lunes a viernes | 09:00–14:00 y 17:00–21:00 |
| Sábados | 09:00–14:00 |
| Domingos | Cerrado |

El tiempo comprado se consume durante los tramos de apertura.

## Estado de la práctica

El código fuente se encuentra en [`src/Main.java`](src/Main.java). La copia de trabajo incorpora los justificantes para los dos tipos de multa y la simulación de la fecha de anulación. Esos cambios están pendientes de compilar y publicar.
