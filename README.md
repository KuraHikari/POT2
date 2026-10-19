# 🅿️ POT2 - Parquímetro de Martos

## 📖 Descripción

Proyecto desarrollado para la Unidad Didáctica 02 (UD02)
del módulo de Programación del Ciclo Formativo de Grado
Superior en Desarrollo de Aplicaciones Multiplataforma (DAM).

El objetivo es desarrollar un programa en Java que simule
el funcionamiento de un parquímetro de la zona azul de
la ciudad de Martos (Jaén).

## ⚙️ Funcionalidades

El programa permite gestionar las siguientes operaciones:

### 🎫 1. Obtener un ticket
- Seleccionar si el usuario es residente o no residente.
- Calcular la duración del estacionamiento según el importe.
- Aplicar las tarifas correspondientes.
- Calcular la fecha y hora de vencimiento respetando
  los horarios de la zona azul.
- Generar un número de ticket verificable.
- Mostrar el ticket por pantalla.

### 🚨 2. Anular una multa
- Anulación por exceder el tiempo permitido (3,00 €).
- Anulación por carecer de ticket (6,00 €).
- Verificación del número de ticket cuando corresponda.
- Generación de un justificante de anulación.

### 🕒 3. Consultar información
- Fecha y hora actual.
- Tarifas de estacionamiento.
- Horarios de funcionamiento de la zona azul.

### 🔐 4. Menú de administrador
Acceso protegido mediante usuario y contraseña.

- Consultar los ingresos y tickets generados.
- Consultar los ingresos por multas y multas anuladas.
- Apagar el programa.

## 💰 Tarifas

| Duración | No residentes | Residentes |
|----------|---------------|------------|
| 00:15:00 | 0,25 € | 0,10 € |
| 00:29:50 | 0,45 € | 0,20 € |
| 00:59:31 | 0,85 € | 0,40 € |
| 01:29:12 | 1,25 € | 0,60 € |
| 02:00:00 | 1,70 € | 0,80 € |

**Nota:** El parquímetro no devuelve cambio.

## 📅 Horarios

| Día | Horario |
|-----|---------|
| Lunes a viernes | 09:00 - 14:00 / 17:00 - 21:00 |
| Sábados | 09:00 - 14:00 |
| Domingos | Cerrado |

El tiempo de estacionamiento únicamente se consume
durante el horario de funcionamiento de la zona azul.


## 🧪 Simulación de fechas

El programa permite simular fechas y horas diferentes
a las actuales para comprobar el cálculo de vencimiento
de los tickets.

## 📂 Contenido del repositorio

- Código fuente del programa (.java).
- Manual de usuario (.pdf).
- README.md.
