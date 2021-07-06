import java.awt.Dimension
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.util.*
import javax.swing.*
import kotlin.collections.ArrayList

class InterfazEquipo(): JFrame() {


    companion object {
        public var equipos = arrayListOf<Equipo>()
        public var fechaInscripcion: Date = Date()
    }


    var btnSigEquipo = JButton(">")
    var btnAntEquipo = JButton("<")
    var btnverJugadores = JButton("Ver jugadores")
    var btnEliminar = JButton("Eliminar")
    var btnIngresarEquipo = JButton("Ingresar Equipo")
    var labelTitulo = JLabel("INGRESE LOS DATOS DEL EQUPO")
    var labelNombreEquipo = JLabel("Ingresar el nombre del equipo: ")
    var textNombreEquipo = JTextField()
    var labelColor = JLabel("Ingrese el/los colores del equipo: ")
    var textColor = JTextField()
    var labelAnio = JLabel("Ingrese el año de fundación: ")
    var textAnio = JTextField()
    var labelDirigente = JLabel("Ingrese el dirigente del equipo: ")
    var textDirigente = JTextField()
    var labelTecnico = JLabel("Ingrese el técnico del equipo: ")
    var textTecnico = JTextField()
    var btnCancelar = JButton("Cancelar")
    var btnActualizar = JButton("Actualizar")

    var contador = 0

