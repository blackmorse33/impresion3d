package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.*
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewModel.login
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    com.example.myapplication.login()
                }
            }
        }
    }
}





@Composable
fun Inicio() {
    MyApplicationTheme {
        MediumTopAppBarExample()
    }
}

/**@Preview(showBackground = true)**/
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopAppBarExample() {

}

@Composable
fun LateralMenu(expandedState: MutableState<Boolean>) {
    DropdownMenu(
        expanded = expandedState.value,
        onDismissRequest = { expandedState.value = false }
    ) {
        DropdownMenuItem(text = { /*TODO*/ }, onClick = { /*TODO*/ })


    }
}



@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    // Contenido de tu aplicación aquí
}

@Composable
fun noticia() {

}


@Composable
fun cursos(){

}


@Composable
fun sesion(){

}


@Composable
fun registro(){

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MyApp() {
    val (screen, setScreen) = remember { mutableStateOf("Menu") }
    var idiomaSeleccionado by remember { mutableStateOf("Español") }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("3D PRINT FOR ALL") },
                actions = {
                    Menusuperior(screen = screen, onOptionSelected = setScreen)
                }
            )
        }
    ) {
        Crossfade(targetState = screen, label = "") { screen ->
            when (screen) {
                "Videos" -> Screen1Content(idiomaSeleccionado)
                "Tienda de Consumibles" -> Screen2Content(idiomaSeleccionado)
                "Configuracion" -> Screen3Content(idiomaSeleccionado)
                "Noticias" -> Screen3Content(idiomaSeleccionado)

                else -> HomeScreenContent()
            }
        }
    }
}

@Composable
fun Menusuperior(screen: String, onOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Home", "Videos", "Tienda de Consumibles", "Configuracion", "Noticias")
    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
        TextButton(onClick = { expanded = true }) {
            Text(screen)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { label ->
                DropdownMenuItem(text = { Text(label) }, onClick = {
                    onOptionSelected(label) // Actualiza la pantalla al seleccionar una opción
                    expanded = false
                })
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreenContent() {
    var selectedScreen by remember { mutableStateOf("Home") }
    Menusuperior(selectedScreen) { screen ->
        selectedScreen = screen
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Contenido de la pantalla de inicio aquí
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.impresora3d),
                    contentDescription = "imagen de prueba",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "¡Explora nuestra plataforma de impresión 3D hoy mismo!")
            }
        }

        // Repositorios 3D
        Repositorios3DSection()

        // Preguntas Frecuentes
        PreguntasFrecuentesSection()

        // Acceso a Cursos Recientes
        CursosRecientesSection()
    }
}

val videos = listOf(
    video("Share Horizons", "https://www.youtube.com/@SHAREHORIZONS","Español"),
    video("Control 3D", "https://www.youtube.com/@Control3D","Español"),
    video("Tu rincón 3D", "https://www.youtube.com/@Turincon3d","Español"),
    video("JC 3Design", "https://www.youtube.com/@JuanCarlos_3design","Español"),
    video("The Maker 3DP", "https://www.youtube.com/@TheMaker3DP","Español"),
    video("Govaju 3DPrinting", "https://www.youtube.com/@govaju3D","Español"),
    video("El canal del Señor ferrete", "https://www.youtube.com/@SrFerrete","Español"),
    video("Adafruit Industries", "https://www.youtube.com/@adafruit","Español"),
    video("3D Now", "https://www.youtube.com/@3DNow","Inglés"),
    video("Maker's Muse", "https://www.youtube.com/@MakersMuse","Inglés"),
    video("Thomas Sanladerer", "https://www.youtube.com/@MadeWithLayers","Inglés"),
    video("3D Printing Nerd", "https://www.youtube.com/@3DPrintingNerd","Inglés"),
    video("Joel Telling - 3D Printing Nerd", "https://www.youtube.com/@thenextlayer","Inglés"),
    video("I Like To Make Stuff", "https://www.youtube.com/@Iliketomakestuff","Inglés"),
    video("MatterHackers", "https://www.youtube.com/@MatterHackers","Inglés")

)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen1Content(idiomaSeleccionado: String) {
    var idiomaSeleccionado by remember { mutableStateOf("") }
    var notificacionesActivadas by remember { mutableStateOf(true) }

    Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                VideoPlayer(videos, idiomaSeleccionado)

                Text(text = "¡Explora nuestra plataforma de impresión 3D hoy mismo!")
        }
    }

}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen2Content(idiomaSeleccionado: String) {
    Card(
        modifier = Modifier
            .padding(100.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.impresora3d),
                contentDescription = "imagen de prueba",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "¡Explora nuestra plataforma de impresión 3D hoy mismo!")
        }
    }
}






