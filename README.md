# Calculator Project

A simple calculator application with a Spring Boot backend for expression evaluation and an Angular frontend for the user interface.

---

## 🚀 Technologies

-   **Backend:** Spring Boot (REST API)
-   **Frontend:** Angular

---

## 📌 Features

-   Supports basic arithmetic operations (+, -, ×, ÷)
-   Clean separation between frontend and backend
-   Frontend communicates with backend through a REST API

---

## 🗂 Project Structure

```text
Calculator-Project
├─ backend/ # Spring Boot REST API (evaluation logic)
└─ frontend/ # Angular UI (calculator buttons + display)

```

## 🔗 API (Quick Example)

**POST** `/calculate`

**Request:**

```json
{ "expression": "9/3+2" }
```
**Response:**
```json
{ "result": "5" }
```
