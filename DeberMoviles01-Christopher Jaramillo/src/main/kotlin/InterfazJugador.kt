import java.awt.Dimension
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.*

class InterfazJugador(equipoSelec: Equipo): JFrame() {


    init {

        this.size = Dimension(500, 420)
        var contenedor = JPanel()
        contenedor.layout = null
        this.contentPane.add(contenedor)
        setLocationRelativeTo(null)

        var labelTitulo = JLabel("INGRESE DATOS DE JUGADORES" )
        var labelNombreJugador = JLabel("Ingresar el nombre del jugador: ")
        var textNombreJugador = JTextField()
        var labelApodo = JLabel("Ingrese el apodo del jugador: ")
        var textApodo = JTextField()
        var labelEstatura = JLabel("Ingrese la estatura del jugador: ")
        var textEstatura = JTextField()
        var labelPenalizacion = JLabel("Seleccione una penalización: ")
        var comboBoxPenalizacion = JComboBox<String>()
        comboBoxPenalizacion.addItem("Si")
        comboBoxPenalizacion.addItem("No")
        var labelEdad = JLabel("Ingrese la edad del jugador: ")
        var textEdad = JTextField()
        var btningresarJugador = JButton("Ingresar jugador")
        var btnCancelar = JButton("Regresar")

        labelTitulo.setBounds(50,0, 300,25)
        labelNombreJugador.setBounds(50,50,300,25)
        textNombreJugador.setBounds(250, 50, 200, 25)
        labelApodo.setBounds(50, 100,300,25)
        textApodo.setBounds(250,100,200,25)
        labelEstatura.setBounds(50,150,300,25)
        textEstatura.setBounds(250,150,200,25)
        labelEdad.setBounds(50, 200, 300, 25)
        textEdad.setBounds(250,200,200,25)
        labelPenalizacion.setBounds(50, 250,300,25)
        comboBoxPenalizacion.setBounds(250,250,200,25)
        btningresarJugador.setBounds(70,300,150,50)
        btnCancelar.setBounds(270,300,150,50)

        contenedor.add(labelTitulo)
        contenedor.add(labelNombreJugador)
        contenedor.add(textNombreJugador)
        contenedor.add(labelApodo)
        contenedor.add(textApodo)
        contenedor.add(labelEstatura)
        contenedor.add(textEstatura)
        contenedor.add(labelEdad)
        contenedor.add(textEdad)
        contenedor.add(labelPenalizacion)
        contenedor.add(comboBoxPenalizacion)
        contenedor.add(btningresarJugador)
        contenedor.add(btnCancelar)

        var mouse1: MouseListener = object : MouseListener {
            override fun mouseClicked(e: MouseEvent?) {
                var opcion = comboBoxPenalizacion.selectedItem
                var valor: Boolean = false

                if(opcion.equals("Si") == true) {
                    valor = true
                }

                añadirJugador(equipoSelec, textNombreJugador.text, textApodo.text, textEstatura.text.toDouble(), valor, textEdad.text.toInt())

                textNombreJugador.text = ""
                textApodo.text = ""
                textEstatura.text = ""
                textEdad.text = ""

                JOptionPane.showMessageDialog(null, "Jugador ingresado correctamente")

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

        var mouse2: MouseListener = object : MouseListener {
            override fun mouseClicked(e: MouseEvent?) {
                volverInicio()
                enviarDatosArchivo(equipoSelec)
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

        btningresarJugador.addMouseListener(mouse1)
        btnCancelar.addMouseListener(mouse2)

    }

    fun añadirJugador(
        equipo: Equipo,
        nombre: String,
        apodo: String,
        estatura: Double,
        penalizacion: Boolean,
        edad: Int
    ){
        var jugador = Jugador(nombre,apodo,estatura,penalizacion,edad)
        equipo.jugadores.add(jugador)
    }

    public fun volverInicio(){
        this.isVisible = false
        var torneo = InterfazTorneo()
        torneo.isVisible = true
    }

    public fun enviarDatosArchivo(equipoSelec: Equipo): Equipo{
        return equipoSelec
    }

   }