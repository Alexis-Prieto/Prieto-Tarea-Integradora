package com.prieto.tecsupfit.data

data class GymClass(
    val id: Int,
    val name: String,
    val time: String,
    val room: String,
    val duration: String = "45 min",
    val description: String,
    val availableSpots: Int,
    val totalSpots: Int,
    val filterTag: String = "Hoy"
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
            description = "Entrenamiento de movilidad y flexibilidad corporal.",
            availableSpots = 10,
            totalSpots = 12,
            filterTag = "Hoy"
        ),
        GymClass(
            id = 2,
            name = "Cross Training",
            time = "6:00 pm",
            room = "Sala 1",
            description = "Entrenamiento funcional de alta intensidad. Cupos limitados.",
            availableSpots = 8,
            totalSpots = 12,
            filterTag = "Hoy"
        ),
        GymClass(
            id = 3,
            name = "Spinning",
            time = "7:30 pm",
            room = "Sala 3",
            description = "Ejercicio cardiovascular de alta intensidad sobre bicicleta estática.",
            availableSpots = 5,
            totalSpots = 10,
            filterTag = "Hoy"
        )
    )
    val sampleReservations = listOf(
        ReservationItem(
            id = 1,
            className = "Yoga funcional",
            schedule = "Hoy, 7:00 am",
            status = "Confirmada"
        ),
        ReservationItem(
            id = 2,
            className = "Spinning",
            schedule = "Ayer, 7:30 pm",
            status = "Completada"
        )
    )
}