# Видеопрокат
## Описание страниц
На каждой из страниц помимо наполнения есть кнопка перехода на главную страницу и на предыдущую страницу. <br />
### [1] Главная страница
* Кнопка Клиенты &emsp; [переход на 2]<br />
* Кнопка Фильмы &emsp;[переход на ]<br />

### [2] Клиенты
* Перечень клиентов. [При нажатии на клиента переход на персональную страницу клиента 3] <br />
* Кнопка Добавить клиента [переход на форму добавления клиента] <br />
* Форма для сортировки по имени <br />

### [3] Персональная страница клиента. <br />
* Информация о клиенте <br />
  * имя  <br />
  * адрес  <br />
  * номер телефона <br />
* Форма для поиска фильма (для выдачи). [переход на страницу информации для выдачи] <br />
* Кнопка Редактировать клиента [переход на форму редактирования клиента] <br />
* Кнопка Удалить клиента <br />
*	Поле Невозвращённые фильмы. Напротив каждого фильма - кнопка Принять возврат. <br />
*	Поле история выдачи  <br />

### [] Информация для выдачи  <br />
 * Информация о фильме <br />
   * название <br />
   * число доступных к выдаче дисков<br />
   * число доступных к выдаче кассет<br />
   * цена проката диска<br />
   * цена проката кассеты<br />

* Кнопка Выдать фильм, вызывает выбор: диск/кассета<br />

 
### [4] Фильмы <br />
* Перечень фильмов. [При нажатии на фильм переход на страницу фильма 5] <br />
* Кнопка Добавить фильм [переход на форму добавления фильма] <br />
* Форма для сортировки по названию/режиссёру/году выпуска/кинокомпании <br />

### [5] Страница фильма. <br />
 * Информация о фильме
   * название
   * кинокомпания
   * режиссёр
   * год выпуска
   * число доступных к выдаче дисков
   * число доступных к выдаче кассет
   * цена проката диска
   * цена проката кассеты
* Кнопка Редактировать фильм [переход на форму редактирования фильма] <br />
* Кнопка Удалить фильм <br />


## Сценарии использования
### 1. Получить список клиентов/фильмов.<br />
* Нажать на главной странице на кнопку Клиенты/Фильмы <br />
### 2. Получить историю выдачи и приёма фильма у клиентов/списка находящихся у него фильмов<br />
* Нажать на главной странице на кнопку Клиенты <br />
* Найти сортировкой по имени клиента и нажать на него. <br />
* Нажать кнопку Посмотреть историю выдачи/Фильмы на руках <br />
### . Добавление клиента <br />
* Нажать на главной странице на кнопку Клиенты <br />
* Нажать кнопку Добавить клиента <br />
* Заполнить форму добавления клиента <br />

### . Удаление клиента <br />
* Нажать на главной странице на кнопку Клиенты <br />
* Найти сортировкой по имени клиента и нажать на него. <br />
* Нажать кнопку Удалить клиента <br />

### . Редактирование информации о клиенте <br />
* Нажать на главной странице на кнопку Клиенты <br />
* Найти сортировкой по имени клиента и нажать на него. <br />
* Нажать кнопку Редактировать клиента <br />
* Заполнить форму редактирования клиента <br />

### . Добавление фильма <br />
* Нажать на главной странице на кнопку Фильмы <br />
* Нажать кнопку Добавить фильм <br />
* Заполнить форму добавления фильма <br />

### . Удаление фильма <br />
* Нажать на главной странице на кнопку Фильмы <br />
* Найти сортировкой фильм и нажать на него. <br />
* Нажать кнопку Удалить фильм <br />
### . Редактирование информации о фильме <br />
* Нажать на главной странице на кнопку Фильмы <br />
* Найти сортировкой фильм и нажать на него. <br />
* Нажать кнопку Редактировать фильм <br />
* Заполнить форму редактирования фильм <br />
