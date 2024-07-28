# FStore App

FStore is a modern e-commerce application designed to provide a seamless shopping experience. Built with the latest technologies, it offers a rich user interface and a robust backend system to manage products, users, and orders efficiently.

## Frontend Technologies

- **Jetpack Compose**: Used for building the UI components, providing a declarative and intuitive approach to Android UI development.
- **Retrofit**: Handles network requests, enabling seamless communication with the backend API.
- **MVVM (Model-View-ViewModel)**: Implements the MVVM architecture pattern to separate concerns and manage UI-related data in a lifecycle-conscious way.
- **DataStore**: Used for data persistence, replacing SharedPreferences for storing user preferences and settings.
- **Coil**: An image loading library for Android that is fast, lightweight, and easy to use.

## Backend Technologies

- **NestJS**: A progressive Node.js framework used for building efficient and scalable server-side applications.
- **AWS S3**: Utilized for storing and serving media assets like product and review images.

## Features

### Authentication

- **Signup**: New users can create an account.
- **Login**: Returning users can log in with their credentials.

### Home Screen

- Displays the latest products available in the store.

### Shop Screen

- Users can browse products with sorting and filtering options to refine their search.

### Product Details Screen

- Users can view detailed information about a product.
- Options to add products to the bag (cart) or favorites list.
- Users can also change the delivery address.

### Bag (Cart) Screen

- Shows the products added to the user's shopping bag, allowing for quantity adjustments and removal.

### Favorites Screen

- Displays products that the user has marked as favorites.

### Orders Screen

- Users can view their past orders and track the status of ongoing ones.

## Screenshots

Here you can include screenshots of your app to showcase its features and design.

![Home Screen](path/to/images/home_screen.png)
*Home screen displaying the latest products.*

![Shop Screen](path/to/images/shop_screen.png)
*Shop screen with filtering options.*

![Product Details](path/to/images/product_details.png)
*Product details screen.*

## Installation and Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/kishore-bot/FStore-App.git
   cd FStore-App
