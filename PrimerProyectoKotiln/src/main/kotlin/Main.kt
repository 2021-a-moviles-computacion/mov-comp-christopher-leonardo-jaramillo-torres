import java.util.*
import kotlin.collections.ArrayList

fun main() {
    //println("Hola mundo")

    // Declaración de variales

    var edadAlumno = 23

    //edadAlumno = 2.3

    var sueldo: Double = 20.5

    //Variables mutables

    var edadMascota: Int = 2

    edadMascota = 5

    edadMascota = 8

    // Variables inmutables

    val numeroCedula = 1726817719

    //numeroCedula = 0

    // Tipos de variables

    val nombre: String = "Christopher"
    val estatura: Double = 1.68
    val estadoCivil: Char = 'S'
    val fechaNacimiento: Date = Date()

    // Condicionales

    if (true) {
        // verdadero
    } else {
        // falso
    }

    // switch stado -> s -> c ::

    /*  val estadoCivilwhen: String = "S"
      when (estadoCivilwhen) {
          ("S") -> {
              println("Feliz")
          }
          "C" -> {
              println("Triste")
          }
          "UN" -> println("Habler")
          else -> println("No reconocido")
      }

      // If else de una sola linea

      val coqueteo = if (estadoCivilwhen == "S") "Si" else "No"

      imprimirNombre("Christopher")

      calcularSueldo(300.2)
      calcularSueldo(300.2, 14.00)
      calcularSueldo(300.2, 14.00, 25.00)

      // Parametros nombrados -> se puede elegir el orden con el que se envia los parametros, la diferencia es el =

      calcularSueldo(
          bonoEspecial = 15.00,
          sueldo = 150.00
      )

      calcularSueldo(
          bonoEspecial = 15.00,
          sueldo = 150.00
      )*/
    // Tipos de arreglos

    // Arreglos estaticos No podemos modificar el arreglo, no se puede añadir valores

    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)

    // Arreglos dinamicos -> si se puede añadir valores

    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3, 4)

    arregloDinamico.add(5)
    arregloDinamico.add(6)

    // Operadores de los arreglos, sirven para los dos tipos de arreglos. NO utilizar for ni while. Todos los operadores tiene
    // algo que devolver

    // For each -> Unit -> Ayuda a interar un arreglo

    /*   val respuestaForEach: Unit = arregloDinamico
           .forEach { valorActual: Int ->
               println("Valor actual: ${valorActual}")
           }

       println(respuestaForEach)

       // it: Int -> El valor o los valores que van a llegar a esta funcion si solo se recive un parametro es el it

       arregloDinamico.forEachIndexed { indice: Int, valorActual: Int ->
           println("valor ${valorActual} Indice: ${indice}")
       }

       // Map -> muta el arreglo (cambiar el arreglo )
       // 1) Enviemos el nuevo valor de la iteracion
       // 2) Nos devuelve un numero arreglo con los valores modificados

       val respuestaMpa: List<Double> = arregloDinamico
           .map { valorActua: Int ->
               return@map valorActua.toDouble() + 100.00
           }

       //arregloDinamico.map { it + 15 }

       println(respuestaMpa)

       // Filter -> filtra el arrego
       // 1) Devolver una expresion (True o False)
       // 2) Nuevo arreglo filtrado

       val respuestaFilter: List<Int>  = arregloDinamico
           .filter{ valorActual: Int ->
           val mayoresCinco: Boolean = valorActual > 2
           return@filter mayoresCinco
       }

       println(respuestaFilter)*/

/*    // OR AND
    // OR -> ANY (ALGUNO CUMPLE)
    // AND -> ALL (TODOS CUMPLEN)

    val respuestaAny: Boolean = arregloDinamico
        .any{valorActual: Int ->
        return@any (valorActual > 5)
        }

    println(respuestaAny)

    val respuestaAll: Boolean = arregloDinamico
        .all{valorActual: Int ->
            return@all (valorActual > 5)
        }

    println(respuestaAll)

    // Reduce -> Devuelve un valor acumulado de las iteraciones ; Se especifica el valor en que comienza

    val respuestaReduceMas: Int = arregloDinamico
        .reduce{ // siempre empieza en 0, normalmente se indica donde inicia el reduce
            acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) // logica que se desea acumular
        }
    println(respuestaReduceMas)

    // Operador Fold

    val arregloDanio = arrayListOf<Int>(12,15,8,10)

    val respuestaReduceFold = arregloDanio
        .fold(100, {acumulado, valorActual ->
            return@fold acumulado - valorActual
        })

    println(respuestaReduceFold)

    val vidaActual: Double = arregloDinamico
        .map { it * 2.3 } // devuelve un arreglo
        .filter { it > 2 } // devuelve un arreglo
        .fold(100.00, { acc, i -> acc - i }) // devuelve un valor -> ya no se puede aplicar un arreglo
        .also { println(it) } // imprime un arreglo

    println("Vida actual: ${vidaActual}")*/


    ////////////////////////////////////////////////////////////

    val ejemploUno = Suma(1, 2)

    val ejemploDos = Suma(null, 2)

    val ejemploTres = Suma(1, null)

    println(ejemploUno.sumer())
    println(Suma.historialSumas)
    println(ejemploDos.sumer())
    println(Suma.historialSumas)
    println(ejemploTres.sumer())
    println(Suma.historialSumas)

} // Fin de bloque Main


// Unit -> NO  devuelve nada. Se puede omitir cuando no se devuelve nada.
fun imprimirNombre(nombre: String): Unit {
    println("Nombre : ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, // parametro requerido
    tasa: Double = 12.00, // Esta parte es opcional cuando se iguala
    bonoEspecial: Double? = null, //Opcional, puede ser nulo -> nullable
): Double {
    // String -> String?
    // Int -> Int?
    // Date -> Date?

    if (bonoEspecial == null) { // Indentar -> ctrl + a -> ctrl + alt + l
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) + bonoEspecial
    }
}

////// Clases

abstract class NumeroJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int
    ) {
        numeroUno = uno
        numeroDos = dos
        println("Numeros inicializados.")
    }

}

abstract class Numeros( // Constructor primario
    protected var numeroUno: Int, // propiedad
    protected var numeroDos: Int // propiedad
) {
    init {// bloque de inicio del contructor primario
        println("Inizializar")
    }
}

class Suma(
    // Esto es mi construcctor primario
    uno: Int, // parametro requerido
    dos: Int, // parametro requerido, sin modificador de accceso
) : Numeros(uno, dos) // aqui le enviamos los datos al constructor de la clase de la que vamos a heredar. (super)
{
    init {
        // this.numeroUno
        // this.numeroDos
        // this.uno -> no existe
        // this.dos -> no existe
    }

    constructor(// cambianla forma en que los valores reciben parametros
        uno: Int?,
        dos: Int
    ) : this(// llamada al constructor primario
        if (uno == null) 0 else uno, dos
    )

    constructor(// cambianla forma en que los valores reciben parametros
        uno: Int,
        dos: Int?
    ) : this(// llamada al constructor primario
        uno, if (dos == null) 0 else dos
    )

    public fun sumer(): Int { // no es necesario utilizar el public en la declaracion de la funcion
        val total: Int = numeroUno + numeroDos // No es necesario utilizar el this
        agregarHistorial(total)
        return total
    }

    // Singleton, propiedades o metodos propios de la clase que no aparecen en la isntancia.

    companion object{
        val historialSumas = arrayListOf<Int>()

        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
            println(historialSumas)
        }
    }
}