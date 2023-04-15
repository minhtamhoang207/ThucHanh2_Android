package com.example.th2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.th2.adapter.SimpleImageArrayAdapter;
import com.example.th2.data.DBHelper;
import com.example.th2.model.MyModel;

import java.util.Calendar;

public class AddItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText edtAddName, edtAddContent, editTextSelectTime;
    Button btnConfirm;
    DBHelper dbHelper;
    Calendar date;
    int pickedImage;
    int[] listImage = new int[]{
        R.drawable.ic_baseline_featured_play_list_24,
        R.drawable.ic_baseline_person_pin_24,
        R.drawable.tomhm,
   };

    private RadioGroup radioGroupCharacter;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;

    void initView() {
        dbHelper = new DBHelper(this);
        btnConfirm = findViewById(R.id.btn_confirm);
        edtAddName = findViewById(R.id.edt_add_name);
        edtAddContent = findViewById(R.id.edt_add_content);
        editTextSelectTime = findViewById(R.id.edt_select_time);

        this.radioGroupCharacter= (RadioGroup) this.findViewById(R.id.radio_group);
        this.radioButtonMale = (RadioButton) this.findViewById(R.id.radioButton_male);
        this.radioButtonFemale  =  (RadioButton)this.findViewById(R.id.radioButton_female);
        this.radioButtonMale.setChecked(true);

//        Spinner spinner = findViewById(R.id.planets_spinner);
//        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.planets_array, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);


        Spinner imageSpinner = findViewById(R.id.image_spinner);
        SimpleImageArrayAdapter imageAdapter
                = new SimpleImageArrayAdapter(this, listImage);
        imageSpinner.setAdapter(imageAdapter);
        imageSpinner.setOnItemSelectedListener(this);

    }

    boolean isValid() {
        return !edtAddName.getText().toString().isEmpty() &&
                !edtAddContent.getText().toString().isEmpty();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        initView();

        btnConfirm.setOnClickListener(v -> {
            if(isValid()) {
                MyModel newItem = new MyModel();
                newItem.setId(0);
                newItem.setJobName(edtAddName.getText().toString());
                newItem.setJobDetail(edtAddContent.getText().toString());
                dbHelper.addData(newItem);
                finish();
            } else {
                Toast.makeText(this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });

        editTextSelectTime.setOnClickListener(v -> {
            showDateTimePicker();
        });

         // When radio button "Female" checked change.
        this.radioButtonMale.setOnCheckedChangeListener(this::doOnGameCharacterChanged);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String spinnerResult = String.valueOf(parent.getItemAtPosition(position));
        pickedImage = listImage[position];
        System.out.println(pickedImage + "TOM TOM TOM");
        System.out.println(spinnerResult);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private void doOnGameCharacterChanged(CompoundButton buttonView, boolean isChecked)  {
        RadioButton radio =(RadioButton) buttonView;
        System.out.println("RadioButton "+ radio.getText()+" : "+ isChecked);
    }

    public void showDateTimePicker() {
       final Calendar currentDate = Calendar.getInstance();
       date = Calendar.getInstance();
       new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
        date.set(year, month, dayOfMonth);
            new TimePickerDialog(this, (view1, hourOfDay, minute) -> {
                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                date.set(Calendar.MINUTE, minute);
                System.out.println("The choosen one " +
                        date.get(Calendar.DAY_OF_MONTH) + "/"
                        + date.get(Calendar.MONTH) + "/"
                        + date.get(Calendar.YEAR)
                );
                String pickedDate = date.get(Calendar.DAY_OF_MONTH) + "/"
                        + date.get(Calendar.MONTH) + "/"
                        + date.get(Calendar.YEAR);

                editTextSelectTime.setText(pickedDate);
            },
            currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
       },
        currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    public void onCheckboxClicked(View view) {
    // Is the view now checked?
    boolean checked = ((CheckBox) view).isChecked();

    // Check which checkbox was clicked
        if (view.getId() == R.id.checkbox) {
            if (checked) {
                System.out.println("Check check");
            } else {
                System.out.println("Uncheck Uncheck");
            }
        }
    }
}