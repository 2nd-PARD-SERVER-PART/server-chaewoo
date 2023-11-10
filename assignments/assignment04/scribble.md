# Scribbles making Tasks

## Designed features:
1. Task...
    1. can have *multiple* subtasks.
    2. can have *single* preceding tasks.
    3. can have *single* succeeding tasks.
2. User...
    1. can have *multiple* tasks.
    2. can *create*, *retrieve*, *update*, and *remove* tasks.
3. Multiple users cannot have same task. This means that **a task belongs to one person.**

---

## Entity
### `task`
| Field | Type | Properties |
| --- | --- | --- |
| `taskId` | `Long` | **ID**, *auto-incrementing* |
| `userId` | `Long` | could be foreign, *one-to-many* with `User` |
| `title` | `String` | **required** |
| `content` | `String` | **required** |
|||
| `isComplete` | `boolean` | `false` *by default* |
| `timeCreated` | `Timestamp` | `@CreationTimestamp` |
| `timeUpdated` | `Timestamp` | `@UpdateTimestamp` |
| `timeStarting` | `Timestamp` | *nullable* |
| `timeEnding` | `Timestamp` | *nullable* |
|||
| `tasksDependent` | `LinkedList<Task>` | *many-to-one* with itself |
| `taskPreceding` | `ArrayList<Task>` | *one-to-one* with itself |
| `taskSucceeding` | `ArrayList<Task>` | *one-to-one* with itself |

### `user`
| Field | Type | Properties |
| --- | --- | --- |
| `userId` | `Long` | **ID**, *auto-incrementing* |
| `userEmail` | `String` | **unique** |
|||
| `tasks` | `ArrayList<Task>` | *many-to-one* with `Task` |

---

## Possible actions

### External

#### `task`
- Creating task
- Reading task
- Updating/revising task
- Deleting task

Note: **Add user authorization progress when implementing as a part of `Assignment05`**

#### `user`
- Signing Up
- Signing In
- Withdrawing


### Internal

#### `task`
- Checking validity
    - By searching tasks for existence

#### `user`
- Checking validity
    - By searching email(when signing up / signing in / withdrawing)
    - By checking JWT (to validate actions done by `User`) (To-do for `Assignemnt05`)

---


<!-- ### Operations
- Operation id (ID, integer, auto-incrementing)
- Title (str)
- Content (str)
- first task (task) -->