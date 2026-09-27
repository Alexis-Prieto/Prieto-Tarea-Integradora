# Flujo de Prompts para la Construcción de la Aplicación — TECSUP Fit

## Prompt 1: 

**Lo que se le pidió:**  
Quiero configurar el sistema de diseño visual en Jetpack Compose con Material 3 para la app TECSUP Fit. Define la paleta de colores con verde principal (#1B5E4F), verde claro de fondo (#E8F5F0), fondo general blanco (#FFFFFF), tarjetas inactivas en gris muy claro (#F2F2F2) y textos en #1A1A1A y #7A7A7A. Aplica tipografía sans-serif con jerarquía clara (títulos 18-20sp bold, ítems 15-16sp semibold, texto secundario 12-13sp regular) y formas con esquinas redondeadas (12-16dp) y componentes estilo píldora. Genera el código completo de Color.kt, Type.kt y Theme.kt.

**Correcciones y ajustes realizados:**  
Se ajustó manualmente el contraste del color gris secundario (#7A7A7A) sobre fondos claros para garantizar legibilidad y accesibilidad (WCAG). Además, se corrigieron las importaciones de Material 3 en `Theme.kt` para asegurar la compatibilidad con el esquema de colores personalizado.

---

## Prompt 2: 

**Lo que se le pidió:**  
Diseña la capa de datos de TECSUP Fit siguiendo un patrón de repositorio reactivo. Define las entidades GymClass (id, nombre, instructor, hora, sala, cupos, filterTag) y Reserva (id, clase, estado enum con Confirmada, Completada, Cancelada). Implementa GymRepository utilizando mutableStateListOf para sincronizar en tiempo real el catálogo de clases y las reservas, e incluye en GymData.kt datos de prueba relevantes para las vistas "Hoy" y "Esta semana". Entrega el código completo en Kotlin.

**Correcciones y ajustes realizados:**  
La propuesta inicial incluía una lista inmutable (`listOf`). Se corrigió explícitamente para asegurar la declaración de `mutableStateListOf` en el repositorio, garantizando que las adiciones y cancelaciones de reservas propagaran los cambios automáticamente a la UI en tiempo real.

---

## Prompt 3: 

**Lo que se le pidió:**  
Implementa la estructura central de navegación para TECSUP Fit en AppNavigation.kt. Configura un NavHost centralizado con 4 pestañas en el BottomBar (Inicio, Reservas, Rutinas y Perfil), deshabilita explícitamente las animaciones de transición (EnterTransition.None / ExitTransition.None) y asegura que al navegar entre pestañas se conserve y restaure el estado usando saveState = true, restoreState = true y launchSingleTop = true. Proporciona el archivo completo.

**Correcciones y ajustes realizados:**  
Se corrigió un comportamiento donde al presionar repetidamente un icono del `BottomBar` se volvía a instanciar la pantalla. Se aseguró la presencia de `launchSingleTop = true` y la correcta evaluación del `currentDestination` con `hierarchy` de Navigation Compose.

---

## Prompt 4: 

**Lo que se le pidió:**  
Desarrolla la interfaz de PantallaInicio.kt (RF-01). Incluye un header verde bosque con esquinas inferiores redondeadas, saludo "Hola, [Nombre]" y título de sección. Agrega selecciones de filtro estilo "pill" ("Hoy" / "Esta semana") para filtrar clases en tiempo real, y una lista de tarjetas con icono circular, horario, instructor, cupos disponibles y un botón de acción para seleccionar la clase. Entrega el código completo.

**Correcciones y ajustes realizados:**  
Se ajustó la lógica del selector de filtros tipo "pill" para que la actualización del estado de selección fuera reactiva a nivel de pantalla y filtrara directamente la colección observada del repositorio, corrigiendo un fallo donde la lista no se refrescaba al cambiar entre pestañas.

---

## Prompt 5: Flujo de Detalle y Confirmación (RF-02)

**Lo que se le pidió:**  
Implementa las pantallas intermedias del flujo de reserva (PantallaDetalle.kt y PantallaConfirmacion.kt - RF-02). La pantalla de detalle debe mostrar la información extendida de la clase seleccionada con el botón "Reservar cupo". La pantalla de confirmación debe incluir un icono circular grande de éxito (check verde), el resumen del cupo reservado y el botón "Ver mis reservas" que redirija al módulo correspondiente. Proporciona ambos composables completos.

**Correcciones y ajustes realizados:**  
Se corrigió el traspaso de argumentos entre `PantallaDetalle.kt` y `PantallaConfirmacion.kt` para asegurar que el objeto `GymClass` o su identificador único mantuviera la consistencia de datos durante la transacción de reserva.

---

## Prompt 6:

**Lo que se le pidió:**  
Desarrolla el módulo de gestión de reservas en PantallaReservas.kt (RF-03). Muestra la lista de tarjetas con las reservas activas y su badge de estado ("Confirmada"). Agrega la opción de cancelar una reserva activa mediante un AlertDialog de Material 3 que indique el nombre y horario de la clase, con botones 'Sí, cancelar' y 'Volver', actualizando el estado reactivo en GymRepository al confirmar. Entrega la implementación completa.

**Correcciones y ajustes realizados:**  
Se ajustó la lógica dentro del callback de cancelación para que modificara el atributo `estado` del enum `EstadoReserva` a `Cancelada` dentro del repositorio reactivo, asegurando que la UI ocultara o reetiquetara la reserva inmediatamente y cerrara el diálogo emergente.

---

## Prompt 7: 

**Lo que se le pidió:**  
Implementa la pantalla de rutinas en PantallaRutinas.kt. Sustituye la vista temporal por un LazyColumn optimizado y conéctalo de forma reactiva con las clases marcadas bajo el filtro "Esta semana" del repositorio. Diseña las tarjetas informativas utilizando los componentes visuales e iconos definidos en el sistema de diseño. Proporciona el código fuente completo del archivo.

**Correcciones y ajustes realizados:**  
Se incluyeron composables auxiliares dentro del mismo archivo para evitar errores de compilación por símbolos no encontrados (como tarjetas de clases customizadas) y se vinculó la lista de manera reactiva mediante la etiqueta `filterTag = "Esta semana"`.

---

## Prompt 8: 

**Lo que se le pidió:**  
Desarrolla la pantalla de perfil de usuario en PantallaPerfil.kt (RF-04). Diseña una cabecera con avatar circular verde bosque e iniciales en blanco, nombre del usuario y plan activo. Agrega una fila de tarjetas de estadísticas (clases asistidas, horas entrenadas, racha) sobre fondo gris claro con bordes redondeados y la lista de ajustes personales. Proporciona la implementación completa.

**Correcciones y ajustes realizados:**  
Se corrigió la distribución responsiva del contenedor de estadísticas agregando `Modifier.weight(1f)` a cada tarjeta en la fila (`Row`), evitando desbordamientos de pantalla en dispositivos con menor resolución de ancho.

---

## Prompt 9: 

**Lo que se le pidió:**  
Realiza los ajustes finales de estabilidad en la navegación y el estado global de TECSUP Fit. En AppNavigation.kt, ajusta la acción de confirmación para que ejecute un desapilamiento limpio (popBackStack) de las pantallas Detalle y Confirmación, evitando que el usuario quede atascado al presionar la pestaña Inicio. Garantiza la generación de IDs incrementales únicos en GymRepository y asegura la compatibilidad estricta de tipos de transición. Entrega las correcciones finales en los archivos afectados.

**Correcciones y ajustes realizados:**  
Se solucionó el problema de navegación mediante `popBackStack(PantallaInicioRoute, inclusive = false)` al completar una reserva. Esto eliminó las pantallas intermedias del historial y corrigió una incompatibilidad de tipos pasando explícitamente un `ExitTransition` en el parámetro `popExitTransition`.
