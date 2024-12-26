# README - Proyecto de Gestión de Usuarios

## Índice

1. [Equipo](#equipo)
2. [Propósito del Proyecto](#propósito-del-proyecto)
3. [Tecnologías](#tecnologías)
4. [Pipeline](#pipeline)
5. [Construcción Automática](#construcción-automática)
6. [Gestión de Tareas](#gestión-de-tareas)

---

## Equipo

- **Nombre del Equipo:** Los Maquiavélicos
- **Miembros del Equipo:**
  - **Castillo Llaza, Lizardo Manuel**
  - **Cardenaz Villagomez, Piero Adriano**
  - **Cornejo Pari, Karla Veronica**

---

## Propósito del Proyecto

### Objetivo

El objetivo de este proyecto es crear una página web que permita la gestión de usuarios, donde se puedan agregar, eliminar y actualizar usuarios directamente desde la interfaz web. Estos cambios se reflejan inmediatamente en la base de datos (PostgreSQL), garantizando que la información de los usuarios esté siempre actualizada.

### Arquitectura de Software

La arquitectura del software está basada en un modelo cliente-servidor. El frontend es desarrollado utilizando **Angular**, mientras que el backend está implementado con **Spring Boot**. La base de datos utilizada es **PostgreSQL**, la cual almacena la información de los usuarios.

### Funcionalidades principales

- **Agregar usuarios:** Los usuarios pueden ser registrados en la base de datos mediante un formulario.
- **Eliminar usuarios:** Los usuarios pueden ser eliminados desde la interfaz, actualizando la base de datos en tiempo real.
- **Actualizar usuarios:** La información de los usuarios puede ser modificada desde la interfaz.

**Imágenes de las funcionalidades:**

- Pantalla de Agregar Usuario:
  ![image](https://github.com/user-attachments/assets/15daa0f0-9124-4730-b511-453d755c5368)

- Pantalla de Eliminar Usuario:
  ![image](https://github.com/user-attachments/assets/d8dab184-eacc-4687-98f5-971e9968c8c1)

---

## Tecnologías

### Lenguaje de Programación

- **Java** (para el backend con Spring Boot)
- **TypeScript** (para el frontend con Angular)
- **SQL** (para la base de datos PostgreSQL)

### Frameworks

- **Angular** (para el frontend)
- **Spring Boot** (para el backend)
- **PostgreSQL** (base de datos)
- **Bootstrap** (para el diseño responsive en el frontend)

### Bibliotecas

- **Bootstrap** (para el diseño de la interfaz de usuario)
- **Spring Data JPA** (para la interacción con la base de datos)
- **JUnit** (para pruebas unitarias)
- **Selenium** (para pruebas funcionales)
- **Jacoco** (para cobertura de pruebas)

### Herramientas de Construcción y Pruebas

- **Maven** (para la gestión de dependencias y construcción del proyecto)
- **JUnit** (para pruebas unitarias)
- **Jacoco** (para cobertura de código)
- **Selenium** (para pruebas funcionales)
- **Postman** (para pruebas de la API)

---

## Pipeline

### Herramienta/Framework

- **Jenkins** (para la automatización de la integración continua y despliegue continuo)

### Evidencia

**Fragmento de código de un pipeline en Jenkins:**

```groovy
pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "MAVEN_HOME"
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/Lizardo-Castillo/Proyecto_Backend.git'

                // Run Maven on a Windows agent.
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
