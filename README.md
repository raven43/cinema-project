# Cinema Project

Приложение служит для сбора и хранения структурированной информации о кинематографе

# Запуск

Пререквизиты:

* JDK 11
* Maven
* Установленный и настроенный MySQL Server 

Для того что бы запустить веб прижение необходимо 

#### 1. Выполнить настройку приложения с помощью переменных окружения 

| Переменная  | Описание                                     |  Значение по умолчанию                                                | 
| ----------- | -------------------------------------------- | --------------------------------------------------------------------- |
| DB_URL      | URL для подключения к базе данных            | jdbc:mysql://localhost:3306/cinema_db?useSSL=false&serverTimezone=UTC |
| DB_USERNAME | Логин для подключения к базе данных          | cinema_server                                                         |
| DB_PASSWORD | Пароль для подключения к базе данных         | Cinema123!                                                            |
| UPLOAD_PATH | Путь к папке в которой будут храниться файлы | /cinema-project-files                                                 |

##### Настройка переменных окружения:

В bash:
```bash
  $>DB_URL=???
```
В cmd:
```cmd
  >set DB_URL=???
```

#### 2. Выполнить в bash/cmd следующую команду

```bash
  $>mvn spring-boot:run
```

# Пользовательский интерфейс

## Основной функционал:

### Логин, Регистрация

![login](docs/images/login.jpg) ![register](docs/images/register.jpg)

### Главная страница

![home page](docs/images/home-page.jpg)

### Страница фильмов

![films list](docs/images/film-list.jpg)
![film page](docs/images/film-page.jpg)

### Страница актёра

![person list](docs/images/person-list.jpg)
![person page](docs/images/parson-page.jpg)

### Комментарии

![comments](docs/images/comments.jpg)

### Редактирование профиля

![profile edit](docs/images/profile-edit.jpg)

## Функционал администратора

### Добавление/Изменение фильма/актёра

![film edit](docs/images/film-edit.jpg) ![person edit](docs/images/person-edit.jpg)

### Изменение привиллегий пользователя

![users list](docs/images/users-list.jpg) ![user edit](docs/images/user-edit.jpg)

