
# BookUsers

**Sistema de registro y gestión de usuarios desarrollado en Java 21 con Swing.**

BookUsers es una aplicación de escritorio creada como proyecto académico de Programación Orientada a Objetos (POO).

Permite registrar usuarios, consultar sus datos, visualizar fotografías y generar fichas individuales en formato PDF.

## 1. Tecnologías utilizadas

- Java 21
- Java Swing
- IntelliJ IDEA
- IntelliJ GUI Designer
- Apache Maven
- Apache PDFBox 3.0.3
- Git y GitHub

## 2. Funcionalidades

### Registro de usuarios

La aplicación permite registrar:

- Tipo de identificación.
- Número de identificación.
- Primer nombre.
- Segundo nombre (opcional).
- Primer apellido.
- Segundo apellido (opcional).
- Nombre de usuario generado automáticamente.
- Contraseña.
- Teléfono.
- Correo electrónico.
- Fotografía JPG o PNG (opcional).

### Validaciones

El formulario comprueba:

- Campos obligatorios.
- Correo electrónico con el carácter @.
- Identificaciones numéricas cuando corresponde.
- Teléfonos que contienen únicamente dígitos.
- Contraseñas con mínimo ocho caracteres, mayúscula, minúscula, número y símbolo.
- Identificaciones duplicadas del mismo tipo.

### Generación automática del username

El nombre de usuario se construye utilizando:

1. Primera letra del primer apellido.
2. Primera letra del segundo apellido, cuando exista.
3. Primer nombre completo.

El resultado se convierte a minúsculas y se normaliza.

El username se actualiza mientras el usuario escribe.

### Listado de usuarios

Los usuarios registrados se muestran mediante un componente JList.

El listado se actualiza cuando la ventana recupera el foco y permite seleccionar un usuario para consultar su información.

### Detalle de usuario

Muestra la información del usuario seleccionado, incluida su fotografía cuando esté disponible.

La contraseña no se muestra en la ventana de detalle.

### Generación de PDF

Mediante Apache PDFBox, la aplicación permite exportar una ficha del usuario que incluye:

- Identificación.
- Nombres y apellidos.
- Username.
- Teléfono.
- Correo electrónico.
- Fotografía, cuando esté disponible.

Por seguridad, la contraseña no se incluye en el PDF.

### Créditos

La aplicación dispone de una ventana destinada a mostrar la información de los integrantes del proyecto.

## 3. Ventanas de la aplicación

| Ventana | Función |
|---------|---------|
| FormMenu | Menú principal y navegación |
| FormRegistro | Registro de usuarios |
| FormListado | Consulta de usuarios registrados |
| FormDetalle | Visualización de información individual |
| FormCreditos | Información de los desarrolladores |

Cada ventana utiliza Java Swing y un formulario de IntelliJ GUI Designer.

## 4. Estructura principal

```text
BookUsers/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── org/
                └── example/
                    ├── Usuario.java
                    ├── Validaciones.java
                    ├── FormMenu.java
                    ├── FormMenu.form
                    ├── FormRegistro.java
                    ├── FormRegistro.form
                    ├── FormListado.java
                    ├── FormListado.form
                    ├── FormDetalle.java
                    ├── FormDetalle.form
                    ├── FormCreditos.java
                    ├── FormCreditos.form
                    └── GeneradorPDF.java
```

La estructura anterior representa los archivos principales del proyecto.

## 5. Requisitos de ejecución

Para abrir y ejecutar BookUsers se recomienda contar con:

- JDK 21.
- IntelliJ IDEA con soporte para Swing GUI Designer.
- Apache Maven.
- Conexión a Internet durante la primera descarga de dependencias Maven.

La dependencia Apache PDFBox está declarada en el archivo pom.xml.

## 6. Instalación y ejecución

### Paso 1. Obtener el proyecto

Clonar el repositorio mediante Git o descargarlo desde GitHub.

```bash
git clone URL_DEL_REPOSITORIO
```

Reemplazar URL_DEL_REPOSITORIO por la dirección real del repositorio.

### Paso 2. Abrir en IntelliJ IDEA

1. Abrir IntelliJ IDEA.
2. Seleccionar Open.
3. Elegir la carpeta BookUsers.
4. Esperar a que IntelliJ cargue el proyecto.

### Paso 3. Configurar Java

1. Abrir File > Project Structure.
2. Seleccionar Project.
3. Configurar el SDK con JDK 21.
4. Verificar que el nivel del lenguaje corresponda a Java 21.

### Paso 4. Sincronizar Maven

1. Abrir la ventana Maven.
2. Seleccionar Reload All Maven Projects.
3. Esperar a que se descarguen las dependencias.

### Paso 5. Compilar y ejecutar

1. Seleccionar Build > Rebuild Project.
2. Abrir la clase principal del proyecto.
3. Ejecutar el método main que inicia FormMenu.
4. Utilizar los botones del menú para navegar por la aplicación.

**Nota:** los formularios .form utilizan IntelliJ GUI Designer. La compilación desde Maven por línea de comandos puede requerir configuración adicional para generar las interfaces.

## 7. Almacenamiento de información

BookUsers utiliza un ArrayList<Usuario> compartido entre las ventanas correspondientes.

Los registros se almacenan temporalmente en memoria durante la ejecución.

La aplicación no utiliza una base de datos.

Al cerrar completamente el programa, los usuarios registrados no se conservan.

## 8. Programación Orientada a Objetos

El proyecto aplica conceptos como:

- Clases y objetos.
- Encapsulamiento.
- Constructores.
- Métodos.
- Atributos.
- Colecciones mediante ArrayList.
- Manejo de eventos.
- Separación de responsabilidades.

La clase Usuario representa la información de cada persona registrada.

La clase Validaciones contiene funciones auxiliares de validación.

Las clases FormMenu, FormRegistro, FormListado, FormDetalle y FormCreditos implementan la interfaz gráfica.

La clase GeneradorPDF se encarga de exportar las fichas.

## 9. Consideraciones

- Las fotografías se referencian mediante rutas locales.
- Si se mueve o elimina una fotografía original, podría dejar de estar disponible.
- La información de los usuarios se pierde al cerrar la aplicación.
- El proyecto está destinado a fines académicos.
- La contraseña se mantiene en memoria sin cifrado, por lo que no debe utilizarse para almacenar credenciales reales.

## 10. Integrantes

### Integrante 1

- Nombre completo: PENDIENTE
- Código estudiantil: PENDIENTE
- Correo electrónico: PENDIENTE

### Integrante 2

- Nombre completo: PENDIENTE
- Código estudiantil: PENDIENTE
- Correo electrónico: PENDIENTE

## 11. Información académica

- Proyecto: BookUsers
- Asignatura: Programación Orientada a Objetos
- Lenguaje: Java 21
- Año: 2026

---

**BookUsers — Proyecto académico de Programación Orientada a Objetos.**
