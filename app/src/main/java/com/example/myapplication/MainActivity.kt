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

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.time.format.TextStyle


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
                    MyApp()
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
                "configuracion" -> Screen2Content()
                "Herramientas" -> Screen3Content()

                else -> HomeScreenContent()
            }
        }
    }
}

@Composable
fun Menusuperior(screen: String, onOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Home", "Opción 1", "Opción 2", "Opción 3")
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
    // Contenido de la pantalla de inicio aquí
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Screen1Content() {


    // Contenido de la pantalla 1 aquí
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Screen2Content() {
    //ConfiguracionContent()
}


/**

@Composable
fun ConfiguracionContent() {
    // Estado para el idioma seleccionado
    var idiomaSeleccionado by remember { mutableStateOf("Español") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Configuración")

        // Función para crear un botón de idioma
        fun IdiomaButton(idioma: String) {
            Button(
                onClick = { idiomaSeleccionado = idioma },
                enabled = idiomaSeleccionado != idioma
            ) {
                Text(text = idioma)
            }

        }

        // Botones de idioma
        IdiomaButton("Español")
        IdiomaButton("Inglés")

        // Botón para cerrar sesión
        Button(onClick = { /* Acción al cerrar sesión */ }) {
            Text(text = "Cerrar Sesión")
        }

        // Opción para activar/desactivar notificaciones
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Notificaciones")
            Spacer(modifier = Modifier.width(16.dp))
            // Aquí puedes agregar un Switch o Checkbox para activar/desactivar las notificaciones
        }
    }
}
**/

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Screen3Content() {
    // Contenido de la pantalla 3 aquí
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun login() {
    // Contenido de la pantalla de inicio de sesión aquí
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


