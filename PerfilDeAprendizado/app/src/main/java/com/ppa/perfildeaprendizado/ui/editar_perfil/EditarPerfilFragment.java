package com.ppa.perfildeaprendizado.ui.editar_perfil;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.ppa.perfildeaprendizado.R;
import com.ppa.perfildeaprendizado.data.model.Aluno;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class EditarPerfilFragment extends Fragment {

    private EditarPerfilViewModel editarPerfilViewModel;
    private EditText nome;
    private EditText matricula;
    private EditText dataNascimento;
    private EditText senha;
    private EditText confirmarSenha;
    private Spinner genero;
    private LinearLayout form;
    private Button enviar;
    private DatePickerDialog datePickerDialog;
    private Calendar dataNascAux = Calendar.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        editarPerfilViewModel =
                ViewModelProviders.of(this).get(EditarPerfilViewModel.class);
        Aluno aluno = (Aluno) getActivity().getIntent().getSerializableExtra(Aluno.class.getSimpleName());
        View root = inflater.inflate(R.layout.fragment_editar_perfil, container, false);
        form = root.findViewById(R.id.form);
        nome = root.findViewById(R.id.nome);
        matricula = root.findViewById(R.id.matricula);
        dataNascimento = root.findViewById(R.id.dataNascimento);
        senha = root.findViewById(R.id.senha);
        confirmarSenha = root.findViewById(R.id.confirmarSenha);
        genero = root.findViewById(R.id.genero);
        enviar = root.findViewById(R.id.enviar);
        dataNascimento.setFocusable(false);
        dataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dataNascAux.set(year, month, dayOfMonth);
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        dataNascimento.setText(formato.format(dataNascAux.getTime()));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        nome.setText(aluno.getNome());
        matricula.setText(aluno.getMatricula());
        if(aluno.getDataNascimento() != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            dataNascimento.setText(formato.format(aluno.getDataNascimento()));
        }
        matricula.setEnabled(false);
        return root;
    }
}
