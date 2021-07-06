import java.awt.Dimension
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JTextArea

class InterfazVerJugadores(jugadores: ArrayList<Jugador>) : JFrame() {
    var textAreaJugadores = JTextArea()

    init {
        this.size = Dimension(450, 450)
        var contenedor = JPanel()
        contenedor.layout = null
        this.contentPane.add(contenedor)
        //var btnRegresar = JButton("Regresar")


        textAreaJugadores.setBounds(20, 20, 400, 300)
        //btnRegresar.setBounds(400,400,150,50)
        verJugadores(jugadores)

        //contenedor.add(btnRegresar)
        contenedor.add(textAreaJugadores)

    }

    public fun verJugadores(jugadores: ArrayList<Jugador>) {

        var respuesta = ""

        jugadores.forEach{
            respuesta += it.nombreJugador + " - " + it.apodoJugador + " - " + it.edadJugador + " - " + it.estaturaJugador + " - " + it.penalizacionJugador + "\n"
        }

        textAreaJugadores.text = respuesta

    }

}