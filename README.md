# Проект по автоматизации тестирования API на сайте - demoqa.com.
<img title="Main Gage" src="media/mainpage.PNG">

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
<img width="16%" title="Maven" src="media/maven-logo-black-on-white.png">
<img width="16%" title="Java" src="media/Java.svg">
<img width="16%" title="JUnit5" src="media/JUnit5.svg">
<img width="16%" title="IntelliJ IDEA" src="media/Intelij_IDEA.svg">
<img width="14%" title="Rest Assured" src="media/RestAssured.svg">
<img width="16%" title="Allure Report" src="media/Allure_Report.svg">
<img width="16%" title="GitHub" src="media/GitHub.svg">
<img width="15%" title="Allure TestOps" src="media/Allure-logo.svg">
</p>

[К оглавлению ⬆](#оглавление)
<a name="проверки"></a>
# Выполненные проверки
- Проверка списка книг
- Авторизация с корректными данными
- Авторизация с не существующим username
- Авторизация с некорректным паролем
- Проверка запроса с параметром и проверка Title
- Проверка запроса с параметром и проверка всех параметром Book
- Проверка существования пользователя
- Проверка неавторизованного пользователя

[К оглавлению ⬆](#оглавление)
<a name="запуск_локально"></a>
# Запуск тестов
Локальный запуск осуществляется командой: 
```
maven clean test
```
<a name="формирование_отчетов"></a>
# Формирование отчетов
Формирование AllureReport:
```
maven allure:serve
```

[К оглавлению ⬆](#оглавление)
<a name="Allure"></a>
# Allure и Allure TestOps
Главный экран отчета
![](media/screenshots/allure-1.png)

Страница с проведенными тестами
![](media/screenshots/allure-2.png)

Каждый запрос и ответ API логируется в удобном виде с помощью настраиваемых шаблонов
![](media/screenshots/allure-3.png)

Allure TestOps Главный экран
![](media/screenshots/testops-1.png)

Allure TestOps страница с тестами
![](media/screenshots/testops-2.png)

Allure TestOps выполненные прогоны
![](media/screenshots/testops-3.png)

[К оглавлению ⬆](#оглавление)
