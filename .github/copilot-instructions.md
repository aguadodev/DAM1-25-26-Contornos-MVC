# Copilot instructions for DAM1-25-26-Contornos-MVC

This document gives an overview of the small MVC project so that any AI coding agent (Copilot or similar) can be productive immediately.  It is intentionally concise and focused on the actual structure and conventions used here.

---

## Project Layout

```
src/
  App.java               (placeholder entry point)
  boundary/              (UI classes)
    ConsolaMatriculaUI.java   // console‑based menu interactio
  control/               (controllers/business logic)
    MatriculaController.java  // in‑memory lists, no persistence
  entity/                (domain model)
    Alumno.java
    Ciclo.java
    Matricula.java
```

- The code lives entirely under `src` and uses the default package-to-directory mapping.
- No build system (Maven/Gradle) is present; compile/run via `javac`/`java` or the VS Code Java extension.
- There is no `lib` folder in the repository; all dependencies are standard JDK classes.

## Architectural Overview

This is a minimal **Model‑View‑Controller** demonstration:

1. **Entities** (`entity` package) model domain objects.  They are plain POJOs with getters, a simple `toString()` and, in `Alumno`, a one‑to‑one link to a `Matricula`.
2. **Controller** (`MatriculaController`) holds in‑memory collections (`List<Alumno>` and `List<Ciclo>`) and simple operations:
   - `crearAlumno(...)` increments an `int` id counter and adds to the list.
   - `matricularAlumno(int, String)` locates objects using `stream().filter(...).findFirst()` and returns `boolean` success.
   - Accessors `getAlumnos()`/`getCiclos()` expose the lists for read‑only use by the UI.
   - Constructor seeds two example `Ciclo` instances.
3. **Boundary / UI** (`ConsolaMatriculaUI`) is a console menu that drives the controller.  It reads input with a `Scanner`, converts text to ints, and prints results.  Main entry is defined in this class rather than `App`.

Data flows from UI ➜ controller methods ➜ entities; there is no persistence beyond the JVM lifetime.

## Important Patterns & Conventions

- **Package names** are lowercase and correspond to directories.  Avoid creating new packages outside `boundary`, `control`, `entity` unless extending architecture.
- **ID counters** are simple `int` fields inside `MatriculaController`.  New entities use `contadorAlumno++` and `contadorMatricula++`.
- **`toString()`** is used for simple outputs; `listarAlumnos()` in the UI prints each `Alumno` using its `toString()`.
- **No exception handling around input parsing** – when adding new options, mirror the existing style (use `Integer.parseInt(scanner.nextLine())` and trust valid input).
- **No persistence or external services**; there are no file or network operations anywhere in the codebase.

## Development / Run Workflows

- **Compile**: `javac` all `.java` files under `src` (e.g. `javac -d bin src/**/*.java`).  The VS Code Java extension also handles compilation automatically.
- **Run**: execute `boundary.ConsolaMatriculaUI` (the sample console app) with `java -cp bin boundary.ConsolaMatriculaUI` or simply run the `main` method from the editor.
- There are no tests, so automated test commands are not applicable.
- The provided `App.java` just prints "Hello, World!" and is unused by business logic.

> The README emphasises basic VS Code Java instructions but has no project‑specific commands.

## Editing and Extending

When adding new features:

- Follow the three‑layer separation.  For example, a `TerminoController` would live in `control` and a corresponding UI in `boundary`.
- Entities may reference each other; ensure `toString()` methods are kept concise to avoid recursive prints.
- Use `Optional` consistently when searching lists (see `matricularAlumno`).
- When interacting with the console, replicate the `mostrarMenu()` / `switch` pattern.

## Notes for AI Agents

- Look for entry points in `main` methods; the real application starts in `ConsolaMatriculaUI`, not `App`.
- There is minimal error handling: anything beyond existing simple cases likely needs explicit checks.
- The project is single‑module and self‑contained – do not add dependency management logic unless the specification changes.
- Avoid assuming persistence; any new storage requirement will require introducing new packages or external libs.

---

Please review these instructions and tell me if any important parts of the application or workflow are unclear or missing.