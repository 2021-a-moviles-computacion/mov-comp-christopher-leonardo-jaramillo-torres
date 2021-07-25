import java.awt.Desktop
import java.awt.Dimension
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel

class InterfazTorneo : JFrame() {

    init {
        setLocationRelativeTo(null)
        this.size = Dimension(500, 500)
        var contenedor = JPanel()
        contenedor.layout = null
        this.contentPane.add(contenedor)
        var añadirEquipo = JButton("Añadir nuevo equipo")
        añadirEquipo.setBounds(150, 100, 200, 50)
        var verEquipos = JButton("Ver equipos")
        verEquipos.setBounds(150, 200, 200, 50)
        var torneo = JButton("Empezar TORNEO")
        torneo.setBounds(150, 300, 200, 50)

        contenedor.add(añadirEquipo)
        contenedor.add(verEquipos)
        contenedor.add(torneo)

        var mouse: MouseListener = object : MouseListener {
            override fun mouseClicked(e: MouseEvent?) {
                añadirEquipos()
            }

            override fun mouseEntered(e: MouseEvent?) {
            }

            override fun mouseExited(e: MouseEvent?) {
            }

            override fun mousePressed(e: MouseEvent?) {
            }

            override fun mouseReleased(e: MouseEvent?) {
            }
        }

        var mouse1: MouseListener = object : MouseListener {
            override fun mouseClicked(e: MouseEvent?) {
                verEquipo()
            }//

            override fun mouseEntered(e: MouseEvent?) {
            }

            override fun mouseExited(e: MouseEvent?) {
            }

            override fun mousePressed(e: MouseEvent?) {
            }

            override fun mouseReleased(e: MouseEvent?) {
            }
        }

        var mouse2: MouseListener = object : MouseListener {
            override fun mouseClicked(e: MouseEvent?) {
                var nombreFichero = "D:\\Torneo.txt"
                var fichero = File(nombreFichero)
                var equipos = InterfazEquipo
                var abrir = Desktop.getDesktop()
                var datos: String = ""
                equipos.equipos.forEach {
                    datos += "Nombre del equipo: " + it.nombreEquipo + "\n"
                    datos += "Colores del equipo: " + it.colorEquipo + "\n"
                    datos += "Año de fundación: " + it.añoFundacion + "\n"
                    datos += "Dirigente del equipo: " + it.dirigente + "\n"
                    datos += "Técnico del equipo: " + it.tecnico + "\n"
                    datos += "Fecha de inscripción: " + equipos.fechaInscripcion.toString() + "\n"
                    datos += "\tNombre del jugador \t Apodo del jugador \t Estatura del jugador \t Edad del jugador \t Penalización" + "\n"
                    it.jugadores.forEach {
                        datos += "\t" + it.nombreJugador + "\t" + it.apodoJugador + "\t" + it.estaturaJugador + "\t" + it.edadJugador + "\t" + it.penalizacionJugador + "\n"
                    }
                }

                fichero.writeText(datos)
                abrir.open(fichero)


            }

            override fun mouseEntered(e: MouseEvent?) {
            }

            override fun mouseExited(e: MouseEvent?) {
            }

            override fun mousePressed(e: MouseEvent?) {
            }

            override fun mouseReleased(e: MouseEvent?) {
            }
        }

        añadirEquipo.addMouseListener(mouse)
        verEquipos.addMouseListener(mouse1)
        torneo.addMouseListener(mouse2)

    }

    public fun verEquipo() {
        var verEquipos = InterfazEquipo()
        verEquipos.isVisible = true
        verEquipos.btnSigEquipo.isEnabled = true
        verEquipos.btnAntEquipo.isEnabled = true
        verEquipos.btnActualizar.isEnabled = true
        verEquipos.btnEliminar.isEnabled = true
        verEquipos.btnIngresarEquipo.isEnabled = false
        verEquipos.btnverJugadores.isEnabled = true

        this.isVisible = false
    }

    public fun añadirEquipos() {
        var nuevoEquipo = InterfazEquipo()
        nuevoEquipo.isVisible = true
        this.isVisible = false
    }

}