package com.prieto.tecsupfit.data

data class GymClass(
    val id: Int,
    val name: String,
    val time: String,
    val room: String,
    val duration: String,
    val description: String,
    val availableSlots: Int,
    val totalSlots: Int,
    val filterTag: String
)
data class ReservationItem(
    val id: Int,
    val className: String,
    val schedule: String,
    val status: String
)
object GymRepository {
    val dateFilters = listOf("Hoy", "Esta semana")

    val sampleClasses = listOf(
        GymClass(
            id = 1,
            name = "Yoga funcional",
            time = "7:00 am",
            room = "Sala 2",
            duration = "45 min",
            description = "Mejora tu flexibilidad, postura y movilidad articular con ejercicios guiados.",
            availableSlots = 5,
            totalSlots = 10,
            filterTag = "Hoy"
        ),
        GymClass(
            id = 2,
            name = "Cross Training",
            time = "6:00 pm",
            room = "Sala 1",
            duration = "45 min",
            description = "Entrenamiento funcional de alta intensidad. Cupos limitados.",
            availableSlots = 8,
            totalSlots = 12,
            filterTag = "Hoy"
        ),
        GymClass(
            id = 3,
            name = "Spinning",
            time = "7:30 pm",
            room = "Sala 3",
            duration = "50 min",
            description = "Cardio de alta quema calórica sobre bicicleta estática.",
            availableSlots = 3,
            totalSlots = 15,
            filterTag = "Hoy"
        ),
        GymClass(
            id = 4,
            name = "Power Pilates",
            time = "8:00 am",
            room = "Sala 2",
            duration = "60 min",
            description = "Fortalecimiento de core y corrección postural mediante ejercicios controlados.",
            availableSlots = 6,
            totalSlots = 10,
            filterTag = "Esta semana"
        )
    )
    val sampleReservations = listOf(
        ReservationItem(1, "Cross Training", "Hoy, 6:00 pm", "Confirmada"),
        ReservationItem(2, "Yoga funcional", "Ayer, 7:00 am", "Completada")
    )
}