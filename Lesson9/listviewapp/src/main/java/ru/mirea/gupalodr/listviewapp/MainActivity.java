package ru.mirea.gupalodr.listviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
// Создать список авторов и книг, которые Вы планируете прочитать в ближайшие 30 лет (более 30)
    private String[] books = {"Пелевин - Метро 2033", "Солженицын - Архипелаг Соловки", "Ахматова - Реквием", "Есенин - Конец Петра", "Бродский - Времена года", "Трифонов - Улики", "Солженицын - Красное колесо", "Пастернак - Доктор Живаго", "Булгаков - Белая гвардия", "Ремарк - Триумфальная арка", "Стругацкие - Понедельник начинается в субботу", "Хаксли - О дивный новый мир", "Толстой - Война и мир", "Киз - Цветы для Элджернона", "Достоевский - Преступление и наказание", "Гоголь - Мертвые души", "Лермонтов - Герой нашего времени", "Чехов - Анна Каренина", "Тургенев - Отцы и дети", "Булгаков - Мастер и Маргарита", "Салтыков-Щедрин - Иван Бабушкин синий уголок", "Набоков - Лолита", "Пушкин - Евгений Онегин", "Льва Толстой - Севастопольская битва", "Гончаров - Обыкновенное чудо", "Бунин - Жизнь Клима Самгина", "Островский - Гроза", "Шукшин - Любимая", "Мандельштам - Воскресение Иисуса Христа",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView booksList = findViewById(R.id.book_list_view);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, books) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);
                text2.setText(getItem(position).toString());
                text1.setText(String.valueOf(position+1));
                return view;
            }
        };

        booksList.setAdapter(adapter);
    }
}