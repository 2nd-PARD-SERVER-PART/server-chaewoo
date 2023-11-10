# PARD Server part, Assignment #4
Assignment after Seminar #4, results:

Topic: "Tasks"(To-dos, bound to User)

## Designed features:
1. Task... (Like a tree with multiple leaf nodes)
    1. can have *multiple* subtasks.
    2. can have *one* supertask.
2. User...
    1. can have *multiple* tasks.
    2. can *create*, *retrieve*, *update*, and *remove* tasks.
3. Misc
    1. Task should update its relationship with others when created/updated/removed.
    2. Tasks *related to* the task created/updated/removed should update their relationship with it.
    3. Task **should belong to** a *single* person. This means that task cannot belong to *multiple* person, and *should* have a user owning it.
    4. Task **should NOT be** retrieved, updated or removed **without the owner**.

## Entity
### `Task`
| Field | Type | Properties |
| --- | --- | --- |
| `id` | `Long` | **ID**, *auto-incrementing* |
| `used` | `User` | *many-to-one* relation |
| `title` | `String` | **required** |
| `content` | `String` | **required** |
|||
| `isDone` | `boolean` | `false` *by default* |
| `timeCreated` | `Timestamp` | `@CreationTimestamp` |
| `timeUpdated` | `Timestamp` | `@UpdateTimestamp` |
| `timeStarting` | `Timestamp` | *nullable* |
| `timeEnding` | `Timestamp` | *nullable* |
|||
| `taskHigher` | `Task` | *many-to-one* relation with **itself** |
| `tasksLower` | `ArrayList<Task>` | *one-to-many* relation with **itself** |

### `User`
| Field | Type | Properties |
| --- | --- | --- |
| `id` | `Long` | **ID**, *auto-incrementing* |
| `email` | `String` | **unique**, **required** |
| `password` | `String` | **required** |
| `name` | `String` | **required** |
|||
| `tasks` | `ArrayList<Task>` | *many-to-one* with `Task` |

---

## Screenshots