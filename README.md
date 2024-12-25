# Waste Management System

A full-stack application for managing waste collection and bin monitoring, built with Angular and Spring Boot.

## Project Overview

This waste management system is designed to help municipalities and waste management companies optimize their collection routes and monitor bin statuses in real-time. The system includes features for tracking bin fill levels, managing collection routes, and analyzing collection data.

### Key Features

- Real-time bin monitoring
- Route optimization for waste collection
- Data analysis and reporting
- User authentication and role-based access
- Multi-language support (French, English, Arabic)

## Technical Stack

### Frontend

- Angular
- TypeScript
- HTML5 & CSS3
- Bootstrap
- RxJS for state management
- JWT for authentication
- Leaflet for maps

### Backend

- Spring Boot
- Spring Security
- JWT Authentication
- JPA/Hibernate
- MySQL Database

## Project Structure

### Frontend Structure

src/
├── app/
│ ├── components/
│ │ ├── home/
│ │ ├── login/
│ │ ├── portal/
│ │ ├── analysis/
│ │ └── contact/
│ ├── core/
│ │ ├── guards/
│ │ ├── interceptors/
│ │ └── services/
│ ├── shared/
│ └── interfaces/
Copy

### Backend Structure

src/
├── main/
│ ├── java/
│ │ └── com/waste/wasteTracker/
│ │ ├── controller/
│ │ ├── model/
│ │ ├── service/
│ │ ├── repository/
│ │ ├── security/
│ │ └── dto/
│ └── resources/
