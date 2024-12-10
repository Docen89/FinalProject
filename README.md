# Проект по автоматизации тестирования API на сайте - demoqa.com.
<img title="Main Gage" src="src/test/resources/media/mainpage.PNG">

<a name="оглавление"></a>
# Оглавление
1. [Технологии](#технологии)
2. [Выполненные проверки](#проверки)
3. [Запуск тестов](#запуск_локально)
    1. [Запуск тестов локально](#запуск_локально)
    2. [Запуск отчетов в AllureReport](#формирование_отчетов)
4. [Allure](#Allure)

<a name="технологии"></a>
# Использованны слудующие технологии:
<p align="center">
<img width="16%" title="Maven" src="src/test/resources/media/maven-logo-black-on-white.png">
<img width="16%" title="Java" src="src/test/resources/media/Java.svg">
<img width="16%" title="JUnit5" src="src/test/resources/media/JUnit5.svg">
<img width="16%" title="IntelliJ IDEA" src="src/test/resources/media/Intelij_IDEA.svg">
<img width="14%" title="Rest Assured" src="src/test/resources/media/RestAssured.svg">
<img width="16%" title="Allure Report" src="src/test/resources/media/Allure_Report.svg">
<img width="16%" title="GitHub" src="src/test/resources/media/GitHub.svg">
<img width="15%" title="Allure TestOps" src="src/test/resources/media/Allure-logo.svg">
</p>

[К оглавлению ⬆](#оглавление)
<a name="проверки"></a>
# Выполненные проверки
- Создание нового пользователя
- Авторизация зарегистрированным пользователем
- Авторизация незарегистрированным пользователем
- Добавление книги к пользователю в профиль
- Повторное добавление книги  в профиль пользователя
- Удаляем книгу из профиля пользователя
- Удаление  пользователя
- Запрос определенной книги
- Запрос несуществующей книги
- Проверка сохранения книги в профиле у пользователя
- Авторизация  с неверным паролем от пользователя
- Авторизация с валидной парой логопасс
- Проверка кнопки 'Go To Book Store'
- Не заполнено поле LastName
- Не заполнено поле FirstName
- Удаление аккаунта пользователя"
- Отображение добавленной книги в профиле у пользователя
- Проверка кнопки 'Delete All Books
- Проверка отображения информации о книге в профиле пользователя
- Поиск книги по названию в BookStore

[К оглавлению ⬆](#оглавление)
<a name="запуск_локально"></a>
# Запуск тестов
Локальный запуск осуществляется командой: 
```sh
mvn clean test
```
<a name="формирование_отчетов"></a>
# Формирование отчетов
Формирование AllureReport:
```sh
mvn allure:serve
```
[К оглавлению ⬆](#оглавление)
<a name="Allure"></a>
# Allure и Allure TestOps
Главный экран отчета
![](src/test/resources/media/11.PNG)

Страница с проведенными тестами
![](src/test/resources/media/22.PNG)

Каждый запрос и ответ API логируется в удобном виде с помощью настраиваемых шаблонов
![](src/test/resources/media/33.PNG)

Allure TestOps Главный экран
![](src/test/resources/media/44.PNG)

Allure TestOps страница с тестами
![](src/test/resources/media/55.PNG)

Allure TestOps выполненные прогоны
![](src/test/resources/media/66.PNG)

[К оглавлению ⬆](#оглавление)
