class Persona(val peso: Double, var altura: Double) {

    val imc: Double
        get() = peso / (altura * altura)

    var nombre: String? = null

    constructor(nombre: String, peso: Double, altura: Double) : this(peso, altura){
        this.nombre = nombre
    }

    fun saludar(): String{
        return "Hola, $nombre"
    }

    fun alturaEncimaMedia(): Boolean{
            return (altura >= 1.75)
    }

    fun pesoEncimaMedia():Boolean{
        return (peso >= 70)
    }

    fun obtenerDescImc():String{
      var mensaje = ""

      when {
          imc < 18.5 -> mensaje += "(Peso insuficiente)"
          imc < 24.9 -> mensaje += "(Peso saludable)"
          imc < 29.9 -> mensaje += "(Peso con sobrepeso)"
          imc > 30.0 -> mensaje += "(Peso obesidad)"
      }

      return mensaje
    }

    fun obtenerDesc():String{
        return ("$nombre con una altura de $altura m ${if(alturaEncimaMedia()){"(Por encima de la media)"} else {"(Por debajo de la media)"}}" +
                " y un peso $peso kg ${if(pesoEncimaMedia()){"(Por encima de la mesa"} else {"(Por debajo de la media)"}} " +
                "tiene un IMC de $imc ${obtenerDescImc()}")
    }


    override fun toString(): String {
        return "Nombre: $nombre, Peso: $peso kg, Altura: $altura m, IMC: ${"%.2f".format(imc)}"
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Persona) return false
        return this.peso == other.peso && this.altura == other.altura
    }
}


fun main() {

    val persona1 = Persona(64.0, 1.86)
    val persona2 = Persona("Antonio", 54.0, 1.60)
    val persona3 = Persona("Adrián", 70.0, 1.75)


    println("Ingrese un nombre para la persona 1:")
    var nuevoNombre: String?
    do {
        nuevoNombre = readLine()
        if (nuevoNombre.isNullOrBlank()) {
            println("**ERROR** Inténtelo de nuevo")
        }
    } while (nuevoNombre.isNullOrBlank())

    persona1.nombre = nuevoNombre
    println("Para la persona 1: \nNombre: ${persona1.nombre}, Peso: ${persona1.peso}kg y Altura: ${persona1.altura}m")


    println("Para la persona 3: \nPeso: ${persona3.peso}kg, Altura: ${persona3.altura}m y IMC: ${persona3.imc}")

    persona3.altura = 1.80
    println("Para la persona 3: \nPeso: ${persona3.peso}kg, Altura: ${persona3.altura}m y IMC: ${persona3.imc}")

    persona2.altura = persona3.altura

    println(persona2)
    println(persona3)

    if (persona2 == persona3) {
        println("La persona 2 y la persona 3 son iguales")
    } else {
        println("La persona 2 y la persona 3 no son iguales")
    }

    var personas: Map<String, Persona> = mapOf(
        "persona1" to Persona("Fran", 80.0, 1.65),
        "persona2" to Persona("Inda", 100.0, 1.50),
        "persona3" to Persona("Ángel", 40.0, 1.85),
        "persona4" to Persona("Simón", 76.0, 2.00)
    )

    for(persona in personas.keys){
        println(personas[persona]?.saludar())
        println(personas[persona]?.obtenerDesc())
    }

}