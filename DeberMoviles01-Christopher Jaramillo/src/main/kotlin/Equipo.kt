import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JOptionPane

class Equipo(
    nombre: String,
    color: String,
    año: Int,
    dirigent: String,
    tecnic: String
) {
    var jugadores: ArrayList<Jugador> = arrayListOf()
    var nombreEquipo = nombre
    var colorEquipo = color
    var añoFundacion = año
    var dirigente = dirigent
    var tecnico = tecnic

}