    init {
        /*var equipo1 = Equipo("a","a",1,"a","a")
        var equipo2 = Equipo("b","b",2,"b","b")
        var equipo3 = Equipo("c","c",3,"c","c")
        equipos.add(equipo1)
        equipos.add(equipo2)
        equipos.add(equipo3)*/

        setLocationRelativeTo(null)
        this.size = Dimension(580, 430)
        var contenedor = JPanel()
        contenedor.layout = null
        this.contentPane.add(contenedor)

        btnAntEquipo.setBounds(250, 0, 50,25)
        btnSigEquipo.setBounds(300, 0, 50,25)
        btnverJugadores.setBounds(350,0,125,25)
        labelTitulo.setBounds(50,0, 300,25)
        labelNombreEquipo.setBounds(50,50,300,25)
        textNombreEquipo.setBounds(250, 50, 200, 25)
        labelColor.setBounds(50, 100,300,25)
        textColor.setBounds(250,100,200,25)
        labelAnio.setBounds(50,150,300,25)
        textAnio.setBounds(250,150,200,25)
        labelDirigente.setBounds(50, 200, 300, 25)
        textDirigente.setBounds(250,200,200,25)
        labelTecnico.setBounds(50, 250,300,25)
        textTecnico.setBounds(250,250,200,25)
        btnIngresarEquipo.setBounds(50,300,125,50)
        btnCancelar.setBounds(200,300,100,50)
        btnActualizar.setBounds(330,300,100,50)
        btnEliminar.setBounds(450,300,100,50)
        btnActualizar.isEnabled = false
        btnSigEquipo.isEnabled = false
        btnEliminar.isEnabled = false
        btnAntEquipo.isEnabled = false
        btnverJugadores.isEnabled = false

        contenedor.add(btnAntEquipo)
        contenedor.add(btnSigEquipo)
        contenedor.add(btnverJugadores)
        contenedor.add(labelTitulo)
        contenedor.add(labelNombreEquipo)
        contenedor.add(textNombreEquipo)
        contenedor.add(labelColor)
        contenedor.add(textColor)
        contenedor.add(labelAnio)
        contenedor.add(textAnio)
        contenedor.add(labelDirigente)
        contenedor.add(textDirigente)
        contenedor.add(labelTecnico)
        contenedor.add(textTecnico)
        contenedor.add(btnIngresarEquipo)
        contenedor.add(btnCancelar)
        contenedor.add(btnActualizar)
        contenedor.add(btnEliminar)

        var mouse1: MouseListener = object : MouseListener {
            override fun mouseClicked(e: MouseEvent?) {
                volverInicio()
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
                crearEquipo(textNombreEquipo.text,textColor.text,textAnio.text.toInt(),textDirigente.text,textTecnico.text,equipos)

                textNombreEquipo.text = ""
                textColor.text = ""
                textAnio.text = ""
                textDirigente.text = ""
                textTecnico.text = ""

                JOptionPane.showMessageDialog(null, "Equipo ingresado correctamente")

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

        var mouse3: MouseListener = object : MouseListener {
            override fun mouseClicked(e: MouseEvent?) {
                sigEquipos()
                println("El contador esta en: ${contador}")

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

        var mouse4: MouseListener = object : MouseListener {
            override fun mouseClicked(e: MouseEvent?) {
                antEquipos()
                println("El contador esta en: ${contador}")
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

        var mouse5: MouseListener = object : MouseListener {
            override fun mouseClicked(e: MouseEvent?) {
                println("El contador esta en antes de actualizar : ${contador}")
                actualizarEquipo()

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

        var mouse6: MouseListener = object : MouseListener {
            override fun mouseClicked(e: MouseEvent?) {
                verJugadores()

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

        var mouse7: MouseListener = object : MouseListener {
            override fun mouseClicked(e: MouseEvent?) {
                eliminarEquipo()

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

        btnCancelar.addMouseListener(mouse1)
        btnIngresarEquipo.addMouseListener(mouse2)
        btnSigEquipo.addMouseListener(mouse3)
        btnAntEquipo.addMouseListener(mouse4)
        btnActualizar.addMouseListener(mouse5)
        btnverJugadores.addMouseListener(mouse6)
        btnEliminar.addMouseListener(mouse7)
    }

    public fun verJugadores(){
        var jugadores: InterfazVerJugadores
        //println("contador para ver los jugadores: ${contador}")
        if ( contador == 0) {
            jugadores = InterfazVerJugadores(equipos[0].jugadores)
            jugadores.isVisible = true

            //println("Entro a la primera parte")
        }else{
            jugadores = InterfazVerJugadores(equipos[contador-1].jugadores)
            jugadores.isVisible = true
            //println("Entro a la segunda parte")
        }
    }

    public fun volverInicio(){
        this.isVisible = false
        var torneo = InterfazTorneo()
        torneo.isVisible = true
        contador = 0
    }

    public fun crearEquipo(
        nombre: String,
        color: String,
        año: Int,
        dirigent: String,
        tecnic: String,
        equipos: ArrayList<Equipo>
    ){
        var nuevoEquipo = Equipo(nombre,color,año, dirigent,tecnic)
        equipos.add(nuevoEquipo)

        var jugadores = InterfazJugador(nuevoEquipo)
        this.isVisible = false
        jugadores.isVisible = true
    }

    public fun sigEquipos(){
        if(contador >= 0 && contador < equipos.size) {
            textNombreEquipo.text = equipos[contador].nombreEquipo
            textColor.text = equipos[contador].colorEquipo
            textAnio.text = equipos[contador].añoFundacion.toString()
            textDirigente.text = equipos[contador].dirigente
            textTecnico.text = equipos[contador].tecnico
            //println("contador para sig: ${contador}")
            contador += 1
        }else if (equipos.size == 0){
            JOptionPane.showMessageDialog(null, "No hay equipos registrados")
        }else if (contador == equipos.size){
            contador = 0
        }else if(contador < 0){
            contador = 0
        }
    }

    public fun antEquipos(){
        if(contador >= 0 && contador < equipos.size) {
            textNombreEquipo.text = equipos[contador].nombreEquipo
            textColor.text = equipos[contador].colorEquipo
            textAnio.text = equipos[contador].añoFundacion.toString()
            textDirigente.text = equipos[contador].dirigente
            textTecnico.text = equipos[contador].tecnico
            //println("contador para antes: ${contador}")
            contador -= 1
        }else if (equipos.size == 0){
            JOptionPane.showMessageDialog(null, "No hay equipos registrados")
        }else if(contador < 0){
            contador = 0
        }else if (contador == equipos.size){
            contador = 0
        }
    }

    public fun actualizarEquipo(){

        //print("Contador para la actualizacion: ${contador} ")
        if(contador < 0) {
            equipos[0].nombreEquipo = textNombreEquipo.text
            equipos[0].colorEquipo = textColor.text
            equipos[0].añoFundacion = textAnio.text.toInt()
            equipos[0].dirigente = textDirigente.text
            equipos[0].tecnico = textTecnico.text
        }else{

            equipos[contador-1].nombreEquipo = textNombreEquipo.text
            equipos[contador-1].colorEquipo = textColor.text
            equipos[contador-1].añoFundacion = textAnio.text.toInt()
            equipos[contador-1].dirigente = textDirigente.text
            equipos[contador-1].tecnico = textTecnico.text
        }

        contador = 0

        textNombreEquipo.text = ""
        textColor.text = ""
        textAnio.text = ""
        textDirigente.text = ""
        textTecnico.text = ""

        JOptionPane.showMessageDialog(null, "Equipo actualizado correctamente")
    }

    public fun eliminarEquipo(){
        println("Contador para la eliminacion: ${contador -1} ")
        if(contador < 0) {
            equipos.removeAt(0)
            contador = 0
        }else{

            equipos.removeAt(contador-1)
            contador = 0
        }

        textNombreEquipo.text = ""
        textColor.text = ""
        textAnio.text = ""
        textDirigente.text = ""
        textTecnico.text = ""

        JOptionPane.showMessageDialog(null, "Equipo eliminado correctamente")
    }

}