package com.example.formularzzadanie;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button przyciskZarejestruj = findViewById(R.id.przyciskZarejestruj);
        przyciskZarejestruj.setOnClickListener(v -> {
            EditText imieP = findViewById(R.id.imie);
            String imie = imieP.getText().toString().trim();

            EditText nazwiskoP = findViewById(R.id.nazwisko);
            String nazwisko = nazwiskoP.getText().toString().trim();

            EditText emailP = findViewById(R.id.email);
            String email = emailP.getText().toString().trim();

            EditText hasloP = findViewById(R.id.haslo);
            String haslo = hasloP.getText().toString().trim();

            if(imie.isEmpty() || nazwisko.isEmpty() || email.isEmpty() || haslo.isEmpty()){
                Toast.makeText(this, "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show();
            }else if(!sprawdzEmail(email)){
                Toast.makeText(this, "Email musi zawierać '@' i '.'", Toast.LENGTH_SHORT).show();
            }else if(!sprawdzHaslo(haslo)){
                Toast.makeText(this, "Haslo musi mieć minimum 8 znaków, zawierać małą, dużą literę i znak specjalny", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Gitówa", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean sprawdzEmail(String email){
        if(email.contains("@") && email.contains(".")) return true;
        return false;
    }

    public boolean sprawdzHaslo(String haslo){
        if(haslo.length() >= 8 &&
           haslo.matches(".*[a-z].*") &&
           haslo.matches(".*[A-Z].*") &&
           haslo.matches(".*[a-zA-z0-9].*")) return true;
        return false;
    }
}
