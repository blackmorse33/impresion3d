package com.example.myapplication

import android.content.Context
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
import androidx.compose.ui.text.style.TextAlign
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler


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
                "Videos" -> Screen1Content()
                "Tienda de Consumibles" -> Screen2Content()
                "Herramientas" -> Screen3Content()

                else -> HomeScreenContent()
            }
        }
    }
}

@Composable
fun Menusuperior(screen: String, onOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Home", "Videos", "Configuración", "Herramientas")
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Screen1Content() {
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
    // Contenido de la pantalla 1 aquí
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Screen2Content() {

}




@Composable
fun ConfiguracionContent() {
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
@Preview
@Composable
fun Screen3Content() {//configuracion de la app
    ConfiguracionContent()
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
            IdiomaButton("Español", idiomaSeleccionado, onIdiomaSelected)
            IdiomaButton("Inglés", idiomaSeleccionado, onIdiomaSelected)
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
                checked = notificacionesActivadas,
                onCheckedChange = { onNotificacionesToggle(it) }
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
@Composable
fun Repositorios3DSection() {
    // TODO: Puedes pasar una lista de repositorios como parámetro
    // Por ejemplo: val repositorios: List<Repositorio> = ...

    // Simulación de datos para mostrar un ejemplo
    val repositorios = listOf(
        Repositorio("https://www.thingiverse.com", R.drawable.thing, "thingiverse"),
        Repositorio("https://www.printables.com/", R.drawable.printables, "printables"),
        Repositorio("https://cults3d.com", R.drawable.cults_large, "cults3d")
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = content,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

