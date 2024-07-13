# Three Card Poker Game

## Description

The Three Card Poker Game is a GUI-based application developed collaboratively, featuring meticulous object management and comprehensive UML planning. This project enhances visual engagement and game logic with in-depth mechanics and dynamic imagery, supported by a rigorous test design. It is implemented using JavaFX and managed as a Maven project.

## Prerequisites

Before you run this application, you will need:
- Java Development Kit (JDK) 17 or higher
- Maven 3.6 or higher
- JavaFX SDK 22.0.1

## Setup Instructions

### 1. Install JDK

Ensure that Java Development Kit (JDK) 17 or higher is installed on your machine. You can download the latest version from [Oracle’s official website](https://www.oracle.com/java/technologies/javase-downloads.html).

### 2. Install Maven

Maven is required to manage dependencies and build configurations. Download and install it from [Apache Maven’s official site](https://maven.apache.org/download.cgi).

### 3. Download JavaFX

Download the JavaFX SDK from [OpenJFX](https://openjfx.io). Ensure you download version 22.0.1 or compatible with your JDK version.

### 4. Configure JavaFX

Extract the downloaded JavaFX SDK to a known location on your machine. You will need to reference this path when setting up your project to run.

### 5. Clone the Repository

Clone this repository to your local machine using Git:

```bash
  git clone https://your-repository-url.git
  cd three-card-poker-game
```
### 6. Run the Application

Navigate to the project directory and use the following command to run the application, ensuring to replace /path/to/javafx-sdk-22.0.1/lib with the actual path to your JavaFX lib directory:

```bash
java --module-path /path/to/javafx-sdk-22.0.1/lib --add-modules javafx.controls,javafx.fxml,javafx.graphics -cp target/ThreeCardPokerGame-0.0.1-SNAPSHOT.jar ThreeCardPokerGame
```
Features

	•	Detailed game logic for a realistic poker experience
	•	Dynamic imagery to enhance user interaction
	•	Comprehensive UML planning to ensure robust architecture and design
	•	Rigorous test design for reliable application performance