@Composable
fun ConfiguracionContent(idiomaSeleccionado: String) {
    // Estado para el idioma seleccionado
    var idiomaSeleccionado by remember { mutableStateOf("Español") }

    // Estado para el control de notificaciones
    var notificacionesActivadas by remember { mutableStateOf(true) }

    ConfiguracionLista(
        idiomaSeleccionado = idiomaSeleccionado,
        onIdiomaSelected = { idiomaSeleccionado = it },
        notificacionesActivadas = notificacionesActivadas,
        onNotificacionesToggle = { notificacionesActivadas = it },
        onCerrarSesion = { /* Lógica de cierre de sesión aquí */ }
    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen3Content(idiomaSeleccionado: String) {//configuracion de la app
    ConfiguracionContent(idiomaSeleccionado)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun login(loginViewModel: login = login()) {
    // Contenido de la pantalla de inicio de sesión aquí
    val isAuthenticated by loginViewModel.isAuthenticated.observeAsState()

    if (isAuthenticated == true) {
        // Si el usuario está autenticado, navega a la pantalla principal
        MyApp()
    } else {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.impresora3d),
            contentDescription = "Login",
            contentScale = ContentScale.Crop, // Escala para llenar el espacio sin cambiar la relación de aspecto
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.TopStart) // Alinea la imagen en la parte superior
                .clip(shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
        )

        // Contenido del formulario de inicio de sesión
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Agrega el título en negrita
            Text(
                text = "3D PRINT FOR ALL",
                style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                modifier = Modifier.padding(bottom = 6.dp)
            )

            // Agrega los elementos del formulario, como EditText, Button, etc.
            // Aquí hay un ejemplo básico con un EditText y un botón de inicio de sesión

            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Usuario") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Button(
                onClick = {
                    // Agrega la lógica de autenticación aquí
                    // Puedes usar los valores de 'username' y 'password'
                    loginViewModel.signInWithEmailAndPassword(username, password)


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Iniciar Sesión")
            }

            val annotatedString = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append("¿No tienes una cuenta? ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                        append("Crea una ahora.")
                    }
                }
            }

            ClickableText(
                text = annotatedString,
                onClick = { offset ->
                    // Agrega la lógica para manejar el clic en el texto aquí
                    // Puedes, por ejemplo, navegar a la pantalla de registro
                },
                modifier = Modifier.padding(top = 16.dp)
            )
            }
        }
    }

}

@Composable
fun ConfiguracionLista(
    idiomaSeleccionado: String,
    onIdiomaSelected: (String) -> Unit,
    notificacionesActivadas: Boolean,
    onNotificacionesToggle: (Boolean) -> Unit,
    onCerrarSesion: () -> Unit
) {
    // Utiliza remember para mantener el estado de la configuración
    var idiomaSeleccionadoState by remember { mutableStateOf(idiomaSeleccionado) }
    var notificacionesActivadasState by remember { mutableStateOf(notificacionesActivadas) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Sección de idioma
        Text(text = "Idioma")
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            IdiomaButton("Español", idiomaSeleccionadoState) {
                idiomaSeleccionadoState = "Español"
                onIdiomaSelected(idiomaSeleccionadoState)
            }
            IdiomaButton("Inglés", idiomaSeleccionadoState) {
                idiomaSeleccionadoState = "Inglés"
                onIdiomaSelected(idiomaSeleccionadoState)
            }
        }

        // Sección de notificaciones
        Text(text = "Notificaciones")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Activar notificaciones")
            Spacer(modifier = Modifier.width(16.dp))
            Switch(
                checked = notificacionesActivadasState,
                onCheckedChange = {
                    notificacionesActivadasState = it
                    onNotificacionesToggle(it)
                }
            )
        }

        // Botón para cerrar sesión
        Button(onClick = { onCerrarSesion() }) {
            Text(text = "Cerrar Sesión")
        }
    }
}

@Composable
fun IdiomaButton(idioma: String, idiomaSeleccionado: String, onIdiomaSelected: (String) -> Unit) {
    Button(
        onClick = { onIdiomaSelected(idioma) },
        enabled = idiomaSeleccionado != idioma
    ) {
        Text(text = idioma)
    }
}

