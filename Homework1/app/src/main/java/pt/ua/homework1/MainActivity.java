package pt.ua.homework1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText imput;
    private Button btnDel, memory1,memory2,memory3;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText newName, newNumber;
    private Button save, cancel;
    private Contact c1 = new Contact("Mae", "11111111");
    private Contact c2 = new Contact("Pai", "222222222");
    private Contact c3 = new Contact("Irmao", "333333333");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imput = findViewById(R.id.textNumber);
        btnDel = findViewById(R.id.buttonDel);
        memory1 = findViewById(R.id.buttonMem1);
        memory2 = findViewById(R.id.buttonMem2);
        memory3 = findViewById(R.id.buttonMem3);

        memory1.setText(c1.getName());
        memory2.setText(c2.getName());
        memory3.setText(c3.getName());

        btnDel.setOnLongClickListener(v -> {
            imput.setText("");
            return true;
        });

        memory1.setOnLongClickListener(v -> {
            createNewSpeedDialDialog(memory1,c1);
            return true;
        });

        memory2.setOnLongClickListener(v -> {
            createNewSpeedDialDialog(memory2,c2);
            return true;
        });

        memory3.setOnLongClickListener(v -> {
            createNewSpeedDialDialog(memory3,c3);
            return true;
        });


    }

    public void createNewSpeedDialDialog(Button button, Contact contact){
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup,null);
        newName = (EditText) contactPopupView.findViewById(R.id.editPersonName);
        newNumber = (EditText) contactPopupView.findViewById(R.id.editNumber);
        save = (Button) contactPopupView.findViewById(R.id.buttonSave);
        cancel = (Button) contactPopupView.findViewById(R.id.buttonCancel);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact.setName(newName.getText().toString());
                contact.setNumber(newNumber.getText().toString());
                button.setText(contact.getName());
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    public void onCall(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        String number = imput.getText().toString();
        number = number.replace("#","%23");
        intent.setData(Uri.parse("tel:"+number));
        startActivity(intent);
    }

    public void addCardinal(View view) {
        changeText("#");
    }

    public void addZero(View view) {
        changeText("0");
    }

    public void addAsteric(View view) {
        changeText("*");
    }

    public void addNine(View view) {
        changeText("9");
    }

    public void addEight(View view) {
        changeText("8");
    }

    public void addSeven(View view) {
        changeText("7");
    }

    public void addSix(View view) {
        changeText("6");
    }

    public void addFive(View view) {
        changeText("5");
    }

    public void addFour(View view) {
        changeText("4");
    }

    public void addThree(View view) {
        changeText("3");
    }

    public void addTwo(View view) {
        changeText("2");
    }

    public void addOne(View view) {
        changeText("1");
    }

    public void onDelete(View view) {
        String temp = imput.getText().toString();
        if(temp.length() > 0)
            imput.setText(temp.substring(0,temp.length()-1));
    }

    public void changeText(String number){
        String temp = imput.getText().toString();
        imput.setText(temp+number);
    }

    public void onMemory1(View view) {
        imput.setText(c1.getNumber());
    }

    public void onMemory2(View view) {
        imput.setText(c2.getNumber());
    }

    public void onMemory3(View view) {
        imput.setText(c3.getNumber());
    }
}