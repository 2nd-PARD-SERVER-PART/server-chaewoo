# `Turbstructor`'s `Spring` projects in PARD server section
Server section, PARD-Y 2", PARD, 2023H2

## Folder structures and Progresses undergone
- [`seminars`](./seminars): `Spring Boot` projects done on server seminars

| Folder Name | Description | Status |
| :--- | :--- | ---: |
| [`seminar01`](./seminars/seminar01) | @ Week 3: **Spring Boot introduction**, **`Controller` / `Model`(Data Transfer Object(DTO))** introduction | `done` |
| [`seminar02`](./seminars/seminar02) | (Week 5): **`CRUD`** implementation (*w/o* Database) | `done` |
| [`seminar03`](./seminars/seminar03) | (Week 6): **Database** introduction | `done` |
| ~~[`seminar04`](./seminars/seminar04)~~ | (Week 9): Database intensed: **relations** between database entities/tables, **`Swagger`** introduction | `unknown`: not uploaded |
| ~~[`seminar05`](./seminars/seminar05)~~ | (week 10): **Authentication / Authorization** introduction | `unknown`: not uploaded |

- [`assignments`](./assignments): Spring Boot projects done as server seminar assignments

| Folder Name | Description | Status |
| :--- | :--- | ---: |
| [`assignment01`](./assignments/assignment01) | 'sending' `model`` data to `view` | `done` |
| [`assignment02`](./assignments/assignment02) | using `Postman`, practicing `CRUD` | `done` |
| [`assignment03`](./assignments/assignment03) | adding **validations** on fields of `entity`, adding **custom** JPA queries | `done` |
| [`assignment04`](./assignments/assignment04) | Implementing **custom project** with 2+ `entities` **that has relations** | nearly `done`: requires more *explanation* on `README.md` (w/ screenshots) |
| [`assignment05`](./assignments/assignment05) | Revising `assignment04` by adding **authentication / authorization** | `incomplete`: needs additional *revision* |

- [`misc`](./misc): Miscellaeneous files used for projects

## Naming Conventions used inside
### Spring Project Metadata
- Group: `club.pard.server` (server.pard.club)
- Artifact
    - Projects in seminars: In format `"seminarXX"`
    - Projects in assignments: In format `"assignmentXX"`
    - Additionally label alphabetically if there are multiple projects in one seminar/assignment. (e.g. `"seminarXXa"`, `"assignmentXXb"`)
    - Additionally label `Mx` if there are undergone modifications on projects. (e.g. `"assignment04M1"`, `"assignment05M2"`) (adding format `M{modificationNumber}`, `modificationNumber` starting from 1.)

### Classes and Packages
- Classes: `<entityName>...<componentName>`
- Packages: `<componentName>.<wideClassPackageName>.<detailedClassPackageName>...`
- Keep all package names **lowercased**, all class names **camelcased**