data class Repositorio(
    val url: String,
    val imageResource: Int,
    val nombre: String
)

data class Review(val text: String, val author: String)

data class video(val name: String, val url: String, val idioma: String)

val reviews = listOf(
    Review("Excelente aplicación.", " Usuario A"),
    Review("Muy útil para principiantes.", "Usuario B"),
    Review("Necesita mejoras en la interfaz.", "Usuario C")
)



@Composable
fun Repositorios3DSection() {
    // TODO: Puedes pasar una lista de repositorios como parámetro
    // Por ejemplo: val repositorios: List<Repositorio> = ...

    // Simulación de datos para mostrar un ejemplo
    val repositorios = listOf(
        Repositorio("https://www.thingiverse.com", R.drawable.thing, "thingiverse"),
        Repositorio("https://www.printables.com/", R.drawable.printables, "printables"),
        Repositorio("https://cults3d.com", R.drawable.cults_large, "cults3d"),
        Repositorio("https://grabcad.com/library", R.drawable.cults_large, "Grabcad")


    )

    SectionTitle("Repositorios 3D")

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        items(repositorios) { repositorio ->
            RepositorioCard(repositorio)
        }
    }
}


@Composable
fun PreguntasFrecuentesSection() {
    // TODO: Puedes agregar preguntas frecuentes aquí

    SectionTitle("Preguntas Frecuentes")

    // Contenido de preguntas frecuentes
    // ...

    // Ejemplo de un recuadro redondeado
    RoundedBox("¿Cómo puedo empezar con la impresión 3D?")
}

@Composable
fun CursosRecientesSection() {
    // TODO: Puedes agregar cursos recientes aquí

    SectionTitle("Cursos Recientes")

    // Contenido de cursos recientes
    // ...

    // Ejemplo de un recuadro redondeado
    RoundedBox("Curso Avanzado de Modelado 3D")
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = androidx.compose.ui.text.TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(bottom = 8.dp)
    )
}
@Composable
fun RepositorioCard(repositorio: Repositorio) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(100.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen cliclable para abrir el enlace
            val context = LocalContext.current
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
                    .clickable {
                        val uri = Uri.parse(repositorio.url)
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        // Lanzar la intención
                        context.startActivity(intent)
                    }
            )
            Spacer(modifier = Modifier.height(4.dp))
            // Nombre del repositorio
            Text(text = repositorio.nombre)
        }
    }
}

@Composable
fun RoundedBox(content: String) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(reviews) { review ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = review.text)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "- ${review.author}")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
@Composable
fun VideoPlayer(
    videos: List<video>,
    idiomaSeleccionado: String
) {
    val videosFiltrados = when (idiomaSeleccionado) {
        "Español" -> videos.filter { it.idioma == "Español" }
        "Inglés" -> videos.filter { it.idioma == "Inglés" }
        else -> videos
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            // Título centrado con recuadro tipo Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Canales/Channels",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        }


        itemsIndexed(videosFiltrados) { _, video ->
            // Genera un color de fondo aleatorio para cada Card
            val backgroundColor = Color(android.graphics.Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
            val context = LocalContext.current

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(color = backgroundColor)
                    .clickable {
                        // Abre la aplicación de YouTube al hacer clic
                        val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse(video.url))
                        context.startActivity(youtubeIntent)
                    }
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Box con fondo de color detrás del Icon
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(color = backgroundColor),
                        contentAlignment = Alignment.Center
                    ) {
                        // Icono de reproducción predeterminado
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White // Tinte del icono
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Nombre del canal
                    Text(text = video.name, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(text = video.idioma, fontWeight = FontWeight.Thin)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}




data class Noticia(val titulo: String, val descripcion: String, val imagenUrl: String)
/**
@Composable
fun NoticiaCard(noticia: Noticia, onNoticiaClick: (Noticia) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNoticiaClick(noticia) },

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Imagen de la noticia
            Image(
                painter = rememberImagePainter(noticia.imagenUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            // Título de la noticia
            Text(
                text = noticia.titulo,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // Descripción de la noticia
            Text(
                text = noticia.descripcion,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
**/
/**
@Composable
fun NoticiasSection(noticias: List<Noticia>, onNoticiaClick: (Noticia) -> Unit) {
    LazyColumn {
        items(noticias) { noticia ->
            NoticiaCard(noticia = noticia, onNoticiaClick = onNoticiaClick)
        }
    }
}
**/