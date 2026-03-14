# Task Manager REST API

Prosta aplikacja backendowa do zarządzania zadaniami (TODO), napisana w Javie z wykorzystaniem Spring Boot.

## Technologie

* **Java 17**
* **Spring Boot 3**
* **Spring Data JPA**
* **Relacyjna baza danych H2** (baza in-memory)
* **Maven**
* **Git**
* **JUnit 5 & Mockito**

## Instrukcja uruchomienia aplikacji

Aplikacja korzysta z wbudowanej bazy danych H2, więc nie jest wymagana instalacja żadnej zewnętrznej bazy.

1. **Sklonuj repozytorium:**

   ```bash
   git clone https://github.com/locomotiv1/taskmanager.git
   cd taskmanager
   ```

2. **Uruchom za pomocą Maven Wrapper:**
   Otwórz terminal w głównym katalogu projektu i wpisz:

   * Na Windows:

     ```cmd
     mvnw.cmd spring-boot:run
     ```

   * Na Mac/Linux:

     ```bash
     ./mvnw spring-boot:run
     ```

Aplikacja uruchomi się domyślnie pod adresem `http://localhost:8080`.

### Dostęp do bazy danych H2

Zawartość bazy danych można przeglądać w trakcie działania aplikacji za pomocą przeglądarki:

* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:tododb`
* **Username:** `sa`
* **Password:** `password`

## Dokumentacja API (Endpointy)

Komunikacja z API odbywa się w formacie JSON.

### 1. Pobieranie listy zadań

* **Endpoint:** `GET /api/tasks`
* **Odpowiedź:** Zwraca tablicę wszystkich zadań.

### 2. Pobieranie pojedynczego zadania

* **Endpoint:** `GET /api/tasks/{id}`
* **Odpowiedź:** Zwraca pojedyncze zadanie lub błąd `404 Not Found`, jeśli zadanie nie istnieje.

### 3. Dodawanie zadania

* **Endpoint:** `POST /api/tasks`
* **Przykładowe request body:**

  ```json
  {
    "title": "Zrobić zadanie rekrutacyjne",
    "description": "Zaimplementować REST API w Spring Boot",
    "status": "IN_PROGRESS"
  }
  ```

  *(Uwaga: `description` jest opcjonalne. Jeśli `status` nie zostanie podany, domyślnie ustawi się na `NEW`).*

### 4. Edycja zadania

* **Endpoint:** `PUT /api/tasks/{id}`
* **Przykładowe ciało żądania (Request Body):**

  ```json
  {
    "title": "Zrobić zadanie rekrutacyjne - ZAKTUALIZOWANE",
    "description": "Dodać testy i dokumentację",
    "status": "DONE"
  }
  ```

### 5. Usuwanie zadania

* **Endpoint:** `DELETE /api/tasks/{id}`
* **Odpowiedź:** Zwraca status `204 No Content` po pomyślnym usunięciu zadania.
